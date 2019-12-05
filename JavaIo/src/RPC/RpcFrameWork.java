package RPC;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

public class RpcFrameWork {

    /**
     * 暴露服务
     */
    public static void export(final Object service,int port) throws Exception {
        if(service==null){
            throw new IllegalArgumentException("service instance ==null");
        }
        if(port<0||port>65535){
            throw new IllegalArgumentException("invalid port"+port);
        }
        System.out.println("Export service " + service.getClass().getName() + " on port " + port);
        ServerSocket serverSocket = new ServerSocket(port);
        for(;;){
            try {
                final Socket socket = serverSocket.accept();
                new Thread(()->{
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            String methodName = input.readUTF();
                            Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                            Object[] arguments = (Object[]) input.readObject();
                            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                            try {
                                Method method = service.getClass().getMethod(methodName,parameterTypes);
                                Object result = method.invoke(service,arguments);
                                out.writeObject(result);
                            }catch (Exception e){
                                e.printStackTrace();
                                out.close();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            input.close();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static <T> T refer(final Class<T> interfaceClass,final String host,final int port) throws Exception{
        if (interfaceClass == null)
            throw new IllegalArgumentException("Interface class == null");
        if (! interfaceClass.isInterface())
            throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");
        if (host == null || host.length() == 0)
            throw new IllegalArgumentException("Host == null!");
        if (port <= 0 || port > 65535)
            throw new IllegalArgumentException("Invalid port " + port);
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket(host,port);
                try {
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        outputStream.writeUTF(method.getName());
                        outputStream.writeObject(method.getParameterTypes());
                        outputStream.writeObject(args);
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        try {
                            Object result = inputStream.readObject();
                            if(result instanceof Throwable){
                                throw (Throwable) result;
                            }
                            return result;
                        }finally {
                            inputStream.close();
                        }
                    }finally {
                        outputStream.close();
                    }
                }finally {
                    socket.close();
                }
            }
        });
    }
}

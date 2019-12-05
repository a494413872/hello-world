package BIO;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    public static void main(String[] args) throws IOException {
        //创建serverSocket，监听8080端口
        ServerSocket serverSocket = new ServerSocket(8080);
        //启动线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        while(true){
            //服务器使用accept阻塞，等待客户端的socket链接
            Socket socket = serverSocket.accept();//阻塞点1.
            System.out.println("服务器获取到一个客户端");
            //把任务提交到线程池处理
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    hand(socket);
                }
            });
        }
    }

    private static void hand(Socket socket) {
        try {
            byte[] bytes = new byte[1024];
            //读取socket字节流
            InputStream inputStream = socket.getInputStream();
            while (true){
                int read = inputStream.read(bytes);//阻塞点2.
                if(read!=-1){
                    //对读取内容进行处理
                    System.out.println(new String(bytes,0,read));
                }else {
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("关闭socket");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

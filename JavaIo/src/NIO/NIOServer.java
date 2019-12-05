package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    private static int flag = 1;
    private static int blockSize = 1024;
    //开辟发送数据缓冲区
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
    private static ByteBuffer receivedBuffer = ByteBuffer.allocate(blockSize);
    private static Selector selector;


    public static void hanleKey(SelectionKey selectionKey) throws IOException {
            ServerSocketChannel server = null;
            SocketChannel client = null;
            String sendText;
            int count = 0;
            if(selectionKey.isAcceptable()){
                server = (ServerSocketChannel) selectionKey.channel();
                /**
                 通过ServerSocketChannel.accept()方法监听新进来的连接。
                 当accept()方法返回的时候，它返回一个包含新进来的连接的SocketChannel。
                 因此， accept()方法会一直阻塞到有新连接到达。通常不会仅仅只监听一个连接
                 */
                client = server.accept();
                client.configureBlocking(false);
                //通道已经连接进来之后，给通道注册读权限
                client.register(selector,SelectionKey.OP_READ);
                System.out.println("客户端连接进来");
            }else if(selectionKey.isReadable()){
                client = (SocketChannel) selectionKey.channel();
                count = client.read(receivedBuffer);
                if(count>0){
                    //flip，把position设置为0，把limit设置为当前值。
                    receivedBuffer.flip();
                    byte[] bytes = new byte[receivedBuffer.limit()];
                    receivedBuffer.get(bytes);
                    String receiveText = new String(bytes);
                    System.out.println("服务端接收到客户端的信息："+receiveText);
                    //读取完成后，给数据赋予写权限
                    client.register(selector,SelectionKey.OP_WRITE);
                }
                receivedBuffer.clear();
            }else if(selectionKey.isWritable()){
                sendBuffer.clear();
                client = (SocketChannel) selectionKey.channel();
                sendText = "hi i m server"+flag++;
                sendBuffer.put(sendText.getBytes());
                sendBuffer.flip();
                client.write(sendBuffer);
                //写完再赋予读权限
                client.register(selector,SelectionKey.OP_READ);
            }

    }
    public static void main(String[] args) throws Exception {
            int port = 8080;
        //初始化
        //ServerSocketChannel是一个可以监听新进来的TCP连接的通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);
        //获取serverSocket
        ServerSocket serverSocket = serverSocketChannel.socket();
        //serverSocket绑定端口
        serverSocket.bind(new InetSocketAddress(port));
        //打开选择器
        selector = Selector.open();
        //注册channel到选择器上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Server start->"+port);
        while (true){
            //获取准备就绪的事件，执行完这个之后selectionKeys里面才会有值。这里拿不到准备就绪的事件会阻塞
            selector.select();
            Set<SelectionKey> selectionKeys =  selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                hanleKey(selectionKey);
                //这里需要移除是因为selector不会自己去删除key，如果不手动移除，下次while循环它还会在selectionKeys里面
                keyIterator.remove();
            }
//            Thread.sleep(Long.parseLong("5000"));
        }
    }
}

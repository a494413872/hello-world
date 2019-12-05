package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NIOClient {
    private static int flag = 1;
    private static int blockSize = 4096;
    //开辟发送数据缓冲区
    private static ByteBuffer sendBuffer = ByteBuffer.allocate(blockSize);
    private static ByteBuffer receivedBuffer = ByteBuffer.allocate(blockSize);
    private final  static InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1",7080);

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(serverAddress);
        Set<SelectionKey> selectionKeys;
        SocketChannel client;

        String sendText;
        while (true){
            Thread.sleep(Long.parseLong("5000"));
            selector.select();
            selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                if(selectionKey.isConnectable()){
                    System.out.println("客户端开始连接");
                    client = (SocketChannel) selectionKey.channel();
                    if(client.isConnectionPending()){
                        client.finishConnect();
                        System.out.println("客户端连接完成");
                        sendBuffer.clear();
                        sendBuffer.put("hello server".getBytes());
                        sendBuffer.flip();
                        client.write(sendBuffer);
                        client.register(selector,SelectionKey.OP_READ);
                    }else{
                        System.out.println("还没有连接完成");
                    }
                }else if(selectionKey.isReadable()){
                        client = (SocketChannel) selectionKey.channel();
                        receivedBuffer.clear();
                        int count = client.read(receivedBuffer);
                        if(count>0){
                            String receiveText = new String(receivedBuffer.array());
                            System.out.println("客户端接受服务器数据："+receiveText);
                            client.register(selector,SelectionKey.OP_WRITE);
                        }
                } else if(selectionKey.isWritable()){
                        client = (SocketChannel) selectionKey.channel();
                        sendBuffer.clear();
                        sendText = "hi i m client"+flag++;
                        sendBuffer.put(sendText.getBytes());
                        sendBuffer.flip();
                        client.write(sendBuffer);
//                    System.out.println("客户端发送给服务器数据："+sendText);
                    client.register(selector,SelectionKey.OP_READ);
                }

            }
            selectionKeys.clear();
        }

    }
}

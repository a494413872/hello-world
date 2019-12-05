package AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AioClient {


    private AsynchronousSocketChannel client;
    public AioClient(String host,int port) throws IOException, ExecutionException, InterruptedException {
        client= AsynchronousSocketChannel.open();
        Future<?> future =client.connect(new InetSocketAddress(host,port));
        future.get();
        System.out.println("执行完毕");
    }
    public void write(byte b){
        ByteBuffer bf = ByteBuffer.allocate(32);
        bf.put(b);
        bf.flip();
        client.write(bf);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        AioClient client = new AioClient("localhost",7080);
        client.write((byte) 11);
    }
}

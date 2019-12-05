package AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class AioServer {

    public AioServer(int port) throws IOException {
        AsynchronousServerSocketChannel listener = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(port));
        listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {

            @Override
            public void completed(AsynchronousSocketChannel result, Object attachment) {
                //异步IO完成
                System.out.println("连接完成");
                //监听下一个事件。
                listener.accept(null,this);
                try {
                    handler(result);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                //异步IO失败
                System.out.println("连接失败");
            }
        });
    }
    public void handler(AsynchronousSocketChannel ch) throws ExecutionException, InterruptedException {
        //真正的逻辑
        ByteBuffer bf = ByteBuffer.allocate(32);
        //把数据读到缓冲区，第一步返回一个Future，然后通过get阻塞主程序，知道future执行完。
        ch.read(bf).get();
        //把缓冲区的指针指向头部，便于读取数据，并设置做多只能读取之前写的数据的量。
        bf.flip();
        System.out.println("服务端接受："+bf.get());
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        AioServer server = new AioServer(7080);
        System.out.println("服务端监听开始");
        Thread.sleep(10000);
    }
}

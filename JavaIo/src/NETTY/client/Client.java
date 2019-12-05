package NETTY.client;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        //客户端
        ClientBootstrap clientBootstrap = new ClientBootstrap();
        //线程池
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //socket工厂
        clientBootstrap.setFactory(new NioClientSocketChannelFactory(boss,worker));

        //管道工厂
        clientBootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                //接收消息解码方式
                pipeline.addLast("decoder",new StringDecoder());
                //服务器发送数据的解码方式
                pipeline.addLast("encoder",new StringEncoder());
                pipeline.addLast("serverHandler",new ClientHandler());
                return pipeline;
            }
        });
        //链接服务端
        ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1",8090));
        //联通之后
        Channel channel = connect.getChannel();
        System.out.println(" client start ");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入");
            channel.write(scanner.next());
        }
    }

}

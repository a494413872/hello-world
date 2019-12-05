package NETTY.custompackage.client;

import NETTY.custompackage.code.RequtestEncoder;
import NETTY.custompackage.code.ResponseDecoder;
import NETTY.custompackage.fubem.FightRequest;
import NETTY.custompackage.model.Request;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

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
                //解码服务器返回对象
                pipeline.addLast("decoder",new ResponseDecoder());
                //编发发送到服务器的对象
                pipeline.addLast("encoder",new RequtestEncoder());
                pipeline.addLast("serverHandler",new ClientHandler());
                return pipeline;
            }
        });
        //链接服务端
        ChannelFuture connect = clientBootstrap.connect(new InetSocketAddress("127.0.0.1",10110));
        Channel channel = connect.sync().getChannel();
        System.out.println(" client start ");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入");
            int fubendId = Integer.parseInt(scanner.nextLine());
            int count = Integer.parseInt(scanner.nextLine());
            FightRequest fightRequest = new FightRequest();
            fightRequest.setFunbenId(fubendId);
            fightRequest.setCount(count);
            Request request = new Request();
            request.setModule((short) 1);
            request.setCmd((short) 1);
            request.setData(fightRequest.getBytes());
            //发送请求
            channel.write(request);
        }
    }

}

package NETTY.custompackage.server;

import NETTY.custompackage.code.RequestDecoder;
import NETTY.custompackage.code.ResponseEncoder;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {
        //服务类
        ServerBootstrap bootstrap = new ServerBootstrap();
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();
        //设置nio socket工厂
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));
        //设置管道的工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                //接收消息解码方式
                pipeline.addLast("decoder",new RequestDecoder());
                //服务器发送数据的解码方式
                pipeline.addLast("encoder",new ResponseEncoder());
                pipeline.addLast("serverHandler",new ServerdHandler());
                return pipeline;
            }
        });
        bootstrap.bind(new InetSocketAddress(10110));
    }
}

package NETTY.IdleHandler;

import NETTY.server.ServerdHandler;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

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
                //设置netty的定时器
                HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
                //设置心跳检测
                /**
                 * 第一个参数，计时器
                 * 第二个参数，读超时时间
                 * 第三个参数，写超时时间
                 * 第四个参数读写超时时间
                 */
                pipeline.addLast("idle",new IdleStateHandler(hashedWheelTimer,5,6,10));
                //接收消息解码方式
                pipeline.addLast("decoder",new StringDecoder());
                //服务器发送数据的解码方式
                pipeline.addLast("encoder",new StringEncoder());
                pipeline.addLast("idleHandler",new IdleHandler());
                return pipeline;
            }
        });
        bootstrap.bind(new InetSocketAddress(8090));
    }
}

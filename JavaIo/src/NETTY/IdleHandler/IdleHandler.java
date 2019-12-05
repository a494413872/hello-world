package NETTY.IdleHandler;

import org.jboss.netty.channel.*;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IdleHandler extends IdleStateAwareChannelHandler {
    private  int i=0;

    @Override
    public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
        i++;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss");
        System.out.println(e.getState()+" "+ simpleDateFormat.format(new Date()));
        ctx.getChannel().write("pleas keep alive "+i);
        if(i==5){
            //发送消息成功后再关闭链接
            ctx.getChannel().write("timeout you will close.").addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    ctx.getChannel().close();
                }
            });

        }

    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("收到 "+e.getMessage());
    }


}

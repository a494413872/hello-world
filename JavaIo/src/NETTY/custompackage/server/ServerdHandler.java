package NETTY.custompackage.server;

import NETTY.custompackage.fubem.FightRequest;
import NETTY.custompackage.fubem.FightResponse;
import NETTY.custompackage.model.Request;
import NETTY.custompackage.model.Response;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;

public class ServerdHandler extends SimpleChannelHandler {
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("receivedMessage");
        Request request = (Request) e.getMessage();
        if(request.getModule()==1){
            if(request.getCmd()==1){
                FightRequest fightRequest = new FightRequest();
                fightRequest.readFromBytes(request.getData());
                System.out.println("fubenId:"+fightRequest.getFunbenId()+" count:"+fightRequest.getCount());
                FightResponse fightResponse = new FightResponse();
                fightResponse.setGold(666);
                Response response = new Response();
                response.setModule((short) 1);
                response.setCmd((short) 1);
                response.setStateCode(1);
                response.setData(fightResponse.getBytes());
                ctx.getChannel().write(response);
            }else {

            }
        }else {

        }
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelOpen");
        super.channelOpen(ctx, e);
    }
    /**
    链接已经建立关闭的时候会触发
     */
    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    /**
     * channel关闭的时候触发
     * @param ctx
     * @param e
     * @throws Exception
     */

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }
}

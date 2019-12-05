package NETTY.custompackage.client;

import NETTY.custompackage.fubem.FightResponse;
import NETTY.custompackage.model.Response;
import org.jboss.netty.channel.*;

public class ClientHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("client received");
        Response response = (Response) e.getMessage();
        if(response.getModule()==1){
            if(response.getCmd()==1){
                FightResponse fightResponse = new FightResponse();
                fightResponse.readFromBytes(response.getData());
                System.out.println("gold:"+fightResponse.getGold());
            }else {

            }
        }else {

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("client exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("client channelOpen");
        super.channelOpen(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("client channelConnected");
        super.channelConnected(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("client channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("client channelClosed");
        super.channelClosed(ctx, e);
    }
}

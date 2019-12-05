package NETTY.custompackage.code;

import NETTY.custompackage.constant.ConstantValue;
import NETTY.custompackage.model.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/*
请求编码对象
 */
public class RequtestEncoder extends OneToOneEncoder {

    @Override
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object o) throws Exception {
        //对象强转为请求。
        Request request = (Request)(o);
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        //下面把requtest转化为二进制数组。结构是。包头+模块号+命令号+长度+数据
        //包头，是为了标记这个数据包的开始
        buffer.writeInt(ConstantValue.FLAG);
        //写模块号.
        buffer.writeShort(request.getModule());
        //cmd
        buffer.writeShort(request.getCmd());
        //写入数据长度
        buffer.writeInt(request.getDataLength());
        //写入数据
        if(request.getData()!=null){
            buffer.writeBytes(request.getData());
        }
        return buffer;
    }
}

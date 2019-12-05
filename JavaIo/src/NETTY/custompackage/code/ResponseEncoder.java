package NETTY.custompackage.code;

import NETTY.custompackage.constant.ConstantValue;
import NETTY.custompackage.model.Request;
import NETTY.custompackage.model.Response;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/*
回复编码对象
回复对象比请求对象多一个状态码字段
包头+模块号+命令号+状态码+长度+数据
 */
public class ResponseEncoder extends OneToOneEncoder {

    @Override
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object o) throws Exception {
        //对象强转为请求。
        Response response = (Response)(o);
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        //下面把requtest转化为二进制数组。结构是。包头+模块号+命令号+长度+数据
        //包头，是为了标记这个数据包的开始
        buffer.writeInt(ConstantValue.FLAG);
        //写模块号.
        buffer.writeShort(response.getModule());
        //cmd
        buffer.writeShort(response.getCmd());
        //比请求多一个状态码
        buffer.writeInt(response.getStateCode());
        //写入数据长度
        buffer.writeInt(response.getDataLength());
        //写入数据
        if(response.getData()!=null){
            buffer.writeBytes(response.getData());
        }


        return buffer;
    }
}

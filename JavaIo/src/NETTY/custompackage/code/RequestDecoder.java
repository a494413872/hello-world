package NETTY.custompackage.code;

import NETTY.custompackage.model.Request;
import NETTY.custompackage.constant.ConstantValue;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

//解码器
//FrameDecoder 可以和onetooneencoder配合使用。解码请求。
public class RequestDecoder extends FrameDecoder {
    //一个数据包的最短长度。至少要比包头+模块+命令+数据长度要长
    public static int BASE_LINE=4+2+2+4;
    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        //刻度长度必须大于基本长度。
        if(channelBuffer.readableBytes()>=BASE_LINE){
            //记录包头开始的读指针。
            int beginReader = channelBuffer.readerIndex();
            //读取包头
            while (true){
                if(channelBuffer.readInt() == ConstantValue.FLAG){
                    //读取到包头就停止读取。
                    break;
                }
            }
            //读取模块号
            short module = channelBuffer.readShort();
            //读取命令号
            short cmd = channelBuffer.readShort();
            //读取数据长度
            int length = channelBuffer.readInt();
            //后面读取数据，但是这时候数据可能还不完整。
            if(channelBuffer.readableBytes()<length){
                //上面已经读取了12个字节的数据。所以在return null之前
                //需要还原buffer指针。
                channelBuffer.readerIndex(beginReader);
                return null;
            }
            //如果后续数据大于等于数据长度就可以读数据
            byte[] data = new byte[length];
            channelBuffer.readBytes(data);

            Request request = new Request();
            request.setModule(module);
            request.setCmd(cmd);
            request.setData(data);
            //如果return某个对象，就会往下传递这个对象。
            //sendUpStream往下传递
            return request;
        }else{
            //数据不完成，返回null。可以继续接受后续数据。
            return null;
        }


    }
}

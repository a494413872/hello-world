package NETTY.serial;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.nio.channels.Channel;
import java.util.Arrays;

public class Test3 {
    public static void main(String[] args) {
        /**
         * netty jar包里面有一个自动扩容的buffer
         */
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
        buffer.writeInt(101);
        buffer.writeInt(21);
        buffer.writeDouble(80.1d);
        /**
         * 创建一个长度等于写指针的空数组，然后把数据读入比特数组
         */
        byte[] bytes = new byte[buffer.writerIndex()];
        buffer.readBytes(bytes);
        System.out.println(Arrays.toString(bytes));

        ChannelBuffer warpBuffer = ChannelBuffers.wrappedBuffer(bytes);

        System.out.println(warpBuffer.readInt());
        System.out.println(warpBuffer.readInt());
        System.out.println(warpBuffer.readDouble());
    }
}

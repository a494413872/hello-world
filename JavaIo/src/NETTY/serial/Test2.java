package NETTY.serial;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Test2 {
    /**
     * 第二种方式是通过bytebuffer
     */


    public static void main(String[] args) {
        int id =101;
        int age =21;
        /**
         * 不会自动扩容。所以一旦长度超限。
         * 会爆出异常。比如再put一个int就会超限
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putInt(id);
        byteBuffer.putInt(age);
//        byteBuffer.putInt(2);
        byte[] bytes = byteBuffer.array();
        System.out.println(Arrays.toString(bytes));

        ByteBuffer buffer2 = ByteBuffer.wrap(bytes);
        System.out.println( buffer2.getInt());
        System.out.println( buffer2.getInt());

    }
}

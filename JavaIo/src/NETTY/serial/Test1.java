package NETTY.serial;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) throws IOException {
        /**
         * int在内存总占4个字节
         * byte是一个字节
         * 一个字节是8个二进制数字
         */
        int id =1024*10*2*2*2;
        int age =21;
        byte b = (byte)160;

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        /**
         *  arrayOutputStream.write(id);不能使用
         *  write方法，直接把int强转byte。这时候int的高位字节会被丢掉。
         */
        arrayOutputStream.write(int2bytes(id));
        arrayOutputStream.write(int2bytes(age));

        byte[] byteArray = arrayOutputStream.toByteArray();
        System.out.println(Arrays.toString(byteArray));

        /**
         * 反序列化
         */
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        byte[] idBytes = new byte[4];
        byteArrayInputStream.read(idBytes);
        System.out.println(bytes2int(idBytes));

        byte[] ageBytes = new byte[4];
        byteArrayInputStream.read(ageBytes);
        System.out.println(bytes2int(ageBytes));

    }

    /**
     * 把int转化为字节数组。
     * 先写高位在写低位
     * 大端序列
     */
    public static byte[] int2bytes(int i){
        byte[] bytes = new byte[4];
        bytes[0] =(byte)(i>>3*8);
        bytes[1] =(byte)(i>>2*8);
        bytes[2] =(byte)(i>>1*8);
        bytes[3] =(byte)(i>>0*8);
        return bytes;
    }

    /**
     * byte数组转int
     * 按位或运算能保证不同的二进制数字和0进行或运算，留下来的是本身。
     * @param
     * @return
     */
    public static int bytes2int(byte[] bytes){

        return (fixBytes2int(bytes[0])<<3*8)|(fixBytes2int(bytes[1])<<2*8)|(fixBytes2int(bytes[2])<<1*8)|(fixBytes2int(bytes[3])<<0*8);
    }

    /**
     * 对变成负数的byte字节进行还原。
     * @param b
     * @return
     */
    public static int fixBytes2int(byte b){
        return b>=0?b:256+b;
    }


}

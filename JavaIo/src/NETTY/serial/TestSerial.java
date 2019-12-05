package NETTY.serial;

import java.util.Arrays;

/**
 * 这种最简单的序列化，是指定字段的，会产生很多空白的内存。
 * 比较不合理。
 * Protobuffer 序列化更为合理。
 * 它通过重新定义字节结构，压缩字段长度。
 *  例如，一个int类型的数字，转化为字节数组需要占4个字节，32位。
 *  而它先去最末尾七尾，然后数据本身小于0x7f，那么只需要存储最末一个字节就好。第八位是0
 *  如果数据大于0x7f，那么把第八位置1，然后存储下来，元数据右移7位继续循环。
 *  所以最好的情况只占一个字节，最差的情况占用5个字节。
 *
 */
public class TestSerial {
    public static void main(String[] args) {
        TestVo testVo = new TestVo();
        testVo.setAge(11);
        testVo.setAge(22);
        testVo.setName("小明");
        testVo.getSkills().add(1123);
        byte[] bytes = testVo.getBytes();
        System.out.println(Arrays.toString(bytes));
        TestVo decode = new TestVo();
        decode.readFromBytes(bytes);
        System.out.println(decode.getAge());
        System.out.println(decode.getName());
    }
}

import java.nio.ByteBuffer;

/**
 * Created by songjian on 6/5/2018.
 */
public class DirectOOm {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1024; i++) {
            ByteBuffer.allocateDirect(1024*1024*10);
            System.out.println(i);
//            Thread.sleep(100);
//            System.gc();
        }
    }
}

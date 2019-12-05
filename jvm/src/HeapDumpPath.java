import java.util.Vector;

/**
 * Created by songjian on 5/8/2018.
 */
public class HeapDumpPath {
    public static void main(String[] args) throws InterruptedException {
//        Vector v = new Vector();
//        for (int i = 0; i <25 ; i++) {
//            v.add(new byte[1*1024*1024]);
//            Thread.sleep(3000);
//        }
        String str = "$sdf$sdfasdfasd";
        str = str.replaceAll("$sdf$","abc");
        System.out.println(str);
    }
}

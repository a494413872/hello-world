/**
 * Created by songjian on 5/8/2018.
 */
public class HeapSize {
    public static void main(String[] args) throws InterruptedException {
        byte[] b = new byte[4*1024*1024];

        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024+"M");

        System.out.print("free=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024+"M");

        System.out.print("Total=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"M");
        System.gc();
        Thread.sleep(10000);
        System.out.print("Xmx=");
        System.out.println(Runtime.getRuntime().maxMemory()/1024.0/1024+"M");

        System.out.print("free=");
        System.out.println(Runtime.getRuntime().freeMemory()/1024.0/1024+"M");

        System.out.print("Total=");
        System.out.println(Runtime.getRuntime().totalMemory()/1024.0/1024+"M");

    }
}

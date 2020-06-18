package ClassLoad;

public class ErrorUtil {

    static {
        int a=1/0;
    }

    static void halo(){
        System.out.println("halohalo");
    }
}

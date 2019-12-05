import java.util.HashMap;
import java.util.Map;

/**
 * Created by songjian on 5/7/2018.
 */
public class PrintGc {
    static Map map = new HashMap();
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; 1==1 ; i++) {
            new User(i);
            map.put(""+i,new User(i));
            System.out.println(i);
            //根据需求灵活调整是否休眠
//            Thread.sleep(100);
        }
    }
}

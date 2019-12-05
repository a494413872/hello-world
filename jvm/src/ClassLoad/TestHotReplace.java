package ClassLoad;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by songjian on 5/31/2018.
 */
public class TestHotReplace {
    public static Class c;

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, InterruptedException {
        /**
         * 启动监视线程
         */
        WatchThead watchThead = new WatchThead();
        watchThead.start();
        Thread.sleep(1000);
        while (true){
            Method m = c.getDeclaredMethod("say");
            m.setAccessible(true);
            m.invoke(c.newInstance());
            Thread.sleep(3000);
        }
    }
}

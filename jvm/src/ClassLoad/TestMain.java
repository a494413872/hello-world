package ClassLoad;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by songjian on 5/30/2018.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        MyClassLoader classLoader = new MyClassLoader();
        Class c = classLoader.loadClass("ClassLoad.UserToLoad");
        Method m = c.getDeclaredMethod("say");
        m.setAccessible(true);
        m.invoke(c.newInstance());
    }
}

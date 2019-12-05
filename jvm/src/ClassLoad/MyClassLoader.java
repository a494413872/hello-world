package ClassLoad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by songjian on 5/30/2018.
 */
public class MyClassLoader extends ClassLoader{
    private String classPath="D:\\class";
    protected Class<?> findClass(String name){
        byte[] data = new byte[0];
        try {
            //1.获取二进制数据流
            data = loadMyByte(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.把二进制流转化为Class
        Class<?> c = defineClass(name, data, 0, data.length);
        return c;
    }
    private byte[] loadMyByte(String name) throws IOException {
        String sName = name.replace(".",File.separator);
        String fullPath = classPath+ File.separator+sName+".class";
        File file = new File(fullPath);
        FileInputStream fis = new FileInputStream(file);
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return  data;
    }
}

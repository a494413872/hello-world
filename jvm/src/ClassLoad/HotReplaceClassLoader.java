package ClassLoad;

import sun.misc.Launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by songjian on 5/30/2018.
 */
public class HotReplaceClassLoader extends ClassLoader{

    HotReplaceClassLoader(){
        super(Launcher.getLauncher().getClassLoader().getParent());
    }


    private String classPath="F:\\git_workspace\\hello-world\\out\\production\\jvm";

    public Class<?> findClass(String name) throws ClassNotFoundException{

        //1.获取二进制数据流
        byte[] data = new byte[0];
        try {
            data = loadMyByte(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class<?> c = defineClass(name, data, 0, data.length);
        //2.把二进制流转化为Class
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

package ClassLoad;


import java.io.File;

/**
 * Created by songjian on 5/31/2018.
 */
public class WatchThead extends Thread {


    private String classPath="F:\\git_workspace\\hello-world\\out\\production\\jvm";
    private long lastModify;
    private String path = classPath+ File.separator+"ClassLoad"+File.separator+"UserHotReplace.class";
    @Override
    public void run() {
        while (true){
            File file = new File(path);
            long newlastModify = file.lastModified();
            if(file.exists()){
                if(lastModify!=newlastModify){
                    System.out.println("file changed %%%%%%%%%%%%%%%%");
                    /**
                     * 这里一定要new classloader 因为同一个classloader是不能再次加载同一个类的会报异常。
                     */
                    HotReplaceClassLoader hotReplaceClassLoader = new HotReplaceClassLoader();
                    try {
                        TestHotReplace.c =hotReplaceClassLoader.loadClass("ClassLoad.UserHotReplace");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    lastModify=newlastModify;
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        while (true){
            String classPath="F:\\git_workspace\\hello-world\\out\\production\\jvm";
            String path = classPath+ File.separator+"ClassLoad"+File.separator+"UserHotReplace.class";
            File file = new File(path);
            System.out.println(file.lastModified());
            WatchThead.sleep(1000);
        }

    }
}

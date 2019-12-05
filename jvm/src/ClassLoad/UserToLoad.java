package ClassLoad;

/**
 * Created by songjian on 5/30/2018.
 */
public class UserToLoad {
    public void say(){
        System.out.println("my class loader is :"+getClass().getClassLoader().getClass()+"12312312");
    }
}

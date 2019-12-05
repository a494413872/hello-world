package ClassLoad;

/**
 * Created by songjian on 5/31/2018.
 */
public class UserHotReplace {
//    UserToLoad userToLoad = new UserToLoad();
    public void say(){
        System.out.println("my class loader is :"+getClass().getClassLoader().getClass());
        System.out.println("VVV");

    }
}

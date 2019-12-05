package zk;

/**
 * Created by songjian on 9/29/2018.
 */
public class Webservice {

    /**
     * 这是你要调用的webservice
     * @param string
     */
    public void invoke(String string){
        System.out.println("这里是你的调用逻辑");
        System.out.println("这是你要传的参数："+string);
    }

}

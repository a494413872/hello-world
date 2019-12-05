package zkClient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import java.util.List;

/**
 * Created by songjian on 4/20/2018.
 */
public class zkClientBase {

    static final String CONNECT_ADDR = "10.200.4.92:2181,10.200.4.93:2181,10.200.4.94:2181";
    static final int SESSION_TIMEOUT = 5000;
    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient(new ZkConnection(CONNECT_ADDR),SESSION_TIMEOUT);

        //1.创建节点
//        zkClient.createEphemeral("/temp");
//        zkClient.createPersistent("/super/p1",true);
//        Thread.sleep(5000);
//        zkClient.deleteRecursive("/super");
//        zkClient.deleteRecursive("/temp");
        //2.读取数据
//        zkClient.createPersistent("/super","1");
//        zkClient.createPersistent("/super/p1","2");
//        zkClient.createPersistent("/super/p2","3");
//        List<String> strings = zkClient.getChildren("/super");
//        for (String string : strings) {
//            System.out.println(string);
//            String rp = "/super/"+string;
//            System.out.println(zkClient.readData(rp));
//        }
        //3.写数据
        zkClient.writeData("/super/p1","新内容");
        System.out.println((String) zkClient.readData("/super/p1"));
        System.out.println(zkClient.exists("/super/p1"));
    }
}

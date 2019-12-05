package zkClient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

import java.util.List;

/**
 * Created by songjian on 4/20/2018.
 */
public class clientWatch {
    static final String CONNECT_ADDR = "10.200.4.92:2181,10.200.4.93:2181,10.200.4.94:2181";
    static final int SESSION_TIMEOUT = 5000;
    public static void main(String[] args) throws InterruptedException {
        ZkClient zkClient = new ZkClient(new ZkConnection(CONNECT_ADDR),SESSION_TIMEOUT);
        //只监听节点的增删，不监听更新
//        zkClient.subscribeChildChanges("/super", new IZkChildListener() {
//            @Override
//            public void handleChildChange(String s, List<String> list) throws Exception {
//                System.out.println("++++"+s);
//                System.out.println("++++"+list);
//            }
//        });
//        Thread.sleep(10000);
        //监听节点更新变化
        zkClient.subscribeDataChanges("/super/p1", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("+++++节点变更+++++"+s+":"+o.toString());
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("+++++删除+++++"+s);
            }
        });
        zkClient.writeData("/super/p1","sssdfs");
        Thread.sleep(1000000);
    }
}

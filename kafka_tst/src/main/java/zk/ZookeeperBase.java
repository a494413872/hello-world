package zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by songjian on 4/9/2018.
 */
public class ZookeeperBase {
    static final String CONNECT_ADDR = "10.200.4.92:2181,10.200.4.93:2181,10.200.4.94:2181";
    static final int SESSION_TIMEOUT = 5000;
    static final CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZooKeeper zk = new ZooKeeper(CONNECT_ADDR, SESSION_TIMEOUT, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                //获取时间状态
                Event.KeeperState keeperState = watchedEvent.getState();
                Event.EventType eventType = watchedEvent.getType();
                if(Event.KeeperState.SyncConnected==keeperState){
                    if(Event.EventType.None == eventType){
                        connectedSemaphore.countDown();
                        System.out.println("zk 建立连接");
                    }
                }
            }
        });
        connectedSemaphore.await();
        String rst = zk.create("/testRoot","testRoot".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        System.out.println(rst);
//        zk.create("/testRoot/child","/testRoot/child".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//        Thread.sleep(8000);

//        zk.delete("/testRoot",-1, new AsyncCallback.VoidCallback(
//        ) {
//            public void processResult(int i, String s, Object o) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(i);
//                System.out.println(s);
//                System.out.println(o);
//            }
//
//        },"a");
//        Thread.sleep(10000);
//        byte[] data = zk.getData("/testRoot",false,null);
//        System.out.println(new String(data));
//        List<String> list = zk.getChildren("/testRoot",false);
//        for (String s : list) {
//            System.out.println(s+":"+new String(zk.getData("/testRoot/"+s,false,null)));
//        }
//          zk.setData("/testRoot","ddss".getBytes(),-1);
        System.out.println(zk.exists("/testRoot",false));

        System.out.println("继续执行");
    }

}

package zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by songjian on 4/10/2018.
 */
public class ZookeeperWatcher implements Watcher{
    AtomicInteger seq = new AtomicInteger();
    private static final int SESSION_TIMEOUT = 10000;
    private static final String ADDRESS="10.200.4.92:2181,10.200.4.93:2181,10.200.4.94:2181";
    private static final String PARENT_PATH = "/p";
    private static final String CHILD_PATH="/p/c";
    private static final String LOG_PREFIX_OF_MAIN = "【Main】";

    private ZooKeeper zk=null;
    private CountDownLatch cd = new CountDownLatch(1);


    public void createConnection(String connectAttr,int sessionTimeout) throws InterruptedException, IOException {
        this.releaseConnection();
        zk = new ZooKeeper(connectAttr,sessionTimeout,this);
        System.out.println("建立连接");
        cd.await();
    }

    public void releaseConnection() throws InterruptedException {
        if(this.zk!=null){
            this.zk.close();
        }
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println("进入process event"+watchedEvent);

        Event.KeeperState keeperState=watchedEvent.getState();
        Event.EventType eventType = watchedEvent.getType();
        String path = watchedEvent.getPath();
        String logPrefix="process seq:"+this.seq.incrementAndGet();

        System.out.println("keeperState:"+keeperState.toString());
        System.out.println("eventType"+eventType.toString());
        System.out.println("path:"+path);

        if(Event.KeeperState.SyncConnected==keeperState){
            if(Event.EventType.None==eventType){
                cd.countDown();
            }
        }


    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZookeeperWatcher zookeeperWatcher = new ZookeeperWatcher();
        zookeeperWatcher.createConnection(ADDRESS,SESSION_TIMEOUT);
        zookeeperWatcher.zk.create(PARENT_PATH, String.valueOf(System.currentTimeMillis()).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zookeeperWatcher.zk.create(CHILD_PATH, String.valueOf(System.currentTimeMillis()).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        zookeeperWatcher.zk.exists(CHILD_PATH,true);
        Thread.sleep(100000);
        String s = "null";
        System.out.println("null"==s);
    }
}

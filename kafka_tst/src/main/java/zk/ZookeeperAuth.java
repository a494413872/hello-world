package zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by songjian on 4/11/2018.
 */
public class ZookeeperAuth implements Watcher {
    AtomicInteger seq = new AtomicInteger();
    private static final int SESSION_TIMEOUT = 10000;
    private static final String ADDRESS="10.200.4.92:2181,10.200.4.93:2181,10.200.4.94:2181";
    private static final String PATH = "/testAuth";
    private static final String DEL_PATH="/testAuth/delPath";
    final static String authentication_type="digest";
    final static String corrrectionAuthtication ="123456";
    final static String badAuth="678236";


    static ZooKeeper zk=null;
    static CountDownLatch cd = new CountDownLatch(1);


    public void createConnection(String connectAttr,int sessionTimeout) throws InterruptedException, IOException {
        this.releaseConnection();
        zk = new ZooKeeper(connectAttr,sessionTimeout,this);
        zk.addAuthInfo(authentication_type,corrrectionAuthtication.getBytes());
        System.out.println("建立连接");
        cd.await();
    }

    public void releaseConnection() throws InterruptedException {
        if(this.zk!=null){
            this.zk.close();
        }
    }

    public void process(WatchedEvent watchedEvent) {
        Event.KeeperState keeperState=watchedEvent.getState();
        Event.EventType eventType = watchedEvent.getType();
        String path = watchedEvent.getPath();
        String logPrefix="【watcher-"+this.seq.incrementAndGet()+"】";
        System.out.println(logPrefix);
        System.out.println("keeperState:"+keeperState.toString());
        System.out.println("eventType"+eventType.toString());
        System.out.println("path:"+path);
        if(Event.KeeperState.SyncConnected==keeperState){
            if(Event.EventType.None==eventType){
                System.out.println("成功连接zk 服务器");
                cd.countDown();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZookeeperAuth zookeeperAuth = new ZookeeperAuth();
        zookeeperAuth.createConnection(ADDRESS,SESSION_TIMEOUT);
        if (zk.exists(PATH,true)!=null) {
            if(zk.exists(DEL_PATH,true)!=null){
                zk.delete(DEL_PATH,-1);
            }
            zk.delete(PATH,-1);
        }
        zk.create(PATH,"init content".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        if(zk.exists(DEL_PATH,true)!=null){
                zk.delete(DEL_PATH,-1);
        }
        zk.create(DEL_PATH,"delete content".getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        getDataByNoAuth();
        getDataByErrorAuth();
        getDataByCorrectAuth();
        Thread.sleep(10000);
    }

    private static void getDataByErrorAuth() throws InterruptedException, IOException, KeeperException {
        System.out.println("【使用错误的授权信息】");
        ZooKeeper badZk= new ZooKeeper(ADDRESS,2000,null);
        badZk.addAuthInfo(authentication_type,badAuth.getBytes());
        Thread.sleep(2000);
        try {
            System.out.println("成功获取数据:"+badZk.getData(PATH,false,null));
        }catch (Exception e){
            System.out.println("获取信息失败，失败原因："+e.getMessage());
        }

    }
    private static void getDataByNoAuth() throws InterruptedException, IOException, KeeperException {
        System.out.println("【使用无授权信息】");
        ZooKeeper noZk= new ZooKeeper(ADDRESS,2000,null);
        Thread.sleep(2000);
        try {
            System.out.println("成功获取数据:"+noZk.getData(PATH,false,null));
        }catch (Exception e){
            System.out.println("获取信息失败，失败原因："+e.getMessage());
        }

    }
    private static void getDataByCorrectAuth() throws InterruptedException, IOException, KeeperException {
        System.out.println("【使用正确的的授权信息】");
        ZooKeeper corrZk= new ZooKeeper(ADDRESS,2000,null);
        corrZk.addAuthInfo(authentication_type,corrrectionAuthtication.getBytes());
        Thread.sleep(2000);
        try {
            System.out.println("成功获取数据:"+new String(corrZk.getData(PATH,false,null)));
        }catch (Exception e){
            System.out.println("获取信息失败，失败原因："+e.getMessage());
        }

    }



}

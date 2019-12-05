package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by songjian on 4/24/2018.
 */
public class CuratorBase {
    static final String CONNECT_ADDR = "10.200.4.92:2181,10.200.4.93:2181,10.200.4.94:2181";
    static final int SESSION_TIMEOUT = 5000;
    public static void main(String[] args) throws Exception {
        //1.重试策略，时间为1s，重试十次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,10);
        //2 创建工厂
        CuratorFramework cf = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR)
                .sessionTimeoutMs(SESSION_TIMEOUT).retryPolicy(retryPolicy).build();
        //开启链接
        cf.start();

        //创建节点
        cf.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/super/c1","23423".getBytes());

        //删除节点
//        cf.delete().deletingChildrenIfNeeded().forPath("/super");

        //读取节点
//        String r1 = "******"+new String(cf.getData().forPath("/super/c1"));
//        System.out.println(r1);
//        cf.setData().forPath("/super/c1","1122".getBytes());
//        String r2 = "******"+new String(cf.getData().forPath("/super/c1"));
//        System.out.println(r2);

        //绑定回调函数
//        ExecutorService pool = Executors.newCachedThreadPool();
//        cf.getData().inBackground(new BackgroundCallback() {
//            @Override
//            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
//                System.out.println("*************acode:"+curatorEvent.getResultCode());
//                System.out.println("*************type:"+curatorEvent.getType());
//                System.out.println("*************线程为："+ Thread.currentThread().getName());
//            }
//        },pool).forPath("/super/c1");

        //获取子节点
        List<String> stringList = cf.getChildren().forPath("/super");
        for (String s : stringList) {
            System.out.println("+++++++++"+s);
        }
        //节点是否存在,为空不存在。
        System.out.println("+++++++++"+cf.checkExists().forPath("/super"));

        Thread.sleep(10000000);
    }
}

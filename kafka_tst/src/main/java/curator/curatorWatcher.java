package curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by songjian on 4/24/2018.
 */
public class curatorWatcher {
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

        //建立一个cache缓存
//        final NodeCache nodeCache = new NodeCache(cf,"/super",false);
//        nodeCache.start(true);
//
//        nodeCache.getListenable().addListener(new NodeCacheListener() {
//            @Override
//            public void nodeChanged() throws Exception {
//                System.out.println(nodeCache.getPath());
//                System.out.println(new String(nodeCache.getCurrentData().getData()));
//                System.out.println(nodeCache.getCurrentData().getStat());
//                System.out.println("------------------------");
//            }
//        });

        //另外一种缓存
        //第三个参数是否接受节点变更内容
        PathChildrenCache cache = new PathChildrenCache(cf,"/super",true);
        //在初始化进行缓存监听
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                switch (pathChildrenCacheEvent.getType()){
                    case CHILD_ADDED:
                        System.out.println("CHILD_ADDED:"+pathChildrenCacheEvent.getData().getPath());
                        System.out.println("CHILD_ADDED:"+new String(pathChildrenCacheEvent.getData().getData()));
                        break;
                    case CHILD_UPDATED:
                        System.out.println("CHILD_UPDATED:"+pathChildrenCacheEvent.getData().getPath());
                        System.out.println("CHILD_UPDATED:"+new String(pathChildrenCacheEvent.getData().getData()));
                        break;
                    case CHILD_REMOVED:
                        System.out.println("CHILD_REMOVED:"+pathChildrenCacheEvent.getData().getPath());
                        System.out.println("CHILD_REMOVED:"+new String(pathChildrenCacheEvent.getData().getData()));
                        break;
                    default:
                        break;
                }
            }
        });


        Thread.sleep(1000000);
    }
}

package memcachedlock;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.Calendar;
import java.util.Date;

/**
 * 单例模式
 */
public class MemcachedUtil {
    /**
     * memchached IP
     */
    private String SERVER = "10.200.4.77:11211,10.200.4.78:11211";

    private static MemcachedUtil memcachedUtil;


    private MemCachedClient memCachedClient;
    //锁自动过期时间5秒
    private static final int DEFAULT_LOCK_SECCONDS=5;
    //尝试释放锁超时时间
    private static final int DEFAULT_TRYLOCK_TIMEOUT_SECONDS=10;

    /**
     * 单例模式，构造函数私有化
     */
    private MemcachedUtil(){
        //初始化
        init();
    }
    private void init(){
        memCachedClient = new MemCachedClient();
        //配置使用的服务器
        String[] servers = SERVER.split(",");
        //建立通信用的连接池
        SockIOPool pool = SockIOPool.getInstance();
        //设置连接池可用cache服务器列表.格式为ip加端口号
        pool.setServers(servers);
        //如果需要可以设置对应服务器的权重
        /*Integer[] weight = {1,2};
        pool.setWeights(weight);*/
        //设置最小链接数
        pool.setInitConn(5);
        //设置最大连接数
        pool.setMaxConn(100);
        //设置初始化连接数
        pool.setInitConn(5);
        //设置连接池维护线程的睡眠时间，设置0，线程维护时间不启动
        pool.setMaintSleep(30);
        //设置nagle算法，设置为false，因为需要比较及时的相应
        //nagle算法是可以批处理请求，提升tcp包有有效部分大小，提高网络效率。但是要求及时相应所以关掉
        pool.setNagle(false);
        //设置socket读取等待超时时间
        pool.setSocketTO(30);
        //设置连接等待超时时间
        pool.setSocketConnectTO(0);
        //启动
        pool.initialize();
    }

    public boolean set(String key, Object obj, int seconds) {
        if(isEmpty(key)) return false;
        return memCachedClient.set(key, obj, getDateAfter(seconds));
    }
    private boolean isEmpty(String str){
        if(str==null||"".equals(str)){
            return true;
        }
        return false;
    }
    /**
     * 获得当前开始活参数秒的时间日期
     * @Title: getDateAfter
     * @Description:
     * @param
     * @return Date    返回类型
     * @throws
     */
    public static Date getDateAfter(int second) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 添加（同步）
     * @param key
     * @param i
     * @param string
     * @return
     */
    public synchronized boolean addSynchronized(String key, String string, int i) {
        return add(key, string, getDateAfter(i));
    }

    /**
     * 添加
     * @param key
     * @param exp
     * @param obj
     * @return
     */
    public boolean add(String key,Object obj,Date exp){
        if(isEmpty(key)){
            return false;
        }
        return memCachedClient.add(key, obj, exp);
    }

    public boolean tryLock(String lockKey){
        return tryLock(lockKey,DEFAULT_LOCK_SECCONDS,DEFAULT_TRYLOCK_TIMEOUT_SECONDS);
    }
    /**
     * 加锁失败之后需要不停的自旋尝试获取锁。对于自旋需要有一个超时时间不然会一直自旋下去
     * @param lockKey
     * @param lockSec
     * @param timeOutSec
     * @return
     */
    public boolean tryLock(String lockKey, int lockSec, int timeOutSec){

        long start=System.currentTimeMillis();
        while(true){
            boolean  locked=memCachedClient.add(lockKey, lockKey, getDateAfter(lockSec));
            if(locked){
//                System.out.println("lock success");
                return true;
            }else{
                long now=System.currentTimeMillis();
                long costed = now-start;
                if(costed>=timeOutSec*1000){
                    Object lockValue = memCachedClient.get(lockKey);
//                    System.out.println("MemcachedUtil tryLock :" + costed + ", LockKey: " + lockKey + ", LockValue: " + lockValue);
                    return false;
                }
            }
        }
    }



    /**
     * 双重检查机制，创建对象
     */
    public static MemcachedUtil getInstance(){
        if(memcachedUtil==null){
            synchronized (MemcachedUtil.class){
                if(memcachedUtil==null){
                    memcachedUtil = new MemcachedUtil();
                }
            }
        }
        return memcachedUtil;
    }

    public boolean remove(String key){
        if(isEmpty(key)){
            return false;
        }
        return memCachedClient.delete(key);
    }
}

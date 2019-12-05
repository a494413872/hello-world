package redislock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.params.SetParams;

/**
 * 仍然使用单例模式
 * 由于所有的操作都需要finally释放链接，所以采用函数式编程的思想。
 * 链接单台机器可以直接用jedis 或者 jedisPool,链接集群用jedisCluster
 * 我们这里采用连接池的方式，jedisPool。
 *
 */
public class JedisTemplate {

    private String server ="10.200.4.85";
    private int port =6379;

    //唯一连接池
    private static JedisPool jedisPool;

    private static JedisTemplate jedisTemplate;
    //私有构造函数
    private JedisTemplate(){
        init();
    }

    private void init(){
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大闲置个数
        config.setMaxIdle(30);
        //设置最小闲置个数
        config.setMinIdle(10);
        //设置最大连接数
        config.setMaxTotal(50);
        jedisPool = new JedisPool(config,server,port);
    }

    public static JedisTemplate getInstance(){

        if(jedisTemplate==null){
            synchronized (JedisTemplate.class){
                if(jedisTemplate==null){
                    jedisTemplate = new JedisTemplate();
                }
            }
        }
        return jedisTemplate;
    }

    //函数式接口，是指只有一个方法的接口
    //定义一个内部接口
    public interface JedisAction<T>{
        T action(Jedis jedis);
    }

    public interface JedisActionNoResult{
        void action(Jedis jedis);
    }

    private void execute(JedisActionNoResult action){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            action.action(jedis);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }
    private <T> T execute(JedisAction<T> action){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return action.action(jedis);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }finally {
            if(jedis!=null){
                jedis.close();
            }
        }
    }

    /**
     * 设置键值对，永久存在
     * @param key
     * @param value
     */
    public void set(String key,String value){
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.set(key,value);
            }
        });
    }

    public String get(String key){
       return execute(new JedisAction<String>() {
            @Override
            public String action(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    public void delete(String key){
        execute(new JedisActionNoResult() {
            @Override
            public void action(Jedis jedis) {
                jedis.del(key);
            }
        });
    }

    /**
     * 如果key不存在就set,并返回true，如果key存在就返回false
     */
    public Boolean setIfNotExist(String key,String value){
       return execute(new JedisAction<Boolean>(){
            @Override
            public Boolean action(Jedis jedis) {
                return jedis.setnx(key,value)==1;
            }
        });
    }

    /**
     * 如果key不存在就set,并返回true如果key存在就返回false，
     * 并附加上超时时间
     */
    public Boolean setIfNotExistWithTimeout(String key,String value,int sceonds){
        return execute(new JedisAction<Boolean>() {
            @Override
            public Boolean action(Jedis jedis) {
                return "OK".equals(jedis.set(key,value,new SetParams().nx().ex(sceonds)));
            }
        });
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
            boolean  locked= setIfNotExistWithTimeout(lockKey,lockKey,lockSec);
            if(locked){
//                System.out.println("lock success");
                return true;
            }else{
                long now=System.currentTimeMillis();
                long costed = now-start;
                if(costed>=timeOutSec*1000){
                    Object lockValue = get(lockKey);
//                    System.out.println("redis tryLock :" + costed + ", LockKey: " + lockKey + ", LockValue: " + lockValue);
                    return false;
                }
            }
        }
    }





}

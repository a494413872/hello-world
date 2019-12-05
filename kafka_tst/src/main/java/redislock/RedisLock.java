package redislock;

public class RedisLock {
    private  static int a = 0;
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                if(JedisTemplate.getInstance().tryLock("redislock",10,100)){
                    a++;
                    System.out.println(a);
                    JedisTemplate.getInstance().delete("redislock");
                }
            }).start();
        }

       Thread.sleep(10*1000l);

    }
}

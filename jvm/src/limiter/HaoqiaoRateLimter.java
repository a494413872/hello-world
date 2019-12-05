/*
package limiter;

//import com.google.common.util.concurrent.RateLimiter;

*/
/**
 * Created by songjian on 7/3/2018.
 *//*

public class HaoqiaoRateLimter {

    public static void main(String[] args) throws Exception{
        final RedisLimiter rt = RedisLimiter.create(5d,"HAO_QIAO");
//
//        while (true){
//            System.out.println("等待时间："+rt.acquire());
//            System.out.println("调取供应商接口逻辑");
//        }
//        RateLimiter rt = RateLimiter.create(1d);
//        final RateLimiter rateLimiter = RateLimiter.create(5);

        Thread thread1 = new Thread(new Runnable()  {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " ---- start");
                int isSleep = 0;
                while (true) {
                    double acquire = rt.acquire();
                    System.out.println(Thread.currentThread().getName() + " acquire:" + acquire + " time:" + System.currentTimeMillis());
                    isSleep ++;
                    if (isSleep == 9) {
                        try {
                            Thread.sleep(800);
                            System.out.println(Thread.currentThread().getName() + " sleep 2000ms time:" + System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        });

        thread1.start();

        Thread.sleep(Integer.MAX_VALUE);
    }
}
*/

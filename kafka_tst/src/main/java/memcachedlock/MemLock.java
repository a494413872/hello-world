package memcachedlock;

public class MemLock {
    private  static int a = 0;
    public static void main(String[] args) throws InterruptedException {
        MemcachedUtil.getInstance().remove("memlock");
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                if(MemcachedUtil.getInstance().tryLock("memlock")){
                    a++;
                    System.out.println(a);
                    MemcachedUtil.getInstance().remove("memlock");
                }
            }).start();
        }

       Thread.sleep(10*1000l);

    }
}

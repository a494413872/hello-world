/**
 * Created by songjian on 8/24/2017.
 */
public class Dog {
    private int mBarkCount = 0;
    public void Barking() {
        long threadId = Thread.currentThread().getId();
        synchronized (this) {
            System.out.println("线程  = " + threadId + " 正在调用……");
            mBarkCount++;  //每调用一次，就加一次
            System.out.println("狗狗已经叫了:" + mBarkCount + "次");
            System.out.println("线程 = " + threadId + "  调用完成……");

        }
        System.out.println("线程  = " + threadId + "未同步: 开始");

        System.out.println("线程 = "  + threadId + "未同步: 结束");
    }

    public void setMBarkCount(int mBarkCount){
        this.mBarkCount = mBarkCount;
    }

}

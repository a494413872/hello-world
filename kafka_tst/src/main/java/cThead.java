import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * Created by songjian on 3/21/2018.
 */
public class cThead extends  Thread {
    String name;
    KafkaStream<byte[], byte[]> stream;

    public cThead(String name,KafkaStream<byte[], byte[]> stream){
        this.name=name;
        this.stream=stream;
    }

    @Override
    public void run() {
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            System.out.println("thread name ：" + name);
            System.out.println("receive：" + new String(it.next().message()));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

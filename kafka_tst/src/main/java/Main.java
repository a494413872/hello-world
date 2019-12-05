/**
 * Created by songjian on 3/21/2018.
 */
public class Main {
    public static void main(String[] args) {

//        kfkProducer kfkProducer = new kfkProducer();
//        kfkProducer.start();
        kfkConsumer kfkConsumer = new kfkConsumer();
        kfkConsumer.start();
    }
}

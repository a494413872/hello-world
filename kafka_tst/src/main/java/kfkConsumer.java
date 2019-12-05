/**
 * Created by songjian on 3/21/2018.
 */
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.Properties;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    import kafka.consumer.ConsumerConfig;
    import kafka.consumer.ConsumerIterator;
    import kafka.consumer.KafkaStream;
    import kafka.javaapi.consumer.ConsumerConnector;
    import kafka.message.MessageAndMetadata;

/**
     * @author leicui bourne_cui@163.com
     */
    public class kfkConsumer extends Thread
    {
        final static String zkConnect = "10.200.4.92:2181";
        final static String groupId = "group1";
        private final ConsumerConnector consumer;
        final static String topic = "hello-kafka";
        private ExecutorService executor;
        public kfkConsumer()
        {
            consumer = kafka.consumer.Consumer.createJavaConsumerConnector(
                    createConsumerConfig());
        }
        private static ConsumerConfig createConsumerConfig()
        {
            Properties props = new Properties();
            props.put("zookeeper.connect", zkConnect);
            props.put("group.id", groupId);
            props.put("zookeeper.session.timeout.ms", "40000");
            props.put("zookeeper.sync.time.ms", "200");
            props.put("auto.commit.interval.ms", "1000");
            return new ConsumerConfig(props);
        }
        @Override
        public void run() {
            Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
            topicCountMap.put(topic, new Integer(2));
            Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
            List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
            executor = Executors.newFixedThreadPool(10);
            cThead t1 = new cThead(1+"",streams.get(0));
            executor.submit(t1);
            cThead t2 = new cThead(2+"",streams.get(1));
            executor.submit(t2);

        }
    }

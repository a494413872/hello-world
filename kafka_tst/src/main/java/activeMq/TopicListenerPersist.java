package activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TopicListenerPersist {
    public static final String BROKER_URL = "failover:(tcp://10.200.4.83:61616)";
    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //1.第一个参数事物，2.第二个签收
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Topic topic= session.createTopic("USER_TOPIC_20200420");
        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic, "remark ... ");
        connection.start();
        Message receive = durableSubscriber.receive();
        while (null!=receive){
            TextMessage textMessage = (TextMessage) receive;
            System.out.println("收到的持久化topic"+textMessage);
            session.commit();
        }

        System.in.read();
        durableSubscriber.close();
        session.close();
        connection.close();
    }
}

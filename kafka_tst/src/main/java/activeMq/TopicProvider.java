package activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TopicProvider {
    public static final String BROKER_URL = "failover:(tcp://10.200.4.83:61616)";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //1.第一个参数事物，2.第二个签收
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Topic topic= session.createTopic("USER_TOPIC_20200420");
        MessageProducer producer = session.createProducer(topic);
        Message msg = session.createTextMessage("topic_02");
        producer.send(msg);
        session.commit();
        producer.close();
        session.close();
        connection.close();
    }
}

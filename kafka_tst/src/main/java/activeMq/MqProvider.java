package activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MqProvider {
    public static final String BROKER_URL = "failover:(tcp://localhost:61616)";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //1.第一个参数事物，2.第二个签收
        Session session = connection.createSession(Boolean.FALSE, Session.DUPS_OK_ACKNOWLEDGE);
        Destination destination = session.createQueue("USER_TASK_20200420");
        MessageProducer producer = session.createProducer(destination);
        Message msg = session.createTextMessage("task_02");
        producer.send(msg);
//        session.commit();
        producer.close();
        session.close();
        connection.close();
    }
}

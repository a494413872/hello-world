package activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

//异步发送
public class MqAsyncProvider {
    public static final String BROKER_URL = "failover:(tcp://10.200.4.83:61616)";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        //开启异步发送
        activeMQConnectionFactory.setUseAsyncSend(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //1.第一个参数事物，2.第二个签收
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("USER_TASK_20200420");
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(destination);
        Message msg = session.createTextMessage("task_02");
        msg.setJMSMessageID(UUID.randomUUID().toString()+"some id");
        String messageId = msg.getJMSMessageID();
        producer.send(msg, new AsyncCallback() {
            @Override
            public void onSuccess() {
                System.out.println(messageId+" success");
            }

            @Override
            public void onException(JMSException e) {
                System.out.println(messageId+" failed");
            }
        });
        producer.close();
        session.close();
        connection.close();
    }
}

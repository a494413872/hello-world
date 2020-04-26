package activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class ConsumerListener {
    public static final String BROKER_URL = "failover:(tcp://10.200.4.83:61616)";
    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //1.第一个参数事物，2.第二个签收
        Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("USER_TASK_20200420");
        MessageConsumer messageConsumer=session.createConsumer(destination);

        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(null!=message&&message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println(textMessage.getText());
                        session.commit();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}

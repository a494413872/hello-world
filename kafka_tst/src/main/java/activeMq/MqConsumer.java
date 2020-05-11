package activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MqConsumer {
    public static final String BROKER_URL = "failover:(tcp://10.200.4.83:61616)";
    public static void main(String[] args) throws Exception {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //1.第一个参数事物，2.第二个签收
        Session session = connection.createSession(Boolean.TRUE, Session.DUPS_OK_ACKNOWLEDGE);
        Destination destination = session.createQueue("USER_TASK_20200420");
        MessageConsumer messageConsumer=session.createConsumer(destination);

//        while (true){
//            TextMessage message = (TextMessage) messageConsumer.receive();
//            if (message!=null){
//                System.out.println("*****************");
//                System.out.println(message.getText());
////                Thread.sleep(10000l);
//            }else {
//                break;
//            }
//        }
        TextMessage message = (TextMessage) messageConsumer.receive();
            System.out.println("*****************");
            System.out.println(message.getText());
//                Thread.sleep(10000l);

//        session.commit();
        messageConsumer.close();
        session.close();
        connection.close();
    }
}

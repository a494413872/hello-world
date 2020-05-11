package activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.activemq.command.ActiveMQMessage;
import sun.applet.resources.MsgAppletViewer;

import javax.jms.*;
import java.util.UUID;

//异步发送
//这个需要服务器支持，所以不跑了
public class MqScheduledProvider {
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
        MessageProducer producer = session.createProducer(destination);
        Message msg = session.createTextMessage("task_02");
        msg.setJMSMessageID(UUID.randomUUID().toString()+"some id");
        String messageId = msg.getJMSMessageID();
        Long delay= 3*1000l;
        Long period = 4*1000l;
        Integer repeat = 5;
        msg.setLongProperty(ActiveMQMessage.AMQ_SCHEDULED_DELAY,delay);
        msg.setLongProperty(ActiveMQMessage.AMQ_SCHEDULED_PERIOD,period);
        msg.setIntProperty(ActiveMQMessage.AMQ_SCHEDULED_REPEAT,repeat);

        producer.send(msg);
        producer.close();
        session.close();
        connection.close();
    }
}

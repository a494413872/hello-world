package activeMq;

import javax.jms.Connection;  
import javax.jms.ConnectionFactory;  
import javax.jms.DeliveryMode;  
import javax.jms.Destination;  
import javax.jms.JMSException;  
import javax.jms.MessageConsumer;  
import javax.jms.MessageProducer;  
import javax.jms.Session;  
import javax.jms.TextMessage;  
  
import org.apache.activemq.ActiveMQConnection;  
import org.apache.activemq.ActiveMQConnectionFactory;  
import org.apache.activemq.ScheduledMessage;  
import org.apache.activemq.command.ActiveMQDestination;


public class TestActiveMQ {
	// tcp 地址  
    public static final String BROKER_URL = "failover:(tcp://10.200.6.229:61616)";
    // 目标，在ActiveMQ管理员控制台创建 http://localhost:8161/admin/queues.jsp  
    public static final String DESTINATION = "test.queue";
      
    /** 
     * <b>function:</b> 发送消息 
     * @author hoojo 
     * @createDate 2013-6-19 下午12:05:42 
     * @param session 
     * @param producer 
     * @throws Exception 
     */      
    public static void sendMessage(Session session, MessageProducer producer) throws Exception {  
          
        //例一：  
        String message = "直接发送数据";  
        TextMessage tm = session.createTextMessage(message);  
        producer.send(tm);  
  
        //例二：  
        //需要修改activemq.xml才能生效，在<broker>里添加属性schedulerSupport="true"  
        message = "延时10秒发送数据";  
        TextMessage tm2 = session.createTextMessage("Send Message After 10 seconds!");   
        tm2.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON, "0 * * * *"); 
     
        producer.send(tm2);  
    }  
      
    public static void run() throws Exception {  
          
        Connection connection = null;  
        Session session = null;  
        try {  
            // 创建链接工厂  
            ConnectionFactory factory = new ActiveMQConnectionFactory(  
                    ActiveMQConnection.DEFAULT_USER,   
                    ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);  
            // 通过工厂创建一个连接  
            connection = factory.createConnection();  
            // 启动连接  
            connection.start();  
            // 创建一个session会话  
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);  
            // 创建一个消息队列  
            Destination destination = session.createQueue(DESTINATION);  
            // 创建消息制作者  
            MessageProducer producer = session.createProducer(destination);  
              
            // 设置持久化模式  
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);  
            sendMessage(session, producer);  
            // 提交会话  
            session.commit();  
              
        } catch (Exception e) {  
            throw e;  
        } finally {  
            // 关闭释放资源  
            if (session != null) {  
                session.close();  
            }  
            if (connection != null) {  
                connection.close();  
            }  
        }  
    }  
      
    public static void ClearMessage()  
    {  
        // ConnectionFactory ：连接工厂，JMS 用它创建连接  
        ConnectionFactory connectionFactory;  
        // Connection ：JMS 客户端到JMS Provider 的连接  
        Connection connection = null;  
        // Session： 一个发送或接收消息的线程  
        Session session;  
        // Destination ：消息的目的地;消息发送给谁.  
        Destination destination;  
        // 消费者，消息接收者  
        MessageConsumer consumer;  
        connectionFactory = new ActiveMQConnectionFactory(  
                ActiveMQConnection.DEFAULT_USER,  
                ActiveMQConnection.DEFAULT_PASSWORD,  
                BROKER_URL);  
        try {  
            // 构造从工厂得到连接对象  
            connection = connectionFactory.createConnection();  
            // 启动  
            connection.start();  
            // 获取操作连接  
            session = connection.createSession(Boolean.FALSE,  
                    Session.AUTO_ACKNOWLEDGE);  
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置  
            destination = session.createQueue(DESTINATION);  
            consumer = session.createConsumer(destination);  
            while (true) {  
                TextMessage message = (TextMessage) consumer.receive(10);  
                if (null != message) {  
                    System.out.println("把消息拿下来但不处理！");  
                } else {  
                    break;  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (null != connection)  
                    connection.close();  
            } catch (Throwable ignore) {  
            }  
        }  
    }  
      
    /** 
     * 删除队列 
     * 如果有consumer连在队列上，删除队列会失败！ 
     * 所以不建议使用 
     * 这个函数有进程阻塞问题 
     * @param url 
     * @param queueName 
     */  
    public static void ClearQueue(String url,String queueName)  
    {  
        ActiveMQConnection con = null;  
        try {  
            con = (ActiveMQConnection) new ActiveMQConnectionFactory(url).createConnection();         
            Destination queue=null;  
            queue = con.createSession(false, Session.AUTO_ACKNOWLEDGE).createQueue(queueName);  
            con.start();  
            con.destroyDestination((ActiveMQDestination) queue);  
        } catch (JMSException e)   
        {  
            e.printStackTrace();  
        } finally {  
            try  
            {  
                con.stop();  
            } catch(Exception e)  
            {  
                e.printStackTrace();  
            }  
        }//end finally  
    }//end func  
      
    /* 
     * Active MQ后台管理页面，用户名密码分别为admin,admin 
     * http://127.0.0.1:8161/admin/ 
     * */  
    public static void main(String[] args) throws Exception {  
        System.out.println("program begin!");  
          
        //删除队列  
        //ClearQueue(BROKER_URL,DESTINATION);  
          
        //通过取队列中的消息，把队列中的消息删除  
        ClearMessage();  
          
        //发送消息示例  
        TestActiveMQ.run();  
          
        Thread.sleep(Long.MAX_VALUE);
        System.out.println("program end!");  
    }//end function  
}

//package utils.xmlParse;
//
//
//import org.apache.commons.pool.KeyedObjectPool;
//import org.apache.commons.pool.KeyedPoolableObjectFactory;
//import org.apache.commons.pool.impl.GenericKeyedObjectPool;
//import org.apache.log4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBElement;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.StringReader;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.NoSuchElementException;
//
///**
// * 线程安全的xml转换
// *
// * @author LSH2052
// */
//@SuppressWarnings("all")
//public class MarshallerUtil {
//    Logger logger = (Logger) LoggerFactory.getLogger(MarshallerUtil.class);
//    /**
//     * A "when exhausted action" type indicating that when the pool is
//     * exhausted (i.e., the maximum number of active objects has
//     * been reached), the {@link #borrowObject}
//     * method should fail, throwing a {@link NoSuchElementException}.
//     *
//     * @see #WHEN_EXHAUSTED_BLOCK
//     * @see #WHEN_EXHAUSTED_GROW
//     * @see #setWhenExhaustedAction
//     */
//    public static final byte WHEN_EXHAUSTED_FAIL = 0;
//
//    /**
//     * A "when exhausted action" type indicating that when the pool
//     * is exhausted (i.e., the maximum number
//     * of active objects has been reached), the {@link #borrowObject}
//     * method should block until a new object is available, or the
//     * {@link #getMaxWait maximum wait time} has been reached.
//     *
//     * @see #WHEN_EXHAUSTED_FAIL
//     * @see #WHEN_EXHAUSTED_GROW
//     * @see #setMaxWait
//     * @see #getMaxWait
//     * @see #setWhenExhaustedAction
//     */
//    public static final byte WHEN_EXHAUSTED_BLOCK = 1;
//
//    /**
//     * A "when exhausted action" type indicating that when the pool is
//     * exhausted (i.e., the maximum number
//     * of active objects has been reached), the {@link #borrowObject}
//     * method should simply create a new object anyway.
//     *
//     * @see #WHEN_EXHAUSTED_FAIL
//     * @see #WHEN_EXHAUSTED_GROW
//     * @see #setWhenExhaustedAction
//     */
//    public static final byte WHEN_EXHAUSTED_GROW = 2;
//
//    /**
//     * The default cap on the number of idle instances (per key) in the pool.
//     *
//     * @see #getMaxIdle
//     * @see #setMaxIdle
//     */
//    public static final int DEFAULT_MAX_IDLE = 200;
//
//    /**
//     * The default cap on the total number of active instances (per key)
//     * from the pool.
//     *
//     * @see #getMaxActive
//     * @see #setMaxActive
//     */
//    public static final int DEFAULT_MAX_ACTIVE = 2000;
//
//    /**
//     * The default cap on the the overall maximum number of objects that can
//     * exist at one time.
//     *
//     * @see #getMaxTotal
//     * @see #setMaxTotal
//     */
//    public static final int DEFAULT_MAX_TOTAL = -1;
//
//    /**
//     * The default "when exhausted action" for the pool.
//     *
//     * @see #WHEN_EXHAUSTED_BLOCK
//     * @see #WHEN_EXHAUSTED_FAIL
//     * @see #WHEN_EXHAUSTED_GROW
//     * @see #setWhenExhaustedAction
//     */
//    public static final byte DEFAULT_WHEN_EXHAUSTED_ACTION = WHEN_EXHAUSTED_BLOCK;
//
//    /**
//     * The default maximum amount of time (in milliseconds) the
//     * {@link #borrowObject} method should block before throwing
//     * an exception when the pool is exhausted and the
//     * {@link #getWhenExhaustedAction "when exhausted" action} is
//     * {@link #WHEN_EXHAUSTED_BLOCK}.
//     *
//     * @see #getMaxWait
//     * @see #setMaxWait
//     */
//    public static final long DEFAULT_MAX_WAIT = -1L;
//
//    /**
//     * The default "test on borrow" value.
//     *
//     * @see #getTestOnBorrow
//     * @see #setTestOnBorrow
//     */
//    public static final boolean DEFAULT_TEST_ON_BORROW = false;
//
//    /**
//     * The default "test on return" value.
//     *
//     * @see #getTestOnReturn
//     * @see #setTestOnReturn
//     */
//    public static final boolean DEFAULT_TEST_ON_RETURN = false;
//
//    /**
//     * The default "test while idle" value.
//     *
//     * @see #getTestWhileIdle
//     * @see #setTestWhileIdle
//     * @see #getTimeBetweenEvictionRunsMillis
//     * @see #setTimeBetweenEvictionRunsMillis
//     */
//    public static final boolean DEFAULT_TEST_WHILE_IDLE = false;
//
//    /**
//     * The default "time between eviction runs" value.
//     *
//     * @see #getTimeBetweenEvictionRunsMillis
//     * @see #setTimeBetweenEvictionRunsMillis
//     */
//    public static final long DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS = -1L;
//
//    /**
//     * The default number of objects to examine per run in the
//     * idle object evictor.
//     *
//     * @see #getNumTestsPerEvictionRun
//     * @see #setNumTestsPerEvictionRun
//     * @see #getTimeBetweenEvictionRunsMillis
//     * @see #setTimeBetweenEvictionRunsMillis
//     */
//    public static final int DEFAULT_NUM_TESTS_PER_EVICTION_RUN = 3;
//
//    /**
//     * The default value for {@link #getMinEvictableIdleTimeMillis}.
//     *
//     * @see #getMinEvictableIdleTimeMillis
//     * @see #setMinEvictableIdleTimeMillis
//     */
//    public static final long DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS = 1000L * 60L * 30L;
//
//    /**
//     * The default minimum level of idle objects in the pool.
//     *
//     * @see #setMinIdle
//     * @see #getMinIdle
//     * @since Pool 1.3
//     */
//    public static final int DEFAULT_MIN_IDLE = 0;
//
//    /**
//     * The default LIFO status. True means that borrowObject returns the
//     * most recently used ("last in") idle object in a pool (if there are
//     * idle instances available). False means that pools behave as FIFO
//     * queues - objects are taken from idle object pools in the order that
//     * they are returned.
//     *
//     * @see #setLifo
//     */
//    public static final boolean DEFAULT_LIFO = true;
//
//    protected static KeyedObjectPool marPool = new GenericKeyedObjectPool(new JaxbMarshallerFactory(),
//            DEFAULT_MAX_ACTIVE,
//            DEFAULT_WHEN_EXHAUSTED_ACTION,
//            DEFAULT_MAX_WAIT,
//            DEFAULT_MAX_IDLE,
//            DEFAULT_TEST_ON_BORROW,
//            DEFAULT_TEST_ON_RETURN,
//            DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS,
//            DEFAULT_NUM_TESTS_PER_EVICTION_RUN,
//            DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS,
//            DEFAULT_TEST_WHILE_IDLE);
//
//    protected static KeyedObjectPool unmarPool = new GenericKeyedObjectPool(
//            new JaxbUnmarshallerFactory(),
//            DEFAULT_MAX_ACTIVE,
//            DEFAULT_WHEN_EXHAUSTED_ACTION,
//            DEFAULT_MAX_WAIT,
//            DEFAULT_MAX_IDLE,
//            DEFAULT_TEST_ON_BORROW,
//            DEFAULT_TEST_ON_RETURN,
//            DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS,
//            DEFAULT_NUM_TESTS_PER_EVICTION_RUN,
//            DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS,
//            DEFAULT_TEST_WHILE_IDLE);
//
//    /**
//     * 利用JAXB将XML解析成对象
//     *
//     * @param clazz
//     * @param xml
//     * @return
//     * @throws Exception
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> T buildJaxbSafe(Class<T> clazz, String xml) {
//        Unmarshaller unmarshaller = null;
//        T object = null;
//        Object o = null;
//        try {
//            // 从线程池中借出
//            unmarshaller = (Unmarshaller) unmarPool.borrowObject(clazz);
//            o = unmarshaller.unmarshal(new StringReader(xml));
//            if (o instanceof JAXBElement) {
//                JAXBElement<?> element = (JAXBElement<?>) o;
//                object = (T) element.getValue();
//            } else {
//                object = (T) unmarshaller.unmarshal(new StringReader(xml));
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("XML转换失败", e);
//        } finally {
//            // 归还到对象池
//            try {
//                unmarPool.returnObject(clazz, unmarshaller);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return object;
//    }
//
//    /**
//     * 利用JAXB将XML解析成对象
//     *
//     * @param clazz
//     * @param xml
//     * @return
//     * @throws Exception
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> T buildJaxbSafe(Class<T> clazz, File file) throws RuntimeException {
//        Unmarshaller unmarshaller = null;
//        T object = null;
//        Object o = null;
//        try {
//            // 从线程池中借出
//            unmarshaller = (Unmarshaller) unmarPool.borrowObject(clazz);
//            o = unmarshaller.unmarshal(file);
//            if (o instanceof JAXBElement) {
//                JAXBElement<?> element = (JAXBElement<?>) o;
//                object = (T) element.getValue();
//            } else {
//                object = (T) unmarshaller.unmarshal(file);
//            }
//        } catch (Exception e) {
//
//            throw new RuntimeException("XML转换失败", e);
//        } finally {
//            // 归还到对象池
//            try {
//                unmarPool.returnObject(clazz, unmarshaller);
//            } catch (Exception e) {
//
//            }
//        }
//        return object;
//    }
//
//    /**
//     * 利用JAXB将对象解析成XML
//     *
//     * @param object
//     * @return
//     * @throws Exception
//     */
//    public static String buildJaxbSafe(Object object) throws RuntimeException {
//        return buildJaxbSafe(object, Boolean.TRUE);
//    }
//
//    @SuppressWarnings("unchecked")
//    public static String buildJaxbSafe(Object object, Boolean format) throws RuntimeException {
//        String result = null;
//        Marshaller marshaller = null;
//        Class<?> clazz = object.getClass();
//        try {
//
//            marshaller = (Marshaller) marPool.borrowObject(clazz);
//            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
//            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            marshaller.marshal(object, os);
//            result = new String(os.toByteArray(), "UTF-8");
//        } catch (Exception e) {
//            throw new RuntimeException("XML转换失败", e);
//        } finally {
//            // 归还到对象池
//            try {
//                marPool.returnObject(clazz, marshaller);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//}
//
//class JaxbUnmarshallerFactory extends JaxbMarshallerFactory {
//
//    @Override
//    public synchronized Object makeObject(Object key) throws Exception {
//        Class<?> clazz = (Class<?>) key;
//        if (!contextMap.containsKey(key)) {
//            JAXBContext context = JAXBContext.newInstance(clazz);
//            contextMap.put(clazz, context);
//        }
//        return contextMap.get(clazz).createUnmarshaller();
//    }
//}
//
//class JaxbMarshallerFactory implements KeyedPoolableObjectFactory {
//
//    protected Map<Class<?>, JAXBContext> contextMap = new HashMap<Class<?>, JAXBContext>();
//
//    @Override
//    public synchronized Object makeObject(Object key) throws Exception {
//        Class<?> clazz = (Class<?>) key;
//        if (!contextMap.containsKey(clazz)) {
//            JAXBContext context = JAXBContext.newInstance(clazz);
//            contextMap.put(clazz, context);
//        }
//        return contextMap.get(clazz).createMarshaller();
//    }
//
//    @Override
//    public synchronized void destroyObject(Object key, Object obj) throws Exception {
//        contextMap.remove(key);
//    }
//
//    @Override
//    public boolean validateObject(Object key, Object obj) {
//        return true;
//    }
//
//    @Override
//    public void activateObject(Object key, Object obj) throws Exception {
//        // System.out.println("user "+Thread.currentThread()+" borrow "+obj.hashCode());
//    }
//
//    @Override
//    public void passivateObject(Object key, Object obj) throws Exception {
//        // System.out.println("user "+Thread.currentThread()+" return "+obj.hashCode());
//    }
//}
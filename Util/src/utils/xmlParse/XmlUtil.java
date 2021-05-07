//package utils.xmlParse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.xml.bind.*;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.StringReader;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//
//
//public class XmlUtil {
//
//    private XmlUtil() {}
//
//    public static final String UTF_8 = "UTF-8";
//    protected static final Logger log = LoggerFactory.getLogger(XmlUtil.class);
//
//    private static Map<List<Class<?>>, JAXBContext> jaxbContextMap = new ConcurrentHashMap<>();
//
//    public static JAXBContext getJAXBContext(Class<?>... classes) throws JAXBException {
//        List<Class<?>> classList = new ArrayList<>(Arrays.asList(classes));
//        if (!jaxbContextMap.containsKey(classList)) {
//            JAXBContext jaxbContext = JAXBContext.newInstance(classes);
//            jaxbContextMap.put(classList, jaxbContext);
//            return jaxbContext;
//        }
//        return jaxbContextMap.get(classList);
//    }
//
//    /**
//     * 利用JAXB将对象解析成XML
//     *
//     * @param object
//     * @return
//     * @throws JAXBException
//     */
//    public static String buildJaxb(Object object) throws JAXBException {
//        return buildJaxb(object, Boolean.TRUE);
//    }
//
//    /**
//     * 利用JAXB将对象解析成XML
//     *
//     * @param object
//     * @param format
//     * @return
//     * @throws JAXBException
//     */
//    public static String buildJaxb(Object object, Boolean format) throws JAXBException {
//        Class<?> clazz = object.getClass();
//        JAXBContext jc = getJAXBContext(clazz);
//        Marshaller marshaller = jc.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_ENCODING,
//                UTF_8);
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
//        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        marshaller.marshal(object, os);
//        return new String(os.toByteArray(), StandardCharsets.UTF_8);
//    }
//
//    /**
//     * 利用JAXB将对象解析成XML(维密专用)
//     *
//     * @param object
//     * @param format
//     * @return
//     * @throws JAXBException
//     */
//    public static String buildJaxbVs(Object object, Boolean format) throws JAXBException {
//        Class<?> clazz = object.getClass();
//        JAXBContext jc = JAXBContext.newInstance(clazz);
//        Marshaller marshaller = jc.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_ENCODING, UTF_8);
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
//        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//        marshaller.setProperty("com.sun.xml.bind.xmlHeaders",
//                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        marshaller.marshal(object, os);
//        return new String(os.toByteArray(), StandardCharsets.UTF_8);
//    }
//
//    /**
//     * 利用JAXB将XML解析成对象
//     *
//     * @param clazz
//     * @param xml
//     * @return
//     * @throws JAXBException
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> T buildJaxb(Class<T> clazz, String xml) throws JAXBException {
//        T object = null;
//        Object o = null;
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            o = unmarshaller.unmarshal(new StringReader(xml));
//            if (o instanceof JAXBElement) {
//                JAXBElement<?> element = (JAXBElement<?>) o;
//                object = (T) element.getValue();
//            } else
//                object = (T) unmarshaller.unmarshal(new StringReader(xml));
//        } catch (JAXBException e) {
//            log.warn("XmlUtil中buildJaxb方法报错: ", e);
//            throw e;
//        }
//        return object;
//    }
//
//    /**
//     * 利用JAXB将XML解析成对象
//     *
//     * @param clazz
//     * @param file
//     * @return
//     * @throws JAXBException
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> T buildJaxb(Class<T> clazz, File file) throws JAXBException {
//        T object = null;
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            Object object1 = unmarshaller.unmarshal(file);
//
//            if (object1 instanceof JAXBElement) {
//                JAXBElement<?> element = (JAXBElement<?>) object1;
//                object = (T) element.getValue();
//            } else {
//                object = (T) unmarshaller.unmarshal(file);
//            }
//        } catch (JAXBException e) {
//            log.warn("XmlUtil中buildJaxb方法报错: ", e);
//            throw e;
//        }
//        return object;
//    }
//
//    /**
//     * 利用JAXB将对象解析成XML
//     *
//     * @param object
//     * @return
//     * @throws JAXBException
//     */
//    public static String vsBuildJaxb(Object object) throws JAXBException {
//        return vsBuildJaxb(object, Boolean.TRUE);
//    }
//
//    /**
//     * 利用JAXB将对象解析成XML
//     *
//     * @param object
//     * @param format
//     * @return
//     * @throws JAXBException
//     */
//    public static String vsBuildJaxb(Object object, Boolean format) throws JAXBException {
//        JAXBContext jc = JAXBContext.newInstance(object.getClass());
//        Marshaller marshaller = jc.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_ENCODING, UTF_8);
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, format);
//        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//        marshaller.setProperty("com.sun.xml.bind.xmlHeaders",
//                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        marshaller.marshal(object, os);
//        return new String(os.toByteArray(), StandardCharsets.UTF_8);
//    }
//}

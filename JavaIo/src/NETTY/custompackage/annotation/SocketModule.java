package NETTY.custompackage.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * @interface 用来定一个一个注解
 *    @Documented – 注解是否将包含在JavaDoc中
 *    @Retention – 什么时候使用该注解
 *    @Target – 注解用于什么地方
 *    @Inherited – 是否允许子类继承该注解
 *    用来注解注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketModule {
    /**
     * 注解可以接收的值
     * @return
     */
    short module();
}

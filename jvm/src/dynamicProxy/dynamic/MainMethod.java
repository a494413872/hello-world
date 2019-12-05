package dynamicProxy.dynamic;

import dynamicProxy.statictype.Cat;
import dynamicProxy.statictype.Pet;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainMethod {
    public static void main(String[] args) {
        Pet obj = new Cat();
        //java动态代理和静态代理的区别是
        //动态代理不需要编写实现Pet接口具体的PetProxy类，而是通过实现InvocationHandler的invode来代用被代理对象。
        //然后通过Proxy.newProxyInstance来创建被代理对象。
        Pet proxy = (Pet) Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("dynamic proxy");
                return method.invoke(obj,args);
            }
        });
        proxy.run();
        proxy.eat();
    }
}

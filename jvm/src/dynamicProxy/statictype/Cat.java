package dynamicProxy.statictype;

public class Cat implements Pet {
    @Override
    public void eat() {
        System.out.println("Cat eat fish");
    }

    @Override
    public void run() {
        System.out.println("Cat do not like run");
    }
}

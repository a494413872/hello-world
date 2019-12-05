package dynamicProxy.statictype;

public class MainMethod {
    public static void main(String[] args) {
        Pet proxy = new PetProxy();
        proxy.eat();
        proxy.run();
    }
}

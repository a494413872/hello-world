package RPC;

public class HelloImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello "+ name;
    }
}

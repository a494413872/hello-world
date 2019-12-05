package RPC;

public class Provider {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloImpl();
        RpcFrameWork.export(service,1234);
    }
}

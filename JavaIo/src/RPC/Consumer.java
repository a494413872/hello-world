package RPC;

public class Consumer {
    public static void main(String[] args) throws Exception {
        HelloService service = RpcFrameWork.refer(HelloService.class,"127.0.0.1",1234);
        for (int i = 0; i < 10; i++) {
            System.out.println(service.hello(i+""));
        }
    }
}

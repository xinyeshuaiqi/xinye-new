package pers.wmx.rpc.client;


import pers.wmx.rpc.server.SayHelloRpcService;

/**
 * @author wangmingxin03
 * Created on 2021-12-02
 */
public class RpcClientMain {
    public static void main(String[] args) throws Exception {
        RpcClient client = new RpcClient();
        SayHelloRpcService service = client
                .callService(SayHelloRpcService.class, "127.0.0.1", 6666);

        String request = "xinye";
        String response = service.hello(request);
        System.out.println(response);
    }
}

package pers.wmx.rpc.server;

/**
 * @author wangmingxin03
 * Created on 2021-12-02
 */
public class RpcServerMain {
    public static void main(String[] args) throws Exception {
        SayHelloRpcService sayHelloRpcService = new SayHelloRpcServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publishService(sayHelloRpcService, 6666);
    }
}

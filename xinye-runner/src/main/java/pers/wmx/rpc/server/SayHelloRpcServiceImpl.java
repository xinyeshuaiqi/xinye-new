package pers.wmx.rpc.server;

/**
 * @author wangmingxin03
 * Created on 2021-12-02
 */
public class SayHelloRpcServiceImpl implements SayHelloRpcService {
    @Override
    public String hello(String clientName) {
        return "hello, " + clientName;
    }
}

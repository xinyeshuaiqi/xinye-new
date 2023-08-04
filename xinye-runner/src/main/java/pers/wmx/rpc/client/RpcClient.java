package pers.wmx.rpc.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * @author wangmingxin03
 * Created on 2021-12-02
 */
public class RpcClient {
    public <T> T callService(Class<T> rpcServiceClass, String host, int port) throws Exception {
        return (T) Proxy.newProxyInstance(rpcServiceClass.getClassLoader(),
                new Class<?>[] {rpcServiceClass}, (proxy, method, args) -> {
                    // 创建客户端到服务端的连接
                    Socket socket = new Socket(host, port);

                    ObjectOutputStream outputStream = null;
                    ObjectInputStream inputStream = null;

                    try {
                        // 拿到输出流，开始调RPC
                        outputStream = new ObjectOutputStream(socket.getOutputStream());
                        outputStream.writeUTF(method.getName());
                        outputStream.writeObject(method.getParameterTypes());
                        outputStream.writeObject(args);

                        // 拿到输入流，获取调用结果
                        inputStream = new ObjectInputStream(socket.getInputStream());
                        Object response = inputStream.readObject();

                        if (response instanceof Throwable) {
                            throw (Throwable)response;
                        }
                        return response;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "";
                    } finally {
                        inputStream.close();
                        outputStream.close();
                        socket.close();
                    }

                });
    }
}

package pers.wmx.rpc.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wangmingxin03
 * Created on 2021-12-02
 */
public class RpcServer {
    /**
     * 发布服务
     *
     * @param service 要发布的服务
     * @param port server端口号
     **/
    public void publishService(Object service, int port) throws Exception {
        if (service == null) {
            throw new IllegalArgumentException("empty service");
        }

        if (port <= 0) {
            throw new IllegalArgumentException("invalid port");
        }

        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            new Thread(() -> {
                try {

                    // 获取Socket输入输出流
                    ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                    try {
                        String methodName = inputStream.readUTF();
                        Class[] parameterTypes = (Class[]) inputStream.readObject();
                        Object[] arguments = (Object[]) inputStream.readObject();

                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                        Object response = method.invoke(service, arguments);

                        outputStream.writeObject(response);
                    } catch (Throwable t) {
                        // 把异常抛给服务调用方
                        outputStream.writeObject(t);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }
}

package pers.wmx.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangmingxin03
 * Created on 2023-07-26
 */
public class SelectorThreadGroup {
    SelectorThread[] selectorThreads;
    ServerSocketChannel server=null;
    AtomicInteger counter = new AtomicInteger(0);

    SelectorThreadGroup(int num){
        //num  线程数
        selectorThreads = new SelectorThread[num];
        for (int i = 0; i < num; i++) {
            selectorThreads[i] = new SelectorThread(this);

            new Thread(selectorThreads[i]).start();
        }
    }

    public void bind(int port) {
        try {
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.bind(new InetSocketAddress(port));
            nextSelector(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextSelector(Channel channel) {
        SelectorThread selectorThread = next();
        selectorThread.queue.add(channel);
        // 打断阻塞
        selectorThread.selector.wakeup();
    }

    private SelectorThread next() {
        int index = counter.getAndIncrement() % selectorThreads.length;  //轮询就会很尴尬，倾斜
        return selectorThreads[index];
    }
}

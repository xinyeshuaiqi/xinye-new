package pers.wmx.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wangmingxin03
 * Created on 2023-07-26
 */
public class SelectorThread
        extends ThreadLocal<LinkedBlockingQueue<Channel>>
        implements Runnable {

    Selector selector = null;
    LinkedBlockingQueue<Channel> queue = get();

    SelectorThreadGroup selectorThreadGroup = null;

    SelectorThread(SelectorThreadGroup selectorThreadGroup){
        try {
            this.selectorThreadGroup = selectorThreadGroup;
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected LinkedBlockingQueue<Channel> initialValue() {
        return new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 阻塞读
                int nums = selector.select();

                if (nums > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        iterator.remove();

                        if(key.isAcceptable()){
                            acceptHandler(key);
                        }else if(key.isReadable()){
                            readHandler(key);
                        }else if(key.isWritable()){

                        }
                    }
                }

                if (!queue.isEmpty()) {
                    Channel channel = queue.take();

                    if(channel instanceof ServerSocketChannel){
                        ServerSocketChannel server = (ServerSocketChannel) channel;

                        // 注册接受连接事件
                        server.register(selector,SelectionKey.OP_ACCEPT);
                        System.out.println(Thread.currentThread().getName()+" register accept");
                    } else if(channel instanceof SocketChannel){
                        SocketChannel client = (SocketChannel) channel;
                        ByteBuffer buffer = ByteBuffer.allocateDirect(4096);

                        // 注册可读事件
                        client.register(selector, SelectionKey.OP_READ, buffer);
                        System.out.println(Thread.currentThread().getName()+" register read : "
                                + client.getRemoteAddress());

                    }
                }

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void readHandler(SelectionKey key) {
        ByteBuffer buffer = (ByteBuffer)key.attachment();
        SocketChannel client = (SocketChannel)key.channel();
        buffer.clear();
        while(true) {
            try {
                int num = client.read(buffer);
                if (num > 0){
                    buffer.flip();  //将读到的内容翻转，然后直接写出
                    while(buffer.hasRemaining()){
                        client.write(buffer);
                    }
                    buffer.clear();
                } else if (num == 0){
                    break;
                } else {
                    //客户端断开了
                    System.out.println("client: " + client.getRemoteAddress() + "closed......");
                    key.cancel();
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void acceptHandler(SelectionKey key) {
        System.out.println(Thread.currentThread().getName()+"   acceptHandler......");

        ServerSocketChannel server = (ServerSocketChannel)key.channel();
        try {
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            selectorThreadGroup.nextSelector(client);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

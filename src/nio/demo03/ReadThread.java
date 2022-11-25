package nio.demo03;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ReadThread extends Thread {

    private Selector selector;

    private ByteBuffer bb = ByteBuffer.allocate(1024);

    public ReadThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            while (true) {
                selector.select();
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey next = it.next();
                    if (next.isAcceptable()) {
                        accept(next);
                    } else if (next.isReadable()) {
                        read(next);
                    }
                    it.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void accept(SelectionKey key) throws Exception {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel socket = channel.accept();
        socket.configureBlocking(false);
        socket.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws Exception {
        SocketChannel socket = (SocketChannel) key.channel();
        if (socket.read(bb) > 0) {
            bb.flip();
            System.out.println(toStr(bb));
            bb.clear();
        }
    }

    private String toStr(ByteBuffer bb) {
        byte[] bytes = new byte[bb.remaining()];
        bb.get(bytes);
        return new String(bytes);
    }
}

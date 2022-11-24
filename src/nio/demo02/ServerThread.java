package nio.demo02;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ServerThread extends Thread {

    private Selector selector;

    private ByteBuffer bb = ByteBuffer.allocate(1024);

    public ServerThread(Selector selector) {
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
                    it.remove();
                    if (!next.isValid()) {
                        continue;
                    } else if (next.isAcceptable()) {
                        accept(next);
                    } else if (next.isReadable()) {
                        read(next);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void accept(SelectionKey key) {
        try {
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            SocketChannel socket = channel.accept();
            socket.configureBlocking(false);
            socket.register(selector, SelectionKey.OP_READ);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        try {
            SocketChannel socket = (SocketChannel) key.channel();
            if (socket.read(bb) > 0) {
                bb.flip();
                System.out.println(toStr(bb));
                bb.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String toStr(ByteBuffer bb) {
        byte[] bytes = new byte[bb.remaining()];
        bb.get(bytes);
        return new String(bytes);
    }
}

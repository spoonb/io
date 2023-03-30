package nio.demo03;

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
                    if (next.isAcceptable()) {
                        accept(next);
                    } else if (next.isReadable()) {
                        String msg = read(next);
                        write(next, msg);
                    }
                    it.remove();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void write(SelectionKey next, String msg) {

    }

    private void accept(SelectionKey key) throws Exception {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel socket = channel.accept();
        socket.configureBlocking(false);
        socket.register(selector, SelectionKey.OP_READ);
    }

    private String read(SelectionKey key) throws Exception {
        SocketChannel socket = (SocketChannel) key.channel();
        String msg = "";
        if (socket.read(bb) > 0) {
            bb.flip();
            msg = toStr(bb);
            bb.clear();
        }
        return msg;
    }

    private String toStr(ByteBuffer bb) {
        byte[] bytes = new byte[bb.remaining()];
        bb.get(bytes);
        return new String(bytes);
    }
}

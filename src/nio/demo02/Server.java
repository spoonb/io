package nio.demo02;

import java.net.InetSocketAddress;
import java.nio.channels.*;

public class Server {

    private static Selector selector;

    public static void main(String[] args) {
        new Server().init();
        new ServerThread(selector).start();
    }

    public void init() {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(9999));
            ssc.configureBlocking(false);
            selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package nio.demo03;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try (var sc = new Scanner(System.in); ) {
            ByteBuffer bb = ByteBuffer.allocate(1024);
            SocketChannel socket = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
            String msg;
            while ((msg = sc.nextLine()) != null) {
                bb.put(msg.getBytes());
                bb.flip();
                socket.write(bb);
                bb.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

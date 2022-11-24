package bio.chat.demo04;

import java.net.Socket;

/**
 * 实现多个客户端之间群发消息
 */
public class Client {

    public static void main(String[] args) {
//        try (var socket = new Socket("127.0.0.1", 9999)) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            new WriteThread(socket).start();
//            var rt = new ReadThread(socket);
            new ReadThread(socket).start();
//            rt.start();
//            rt.join(); // 让上述两个线程中的一个插队主线程以保证主线程不结束
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

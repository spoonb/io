package bio.socket.demo03;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;

/**
 * 需求：服务端可以不停接受客户端发来的消息
 */
public class Server {

    public static void main(String[] args) {
        // 1. 创建Socket
        try (var socket = new ServerSocket(9999)) {
            // 为每个客户端创建一个线程
            do {
                new ServerThread(socket.accept()).start();
            } while (true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

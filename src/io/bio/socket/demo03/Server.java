package io.bio.socket.demo03;

import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {
        // 1. 创建Socket
        try (var socket = new ServerSocket(9999)) {
            // 为每个客户端创建一个线程
            do {
                new ServerThread(socket.accept()).start();
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

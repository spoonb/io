package io.bio.chat.demo04;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static List<Socket> sockets = new ArrayList<>();

    public static void main(String[] args) {
        try (var ss = new ServerSocket(9999)) {
            while (true) {
                Socket socket = ss.accept();
                sockets.add(socket);
                System.out.printf("%s --> 已登录\n", socket);
                new ChatThread(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Socket> getSockets() {
        return sockets;
    }
}

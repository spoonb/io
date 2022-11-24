package bio.chat.demo03;

import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {
        try (var ss = new ServerSocket(9999)) {
            while (true) {
                new ChatThread(ss.accept()).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

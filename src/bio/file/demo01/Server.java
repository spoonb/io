package bio.file.demo01;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try (var ss = new ServerSocket(9999)) {
            Socket socket;
            do {
                new Thread(new ServerRunnable(socket = ss.accept())).start();
            } while (socket != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

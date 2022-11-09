package bio.socket.demo04;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 6, 300, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        try (var ss = new ServerSocket(9999)) {
            Socket socket;
            do {
                socket = ss.accept();
                pool.execute(new ServerRunnable(socket));
            } while (socket != null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

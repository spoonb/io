package bio.socket.demo04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerRunnable implements Runnable {

    private Socket socket;

    public ServerRunnable(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (var is = socket.getInputStream(); var br = new BufferedReader(new InputStreamReader(is))) {
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

package bio.chat.demo04;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class WriteThread extends Thread {

    private Socket socket;

    public WriteThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(var os = socket.getOutputStream();
            var ps = new PrintStream(os);
            var sc = new Scanner(System.in)) {
            String msg;
            while ((msg = sc.nextLine()) != null) {
                ps.println(msg);
                ps.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package io.bio.chat.demo04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ChatThread extends Thread {

    private Socket socket;

    public ChatThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(var is = socket.getInputStream();
            var br = new BufferedReader(new InputStreamReader(is))) {
            String msg;
            while ((msg = br.readLine()) != null) {
                for (Socket socket : Server.getSockets()) {
                    if (this.socket == socket) continue;
                    var ps = new PrintStream(socket.getOutputStream());
                    ps.println(msg);
                    ps.flush();
                }
            }
        } catch (Exception e) {
            System.out.printf("%s --> 已登出\n", socket);
            Server.getSockets().remove(socket);
        }
    }
}

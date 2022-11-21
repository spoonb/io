package io.bio.chat.demo03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
                System.out.println(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

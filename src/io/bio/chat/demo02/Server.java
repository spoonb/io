package io.bio.chat.demo02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {
        try (var ss = new ServerSocket(9999);
             var socket = ss.accept();
             var is = socket.getInputStream();
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

package io.bio.socket.demo07;

import java.io.DataInputStream;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {
        try (var socket = new ServerSocket(9999).accept();
             var dis = new DataInputStream(socket.getInputStream())) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

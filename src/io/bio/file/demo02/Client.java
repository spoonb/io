package io.bio.file.demo02;

import java.io.*;
import java.net.Socket;

/**
 * 需求：模拟群聊
 */
public class Client {

    public static void main(String[] args) {
        try (var socket = new Socket("127.0.0.1", 9999)) {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            byte[] bytes = new byte[1024];

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package io.bio.chat.demo01;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 实现客户端与服务端单次信息发送
 */
public class Client {

    public static void main(String[] args) {
        try (var socket = new Socket("127.0.0.1", 9999);
             var sc = new Scanner(System.in);
             var os = socket.getOutputStream()) {
            PrintStream ps = new PrintStream(os);
            ps.println(sc.nextLine());
            ps.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

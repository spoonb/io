package io.bio.socket.demo04;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 问题：由于每增加一个客户端与服务端通信，服务端就会增加一个线程。随着并发量的增加，服务端存在资源耗尽的风险。
 * 方案：通过固定线程池的最大线程数，并且给定超过最大线程数时用以等待线程的队列。
 */
public class Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (var os = new Socket("127.0.0.1", 9999).getOutputStream();
             var ps = new PrintStream(os)) {
            String msg;
            do {
                msg = sc.nextLine();
                ps.println(msg);
                ps.println(msg);
            } while (!"exit".equalsIgnoreCase(msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

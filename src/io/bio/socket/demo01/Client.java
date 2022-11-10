package io.bio.socket.demo01;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * 需求：客户端可以向服务端发送消息
 */
public class Client {

    public static void main(String[] args) {
        try (var socket = new Socket("127.0.0.1", 9999)) {
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            /*
               由于没有换行所以服务端不认为收到了一行数据
               又因为客户端的main方法执行完毕导致程序exit从而导致socket被释放
               进而导致服务端的socket也被异常释放，报错
             */
            ps.print("hello world!");
//            ps.println("hello world!");
            ps.flush();
//            ps.close();
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

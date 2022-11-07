package bio.socket.demo03;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 需求：客户端可以不停向服务端发送消息
 */
public class Client {

    public static void main(String[] args) {
        try (var socket = new Socket("127.0.0.1", 9999)) {
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            Scanner sc = new Scanner(System.in);
            String msg = null;
            // do-while包裹即可
            do {
                msg = sc.nextLine();
                ps.println(msg);
                ps.flush();
            } while (!"exit".equalsIgnoreCase(msg)); // 输入exit时，客户端生命周期结束
            System.out.println("");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

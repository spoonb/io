package bio.socket.demo01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;

/**
 * 需求：服务端可以接受客户端发来的消息
 */
public class Server {

    public static void main(String[] args) {
        // 1. 创建Socket
        try (var socket = new ServerSocket(9999); var accept = socket.accept()) {
            // 2. 通过输入流读入客户端传入的信息
            InputStream is = accept.getInputStream();
            // 3. 将字节流转化为字符流
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // 4. 获取客户端发送的消息
            String msg = null;
            if ((msg = br.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

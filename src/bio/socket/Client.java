package bio.socket;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream os = socket.getOutputStream();
            PrintStream ps = new PrintStream(os);
            /*
               由于没有换行所以服务端不认为收到了一行数据
               又因为客户端的main方法执行完毕导致程序exit从而导致socket被释放
               进而导致服务端的socket也被异常释放，报错
             */
            ps.print("hello world!");
//             ps.println("hello world!");
            ps.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

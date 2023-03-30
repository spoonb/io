package bio.socketexception.demo01;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("127.0.0.1", 8083);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        InputStream br = socket.getInputStream();
        String msg;
        byte[] buff = new byte[2];
        while ((msg = sc.next()) != null && msg != "") {
            bw.write(msg);
            bw.flush();
            while (br.read(buff) > 0) {
                System.out.println(new String(buff));
            }
        }
    }
}

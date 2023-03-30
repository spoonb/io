package bio.socketexception.demo01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8083);
        Socket accept = server.accept();
        InputStream is = accept.getInputStream();
        OutputStream os = accept.getOutputStream();
        byte[] buff = new byte[2];
        while (is.read(buff) > 0) {
            os.write(buff);
            os.flush();
        }
    }
}

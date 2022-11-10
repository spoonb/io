package io.bio.socket.demo06;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

public class ServerThread extends Thread {

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (var dis = new DataInputStream(socket.getInputStream())) {


            OutputStream foo = new FileOutputStream("src/io/bio/socket/demo05/server/"
                    + UUID.randomUUID() + dis.readUTF());

            byte[] bytes = new byte[1024];
            int len;
            while ((len = dis.read(bytes)) != -1) {
                foo.write(bytes, 0, len);
                foo.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

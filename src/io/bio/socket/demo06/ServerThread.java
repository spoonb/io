package io.bio.socket.demo06;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ServerThread extends Thread {

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (var dis = new DataInputStream(socket.getInputStream())) {
            int size = dis.readInt();
            FileInfo[] files = new FileInfo[size];
            for (int i = 0; i < size; i ++) {
                files[i] = new FileInfo(dis.readUTF(), dis.readLong());
            }
            int len, i = 0;
            long done = 0;
            byte[] bytes = new byte[1024];
            OutputStream foo = new FileOutputStream("src/io/bio/socket/demo06/server/" + UUID.randomUUID() + files[i].getSuffix());
            while ((len = dis.read(bytes)) != -1) {
                if (files[i].getLen() - done > len) {
                    foo.write(bytes, 0, len);
                    foo.flush();
                    done += len;
                } else {
                    int cur = (int) (files[i].getLen() - done);
                    foo.write(bytes, 0, cur);
                    foo.close();
                    foo = new FileOutputStream("src/io/bio/socket/demo06/server/" + UUID.randomUUID() + files[++ i].getSuffix());
                    foo.write(bytes, cur, len - cur);
                    foo.flush();
                    done = len - cur;
                }
            }
            foo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

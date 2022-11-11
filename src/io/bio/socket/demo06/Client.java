package io.bio.socket.demo06;

import java.io.*;
import java.net.Socket;

/**
 * 需求：向客户端发送多个文件
 */
public class Client {

    public static void main(String[] args) {
        try (var socket = new Socket("127.0.0.1", 9999)) {
            File dir = new File("src/io/bio/socket/demo06/client/");
            OutputStream os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeInt(dir.listFiles().length);
            for (File file : dir.listFiles()) {
                dos.writeUTF(suffix(file));
                dos.writeLong(file.length());
            }
            byte[] bytes = new byte[1024];
            for (File file : dir.listFiles()) {
                BufferedInputStream bi = new BufferedInputStream(new FileInputStream(file));
                int len;
                while ((len = bi.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                    dos.flush();
                }
                bi.close();
            }
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String suffix(File file) {
        if (file == null) return "";
        String filename = file.getName();
        int idx = filename.lastIndexOf(".");
        if (idx == -1) {
            return "";
        }
        return filename.substring(idx);
    }
}

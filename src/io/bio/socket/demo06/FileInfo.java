package io.bio.socket.demo06;

public class FileInfo {

    private String suffix;

    private long len;

    public FileInfo(String suffix, long len) {
        this.suffix = suffix;
        this.len = len;
    }

    public String getSuffix() {
        return suffix;
    }

    public long getLen() {
        return len;
    }
}

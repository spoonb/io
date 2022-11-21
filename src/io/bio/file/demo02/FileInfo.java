package io.bio.file.demo02;

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

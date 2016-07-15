package com.baidubce.services.doc.model;

/**
 * Created by baidu on 16/1/7.
 */
public class DocumentMeta {
    private String md5 = null;
    private long sizeInBytes = -1;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(long sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DocumentMeta {\n");
        sb.append("    md5: ").append(md5).append("\n");
        sb.append("    sizeInBytes: ").append(sizeInBytes).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

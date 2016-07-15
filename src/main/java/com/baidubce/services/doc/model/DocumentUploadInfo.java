package com.baidubce.services.doc.model;

/**
 * Created by baidu on 16/1/7.
 */
public class DocumentUploadInfo {
    private String bucket = null;
    private String object = null;
    private String bosEndpoint = null;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getBosEndpoint() {
        return bosEndpoint;
    }

    public void setBosEndpoint(String bosEndpoint) {
        this.bosEndpoint = bosEndpoint;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DocumentUploadInfo {\n");
        sb.append("    bucket: ").append(bucket).append("\n");
        sb.append("    object: ").append(object).append("\n");
        sb.append("    bosEndpoint: ").append(bosEndpoint).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

package com.baidubce.services.doc.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Created by baidu on 15/12/31.
 */
public class RegisterDocumentResponse extends AbstractBceResponse {
    private String documentId;
    private String bucket;
    private String object;
    private String bosEndpoint;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

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
        sb.append("class RegisterDocument {\n");

        sb.append("    documentId: ").append(documentId).append("\n");
        sb.append("    bucket: ").append(bucket).append("\n");
        sb.append("    object: ").append(object).append("\n");
        sb.append("    bosEndpoint: ").append(bosEndpoint).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}

package com.baidubce.services.doc.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Created by baidu on 15/12/31.
 */
public class ReadDocumentResponse extends AbstractBceResponse {
    private String documentId;
    private String host;
    private String docId;
    private String token;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Read Document {\n");

        sb.append("    documentId: ").append(documentId).append("\n");
        sb.append("    host: ").append(host).append("\n");
        sb.append("    docId: ").append(docId).append("\n");
        sb.append("    token: ").append(token).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}

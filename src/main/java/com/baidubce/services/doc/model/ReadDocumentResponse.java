package com.baidubce.services.doc.model;

import com.baidubce.model.AbstractBceResponse;
import java.util.Date;

/**
 * Created by baidu on 15/12/31.
 */
public class ReadDocumentResponse extends AbstractBceResponse {
    private String documentId;
    private String host;
    private String docId;
    private String token;
    private Date createTime;
    private Date expireTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Read Document {\n");

        sb.append("    documentId: ").append(documentId).append("\n");
        sb.append("    host: ").append(host).append("\n");
        sb.append("    docId: ").append(docId).append("\n");
        sb.append("    token: ").append(token).append("\n");
        if (createTime != null) {
            sb.append("    createTime: ").append(createTime).append("\n");
        }
        if (expireTime != null) {
            sb.append("    expireTime: ").append(expireTime).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

}

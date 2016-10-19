package com.baidubce.services.doc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by baidu on 15/12/30.
 */
public class ReadDocumentRequest extends AbstractBceRequest {
    private String documentId = null;
    private long expireInSeconds = 3600;

    public ReadDocumentRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getDocumentId() { return this.documentId; }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public long getExpireInSeconds() {
        return expireInSeconds;
    }

    public void setExpireInSeconds(long expireInSeconds) {
        this.expireInSeconds = expireInSeconds;
    }
}

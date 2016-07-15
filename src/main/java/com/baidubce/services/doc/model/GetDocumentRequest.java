package com.baidubce.services.doc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by baidu on 15/12/30.
 */
public class GetDocumentRequest extends AbstractBceRequest {
    private String documentId = null;

    public GetDocumentRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getDocumentId() { return this.documentId; }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

}

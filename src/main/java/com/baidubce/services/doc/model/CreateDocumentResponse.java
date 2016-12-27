package com.baidubce.services.doc.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Created by baidu on 15/12/31.
 */
public class CreateDocumentResponse extends AbstractBceResponse {
    private String documentId;

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDocumentId() { return this.documentId; }
}

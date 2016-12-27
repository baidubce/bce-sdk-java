package com.baidubce.services.doc.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * Created by baidu on 15/12/31.
 */
public class GetDocumentDownloadResponse extends AbstractBceResponse {
    private String documentId = null;
    private String downloadUrl = null;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DocuementDownload {\n");

        sb.append("    documentId: ").append(documentId).append("\n");
        sb.append("    downloadUrl: ").append(downloadUrl).append("\n");

        sb.append("}\n");
        return sb.toString();
    }
}

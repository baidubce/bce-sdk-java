package com.baidubce.services.doc.model;

import com.baidubce.model.AbstractBceResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by baidu on 15/12/31.
 */
public class ListDocumentsResponse extends AbstractBceResponse {
    private List<Document> documents = new ArrayList<Document>();

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Document> getDocuments() { return this.documents; }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListDocumentsResponse {\n");
        sb.append("    documents: ").append(documents).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

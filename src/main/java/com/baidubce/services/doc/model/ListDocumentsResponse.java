/*
 * Copyright 2015 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.doc.model;

import com.baidubce.model.AbstractBceResponse;
import java.util.ArrayList;
import java.util.List;

public class ListDocumentsResponse extends AbstractBceResponse {
    private List<Document> documents = new ArrayList<Document>();

    private String marker;

    private boolean isTruncated;

    private String nextMarker;

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Document> getDocuments() { return this.documents; }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public boolean getIsTruncated() {
        return this.isTruncated;
    }

    public void setIsTruncated(boolean truncated) {
        this.isTruncated = truncated;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListDocumentsResponse {\n");
        sb.append("marker:").append(this.marker).append("\n");
        sb.append("nextMarker:").append(this.nextMarker).append("\n");
        sb.append("isTruncated").append(this.isTruncated).append("\n");
        sb.append("    documents: ").append(documents).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

/*
 * Copyright 2018 Baidu, Inc.
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
package com.baidubce.services.vcr.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * Get image async response.
 */
public class GetImageAsyncResponse extends AbstractBceResponse {
    private String source;
    private String label;
    private List<CheckResult> results;
    private VcrError error;
    private String status;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<CheckResult> getResults() {
        return results;
    }

    public void setResults(List<CheckResult> results) {
        this.results = results;
    }

    public VcrError getError() {
        return error;
    }

    public void setError(VcrError error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GetImageAsyncResponse{");
        sb.append("source='").append(source).append('\'');
        sb.append(", label='").append(label).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", error='").append(error).append('\'');
        sb.append(", results=").append(results);
        sb.append('}');
        return sb.toString();
    }
}

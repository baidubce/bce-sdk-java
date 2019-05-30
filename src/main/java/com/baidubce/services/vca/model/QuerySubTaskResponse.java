/**
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.baidubce.model.AbstractBceResponse;

/**
 * query sub task response.
 *
 * @author chenzhangli01
 */
public class QuerySubTaskResponse extends AbstractBceResponse {
    private String source;
    private String type;
    private String status;
    private String result;
    private VcaError error;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VcaError getError() {
        return error;
    }

    public void setError(VcaError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuerySubTaskResponse{");
        sb.append("source='").append(source).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", result='").append(result).append('\'');
        sb.append(", error=").append(error);
        sb.append('}');
        return sb.toString();
    }
}

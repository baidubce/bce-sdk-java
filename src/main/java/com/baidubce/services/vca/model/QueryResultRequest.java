/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Vca get media tags result request.
 *
 * @author houshunwei
 */
public class QueryResultRequest extends AbstractBceRequest {
    private String source;

    @Override
    public QueryResultRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public QueryResultRequest withMediaSource(String source) {
        this.setSource(source);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QueryResultRequest{");
        sb.append("source='").append(source).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}

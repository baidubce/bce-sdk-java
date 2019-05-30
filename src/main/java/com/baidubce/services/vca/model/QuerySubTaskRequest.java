/**
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * query sub task request.
 *
 * @author chenzhangli01
 */
public class QuerySubTaskRequest extends AbstractBceRequest {
    private String source;
    private String subTaskType;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSubTaskType() {
        return subTaskType;
    }

    public void setSubTaskType(String subTaskType) {
        this.subTaskType = subTaskType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuerySubTaskRequest{");
        sb.append("source='").append(source).append('\'');
        sb.append(", subTaskType='").append(subTaskType).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

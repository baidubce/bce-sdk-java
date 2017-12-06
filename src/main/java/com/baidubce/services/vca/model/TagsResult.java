/**
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.vca.model;

import java.util.List;

/**
 * Vca tags result model.
 *
 * @author houshunwei
 */
public class TagsResult {
    private String type;
    private List<ResultItem> result;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setResult(List<ResultItem> result) {
        this.result = result;
    }

    public List<ResultItem> getResult() {
        return this.result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TagsResult{");
        sb.append("type='").append(type).append('\'');
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}

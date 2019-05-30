/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.vehicle;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Response for query attribute name by display name
 */
public class SchemaAttributeNameResponse extends AbstractBceResponse {
    private String displayName;
    private List<String> attributeNames;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getAttributeNames() {
        return attributeNames;
    }

    public void setAttributeNames(List<String> attributeNames) {
        this.attributeNames = attributeNames;
    }
}

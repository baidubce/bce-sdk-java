/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eipbp.model;

import com.baidubce.services.eip.model.ListRequest;

/**
 * The request for listing eipBp.
 */
public class ListEipBpsRequest extends ListRequest {
    /**
     * eipbp id condition.
     */
    private String id;
    /**
     * eipbp name condition.
     */
    private String name;
    /**
     * eipbp binding instance type condition.
     */
    private String bindType;

    /**
     * eipbp type condition.
     */
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBindType() {
        return bindType;
    }

    public void setBindType(String bindType) {
        this.bindType = bindType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ListEipBpsRequest withId(String id) {
        this.id = id;
        return this;
    }

    public ListEipBpsRequest withName(String name) {
        this.name = name;
        return this;
    }

    public ListEipBpsRequest withBindType(String bindType) {
        this.bindType = bindType;
        return this;
    }

    public ListEipBpsRequest withType(String type) {
        this.type = type;
        return this;
    }
}

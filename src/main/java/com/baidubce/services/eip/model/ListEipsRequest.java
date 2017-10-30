/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eip.model;

/**
 * The request for listing eip.
 */
public class ListEipsRequest extends ListRequest {
    /**
     * eip address condition
     */
    private String eip;
    /**
     * bound instance type condition
     */
    private String instanceType;
    /**
     * bound instance id condition
     */
    private String instanceId;

    public String getEip() {
        return eip;
    }

    public void setEip(String eip) {
        this.eip = eip;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public ListEipsRequest withEip(String eip) {
        this.eip = eip;
        return this;
    }

    public ListEipsRequest withInstanceType(String instanceType) {
        this.instanceType = instanceType;
        return this;
    }

    public ListEipsRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }
}

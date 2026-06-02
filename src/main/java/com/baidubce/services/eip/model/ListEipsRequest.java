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
    /**
     *  eip ip type
     */
    private String ipVersion;

    /**
     * eip名称查询条件
     */
    private String name;

    /**
     * eip状态查询条件
     */
    private String status;

    /**
     * eip短ID列表查询条件
     */
    private String eipIds;

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

    public String getIpVersion() {
        return ipVersion;
    }

    public void setIpVersion(String ipVersion) {
        this.ipVersion = ipVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEipIds() {
        return eipIds;
    }

    public void setEipIds(String eipIds) {
        this.eipIds = eipIds;
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

    public ListEipsRequest withIpVersion(String ipVersion) {
        this.ipVersion = ipVersion;
        return this;
    }
    public ListEipsRequest withName(String name) {
        this.name = name;
        return this;
    }

    public ListEipsRequest withStatus(String status) {
        this.status = status;
        return this;
    }

    public ListEipsRequest withEipIds(String eipIds) {
        this.eipIds = eipIds;
        return this;
    }
}

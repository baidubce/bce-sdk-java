/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.endpoint.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.ListRequest;

/**
 * Created by XingChunyang
 * Date: 2021/01/21.
 */

public class ListEndpointRequest extends ListRequest {
    /**
     * The vpcId of Endpoint.
     */
    private String vpcId;


    /**
     * The name of Endpoint.
     */
    private String name;


    /**
     * The ipAddress of Endpoint.
     */
    private String ipAddress;


    /**
     * The status of Endpoint.
     */
    private String status;

    /**
     * The subnetId of Endpoint.
     */
    private String subnetId;

    /**
     * The service of Endpoint.
     */
    private String service;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}

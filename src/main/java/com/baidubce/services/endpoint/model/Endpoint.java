/*
 * Copyright (C) 2021 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.endpoint.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.ToString;

/**
 * Created by XingChunyang
 * Date: 2021/01/21.
 */

@ToString
public class Endpoint extends AbstractBceResponse {

    /**
     * The endpointId of endpoint.
     */
    private String endpointId;

    /**
     * The name of endpoint.
     */
    private String name;

    /**
     * The ipAddress of endpoint.
     */
    private String ipAddress;

    /**
     * The status of endpoint.
     */
    private String status;

    /**
     * The service of endpoint.
     */
    private String service;

    /**
     * The subnetId of endpoint.
     */
    private String subnetId;

    /**
     * The description of endpoint.
     */
    private String description;

    /**
     * The createTime of endpoint.
     */
    private String createTime;

    /**
     * The productType of endpoint.
     */
    private String productType;

    /**
     * The vpcId of endpoint.
     */
    private String vpcId;

    public String getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(String endpointId) {
        this.endpointId = endpointId;
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(String subnetId) {
        this.subnetId = subnetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }
}

/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListInstanceRequest {
    /**
     * 实例类型，取值[ eip | nat | etGateway | peerconn | csn | ipv6Gateway ]
     */
    private String instanceType;
    /**
     * 批量获取列表的查询的起始位置，是一个由系统生成的字符串
     */
    private String marker;
    /**
     * 每页包含的最大数量，最大数量通常不超过1000，缺省值为1000
     */
    private Integer maxKeys;
    /**
     * 防护状态，取值 [ unbound | protected | unprotected ]
     */
    private String status;
    /**
     * 地域信息，取值 [ bj | gz | su | fsh | hkg | bd | fwh | sin ]
     */
    private String region;

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}

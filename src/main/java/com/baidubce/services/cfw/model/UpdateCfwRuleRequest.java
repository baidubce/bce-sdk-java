/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateCfwRuleRequest extends BaseBceRequest {
    /**
     * IP协议类型，取值 [ 4 | 6 ]
     */
    private Integer ipVersion;

    /**
     * 规则优先级，取值 [ 1-1000 ]
     */
    private Integer priority;

    /**
     * 协议类型，取值 [ TCP | UDP | ICMP | ALL ]
     */
    private String protocol;

    /**
     * 方向，取值 [ in | out ]
     */
    private String direction;

    /**
     * 源IP，0.0.0.0/0表示所有
     */
    private String sourceAddress;

    /**
     * 目的IP，0.0.0.0/0表示所有
     */
    private String destAddress;

    /**
     * 源端口，0-65535表示所有
     */
    private String sourcePort;

    /**
     * 目的端口，0-65535表示所有
     */
    private String destPort;

    /**
     * 策略，取值 [ allow | deny ]
     */
    private String action;

    /**
     * CFW规则的描述
     */
    private String description;

    public void setIpVersion(Integer ipVersion) {
        this.ipVersion = ipVersion;
    }

    public Integer getIpVersion() {
        return this.ipVersion;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority() {
        return this.priority;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getProtocol() {
        return this.protocol;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getSourceAddress() {
        return this.sourceAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public String getDestAddress() {
        return this.destAddress;
    }

    public void setSourcePort(String sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getSourcePort() {
        return this.sourcePort;
    }

    public void setDestPort(String destPort) {
        this.destPort = destPort;
    }

    public String getDestPort() {
        return this.destPort;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "UpdateCfwRuleRequest{"
                + "ipVersion=" + ipVersion + "\n"
                + "priority=" + priority + "\n"
                + "protocol=" + protocol + "\n"
                + "direction=" + direction + "\n"
                + "sourceAddress=" + sourceAddress + "\n"
                + "destAddress=" + destAddress + "\n"
                + "sourcePort=" + sourcePort + "\n"
                + "destPort=" + destPort + "\n"
                + "action=" + action + "\n"
                + "description=" + description + "\n"
                + "}";
    }

}
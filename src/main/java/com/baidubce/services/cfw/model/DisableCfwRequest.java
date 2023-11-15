/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DisableCfwRequest extends BaseBceRequest {
    /**
     * 防护实例的id
     */
    private String instanceId;

    /**
     * PEERCONN实例特有属性，实例角色，取值 [ acceptor | initiatorn ]，当实例为PEERCONN时，该值必填
     */
    private String role;

    /**
     * CSN实例特有属性，CSN中网络实例id，当实例为CSN时，该值必填
     */
    private String memberId;

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return this.memberId;
    }

    @Override
    public String toString() {
        return "DisableCfwRequest{"
                + "instanceId=" + instanceId + "\n"
                + "role=" + role + "\n"
                + "memberId=" + memberId + "\n"
                + "}";
    }

}
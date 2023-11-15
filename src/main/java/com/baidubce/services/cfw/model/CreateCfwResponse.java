/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCfwResponse extends BaseBceResponse {
    /**
     * CFW的id
     */
    private String cfwId;

    public void setCfwId(String cfwId) {
        this.cfwId = cfwId;
    }

    public String getCfwId() {
        return this.cfwId;
    }

    @Override
    public String toString() {
        return "CreateCfwResponse{"
                + "cfwId=" + cfwId + "\n"
                + "}";
    }

}
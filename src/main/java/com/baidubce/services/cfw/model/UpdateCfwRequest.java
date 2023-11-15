/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateCfwRequest extends BaseBceRequest {
    /**
     * CFW名称，长度不超过65个字符，可由数字、字符、下划线组成
     */
    private String name;

    /**
     * CFW描述，不超过200字符
     */
    private String description;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "UpdateCfwRequest{"
                + "name=" + name + "\n"
                + "description=" + description + "\n"
                + "}";
    }

}
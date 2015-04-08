/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.ses.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The model object of DKIM attribute. It contains lots of properties.
 */
public class DkimAttrModel {
    private String identity;
    @JsonProperty("dkim_enabled")
    private boolean dkimEnabled;
    private DkimAttrModelTokenModel token;
    private Integer status;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public boolean isDkimEnabled() {
        return dkimEnabled;
    }

    public void setDkimEnabled(boolean dkimEnabled) {
        this.dkimEnabled = dkimEnabled;
    }

    public DkimAttrModelTokenModel getToken() {
        return token;
    }

    public void setToken(DkimAttrModelTokenModel token) {
        this.token = token;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DkimAttrModel [identity=" + identity + ", dkimEnabled=" + dkimEnabled + ", token=" + token
                + ", status=" + status + "]";
    }

}

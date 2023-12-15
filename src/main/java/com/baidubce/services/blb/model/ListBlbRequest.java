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
package com.baidubce.services.blb.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.ListRequest;

/**
 * The request for list blb.
 */
public class ListBlbRequest extends ListRequest {

    /**
     * the address of the blb.
     */
    private String address;
    /**
     * the name of the blb.
     */
    private String name;
    /**
     * the shor id of the blb.
     */
    private String blbId;
    /**
     * the id of the bcc.
     */
    private String bccId;
    /**
     * the type of the blb.
     */
    private String type;

    public ListBlbRequest withAddress(String address) {
        this.address = address;
        return this;
    }

    public ListBlbRequest withName(String name) {
        this.name = name;
        return this;
    }

    public ListBlbRequest withBlbId(String blbId) {
        this.blbId = blbId;
        return this;
    }

    public ListBlbRequest withBccId(String bccId) {
        this.bccId = bccId;
        return this;
    }

    public ListBlbRequest withType(String type) {
        this.type = type;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlbId() {
        return blbId;
    }

    public void setBlbId(String blbId) {
        this.blbId = blbId;
    }

    public String getBccId() {
        return bccId;
    }

    public void setBccId(String bccId) {
        this.bccId = bccId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public ListBlbRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

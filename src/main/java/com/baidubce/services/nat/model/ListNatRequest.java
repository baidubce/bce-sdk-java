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
package com.baidubce.services.nat.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.ListRequest;

/**
 * The request for list nat.
 */
public class ListNatRequest extends ListRequest {

    /**
     * The vpcId of nat.
     */
    private String vpcId;

    /**
     * The natId of nat.
     */
    private String natId;

    /**
     * The name of nat.
     */
    private String name;

    /**
     * The ip of nat.
     */
    private String ip;

    /**
     * Configure vpcId for the request.
     *
     * @param vpcId The vpcId of ListNatRequest
     * @return ListNatRequest with specific vpcId
     */
    public ListNatRequest withVpcId(String vpcId) {
        this.vpcId = vpcId;
        return this;
    }

    /**
     * Configure natId for the request.
     *
     * @param natId The vpcId of ListNatRequest
     * @return ListNatRequest with specific natId
     */
    public ListNatRequest withNatId(String natId) {
        this.natId = natId;
        return this;
    }

    /**
     * Configure name for the request.
     *
     * @param name The vpcId of ListNatRequest
     * @return ListNatRequest with specific name
     */
    public ListNatRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Configure ip for the request.
     *
     * @param ip The vpcId of ListNatRequest
     * @return ListNatRequest with specific ip
     */
    public ListNatRequest withIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getNatId() {
        return natId;
    }

    public void setNatId(String natId) {
        this.natId = natId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

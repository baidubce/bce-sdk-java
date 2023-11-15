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

import java.util.List;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bcc.model.TagModel;

/**
 * The response for get nat.
 */
public class GetNatResponse extends AbstractBceResponse {

    /**
     * The id of this nat.
     */
    private String id;

    /**
     * The name of this nat.
     */
    private String name;

    /**
     * The vpcId of this nat.
     */
    private String vpcId;

    /**
     * The spec of this nat.
     */
    private String spec;

    /**
     * The eips of this nat.
     */
    private List<String> eips;

    /**
     * The paymentTiming of this nat.
     */
    private String paymentTiming;

    /**
     * The expireTime of this nat.
     */
    private String expireTime;

    /**
     * The status of this nat.
     */
    private String status;

    /**
     * the list of tags which are bound to nat instance
     */
    private List<TagModel> tags;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVpcId() {
        return vpcId;
    }

    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public List<String> getEips() {
        return eips;
    }

    public void setEips(List<String> eips) {
        this.eips = eips;
    }

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }
}

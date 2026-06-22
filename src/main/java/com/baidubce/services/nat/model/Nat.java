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

import com.baidubce.services.bcc.model.TagModel;
import lombok.ToString;

import java.util.List;

/**
 * Nat detail info model
 */
@ToString
public class Nat {

    /**
     * The id of this nat.
     */
    private String id;

    /**
     * The name of this nat.
     */
    private String name;

    /**
     * The type of this nat, enhanced or normal.
     */
    private String natType;

    /**
     * The vpcId of this nat.
     */
    private String vpcId;

    /**
     * The spec of this nat.
     */
    private String spec;

    /**
     * The snat eips of this nat.
     */
    private List<String> eips;

    /**
     * The dnat eips of this nat.
     */
    private List<String> dnatEips;

    /**
     * The bind eips of this nat, only available for enhance nat.
     */
    private List<String> bindEips;

    /**
     * The paymentTiming of this nat.
     */
    private String paymentTiming;

    /**
     * The expireTime of this nat.
     */
    private String expireTime;

    /**
     * The createTime of this nat.
     */
    private String createTime;

    /**
     * The status of this nat.
     */
    private String status;

    /**
     * ipVersion
     * @return
     */
    private String ipVersion;

    /**
     * The delete protect switch of this nat.
     */
    private Boolean deleteProtect;

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

    public String getNatType() {
        return natType;
    }

    public void setNatType(String natType) {
        this.natType = natType;
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

    public List<String> getDnatEips() {
        return dnatEips;
    }

    public void setDnatEips(List<String> dnatEips) {
        this.dnatEips = dnatEips;
    }

    public List<String> getBindEips() {
        return bindEips;
    }

    public void setBindEips(List<String> bindEips) {
        this.bindEips = bindEips;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIpVersion() {
        return ipVersion;
    }

    public void setIpVersion(String ipVersion) {
        this.ipVersion = ipVersion;
    }

    public Boolean getDeleteProtect() {
        return deleteProtect;
    }

    public void setDeleteProtect(Boolean deleteProtect) {
        this.deleteProtect = deleteProtect;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }
}

/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * The list model for describing SecurityGroup.
 */
public class SecurityGroupModel extends AbstractBceResponse {

    /**
     * The id of the SecurityGroup.
     */
    private String id;

    /**
     * The name of the SecurityGroup.
     */
    private String name;

    /**
     * The id of vpc where the SecurityGroup in.
     */
    private String vpcId;

    /**
     * The description of the SecurityGroup.
     */
    private String desc;

    /**
     * The number of instances which bind to the SecurityGroup.
     */
    private Integer bindInstanceNum;

    /**
     * The creation time of the SecurityGroup.
     */
    private String createdTime;

    /**
     * List of rules which describe how the SecurityGroup works.
     */
    private List<SecurityGroupRuleModel> rules;

    /**
     * The list of tags.
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getBindInstanceNum() {
        return bindInstanceNum;
    }

    public void setBindInstanceNum(Integer bindInstanceNum) {
        this.bindInstanceNum = bindInstanceNum;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public List<SecurityGroupRuleModel> getRules() {
        return rules;
    }

    public void setRules(List<SecurityGroupRuleModel> rules) {
        this.rules = rules;
    }

    public List<TagModel> getTags() {
        return tags;
    }

    public void setTags(List<TagModel> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "SecurityGroupModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", vpcId='" + vpcId + '\'' +
                ", desc='" + desc + '\'' +
                ", bindInstanceNum='" + bindInstanceNum + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", rules=" + rules +
                ", tags=" + tags +
                '}';
    }
}

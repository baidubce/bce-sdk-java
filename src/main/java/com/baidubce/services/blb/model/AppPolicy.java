/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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

import java.util.List;

/**
 * The model of appBlb policy.
 */
public class AppPolicy {

    /**
     * the id of the policy.
     */
    private String id;
    /**
     * the description of the policy.
     */
    private String desc;
    /**
     * the appServerGroupId of the policy.
     */
    private String appServerGroupId;
    /**
     * the appServerGroupName of the policy.
     */
    private String appServerGroupName;
    /**
     * the appIpGroupId of the policy.
     */
    private String appIpGroupId;
    /**
     * the appIpGroupName of the policy.
     */
    private String appIpGroupName;
    /**
     * the groupType of the policy.
     */
    private String groupType;
    /**
     * the frontendPort of the policy.
     */
    private Integer frontendPort;
    /**
     * the backendPort of the policy.
     */
    private Integer backendPort;
    /**
     * the portType of the policy.
     */
    private String portType;
    /**
     * the priority of the policy.
     */
    private Integer priority;
    /**
     * the ruleList of the policy.
     */
    private List<AppRule> ruleList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAppServerGroupId() {
        return appServerGroupId;
    }

    public void setAppServerGroupId(String appServerGroupId) {
        this.appServerGroupId = appServerGroupId;
    }

    public String getAppServerGroupName() {
        return appServerGroupName;
    }

    public void setAppServerGroupName(String appServerGroupName) {
        this.appServerGroupName = appServerGroupName;
    }

    public Integer getFrontendPort() {
        return frontendPort;
    }

    public void setFrontendPort(Integer frontendPort) {
        this.frontendPort = frontendPort;
    }

    public Integer getBackendPort() {
        return backendPort;
    }

    public void setBackendPort(Integer backendPort) {
        this.backendPort = backendPort;
    }

    public String getPortType() {
        return portType;
    }

    public void setPortType(String portType) {
        this.portType = portType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public List<AppRule> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<AppRule> ruleList) {
        this.ruleList = ruleList;
    }

    public String getAppIpGroupId() {
        return appIpGroupId;
    }

    public void setAppIpGroupId(String appIpGroupId) {
        this.appIpGroupId = appIpGroupId;
    }

    public String getAppIpGroupName() {
        return appIpGroupName;
    }

    public void setAppIpGroupName(String appIpGroupName) {
        this.appIpGroupName = appIpGroupName;
    }

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    @Override
    public String toString() {
        return "AppPolicy{" +
                "id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", appServerGroupId='" + appServerGroupId + '\'' +
                ", appServerGroupName='" + appServerGroupName + '\'' +
                ", appIpGroupId='" + appIpGroupId + '\'' +
                ", appIpGroupName='" + appIpGroupName + '\'' +
                ", groupType='" + groupType + '\'' +
                ", frontendPort=" + frontendPort +
                ", backendPort=" + backendPort +
                ", portType='" + portType + '\'' +
                ", priority=" + priority +
                ", ruleList=" + ruleList +
                '}';
    }
}

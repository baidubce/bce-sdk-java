/*
 * Copyright 2023 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.userservice.model;

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUserServiceResponse extends BaseBceResponse {
    /**
     * 服务发布点的id
     */
    private String serviceId;

    /**
     * 服务发布点的名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 绑定服务类型，目前仅支持绑定BLB实例
     */
    private String bindType;

    /**
     * 绑定实例ID
     */
    private String instanceId;

    /**
     * 发布点状态，取值范围inService/available/unavailable/dead/free，分别表示：服务中/可用/不可用/故障/未绑定
     */
    private String status;

    /**
     * 服务发布点唯一对应域名
     */
    private String service;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 关联的服务网卡数量
     */
    private Integer endpointCount;

    /**
     * 关联的服务网卡列表
     */
    private List<ListUserServiceResponseEndpointList> endpointList;

    /**
     * 授权列表
     */
    private List<Auth> authList;

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceId() {
        return this.serviceId;
    }

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

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setBindType(String bindType) {
        this.bindType = bindType;
    }

    public String getBindType() {
        return this.bindType;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getService() {
        return this.service;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setEndpointCount(Integer endpointCount) {
        this.endpointCount = endpointCount;
    }

    public Integer getEndpointCount() {
        return this.endpointCount;
    }

    public void setEndpointList(List<ListUserServiceResponseEndpointList> endpointList) {
        this.endpointList = endpointList;
    }

    public List<ListUserServiceResponseEndpointList> getEndpointList() {
        return this.endpointList;
    }

    public void setAuthList(List<Auth> authList) {
        this.authList = authList;
    }

    public List<Auth> getAuthList() {
        return this.authList;
    }

    @Override
    public String toString() {
        return "GetUserServiceResponse{"
                + "serviceId=" + serviceId + "\n"
                + "name=" + name + "\n"
                + "description=" + description + "\n"
                + "serviceName=" + serviceName + "\n"
                + "bindType=" + bindType + "\n"
                + "instanceId=" + instanceId + "\n"
                + "status=" + status + "\n"
                + "service=" + service + "\n"
                + "createTime=" + createTime + "\n"
                + "endpointCount=" + endpointCount + "\n"
                + "endpointList=" + endpointList + "\n"
                + "authList=" + authList + "\n"
                + "}";
    }

    public static class ListUserServiceResponseEndpointList {
        private String endpointId;
    
        private String uid;
    
        private String attachTime;
    
        public void setEndpointId(String endpointId) {
            this.endpointId = endpointId;
        }
    
        public String getEndpointId() {
            return this.endpointId;
        }
    
        public void setUid(String uid) {
            this.uid = uid;
        }
    
        public String getUid() {
            return this.uid;
        }
    
        public void setAttachTime(String attachTime) {
            this.attachTime = attachTime;
        }
    
        public String getAttachTime() {
            return this.attachTime;
        }
    
        @Override
        public String toString() {
            return "ListUserServiceResponseEndpointList{"
                    + "endpointId=" + endpointId + "\n"
                    + "uid=" + uid + "\n"
                    + "attachTime=" + attachTime + "\n"
                    + "}";
        }
    }

    public static class Auth {
        private String uid;
    
        private String auth;
    
        public void setUid(String uid) {
            this.uid = uid;
        }
    
        public String getUid() {
            return this.uid;
        }
    
        public void setAuth(String auth) {
            this.auth = auth;
        }
    
        public String getAuth() {
            return this.auth;
        }
    
        @Override
        public String toString() {
            return "Auth{"
                    + "uid=" + uid + "\n"
                    + "auth=" + auth + "\n"
                    + "}";
        }
    }

}
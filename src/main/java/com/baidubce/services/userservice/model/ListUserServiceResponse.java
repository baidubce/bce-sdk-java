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
public class ListUserServiceResponse extends BaseBceResponse {
    /**
     * 标记查询的起始位置
     */
    private String marker;

    /**
     * true表示后面还有数据，false表示已经是最后一页
     */
    private Boolean isTruncated;

    /**
     * 获取下一页所需要传递的marker值。当isTruncated为false时，该域不出现
     */
    private String nextMarker;

    /**
     * 每页包含的最大数量
     */
    private Integer maxKeys;

    /**
     * 服务发布点列表
     */
    private List<Service> services;

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getMarker() {
        return this.marker;
    }

    public void setIsTruncated(Boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    public Boolean isIsTruncated() {
        return this.isTruncated;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    public String getNextMarker() {
        return this.nextMarker;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }

    public Integer getMaxKeys() {
        return this.maxKeys;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<Service> getServices() {
        return this.services;
    }

    @Override
    public String toString() {
        return "ListUserServiceResponse{"
                + "marker=" + marker + "\n"
                + "isTruncated=" + isTruncated + "\n"
                + "nextMarker=" + nextMarker + "\n"
                + "maxKeys=" + maxKeys + "\n"
                + "services=" + services + "\n"
                + "}";
    }

    public static class Service {
        private String serviceId;
    
        private String name;
    
        private String description;
    
        private String serviceName;
    
        private String bindType;
    
        private String instanceId;
    
        private String status;
    
        private String service;
    
        private String createTime;
    
        private Integer endpointCount;
    
        private List<ServiceEndpointList> endpointList;
    
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
    
        public void setEndpointList(List<ServiceEndpointList> endpointList) {
            this.endpointList = endpointList;
        }
    
        public List<ServiceEndpointList> getEndpointList() {
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
            return "Service{"
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
    
        public static class ServiceEndpointList {
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
                return "ServiceEndpointList{"
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

}
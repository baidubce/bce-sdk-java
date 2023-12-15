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

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserServiceRequest extends BaseBceRequest {
    /**
     * 服务发布点的名称，大小写字母、数字以及-_/.特殊字符，必须以字母开头，长度1-65
     */
    private String name;

    /**
     * 服务发布点的描述，最大支持200字符
     */
    private String description;

    /**
     * 对应服务名称,大小写字母、数字长度1-65
     */
    private String serviceName;

    /**
     * 绑定实例id，当前只支持绑定blb
     */
    private String instanceId;

    /**
     * authList
     */
    private List<Auth> authList;

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

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public void setAuthList(List<Auth> authList) {
        this.authList = authList;
    }

    public List<Auth> getAuthList() {
        return this.authList;
    }

    @Override
    public String toString() {
        return "CreateUserServiceRequest{"
                + "name=" + name + "\n"
                + "description=" + description + "\n"
                + "serviceName=" + serviceName + "\n"
                + "instanceId=" + instanceId + "\n"
                + "authList=" + authList + "\n"
                + "}";
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
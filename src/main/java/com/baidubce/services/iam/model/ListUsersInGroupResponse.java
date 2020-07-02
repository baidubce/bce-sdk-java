/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.iam.model;

import java.util.Date;
import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListUsersInGroupResponse extends BaseBceResponse {
    /**
     * users
     */
    private List<User> users;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return this.users;
    }

    @Override
    public String toString() {
        return "ListUsersInGroupResponse{"
                + "users=" + users + "\n"
                + "}";
    }

    public static class User {
        private String id;
    
        private String name;
    
        private String description;
    
        private Date createTime;
    
        private Boolean enabled;
    
        public void setId(String id) {
            this.id = id;
        }
    
        public String getId() {
            return this.id;
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
    
        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
    
        public Date getCreateTime() {
            return this.createTime;
        }
    
        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    
        public Boolean isEnabled() {
            return this.enabled;
        }
    
        @Override
        public String toString() {
            return "User{"
                    + "id=" + id + "\n"
                    + "name=" + name + "\n"
                    + "description=" + description + "\n"
                    + "createTime=" + createTime + "\n"
                    + "enabled=" + enabled + "\n"
                    + "}";
        }
    }

}
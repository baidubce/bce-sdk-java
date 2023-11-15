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

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListAccessKeyResponse extends BaseBceResponse {
    /**
     * accessKeys
     */
    private List<AccessKey> accessKeys;

    public void setAccessKeys(List<AccessKey> accessKeys) {
        this.accessKeys = accessKeys;
    }

    public List<AccessKey> getAccessKeys() {
        return this.accessKeys;
    }

    @Override
    public String toString() {
        return "ListAccessKeyResponse{"
                + "accessKeys=" + accessKeys + "\n"
                + "}";
    }

    public static class AccessKey {
        private String id;

        /**
         * createTime
         */
        private Date createTime;
    
        private String description;
    
        private Boolean enabled;

        private Date lastUsedTime;
    
        public void setId(String id) {
            this.id = id;
        }
    
        public String getId() {
            return this.id;
        }
    
        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
    
        public Date getCreateTime() {
            return this.createTime;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        public String getDescription() {
            return this.description;
        }
    
        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    
        public Boolean isEnabled() {
            return this.enabled;
        }

        public Date getLastUsedTime() {
            return lastUsedTime;
        }

        public void setLastUsedTime(Date lastUsedTime) {
            this.lastUsedTime = lastUsedTime;
        }

        @Override
        public String toString() {
            return "AccessKey{"
                    + "id=" + id + "\n"
                    + "createTime=" + createTime + "\n"
                    + "description=" + description + "\n"
                    + "enabled=" + enabled + "\n"
                    + "lastUsedTime=" + lastUsedTime + "\n"
                    + "}";
        }
    }

}
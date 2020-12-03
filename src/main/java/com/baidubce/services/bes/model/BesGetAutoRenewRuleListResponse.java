/**
 * Copyright 2020 Baidu, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bes.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class BesGetAutoRenewRuleListResponse extends AbstractBceResponse implements Serializable {
    private Boolean success;
    private int status;
    private List<AutoRenewRulesList> result;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AutoRenewRulesList> getResult() {
        return result;
    }

    public void setResult(List<AutoRenewRulesList> result) {
        this.result = result;
    }

    public static class AutoRenewRulesList {
        private String uuid;
        private String userId;
        private String clusterId;
        private String region;
        private String renewTimeUnit;
        private Integer renewTime;
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
                timezone = "UTC"
        )
        private Timestamp createTime;
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'",
                timezone = "UTC"
        )
        private Timestamp updateTime;

        public String getUuid() {
            return this.uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getClusterId() {
            return this.clusterId;
        }

        public void setClusterId(String clusterId) {
            this.clusterId = clusterId;
        }

        public String getRegion() {
            return this.region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getRenewTimeUnit() {
            return this.renewTimeUnit;
        }

        public void setRenewTimeUnit(String renewTimeUnit) {
            this.renewTimeUnit = renewTimeUnit;
        }

        public Integer getRenewTime() {
            return this.renewTime;
        }

        public void setRenewTime(Integer renewTime) {
            this.renewTime = renewTime;
        }

        public Timestamp getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(Timestamp createTime) {
            this.createTime = createTime;
        }

        public Timestamp getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(Timestamp updateTime) {
            this.updateTime = updateTime;
        }
    }
}
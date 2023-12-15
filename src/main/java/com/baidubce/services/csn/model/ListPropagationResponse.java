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
package com.baidubce.services.csn.model;

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ListPropagationResponse extends BaseBceResponse {
    /**
     * 包含查询结果的列表
     */
    private List<CsnRtPropagation> propagations;

    public void setPropagations(List<CsnRtPropagation> propagations) {
        this.propagations = propagations;
    }

    public List<CsnRtPropagation> getPropagations() {
        return this.propagations;
    }

    @Override
    public String toString() {
        return "ListPropagationResponse{"
                + "propagations=" + propagations + "\n"
                + "}";
    }

    public static class CsnRtPropagation {
        private String attachId;
    
        private String description;
    
        private String instanceId;
    
        private String instanceName;
    
        private String instanceRegion;
    
        private String instanceType;
    
        private String status;
    
        public void setAttachId(String attachId) {
            this.attachId = attachId;
        }
    
        public String getAttachId() {
            return this.attachId;
        }
    
        public void setDescription(String description) {
            this.description = description;
        }
    
        public String getDescription() {
            return this.description;
        }
    
        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }
    
        public String getInstanceId() {
            return this.instanceId;
        }
    
        public void setInstanceName(String instanceName) {
            this.instanceName = instanceName;
        }
    
        public String getInstanceName() {
            return this.instanceName;
        }
    
        public void setInstanceRegion(String instanceRegion) {
            this.instanceRegion = instanceRegion;
        }
    
        public String getInstanceRegion() {
            return this.instanceRegion;
        }
    
        public void setInstanceType(String instanceType) {
            this.instanceType = instanceType;
        }
    
        public String getInstanceType() {
            return this.instanceType;
        }
    
        public void setStatus(String status) {
            this.status = status;
        }
    
        public String getStatus() {
            return this.status;
        }
    
        @Override
        public String toString() {
            return "CsnRtPropagation{"
                    + "attachId=" + attachId + "\n"
                    + "description=" + description + "\n"
                    + "instanceId=" + instanceId + "\n"
                    + "instanceName=" + instanceName + "\n"
                    + "instanceRegion=" + instanceRegion + "\n"
                    + "instanceType=" + instanceType + "\n"
                    + "status=" + status + "\n"
                    + "}";
        }
    }

}
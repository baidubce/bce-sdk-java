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

import java.util.List;

import com.baidubce.common.BaseBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountCancelPreCheckResponse extends BaseBceResponse {
    /**
     * checkResult
     */
    private ModelNull checkResult;

    /**
     * checkResultList
     */
    private List<ModelNull> checkResultList;

    public void setCheckResult(ModelNull checkResult) {
        this.checkResult = checkResult;
    }

    public ModelNull getCheckResult() {
        return this.checkResult;
    }

    public void setCheckResultList(List<ModelNull> checkResultList) {
        this.checkResultList = checkResultList;
    }

    public List<ModelNull> getCheckResultList() {
        return this.checkResultList;
    }

    @Override
    public String toString() {
        return "AccountCancelPreCheckResponse{"
                + "checkResult=" + checkResult + "\n"
                + "checkResultList=" + checkResultList + "\n"
                + "}";
    }

    public static class ModelNull {
        private String type;
    
        private Boolean pass;
    
        private List<String> reason;
    
        public void setType(String type) {
            this.type = type;
        }
    
        public String getType() {
            return this.type;
        }
    
        public void setPass(Boolean pass) {
            this.pass = pass;
        }
    
        public Boolean isPass() {
            return this.pass;
        }
    
        public void setReason(List<String> reason) {
            this.reason = reason;
        }
    
        public List<String> getReason() {
            return this.reason;
        }
    
        @Override
        public String toString() {
            return "ModelNull{"
                    + "type=" + type + "\n"
                    + "pass=" + pass + "\n"
                    + "reason=" + reason + "\n"
                    + "}";
        }
    }

}
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
public class AuthenticationResponse extends BaseBceResponse {
    /**
     * userId
     */
    private String userId;

    /**
     * name
     */
    private String name;

    /**
     * sessionTokenModel
     */
    private ModelNull sessionTokenModel;

    /**
     * sessionToken
     */
    private ModelNull sessionToken;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSessionTokenModel(ModelNull sessionTokenModel) {
        this.sessionTokenModel = sessionTokenModel;
    }

    public ModelNull getSessionTokenModel() {
        return this.sessionTokenModel;
    }

    public void setSessionToken(ModelNull sessionToken) {
        this.sessionToken = sessionToken;
    }

    public ModelNull getSessionToken() {
        return this.sessionToken;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{"
                + "userId=" + userId + "\n"
                + "name=" + name + "\n"
                + "sessionTokenModel=" + sessionTokenModel + "\n"
                + "sessionToken=" + sessionToken + "\n"
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
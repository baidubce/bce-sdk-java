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
public class EditAuthRequest extends BaseBceRequest {
    /**
     * authList
     */
    private List<Auth> authList;

    public void setAuthList(List<Auth> authList) {
        this.authList = authList;
    }

    public List<Auth> getAuthList() {
        return this.authList;
    }

    @Override
    public String toString() {
        return "EditAuthRequest{"
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
/*
 * Copyright 2018 Baidu, Inc.
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
package com.baidubce.services.kms.model;

import com.baidubce.auth.BceCredentials;

/**
 * Provides options for creating master key.
 */
public class CreateKeyRequest extends GenericKmsRequest {

    private String description;

    private String keyUsage;

    public CreateKeyRequest() {
    }

    public CreateKeyRequest(String description, String keyUsage) {
        this.setDescription(description);
        this.setKeyUsage(keyUsage);
    }
    
    /**
     * Overrides abstract method withRequestCredentials(BceCredentials) in AbstractBceRequest
     */ 
    public CreateKeyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setKeyUsage(String keyUsage) {
        this.keyUsage = keyUsage;
    }

    public String getKeyUsage() {
        return keyUsage;
    }
}
// vim: et tw=100 ts=4 sw=4 cc=120

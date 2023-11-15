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

    private String protectedBy;

    private String keySpec;

    private String keyUsage;

    private String origin;

    private int rotateCycle;

    public CreateKeyRequest() {
    }

    public CreateKeyRequest(String description, String protectedBy, String keyUsage, 
        String keySpec, String origin) {
        this.setDescription(description);
        this.setProtectedBy(protectedBy);
        this.setKeySpec(keySpec);
        this.setKeyUsage(keyUsage);
        this.setOrigin(origin);
        this.setRotateCycle(0);
    }

    public CreateKeyRequest(String description, String protectedBy, String keyUsage, 
        String keySpec, String origin, int rotateCycle) {
        this.setDescription(description);
        this.setProtectedBy(protectedBy);
        this.setKeySpec(keySpec);
        this.setKeyUsage(keyUsage);
        this.setOrigin(origin);
        this.setRotateCycle(rotateCycle);
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

    public String getProtectedBy() {
        return protectedBy;
    }

    public void setProtectedBy(String protectedBy) {
        this.protectedBy = protectedBy;
    }
    public String getKeySpec() {
        return keySpec;
    }

    public void setKeySpec(String keySpec) {
        this.keySpec = keySpec;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setRotateCycle(int rotateCycle){
        this.rotateCycle = rotateCycle;
    }

    public int getRotateCycle(){
        return this.rotateCycle;
    }
}
// vim: et tw=100 ts=4 sw=4 cc=120

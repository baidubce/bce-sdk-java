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
 * Provides options for deleting master key.
 */
public class ScheduleKeyDeletionRequest extends GenericKmsRequest {

    private String keyId;

    private int pendingWindowInDays;

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public ScheduleKeyDeletionRequest() {
    }

    public ScheduleKeyDeletionRequest(String keyId, int pendingWindowsInDays) {
        this.setPendingWindowInDays(pendingWindowsInDays);
        this.setKeyId(keyId);
    }

    /**
     * Overrides abstract method withRequestCredentials(BceCredentials) in AbstractBceRequest
     */ 
    public ScheduleKeyDeletionRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setPendingWindowInDays(int pendingWindowInDays) {
        this.pendingWindowInDays = pendingWindowInDays;
    }

    public int getPendingWindowsInDays() {
        return pendingWindowInDays;
    }
}
// vim: et tw=100 ts=4 sw=4 cc=120

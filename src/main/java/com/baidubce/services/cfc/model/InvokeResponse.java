/*
 * Copyright  2019 Baidu, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.cfc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response object for invoking a CFC function
 */
public class InvokeResponse extends CfcResponse {
    /**
     * Represent the result of invoking the user function
     */
    @JsonProperty(value = "Payload")
    private byte[] Payload;

    /**
     * The log of invoking the user function
     */
    @JsonProperty(value = "BceLogResult")
    private String BceLogResult;

    /**
     * Get the result of invoking the user function
     * @return The result of invoking the user function
     */
    @JsonProperty(value = "Payload")
    public byte[] getPayload() {
        return Payload;
    }

    /**
     * Set the result of invoking the user function
     * @param payload The result of invoking the user function
     */
    public void setPayload(byte[] payload) {
        Payload = payload;
    }

    /**
     * Get the log of invoking the user function
     * @return The log of invoking the user function
     */
    @JsonProperty(value = "BceLogResult")
    public String getBceLogResult() {
        return BceLogResult;
    }

    /**
     * Set the log of invoking the user function
     * @param bceLogResult The log of invoking the user function
     */
    public void setBceLogResult(String bceLogResult) {
        BceLogResult = bceLogResult;
    }
}
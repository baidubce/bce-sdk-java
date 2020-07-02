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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Request object for publishing the CFC function
 */
public class PublishVersionRequest extends AbstractBceRequest {
    /**
     * Function Name You can specify a function name (for example, Thumbnail), or you can specify the function's BRN
     * resource name (for example: brn:bce:cfc:bj:account-id:function:thumbnail). CFC also allows you to specify a
     * partial BRN (for example, account-id:Thumbnail). Note that the BRN length is limited to 1-140. If only the
     * function name is specified, the length is limited to 64 characters.
     */
    @JsonProperty(value = "FunctionName")
    private String FunctionName;

    /**
     * The version description 0-256
     */
    @JsonProperty(value = "Description")
    private String Description;

    /**
     * The SHA256 hash of the deployment package you want to publish. This will provide verification for the code you
     * posted. If you supply this parameter, the value must match the SHA256 of the latest version that was successfully
     * published.
     */
    @JsonProperty(value = "CodeSha256")
    private String CodeSha256;

    /**
     * Get the function name
     * @return The function name
     */
    @JsonProperty(value = "FunctionName")
    public String getFunctionName() {
        return this.FunctionName;
    }

    /**
     * Set  function Name You can specify a function name (for example, Thumbnail), or you can specify the function's
     * BRN resource name (for example: brn:bce:cfc:bj:account-id:function:thumbnail). CFC also allows you to specify a
     * partial BRN (for example, account-id:Thumbnail). Note that the BRN length is limited to 1-140. If only the
     * function name is specified, the length is limited to 64 characters.
     * @param functionName
     */
    public void setFunctionName(String functionName) {
        this.FunctionName = functionName;
    }

    /**
     * Get the version description
     * @return The version description
     */
    @JsonProperty(value = "Description")
    public String getDescription() {
        return this.Description;
    }

    /**
     * Set the version description
     * @param description The version description
     */
    public void setDescription(String description) {
        this.Description = description;
    }

    /**
     * Get the function code sha256
     * @return The function code sha256
     */
    @JsonProperty(value = "CodeSha256")
    public String getCodeSha256() {
        return this.CodeSha256;
    }

    /**
     * Set the function code sha256
     * @param codeSha256 The function code sha256
     */
    public void setCodeSha256(String codeSha256) {
        this.CodeSha256 = codeSha256;
    }

    public PublishVersionRequest withFunctionName(String functionName) {
        this.setFunctionName(functionName);
        return this;
    }

    public PublishVersionRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public PublishVersionRequest withCodeSha256(String codeSha256) {
        this.setCodeSha256(codeSha256);
        return this;
    }

    /**
     * (non-Javadoc)
     *
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce.auth.BceCredentials)
     */
    @Override
    public PublishVersionRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        try {
            return JsonUtils.toJsonPrettyString(this);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}


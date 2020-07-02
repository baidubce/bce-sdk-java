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
 * Request object for updating the CFC function code
 */
public class UpdateFunctionCodeRequest extends AbstractBceRequest {
    /**
     * Function Name You can specify a function name (for example, Thumbnail), or you can specify the function's BRN
     * resource name (for example, brn:bce:cfc:bj:account-id:function:thumbnail). CFC also allows you to specify a
     * partial BRN (for example, account-id:Thumbnail). Note that the BRN length is limited to 1-140. If only the
     * function name is specified, the length is limited to 64 characters.
     */
    @JsonProperty(value = "FunctionName")
    private String FunctionName;

    /**
     * The base64-encoded of the zip package you want to publish Note that the zip package compresses the contents of
     * the directory, not the directory itself.
     */
    @JsonProperty(value = "ZipFile")
    private String ZipFile;

    /**
     * Whether to publish directly
     */
    @JsonProperty(value = "Publish")
    private boolean Publish;

    /**
     * This Boolean parameter can be used to test your request for CFC to update the CFC function and publish a version
     * as an atomic operation. It will do all the necessary calculations and validations for your code, but will not
     * upload it or release a version. Each time the operation is called, the CodeSha256 hash value of the supplied
     * code is also evaluated and returned in the response. Not yet supported
     */
    @JsonProperty(value = "DryRun")
    private boolean DryRun;

    /**
     * Get the function name
     * @return The function name
     */
    @JsonProperty(value = "FunctionName")
    public String getFunctionName() {
        return this.FunctionName;
    }

    /**
     * Set the function name
     * @param functionName The function name
     */
    public void setFunctionName(String functionName) {
        this.FunctionName = functionName;
    }

    /**
     * Get the function code zip
     * @return The function code zip
     */
    @JsonProperty(value = "ZipFile")
    public String getZipFile() {
        return this.ZipFile;
    }

    /**
     * Set the function code zip
     * @param zipFile The function code zip
     */
    public void setZipFile(String zipFile) {
        this.ZipFile = zipFile;
    }

    /**
     * Get the publish
     * @return The publish
     */
    @JsonProperty(value = "Publish")
    public boolean getPublish() {
        return this.Publish;
    }

    /**
     * Set the publish
     * @param publish The publish
     */
    public void setPublish(boolean publish) {
        this.Publish = publish;
    }

    /**
     * Get the dryrun
     * @return The dryrun
     */
    @JsonProperty(value = "DryRun")
    public boolean getDryRun() {
        return this.DryRun;
    }

    /**
     * Set the dryrun
     * @param dryRun The dryrun
     */
    public void setDryRun(boolean dryRun) {
        this.DryRun = dryRun;
    }

    public UpdateFunctionCodeRequest withFunctionName(String functionName) {
        this.setFunctionName(functionName);
        return this;
    }

    public UpdateFunctionCodeRequest withZipFile(String zipFile) {
        this.setZipFile(zipFile);
        return this;
    }

    public UpdateFunctionCodeRequest withPublish(boolean publish) {
        this.setPublish(publish);
        return this;
    }

    public UpdateFunctionCodeRequest withDryRun(boolean dryRun) {
        this.setDryRun(dryRun);
        return this;
    }

    public UpdateFunctionCodeRequest withRequestCredentials(BceCredentials credentials) {
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


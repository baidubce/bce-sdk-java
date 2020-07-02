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
 * Represent the user function code
 */
public class Code {

    /**
     * Contains the contents of the compressed directory, not the directory itself
     */
    @JsonProperty(value = "ZipFile")
    private String ZipFile;

    /**
     * Whether to release the version, true or false
     */
    @JsonProperty(value = "Publish")
    private boolean Publish;

    /**
     * true or false
     */
    @JsonProperty(value = "DryRun")
    private boolean DryRun;

    /**
     * Get the Zip package of the function code file to be published.
     * When using this interface, you should convert the contents of the zip file to base64-encoded.
     * @return ZipFile The Zip package of the function code file to be published.
     */
    @JsonProperty(value = "ZipFile")
    public String getZipFile() {
        return this.ZipFile;
    }

    /**
     * Set the Zip package of the function code file to be published.
     * When using this interface, you should convert the contents of the zip file to base64-encoded.
     * @param zipFile The Zip package of the function code file to be published.
     */
    public void setZipFile(String zipFile) {
        this.ZipFile = zipFile;
    }

    /**
     * Get the publish
     * @return Publish
     */
    @JsonProperty(value = "Publish")
    public boolean getPublish() {
        return this.Publish;
    }

    /**
     * Set the publish
     * @param publish
     */
    public void setPublish(boolean publish) {
        this.Publish = publish;
    }

    @JsonProperty(value = "DryRun")
    public boolean getDryRun() {
        return this.DryRun;
    }

    public void setDryRun(boolean dryrun) {
        this.DryRun = dryrun;
    }
}

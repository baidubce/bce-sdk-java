/*
 * Copyright (c) 2019-2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.keypair;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * The request class for importing keypair
 */
public class KeypairImportRequest extends AbstractBceRequest {

    /**
     * the name of the keypair to be imported.
     */
    private String name;

    /**
     * the desctiption of the keypair to be imported.
     */
    private String description;

    /**
     * the public key to be imported.
     */
    private String publicKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }


    public KeypairImportRequest withName(String name) {
        this.name = name;
        return this;
    }

    public KeypairImportRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public KeypairImportRequest withPublicKey(String publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    @Override
    public String toString() {
        return "KeypairImportRequest{name='" + name
                + "', description='" + description
                + "', publicKey='" + publicKey + "'}";
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

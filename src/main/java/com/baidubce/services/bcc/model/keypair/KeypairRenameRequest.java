/*
 * Copyright 2020 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.bcc.model.keypair;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request for renaming the keypair.
 */
public class KeypairRenameRequest extends AbstractBceRequest {

    /**
     * The short id of the keypair
     */
    @JsonIgnore
    private String keypairId;

    /**
     * the new name of the keypair to be renamed.
     */
    private String name;

    public String getKeypairId() {
        return keypairId;
    }

    public void setKeypairId(String keypairId) {
        this.keypairId = keypairId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public KeypairRenameRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        return "KeypairRenameRequest{" +
                "keypairId='" + keypairId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

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
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The request class when detaching keypair from instances
 */
public class KeypairDetachRequest extends AbstractBceRequest {

    /**
     * keypair id
     */
    @JsonIgnore
    private String keypairId;

    /**
     * The list of instance ids, specify the instances whose keypair will be detached.
     */
    private List<String> instanceIds;

    public String getKeypairId() {
        return keypairId;
    }

    public void setKeypairId(String keypairId) {
        this.keypairId = keypairId;
    }

    public List<String> getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }

    public KeypairDetachRequest withKeypairId(String keypairId) {
        this.keypairId = keypairId;
        return this;
    }

    public KeypairDetachRequest addInstance(String instanceId) {
        if (this.instanceIds == null) {
            this.instanceIds = new ArrayList<String>();
        }

        this.instanceIds.add(instanceId);
        return this;
    }

    @Override
    public String toString() {
        return "KeypairDetachRequest{keypairId='" + keypairId
                + "', instanceIds=" + Arrays.toString(instanceIds.toArray())
                + "}";
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

import java.util.ArrayList;
import java.util.List;

/**
 * The request class used for attaching keypair to instances
 */
public class KeypairAttachRequest extends AbstractBceRequest {

    /**
     * The short id of the keypair
     */
    private String keypairId;

    /**
     * The list of instance ids, specify which instances should the keypair been attached to
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

    public  KeypairAttachRequest addInstance(String instanceId) {
        if (null == this.instanceIds) {
            this.instanceIds = new ArrayList<String>();
        }

        this.instanceIds.add(instanceId);
        return this;
    }

    public KeypairAttachRequest withKeypairId(String keypairId) {
        this.keypairId = keypairId;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

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
package com.baidubce.services.dcc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The request class for renaming dcc instance.
 */
public class DccRenameRequest extends AbstractBceRequest  {

    /**
     * The short id of the dcc instance.
     */
    @JsonIgnore
    private String instanceId;

    /**
     * The new name of the dcc instance.
     */
    private String name;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DccRenameRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }

    public DccRenameRequest withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return "DccRenameRequest{name='" + name
                + "', instanceId='" + instanceId + "}";
    }

    @Override
    public DccRenameRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

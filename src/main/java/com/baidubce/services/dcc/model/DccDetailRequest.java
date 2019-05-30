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

/**
 * The request class of querying detail information of dcc.
 */
public class DccDetailRequest extends AbstractBceRequest {

    /**
     * The short id of the dcc.
     */
    private String dccId;

    public String getDccId() {
        return dccId;
    }

    public void setDccId(String dccId) {
        this.dccId = dccId;
    }

    public DccDetailRequest withDccId(String dccId) {
        this.dccId = dccId;
        return this;
    }

    @Override
    public String toString() {
        return "DccDetailRequest{ dccId='" + dccId + "'}";
    }

    @Override
    public DccDetailRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

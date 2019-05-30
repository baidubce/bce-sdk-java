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
package com.baidubce.services.bbc.model.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * The request for getting vpc/subnet of bbc instance
 */
public class GetInstanceVpcRequest extends AbstractBceRequest {

    /**
     * The ids of bbc
     */
    private List<String> bbcIds;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetInstanceRequest with credentials.
     */
    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public List<String> getBbcIds() {
        return bbcIds;
    }

    /**
     * Configure the bbc instanceId list for the request.
     *
     * @param bbcIds The id of instance.
     * @return GetInstanceRequest with specified instanceId.
     */
    public GetInstanceVpcRequest withBbcIds(List<String> bbcIds) {
        this.setBbcIds(bbcIds);
        return this;
    }

    public void setBbcIds(List<String> bbcIds) {
        this.bbcIds = bbcIds;
    }
}

/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.vm.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bec.model.ListRequest;
import lombok.Data;

/**
 * The request for getting the BEC virtual machine list of the node.
 */
@Data
public class GetBecNodeVmInstanceListRequest extends AbstractBceRequest {

    /**
     * List request information.
     */
    private ListRequest listRequest;

    /**
     * the region of the node.
     */
    private String region;

    /**
     * the serviceProvider of the node.
     */
    private String serviceProvider;

    /**
     * the city of the node.
     */
    private String city;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetBecNodeVmInstanceListRequest with credentials.
     */
    public GetBecNodeVmInstanceListRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.network.nat.instance;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bec.model.ListRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-20 11:18
 * @Version v1.0
 * <p>
 * The request for getting nats.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBecNatsRequest extends AbstractBceRequest {

    /**
     * ListRequest.
     */
    private ListRequest listRequest;

    /**
     * The vpc id.
     */
    private String vpcId;

    /**
     * The region id.
     */
    private String regionId;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return Request entity with credentials.
     */
    public GetBecNatsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
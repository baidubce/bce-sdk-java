/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bsc.model.billing;

import com.baidu.bce.billing.proxy.model.v1.LegacyChargeDataRequest;
import com.baidu.bce.internalsdk.core.BceClient;
import com.baidu.bce.internalsdk.core.Entity;
import lombok.extern.slf4j.Slf4j;

/**
 * Provides the client for accessing the billing.
 */

@Slf4j
public class BillingClient extends BceClient {
    public BillingClient(String endpoint, String accessKey, String secretKey) {
        super(endpoint, accessKey, secretKey);
    }

    public void sendBilling(LegacyChargeDataRequest request) {
        SendBillingResponse response = createAuthorizedRequest().path("/v1/chargedata")
                .post(Entity.json(request), SendBillingResponse.class);

        log.info("send billing response: requestId -> {}, code -> {}, message -> {}", response.getRequestId(),
                response.getCode(), response.getMessage());
    }
}

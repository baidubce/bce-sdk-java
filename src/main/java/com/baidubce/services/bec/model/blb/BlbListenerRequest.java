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
package com.baidubce.services.bec.model.blb;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bec.model.enums.LbModeEnum;
import lombok.Data;

/**
 * The request for creating a load balancing listener setting.
 */
@Data
public class BlbListenerRequest extends AbstractBceRequest {

    /**
     * Load balancing port.
     */
    private Port frontendPort;

    /**
     * Backend port.
     */
    private Integer backendPort;

    /**
     * Whether enable to get the true ip.
     */
    private Boolean enableCipTTM;

    /**
     * Forwarding rules.
     */
    private LbModeEnum lbMode;

    /**
     * Health check settings.
     */
    private HealthCheck healthCheck;

    /**
     * The keepalive timeout, 10 - 4000 seconds.
     */
    private Integer keepaliveTimeout;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return BlbListenerRequest with credentials.
     */
    public BlbListenerRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

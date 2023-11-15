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
package com.baidubce.services.bec.model.overview;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * The request for getting BEC user level container metrics.
 */
@Data
public class GetBecContainerMetricsRequest extends AbstractBceRequest {

    /**
     * Metrics Type.
     */
    private String type;

    /**
     * Offset In Seconds.
     */
    private Integer offsetInSeconds;

    /**
     * ServiceProvider.
     */
    private String serviceProvider;

    /**
     * stepInMin
     */
    private Integer stepInMin;

    /**
     * Configure request credential for the request.
     *
     * @param credentials a valid instance of BceCredentials.
     * @return GetInstanceRequest with credentials.
     */
    public GetBecContainerMetricsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

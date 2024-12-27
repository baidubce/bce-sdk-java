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

import lombok.Data;

/**
 * Health check settings.
 */
@Data
public class HealthCheck {

    /**
     * Response timeout period.
     */
    private Integer timeoutInSeconds;

    /**
     * Health check interval time.
     */
    private Integer intervalInSeconds;

    /**
     * Unhealthy threshold.
     */
    private Integer unhealthyThreshold;

    /**
     * Healthy threshold.
     */
    private Integer healthyThreshold;

    /**
     * Healthy check string, udp check string.
     */
    private String healthCheckString;

    /**
     * The health check type, like tcp, http, udp.
     */
    private String healthCheckType;

    /**
     * The health check url path, default /.
     */
    private String healthCheckURI;
}

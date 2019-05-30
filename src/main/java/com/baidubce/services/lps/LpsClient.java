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
package com.baidubce.services.lps;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.lps.model.DirectionRequest;
import com.baidubce.services.lps.model.DirectionResponse;
import com.baidubce.services.lps.model.RouteMatrixRequest;
import com.baidubce.services.lps.model.RouteMatrixResponse;
import com.baidubce.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lps Client which provides the ability to communicating with logistic map service.
 *
 * @author weizhijun
 * @date 2019/03/07
 */
@Slf4j
public class LpsClient extends BaseLpsClient {

    private static final String DIRECTION_URL_PATH = "/v1/lps/logistics/direction";
    private static final String ROUTE_MATRIX_URL_PATH = "/v1/lps/logistics/routematrix";

    /**
     * Construct a lps client with default settings.
     */
    public LpsClient() {
        super();
    }

    /**
     * Construct a lps client with customized bce client configuration.
     *
     * @param configuration BceClientConfiguration
     */
    public LpsClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    /**
     * Construct a lps client access key, secret key and other default settings.
     *
     * @param accessKey access key id
     * @param secretKey secret key
     */
    public LpsClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    /**
     * Direction the path between the given start point and end point, based on the given planning strategy.
     *
     * @param request DirectionRequest
     *
     * @return DirectionResponse
     */
    public DirectionResponse direction(DirectionRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, DIRECTION_URL_PATH);
        log.info("direction request: {}", JsonUtils.toJsonString(internalRequest));
        return this.invokeHttpClient(internalRequest, DirectionResponse.class);
    }

    /**
     * Batch calculate the restriction, distance and duration information of the route according to the given point
     * matrix (multiple start point and multiple end point).
     *
     * @param request RouteMatrixRequest
     *
     * @return RouteMatrixResponse
     */
    public RouteMatrixResponse routeMatrix(RouteMatrixRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, ROUTE_MATRIX_URL_PATH);
        log.info("route matrix request: {}", JsonUtils.toJsonString(internalRequest));
        return this.invokeHttpClient(internalRequest, RouteMatrixResponse.class);
    }
}

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

import java.util.HashMap;
import java.util.Map;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.lps.model.ListRouteResponse;
import com.baidubce.services.lps.model.UploadRouteRequest;
import com.baidubce.services.lps.model.UploadRouteResponse;
import com.baidubce.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Lps Route Client which provides the ability to communicating with private route service.
 * <p>
 * Created by chenbo14 on 2019/11/28.
 */
@Slf4j
public class LpsRouteClient extends BaseLpsClient {

    private static final String UPLOAD_ROUTE_URL_PATH = "/v1/lps/route/upload";
    private static final String LIST_ROUTE_URL_PATH = "/v1/lps/route/list";
    private static final String ROUTE_URL_PATH = "/v1/lps/route";
    private static final String PAGE_NO = "pageNo";
    private static final String PAGE_SIZE = "pageSize";

    /**
     * Construct a lps client with default settings.
     */
    public LpsRouteClient() {
        super();
    }

    /**
     * Construct a lps client with customized bce client configuration.
     *
     * @param configuration BceClientConfiguration
     */
    public LpsRouteClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    /**
     * Construct a lps client access key, secret key and other default settings.
     *
     * @param accessKey access key id
     * @param secretKey secret key
     */
    public LpsRouteClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    /**
     * Upload the private route Info
     *
     * @param request UploadRouteRequest
     *
     * @return uploadRouteResponse with routeId
     */
    public UploadRouteResponse uploadRouteInfo(UploadRouteRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, UPLOAD_ROUTE_URL_PATH);
        log.info("uploadRouteInfo request: {}", JsonUtils.toJsonString(internalRequest));
        return this.invokeHttpClient(internalRequest, UploadRouteResponse.class);
    }

    /**
     * List route info
     *
     * @param pageNo
     * @param pageSize
     *
     * @return
     */
    public ListRouteResponse listRouteInfo(int pageNo, int pageSize) {
        Map<String, String> params = new HashMap<String, String>();
        params.put(PAGE_NO, String.valueOf(pageNo));
        params.put(PAGE_SIZE, String.valueOf(pageSize));
        InternalRequest internalRequest = createRequest(null, HttpMethodName.GET, LIST_ROUTE_URL_PATH);
        internalRequest.setParameters(params);
        log.info("listRouteInfo request: {}", JsonUtils.toJsonString(internalRequest));
        return this.invokeHttpClient(internalRequest, ListRouteResponse.class);
    }

    /**
     * delete route info
     *
     * @param routeId
     *
     * @return
     */
    public void deleteRouteInfo(String routeId) {
        InternalRequest internalRequest = createRequest(null, HttpMethodName.DELETE,
                ROUTE_URL_PATH + "/" + routeId);
        log.info("deleteRouteInfo request: {}", JsonUtils.toJsonString(internalRequest));
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

}

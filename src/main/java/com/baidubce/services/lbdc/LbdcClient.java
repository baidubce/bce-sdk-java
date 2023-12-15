/*
 * Copyright 2023 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.lbdc;

import java.util.Map;

import com.baidubce.BceClientConfiguration;
import com.baidubce.common.ApiInfo;
import com.baidubce.common.BaseBceClient;
import com.baidubce.common.BaseBceResponse;
import com.baidubce.common.BceRegion;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.lbdc.api.LbdcApi;
import com.baidubce.services.lbdc.model.CreateLbdcRequest;
import com.baidubce.services.lbdc.model.CreateLbdcResponse;
import com.baidubce.services.lbdc.model.GetBoundBlBListOfLbdcResponse;
import com.baidubce.services.lbdc.model.GetLbdcResponse;
import com.baidubce.services.lbdc.model.ListLbdcResponse;
import com.baidubce.services.lbdc.model.RenewLbdcRequest;
import com.baidubce.services.lbdc.model.UpdateLbdcRequest;
import com.baidubce.services.lbdc.model.UpgradeLbdcRequest;
import com.google.common.collect.ImmutableMap;

/**
 * Lbdc
 */
public class LbdcClient extends BaseBceClient {
    private static final Map<BceRegion, String> ENDPOINTS = ImmutableMap.<BceRegion, String>builder()
            .put(BceRegion.BJ, "http://blb.bj.baidubce.com")
            .build();
    /**
     * Service name for extra config and handler.
     */
    private static final String SERVICE_ID = "Lbdc";

    /**
     * Constructs a new client to invoke service methods on demo with region.
     */
    public LbdcClient(String ak, String sk, BceRegion region) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(region));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public LbdcClient(String ak, String sk) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(BceRegion.DEFAULT));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public LbdcClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    /**
     * Api lists
     */
    private static final Map<String, ApiInfo> LBDC_APIS = LbdcApi.getApis();

    public CreateLbdcResponse createLbdc(CreateLbdcRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LBDC_APIS.get("createLbdc"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreateLbdcResponse.class);
    }

    public GetBoundBlBListOfLbdcResponse getBoundBlBListOfLbdc(String id) {
        ApiInfo apiInfo = new ApiInfo(LBDC_APIS.get("getBoundBlBListOfLbdc"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("id", id).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetBoundBlBListOfLbdcResponse.class);
    }

    public GetLbdcResponse getLbdc(String id) {
        ApiInfo apiInfo = new ApiInfo(LBDC_APIS.get("getLbdc"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("id", id).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetLbdcResponse.class);
    }

    public ListLbdcResponse listLbdc(String id, String name) {
        ApiInfo apiInfo = new ApiInfo(LBDC_APIS.get("listLbdc"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("id", id);
        apiInfo.getQueries().put("name", name);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListLbdcResponse.class);
    }

    public void renewLbdc(String id, RenewLbdcRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LBDC_APIS.get("renewLbdc"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("id", id).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void updateLbdc(String id, UpdateLbdcRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LBDC_APIS.get("updateLbdc"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("id", id).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void upgradeLbdc(String id, UpgradeLbdcRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LBDC_APIS.get("upgradeLbdc"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("id", id).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

}

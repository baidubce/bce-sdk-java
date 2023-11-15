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
package com.baidubce.services.localdns;

import com.baidubce.BceClientConfiguration;
import com.baidubce.common.ApiInfo;
import com.baidubce.common.BaseBceClient;
import com.baidubce.common.BaseBceResponse;
import com.baidubce.common.BceRegion;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.localdns.api.LdApi;
import com.baidubce.services.localdns.model.AddRecordRequest;
import com.baidubce.services.localdns.model.AddRecordResponse;
import com.baidubce.services.localdns.model.BindVpcRequest;
import com.baidubce.services.localdns.model.CreatePrivateZoneRequest;
import com.baidubce.services.localdns.model.CreatePrivateZoneResponse;
import com.baidubce.services.localdns.model.GetPrivateZoneResponse;
import com.baidubce.services.localdns.model.ListPrivateZoneResponse;
import com.baidubce.services.localdns.model.ListRecordResponse;
import com.baidubce.services.localdns.model.UnbindVpcRequest;
import com.baidubce.services.localdns.model.UpdateRecordRequest;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Ld
 */
public class LdClient extends BaseBceClient {

    private static final Map<BceRegion, String> ENDPOINTS = ImmutableMap.<BceRegion, String>builder()
            .put(BceRegion.BJ, "http://privatezone.baidubce.com")
            .build();
    /**
     * Service name for extra config and handler.
     */
    private static final String SERVICE_ID = "Ld";

    /**
     * Constructs a new client to invoke service methods on demo with region.
     */
    public LdClient(String ak, String sk, BceRegion region) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(region));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public LdClient(String ak, String sk) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(BceRegion.DEFAULT));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public LdClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    /**
     * Api lists
     */
    private static final Map<String, ApiInfo> LD_APIS = LdApi.getApis();

    public AddRecordResponse addRecord(String zoneId, AddRecordRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("addRecord"));
        String apiPath = apiInfo.getPath().withPathParameter("zoneId", zoneId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, AddRecordResponse.class);
    }

    public void bindVpc(String zoneId, BindVpcRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("bindVpc"));
        String apiPath = apiInfo.getPath().withPathParameter("zoneId", zoneId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public CreatePrivateZoneResponse createPrivateZone(CreatePrivateZoneRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("createPrivateZone"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreatePrivateZoneResponse.class);
    }

    public void deletePrivateZone(String zoneId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("deletePrivateZone"));
        String apiPath = apiInfo.getPath().withPathParameter("zoneId", zoneId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void deleteRecord(String recordId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("deleteRecord"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("recordId", recordId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void disableRecord(String recordId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("disableRecord"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("recordId", recordId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void enableRecord(String recordId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("enableRecord"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("recordId", recordId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public GetPrivateZoneResponse getPrivateZone(String zoneId) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("getPrivateZone"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("zoneId", zoneId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetPrivateZoneResponse.class);
    }

    public ListPrivateZoneResponse listPrivateZone(String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("listPrivateZone"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("marker", marker);
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListPrivateZoneResponse.class);
    }

    public ListRecordResponse listRecord(String zoneId) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("listRecord"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("zoneId", zoneId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListRecordResponse.class);
    }

    public void unbindVpc(String zoneId, UnbindVpcRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("unbindVpc"));
        String apiPath = apiInfo.getPath().withPathParameter("zoneId", zoneId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void updateRecord(String recordId, UpdateRecordRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(LD_APIS.get("updateRecord"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("recordId", recordId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

}

/*
 * Copyright 2022 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.dns;

import com.baidubce.BceClientConfiguration;
import com.baidubce.common.ApiInfo;
import com.baidubce.common.BaseBceClient;
import com.baidubce.common.BaseBceResponse;
import com.baidubce.common.BceRegion;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.dns.api.DnsApi;
import com.baidubce.services.dns.model.AddLineGroupRequest;
import com.baidubce.services.dns.model.CreatePaidZoneRequest;
import com.baidubce.services.dns.model.CreateRecordRequest;
import com.baidubce.services.dns.model.CreateZoneRequest;
import com.baidubce.services.dns.model.ListLineGroupResponse;
import com.baidubce.services.dns.model.ListRecordResponse;
import com.baidubce.services.dns.model.ListZoneResponse;
import com.baidubce.services.dns.model.RenewZoneRequest;
import com.baidubce.services.dns.model.UpdateLineGroupRequest;
import com.baidubce.services.dns.model.UpdateRecordRequest;
import com.baidubce.services.dns.model.UpgradeZoneRequest;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Dns
 */
public class DnsClient extends BaseBceClient {

    private static final Map<BceRegion, String> ENDPOINTS = ImmutableMap.<BceRegion, String>builder()
            .put(BceRegion.DEFAULT, "http://dns.baidubce.com")
            .build();
    /**
     * Service name for extra config and handler.
     */
    private static final String SERVICE_ID = "Dns";

    /**
     * Constructs a new client to invoke service methods on demo with region.
     */
    public DnsClient(String ak, String sk, BceRegion region) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(region));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public DnsClient(String ak, String sk) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(BceRegion.DEFAULT));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public DnsClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    /**
     * Api lists
     */
    private static final Map<String, ApiInfo> DNS_APIS = DnsApi.getApis();

    public void addLineGroup(AddLineGroupRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("addLineGroup"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void createPaidZone(CreatePaidZoneRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("createPaidZone"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void createRecord(String zoneName, CreateRecordRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("createRecord"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("zoneName", zoneName).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void createZone(CreateZoneRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("createZone"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void deleteLineGroup(String lineId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("deleteLineGroup"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("lineId", lineId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void deleteRecord(String zoneName, String recordId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("deleteRecord"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("zoneName", zoneName)
                .withPathParameter("recordId", recordId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void deleteZone(String zoneName, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("deleteZone"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("zoneName", zoneName).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public ListLineGroupResponse listLineGroup(String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("listLineGroup"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("marker", marker);
        if (maxKeys == null) {
            maxKeys = 1000;
        }
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListLineGroupResponse.class);
    }

    public ListRecordResponse listRecord(String zoneName, String rr, String id, String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("listRecord"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("zoneName", zoneName).get();
        apiInfo.getQueries().put("rr", rr);
        apiInfo.getQueries().put("id", id);
        apiInfo.getQueries().put("marker", marker);
        if (maxKeys == null) {
            maxKeys = 1000;
        }
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListRecordResponse.class);
    }

    public ListZoneResponse listZone(String name, String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("listZone"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("name", name);
        apiInfo.getQueries().put("marker", marker);
        if (maxKeys == null) {
            maxKeys = 1000;
        }
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListZoneResponse.class);
    }

    public void renewZone(String name, RenewZoneRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("renewZone"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("name", name).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void updateLineGroup(String lineId, UpdateLineGroupRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("updateLineGroup"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("lineId", lineId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void updateRecord(String zoneName, String recordId, UpdateRecordRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("updateRecord"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("zoneName", zoneName)
                .withPathParameter("recordId", recordId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void updateRecordDisable(String zoneName, String recordId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("updateRecordDisable"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("zoneName", zoneName)
                .withPathParameter("recordId", recordId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void updateRecordEnable(String zoneName, String recordId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("updateRecordEnable"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("zoneName", zoneName)
                .withPathParameter("recordId", recordId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void upgradeZone(UpgradeZoneRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(DNS_APIS.get("upgradeZone"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

}

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
package com.baidubce.services.userservice;

import java.util.Map;

import com.baidubce.BceClientConfiguration;
import com.baidubce.common.ApiInfo;
import com.baidubce.common.BaseBceClient;
import com.baidubce.common.BaseBceResponse;
import com.baidubce.common.BceRegion;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.userservice.api.UserserviceApi;
import com.baidubce.services.userservice.model.AddAuthRequest;
import com.baidubce.services.userservice.model.BindInstanceRequest;
import com.baidubce.services.userservice.model.CreateUserServiceRequest;
import com.baidubce.services.userservice.model.CreateUserServiceResponse;
import com.baidubce.services.userservice.model.EditAuthRequest;
import com.baidubce.services.userservice.model.GetUserServiceResponse;
import com.baidubce.services.userservice.model.ListUserServiceResponse;
import com.baidubce.services.userservice.model.RemoveAuthRequest;
import com.baidubce.services.userservice.model.UpdateUserServiceRequest;
import com.google.common.collect.ImmutableMap;

/**
 * Userservice
 */
public class UserserviceClient extends BaseBceClient {
    private static final Map<BceRegion, String> ENDPOINTS = ImmutableMap.<BceRegion, String>builder()
            .put(BceRegion.BJ, "http://blb.bj.baidubce.com")
            .build();
    /**
     * Service name for extra config and handler.
     */
    private static final String SERVICE_ID = "Userservice";

    /**
     * Constructs a new client to invoke service methods on demo with region.
     */
    public UserserviceClient(String ak, String sk, BceRegion region) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(region));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public UserserviceClient(String ak, String sk) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(BceRegion.DEFAULT));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public UserserviceClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    /**
     * Api lists
     */
    private static final Map<String, ApiInfo> USERSERVICE_APIS = UserserviceApi.getApis();

    public void addAuth(String service, AddAuthRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(USERSERVICE_APIS.get("addAuth"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("service", service).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void bindInstance(String service, BindInstanceRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(USERSERVICE_APIS.get("bindInstance"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("service", service).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public CreateUserServiceResponse createUserService(CreateUserServiceRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(USERSERVICE_APIS.get("createUserService"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreateUserServiceResponse.class);
    }

    public void deleteUserService(String service, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(USERSERVICE_APIS.get("deleteUserService"));
        String apiPath = apiInfo.getPath().withPathParameter("service", service).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void editAuth(String service, EditAuthRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(USERSERVICE_APIS.get("editAuth"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("service", service).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public GetUserServiceResponse getUserService(String service) {
        ApiInfo apiInfo = new ApiInfo(USERSERVICE_APIS.get("getUserService"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("service", service).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetUserServiceResponse.class);
    }

    public ListUserServiceResponse listUserService(String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(USERSERVICE_APIS.get("listUserService"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("marker", marker);
        if (maxKeys == null) {
            maxKeys = 1000;
        }
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListUserServiceResponse.class);
    }

    public void removeAuth(String service, RemoveAuthRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(USERSERVICE_APIS.get("removeAuth"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("service", service).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void unbindInstance(String service, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(USERSERVICE_APIS.get("unbindInstance"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("service", service).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    public void updateUserService(String service, UpdateUserServiceRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(USERSERVICE_APIS.get("updateUserService"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("service", service).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

}

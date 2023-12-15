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
package com.baidubce.services.csn;

import java.util.Map;

import com.baidubce.BceClientConfiguration;
import com.baidubce.common.ApiInfo;
import com.baidubce.common.BaseBceClient;
import com.baidubce.common.BaseBceResponse;
import com.baidubce.common.BceRegion;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.csn.api.CsnApi;
import com.baidubce.services.csn.model.AttachInstanceRequest;
import com.baidubce.services.csn.model.BindCsnBpRequest;
import com.baidubce.services.csn.model.CreateAssociationRequest;
import com.baidubce.services.csn.model.CreateCsnBpLimitRequest;
import com.baidubce.services.csn.model.CreateCsnBpRequest;
import com.baidubce.services.csn.model.CreateCsnBpResponse;
import com.baidubce.services.csn.model.CreateCsnRequest;
import com.baidubce.services.csn.model.CreateCsnResponse;
import com.baidubce.services.csn.model.CreatePropagationRequest;
import com.baidubce.services.csn.model.CreateRouteRuleRequest;
import com.baidubce.services.csn.model.DeleteCsnBpLimitRequest;
import com.baidubce.services.csn.model.DetachInstanceRequest;
import com.baidubce.services.csn.model.GetCsnBpResponse;
import com.baidubce.services.csn.model.GetCsnResponse;
import com.baidubce.services.csn.model.ListAssociationResponse;
import com.baidubce.services.csn.model.ListCsnBpLimitByCsnIdResponse;
import com.baidubce.services.csn.model.ListCsnBpLimitResponse;
import com.baidubce.services.csn.model.ListCsnBpResponse;
import com.baidubce.services.csn.model.ListCsnResponse;
import com.baidubce.services.csn.model.ListInstanceResponse;
import com.baidubce.services.csn.model.ListPropagationResponse;
import com.baidubce.services.csn.model.ListRouteRuleResponse;
import com.baidubce.services.csn.model.ListRouteTableResponse;
import com.baidubce.services.csn.model.ListTgwResponse;
import com.baidubce.services.csn.model.ListTgwRuleResponse;
import com.baidubce.services.csn.model.ResizeCsnBpRequest;
import com.baidubce.services.csn.model.UnbindCsnBpRequest;
import com.baidubce.services.csn.model.UpdateCsnBpLimitRequest;
import com.baidubce.services.csn.model.UpdateCsnBpRequest;
import com.baidubce.services.csn.model.UpdateCsnRequest;
import com.baidubce.services.csn.model.UpdateTgwRequest;
import com.google.common.collect.ImmutableMap;

/**
 * Csn
 */
public class CsnClient extends BaseBceClient {

    private static final Map<BceRegion, String> ENDPOINTS = ImmutableMap.<BceRegion, String>builder()
            .put(BceRegion.DEFAULT, "http://csn.baidubce.com")
            .build();

    /**
     * Service name for extra config and handler.
     */
    private static final String SERVICE_ID = "Csn";

    /**
     * Constructs a new client to invoke service methods on demo with region.
     */
    public CsnClient(String ak, String sk, BceRegion region) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(region));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public CsnClient(String ak, String sk) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(BceRegion.DEFAULT));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public CsnClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    /**
     * Api lists
     */
    private static final Map<String, ApiInfo> CSN_APIS = CsnApi.getApis();

    /*    -------------------------- 云智能网相关 --------------------------    */

    /**
     * 创建云智能网。
     *
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串，详见ClientToken幂等性
     * @param body
     * @return CreateCsnResponse
     */
    public CreateCsnResponse createCsn(CreateCsnRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("createCsn"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreateCsnResponse.class);
    }

    /**
     * 更新云智能网。  更新云智能网的名称和描述。
     *
     * @param csnId 云智能网ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串，详见ClientToken幂等性
     * @param body
     */
    public void updateCsn(String csnId, UpdateCsnRequest body, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("updateCsn"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnId", csnId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 删除云智能网。  已经加载了网络实例的云智能网不能直接删除，必须先卸载实例。
     *
     * @param csnId 云智能网的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串，详见ClientToken幂等性
     */
    public void deleteCsn(String csnId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("deleteCsn"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnId", csnId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 查询云智能网列表。
     *
     * @param marker 批量获取列表的查询的起始位置，是一个由系统生成的字符串
     * @param maxKeys 每页包含的最大数量，最大数量不超过1000，缺省值为1000
     * @return ListCsnResponse
     */
    public ListCsnResponse listCsn(String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("listCsn"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("marker", marker);
        if (null == maxKeys) {
            maxKeys = 1000;
        }
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListCsnResponse.class);
    }

    /**
     * 查询指定云智能网下加载的网络实例信息。
     *
     * @param csnId 云智能网的ID
     * @param marker 批量获取列表的查询的起始位置，是一个由系统生成的字符串
     * @param maxKeys 每页包含的最大数量，最大数量不超过1000，缺省值为1000
     * @return ListInstanceResponse 云智能网下加载的网络实例列表
     */
    public ListInstanceResponse listInstance(String csnId, String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("listInstance"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnId", csnId).get();
        apiInfo.getQueries().put("marker", marker);
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListInstanceResponse.class);
    }

    /**
     * 查询云智能网详情。
     *
     * @param csnId csnId 云智能网的ID
     * @return GetCsnResponse 云智能网详情
     */
    public GetCsnResponse getCsn(String csnId) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("getCsn"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnId", csnId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetCsnResponse.class);
    }

    /**
     * 从云智能网中移出指定的网络实例。
     *
     * @param csnId 云智能网的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串，详见ClientToken幂等性
     * @param detachInstanceRequest 卸载网络实例的请求参数
     */
    public void detachInstance(String csnId, DetachInstanceRequest detachInstanceRequest, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("detachInstance"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnId", csnId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), detachInstanceRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 将网络实例加载进云智能网。
     *
     * @param csnId 云智能网的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串，详见ClientToken幂等性
     * @param attachInstanceRequest 加载网络实例的请求参数
     */
    public void attachInstance(String csnId, AttachInstanceRequest attachInstanceRequest, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("attachInstance"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnId", csnId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), attachInstanceRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /*    -------------------------- 路由管理相关 --------------------------    */

    /**
     * 添加云智能网路由表的路由条目。
     *
     * @param csnRtId 云智能网路由表的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串，详见ClientToken幂等性
     * @param createRouteRuleRequest
     */
    public void createRouteRule(String csnRtId, CreateRouteRuleRequest createRouteRuleRequest,
                                String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("createRouteRule"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnRtId", csnRtId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), createRouteRuleRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 查询指定云智能网路由表的路由条目。
     *
     * @param csnRtId 云智能网路由表的ID
     * @param marker 批量获取列表的查询的起始位置，是一个由系统生成的字符串
     * @param maxKeys 每页包含的最大数量，最大数量不超过1000。缺省值为1000
     * @return ListRouteRuleResponse
     */
    public ListRouteRuleResponse listRouteRule(String csnRtId, String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("listRouteRule"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnRtId", csnRtId).get();
        apiInfo.getQueries().put("marker", marker);
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListRouteRuleResponse.class);
    }

    /**
     * 删除云智能网路由表的指定路由条目。
     *
     * @param csnRtId 路由表的ID
     * @param csnRtRuleId 路由条目的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串，详见ClientToken幂等性
     */
    public void deleteRouteRule(String csnRtId, String csnRtRuleId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("deleteRouteRule"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnRtId", csnRtId)
                .withPathParameter("csnRtRuleId", csnRtRuleId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 创建路由表的学习关系。
     *
     * @param csnRtId 云智能网路由表的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串，详见ClientToken幂等性
     * @param createPropagationRequest
     */
    public void createPropagation(String csnRtId, CreatePropagationRequest createPropagationRequest,
                                  String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("createPropagation"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnRtId", csnRtId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), createPropagationRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 查询指定云智能网路由表的学习关系。
     *
     * @param csnRtId 云智能网路由表的ID
     * @return ListPropagationResponse
     */
    public ListPropagationResponse listPropagation(String csnRtId) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("listPropagation"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnRtId", csnRtId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListPropagationResponse.class);
    }

    /**
     * 删除云智能网路由表的学习关系。
     *
     * @param csnRtId 路由表的ID
     * @param attachId 网络实例在云智能网中的身份ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串，详见ClientToken幂等性
     */
    public void deletePropagation(String csnRtId, String attachId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("deletePropagation"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnRtId", csnRtId)
                .withPathParameter("attachId", attachId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 创建路由表的关联关系。
     *
     * @param csnRtId 云智能网路由表的ID
     * @param createAssociationRequest 创建路由表参数
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串，详见ClientToken幂等性
     */
    public void createAssociation(String csnRtId,
                                  CreateAssociationRequest createAssociationRequest,
                                  String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("createAssociation"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnRtId", csnRtId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), createAssociationRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 查询指定云智能网路由表的关联关系。
     *
     * @param csnRtId 云智能网路由表的ID
     * @return ListAssociationResponse
     */
    public ListAssociationResponse listAssociation(String csnRtId) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("listAssociation"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnRtId", csnRtId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListAssociationResponse.class);
    }

    /**
     * 删除云智能网路由表的关联关系。
     *
     * @param csnRtId 路由表的ID
     * @param attachId 网络实例在云智能网中的身份ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串，详见ClientToken幂等性
     */
    public void deleteAssociation(String csnRtId, String attachId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("deleteAssociation"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnRtId", csnRtId)
                .withPathParameter("attachId", attachId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 查询云智能网的路由表列表。
     *
     * @param csnId 云智能网的ID
     * @param marker 批量获取列表的查询的起始位置，是一个由系统生成的字符串
     * @param maxKeys 每页包含的最大数量，最大数量不超过1000，缺省值为1000
     * @return ListRouteTableResponse 云智能网的路由表列表
     */
    public ListRouteTableResponse listRouteTable(String csnId, String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("listRouteTable"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnId", csnId).get();
        apiInfo.getQueries().put("marker", marker);
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListRouteTableResponse.class);
    }

    /*    -------------------------- 带宽包相关 --------------------------    */

    /**
     * 查询云智能网带宽包列表。
     *
     * @param marker 批量获取列表的查询的起始位置，是一个由系统生成的字符串
     * @param maxKeys 每页包含的最大数量，最大数量不超过1000，缺省值为1000
     * @return 云智能网带宽包列表
     */
    public ListCsnBpResponse listCsnBp(String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("listCsnBp"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("marker", marker);
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListCsnBpResponse.class);
    }

    /**
     * 查询指定云智能网带宽包详情。
     *
     * @param csnBpId 带宽包的ID
     * @return 云智能网带宽包详情
     */
    public GetCsnBpResponse getCsnBp(String csnBpId) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("getCsnBp"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnBpId", csnBpId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetCsnBpResponse.class);
    }

    /**
     * 创建云智能网共享带宽包。
     *
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串
     * @param createCsnBpRequest 创建云智能网带宽包参数
     * @return CreateCsnBpResponse 云智能网带宽包返回，内含带宽包的ID
     */
    public CreateCsnBpResponse createCsnBp(CreateCsnBpRequest createCsnBpRequest, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("createCsnBp"));
        String apiPath = apiInfo.getPath().get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), createCsnBpRequest);
        return invokeHttpClient(internalRequest, CreateCsnBpResponse.class);
    }

    /**
     * 更新带宽包的名称信息。
     *
     * @param csnBpId 带宽包的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串
     * @param updateCsnBpRequest 更新带宽包传参，内含需要更改后的name
     */
    public void updateCsnBp(String csnBpId, UpdateCsnBpRequest updateCsnBpRequest, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("updateCsnBp"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnBpId", csnBpId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), updateCsnBpRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 删除带宽包。
     *
     * @param csnBpId 带宽包的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串
     */
    public void deleteCsnBp(String csnBpId, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("deleteCsnBp"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnBpId", csnBpId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 带宽包的带宽升降级。
     *
     * @param csnBpId 带宽包的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串
     * @param resizeCsnBpRequest 带宽包升级参数，内含升降级的带宽值
     */
    public void resizeCsnBp(String csnBpId, ResizeCsnBpRequest resizeCsnBpRequest, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("resizeCsnBp"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnBpId", csnBpId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), resizeCsnBpRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 带宽包解绑云智能网。
     *
     * @param csnBpId 带宽包的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串
     * @param unbindCsnBpRequest 解绑云智能网参数，内含云智能网ID
     */
    public void unbindCsnBp(String csnBpId, UnbindCsnBpRequest unbindCsnBpRequest, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("unbindCsnBp"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnBpId", csnBpId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), unbindCsnBpRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 带宽包绑定云智能网。
     *
     * @param csnBpId 带宽包的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串
     * @param bindCsnBpRequest 绑定参数，内含云智能网ID
     */
    public void bindCsnBp(String csnBpId, BindCsnBpRequest bindCsnBpRequest, String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("bindCsnBp"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnBpId", csnBpId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), bindCsnBpRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /*    -------------------------- 区域带宽相关 --------------------------    */

    /**
     * 查询带宽包的地域带宽列表。
     *
     * @param csnBpId 带宽包的ID
     * @return 带宽包的地域带宽列表
     */
    public ListCsnBpLimitResponse listCsnBpLimit(String csnBpId) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("listCsnBpLimit"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnBpId", csnBpId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListCsnBpLimitResponse.class);
    }

    /**
     * 创建带宽包中两个地域间的地域带宽。
     *
     * @param csnBpId 带宽包的ID
     * @param createCsnBpLimitRequest 创建地域带宽参数
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串
     */
    public void createCsnBpLimit(String csnBpId,
                                 CreateCsnBpLimitRequest createCsnBpLimitRequest,
                                 String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("createCsnBpLimit"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnBpId", csnBpId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), createCsnBpLimitRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 更新带宽包中两个地域间的地域带宽。
     *
     * @param csnBpId 带宽包的ID
     * @param updateCsnBpLimitRequest 更新地域带宽参数
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串
     */
    public void updateCsnBpLimit(String csnBpId,
                                 UpdateCsnBpLimitRequest updateCsnBpLimitRequest,
                                 String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("updateCsnBpLimit"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnBpId", csnBpId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), updateCsnBpLimitRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 删除带宽包中两个地域间的地域带宽。
     *
     * @param csnBpId 带宽包的ID
     * @param deleteCsnBpLimitRequest 删除地域带宽参数
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串
     */
    public void deleteCsnBpLimit(String csnBpId,
                                 DeleteCsnBpLimitRequest deleteCsnBpLimitRequest,
                                 String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("deleteCsnBpLimit"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnBpId", csnBpId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), deleteCsnBpLimitRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 查询云智能网的地域带宽列表。
     *
     * @param csnId 云智能网的ID
     * @return 云智能网的地域带宽列表
     */
    public ListCsnBpLimitByCsnIdResponse listCsnBpLimitByCsnId(String csnId) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("listCsnBpLimitByCsnId"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnId", csnId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListCsnBpLimitByCsnIdResponse.class);
    }

    /*    -------------------------- TGW相关 --------------------------    */

    /**
     * 查询云智能网TGW列表。  
     *
     * @param csnId 云智能网的ID
     * @param marker 批量获取列表的查询的起始位置，是一个由系统生成的字符串
     * @param maxKeys 每页包含的最大数量，最大数量不超过1000，缺省值为1000
     * @return ListTgwResponse
     */
    public ListTgwResponse listTgw(String csnId, String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("listTgw"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnId", csnId).get();
        apiInfo.getQueries().put("marker", marker);
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListTgwResponse.class);
    }

    /**
     * 更新TGW的名称、描述。
     *
     * @param csnId 云智能网的ID
     * @param tgwId TGW实例的ID
     * @param clientToken 幂等性Token，是一个长度不超过64位的ASCII字符串
     * @param updateTgwRequest
     */
    public void updateTgw(String csnId, String tgwId, UpdateTgwRequest updateTgwRequest,
                          String clientToken) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("updateTgw"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnId", csnId)
                .withPathParameter("tgwId", tgwId).get();
        apiInfo.getQueries().put("clientToken", clientToken);
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), updateTgwRequest);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 查询指定TGW的路由条目。
     *
     * @param csnId 云智能网的ID
     * @param tgwId TGW的ID
     * @param marker 批量获取列表的查询的起始位置，是一个由系统生成的字符串
     * @param maxKeys 每页包含的最大数量，最大数量不超过1000，缺省值为1000
     * @return ListTgwRuleResponse
     */
    public ListTgwRuleResponse listTgwRule(String csnId, String tgwId, String marker,
                                           Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(CSN_APIS.get("listTgwRule"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("csnId", csnId)
                .withPathParameter("tgwId", tgwId).get();
        apiInfo.getQueries().put("marker", marker);
        apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListTgwRuleResponse.class);
    }

}

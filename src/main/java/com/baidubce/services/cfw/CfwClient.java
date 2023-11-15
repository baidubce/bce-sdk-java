/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.common.ApiInfo;
import com.baidubce.common.BaseBceClient;
import com.baidubce.common.BaseBceResponse;
import com.baidubce.common.BceRegion;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.cfw.api.CfwApi;
import com.baidubce.services.cfw.model.BindCfwRequest;
import com.baidubce.services.cfw.model.CreateCfwRequest;
import com.baidubce.services.cfw.model.CreateCfwResponse;
import com.baidubce.services.cfw.model.CreateCfwRuleRequest;
import com.baidubce.services.cfw.model.DeleteCfwRuleRequest;
import com.baidubce.services.cfw.model.DisableCfwRequest;
import com.baidubce.services.cfw.model.EnableCfwRequest;
import com.baidubce.services.cfw.model.GetCfwResponse;
import com.baidubce.services.cfw.model.ListCfwRequest;
import com.baidubce.services.cfw.model.ListCfwResponse;
import com.baidubce.services.cfw.model.ListInstanceRequest;
import com.baidubce.services.cfw.model.UnbindCfwRequest;
import com.baidubce.services.cfw.model.UpdateCfwRequest;
import com.baidubce.services.cfw.model.ListInstanceResponse;
import com.baidubce.services.cfw.model.UpdateCfwRuleRequest;
import com.google.common.collect.ImmutableMap;

/**
 * Cfw
 */
public class CfwClient extends BaseBceClient {
    /**
     * endpoint
     */
    private static final Map<BceRegion, String> ENDPOINTS = ImmutableMap.<BceRegion, String>builder()
            .put(BceRegion.DEFAULT, "cfw.baidubce.com")
            .build();

    /**
     * API version
     */
    private static final String VERSION = "1";

    /**
     * Service name for extra config and handler.
     */
    private static final String SERVICE_ID = "Cfw";

    /**
     * Constructs a new client to invoke service methods on demo with region.
     */
    public CfwClient(String ak, String sk, BceRegion region) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(region));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public CfwClient(String ak, String sk) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(BceRegion.DEFAULT));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public CfwClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    /**
     * Api lists
     */
    private static final Map<String, ApiInfo> CFW_APIS = CfwApi.getApis();

    /**
     * 批量实例绑定CFW策略。 - 没有规则的CFW不能绑定到实例
     *
     * @param cfwId CFW的id
     * @param body
     */
    public void bindCfw(String cfwId, BindCfwRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("bindCfw"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION)
                .withPathParameter("cfwId", cfwId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 创建CFW策略。
     *
     * @param body
     * @return CreateCfwResponse
     */
    public CreateCfwResponse createCfw(CreateCfwRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("createCfw"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreateCfwResponse.class);
    }

    /**
     * 批量创建CFW中防护规则。
     * - 五元组(protocol/sourceAddress/destAddress/sourcePort/destPort) + 方向(direction)不能全部相同。
     * - 一次最多创建100条规则。
     *
     * @param cfwId CFW的id
     * @param body
     */
    public void createCfwRule(String cfwId, CreateCfwRuleRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("createCfwRule"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION)
                .withPathParameter("cfwId", cfwId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 删除指定CFW策略。 - CFW存在绑定关系时不允许删除
     *
     * @param cfwId CFW的id
     */
    public void deleteCfw(String cfwId) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("deleteCfw"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION)
                .withPathParameter("cfwId", cfwId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 批量删除指定CFW中某些规则。 - CFW已绑定到实例时，至少保留一条规则。
     *
     * @param cfwId CFW的id
     * @param body
     */
    public void deleteCfwRule(String cfwId, DeleteCfwRuleRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("deleteCfwRule"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION)
                .withPathParameter("cfwId", cfwId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 已绑定CFW的实例，使用该接口临时关闭CFW的防护功能。
     *
     * @param cfwId CFW的id
     * @param body
     */
    public void disableCfw(String cfwId, DisableCfwRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("disableCfw"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION)
                .withPathParameter("cfwId", cfwId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 已绑定CFW并且临时关闭了防护功能的实例，使用该接口恢复CFW的防护功能。
     *
     * @param cfwId CFW的id
     * @param body
     */
    public void enableCfw(String cfwId, EnableCfwRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("enableCfw"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION)
                .withPathParameter("cfwId", cfwId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 查询指定CFW策略的详情信息。
     *
     * @param cfwId CFW的id
     * @return GetCfwResponse
     */
    public GetCfwResponse getCfw(String cfwId) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("getCfw"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION)
                .withPathParameter("cfwId", cfwId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetCfwResponse.class);
    }

    /**
     * 查询CFW策略列表信息。
     *
     * @param listCfwRequest
     * @return ListCfwResponse
     */
    public ListCfwResponse listCfw(ListCfwRequest listCfwRequest) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("listCfw"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION).get();
        if (StringUtils.isNotBlank(listCfwRequest.getMarker())) {
            apiInfo.getQueries().put("marker", listCfwRequest.getMarker());
        }
        if (listCfwRequest.getMaxKeys() != null) {
            apiInfo.getQueries().put("maxKeys", String.valueOf(listCfwRequest.getMaxKeys()));
        }
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListCfwResponse.class);
    }

    /**
     * 查询防护边界实例的列表。
     *
     * @param listInstanceRequest
     * @return ListInstanceResponse
     */
    public ListInstanceResponse listInstance(ListInstanceRequest listInstanceRequest) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("listInstance"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION).get();
        if (StringUtils.isBlank(listInstanceRequest.getInstanceType())) {
            throw new BceClientException("InstanceType cannot be empty!");
        }
        apiInfo.getQueries().put("instanceType", listInstanceRequest.getInstanceType());
        if (StringUtils.isNotBlank(listInstanceRequest.getMarker())) {
            apiInfo.getQueries().put("marker", listInstanceRequest.getMarker());
        }
        if (StringUtils.isNotBlank(listInstanceRequest.getStatus())) {
            apiInfo.getQueries().put("status", listInstanceRequest.getStatus());
        }
        if (StringUtils.isNotBlank(listInstanceRequest.getRegion())) {
            apiInfo.getQueries().put("region", listInstanceRequest.getRegion());
        }
        if (listInstanceRequest.getMaxKeys() != null) {
            apiInfo.getQueries().put("maxKeys", String.valueOf(listInstanceRequest.getMaxKeys()));
        }
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, ListInstanceResponse.class);
    }

    /**
     * 实例批量解绑CFW。
     *
     * @param cfwId CFW的id
     * @param body
     */
    public void unbindCfw(String cfwId, UnbindCfwRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("unbindCfw"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION)
                .withPathParameter("cfwId", cfwId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 更新CFW策略的基本信息。
     *
     * @param cfwId CFW的id
     * @param body
     */
    public void updateCfw(String cfwId, UpdateCfwRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("updateCfw"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION)
                .withPathParameter("cfwId", cfwId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 修改指定CFW规则。 - 五元组(protocol/sourceAddress/destAddress/sourcePort/destPort) + 方向(direction)不能全部相同。
     *
     * @param cfwId CFW策略的id
     * @param cfwRuleId CFW规则的id
     * @param body
     */
    public void updateCfwRule(String cfwId, String cfwRuleId, UpdateCfwRuleRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFW_APIS.get("updateCfwRule"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("version", VERSION)
                .withPathParameter("cfwId", cfwId)
                .withPathParameter("cfwRuleId", cfwRuleId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

}

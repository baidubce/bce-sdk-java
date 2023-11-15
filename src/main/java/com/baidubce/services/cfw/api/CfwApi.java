/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cfw.api;

import java.util.HashMap;
import java.util.Map;

import com.baidubce.common.ApiInfo;
import com.baidubce.common.ApiPath;
import com.baidubce.http.HttpMethodName;

public class CfwApi {
    /**
     * Api list with api name
     */
    private static Map<String, ApiInfo> apis = new HashMap<String, ApiInfo>();

    public static Map<String, ApiInfo> getApis() {
        // 批量实例绑定CFW策略。 - 没有规则的CFW不能绑定到实例
        apis.put("bindCfw", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v[version]/cfw/[cfwId]"),
                new HashMap<String, String>(){
                    {
                        put("bind", "");
                    }
                },
                new HashMap<String, String>()));
        // 创建CFW策略。
        apis.put("createCfw", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v[version]/cfw"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 批量创建CFW中防护规则。 - 五元组(protocol/sourceAddress/destAddress/sourcePort/destPort) + 方向(direction)不能全部相同。 - 一次最多创建100条规则。
        apis.put("createCfwRule", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v[version]/cfw/[cfwId]/rule"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 删除指定CFW策略。 - CFW存在绑定关系时不允许删除
        apis.put("deleteCfw", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v[version]/cfw/[cfwId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 批量删除指定CFW中某些规则。 - CFW已绑定到实例时，至少保留一条规则。
        apis.put("deleteCfwRule", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v[version]/cfw/[cfwId]/delete/rule"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 已绑定CFW的实例，使用该接口临时关闭CFW的防护功能。
        apis.put("disableCfw", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v[version]/cfw/[cfwId]"),
                new HashMap<String, String>(){
                    {
                        put("off", "");
                    }
                },
                new HashMap<String, String>()));
        // 已绑定CFW并且临时关闭了防护功能的实例，使用该接口恢复CFW的防护功能。
        apis.put("enableCfw", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v[version]/cfw/[cfwId]"),
                new HashMap<String, String>(){
                    {
                        put("on", "");
                    }
                },
                new HashMap<String, String>()));
        // 查询指定CFW策略的详情信息。
        apis.put("getCfw", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v[version]/cfw/[cfwId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 查询CFW策略列表信息。
        apis.put("listCfw", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v[version]/cfw"),
                new HashMap<String, String>() {
                    {
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));
        // 查询防护边界实例的列表。
        apis.put("listInstance", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v[version]/cfw/instance"),
                new HashMap<String, String>() {
                    {
                        put("instanceType", null);
                        put("marker", null);
                        put("maxKeys", null);
                        put("status", null);
                        put("region", null);
                    }
                },
                new HashMap<String, String>()));
        // 实例批量解绑CFW。
        apis.put("unbindCfw", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v[version]/cfw/[cfwId]"),
                new HashMap<String, String>(){
                    {
                        put("unbind", "");
                    }
                },
                new HashMap<String, String>()));
        // 更新CFW策略的基本信息。
        apis.put("updateCfw", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v[version]/cfw/[cfwId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 修改指定CFW规则。 - 五元组(protocol/sourceAddress/destAddress/sourcePort/destPort) + 方向(direction)不能全部相同。
        apis.put("updateCfwRule", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v[version]/cfw/[cfwId]/rule/[cfwRuleId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        return apis;
    }
}

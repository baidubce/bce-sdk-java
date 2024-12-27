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
package com.baidubce.services.csn.api;

import java.util.HashMap;
import java.util.Map;

import com.baidubce.common.ApiInfo;
import com.baidubce.common.ApiPath;
import com.baidubce.http.HttpMethodName;

public class CsnApi {
    /**
     * Api list with api name
     */
    private static Map<String, ApiInfo> apis = new HashMap<String, ApiInfo>();

    public static Map<String, ApiInfo> getApis() {

        /*
         ---------------------- 云智能网相关 ---------------------
         */
        // 创建云智能网。
        apis.put("createCsn", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/csn"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 更新云智能网。  更新云智能网的名称和描述。
        apis.put("updateCsn", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/csn/[csnId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 删除云智能网。  已经加载了网络实例的云智能网不能直接删除，必须先卸载实例。
        apis.put("deleteCsn", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/csn/[csnId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 查询云智能网列表。
        apis.put("listCsn", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn"),
                new HashMap<String, String>() {
                    {
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));
        // 查询指定云智能网下加载的网络实例信息。
        apis.put("listInstance", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/[csnId]/instance"),
                new HashMap<String, String>() {
                    {
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));
        // 查询云智能网详情。
        apis.put("getCsn", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/[csnId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 从云智能网中移出指定的网络实例。
        apis.put("detachInstance", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/csn/[csnId]"),
                new HashMap<String, String>() {
                    {
                        put("detach", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 将网络实例加载进云智能网。
        apis.put("attachInstance", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/csn/[csnId]"),
                new HashMap<String, String>() {
                    {
                        put("attach", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));

        /*
         ---------------------- 路由管理相关 ---------------------
         */
        // 添加云智能网路由表的路由条目。
        apis.put("createRouteRule", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/csn/routeTable/[csnRtId]/rule"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 查询指定云智能网路由表的路由条目。
        apis.put("listRouteRule", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/routeTable/[csnRtId]/rule"),
                new HashMap<String, String>() {
                    {
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));
        // 删除云智能网路由表的指定路由条目。
        apis.put("deleteRouteRule", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/csn/routeTable/[csnRtId]/rule/[csnRtRuleId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 创建路由表的学习关系。
        apis.put("createPropagation", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/csn/routeTable/[csnRtId]/propagation"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 查询指定云智能网路由表的学习关系。
        apis.put("listPropagation", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/routeTable/[csnRtId]/propagation"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 删除云智能网路由表的学习关系。
        apis.put("deletePropagation", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/csn/routeTable/[csnRtId]/propagation/[attachId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 创建路由表的关联关系。
        apis.put("createAssociation", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/csn/routeTable/[csnRtId]/association"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 查询指定云智能网路由表的关联关系。
        apis.put("listAssociation", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/routeTable/[csnRtId]/association"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 删除云智能网路由表的关联关系。
        apis.put("deleteAssociation", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/csn/routeTable/[csnRtId]/association/[attachId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 查询云智能网的路由表列表。
        apis.put("listRouteTable", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/[csnId]/routeTable"),
                new HashMap<String, String>() {
                    {
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));

        /*
         ---------------------- 带宽包相关 ---------------------
         */
        // 查询云智能网带宽包列表。
        apis.put("listCsnBp", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/bp"),
                new HashMap<String, String>() {
                    {
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));
        // 查询指定云智能网带宽包详情。
        apis.put("getCsnBp", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/bp/[csnBpId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 创建云智能网共享带宽包。
        apis.put("createCsnBp", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/csn/bp"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 更新带宽包的名称信息。
        apis.put("updateCsnBp", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/csn/bp/[csnBpId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 删除带宽包。
        apis.put("deleteCsnBp", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/csn/bp/[csnBpId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 带宽包的带宽升降级。
        apis.put("resizeCsnBp", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/csn/bp/[csnBpId]"),
                new HashMap<String, String>() {
                    {
                        put("resize", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 带宽包解绑云智能网。
        apis.put("unbindCsnBp", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/csn/bp/[csnBpId]"),
                new HashMap<String, String>() {
                    {
                        put("unbind", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 带宽包绑定云智能网。
        apis.put("bindCsnBp", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/csn/bp/[csnBpId]"),
                new HashMap<String, String>() {
                    {
                        put("bind", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 带宽包询价。
        apis.put("csnBpPrice", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/csn/bp/price"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));

        /*
         ---------------------- 区域带宽相关 ---------------------
         */
        // 查询带宽包的地域带宽列表。
        apis.put("listCsnBpLimit", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/bp/[csnBpId]/limit"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 创建带宽包中两个地域间的地域带宽。
        apis.put("createCsnBpLimit", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/csn/bp/[csnBpId]/limit"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 更新带宽包中两个地域间的地域带宽。
        apis.put("updateCsnBpLimit", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/csn/bp/[csnBpId]/limit"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 删除带宽包中两个地域间的地域带宽。
        apis.put("deleteCsnBpLimit", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/csn/bp/[csnBpId]/limit/delete"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 查询云智能网的地域带宽列表。
        apis.put("listCsnBpLimitByCsnId", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/[csnId]/bp/limit"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));

        /*
         ---------------------- TGW相关 ---------------------
         */
        // 查询云智能网TGW列表。  
        apis.put("listTgw", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/[csnId]/tgw"),
                new HashMap<String, String>() {
                    {
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));
        // 更新TGW的名称、描述。
        apis.put("updateTgw", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/csn/[csnId]/tgw/[tgwId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 查询指定TGW的路由条目。
        apis.put("listTgwRule", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/csn/[csnId]/tgw/[tgwId]/rule"),
                new HashMap<String, String>() {
                    {
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));
        return apis;
    }
}

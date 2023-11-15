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
package com.baidubce.services.dns.api;

import java.util.HashMap;
import java.util.Map;

import com.baidubce.common.ApiInfo;
import com.baidubce.common.ApiPath;
import com.baidubce.http.HttpMethodName;

public class DnsApi {
    /**
     * Api list with api name
     */
    public static Map<String, ApiInfo> apis = new HashMap<String, ApiInfo>();

    public static Map<String, ApiInfo> getApis() {
        // 
        apis.put("addLineGroup", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/dns/customline"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("createPaidZone", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/dns/zone/order"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("createRecord", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/dns/zone/[zoneName]/record"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("createZone", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/dns/zone"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("deleteLineGroup", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/dns/customline/[lineId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("deleteRecord", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/dns/zone/[zoneName]/record/[recordId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("deleteZone", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/dns/zone/[zoneName]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("listLineGroup", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/dns/customline"),
                new HashMap<String, String>() {
                    {
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("listRecord", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/dns/zone/[zoneName]/record"),
                new HashMap<String, String>() {
                    {
                        put("rr", null);
                        put("id", null);
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("listZone", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/dns/zone"),
                new HashMap<String, String>() {
                    {
                        put("name", null);
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("renewZone", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/dns/zone/order/[name]"),
                new HashMap<String, String>() {
                    {
                        put("purchaseReserved", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("updateLineGroup", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/dns/customline/[lineId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("updateRecord", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/dns/zone/[zoneName]/record/[recordId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("updateRecordDisable", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/dns/zone/[zoneName]/record/[recordId]"),
                new HashMap<String, String>() {
                    {
                        put("disable", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("updateRecordEnable", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/dns/zone/[zoneName]/record/[recordId]"),
                new HashMap<String, String>() {
                    {
                        put("enable", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("upgradeZone", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/dns/zone/order"),
                new HashMap<String, String>() {
                    {
                        put("upgradeToDiscount", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        return apis;
    }
}

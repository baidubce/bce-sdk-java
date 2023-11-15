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
package com.baidubce.services.localdns.api;

import java.util.HashMap;
import java.util.Map;

import com.baidubce.common.ApiInfo;
import com.baidubce.common.ApiPath;
import com.baidubce.http.HttpMethodName;

public class LdApi {
    /**
     * Api list with api name
     */
    private static Map<String, ApiInfo> apis = new HashMap<String, ApiInfo>();

    public static Map<String, ApiInfo> getApis() {
        // 
        apis.put("addRecord", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/privatezone/[zoneId]/record"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("bindVpc", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/privatezone/[zoneId]"),
                new HashMap<String, String>() {
                    {
                        put("bind", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("createPrivateZone", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/privatezone"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("deletePrivateZone", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/privatezone/[zoneId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("deleteRecord", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/privatezone/record/[recordId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("disableRecord", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/privatezone/record/[recordId]"),
                new HashMap<String, String>() {
                    {
                        put("disable", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("enableRecord", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/privatezone/record/[recordId]"),
                new HashMap<String, String>() {
                    {
                        put("enable", null);
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("getPrivateZone", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/privatezone/[zoneId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 
        apis.put("listPrivateZone", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/privatezone"),
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
                new ApiPath("/v1/privatezone/[zoneId]/record"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 
        apis.put("unbindVpc", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/privatezone/[zoneId]"),
                new HashMap<String, String>() {
                    {
                        put("unbind", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("updateRecord", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/privatezone/record/[recordId]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        return apis;
    }
}

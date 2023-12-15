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
package com.baidubce.services.lbdc.api;

import java.util.HashMap;
import java.util.Map;

import com.baidubce.common.ApiInfo;
import com.baidubce.common.ApiPath;
import com.baidubce.http.HttpMethodName;

public class LbdcApi {
    /**
     * Api list with api name
     */
    private static Map<String, ApiInfo> apis = new HashMap<String, ApiInfo>();

    public static Map<String, ApiInfo> getApis() {
        // 
        apis.put("createLbdc", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/lbdc"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("getBoundBlBListOfLbdc", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/lbdc/[id]/blb"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 
        apis.put("getLbdc", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/lbdc/[id]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 
        apis.put("listLbdc", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/lbdc"),
                new HashMap<String, String>() {
                    {
                        put("id", null);
                        put("name", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("renewLbdc", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/lbdc/[id]"),
                new HashMap<String, String>() {
                    {
                        put("purchaseReserved", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("updateLbdc", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/lbdc/[id]"),
                new HashMap<String, String>() {
                    {
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("upgradeLbdc", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/lbdc/[id]"),
                new HashMap<String, String>() {
                    {
                        put("resize", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        return apis;
    }
}

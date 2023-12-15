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
package com.baidubce.services.userservice.api;

import java.util.HashMap;
import java.util.Map;

import com.baidubce.common.ApiInfo;
import com.baidubce.common.ApiPath;
import com.baidubce.http.HttpMethodName;

public class UserserviceApi {
    /**
     * Api list with api name
     */
    private static Map<String, ApiInfo> apis = new HashMap<String, ApiInfo>();

    public static Map<String, ApiInfo> getApis() {
        // 
        apis.put("addAuth", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/service/[service]"),
                new HashMap<String, String>() {
                    {
                        put("addAuth", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("bindInstance", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/service/[service]"),
                new HashMap<String, String>() {
                    {
                        put("bind", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("createUserService", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/service"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 
        apis.put("deleteUserService", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/service/[service]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 
        apis.put("editAuth", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/service/[service]"),
                new HashMap<String, String>() {
                    {
                        put("editAuth", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("getUserService", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/service/[service]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 
        apis.put("listUserService", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/service"),
                new HashMap<String, String>() {
                    {
                        put("marker", null);
                        put("maxKeys", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("removeAuth", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/service/[service]"),
                new HashMap<String, String>() {
                    {
                        put("removeAuth", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("unbindInstance", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/service/[service]"),
                new HashMap<String, String>() {
                    {
                        put("unbind", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        // 
        apis.put("updateUserService", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/service/[service]"),
                new HashMap<String, String>() {
                    {
                        put("modifyAttribute", "");
                        put("clientToken", null);
                    }
                },
                new HashMap<String, String>()));
        return apis;
    }
}

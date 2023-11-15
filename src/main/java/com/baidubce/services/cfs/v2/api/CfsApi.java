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
package com.baidubce.services.cfs.v2.api;

import java.util.HashMap;
import java.util.Map;

import com.baidubce.common.ApiInfo;
import com.baidubce.common.ApiPath;
import com.baidubce.http.HttpMethodName;

public class CfsApi {
    /**
     * Api list with api name
     */
    private static Map<String, ApiInfo> apis = new HashMap<String, ApiInfo>();

    public static Map<String, ApiInfo> getApis() {
        // 创建文件系统
        apis.put("createFS", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/cfs"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // * 给创建一个文件系统的挂载点，返回domain。 * 付费方式为后付费。
        apis.put("createMountTarget", new ApiInfo(
                HttpMethodName.valueOf("POST"),
                new ApiPath("/v1/cfs/[fsId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 释放指定的FileSystem的MountTarget
        apis.put("deleteMountTarget", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/cfs/[fsId]/[mountId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 查询用户账户下的所有FileSystem信息。 支持按fsId、userId，匹配规则支持部分包含。 返回结果是多重查询条件的交集 结果支持marker分页，分页大小默认为1000，可通过maxKeys参数指定
        apis.put("describeFs", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/cfs"),
                new HashMap<String, String>() {
                    {
                    }
                },
                new HashMap<String, String>()));
        // * 查询指定文件系统下的所有MountTarget信息。 * 返回结果是多重查询条件的交集 * 结果支持marker分页，分页大小默认为1000，可通过maxKeys参数指定
        apis.put("describeMountTarget", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/cfs/[fsId]"),
                new HashMap<String, String>() {
                    {
                    }
                },
                new HashMap<String, String>()));
        // 释放指定的FileSystem，被释放的FileSystem超过回收时间后会被永远删除，无法找回
        apis.put("dropFs", new ApiInfo(
                HttpMethodName.valueOf("DELETE"),
                new ApiPath("/v1/cfs/[fsId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 获取文件系统quota
        apis.put("getFsQuota", new ApiInfo(
                HttpMethodName.valueOf("GET"),
                new ApiPath("/v1/cfs/quota"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        // 更新一个filesystem的配置信息。
        apis.put("updateFs", new ApiInfo(
                HttpMethodName.valueOf("PUT"),
                new ApiPath("/v1/cfs/[fsId]"),
                new HashMap<String, String>(),
                new HashMap<String, String>()));
        return apis;
    }
}

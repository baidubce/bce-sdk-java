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
package com.baidubce.services.cfs.v2;

import java.util.Map;

import com.baidubce.BceClientConfiguration;
import com.baidubce.common.ApiInfo;
import com.baidubce.common.BaseBceClient;
import com.baidubce.common.BaseBceResponse;
import com.baidubce.common.BceRegion;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.cfs.v2.api.CfsApi;
import com.baidubce.services.cfs.v2.model.CreateFSRequest;
import com.baidubce.services.cfs.v2.model.CreateFSResponse;
import com.baidubce.services.cfs.v2.model.CreateMountTargetRequest;
import com.baidubce.services.cfs.v2.model.CreateMountTargetResponse;
import com.baidubce.services.cfs.v2.model.DescribeFsRequest;
import com.baidubce.services.cfs.v2.model.DescribeFsResponse;
import com.baidubce.services.cfs.v2.model.DescribeMountTargetRequest;
import com.baidubce.services.cfs.v2.model.DescribeMountTargetResponse;
import com.baidubce.services.cfs.v2.model.DropFsRequest;
import com.baidubce.services.cfs.v2.model.GetFsQuotaResponse;
import com.baidubce.services.cfs.v2.model.UpdateFsRequest;
import com.google.common.collect.ImmutableMap;
import com.baidubce.BceClientException;


/**
 * Cfs
 */
public class CfsClient extends BaseBceClient {

    private static final Map<BceRegion, String> ENDPOINTS = ImmutableMap.<BceRegion, String>builder()
            .put(BceRegion.DEFAULT, "http://cfs.bj.baidubce.com")
            .put(BceRegion.BJ, "http://cfs.bj.baidubce.com")
            .put(BceRegion.BD, "http://cfs.bd.baidubce.com")
            .put(BceRegion.SU, "http://cfs.su.baidubce.com")
            .put(BceRegion.GZ, "http://cfs.gz.baidubce.com")
            .put(BceRegion.FWH, "http://cfs.fwh.baidubce.com")
            .put(BceRegion.HKG, "http://cfs.hkg.baidubce.com")
            .build();

    /**
     * Service name for extra config and handler.
     */
    private static final String SERVICE_ID = "Cfs";

    /**
     * Constructs a new client to invoke service methods on demo with region.
     */
    public CfsClient(String ak, String sk, BceRegion region) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(region));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public CfsClient(String ak, String sk) {
        super(SERVICE_ID, ak, sk, ENDPOINTS.get(BceRegion.DEFAULT));
    }

    /**
     * Constructs a new client to invoke service methods on demo.
     */
    public CfsClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    /**
     * Api lists
     */
    private static final Map<String, ApiInfo> CFS_APIS = CfsApi.getApis();

    /**
     * 创建文件系统
     *
     * @param body 
     * @return CreateFSResponse
     */
    public CreateFSResponse createFS(CreateFSRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFS_APIS.get("createFS"));
        String apiPath = apiInfo.getPath().get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreateFSResponse.class);
    }

    /**
     * * 给创建一个文件系统的挂载点，返回domain。 * 付费方式为后付费。
     *
     * @param fsId 待创建MountTarget的FileSystem的ID
     * @param body 
     * @return CreateMountTargetResponse
     */
    public CreateMountTargetResponse createMountTarget(String fsId, CreateMountTargetRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFS_APIS.get("createMountTarget"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("fsId", fsId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, CreateMountTargetResponse.class);
    }

    /**
     * 释放指定的FileSystem的MountTarget
     *
     * @param fsId 待释放的target的FileSystem的ID
     * @param mountId 待释放的target的ID 
     */
    public void deleteMountTarget(String fsId, String mountId) {
        ApiInfo apiInfo = new ApiInfo(CFS_APIS.get("deleteMountTarget"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("fsId", fsId)
                .withPathParameter("mountId", mountId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 查询用户账户下的所有FileSystem信息。 支持按fsId、userId，匹配规则支持部分包含。 返回结果是多重查询条件的交集 结果支持marker分页，分页大小默认为1000，可通过maxKeys参数指定
     *
     * @param userId 要查询文件系统所属的用户ID
     * @param fsId 要查询的FileSystem ID
     * @param marker 批量获取列表的查询的起始位置，是一个由系统生成的字符串
     * @param maxKeys 每页包含的最大数量，最大数量不超过1000。缺省值为1000
     * @param body 
     * @return DescribeFsResponse
     */
    public DescribeFsResponse describeFs(DescribeFsRequest body, String userId, String fsId, String marker, 
            Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(CFS_APIS.get("describeFs"));
        String apiPath = apiInfo.getPath().get();

        if (userId.length() == 0 && fsId.length() == 0) {
            throw new BceClientException("Invalid request, userId or fsId should not be empty");
        }
        if (userId.length() != 0) {
            apiInfo.getQueries().put("userId", userId);
        }
        if (fsId.length() != 0 ) {
            apiInfo.getQueries().put("fsId", fsId);
        }
        if (marker.length() != 0) {
            apiInfo.getQueries().put("marker", marker);
        }
        if (maxKeys > 0) {
            apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        }
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);

        return invokeHttpClient(internalRequest, DescribeFsResponse.class);
    }

    /**
     * * 查询指定文件系统下的所有MountTarget信息。 * 返回结果是多重查询条件的交集 * 结果支持marker分页，分页大小默认为1000，可通过maxKeys参数指定
     *
     * @param fsId 
     * @param mountId 要查询的MountTarget的ID
     * @param marker 批量获取列表的查询的起始位置，是一个由系统生成的字符串
     * @param maxKeys 每页包含的最大数量，最大数量不超过1000。缺省值为1000
     * @param body 
     * @return DescribeMountTargetResponse
     */
    public DescribeMountTargetResponse describeMountTarget(String fsId, DescribeMountTargetRequest body,
            String mountId, String marker, Integer maxKeys) {
        ApiInfo apiInfo = new ApiInfo(CFS_APIS.get("describeMountTarget"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("fsId", fsId).get();
        if (mountId.length() != 0) {
            apiInfo.getQueries().put("mountId", mountId);
        }
        if (marker.length() != 0) {
            apiInfo.getQueries().put("marker", marker);
        }
        if (maxKeys > 0) {
            apiInfo.getQueries().put("maxKeys", String.valueOf(maxKeys));
        }
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        return invokeHttpClient(internalRequest, DescribeMountTargetResponse.class);
    }

    /**
     * 释放指定的FileSystem，被释放的FileSystem超过回收时间后会被永远删除，无法找回
     *
     * @param fsId 待释放的FileSystem的ID
     * @param body 
     */
    public void dropFs(String fsId, DropFsRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFS_APIS.get("dropFs"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("fsId", fsId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }

    /**
     * 获取文件系统quota
     *
     * @return GetFsQuotaResponse
     */
    public GetFsQuotaResponse getFsQuota() {
        ApiInfo apiInfo = new ApiInfo(CFS_APIS.get("getFsQuota"));
        String apiPath = apiInfo.getPath().get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), null);
        return invokeHttpClient(internalRequest, GetFsQuotaResponse.class);
    }

    /**
     * 更新一个filesystem的配置信息。
     *
     * @param fsId 待更新的FileSystem的ID
     * @param body 
     */
    public void updateFs(String fsId, UpdateFsRequest body) {
        ApiInfo apiInfo = new ApiInfo(CFS_APIS.get("updateFs"));
        String apiPath = apiInfo.getPath()
                .withPathParameter("fsId", fsId).get();
        InternalRequest internalRequest = createRequest(apiInfo.getMethod(), apiPath, apiInfo.getQueries(),
                apiInfo.getHeaders(), body);
        invokeHttpClient(internalRequest, BaseBceResponse.class);
    }
}

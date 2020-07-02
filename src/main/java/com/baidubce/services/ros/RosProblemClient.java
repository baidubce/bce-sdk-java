/*
 * Copyright 2019-2020 Baidu, Inc.
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
package com.baidubce.services.ros;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.ros.model.BaseResponse;
import com.baidubce.services.ros.model.problem.CreateMultiDepotProblemRequest;
import com.baidubce.services.ros.model.problem.CreateMultiSingleDepotProblemRequest;
import com.baidubce.services.ros.model.problem.CreateOptimizationRequest;
import com.baidubce.services.ros.model.problem.CreateOrderedRequest;
import com.baidubce.services.ros.model.problem.CreateProblemRequest;
import com.baidubce.services.ros.model.problem.GetRouteResponse;
import com.baidubce.services.ros.model.problem.GetSolutionResponse;

import com.baidubce.services.ros.model.problem.PartitionRequest;
import com.baidubce.services.ros.model.problem.PartitionResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Ros client.
 *
 * @author chenbo14
 * @date 2019/05/31
 */
@Slf4j
public class RosProblemClient extends BaseRosClient {
    private static final String PROBLEM_PATH_V1 = "/v1/scheduler/problem";
    private static final String PROBLEM_PATH_V2 = "/v2/scheduler/problem";
    private static final String PROBLEM_PATH_V3 = "/v3/scheduler/problem";
    private static final String MULTI_DEPOT_PROBLEM_PATH = "/v1/scheduler/multidepot";
    private static final String MULTI_SINGLE_DEPOT_PROBLEM_PATH = "/v1/scheduler/multidepot/single";

    public RosProblemClient() {
        super();
    }

    public RosProblemClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    public RosProblemClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    /**
     * Create Scheduler Task.
     *
     * @param request ProblemRequest
     *
     * @return Object of BaseResponse
     */
    public BaseResponse createSchedulerTask(CreateProblemRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, PROBLEM_PATH_V1);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    /**
     * Create Scheduler Task V3,support multi depots.
     *
     * @param request ProblemRequest
     *
     * @return Object of BaseResponse
     */
    public BaseResponse createSchedulerTaskV3(CreateProblemRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, PROBLEM_PATH_V3);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    /**
     * Query scheduler task solution.
     *
     * @param taskId taskId from createSchedulerTask response
     *
     * @return GetSolutionResponse
     */
    public GetSolutionResponse getSchedulerSolution(String taskId) {
        InternalRequest internalRequest = createRequest(null, HttpMethodName.GET, PROBLEM_PATH_V2 + "/" + taskId);
        return this.invokeHttpClient(internalRequest, GetSolutionResponse.class);
    }

    /**
     * Create Optimization Task.
     *
     * @param request CreateOptimizationRequest
     *
     * @return Object of BaseResponse
     */
    public BaseResponse createOptimizationTask(CreateOptimizationRequest request) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.POST, PROBLEM_PATH_V1 + "/optimization");
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    /**
     * Query optimization task solution.
     *
     * @param taskId taskId from createOptimizationTask response
     *
     * @return GetRouteResponse
     */
    public GetRouteResponse getOptimizationRoute(String taskId) {
        InternalRequest internalRequest = createRequest(null, HttpMethodName.GET,
                PROBLEM_PATH_V2 + "/optimization/" + taskId);
        return this.invokeHttpClient(internalRequest, GetRouteResponse.class);
    }

    /**
     * Create Ordered Task.
     *
     * @param request CreateOrderedRequest
     *
     * @return Object of BaseResponse
     */
    public BaseResponse createOrderedTask(CreateOrderedRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, PROBLEM_PATH_V1 + "/ordered");
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    /**
     * Query ordered task solution.
     *
     * @param taskId taskId from createOrderedTask response
     *
     * @return GetRouteResponse
     */
    public GetRouteResponse getOrderedRoute(String taskId) {
        InternalRequest internalRequest = createRequest(null, HttpMethodName.GET,
                PROBLEM_PATH_V2 + "/ordered/" + taskId);
        return this.invokeHttpClient(internalRequest, GetRouteResponse.class);
    }

    /**
     * Create MultiDepots Task.
     *
     * @param request CreateMultiDepotsProblemRequest
     *
     * @return Object of BaseResponse
     */
    public BaseResponse createMultiDepotsTask(CreateMultiDepotProblemRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, MULTI_DEPOT_PROBLEM_PATH);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    /**
     * Create MultiSingleDepot Task.
     *
     * @param request CreateMultiSingleDepotProblemRequest
     *
     * @return
     */
    public BaseResponse createMultiSingleDepotTask(CreateMultiSingleDepotProblemRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, MULTI_SINGLE_DEPOT_PROBLEM_PATH);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    /**
     * Create partition
     *
     * @param request PartitionRequest
     *
     * @return Object of PartitionResponse
     */
    public PartitionResponse partition(PartitionRequest request, String path) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, path);
        return this.invokeHttpClient(internalRequest, PartitionResponse.class);
    }
}

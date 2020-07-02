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
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.ros.model.BaseResponse;
import com.baidubce.services.ros.model.CurrentTaskResponse;
import com.baidubce.services.ros.model.DeleteMatrixResponse;
import com.baidubce.services.ros.model.FastOrderProblem;
import com.baidubce.services.ros.model.FastOrderSolutionResponse;
import com.baidubce.services.ros.model.FastOrderSolutionResponseV2;
import com.baidubce.services.ros.model.GetSchedulerResultResponse;
import com.baidubce.services.ros.model.GetSingleVehicleResultResponse;
import com.baidubce.services.ros.model.HighWayLocationResponse;
import com.baidubce.services.ros.model.MatrixResponse;
import com.baidubce.services.ros.model.OptimizationProblemRequest;
import com.baidubce.services.ros.model.OrderedProblemRequest;
import com.baidubce.services.ros.model.ProblemRequest;
import com.baidubce.services.ros.model.RosGeneralResponse;
import com.baidubce.services.ros.model.UpdateWeightResponse;
import com.baidubce.services.ros.model.matrix.MatrixCreateRequest;
import com.baidubce.services.ros.model.matrix.MatrixDeleteRequest;
import com.baidubce.services.ros.model.matrix.MatrixMixedUpdateRequest;
import com.baidubce.services.ros.model.matrix.MatrixPointRequest;
import com.baidubce.services.ros.model.matrix.MatrixUpdateRequest;
import com.baidubce.services.ros.model.matrix.WeightUpdateRequest;
import com.baidubce.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Ros client.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Slf4j
public class RosClient extends BaseRosClient {
    private static final String FAST_ORDER_PATH = "/v1/scheduler/fastorder";
    private static final String FAST_ORDER_PATH_V2 = "/v2/scheduler/fastorder";

    private static final String PROBLEM_PATH = "/v1/scheduler/problem";

    private static final String MATRIX_PATH = "/v1/matrix";

    public RosClient() {
        super();
    }

    public RosClient(BceClientConfiguration configuration) {
        super(configuration);
    }

    public RosClient(String accessKey, String secretKey) {
        super(accessKey, secretKey);
    }

    /**
     * Create fast order task.
     *
     * @param problem FastOrderProblem
     *
     * @return RosGeneralResponse
     */
    public RosGeneralResponse createFastOrderTask(FastOrderProblem problem) {
        InternalRequest internalRequest = createRequest(problem, HttpMethodName.POST, FAST_ORDER_PATH);
        log.info("FastOrderProblem is :{}", JsonUtils.toJsonString(internalRequest));
        return this.invokeHttpClient(internalRequest, RosGeneralResponse.class);
    }

    /**
     * Query fast order task solution.
     *
     * @param problemId problemId from createFastOrderTask response
     *
     * @return FastOrderSolutionResponse
     */
    public FastOrderSolutionResponse getFastOrderSolutionResponse(String problemId) {
        InternalRequest internalRequest = createRequest(null, HttpMethodName.GET, FAST_ORDER_PATH + "/" + problemId);
        log.info("FastOrderProblem query request is :{}", JsonUtils.toJsonString(internalRequest));
        return this.invokeHttpClient(internalRequest, FastOrderSolutionResponse.class);
    }

    /**
     * Query fast order task solution.
     *
     * @param problemId problemId from createFastOrderTask response
     *
     * @return FastOrderSolutionResponse
     */
    public FastOrderSolutionResponseV2 getFastOrderSolutionResponseV2(String problemId) {
        InternalRequest internalRequest = createRequest(null, HttpMethodName.GET, FAST_ORDER_PATH_V2 + "/" + problemId);
        log.info("FastOrderProblem query request is :{}", JsonUtils.toJsonString(internalRequest));
        return this.invokeHttpClient(internalRequest, FastOrderSolutionResponseV2.class);
    }


    /**
     * Create Scheduler Task.
     *
     * @param request ProblemRequest
     *
     * @return Object of BaseResponse
     */
    public BaseResponse createProblem(ProblemRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, PROBLEM_PATH);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    /**
     * Query scheduler task solution.
     *
     * @param problemId problemId from createProblem response
     *
     * @return GetSchedulerResultResponse
     */
    public GetSchedulerResultResponse getSchedulerTaskResult(String problemId) {
        InternalRequest internalRequest = createRequest(null, HttpMethodName.GET, PROBLEM_PATH + "/" + problemId);
        return this.invokeHttpClient(internalRequest, GetSchedulerResultResponse.class);
    }

    /**
     * Create Optimization Task.
     *
     * @param request OptimizationProblemRequest
     *
     * @return Object of BaseResponse
     */
    public BaseResponse createOptimizationTask(OptimizationProblemRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, PROBLEM_PATH + "/optimization");
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    /**
     * Query optimization task solution.
     *
     * @param optimizationProblemId problemId from createOptimizationTask response
     *
     * @return GetSingleVehicleResultResponse
     */
    public GetSingleVehicleResultResponse getOptimizationTaskResult(String optimizationProblemId) {
        InternalRequest internalRequest = createRequest(null, HttpMethodName.GET,
                PROBLEM_PATH + "/optimization/" + optimizationProblemId);
        return this.invokeHttpClient(internalRequest, GetSingleVehicleResultResponse.class);
    }

    /**
     * Create Ordered Task.
     *
     * @param request OrderedProblemRequest
     *
     * @return Object of BaseResponse
     */
    public BaseResponse createOrderedTask(OrderedProblemRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, PROBLEM_PATH + "/ordered");
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    /**
     * Query ordered task solution.
     *
     * @param orderedProblemId problemId from createOrderedTask response
     *
     * @return GetSingleVehicleResultResponse
     */
    public GetSingleVehicleResultResponse getOrderedTaskResult(String orderedProblemId) {
        InternalRequest internalRequest = createRequest(null, HttpMethodName.GET,
                PROBLEM_PATH + "/ordered/" + orderedProblemId);
        return this.invokeHttpClient(internalRequest, GetSingleVehicleResultResponse.class);
    }

    /**
     * create matrix
     *
     * @param request
     *
     * @return
     */
    public MatrixResponse createMatrix(MatrixCreateRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, MATRIX_PATH);
        return invokeHttpClient(internalRequest, MatrixResponse.class);
    }

    /**
     * query matrix status
     *
     * @param matrixId matrix id
     * @param commitId matrix version
     *
     * @return
     */
    public MatrixResponse getMatrixStatus(String matrixId, String commitId) {
        InternalRequest internalRequest =
                createRequest(null, HttpMethodName.GET, MATRIX_PATH + "/" + matrixId + "/" + commitId);
        return invokeHttpClient(internalRequest, MatrixResponse.class);
    }

    /**
     * update A->B
     *
     * @param request
     * @param matrixId
     *
     * @return
     */
    public MatrixResponse updatePoint(MatrixPointRequest request, String matrixId) {
        InternalRequest internalRequest =
                createRequest(request, HttpMethodName.PUT, MATRIX_PATH + "/" + matrixId + "/point");
        return invokeHttpClient(internalRequest, MatrixResponse.class);
    }

    /**
     * update matrix : add Location Update location and delete location
     *
     * @param request
     * @param matrixId
     *
     * @return
     */
    public MatrixResponse updateMatrix(MatrixUpdateRequest request, String matrixId) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, MATRIX_PATH + "/" + matrixId);
        return invokeHttpClient(internalRequest, MatrixResponse.class);
    }

    public MatrixResponse mixedUpdateMatrix(MatrixMixedUpdateRequest request, String matrixId) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, MATRIX_PATH + "/mixed");
        internalRequest.addParameter("matrixId", matrixId);
        return invokeHttpClient(internalRequest, MatrixResponse.class);
    }

    /**
     * delete matrix
     *
     * @param request
     */
    public void deleteMatrix(MatrixDeleteRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, MATRIX_PATH);
        internalRequest.addParameter("delete", "");
        invokeHttpClient(internalRequest, DeleteMatrixResponse.class);
    }

    /**
     * query matrix task status
     * @return
     */
    public CurrentTaskResponse getTaskExecuteStatus() {
        InternalRequest internalRequest =
                createRequest(null, HttpMethodName.GET, MATRIX_PATH + "/current/task");
        return invokeHttpClient(internalRequest, CurrentTaskResponse.class);
    }

    /**
     * update task weight
     * @param request
     */
    public void updateTaskWeight(WeightUpdateRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, MATRIX_PATH + "/weight");
        invokeHttpClient(internalRequest, UpdateWeightResponse.class);
    }

    /**
     * get the hightWay location list
     * @param matrixId
     * @return
     */
    public HighWayLocationResponse getMatrixHighWayLocation(String matrixId) {
        InternalRequest internalRequest =
                createRequest(null, HttpMethodName.GET, MATRIX_PATH + "/highway/" + matrixId);
        return invokeHttpClient(internalRequest, HighWayLocationResponse.class);
    }

    public BaseResponse createTask(String path, AbstractBceRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, path);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }
}

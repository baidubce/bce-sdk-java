/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ros;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.ros.model.FastOrderProblem;
import com.baidubce.services.ros.model.FastOrderSolutionResponse;
import com.baidubce.services.ros.model.RosGeneralResponse;
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

}

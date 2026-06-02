package com.baidubce.services.aihc.modules.model;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.aihc.common.AihcConstants;
import com.baidubce.services.aihc.core.AihcClientCore;
import com.baidubce.services.aihc.model.model.DescribeModelsRequest;
import com.baidubce.services.aihc.model.model.DescribeModelsResponse;
import com.baidubce.services.aihc.model.model.DescribeModelRequest;
import com.baidubce.services.aihc.model.model.DescribeModelResponse;

/**
 * 模型管理模块实现类
 */
public class ModelModuleImpl extends AihcClientCore implements ModelModule {

    /**
     * 构造函数
     */
    public ModelModuleImpl() {
        super();
    }

    /**
     * 构造函数
     *
     * @param clientConfiguration BCE客户端配置
     */
    public ModelModuleImpl(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration);
    }

    @Override
    public DescribeModelsResponse describeModels(DescribeModelsRequest request) {
        InternalRequest internalRequest = createAihcRequest(request, HttpMethodName.GET, AihcConstants.ModelActions.DESCRIBE_MODELS);

        // Add query parameters according to API documentation
        if (request.getKeyword() != null) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (request.getModelType() != null) {
            internalRequest.addParameter("modelType", request.getModelType());
        }
        if (request.getFramework() != null) {
            internalRequest.addParameter("framework", request.getFramework());
        }
        if (request.getStatus() != null) {
            internalRequest.addParameter("status", request.getStatus());
        }
        if (request.getPageNumber() != null && request.getPageNumber() > 0) {
            internalRequest.addParameter("pageNumber", String.valueOf(request.getPageNumber()));
        }
        if (request.getPageSize() != null && request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }

        return invokeHttpClient(internalRequest, DescribeModelsResponse.class);
    }

    @Override
    public DescribeModelResponse describeModel(DescribeModelRequest request) {
        InternalRequest internalRequest = createAihcRequest(request, HttpMethodName.GET, AihcConstants.ModelActions.DESCRIBE_MODEL);

        // Add query parameters according to API documentation
        if (request.getModelId() != null) {
            internalRequest.addParameter("modelId", request.getModelId());
        }

        return invokeHttpClient(internalRequest, DescribeModelResponse.class);
    }
} 
package com.baidubce.services.aihc.modules.model;

import com.baidubce.services.aihc.model.model.DescribeModelsRequest;
import com.baidubce.services.aihc.model.model.DescribeModelsResponse;
import com.baidubce.services.aihc.model.model.DescribeModelRequest;
import com.baidubce.services.aihc.model.model.DescribeModelResponse;

/**
 * 模型管理模块接口
 * 定义所有模型相关的API方法
 */
public interface ModelModule {

    /**
     * 获取模型列表，支持分页查询
     * 
     * @param request 获取模型列表的请求
     * @return 模型列表信息
     */
    DescribeModelsResponse describeModels(DescribeModelsRequest request);

    /**
     * 获取模型详情
     * 
     * @param request 获取模型详情的请求
     * @return 模型详情信息
     */
    DescribeModelResponse describeModel(DescribeModelRequest request);

    // 预留其他模型相关方法的接口
    // 如：createModel, deleteModel, modifyModel 等
} 
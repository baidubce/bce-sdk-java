package com.baidubce.services.aihc.model.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * 获取模型详情的请求
 */
public class DescribeModelRequest extends AbstractBceRequest {

    /**
     * 模型ID
     */
    private String modelId;

    /**
     * 获取模型ID
     * 
     * @return 模型ID
     */
    public String getModelId() {
        return modelId;
    }

    /**
     * 设置模型ID
     * 
     * @param modelId 模型ID
     */
    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    /**
     * 设置模型ID并返回当前对象
     * 
     * @param modelId 模型ID
     * @return 当前对象
     */
    public DescribeModelRequest withModelId(String modelId) {
        this.modelId = modelId;
        return this;
    }

    @Override
    public DescribeModelRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
} 
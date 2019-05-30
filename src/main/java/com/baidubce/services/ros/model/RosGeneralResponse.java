/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ros.model;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

/**
 * Ros general response.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Data
public class RosGeneralResponse extends AbstractBceResponse {
    private String id;
    private String status;
    private String errorCode;
    private String errorMessage;
}

/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ros.model;


import lombok.Data;

/**
 * Fast order problem.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Data
public class FastOrderSolutionResponse extends RosGeneralResponse {
    private FastOrderSolution solution;
}

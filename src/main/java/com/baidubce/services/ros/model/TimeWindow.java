/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ros.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Time window, unit: minute.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeWindow {
    private Integer startTime;
    private Integer endTime;
}

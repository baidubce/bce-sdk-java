/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ros.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fast order service job info.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastOrderServiceJob {
    private String serviceJobId;
    private LocationPoint location;
    private Integer serviceStayDuration;
    private Capacity demand;
    private List<TimeWindow> serviceTimeWindows;
}

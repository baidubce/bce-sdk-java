package com.baidubce.services.bcm.model.custom;

import lombok.Data;

import java.util.List;

/**
 * 自定义空间中的指标
 *
 * @Author: wanglu51
 * @Date: 2023/12/7 10:59
 */
@Data
public class NamespaceMetric {
    private Long id;
    private String userId;
    private String namespace;
    private String metricName;
    private String metricAlias;
    private String unit;
    private Integer cycle;
    private List<NamespaceMetricDimension> dimensions;
}

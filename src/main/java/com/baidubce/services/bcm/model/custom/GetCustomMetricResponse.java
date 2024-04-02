package com.baidubce.services.bcm.model.custom;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

/**
 * 获取某个自定义空间的指标响应
 *
 * @Author: wanglu51
 * @Date: 2023/12/7 10:59
 */
@Data
public class GetCustomMetricResponse extends AbstractBceResponse {
    private Long id;
    private String userId;
    private String namespace;
    private String metricName;
    private String metricAlias;
    private String unit;
    private Integer cycle;
    private List<NamespaceMetricDimension> dimensions;
}

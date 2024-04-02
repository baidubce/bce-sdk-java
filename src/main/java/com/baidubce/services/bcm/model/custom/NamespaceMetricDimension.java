package com.baidubce.services.bcm.model.custom;

import lombok.Data;

/**
 * 自定义指标维度
 *
 * @Author: wanglu51
 * @Date: 2023/12/7 11:20
 */
@Data
public class NamespaceMetricDimension {
    private String name;
    private String alias;
    private Integer order;
}

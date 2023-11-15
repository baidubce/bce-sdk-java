package com.baidubce.services.bcm.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * statistic value object
 *
 * @Author: wanglu51
 * @Date: 2023/6/12 10:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatisticValue {
    private double maximum;
    private double minimum;
    private double average;
    private Integer sampleCount;
    private Double sum;
}
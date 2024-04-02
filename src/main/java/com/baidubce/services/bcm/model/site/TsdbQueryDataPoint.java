package com.baidubce.services.bcm.model.site;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author panzhiwei01@baidu.com
 * @Date 2020/2/25 6:42 下午
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TsdbQueryDataPoint {
    private String timestamp;
    private Double average;
    private Double sum;
    private Double maximum;
    private Double minimum;
    private Integer sampleCount;
    private Object value;
}

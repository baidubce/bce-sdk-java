package com.baidubce.services.bcm.model.application;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gongjiaming
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogExtractResult {
    private String extractFieldName;
    private String extractFieldValue;
    private String dimensionMapTable;
    private int metricEnable;
}
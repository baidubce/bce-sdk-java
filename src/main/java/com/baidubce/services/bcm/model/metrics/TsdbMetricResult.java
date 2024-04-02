package com.baidubce.services.bcm.model.metrics;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TsdbMetricResult<T> extends AbstractBceResponse {
    private String requestId;
    private String code;
    private String message;
    private T result;
}

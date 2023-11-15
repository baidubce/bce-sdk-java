package com.baidubce.services.bcm.model;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * push metric data response
 *
 * @Author: wanglu51
 * @Date: 2023/6/12 10:24
 */
@Data
public class PushMetricDataResponse extends AbstractBceResponse {
    private String requestId;
    private String message;
    private Boolean success;
}

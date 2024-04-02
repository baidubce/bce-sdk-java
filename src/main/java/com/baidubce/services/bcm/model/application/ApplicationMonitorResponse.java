package com.baidubce.services.bcm.model.application;

import com.baidubce.model.AbstractBceResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gongjiaming
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationMonitorResponse extends AbstractBceResponse {
    private String requestId;
    private String code;
    private String message;
}

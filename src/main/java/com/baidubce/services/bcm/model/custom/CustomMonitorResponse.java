package com.baidubce.services.bcm.model.custom;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * 自定义监控返回结构
 *
 * @Author: wanglu51
 * @Date: 2023/12/7 10:12
 */
@Data
public class CustomMonitorResponse extends AbstractBceResponse {
    private String requestId;
    private String code;
    private String message;
}

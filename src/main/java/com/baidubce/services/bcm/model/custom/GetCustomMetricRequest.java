package com.baidubce.services.bcm.model.custom;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取某个自定义空间指标请求
 *
 * @Author: wanglu51
 * @Date: 2023/12/6 19:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomMetricRequest extends AbstractBceRequest {
    private String userId;
    private String namespace;
    private String metricName;

    @Override
    public GetCustomMetricRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

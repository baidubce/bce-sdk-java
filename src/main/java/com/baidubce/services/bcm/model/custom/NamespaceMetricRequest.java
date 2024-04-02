package com.baidubce.services.bcm.model.custom;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 自定义空间中的指标
 *
 * @Author: wanglu51
 * @Date: 2023/12/7 10:59
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NamespaceMetricRequest extends AbstractBceRequest {
    private Long id;
    private String userId;
    private String namespace;
    private String metricName;
    private String metricAlias;
    private String unit;
    private Integer cycle;
    private List<NamespaceMetricDimension> dimensions;

    @Override
    public NamespaceMetricRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

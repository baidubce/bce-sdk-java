package com.baidubce.services.bcm.model.site;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * create by pangyangyang on 2023/12/13
 */
@Data
public class SiteMetricDataQueryRequest extends AbstractBceRequest {

    private String userId;
    private String taskId;
    private String metricName;
    private String[] statistics;
    private String startTime;
    private String endTime;
    private int cycle;
    private String dimensions;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

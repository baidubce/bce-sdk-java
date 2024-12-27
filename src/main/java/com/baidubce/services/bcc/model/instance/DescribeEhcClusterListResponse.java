package com.baidubce.services.bcc.model.instance;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

import java.util.List;

@Data
public class DescribeEhcClusterListResponse extends AbstractBceResponse {
    /**
     * List of instance info
     */
    private List<EhcCluster> ehcClusters;

}

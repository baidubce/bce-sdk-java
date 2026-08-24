package com.baidubce.services.bes.v3.model.cluster;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class ListClustersResponse extends AbstractBceResponse {
    private List<ClusterSummary> clusters;
    private Integer pageNo;
    private Integer pageSize;
    private Integer totalCount;

}

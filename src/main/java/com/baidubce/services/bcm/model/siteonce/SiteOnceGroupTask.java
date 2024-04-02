package com.baidubce.services.bcm.model.siteonce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteOnceGroupTask {
    private int totalNum;
    private int sumSampleNum;
    private int pageNo;
    private int pageSize;
    private String order;
    private String orderBy;
    private String filterArea;
    private String filterIsp;
    private SiteOnceProtocol protocolType;
    private String url;
    private String taskType;
    private String groupId;
    private List<String> metricOrder;
    private Set<String> allAreas;
    private SiteOnceOverview overviewData;
    private List<SiteOnceDetail> detailData;
}

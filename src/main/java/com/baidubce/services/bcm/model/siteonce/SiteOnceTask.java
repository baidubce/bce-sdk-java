package com.baidubce.services.bcm.model.siteonce;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SiteOnceTask {
    private int totalNum;
    private int pageNo;
    private int pageSize;
    private String order;
    private String orderBy;
    private String filterArea;
    private String filterIsp;
    private String status;
    private SiteOnceProtocol protocolType;
    private String url;
    private String taskType;
    private int agentNum;
    private Date monitorTime;
    private Date createTime;
    private String siteId;
    private String jobId;
    private String groupId;
    private String userId;
    private Set<String> allAreas;
    private List<String> metricOrder;
    private SiteOnceOverview overviewData;
    private List<SiteOnceDetail> detailData;
    private SiteOnceRequest taskConfig;
}

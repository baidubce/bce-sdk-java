package com.baidubce.services.bcm.model.siteonce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteOnceRecord {
    private String siteId;
    private String groupId;
    private String userId;
    private String taskType;
    private String ipType;
    private SiteOnceProtocol protocolType;
    private String url;
    private int sumSampleNum;
    private int agentNum;
    private BigDecimal success;
    private Date monitorTime;
    private Date createTime;
    private String status;
}

package com.baidubce.services.bcm.model.siteonce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteOnceDetail {
    private long id;
    private SiteAgentRegion region;
    private String agentProv;
    private String agentIsp;
    private String clientIp;
    private String clientCity;
    private String remoteAddr;
    private String remoteArea;
    private String remoteCity;
    private String remoteCounty;
    private List<String> analysisResult;
    private String ipProtocol;
    private Map<String, Double> metrics;
    private BigDecimal success;
    private Date monitorTime;
    private String status;
}

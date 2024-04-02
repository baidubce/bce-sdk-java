package com.baidubce.services.bcm.model.siteonce;

import lombok.Data;


/**
 * create by pangyangyang on 2021/02/22
 */
@Data
public class SiteAgent {

    private String agentId;
    private String agentName;
    private Integer status;
    private Integer ipv6Status;
    private SiteAgentRegion region;

}

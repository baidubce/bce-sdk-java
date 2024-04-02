package com.baidubce.services.bcm.model.site;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * create by pangyangyang on 2023/12/14
 */
@Data
public class SiteViewResponse extends AbstractBceResponse {

    private String id;
    private String name;
    private String availability;
    private Double responseTime;
}


package com.baidubce.services.bcm.model.site;

import com.baidubce.model.AbstractBceResponse;
import lombok.Data;

/**
 * create by pangyangyang on 2021/06/17
 */
@Data
public class SiteInfoResponse extends AbstractBceResponse {

    private String name;
    private String address;
    private String method;
    private String siteId;
}

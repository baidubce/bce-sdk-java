package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Response of Obtain new purchase price
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSupportHotSwappingResponse extends AbstractBceResponse {
    private Integer supportHotUpgrade;

    public Integer getSupportHotUpgrade() {
        return supportHotUpgrade;
    }
    public void setSupportHotUpgrade(Integer supportHotUpgrade) {
        this.supportHotUpgrade = supportHotUpgrade;
    }


}

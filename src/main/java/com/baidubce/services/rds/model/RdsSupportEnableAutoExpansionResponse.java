package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Response of Does the instance support enabling automatic scaling
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsSupportEnableAutoExpansionResponse extends AbstractBceResponse {

    Integer supportEnableDiskAutoResize;

    public Integer getSupportEnableDiskAutoResize() {
        return supportEnableDiskAutoResize;
    }
    public void setSupportEnableDiskAutoResize(Integer supportEnableDiskAutoResize) {
        this.supportEnableDiskAutoResize = supportEnableDiskAutoResize;
    }
}

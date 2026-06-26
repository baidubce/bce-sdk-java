package com.baidubce.services.scs.model.instance;

import com.baidubce.model.AbstractBceResponse;

/**
 * Response of scs auto scaling config.
 */
public class ScsAutoScalingConfigResponse extends AbstractBceResponse {

    private ScsMemAutoScalingConfig memSpec;

    public ScsMemAutoScalingConfig getMemSpec() {
        return memSpec;
    }

    public void setMemSpec(ScsMemAutoScalingConfig memSpec) {
        this.memSpec = memSpec;
    }
}

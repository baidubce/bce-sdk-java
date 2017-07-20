package com.baidubce.services.iotalarm.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yuanyoujun on 2017/6/20.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UuidResponse extends AbstractBceResponse {
    private String result;
    private String uuid;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

package com.baidubce.services.bacnet.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by yuanyoujun on 2017/10/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdResponse extends AbstractBceResponse {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

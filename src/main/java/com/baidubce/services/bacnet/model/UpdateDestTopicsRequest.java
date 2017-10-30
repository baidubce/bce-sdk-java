package com.baidubce.services.bacnet.model;

import com.baidubce.model.GenericAccountRequest;

/**
 * Created by yuanyoujun on 2017/10/19.
 */
public class UpdateDestTopicsRequest extends GenericAccountRequest {
    private String destTopics;

    public String getDestTopics() {
        return destTopics;
    }

    public void setDestTopics(String destTopics) {
        this.destTopics = destTopics;
    }
}

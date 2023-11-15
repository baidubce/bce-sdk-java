package com.baidubce.services.scs.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * The response of instance create
 */
public class ScsCreateResponse extends AbstractBceResponse {

    private List<String> instanceIds = new ArrayList<String>();

    public List<String> getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }
}

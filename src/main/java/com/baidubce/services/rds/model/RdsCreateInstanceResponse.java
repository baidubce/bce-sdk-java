package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * The response of create rds instance
 */
public class RdsCreateInstanceResponse extends AbstractBceResponse {

    private List<String> instanceIds;

    public List<String> getInstanceIds() {
        return instanceIds;
    }

    public void setInstanceIds(List<String> instanceIds) {
        this.instanceIds = instanceIds;
    }
}

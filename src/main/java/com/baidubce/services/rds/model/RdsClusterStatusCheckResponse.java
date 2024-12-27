package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * The Response of Cluster status check
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RdsClusterStatusCheckResponse extends AbstractBceResponse {
    private String clusterStatus;

    public String getClusterStatus() {
        return clusterStatus;
    }
    public void setClusterStatus(String clusterStatus) {
        this.clusterStatus = clusterStatus;
    }

    private Map<String, String> checkList;
    public Map<String, String> getCheckList() {
        return checkList;
    }
    public void setCheckList(Map<String, String> checkList) {
        this.checkList = checkList;
    }
}

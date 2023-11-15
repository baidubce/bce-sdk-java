package com.baidubce.services.rds.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * The response of release rds instance
 */
public class RdsReleaseInstanceResponse extends AbstractBceResponse {

    private Boolean success;
    private String message;
    private List<String> releaseFailedInstanceIds = new ArrayList<String>();

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getReleaseFailedInstanceIds() {
        return releaseFailedInstanceIds;
    }

    public void setReleaseFailedInstanceIds(List<String> releaseFailedInstanceIds) {
        this.releaseFailedInstanceIds = releaseFailedInstanceIds;
    }
}

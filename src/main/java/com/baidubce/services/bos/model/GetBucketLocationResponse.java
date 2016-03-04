package com.baidubce.services.bos.model;

/**
 * Get Bucket Location Response
 */

public class GetBucketLocationResponse extends BosResponse {
    private String locationConstraint = "";

    public String getLocationConstraint() {
        return this.locationConstraint;
    }

    public void setLocationConstraint(String locationConstraint) {
        this.locationConstraint = locationConstraint;
    }
}

package com.baidubce.services.iothisk.model;

/**
 * Represent the response of batch create client certs.
 */
public class BatchCreateClientCertResponse extends IotPkiManageResponse {

    /**
     * Job ID of the create batch.
     */
    private String jobId;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
}

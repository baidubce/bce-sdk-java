package com.baidubce.services.media.model;

import com.baidubce.model.AbstractBceResponse;

public class CreateTranscodingJobResponse extends AbstractBceResponse {
    private String jobId = null;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateTranscodingJobResponse {\n");
        sb.append("    jobId: ").append(jobId).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

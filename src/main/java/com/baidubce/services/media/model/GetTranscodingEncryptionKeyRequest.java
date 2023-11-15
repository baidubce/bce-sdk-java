package com.baidubce.services.media.model;

import static com.baidubce.util.Validate.checkStringNotEmpty;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GetTranscodingEncryptionKeyRequest extends AbstractBceRequest {

    private String jobId = null;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        checkStringNotEmpty(jobId, "The parameter jobId should NOT be null or empty string.");
        this.jobId = jobId;
    }

    public GetTranscodingEncryptionKeyRequest withJobId(String jobId) {
        checkStringNotEmpty(jobId, "The parameter jobId should NOT be null or empty string.");
        this.jobId = jobId;
        return this;
    }

    public GetTranscodingEncryptionKeyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetTranscodingEncryptionKeyRequest {\n");
        sb.append("    JobId: ").append(jobId).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}

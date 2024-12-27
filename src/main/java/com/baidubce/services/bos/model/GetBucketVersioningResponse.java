package com.baidubce.services.bos.model;

public class GetBucketVersioningResponse extends BosResponse {
    private String status;

    @Override
    public String toString() {
        return "GetBucketVersioningResponse{" +
                "status=" + status +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

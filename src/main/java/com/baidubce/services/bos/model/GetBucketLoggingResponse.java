package com.baidubce.services.bos.model;

/**
 * Get Bucket Logging Response
 */

public class GetBucketLoggingResponse extends BosResponse {

    /**
     * status of GetBucketLoggingResponse.
     */
    private String status = "";

    /**
     * targetBucket of GetBucketLoggingResponse.
     */
    private String targetBucket = "";

    /**
     * targetPrefix of GetBucketLoggingResponse.
     */
    private String targetPrefix = "";

    /**
     * Gets status of GetBucketLoggingResponse.
     * @return status of GetBucketLoggingResponse.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status of GetBucketLoggingResponse.
     * @param status The status of GetBucketLoggingResponse.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets targetBucket of GetBucketLoggingResponse.
     * @return targetBucket of GetBucketLoggingResponse.
     */
    public String getTargetBucket() {
        return targetBucket;
    }

    /**
     * Sets targetBucket of GetBucketLoggingResponse.
     * @param targetBucket The targetBucket of GetBucketLoggingResponse.
     */
    public void setTargetBucket(String targetBucket) {
        this.targetBucket = targetBucket;
    }

    /**
     * Gets targetPrefix of GetBucketLoggingResponse.
     * @return targetPrefix of GetBucketLoggingResponse.
     */
    public String getTargetPrefix() {
        return targetPrefix;
    }

    /**
     * Sets targetPrefix of GetBucketLoggingResponse.
     * @param targetPrefix The targetPrefix of GetBucketLoggingResponse.
     */
    public void setTargetPrefix(String targetPrefix) {
        this.targetPrefix = targetPrefix;
    }

    @Override
    public String toString() {
        return "GetBucketLoggingResponse{"
                + "status='" + status + '\''
                + ", targetBucket='" + targetBucket + '\''
                + ", targetPrefix='" + targetPrefix + '\''
                + '}';
    }
}

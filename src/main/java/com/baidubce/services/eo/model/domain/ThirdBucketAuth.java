package com.baidubce.services.eo.model.domain;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The object storage authentication configuration of an origin pool origin.
 *
 */
public class ThirdBucketAuth extends JsonObject {
    /**
     * The auth type. Valid values are "aws_v2", "aws_v4", "bos".
     */
    private String authType;

    /**
     * Whether to enable private access auth. Valid values are true and false.
     */
    private Boolean enabled;

    /**
     * The Access Key. Only for S3, required when private access auth is enabled.
     */
    private String ak;

    /**
     * The Secret Access Key. Only for S3, required when private access auth is enabled.
     */
    private String sk;

    /**
     * The bucket. Only for S3. Required for "aws_v2", not needed for "aws_v4".
     */
    private String bucket;

    /**
     * The region. Only for S3. Not needed for "aws_v2", optional for "aws_v4" (defaults to
     * "us-east-1").
     */
    private String region;

    /**
     * The service. Only for S3. Not needed for "aws_v2", optional for "aws_v4" (defaults to "s3").
     */
    private String service;

    /**
     * @param authType
     * @return this object
     */
    public ThirdBucketAuth withAuthType(String authType) {
        this.authType = authType;
        return this;
    }

    /**
     * @return authType
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * @param authType
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * @param enabled
     * @return this object
     */
    public ThirdBucketAuth withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @param ak
     * @return this object
     */
    public ThirdBucketAuth withAk(String ak) {
        this.ak = ak;
        return this;
    }

    /**
     * @return ak
     */
    public String getAk() {
        return ak;
    }

    /**
     * @param ak
     */
    public void setAk(String ak) {
        this.ak = ak;
    }

    /**
     * @param sk
     * @return this object
     */
    public ThirdBucketAuth withSk(String sk) {
        this.sk = sk;
        return this;
    }

    /**
     * @return sk
     */
    public String getSk() {
        return sk;
    }

    /**
     * @param sk
     */
    public void setSk(String sk) {
        this.sk = sk;
    }

    /**
     * @param bucket
     * @return this object
     */
    public ThirdBucketAuth withBucket(String bucket) {
        this.bucket = bucket;
        return this;
    }

    /**
     * @return bucket
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * @param bucket
     */
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    /**
     * @param region
     * @return this object
     */
    public ThirdBucketAuth withRegion(String region) {
        this.region = region;
        return this;
    }

    /**
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @param service
     * @return this object
     */
    public ThirdBucketAuth withService(String service) {
        this.service = service;
        return this;
    }

    /**
     * @return service
     */
    public String getService() {
        return service;
    }

    /**
     * @param service
     */
    public void setService(String service) {
        this.service = service;
    }
}

package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Request for setting bucket-level ObjectSet configuration.
 */
@JsonIgnoreProperties({"bucketName"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PutBucketObjectSetConfigurationRequest extends GenericBucketRequest {
    private int pathLevel;
    private Long storageQuota;
    private Long objectCountQuota;

    public PutBucketObjectSetConfigurationRequest(String bucketName, int pathLevel) {
        this(bucketName, pathLevel, null, null);
    }

    public PutBucketObjectSetConfigurationRequest(String bucketName, int pathLevel,
                                                   Long storageQuota, Long objectCountQuota) {
        super(bucketName);
        this.setPathLevel(pathLevel);
        this.setStorageQuota(storageQuota);
        this.setObjectCountQuota(objectCountQuota);
    }

    public int getPathLevel() {
        return pathLevel;
    }

    public void setPathLevel(int pathLevel) {
        if (pathLevel < 1 || pathLevel > 5) {
            throw new IllegalArgumentException("pathLevel should be between 1 and 5.");
        }
        this.pathLevel = pathLevel;
    }

    public Long getStorageQuota() {
        return storageQuota;
    }

    public void setStorageQuota(Long storageQuota) {
        checkQuota(storageQuota, "storageQuota");
        this.storageQuota = storageQuota;
    }

    public Long getObjectCountQuota() {
        return objectCountQuota;
    }

    public void setObjectCountQuota(Long objectCountQuota) {
        checkQuota(objectCountQuota, "objectCountQuota");
        this.objectCountQuota = objectCountQuota;
    }

    private static void checkQuota(Long quota, String name) {
        if (quota != null && quota < 0) {
            throw new IllegalArgumentException(name + " should not be negative.");
        }
    }

    @Override
    public PutBucketObjectSetConfigurationRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public PutBucketObjectSetConfigurationRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public PutBucketObjectSetConfigurationRequest withPathLevel(int pathLevel) {
        this.setPathLevel(pathLevel);
        return this;
    }

    public PutBucketObjectSetConfigurationRequest withStorageQuota(Long storageQuota) {
        this.setStorageQuota(storageQuota);
        return this;
    }

    public PutBucketObjectSetConfigurationRequest withObjectCountQuota(Long objectCountQuota) {
        this.setObjectCountQuota(objectCountQuota);
        return this;
    }
}

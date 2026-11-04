package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/** Request for setting an ObjectSet quota. */
@JsonIgnoreProperties({"bucketName", "objectSetName"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PutObjectSetQuotaRequest extends ObjectSetRequest {
    private Long storageQuota;
    private Long objectCountQuota;

    public PutObjectSetQuotaRequest(String bucketName, String objectSetName,
                                    Long storageQuota, Long objectCountQuota) {
        super(bucketName, objectSetName);
        this.setStorageQuota(storageQuota);
        this.setObjectCountQuota(objectCountQuota);
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
    public PutObjectSetQuotaRequest withBucketName(String bucketName) {
        super.withBucketName(bucketName);
        return this;
    }

    @Override
    public PutObjectSetQuotaRequest withObjectSetName(String objectSetName) {
        super.withObjectSetName(objectSetName);
        return this;
    }

    @Override
    public PutObjectSetQuotaRequest withRequestCredentials(BceCredentials credentials) {
        super.withRequestCredentials(credentials);
        return this;
    }

    public PutObjectSetQuotaRequest withStorageQuota(Long storageQuota) {
        this.setStorageQuota(storageQuota);
        return this;
    }

    public PutObjectSetQuotaRequest withObjectCountQuota(Long objectCountQuota) {
        this.setObjectCountQuota(objectCountQuota);
        return this;
    }
}

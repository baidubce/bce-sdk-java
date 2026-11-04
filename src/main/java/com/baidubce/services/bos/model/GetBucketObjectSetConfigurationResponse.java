package com.baidubce.services.bos.model;

/**
 * Bucket-level ObjectSet configuration.
 */
public class GetBucketObjectSetConfigurationResponse extends BosResponse {
    private Integer pathLevel;
    private Long storageQuota;
    private Long objectCountQuota;

    public Integer getPathLevel() {
        return pathLevel;
    }

    public void setPathLevel(Integer pathLevel) {
        this.pathLevel = pathLevel;
    }

    public Long getStorageQuota() {
        return storageQuota;
    }

    public void setStorageQuota(Long storageQuota) {
        this.storageQuota = storageQuota;
    }

    public Long getObjectCountQuota() {
        return objectCountQuota;
    }

    public void setObjectCountQuota(Long objectCountQuota) {
        this.objectCountQuota = objectCountQuota;
    }
}

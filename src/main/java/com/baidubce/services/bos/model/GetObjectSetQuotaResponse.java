package com.baidubce.services.bos.model;

/** Quotas configured for an ObjectSet. */
public class GetObjectSetQuotaResponse extends BosResponse {
    private Long storageQuota;
    private Long objectCountQuota;

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

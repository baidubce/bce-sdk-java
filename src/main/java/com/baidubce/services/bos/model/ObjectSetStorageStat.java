package com.baidubce.services.bos.model;

import com.fasterxml.jackson.annotation.JsonAlias;

/** Storage usage and object count for one storage class. */
public class ObjectSetStorageStat {
    @JsonAlias("Storage")
    private Long storage;
    @JsonAlias("ObjectCount")
    private Long objectCount;

    public Long getStorage() {
        return storage;
    }

    public void setStorage(Long storage) {
        this.storage = storage;
    }

    public Long getObjectCount() {
        return objectCount;
    }

    public void setObjectCount(Long objectCount) {
        this.objectCount = objectCount;
    }
}

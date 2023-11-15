package com.baidubce.services.bos.model;

public class GetBucketStorageClassResponse extends BosResponse {

    /**
     * The StorageClass is an identification that distinguish between infrequent access bos
     * and standard bos.
     */
    private String storageClass;

    /**
     * Gets the storageClass of the input file which is be uploaded to Baidu Bos.
     *
     * @return storageClass  The StorageClass is an identification that distinguish between infrequent access bos
     * and standard bos.
     */
    public String getStorageClass() {
        return storageClass;
    }

    /**
     * Sets the storageClass of the input file which is be uploaded to Baidu Bos.
     *
     * @param storageClass  The StorageClass is an identification that distinguish between infrequent access bos
     * and standard bos.
     */
    public void setStorageClass(String storageClass) {
        this.storageClass = storageClass;
    }

    @Override
    public String toString() {
        return "GetBucketStorageClassResponse{"
                + "storageClass='" + storageClass + '\''
                + '}';
    }
}

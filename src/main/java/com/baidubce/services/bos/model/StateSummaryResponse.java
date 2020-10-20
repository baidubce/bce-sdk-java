package com.baidubce.services.bos.model;

/**
 * Contains options to return a summary information about the contents begin with the prefix.
 */
public class StateSummaryResponse extends BosResponse {

    /**
     * The name of the bucket containing the listed parts, as specified in the
     * original request.
     */
    private String bucketName;

    /**
     * Optional parameter restricting the response to keys which begin with the specified prefix.
     * equals to StatContentsRequest's
     */
    private String prefix;

    /**
     * The size of all objects begin with the prefix.
     */
    private long totalSize;

    /**
     * The number of all objects (including file and directory) begin with the prefix.
     */
    private long objectsCount;

    /**
     * The number of files begin with the prefix.
     */
    private long filesCount;

    public StateSummaryResponse() {

    }

    public StateSummaryResponse(String bucketName, String prefix, long totalSize, long objectsCount, long filesCount) {
        this.bucketName = bucketName;
        this.prefix = prefix;
        this.totalSize = totalSize;
        this.objectsCount = objectsCount;
        this.filesCount = filesCount;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public long getFilesCount() {
        return filesCount;
    }

    public void setFilesCount(int filesCount) {
        this.filesCount = filesCount;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getObjectsCount() {
        return objectsCount;
    }

    public void setObjectsCount(long objectsCount) {
        this.objectsCount = objectsCount;
    }
}

package com.baidubce.services.bos.model;

import static com.google.common.base.Preconditions.checkArgument;

import com.baidubce.auth.BceCredentials;

/**
 * Contains the parameters used for the UploadPartCopy operation on Baidu Bos.
 *
 * <p>
 * Required Parameters: BucketName, Key, sourceBucketName, sourcetkey, UploadId,
 * PartNumber, partSize, offSet
 */
public class UploadPartCopyRequest extends GenericUploadRequest {

    /**
     * The part number describing this part's position relative to the other
     * parts in the multipart upload. Part number must be between 1 and 10,000
     * (inclusive).
     */
    private int partNumber;

    /**
     * The size of this part, in bytes.
     */
    private long partSize;

    /**
     * The offset describing skipBytes for this object in bytes.
     */
    private long offSet;

    /**
     * The sourceBucketName describing the source bucket for uploadCopyPart operation.
     */
    private String sourceBucketName;

    /**
     * The sourcetkey describing the source object for uploadCopyPart operation.
     */
    private String sourcetkey;

    private String xBceCrc;

    public String getxBceCrc() {
        return xBceCrc;
    }

    public void setxBceCrc(String xBceCrc) {
        this.xBceCrc = xBceCrc;
    }

    public UploadPartCopyRequest() {
        super();
    }

    /**
     * Constructs a new UploadPartCopyRequest object to copy a stream of data to the
     * specified bucket and key,
     *
     * @param bucketName The name of the bucket containing the initiated multipart upload with
     *     which this new part will be associated.
     * @param key The key of the initiated multipart upload.
     * @param sourcetBucketName The sourceBucketName describing the source bucket for uploadCopyPart operation.
     * @param sourcetkey The sourcetkey describing the source object for uploadCopyPart operation.
     * @param uploadId The ID of an existing, initiated multipart upload, with which this new
     *     part will be associated.
     * @param partNumber The part number describing this part's position relative to the other
     *     parts in the multipart upload. Part number must be between 1 and 10,000 (inclusive).
     * @param partSize The size of this part, in bytes.
     * @param offSet The offset describing skipBytes for this object in bytes.
     */
    public UploadPartCopyRequest(String bucketName, String key, String sourcetBucketName, String sourcetkey,
                                 String uploadId, int partNumber, long partSize, long offSet) {
        super(bucketName, key, uploadId);
        this.setSourceBucketName(sourcetBucketName);
        this.setSourceKey(sourcetkey);
        this.setPartNumber(partNumber);
        this.setPartSize(partSize);
        this.setOffSet(offSet);
    }

    @Override
    public UploadPartCopyRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Sets the name of the bucket containing the existing, initiated multipart
     * upload, with which this new part will be associated, and returns this
     * updated object so that additional method calls can be chained together.
     *
     * @param bucketName the name of the bucket containing the existing, initiated
     *     multipart upload, with which this new part will be associated.
     * @return This updated UploadPartCopyRequest object.
     */
    @Override
    public UploadPartCopyRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    /**
     * Sets the key of the initiated multipart upload, and returns this updated
     * object so that additional method calls can be chained together.
     *
     * @param key the key of the initiated multipart upload.
     * @return This updated UploadPartCopyRequest object.
     */
    @Override
    public UploadPartCopyRequest withKey(String key) {
        this.setKey(key);
        return this;
    }

    /**
     * Sets the ID of the existing, initiated multipart upload with which this
     * new part will be associated, and returns this updated UploadPartRequest
     * object so that additional method calls can be chained together.
     *
     * @param uploadId the ID of the existing, initiated multipart upload with which
     *    this new part will be associated.
     * @return This updated UploadPartCopyRequest object.
     */
    @Override
    public UploadPartCopyRequest withUploadId(String uploadId) {
        this.setUploadId(uploadId);
        return this;
    }

    /**
     * Returns the part number describing this part's position relative to the
     * other parts in the multipart upload. Part number must be between 1 and
     * 10,000 (inclusive).
     *
     * @return the part number describing this part's position relative to the
     *     other parts in the multipart upload. Part number must be between
     *     1 and 10,000 (inclusive).
     */
    public int getPartNumber() {
        return this.partNumber;
    }

    /**
     * Sets the part number describing this part's position relative to the
     * other parts in the multipart upload. Part number must be between 1 and
     * 10,000 (inclusive).
     *
     * @param partNumber the part number describing this part's position relative to
     *     the other parts in the multipart upload. Part number must be
     *     between 1 and 10,000 (inclusive).
     */
    public void setPartNumber(int partNumber) {
        checkArgument(partNumber > 0, "partNumber should be positive, but is %s", partNumber);
        this.partNumber = partNumber;
    }

    public UploadPartCopyRequest withPartNumber(int partNumber) {
        this.setPartNumber(partNumber);
        return this;
    }

    /**
     * Returns the size of this part, in bytes.
     *
     * @return the size of this part, in bytes.
     */
    public long getPartSize() {
        return this.partSize;
    }

    /**
     * Sets the size of this part, in bytes.
     *
     * @param partSize the size of this part, in bytes.
     */
    public void setPartSize(long partSize) {
        checkArgument(partSize >= 0, "partSize should not be negative.");
        this.partSize = partSize;
    }

    public UploadPartCopyRequest withPartSize(long partSize) {
        this.setPartSize(partSize);
        return this;
    }

    public long getOffSet() {
        return this.offSet;
    }

    public void setOffSet(long offSet) {
        this.offSet = offSet;
    }

    public UploadPartCopyRequest withOffSet(long offSet) {
        this.setOffSet(offSet);
        return this;
    }

    public String getSourceBucketName() {
        return this.sourceBucketName;
    }

    public void setSourceBucketName(String sourceBucketName) {
        this.sourceBucketName = sourceBucketName;
    }

    public UploadPartCopyRequest withSourceBucketName(String sourceBucketName) {
        this.setSourceBucketName(sourceBucketName);
        return this;
    }

    public String getSourceKey() {
        return this.sourcetkey;
    }

    public void setSourceKey(String sourcetkey) {
        this.sourcetkey = sourcetkey;
    }

    public UploadPartCopyRequest withSourceKey(String sourcetkey) {
        this.setSourceKey(sourcetkey);
        return this;
    }

    /**
     * Gets the limit of put object speed.
     * @return the limit of put object speed. unit: bit/s
     */
    public long getTrafficLimitBitPS() {
        return trafficLimitBitPS;
    }

    /**
     * Sets Gets the limit of put object speed. range: 819200 bit/s ~ 838860800 bit/s
     * @param trafficLimitBitPS the limit of put object speed. unit: bit/s
     */
    public void setTrafficLimitBitPS(long trafficLimitBitPS) {
        this.trafficLimitBitPS = trafficLimitBitPS;
    }

    /**
     *
     * @param trafficLimitBitPS the limit of put object speed. unit: bit/s, range: 819200 bit/s ~ 838860800 bit/s
     * @return This PutObjectRequest, so that additional method calls can be chained together
     */
    public UploadPartCopyRequest withTrafficLimitBitPS(long trafficLimitBitPS) {
        this.setTrafficLimitBitPS(trafficLimitBitPS);
        return this;
    }
}

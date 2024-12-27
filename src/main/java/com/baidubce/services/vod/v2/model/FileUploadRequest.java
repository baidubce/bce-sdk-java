package com.baidubce.services.vod.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bos.model.ObjectMetadata;
import java.io.File;
import java.io.InputStream;
import java.util.List;

public class FileUploadRequest extends AbstractBceRequest {

    /**
     * The file containing the data to be uploaded to Baidu Vod media. You must either
     * specify a file or an InputStream containing the data to be uploaded to
     * Baidu Vod.
     * <br>
     * When uploading with multipart, only "file" can be used.
     */
    private File file;
    /**
     * The InputStream containing the data to be uploaded to Baidu Vod media. You must
     * either specify a file or an InputStream containing the data to be
     * uploaded to Baidu Vod.
     * <br>
     * It doesn't work for multipart uploads.
     */
    private InputStream inputStream;
    /**
     * See {@link com.baidubce.services.bos.model.ObjectMetadata}
     */
    private ObjectMetadata objectMetadata = new ObjectMetadata();
    /**
     * See {@link com.baidubce.services.bos.model.BosProgressCallback}
     */
    private VodUploadProgressCallback<?> progressCallback = null;
    /**
     * When uploading with multipart, you can specify multiple progressCallbacks.
     * Each progressCallback corresponds to a part upload and is used sequentially.
     */
    private List<VodUploadProgressCallback<?>> multipartProgressCallbacks;
    /**
     * The parallelism of multipart upload.
     */
    private int multipartUploadParallelism = 1;
    /**
     * The limit of put object speed. unit: bit/s
     * range: 819200 bit/s ~ 838860800 bit/s
     */
    private long trafficLimitBitPS = -1;


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ObjectMetadata getObjectMetadata() {
        return objectMetadata;
    }

    public void setObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
    }

    public VodUploadProgressCallback<?> getProgressCallback() {
        return progressCallback;
    }

    public void setProgressCallback(VodUploadProgressCallback<?> progressCallback) {
        this.progressCallback = progressCallback;
    }

    public List<VodUploadProgressCallback<?>> getMultipartProgressCallbacks() {
        return multipartProgressCallbacks;
    }

    public void setMultipartProgressCallbacks(List<VodUploadProgressCallback<?>> multipartProgressCallbacks) {
        this.multipartProgressCallbacks = multipartProgressCallbacks;
    }

    public int getMultipartUploadParallelism() {
        return multipartUploadParallelism;
    }

    public void setMultipartUploadParallelism(int multipartUploadParallelism) {
        this.multipartUploadParallelism = multipartUploadParallelism;
    }

    public long getTrafficLimitBitPS() {
        return trafficLimitBitPS;
    }

    public void setTrafficLimitBitPS(long trafficLimitBitPS) {
        this.trafficLimitBitPS = trafficLimitBitPS;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public FileUploadRequest withFile(File file) {
        this.file = file;
        return this;
    }

    public FileUploadRequest withInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public FileUploadRequest withObjectMetadata(ObjectMetadata objectMetadata) {
        this.objectMetadata = objectMetadata;
        return this;
    }

    public FileUploadRequest withProgressCallback(VodUploadProgressCallback<?> progressCallback) {
        this.progressCallback = progressCallback;
        return this;
    }

    public FileUploadRequest withMultipartProgressCallbacks(List<VodUploadProgressCallback<?>> multipartProgressCallbacks) {
        this.multipartProgressCallbacks = multipartProgressCallbacks;
        return this;
    }

    public FileUploadRequest withMultipartUploadParallelism(int multipartUploadParallelism) {
        this.multipartUploadParallelism = multipartUploadParallelism;
        return this;
    }

    public FileUploadRequest withTrafficLimitBitPS(long trafficLimitBitPS) {
        this.trafficLimitBitPS = trafficLimitBitPS;
        return this;
    }

}

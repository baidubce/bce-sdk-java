package com.baidubce.services.vod.model;

import com.baidubce.model.AbstractBceResponse;

public class GenerateMediaIdResponse extends AbstractBceResponse {

    private String mediaId;

    private String sourceBucket;

    private String sourceKey;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getSourceKey() {
        return sourceKey;
    }

    public void setSourceKey(String sourceKey) {
        this.sourceKey = sourceKey;
    }

    public String getSourceBucket() {
        return sourceBucket;
    }

    public void setSourceBucket(String sourceBucket) {
        this.sourceBucket = sourceBucket;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GenerateMediaIdResponse { \n");
        sb.append("  mediaId = ").append(mediaId).append("\n");
        sb.append("  sourceBucket = ").append(sourceBucket).append("\n");
        sb.append("  sourceKey = ").append(sourceKey).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}

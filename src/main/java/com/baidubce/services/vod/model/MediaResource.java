package com.baidubce.services.vod.model;

public class MediaResource {

    private String mediaId;
    private String status;
    private Attributes attributes;
    private MediaMeta meta;
    private String publishTime;
    private String createTime;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MediaMeta getMeta() {
        return meta;
    }

    public void setMeta(MediaMeta meta) {
        this.meta = meta;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("  MediaResource { \n");
        sb.append("    mediaId = ").append(mediaId).append("\n");
        sb.append("    status = ").append(status).append("\n");
        sb.append("    attributes = ").append(attributes).append("\n");
        sb.append("    meta = ").append(meta).append("\n");
        sb.append("    createTime = ").append(createTime).append("\n");
        sb.append("    publishTime = ").append(publishTime).append("\n");
        sb.append("  }\n");
        return sb.toString();
    }

}

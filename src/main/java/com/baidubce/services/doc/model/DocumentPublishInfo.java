package com.baidubce.services.doc.model;

import java.util.Date;

/**
 * Created by baidu on 16/1/7.
 */
public class DocumentPublishInfo {
    private int pageCount = 0;
    private int sizeInBytes = 0;
    private Date publishTime = null;
    private String coverUrl = null;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSizeInBytes() {
        return sizeInBytes;
    }

    public void setSizeInBytes(int sizeInBytes) {
        this.sizeInBytes = sizeInBytes;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DocumentPublishInfo {\n");
        sb.append("    pageCount: ").append(pageCount).append("\n");
        sb.append("    sizeInBytes: ").append(sizeInBytes).append("\n");
        sb.append("    publishTime: ").append(publishTime).append("\n");
        sb.append("    coverUrl: ").append(coverUrl).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

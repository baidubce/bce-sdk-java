package com.baidubce.services.vod.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class MediaFetchRequest extends AbstractBceRequest {

    /**
     * 文件拉取链接，http/https url，文件扩展名必须为：
     * <br>
     * 视频：mp4、flv、mov、3gp、avi、mpg、asf、wmv、mkv、ts、webm、mxf
     * <br>
     * 音频：mp3、m4a、flac、ogg、wav、ra、aac、amr
     * <br>
     * 图片：jpg、jpeg、png、gif、bmp、webp、svg
     */
    private String url;
    private String name;
    /**
     * 视频文件支持指定封面拉取链接，http/https url，文件扩展名必须为：jpg、jpeg、png、gif、bmp、webp
     */
    private String coverUrl;
    private String highlightProjectId;
    private String categoryId;
    private MediaWorkflowTaskInput workflow;
    private MediaPresetTaskInput preset;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getHighlightProjectId() {
        return highlightProjectId;
    }

    public void setHighlightProjectId(String highlightProjectId) {
        this.highlightProjectId = highlightProjectId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public MediaWorkflowTaskInput getWorkflow() {
        return workflow;
    }

    public void setWorkflow(MediaWorkflowTaskInput workflow) {
        this.workflow = workflow;
    }

    public MediaPresetTaskInput getPreset() {
        return preset;
    }

    public void setPreset(MediaPresetTaskInput preset) {
        this.preset = preset;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public MediaFetchRequest withUrl(String url) {
        this.url = url;
        return this;
    }

    public MediaFetchRequest withName(String name) {
        this.name = name;
        return this;
    }

    public MediaFetchRequest withCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
        return this;
    }

    public MediaFetchRequest withHighlightProjectId(String highlightProjectId) {
        this.highlightProjectId = highlightProjectId;
        return this;
    }

    public MediaFetchRequest withCategoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public MediaFetchRequest withWorkflow(MediaWorkflowTaskInput workflow) {
        this.workflow = workflow;
        return this;
    }

    public MediaFetchRequest withPreset(MediaPresetTaskInput preset) {
        this.preset = preset;
        return this;
    }

}

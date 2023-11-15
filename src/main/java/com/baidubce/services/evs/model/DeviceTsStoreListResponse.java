package com.baidubce.services.evs.model;

import java.util.Map;

public class DeviceTsStoreListResponse extends EvsBaseResponse {

    private static final long serialVersionUID = 7665676333267546255L;

    private String title;

    private String thumbnailUrl;

    private Long from;

    private Double duration;

    private String playUrl;

    private String cropThumbnailUrl;

    private Map<String, Object> aiAnalysisData;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getCropThumbnailUrl() {
        return cropThumbnailUrl;
    }

    public void setCropThumbnailUrl(String cropThumbnailUrl) {
        this.cropThumbnailUrl = cropThumbnailUrl;
    }

    public Map<String, Object> getAiAnalysisData() {
        return aiAnalysisData;
    }

    public void setAiAnalysisData(Map<String, Object> aiAnalysisData) {
        this.aiAnalysisData = aiAnalysisData;
    }

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceTsStoreListResponse that = (DeviceTsStoreListResponse) o;

        if (title != null ? !title.equals(that.title) : that.title != null) {
            return false;
        }
        if (thumbnailUrl != null ? !thumbnailUrl.equals(that.thumbnailUrl) : that.thumbnailUrl != null) {
            return false;
        }
        if (playUrl != null ? !playUrl.equals(that.playUrl) : that.playUrl != null) {
            return false;
        }
        if (from != null ? !from.equals(that.from) : that.from != null) {
            return false;
        }
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) {
            return false;
        }
        if (cropThumbnailUrl != null ?
                !cropThumbnailUrl.equals(that.cropThumbnailUrl) :
                that.cropThumbnailUrl != null) {
            return false;
        }
        return aiAnalysisData != null ? aiAnalysisData.equals(that.aiAnalysisData) : that.aiAnalysisData == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (thumbnailUrl != null ? thumbnailUrl.hashCode() : 0);
        result = 31 * result + (playUrl != null ? playUrl.hashCode() : 0);
        result = 31 * result + (cropThumbnailUrl != null ? cropThumbnailUrl.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (aiAnalysisData != null ? aiAnalysisData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceTsStoreListResponse{" +
                "title='" + title + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", from=" + from +
                ", duration=" + duration +
                ", playUrl='" + playUrl + '\'' +
                ", cropThumbnailUrl='" + cropThumbnailUrl + '\'' +
                ", aiAnalysisData=" + aiAnalysisData +
                '}';
    }
}

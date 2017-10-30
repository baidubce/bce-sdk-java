package com.baidubce.services.vodpro.model.adaptor.response;

import java.util.List;

/**
 * Created on 17/10/18
 *
 * @author liumin08
 */
public class Evidence {
    private Integer beginTime;
    private Integer endTime;
    private String type;
    private String url;
    private List<Label> labels;

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Evidence{"
                + "beginTime=" + beginTime
                + ", endTime=" + endTime
                + ", type=" + type
                + ", url='" + url + '\''
                + ", labels=" + labels
                + '}';
    }
}

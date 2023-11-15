package com.baidubce.services.cdn.model.util;

public class UrlRecord {
    private String url;
    private String time;

    /**
     * 执行的操作，1表ban操作，0表unban操作
     * 查询url封禁操作记录用到
     */
    private int action;

    public UrlRecord() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
}

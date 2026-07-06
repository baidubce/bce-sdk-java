package com.baidubce.services.eo.model.logmodel;

import com.baidubce.services.eo.model.JsonObject;

public class LogEntry extends JsonObject {
    private String domain;
    private String url;
    private String name;
    private Integer size;
    private String logTimeBegin;
    private String logTimeEnd;

    /**
     * @return domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     *
     * @param domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return size
     */
    public Integer getSize() {
        return size;
    }

    /**
     *
     * @param size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return logTimeBegin
     */
    public String getLogTimeBegin() {
        return logTimeBegin;
    }

    /**
     *
     * @param logTimeBegin
     */
    public void setLogTimeBegin(String logTimeBegin) {
        this.logTimeBegin = logTimeBegin;
    }

    /**
     * @return logTimeEnd
     */
    public String getLogTimeEnd() {
        return logTimeEnd;
    }

    /**
     *
     * @param logTimeEnd
     */
    public void setLogTimeEnd(String logTimeEnd) {
        this.logTimeEnd = logTimeEnd;
    }
}

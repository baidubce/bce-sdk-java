package com.baidubce.services.eo.model.rule;

import com.baidubce.services.eo.model.JsonObject;

/**
 * The URL authentication configuration.
 */
public class AntiHotLink extends JsonObject {
    /**
     * The auth type.
     */
    private String antiType;

    /**
     * The primary key.
     */
    private String secretKey;

    /**
     * The secondary key.
     */
    private String newsecretKey;

    /**
     * The auth valid duration in seconds, supported by typeA/B/C.
     */
    private Integer timeout;

    /**
     * The auth time format.
     */
    private String timestampFormat;

    /**
     * The signature arg, only supported by typeA.
     */
    private String authArg;

    /**
     * @param antiType
     * @return this object
     */
    public AntiHotLink withAntiType(String antiType) {
        this.antiType = antiType;
        return this;
    }

    /**
     * @return antiType
     */
    public String getAntiType() {
        return antiType;
    }

    /**
     * @param antiType
     */
    public void setAntiType(String antiType) {
        this.antiType = antiType;
    }

    /**
     * @param secretKey
     * @return this object
     */
    public AntiHotLink withSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    /**
     * @return secretKey
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * @param secretKey
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    /**
     * @param newsecretKey
     * @return this object
     */
    public AntiHotLink withNewsecretKey(String newsecretKey) {
        this.newsecretKey = newsecretKey;
        return this;
    }

    /**
     * @return newsecretKey
     */
    public String getNewsecretKey() {
        return newsecretKey;
    }

    /**
     * @param newsecretKey
     */
    public void setNewsecretKey(String newsecretKey) {
        this.newsecretKey = newsecretKey;
    }

    /**
     * @param timeout
     * @return this object
     */
    public AntiHotLink withTimeout(Integer timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * @return timeout
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * @param timeout
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    /**
     * @param timestampFormat
     * @return this object
     */
    public AntiHotLink withTimestampFormat(String timestampFormat) {
        this.timestampFormat = timestampFormat;
        return this;
    }

    /**
     * @return timestampFormat
     */
    public String getTimestampFormat() {
        return timestampFormat;
    }

    /**
     * @param timestampFormat
     */
    public void setTimestampFormat(String timestampFormat) {
        this.timestampFormat = timestampFormat;
    }

    /**
     * @param authArg
     * @return this object
     */
    public AntiHotLink withAuthArg(String authArg) {
        this.authArg = authArg;
        return this;
    }

    /**
     * @return authArg
     */
    public String getAuthArg() {
        return authArg;
    }

    /**
     * @param authArg
     */
    public void setAuthArg(String authArg) {
        this.authArg = authArg;
    }
}

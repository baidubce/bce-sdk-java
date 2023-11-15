package com.baidubce.services.cdn.model.domain;

public class OriginTimeout {
    private int connectTimeout;
    private int loadTimeout;

    public OriginTimeout() {
    }

    public OriginTimeout(int connectTimeout, int loadTimeout) {
        this.connectTimeout = connectTimeout;
        this.loadTimeout = loadTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getLoadTimeout() {
        return loadTimeout;
    }

    public void setLoadTimeout(int loadTimeout) {
        this.loadTimeout = loadTimeout;
    }

    public OriginTimeout withConnectTimeout(int connectTimeout) {
        setConnectTimeout(connectTimeout);
        return this;
    }

    public OriginTimeout withLoadTimeout(int loadTimeout) {
        setLoadTimeout(loadTimeout);
        return this;
    }
}

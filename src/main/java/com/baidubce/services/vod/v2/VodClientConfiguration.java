package com.baidubce.services.vod.v2;

import com.baidubce.BceClientConfiguration;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;

public class VodClientConfiguration extends BceClientConfiguration {

    public static final int DEFAULT_STREAM_BUFFER_SIZE = 5 * 1024 * 1024;

    private int streamBufferSize = DEFAULT_STREAM_BUFFER_SIZE;

    /**
     * whether to enable using nio to http async put, default value is false for async put
     */
    private boolean enableHttpAsyncPut = false;

    public int getStreamBufferSize() {
        return streamBufferSize;
    }

    public void setStreamBufferSize(int streamBufferSize) {
        this.streamBufferSize = streamBufferSize;
    }

    public boolean isEnableHttpAsyncPut() {
        return enableHttpAsyncPut;
    }

    public void setEnableHttpAsyncPut(boolean enableHttpAsyncPut) {
        this.enableHttpAsyncPut = enableHttpAsyncPut;
    }

    public boolean isHttpsEnabled() {
        return getProtocol().equals(Protocol.HTTPS) || getEndpoint().toLowerCase().startsWith("https://");
    }

    /**
     * Set vod credentials.
     *
     * @param credentials The default credentials
     * @return A VodClientConfiguration instance.
     */
    public VodClientConfiguration withCredentials(DefaultBceCredentials credentials) {
        this.setCredentials(credentials);
        return this;
    }

    /**
     * Set vod endpoint.
     *
     * @param endpoint The service endpoint URL to which the client will connect.
     * @return A VodClientConfiguration instance.
     */
    public VodClientConfiguration withEndpoint(String endpoint) {
        this.setEndpoint(endpoint);
        return this;
    }

    public VodClientConfiguration withStreamBufferSize(int streamBufferSize) {
        this.streamBufferSize = streamBufferSize;
        return this;
    }

}

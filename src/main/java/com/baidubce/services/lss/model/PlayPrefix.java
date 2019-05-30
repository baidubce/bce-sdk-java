package com.baidubce.services.lss.model;

import java.io.Serializable;

/**
 * Created by wuyafei on 16/6/28.
 */
public class PlayPrefix implements Serializable {
    private String rtmp;

    private String hls;

    private String flv;

    public String getRtmp() {
        return rtmp;
    }

    public void setRtmp(String rtmp) {
        this.rtmp = rtmp;
    }

    public String getHls() {
        return hls;
    }

    public void setHls(String hls) {
        this.hls = hls;
    }

    public String getFlv() {
        return flv;
    }

    public void setFlv(String flv) {
        this.flv = flv;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class PlayPrefix {\n");
        sb.append("    rtmpUrlPrefix: ").append(rtmp).append("\n");
        sb.append("    hlsUrlPrefix: ").append(hls).append("\n");
        sb.append("    flvUrlPrefix: ").append(flv).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

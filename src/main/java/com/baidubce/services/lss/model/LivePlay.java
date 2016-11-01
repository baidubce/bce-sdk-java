/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.lss.model;

import java.util.Map;

public class LivePlay {

    private String rtmpUrl = null;

    private String hlsUrl = null;

    private String flvUrl = null;

    private Map<String, String> hlsUrls;
    private Map<String, String> rtmpUrls;
    private Map<String, String> flvUrls;

    public String getRtmpUrl() {
        return rtmpUrl;
    }

    public void setRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
    }

    public LivePlay withRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
        return this;
    }

    public Map<String, String> getRtmpUrls() {
        return rtmpUrls;
    }

    public void setRtmpUrls(Map<String, String> rtmpUrls) {
        this.rtmpUrls = rtmpUrls;
    }

    public LivePlay withRtmpUrls(Map<String, String> rtmpUrls) {
        this.rtmpUrls = rtmpUrls;
        return this;
    }

    public String getHlsUrl() {
        return hlsUrl;
    }

    public void setHlsUrl(String hlsUrl) {
        this.hlsUrl = hlsUrl;
    }

    public LivePlay withHlsUrl(String hlsUrl) {
        this.hlsUrl = hlsUrl;
        return this;
    }

    public Map<String, String> getHlsUrls() {
        return hlsUrls;
    }

    public void setHlsUrls(Map<String, String> hlsUrls) {
        this.hlsUrls = hlsUrls;
    }

    public LivePlay withHlsUrls(Map<String, String> hlsUrls) {
        this.hlsUrls = hlsUrls;
        return this;
    }

    public String getFlvUrl() {
        return flvUrl;
    }

    public void setFlvUrl(String flvUrl) {
        this.flvUrl = flvUrl;
    }

    public LivePlay withFlvUrl(String flvUrl) {
        this.flvUrl = flvUrl;
        return this;
    }

    public Map<String, String> getFlvUrls() {
        return flvUrls;
    }

    public void setFlvUrls(Map<String, String> flvUrls) {
        this.flvUrls = flvUrls;
    }

    public LivePlay withFlvUrls(Map<String, String> flvUrls) {
        this.flvUrls = flvUrls;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class LivePlay {\n");
        sb.append("    rtmpUrl: ").append(rtmpUrl).append("\n");
        sb.append("    hlsUrl: ").append(hlsUrl).append("\n");
        sb.append("    flvUrl: ").append(flvUrl).append("\n");
        sb.append("    rtmpUrls: ").append(rtmpUrls).append("\n");
        sb.append("    hlsUrls: ").append(hlsUrls).append("\n");
        sb.append("    flvUrls: ").append(flvUrls).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

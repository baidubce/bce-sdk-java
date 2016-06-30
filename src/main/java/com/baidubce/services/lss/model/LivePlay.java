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

public class LivePlay {

    private String rtmpUrl = null;

    private String hlsUrl = null;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class LivePlay {\n");
        sb.append("    rtmpUrl: ").append(rtmpUrl).append("\n");
        sb.append("    hlsUrl: ").append(hlsUrl).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

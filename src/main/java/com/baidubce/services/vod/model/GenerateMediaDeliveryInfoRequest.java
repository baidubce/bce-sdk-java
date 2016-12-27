/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.vod.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class GenerateMediaDeliveryInfoRequest extends AbstractBceRequest {

    /*
     * The unique ID of media resource managed by VOD service.
     */
    private String mediaId;

    private String transcodingPresetName;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTranscodingPresetName() {
        return transcodingPresetName;
    }

    public void setTranscodingPresetName(String transcodingPresetName) {
        this.transcodingPresetName = transcodingPresetName;
    }

    public GenerateMediaDeliveryInfoRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public GenerateMediaDeliveryInfoRequest withTranscodingPresetName(String transcodingPresetName) {
        this.transcodingPresetName = transcodingPresetName;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("GenerateMediaDeliveryInfoRequest { \n");
        sb.append("  mediaId = ").append(mediaId).append("\n");
        sb.append("  transcodingPresetName = ").append(transcodingPresetName).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

}

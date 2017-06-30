/*
 * Copyright 2015 Baidu, Inc.
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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class CreatePresetRequest extends AbstractBceRequest {

    private String name = null;

    private String description = null;

    private Boolean forwardOnly = null;

    private Audio audio = null;

    private Video video = null;

    private Hls hls = null;

    private Rtmp rtmp = null;

    private LiveThumbnail thumbnail = null;

    private Watermarks watermarks = null;

    /**
     * Returns the name of the preset.
     *
     * @return the name of the preset
     **/
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the preset.
     *
     * @param name the name of the preset
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the name of the preset and returns this object.
     *
     * @param name the name of the preset.
     * @return this object
     */
    public CreatePresetRequest withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Returns the description of the preset.
     *
     * @return the description of the preset
     **/
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the preset.
     *
     * @param description the description of the preset
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the description of the preset and returns this object.
     *
     * @param description the description of the preset.
     * @return this object
     */
    public CreatePresetRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Returns true if forward only.
     *
     * @return true if forward only.
     **/
    public Boolean getForwardOnly() {
        return forwardOnly;
    }

    /**
     * Sets the forward option
     *
     * @param forwardOnly true if forward only
     */
    public void setForwardOnly(Boolean forwardOnly) {
        this.forwardOnly = forwardOnly;
    }

    /**
     * Sets the forward option and returns this object.
     *
     * @param forwardOnly true if forward only
     * @return this object
     */
    public CreatePresetRequest withForwardOnly(Boolean forwardOnly) {
        this.forwardOnly = forwardOnly;
        return this;
    }

    /**
     * Returns the audio settings.
     *
     * @return the audio settings
     **/
    public Audio getAudio() {
        return audio;
    }

    /**
     * Sets the audio settings.
     *
     * @param audio the audio settings
     */
    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    /**
     * Sets the audio settings and returns this object.
     *
     * @param audio the audio settings
     * @return this object
     */
    public CreatePresetRequest withAudio(Audio audio) {
        this.audio = audio;
        return this;
    }

    /**
     * Returns the video settings.
     *
     * @return the video settings
     **/
    public Video getVideo() {
        return video;
    }

    /**
     * Sets the video settings.
     *
     * @param video the video settings
     */
    public void setVideo(Video video) {
        this.video = video;
    }

    /**
     * Sets the video settings and returns this object.
     *
     * @param video the video settings
     * @return this object
     */
    public CreatePresetRequest withVideo(Video video) {
        this.video = video;
        return this;
    }

    /**
     * Returns the HLS settings.
     *
     * @return the HLS settings
     **/
    public Hls getHls() {
        return hls;
    }

    /**
     * Sets the HLS settings.
     *
     * @param hls the HLS setting
     */
    public void setHls(Hls hls) {
        this.hls = hls;
    }

    /**
     * Sets the HLS settings and returns this object.
     *
     * @param hls the HLS settings
     * @return this object
     */
    public CreatePresetRequest withHls(Hls hls) {
        this.hls = hls;
        return this;
    }

    /**
     * Returns the RTMP settings.
     *
     * @return the RTMP settings
     **/
    public Rtmp getRtmp() {
        return rtmp;
    }

    /**
     * Sets the RTMP settings.
     *
     * @param rtmp the RTMP settings
     */
    public void setRtmp(Rtmp rtmp) {
        this.rtmp = rtmp;
    }

    /**
     * Sets the RTMP settings and returns this object.
     *
     * @param rtmp the RTMP settings
     * @return this object
     */
    public CreatePresetRequest withRtmp(Rtmp rtmp) {
        this.rtmp = rtmp;
        return this;
    }

    /**
     * Returns the watermark settings.
     *
     * @return the watermark settings
     **/
    public Watermarks getWatermarks() {
        return watermarks;
    }

    public void setWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
    }

    public CreatePresetRequest withWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
        return this;
    }

    /**
     * 缩略图输出信息的集合
     **/
    public LiveThumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(LiveThumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CreatePresetRequest withThumbnail(LiveThumbnail thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public CreatePresetRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class CreatePresetRequest {\n");
        sb.append("    name: ").append(name).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    forwardOnly: ").append(forwardOnly).append("\n");
        sb.append("    audio: ").append(audio).append("\n");
        sb.append("    video: ").append(video).append("\n");
        sb.append("    hls: ").append(hls).append("\n");
        sb.append("    rtmp: ").append(rtmp).append("\n");
        sb.append("    thumbnail: ").append(thumbnail).append("\n");
        sb.append("    watermarks: ").append(watermarks).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

/*
 * Copyright 2017 Baidu, Inc.
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

import com.baidubce.model.AbstractBceResponse;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Represents response of querying a stream
 */
public class GetStreamResponse extends AbstractBceResponse {

    private String sessionId = null;

    private String playDomain = null;

    private String app = null;

    private String description = null;

    private String preset = null;

    private Map<String, String> presets = null;

    private String createTime = null;

    private String status = null;

    private String notification = null;

    private String securityPolicy = null;

    private String recording = null;

    private String thumbnail = null;

    private List<String> thumbnails = null;

    private String scene = null;

    private Watermarks watermarks = null;

    private LivePublish publish = null;

    private LivePlay play = null;

    private String streamingStatus = null;

    private StreamingStatistics statistics = null;

    private String audit = null;

    private String destinationPushUrl = null;

    /**
     * Returns sessionId
     * @return sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * Sets sessionId
     * @param sessionId
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * Returns playDomain
     * @return playDomain
     */
    public String getPlayDomain() {
        return playDomain;
    }

    /**
     * Sets playDomain
     * @param playDomain
     */
    public void setPlayDomain(String playDomain) {
        this.playDomain = playDomain;
    }

    /**
     * Returns app
     * @return app
     */
    public String getApp() {
        return app;
    }

    /**
     * Sets app
     * @param app
     */
    public void setApp(String app) {
        this.app = app;
    }

    /**
     * Returns scent
     * @return scent
     */
    public String getScene() {
        return scene;
    }

    /**
     * Sets scene
     * @param scene
     */
    public void setScene(String scene) {
        this.scene = scene;
    }

    /**
     * Returns description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns preset
     * @return preset
     */
    public String getPreset() {
        return preset;
    }

    /**
     * Sets preset
     * @param preset
     */
    public void setPreset(String preset) {
        this.preset = preset;
    }

    /**
     * Returns createTime
     * @return createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * Sets createTime
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * Returns status
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns notification
     * @return notification
     */
    public String getNotification() {
        return notification;
    }

    /**
     * Sets notification
     * @param notification
     */
    public void setNotification(String notification) {
        this.notification = notification;
    }

    /**
     * Returns securityPolicy
     * @return securityPolicy
     */
    public String getSecurityPolicy() {
        return securityPolicy;
    }

    /**
     * Sets securityPolicy
     * @param securityPolicy
     */
    public void setSecurityPolicy(String securityPolicy) {
        this.securityPolicy = securityPolicy;
    }

    /**
     * Returns recording
     * @return recording
     */
    public String getRecording() {
        return recording;
    }

    /**
     * Sets recording
     * @param recording
     */
    public void setRecording(String recording) {
        this.recording = recording;
    }

    /**
     * Returns thumbnail
     * @return thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * Sets thumbnail
     * @param thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * Returns thumbnails
     * @return thumbnails
     */
    public List<String> getThumbnails() {
        return thumbnails;
    }

    /**
     * Sets thumbnails
     * @param thumbnails
     */
    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
    }

    /**
     * Returns watermarks
     * @return watermarks
     */
    public Watermarks getWatermarks() {
        return watermarks;
    }

    /**
     * Sets watermarks
     * @param watermarks
     */
    public void setWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
    }

    /**
     * Returns publish
     * @return publish
     */
    public LivePublish getPublish() {
        return publish;
    }

    /**
     * Sets publish
     * @param publish
     */
    public void setPublish(LivePublish publish) {
        this.publish = publish;
    }

    /**
     * Returns play
     * @return play
     */
    public LivePlay getPlay() {
        return play;
    }

    /**
     * Sets play
     * @param play
     */
    public void setPlay(LivePlay play) {
        this.play = play;
    }

    /**
     * Returns presets
     * @return presets
     */
    public Map<String, String> getPresets() {
        return presets;
    }

    /**
     * Sets presets
     * @param presets
     */
    public void setPresets(Map<String, String> presets) {
        this.presets = presets;
    }

    /**
     * Returns streamingStatus
     * @return streamingStatus
     */
    public String getStreamingStatus() {
        return streamingStatus;
    }

    /**
     * Sets streamingStatus
     * @param streamingStatus
     */
    public void setStreamingStatus(String streamingStatus) {
        this.streamingStatus = streamingStatus;
    }

    /**
     * Returns statistics
     * @return statistics
     */
    public StreamingStatistics getStatistics() {
        return statistics;
    }

    /**
     * Sets statistics
     * @param statistics
     */
    public void setStatistics(StreamingStatistics statistics) {
        this.statistics = statistics;
    }

    /**
     * Returns audit
     * @return audit
     */
    public String getAudit() {
        return audit;
    }

    /**
     * Sets audit
     * @param audit
     */
    public void setAudit(String audit) {
        this.audit = audit;
    }

    /**
     * Returns destinationPushUrl
     * @return destinationPushUrl
     */
    public String getDestinationPushUrl() {
        return destinationPushUrl;
    }

    /**
     * Sets destinationPushUrl
     * @param destinationPushUrl
     */
    public void setDestinationPushUrl(String destinationPushUrl) {
        this.destinationPushUrl = destinationPushUrl;
    }

    /**
     * Represents statistics of a streaming stream
     */
    public static class StreamingStatistics implements Serializable {
        private Long bandwidthInBps = null;

        private Long playCount = null;

        /**
         * Returns bandwidthInBps
         * @return bandwidthInBps
         */
        public Long getBandwidthInBps() {
            return bandwidthInBps;
        }

        /**
         * Sets bandwidthInBps
         * @param bandwidthInBps
         */
        public void setBandwidthInBps(Long bandwidthInBps) {
            this.bandwidthInBps = bandwidthInBps;
        }

        /**
         * Returns playCount
         * @return playCount
         */
        public Long getPlayCount() {
            return playCount;
        }

        /**
         * Sets playCount
         * @param playCount
         */
        public void setPlayCount(Long playCount) {
            this.playCount = playCount;
        }

        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("class StreamingStatistics {\n");
            sb.append("    bandwidthInBps: ").append(bandwidthInBps).append("\n");
            sb.append("    playCount: ").append(playCount).append("\n");
            sb.append("}\n");
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class CreateSessionResponse {\n");
        sb.append("    sessionId: ").append(sessionId).append("\n");
        sb.append("    playDomain: ").append(playDomain).append("\n");
        sb.append("    app: ").append(app).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    preset: ").append(preset).append("\n");
        sb.append("    presets: ").append(presets).append("\n");
        sb.append("    createTime: ").append(createTime).append("\n");
        sb.append("    status: ").append(status).append("\n");
        sb.append("    streamingStatus: ").append(streamingStatus).append("\n");
        sb.append("    notification: ").append(notification).append("\n");
        sb.append("    securityPolicy: ").append(securityPolicy).append("\n");
        sb.append("    audit: ").append(audit).append("\n");
        sb.append("    recording: ").append(recording).append("\n");
        sb.append("    thumbnail: ").append(thumbnail).append("\n");
        sb.append("    thumbnails: ").append(thumbnails).append("\n");
        sb.append("    watermarks: ").append(watermarks).append("\n");
        sb.append("    scene: ").append(scene).append("\n");
        sb.append("    publish: ").append(publish).append("\n");
        sb.append("    play: ").append(play).append("\n");
        sb.append("    StreamingStatistics ").append(statistics).append("\n");
        sb.append("    destinationPushUrl").append(destinationPushUrl).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

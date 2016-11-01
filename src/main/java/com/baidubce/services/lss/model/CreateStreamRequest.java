package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

/**
 * Created by wuyafei on 16/10/14.
 */
public class CreateStreamRequest extends AbstractBceRequest {

    private String playDomain = null;

    private String app = null;

    private String description = null;

    private String notification = null;

    private String recording = null;

    private String thumbnail = null;

    private List<String> thumbnails = null;

    private Watermarks watermarks = null;

    private PublishInfo publish = null;

    private Scene scene;

    public String getPlayDomain() {
        return playDomain;
    }

    public void setPlayDomain(String playDomain) {
        this.playDomain = playDomain;
    }

    public CreateStreamRequest withPlayDomain(String playDomain) {
        this.playDomain = playDomain;
        return this;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public CreateStreamRequest withApp(String app) {
        this.app = app;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CreateStreamRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public CreateStreamRequest withNotification(String notification) {
        this.notification = notification;
        return this;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }

    public CreateStreamRequest withRecording(String recording) {
        this.recording = recording;
        return this;
    }

    public PublishInfo getPublish() {
        return publish;
    }

    public void setPublish(PublishInfo publish) {
        this.publish = publish;
    }

    public CreateStreamRequest withPublish(PublishInfo publish) {
        this.publish = publish;
        return this;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CreateStreamRequest withThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    public List<String> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public CreateStreamRequest withThumbnails(List<String> thumbnails) {
        this.thumbnails = thumbnails;
        return this;
    }

    public Watermarks getWatermarks() {
        return watermarks;
    }

    public void setWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
    }

    public CreateStreamRequest withWatermarks(Watermarks watermarks) {
        this.watermarks = watermarks;
        return this;
    }

    public CreateStreamRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public CreateStreamRequest withScene(Scene scene) {
        this.scene = scene;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class CreateStreamRequest {\n");
        sb.append("    playDomain: ").append(playDomain).append("\n");
        sb.append("    app: ").append(app).append("\n");
        sb.append("    description: ").append(description).append("\n");
        sb.append("    notification: ").append(notification).append("\n");
        sb.append("    recording: ").append(recording).append("\n");
        sb.append("    thumbnail: ").append(thumbnail).append("\n");
        sb.append("    thumbnails: ").append(thumbnails).append("\n");
        sb.append("    watermarks: ").append(watermarks).append("\n");
        sb.append("    publish: ").append(publish).append("\n");
        sb.append("    scene: ").append(scene).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    public static class PublishInfo {
        private String pushStream;

        public String getPushStream() {
            return this.pushStream;
        }

        public void setPushStream(String pushStream) {
            this.pushStream = pushStream;
        }

        public PublishInfo withPushStream(String pushStream) {
            this.pushStream = pushStream;
            return this;
        }
        
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append("class PublishInfo {\n");
            sb.append("    pushStream: ").append(pushStream).append("\n");
            sb.append("}\n");
            return sb.toString();
        }
    }

    public enum Scene {
        normal, game, portrait
    }
}

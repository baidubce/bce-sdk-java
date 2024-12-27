package com.baidubce.services.vod.v2.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.util.List;

public class ComposeCreateRequest extends AbstractBceRequest {

    private Timeline timeline;

    private ComposeOutput output;

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public ComposeOutput getOutput() {
        return output;
    }

    public void setOutput(ComposeOutput output) {
        this.output = output;
    }

    public static class Timeline {

        private List<VideoTrack> videoTracks;
        private List<AudioTrack> audioTracks;

        public Timeline() {
        }

        public List<VideoTrack> getVideoTracks() {
            return videoTracks;
        }

        public void setVideoTracks(List<VideoTrack> videoTracks) {
            this.videoTracks = videoTracks;
        }

        public List<AudioTrack> getAudioTracks() {
            return audioTracks;
        }

        public void setAudioTracks(List<AudioTrack> audioTracks) {
            this.audioTracks = audioTracks;
        }

    }

    public static class ComposeOutput {

        private String fileName;
        private String videoCodec = "h264";
        private String audioCodec = "aac";
        private int width = 1920;
        private int height = 1080;
        private float frameRate = 30.0f;
        private int audioSampleRateInHz = 44100;
        private int audioChannels = 2;
        private String compressionType;
        private int gop = 125;

        public ComposeOutput() {
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getVideoCodec() {
            return videoCodec;
        }

        public void setVideoCodec(String videoCodec) {
            this.videoCodec = videoCodec;
        }

        public String getAudioCodec() {
            return audioCodec;
        }

        public void setAudioCodec(String audioCodec) {
            this.audioCodec = audioCodec;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public float getFrameRate() {
            return frameRate;
        }

        public void setFrameRate(float frameRate) {
            this.frameRate = frameRate;
        }

        public int getAudioSampleRateInHz() {
            return audioSampleRateInHz;
        }

        public void setAudioSampleRateInHz(int audioSampleRateInHz) {
            this.audioSampleRateInHz = audioSampleRateInHz;
        }

        public int getAudioChannels() {
            return audioChannels;
        }

        public void setAudioChannels(int audioChannels) {
            this.audioChannels = audioChannels;
        }

        public String getCompressionType() {
            return compressionType;
        }

        public void setCompressionType(String compressionType) {
            this.compressionType = compressionType;
        }

        public int getGop() {
            return gop;
        }

        public void setGop(int gop) {
            this.gop = gop;
        }

    }

    public static class VideoTrack {

        private List<VideoTrackItem> videoItems;

        public VideoTrack() {
        }

        public List<VideoTrackItem> getVideoItems() {
            return videoItems;
        }

        public void setVideoItems(List<VideoTrackItem> videoItems) {
            this.videoItems = videoItems;
        }

    }

    public static class AudioTrack {

        private List<AudioTrackItem> audioItems;

        public AudioTrack() {
        }

        public List<AudioTrackItem> getAudioItems() {
            return audioItems;
        }

        public void setAudioItems(List<AudioTrackItem> audioItems) {
            this.audioItems = audioItems;
        }

    }

    public static class VideoTrackItem {

        private String mediaId;
        private String sourceUrl;
        private String type;
        private double start;
        private double duration;
        private double showStart;
        private double showDuration;
        private List<AudioOperation> audioOperations;
        private List<ImageOperation> imageOperations;
        private double xpos;
        private double ypos;
        private double width;
        private double height;

        public VideoTrackItem() {
        }

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getStart() {
            return start;
        }

        public void setStart(double start) {
            this.start = start;
        }

        public double getDuration() {
            return duration;
        }

        public void setDuration(double duration) {
            this.duration = duration;
        }

        public double getShowStart() {
            return showStart;
        }

        public void setShowStart(double showStart) {
            this.showStart = showStart;
        }

        public double getShowDuration() {
            return showDuration;
        }

        public void setShowDuration(double showDuration) {
            this.showDuration = showDuration;
        }

        public List<AudioOperation> getAudioOperations() {
            return audioOperations;
        }

        public void setAudioOperations(List<AudioOperation> audioOperations) {
            this.audioOperations = audioOperations;
        }

        public List<ImageOperation> getImageOperations() {
            return imageOperations;
        }

        public void setImageOperations(List<ImageOperation> imageOperations) {
            this.imageOperations = imageOperations;
        }

        public double getXpos() {
            return xpos;
        }

        public void setXpos(double xpos) {
            this.xpos = xpos;
        }

        public double getYpos() {
            return ypos;
        }

        public void setYpos(double ypos) {
            this.ypos = ypos;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

    }

    public static class AudioTrackItem {

        private String mediaId;
        private String sourceUrl;
        private double start;
        private double duration;
        private double showStart;
        private double showDuration;
        private List<AudioOperation> audioOperations;

        public AudioTrackItem() {
        }

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public double getStart() {
            return start;
        }

        public void setStart(double start) {
            this.start = start;
        }

        public double getDuration() {
            return duration;
        }

        public void setDuration(double duration) {
            this.duration = duration;
        }

        public double getShowStart() {
            return showStart;
        }

        public void setShowStart(double showStart) {
            this.showStart = showStart;
        }

        public double getShowDuration() {
            return showDuration;
        }

        public void setShowDuration(double showDuration) {
            this.showDuration = showDuration;
        }

        public List<AudioOperation> getAudioOperations() {
            return audioOperations;
        }

        public void setAudioOperations(List<AudioOperation> audioOperations) {
            this.audioOperations = audioOperations;
        }

    }

    public static class AudioOperation {

        private double volume = 1.0d;
        private double speed = 1.0d;
        private String paddingMode = "loop";

        public AudioOperation() {
        }

        public double getVolume() {
            return volume;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public String getPaddingMode() {
            return paddingMode;
        }

        public void setPaddingMode(String paddingMode) {
            this.paddingMode = paddingMode;
        }

    }

    public static class ImageOperation {

        private int rotate = 0;
        private String mirror;
        private double speed = 1.0d;
        private String paddingMode = "loop";
        private CropOperation crop;

        public ImageOperation() {
        }

        public int getRotate() {
            return rotate;
        }

        public void setRotate(int rotate) {
            this.rotate = rotate;
        }

        public String getMirror() {
            return mirror;
        }

        public void setMirror(String mirror) {
            this.mirror = mirror;
        }

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public String getPaddingMode() {
            return paddingMode;
        }

        public void setPaddingMode(String paddingMode) {
            this.paddingMode = paddingMode;
        }

        public CropOperation getCrop() {
            return crop;
        }

        public void setCrop(CropOperation crop) {
            this.crop = crop;
        }

    }

    public static class CropOperation {

        private double xpos;
        private double ypos;
        private double width;
        private double height;

        public CropOperation() {
        }

        public double getXpos() {
            return xpos;
        }

        public void setXpos(double xpos) {
            this.xpos = xpos;
        }

        public double getYpos() {
            return ypos;
        }

        public void setYpos(double ypos) {
            this.ypos = ypos;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }

    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}

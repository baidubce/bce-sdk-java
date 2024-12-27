package com.baidubce.services.vod.v2.model;

public class MediaMetadataInfo {

    private String eTag;
    private Long fileSizeInByte;
    private String type;
    private String container;
    private Integer durationInSecond;
    private Integer durationInMillisecond;
    private VideoCodec video;
    private AudioCodec audio;
    private BlackBorderArea blackBorderArea;

    public static class VideoCodec {

        private String codec;
        private Integer heightInPixel;
        private Integer widthInPixel;
        private Integer bitRateInBps;
        private Float frameRate;
        private Integer rotate;
        private String dar;

        public String getCodec() {
            return codec;
        }

        public void setCodec(String codec) {
            this.codec = codec;
        }

        public Integer getHeightInPixel() {
            return heightInPixel;
        }

        public void setHeightInPixel(Integer heightInPixel) {
            this.heightInPixel = heightInPixel;
        }

        public Integer getWidthInPixel() {
            return widthInPixel;
        }

        public void setWidthInPixel(Integer widthInPixel) {
            this.widthInPixel = widthInPixel;
        }

        public Integer getBitRateInBps() {
            return bitRateInBps;
        }

        public void setBitRateInBps(Integer bitRateInBps) {
            this.bitRateInBps = bitRateInBps;
        }

        public Float getFrameRate() {
            return frameRate;
        }

        public void setFrameRate(Float frameRate) {
            this.frameRate = frameRate;
        }

        public Integer getRotate() {
            return rotate;
        }

        public void setRotate(Integer rotate) {
            this.rotate = rotate;
        }

        public String getDar() {
            return dar;
        }

        public void setDar(String dar) {
            this.dar = dar;
        }

    }

    public static class AudioCodec {

        private String codec;
        private Integer channels;
        private Integer sampleRateInHz;
        private Integer bitRateInBps;

        public String getCodec() {
            return codec;
        }

        public void setCodec(String codec) {
            this.codec = codec;
        }

        public Integer getChannels() {
            return channels;
        }

        public void setChannels(Integer channels) {
            this.channels = channels;
        }

        public Integer getSampleRateInHz() {
            return sampleRateInHz;
        }

        public void setSampleRateInHz(Integer sampleRateInHz) {
            this.sampleRateInHz = sampleRateInHz;
        }

        public Integer getBitRateInBps() {
            return bitRateInBps;
        }

        public void setBitRateInBps(Integer bitRateInBps) {
            this.bitRateInBps = bitRateInBps;
        }

    }

    public static class BlackBorderArea {

        private Integer x;
        private Integer y;
        private Integer width;
        private Integer height;

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }

    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public Long getFileSizeInByte() {
        return fileSizeInByte;
    }

    public void setFileSizeInByte(Long fileSizeInByte) {
        this.fileSizeInByte = fileSizeInByte;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContainer() {
        return container;
    }

    public void setContainer(String container) {
        this.container = container;
    }

    public Integer getDurationInSecond() {
        return durationInSecond;
    }

    public void setDurationInSecond(Integer durationInSecond) {
        this.durationInSecond = durationInSecond;
    }

    public Integer getDurationInMillisecond() {
        return durationInMillisecond;
    }

    public void setDurationInMillisecond(Integer durationInMillisecond) {
        this.durationInMillisecond = durationInMillisecond;
    }

    public VideoCodec getVideo() {
        return video;
    }

    public void setVideo(VideoCodec video) {
        this.video = video;
    }

    public AudioCodec getAudio() {
        return audio;
    }

    public void setAudio(AudioCodec audio) {
        this.audio = audio;
    }

    public BlackBorderArea getBlackBorderArea() {
        return blackBorderArea;
    }

    public void setBlackBorderArea(BlackBorderArea blackBorderArea) {
        this.blackBorderArea = blackBorderArea;
    }

}

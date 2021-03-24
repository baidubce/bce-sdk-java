package com.baidubce.services.media.model;

public class JobOutputInfoVideo {
    /**
     * output video duration in seconds
     */
    private Integer durationInSeconds;

    /**
     * output video file size in kiloByte
     */
    private Double sizeInKiloByte;

    /**
     * output video width in pixel
     */
    private Integer widthInPixel;

    /**
     * output video geight in pixel
     */
    private Integer heightInPixel;

    /**
     * output video frameRate
     */
    private Integer frameRate;

    /**
     * output(just mp4 file with video) moov size in Byte
     */
    private Integer mp4MoovSize;

    public Integer getDurationInSeconds() { return durationInSeconds; }

    public void setDurationInSeconds(Integer inputDuration) { this.durationInSeconds = inputDuration; }

    public Double getSizeInKiloByte() { return sizeInKiloByte; }

    public void setSizeInKiloByte(Double inputSize) { this.sizeInKiloByte = inputSize; }

    public Integer getWidthInPixel() { return widthInPixel; }

    public void setWidthInPixel(Integer inputWidth) { this.widthInPixel = inputWidth; }

    public Integer getHeightInPixel() { return heightInPixel; }

    public void setHeightInPixel(Integer inputHeight) { this.heightInPixel = inputHeight; }

    public Integer getFrameRate() { return frameRate; }

    public void setFrameRate(Integer inputFrameRate) { this.frameRate = inputFrameRate; }

    public Integer getMp4MoovSize() { return mp4MoovSize; }

    public void setMp4MoovSize(Integer inputMoovszie) { this.mp4MoovSize = inputMoovszie; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class JobOutputInfoVideo {\n");
        sb.append("    durationInSeconds: ").append(durationInSeconds).append("\n");
        sb.append("    sizeInKiloByte: ").append(sizeInKiloByte).append("\n");
        sb.append("    widthInPixel: ").append(widthInPixel).append("\n");
        sb.append("    heightInPixel: ").append(heightInPixel).append("\n");
        sb.append("    frameRate: ").append(frameRate).append("\n");
        sb.append("    mp4MoovSize: ").append(mp4MoovSize).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

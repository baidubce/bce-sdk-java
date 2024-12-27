package com.baidubce.services.vod.v2.model;

import java.util.List;

public class MediaPresetTaskInput {

    private List<String> presetIds;
    private List<String> watermarkIds;
    private MediaTranscodeDigitalWatermark digitalWatermark;

    public List<String> getPresetIds() {
        return presetIds;
    }

    public void setPresetIds(List<String> presetIds) {
        this.presetIds = presetIds;
    }

    public List<String> getWatermarkIds() {
        return watermarkIds;
    }

    public void setWatermarkIds(List<String> watermarkIds) {
        this.watermarkIds = watermarkIds;
    }

    public MediaTranscodeDigitalWatermark getDigitalWatermark() {
        return digitalWatermark;
    }

    public void setDigitalWatermark(MediaTranscodeDigitalWatermark digitalWatermark) {
        this.digitalWatermark = digitalWatermark;
    }

    public MediaPresetTaskInput withPresetIds(List<String> presetIds) {
        this.presetIds = presetIds;
        return this;
    }

    public MediaPresetTaskInput withWatermarkIds(List<String> watermarkIds) {
        this.watermarkIds = watermarkIds;
        return this;
    }

    public MediaPresetTaskInput withDigitalWatermark(MediaTranscodeDigitalWatermark digitalWatermark) {
        this.digitalWatermark = digitalWatermark;
        return this;
    }

}

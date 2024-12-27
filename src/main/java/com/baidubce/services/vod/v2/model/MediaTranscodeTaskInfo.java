package com.baidubce.services.vod.v2.model;

import java.util.List;

public class MediaTranscodeTaskInfo {

    private String mediaId;
    private String status;
    private String errMsg;
    private MediaTranscodeTaskInput transcodeInput;
    private MediaTranscodeOutputInfo transcodeOutput;

    public static class MediaTranscodeTaskInput {

        private String presetId;
        private List<String> watermarkIds;
        private MediaTranscodeDigitalWatermark digitalWatermark;

        public String getPresetId() {
            return presetId;
        }

        public void setPresetId(String presetId) {
            this.presetId = presetId;
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

    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public MediaTranscodeTaskInput getTranscodeInput() {
        return transcodeInput;
    }

    public void setTranscodeInput(MediaTranscodeTaskInput transcodeInput) {
        this.transcodeInput = transcodeInput;
    }

    public MediaTranscodeOutputInfo getTranscodeOutput() {
        return transcodeOutput;
    }

    public void setTranscodeOutput(MediaTranscodeOutputInfo transcodeOutput) {
        this.transcodeOutput = transcodeOutput;
    }

}

package com.baidubce.services.vod.v2.model;

public class MediaTranscodeOutputMeta {

    private MediaTranscodeOutputVideoCodec video;
    private MediaTranscodeOutputAudioCodec audio;

    public MediaTranscodeOutputVideoCodec getVideo() {
        return video;
    }

    public void setVideo(MediaTranscodeOutputVideoCodec video) {
        this.video = video;
    }

    public MediaTranscodeOutputAudioCodec getAudio() {
        return audio;
    }

    public void setAudio(MediaTranscodeOutputAudioCodec audio) {
        this.audio = audio;
    }

}

package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.vodpro.model.common.UtcTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * The response represents stream source info.
 */
@Data
public class GetStreamSourceInfoResponse extends AbstractBceResponse {

    /**
     * global unique session id
     */
    private String sessionId;

    /**
     * live stream play domain
     */
    private String playDomain;

    /**
     * live stream app
     */
    private String app;

    /**
     * live stream name
     */
    private String stream;

    /**
     * ip of CDN server that push the stream to media server
     */
    private String sourceIP;

    /**
     * client ip that push the stream
     */
    private String publishIP;

    /**
     * quality of the stream, value between [0, 100], high score means good quality
     */
    private Integer score;

    /**
     * timestamp of this stream info
     */
    @UtcTime
    private Date captureTime;

    /**
     * total input bit rate
     */
    private Integer inputBitRateInBps;

    /**
     * video info part
     */
    private Video video;

    /**
     * audio info part
     */
    private Audio audio;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class GetRecordingResponse {\n");
        sb.append("    sessionId: ").append(sessionId).append("\n");
        sb.append("    sourceIp: ").append(sourceIP).append("\n");
        sb.append("    publishIp: ").append(publishIP).append("\n");
        sb.append("    score: ").append(score).append("\n");
        sb.append("    captureTime: ").append(captureTime).append("\n");
        sb.append("    inputBitRateInBps: ").append(inputBitRateInBps).append("\n");
        sb.append("    video: {\n");
        sb.append("        codec: ").append(video.getCodec()).append("\n");
        sb.append("        profile: ").append(video.getProfile()).append("\n");
        sb.append("        level: ").append(video.getLevel()).append("\n");
        sb.append("        widthInPixel: ").append(video.getWidthInPixel()).append("\n");
        sb.append("        heightInPixel: ").append(video.getHeightInPixel()).append("\n");
        sb.append("        frameRate: ").append(video.getFrameRate()).append("\n");
        sb.append("        bitRateInBps: ").append(video.getBitRateInBps()).append("\n");
        sb.append("        realFPS: ").append(video.getRealFPS()).append("\n");
        sb.append("    }");
        sb.append("    audio: \n");
        sb.append("        codec: ").append(audio.getCodec()).append("\n");
        sb.append("        profile: ").append(audio.getProfile()).append("\n");
        sb.append("        sampleRateInHz: ").append(audio.getSampleRateInHz()).append("\n");
        sb.append("        channels: ").append(audio.getChannels()).append("\n");
        sb.append("        bitRateInBps: ").append(audio.getBitRateInBps()).append("\n");
        sb.append("    }");
        sb.append("}\n");
        return sb.toString();
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Video {
        /**
         * video codec
         */
        private String codec;

        /**
         * video profile
         */
        private String profile;

        /**
         * video level
         */
        private String level;

        /**
         * video width
         */
        private Integer widthInPixel;

        /**
         * video height
         */
        private Integer heightInPixel;

        /**
         * video standard frame rate
         */
        private Float frameRate;

        /**
         * video input bit rate
         */
        private Integer bitRateInBps;

        /**
         * video real-time frame rate
         */
        private Float realFPS;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Audio {
        /**
         * audio codec
         */
        private String codec;

        /**
         * audio profile
         */
        private String profile;

        /**
         * audio sampling rate
         */
        private Integer sampleRateInHz;

        /**
         * audio channels number
         */
        private Integer channels;

        /**
         * audio input bit rate
         */
        private Integer bitRateInBps;
    }
}

package com.baidubce.services.media.model;

public class JobOutputInfo {
    /**
     * output job video info
     */
    private JobOutputInfoVideo video = null;

    /**
     * output job audio info
     */
    private JobOutputInfoAudio audio = null;

    public JobOutputInfoVideo getVideo() { return video; }

    public void setVideo(JobOutputInfoVideo inputVideo) { this.video = inputVideo; }

    public JobOutputInfoAudio getAudio() { return audio; }

    public void setAudio(JobOutputInfoAudio inputAudio) { this.audio = inputAudio; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class JobOutputInfo {\n");
        if (video != null) {
            sb.append("    video: ").append(video).append("\n");
        }
        if (audio != null) {
            sb.append("    audio: ").append(audio).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}

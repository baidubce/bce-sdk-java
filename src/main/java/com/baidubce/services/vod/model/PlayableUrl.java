package com.baidubce.services.vod.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayableUrl {

    /*
     * The unique ID of media resource managed by VOD service.
     */
    @JsonProperty(value = "media_id")
    private String mediaId;

    /*
     * Media resource's playable URL address.
     */
    private String file;

    private String cover;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayableUrl { \n");
        sb.append("   mediaId = ").append(mediaId).append("\n");
        sb.append("   file = ").append(file).append("\n");
        sb.append("   cover = ").append(cover).append("\n");
        sb.append("  }\n");
        return sb.toString();
    }

}

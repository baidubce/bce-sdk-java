package com.baidubce.services.vod.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class InternalCreateMediaRequest extends AbstractBceRequest {

    /*
     * The unique ID of media resource managed by VOD service.
     */
    @JsonIgnore
    private String mediaId;

    /*
     * The title string of media resource
     */
    private String title;

    /*
     * The description string of media resource
     */
    private String description;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("InternalCreateMediaRequest { \n");
        sb.append("  mediaId = ").append(mediaId).append("\n");
        sb.append("  title = ").append(title).append("\n");
        sb.append("  description = ").append(description).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public InternalCreateMediaRequest withMediaId(String mediaId) {
        this.mediaId = mediaId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public InternalCreateMediaRequest withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InternalCreateMediaRequest withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        setRequestCredentials(credentials);
        return this;
    }

}

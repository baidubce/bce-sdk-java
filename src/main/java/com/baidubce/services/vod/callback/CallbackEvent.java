package com.baidubce.services.vod.callback;

import java.util.Date;

/**
 * Callback event that contains the event type and specific event information.
 */
public class CallbackEvent {

    /**
     * The unique identifier for the event.
     */
    private String eventId;

    /**
     * The type of the event.
     */
    private String eventType;

    /**
     * The time when the event occurred.
     */
    private Date eventTime;

    /**
     * Event representing the completion of a media upload.
     */
    private MediaUploadCompleteEvent mediaUploadCompleteEvent;

    /**
     * Event representing the completion of a media fetch.
     */
    private MediaFetchCompleteEvent mediaFetchCompleteEvent;

    /**
     * Event representing the completion of media transcoding.
     */
    private MediaTranscodeCompleteEvent mediaTranscodeCompleteEvent;

    /**
     * Event representing the completion of media thumbnail generation.
     */
    private MediaThumbnailCompleteEvent mediaThumbnailCompleteEvent;

    /**
     * Event representing the completion of media analysis.
     */
    private MediaAnalysisCompleteEvent mediaAnalysisCompleteEvent;

    /**
     * Event representing the completion of media regulation.
     */
    private MediaRegulationCompleteEvent mediaRegulationCompleteEvent;

    /**
     * Event representing a change in the status of a compose task.
     */
    private ComposeTaskStatusChangeEvent composeTaskStatusChangeEvent;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public MediaUploadCompleteEvent getMediaUploadCompleteEvent() {
        return mediaUploadCompleteEvent;
    }

    public void setMediaUploadCompleteEvent(MediaUploadCompleteEvent mediaUploadCompleteEvent) {
        this.mediaUploadCompleteEvent = mediaUploadCompleteEvent;
    }

    public MediaFetchCompleteEvent getMediaFetchCompleteEvent() {
        return mediaFetchCompleteEvent;
    }

    public void setMediaFetchCompleteEvent(MediaFetchCompleteEvent mediaFetchCompleteEvent) {
        this.mediaFetchCompleteEvent = mediaFetchCompleteEvent;
    }

    public MediaTranscodeCompleteEvent getMediaTranscodeCompleteEvent() {
        return mediaTranscodeCompleteEvent;
    }

    public void setMediaTranscodeCompleteEvent(MediaTranscodeCompleteEvent mediaTranscodeCompleteEvent) {
        this.mediaTranscodeCompleteEvent = mediaTranscodeCompleteEvent;
    }

    public MediaThumbnailCompleteEvent getMediaThumbnailCompleteEvent() {
        return mediaThumbnailCompleteEvent;
    }

    public void setMediaThumbnailCompleteEvent(MediaThumbnailCompleteEvent mediaThumbnailCompleteEvent) {
        this.mediaThumbnailCompleteEvent = mediaThumbnailCompleteEvent;
    }

    public MediaAnalysisCompleteEvent getMediaAnalysisCompleteEvent() {
        return mediaAnalysisCompleteEvent;
    }

    public void setMediaAnalysisCompleteEvent(MediaAnalysisCompleteEvent mediaAnalysisCompleteEvent) {
        this.mediaAnalysisCompleteEvent = mediaAnalysisCompleteEvent;
    }

    public MediaRegulationCompleteEvent getMediaRegulationCompleteEvent() {
        return mediaRegulationCompleteEvent;
    }

    public void setMediaRegulationCompleteEvent(MediaRegulationCompleteEvent mediaRegulationCompleteEvent) {
        this.mediaRegulationCompleteEvent = mediaRegulationCompleteEvent;
    }

    public ComposeTaskStatusChangeEvent getComposeTaskStatusChangeEvent() {
        return composeTaskStatusChangeEvent;
    }

    public void setComposeTaskStatusChangeEvent(ComposeTaskStatusChangeEvent composeTaskStatusChangeEvent) {
        this.composeTaskStatusChangeEvent = composeTaskStatusChangeEvent;
    }

}

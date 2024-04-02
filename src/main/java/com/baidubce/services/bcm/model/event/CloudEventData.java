package com.baidubce.services.bcm.model.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by dongjiawei on 2023/12/13.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudEventData {

    private String accountId;

    /**
     * Name of the cloud service to which the event belongs
     */
    private String serviceName;

    /**
     * Region to which the event belongs, default is "global"
     */
    private String region = "global";

    /**
     * Type of the instance to which the event belongs,
     * used to distinguish different types of cloud resource objects, default is "instance"
     */
    private String resourceType;

    /**
     * Instance Id of the event instance
     */
    private String resourceId;

    /**
     * Event ID
     */
    private String eventId;

    /**
     * Event type
     */
    private String eventType;

    /**
     * Event level, [NOTICE/WARNING/MAJOR/CRITICAL]
     */
    private EventLevel eventLevel;

    /**
     * Event alias
     */
    private String eventAlias;

    /**
     * Timestamp of the event occurrence, in UTC format
     */
    private String timestamp;

    /**
     * Event content
     */
    private String content;

}
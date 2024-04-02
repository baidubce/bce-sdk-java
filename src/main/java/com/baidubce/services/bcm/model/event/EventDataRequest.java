package com.baidubce.services.bcm.model.event;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * Created by dongjiawei on 2023/12/12.
 */
@Data
public class EventDataRequest extends AbstractBceRequest {
    /**
     * Page number for pagination
     */
    private Integer pageNo = 1;
    /**
     * Page size for pagination
     */
    private Integer pageSize = 10;
    /**
     * Filter condition for the event occurrence time, represented in UTC date, e.g., 2019-01-01T00:00:00Z
     */
    private String startTime;
    /**
     * Filter condition for the event end time, represented in UTC date, e.g., 2019-01-01T00:00:00Z
     */
    private String endTime;
    /**
     * Account ID
     */
    private String accountId;
    /**
     * Whether to sort in ascending order by event occurrence time, default is descending (false)
     */
    private Boolean ascending;
    /**
     * Name of the cloud service to which the event belongs
     */
    private String scope;
    /**
     * Filter condition for the region to which the event belongs
     */
    private String region;
    /**
     * Filter condition for the event level, including: CRITICAL, MAJOR, WARNING, NOTICE
     */
    private EventLevel eventLevel;
    /**
     * Exact search for event name
     */
    private String eventName;
    /**
     * Fuzzy search for event alias
     */
    private String eventAlias;
    /**
     * Product subtype to which the event-affected instance belongs, e.g., BCC subtype: Instance
     */
    private String resourceType;
    /**
     * Instance Id of the event instance, e.g., BCC instance: i-SyZeMxxx
     */
    private String resourceId;
    /**
     * Unique ID of the event. e.g., 999bbc21-2061-49e6-bb2a-fd3e6c5xxxxx
     */
    private String eventId;

    @Override
    public EventDataRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
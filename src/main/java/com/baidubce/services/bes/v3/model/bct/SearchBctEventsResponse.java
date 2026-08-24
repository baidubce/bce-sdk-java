package com.baidubce.services.bes.v3.model.bct;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class SearchBctEventsResponse extends AbstractBceResponse {
    private Integer pageSize;
    private List<BctEvent> data;
    private String nextMarker;
    private Boolean truncated;

    @Data
    public static class BctEvent {
        private String eventType;
        private String eventSource;
        private String eventName;
        private Long eventTimeInMilliseconds;
        private String eventTime;
        private String userIpAddress;
        private String userAgent;
        private String regionId;
        private String requestId;
        private String orderId;
        private String apiVersion;
        private String description;
        private String errorCode;
        private String errorMessage;
        private Boolean success;
        private BctUserIdentity userIdentity;
    }

    @Data
    public static class BctUserIdentity {
        private String iamDomainId;
        private String iamUserId;
        private String loginUserId;
        private String userDisplayName;
        private String accesskey;
    }
}

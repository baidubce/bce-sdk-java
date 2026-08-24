package com.baidubce.services.bes.v3.model.audit;

import com.baidubce.model.AbstractBceResponse;

import lombok.Data;

import java.util.List;

@Data
public class SearchAuditEventsResponse extends AbstractBceResponse {
    private List<AuditEvent> logs;
    private String cursor;

    @Data
    public static class AuditEvent {
        private String category;
        private String timestamp;
        private String content;
    }
}

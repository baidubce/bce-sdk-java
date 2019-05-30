package com.baidubce.services.cvca.model;

import com.baidubce.model.GenericAccountRequest;

/**
 * chat request
 *
 * @author wujinlin
 */
public class ChatRequest extends GenericAccountRequest {
    private String query;
    private String sessionId;
    private String customerId;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}

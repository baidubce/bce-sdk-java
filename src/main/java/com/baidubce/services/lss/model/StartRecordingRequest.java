package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by shidaiting01 on 2016/3/10.
 */
public class StartRecordingRequest extends AbstractBceRequest {
    private String sessionId = null;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public StartRecordingRequest withSessionId(String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public StartRecordingRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class StartRecordingRequest {\n");
        sb.append("    sessionId: ").append(sessionId).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

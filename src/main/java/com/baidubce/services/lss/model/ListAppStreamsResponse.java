package com.baidubce.services.lss.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.List;

/**
 * Created by wuyafei on 16/6/28.
 */
public class ListAppStreamsResponse extends AbstractBceResponse {
    private List<LiveSession> sessions = null;

    public List<LiveSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<LiveSession> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ListAppStreamsResponse {\n");
        sb.append("    streams: ").append(sessions).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

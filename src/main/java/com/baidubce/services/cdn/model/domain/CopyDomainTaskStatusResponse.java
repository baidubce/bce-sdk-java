package com.baidubce.services.cdn.model.domain;


import java.util.List;

public class CopyDomainTaskStatusResponse extends CommonResponse {
    private List<String> messages;

    public CopyDomainTaskStatusResponse() {
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}

package com.baidubce.services.iothub.model;

public class MqttUsage {

    private long publishReceived;

    private long publishSent;

    public long getPublishReceived() {
        return publishReceived;
    }

    public void setPublishReceived(long value) {
        publishReceived = value;
    }

    public long getPublishSent() {
        return publishSent;
    }

    public void setPublishSent(long value) {
        publishSent = value;
    }

    public long getTotal() {
        return publishReceived + publishSent;
    }
}

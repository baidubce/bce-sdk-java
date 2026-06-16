package com.baidubce.services.et.model;

import lombok.ToString;

@ToString
public class CreateEtChannelBfdRequest extends EtChannelIdRequest {
    private int sendInterval;
    private int receivInterval;
    private int detectMultiplier;

    public int getSendInterval() {
        return sendInterval;
    }

    public void setSendInterval(int sendInterval) {
        this.sendInterval = sendInterval;
    }

    public int getReceivInterval() {
        return receivInterval;
    }

    public void setReceivInterval(int receivInterval) {
        this.receivInterval = receivInterval;
    }

    public int getDetectMultiplier() {
        return detectMultiplier;
    }

    public void setDetectMultiplier(int detectMultiplier) {
        this.detectMultiplier = detectMultiplier;
    }
}

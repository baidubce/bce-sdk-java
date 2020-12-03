package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

public class OriginProtocol extends JsonObject {
    private String value;

    public OriginProtocol() {
    }

    public OriginProtocol(String value) {
        this.value = value;
    }

    public OriginProtocol withValue(String value) {
        this.value = value;
        return this;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

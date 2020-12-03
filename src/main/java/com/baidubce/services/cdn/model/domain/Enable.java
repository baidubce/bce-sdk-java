package com.baidubce.services.cdn.model.domain;

import com.baidubce.services.cdn.model.JsonObject;

public class Enable extends JsonObject {
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Enable withEnable(boolean enable) {
        setEnable(enable);
        return this;
    }
}

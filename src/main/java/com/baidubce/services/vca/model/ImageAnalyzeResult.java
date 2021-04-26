package com.baidubce.services.vca.model;

import java.io.Serializable;

public class ImageAnalyzeResult implements Serializable {
    String type;
    String data;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ImageAnalyzeResult{");
        sb.append("type='").append(type).append('\'');
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

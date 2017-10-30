package com.baidubce.services.vodpro.model.adaptor.request;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.vodpro.model.adaptor.RequestType;

/**
 * Created on 17/10/10
 *
 * @author liumin08
 */
public class TaskStartRequest extends AbstractBceRequest {
    private String url;
    private String description;
    private String preset;
    private RequestType type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreset() {
        return preset;
    }

    public void setPreset(String preset) {
        this.preset = preset;
    }

    public RequestType getType() {
        return type;
    }

    public void setType(RequestType type) {
        this.type = type;
    }

    @Override
    public TaskStartRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

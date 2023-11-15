package com.baidubce.services.evs.model;

import java.util.List;

public class CallbackUpdateRequest extends EvsBaseRequest {

    private static final long serialVersionUID = -2343543510056058120L;

    protected boolean enabled;

    protected String endpoint;

    protected boolean authEnabled;

    protected String key;

    /**
     * Type Support：
     * ON_PUBLISH、ON_UNPUBLISH、ON_SNAPSHOT、ON_RECORD、
     * ON_PLAY、ON_STOP、ON_REGISTER、ON_UNREGISTER、ON_BASE_AI
     */
    private List<String> types;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public boolean isAuthEnabled() {
        return authEnabled;
    }

    public void setAuthEnabled(boolean authEnabled) {
        this.authEnabled = authEnabled;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CallbackUpdateRequest that = (CallbackUpdateRequest) o;

        if (enabled != that.enabled) {
            return false;
        }
        if (authEnabled != that.authEnabled) {
            return false;
        }
        if (endpoint != null ? !endpoint.equals(that.endpoint) : that.endpoint != null) {
            return false;
        }
        if (key != null ? !key.equals(that.key) : that.key != null) {
            return false;
        }
        return types != null ? types.equals(that.types) : that.types == null;
    }

    @Override
    public int hashCode() {
        int result = (enabled ? 1 : 0);
        result = 31 * result + (endpoint != null ? endpoint.hashCode() : 0);
        result = 31 * result + (authEnabled ? 1 : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (types != null ? types.hashCode() : 0);
        return result;
    }
}
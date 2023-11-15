package com.baidubce.services.evs.model;

import java.io.Serializable;

public class EvsAuth extends SameAsSpace implements Serializable {

    private static final long serialVersionUID = -2716207059185435020L;

    private boolean enabled;

    private String key;

    private int expire;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EvsAuth that = (EvsAuth) o;

        if (enabled != that.enabled) {
            return false;
        }
        if (expire != that.expire) {
            return false;
        }
        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        int result = (enabled ? 1 : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + expire;
        return result;
    }

    @Override
    public String toString() {
        return "EvsAuthRequest{" +
                "enabled=" + enabled +
                ", key='" + key + '\'' +
                ", expire=" + expire +
                '}';
    }
}

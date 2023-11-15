package com.baidubce.services.evs.model;

public class Thumbnail extends SameAsSpace {

    private static final long serialVersionUID = -5678090094416783791L;

    private boolean enabled;

    private int interval;

    private boolean authEnabled;

    private int authExpire;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isAuthEnabled() {
        return authEnabled;
    }

    public void setAuthEnabled(boolean authEnabled) {
        this.authEnabled = authEnabled;
    }

    public int getAuthExpire() {
        return authExpire;
    }

    public void setAuthExpire(int authExpire) {
        this.authExpire = authExpire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Thumbnail thumbnail = (Thumbnail) o;

        if (enabled != thumbnail.enabled) {
            return false;
        }
        if (interval != thumbnail.interval) {
            return false;
        }
        if (authEnabled != thumbnail.authEnabled) {
            return false;
        }
        return authExpire == thumbnail.authExpire;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + interval;
        result = 31 * result + (authEnabled ? 1 : 0);
        result = 31 * result + authExpire;
        return result;
    }

    @Override
    public String toString() {
        return "Thumbnail{" +
                "enabled=" + enabled +
                ", interval=" + interval +
                ", authEnabled=" + authEnabled +
                ", authExpire=" + authExpire +
                "} " + super.toString();
    }
}

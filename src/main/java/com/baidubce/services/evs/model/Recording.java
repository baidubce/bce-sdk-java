package com.baidubce.services.evs.model;

public class Recording extends SameAsSpace {

    private static final long serialVersionUID = -7058120858705117101L;

    private boolean enabled;

    private int duration;

    /**
     * Support: MP4、FLV、M3U8
     */
    private String format;

    private boolean authEnabled;

    private int authExpire;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
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

        Recording recording = (Recording) o;

        if (enabled != recording.enabled) {
            return false;
        }
        if (duration != recording.duration) {
            return false;
        }
        if (authEnabled != recording.authEnabled) {
            return false;
        }
        if (authExpire != recording.authExpire) {
            return false;
        }
        return format != null ? format.equals(recording.format) : recording.format == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + duration;
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (authEnabled ? 1 : 0);
        result = 31 * result + authExpire;
        return result;
    }

    @Override
    public String toString() {
        return "Recording{" +
                "enabled=" + enabled +
                ", duration=" + duration +
                ", format=" + format +
                ", authEnabled=" + authEnabled +
                ", authExpire=" + authExpire +
                "} " + super.toString();
    }

}

package com.baidubce.services.evs.model;

public class TimeShift extends SameAsSpace {

    private static final long serialVersionUID = -6097367406311037557L;

    private boolean enabled;

    private Integer range;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
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

        TimeShift timeShift = (TimeShift) o;

        if (enabled != timeShift.enabled) {
            return false;
        }
        return range != null ? range.equals(timeShift.range) : timeShift.range == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (range != null ? range.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TimeShift{" +
                "enabled=" + enabled +
                ", range=" + range +
                "} " + super.toString();
    }
}

package com.baidubce.services.evs.model;

public class DeviceAiAnalysisListMarkRequest extends EvsBaseRequest {

    private static final long serialVersionUID = -4908751589233017851L;

    private Long begin;

    private Long end;

    private Long marker;

    private int maxSize;

    public Long getMarker() {
        return marker;
    }

    public void setMarker(Long marker) {
        this.marker = marker;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public Long getBegin() {
        return begin;
    }

    public void setBegin(Long begin) {
        this.begin = begin;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceAiAnalysisListMarkRequest that = (DeviceAiAnalysisListMarkRequest) o;

        if (begin != null ? !begin.equals(that.begin) : that.begin != null) {
            return false;
        }
        if (end != null ? !end.equals(that.end) : that.end != null) {
            return false;
        }
        if (maxSize != that.maxSize) {
            return false;
        }
        return marker != null ? marker.equals(that.marker) : that.marker == null;
    }

    @Override
    public int hashCode() {
        int result = begin != null ? begin.hashCode() : 0;
        result = 31 * result + (marker != null ? marker.hashCode() : 0);
        result = 31 * result + maxSize;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceAiAnalysisListMarkRequest{" +
                "begin=" + begin +
                ", end=" + end +
                ", marker=" + marker +
                ", maxSize=" + maxSize +
                "} " + super.toString();
    }
}

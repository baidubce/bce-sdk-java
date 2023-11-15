package com.baidubce.services.evs.model;

import com.baidubce.model.AbstractBceResponse;

import java.io.Serializable;
import java.util.Collection;

public class SpaceMarkListResponse extends AbstractBceResponse implements Serializable {

    private static final long serialVersionUID = -62001972372143996L;

    private Collection<SpaceListResponse> data;

    private Boolean isTruncated;

    private String marker;

    private String nextMarker;

    public Collection<SpaceListResponse> getData() {
        return data;
    }

    public void setData(Collection<SpaceListResponse> data) {
        this.data = data;
    }

    public Boolean getTruncated() {
        return isTruncated;
    }

    public void setTruncated(Boolean truncated) {
        isTruncated = truncated;
    }

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SpaceMarkListResponse that = (SpaceMarkListResponse) o;

        if (data != null ? !data.equals(that.data) : that.data != null) {
            return false;
        }
        if (isTruncated != null ? !isTruncated.equals(that.isTruncated) : that.isTruncated != null) {
            return false;
        }
        if (marker != null ? !marker.equals(that.marker) : that.marker != null) {
            return false;
        }
        return nextMarker != null ? nextMarker.equals(that.nextMarker) : that.nextMarker == null;
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (isTruncated != null ? isTruncated.hashCode() : 0);
        result = 31 * result + (marker != null ? marker.hashCode() : 0);
        result = 31 * result + (nextMarker != null ? nextMarker.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SpaceMarkListResponse{" +
                "data=" + data +
                ", isTruncated=" + isTruncated +
                ", marker='" + marker + '\'' +
                ", nextMarker='" + nextMarker + '\'' +
                "} " + super.toString();
    }
}
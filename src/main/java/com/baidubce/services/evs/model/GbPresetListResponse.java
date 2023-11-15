package com.baidubce.services.evs.model;

import com.baidubce.model.AbstractBceResponse;

import java.io.Serializable;
import java.util.Collection;

public class GbPresetListResponse extends AbstractBceResponse implements Serializable {

    private static final long serialVersionUID = 4170331210179491182L;

    private Collection<GbPresetGetResponse> data;

    public Collection<GbPresetGetResponse> getData() {
        return data;
    }

    public void setData(Collection<GbPresetGetResponse> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GbPresetListResponse that = (GbPresetListResponse) o;

        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
}
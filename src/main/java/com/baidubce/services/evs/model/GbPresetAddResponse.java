package com.baidubce.services.evs.model;

public class GbPresetAddResponse extends EvsBaseResponse {

    private static final long serialVersionUID = -3014110735179084080L;

    private Integer presetId;

    public Integer getPresetId() {
        return presetId;
    }

    public void setPresetId(Integer presetId) {
        this.presetId = presetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GbPresetAddResponse that = (GbPresetAddResponse) o;

        return presetId != null ? presetId.equals(that.presetId) : that.presetId == null;
    }

    @Override
    public int hashCode() {
        return presetId != null ? presetId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GbPresetAddResponse{" +
                "presetId=" + presetId +
                "} " + super.toString();
    }
}
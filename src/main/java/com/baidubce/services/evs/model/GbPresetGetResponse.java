package com.baidubce.services.evs.model;

public class GbPresetGetResponse extends EvsBaseResponse {

    private static final long serialVersionUID = -617473319695278892L;

    private Integer presetId;

    private String presetName;

    private String status;

    public Integer getPresetId() {
        return presetId;
    }

    public void setPresetId(Integer presetId) {
        this.presetId = presetId;
    }

    public String getPresetName() {
        return presetName;
    }

    public void setPresetName(String presetName) {
        this.presetName = presetName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GbPresetGetResponse that = (GbPresetGetResponse) o;

        if (presetId != null ? !presetId.equals(that.presetId) : that.presetId != null) {
            return false;
        }
        if (presetName != null ? !presetName.equals(that.presetName) : that.presetName != null) {
            return false;
        }
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = presetId != null ? presetId.hashCode() : 0;
        result = 31 * result + (presetName != null ? presetName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GbPresetGetResponse{" +
                "presetId=" + presetId +
                ", presetName='" + presetName + '\'' +
                ", status='" + status + '\'' +
                "} " + super.toString();
    }
}
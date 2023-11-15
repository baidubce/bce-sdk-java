package com.baidubce.services.evs.model;

import java.util.List;

public class DeviceChannelListResponse extends EvsBaseResponse {

    private static final long serialVersionUID = -4528924813667199321L;

    private List<DeviceChannelGetResponse> data;

    public List<DeviceChannelGetResponse> getData() {
        return data;
    }

    public void setData(List<DeviceChannelGetResponse> data) {
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

        DeviceChannelListResponse that = (DeviceChannelListResponse) o;

        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DeviceChannelListResponse{" +
                "data=" + data +
                "} " + super.toString();
    }
}

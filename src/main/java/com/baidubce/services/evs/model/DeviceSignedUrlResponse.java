package com.baidubce.services.evs.model;

public class DeviceSignedUrlResponse extends EvsBaseResponse {

    private static final long serialVersionUID = -6701495483373638576L;

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceSignedUrlResponse that = (DeviceSignedUrlResponse) o;

        return url != null ? url.equals(that.url) : that.url == null;
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DeviceChannelSignedUrlResponse{" +
                "url='" + url + '\'' +
                "} " + super.toString();
    }
}

package com.baidubce.services.evs.model;

import java.io.Serializable;

public class DeviceGbProperties implements Serializable {
    private static final long serialVersionUID = 8040264671754338493L;

    private String streamType;
    private String codeStream;

    public String getStreamType() {
        return streamType;
    }

    public void setStreamType(String streamType) {
        this.streamType = streamType;
    }

    public String getCodeStream() {
        return codeStream;
    }

    public void setCodeStream(String codeStream) {
        this.codeStream = codeStream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceGbProperties that = (DeviceGbProperties) o;
        if (streamType != null ? !streamType.equals(that.streamType) : that.streamType != null) {
            return false;
        }
        return codeStream != null ? codeStream.equals(that.codeStream) : that.codeStream == null;
    }

    @Override
    public int hashCode() {
        int result = streamType != null ? streamType.hashCode() : 0;
        result = 31 * result + (codeStream != null ? codeStream.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceGbProperties{" +
                "streamType='" + streamType + '\'' +
                ", codeStream='" + codeStream + '\'' +
                '}';
    }
}

package com.baidubce.services.evs.model;

import java.io.Serializable;

public class DeviceGis implements Serializable {

    private static final long serialVersionUID = -3388008028758290839L;

    private float longitude;

    private float latitude;

    private String name;

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceGis deviceGis = (DeviceGis) o;

        if (Float.compare(deviceGis.longitude, longitude) != 0) {
            return false;
        }
        if (Float.compare(deviceGis.latitude, latitude) != 0) {
            return false;
        }
        return name != null ? name.equals(deviceGis.name) : deviceGis.name == null;
    }

    @Override
    public int hashCode() {
        int result = (longitude != +0.0f ? Float.floatToIntBits(longitude) : 0);
        result = 31 * result + (latitude != +0.0f ? Float.floatToIntBits(latitude) : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceGis{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", name='" + name + '\'' +
                '}';
    }
}
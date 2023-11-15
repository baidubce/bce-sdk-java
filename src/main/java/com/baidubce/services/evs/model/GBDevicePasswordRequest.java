package com.baidubce.services.evs.model;

public class GBDevicePasswordRequest extends EvsBaseRequest {

    private static final long serialVersionUID = -1411396149854367205L;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GBDevicePasswordRequest that = (GBDevicePasswordRequest) o;

        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        return password != null ? password.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GBDevicePasswordRequest{" +
                "password='" + password + '\'' +
                "} " + super.toString();
    }
}

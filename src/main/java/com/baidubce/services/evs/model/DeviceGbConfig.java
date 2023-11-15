package com.baidubce.services.evs.model;

import java.io.Serializable;

public class DeviceGbConfig implements Serializable {

    private static final long serialVersionUID = -1408137063853326418L;

    /**
     * Support：IPC、NVR
     */
    private String platform;

    private String gbId;

    private String username;

    private String password;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getGbId() {
        return gbId;
    }

    public void setGbId(String gbId) {
        this.gbId = gbId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

        DeviceGbConfig that = (DeviceGbConfig) o;

        if (platform != null ? !platform.equals(that.platform) : that.platform != null) {
            return false;
        }
        if (gbId != null ? !gbId.equals(that.gbId) : that.gbId != null) {
            return false;
        }
        if (username != null ? !username.equals(that.username) : that.username != null) {
            return false;
        }
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = platform != null ? platform.hashCode() : 0;
        result = 31 * result + (gbId != null ? gbId.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DeviceGbConfig{" +
                "platform='" + platform + '\'' +
                ", gbId='" + gbId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

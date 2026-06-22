package com.baidubce.services.gaiadb.model;

public class UpdateAccountPasswordRequest extends GenericGaiadbRequest {
    private String password;
    private String etag;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}

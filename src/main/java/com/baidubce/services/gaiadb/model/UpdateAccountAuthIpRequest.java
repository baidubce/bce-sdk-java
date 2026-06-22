package com.baidubce.services.gaiadb.model;

public class UpdateAccountAuthIpRequest extends GenericGaiadbRequest {
    private String action;
    private String etag;
    private Account.AuthValue value;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Account.AuthValue getValue() {
        return value;
    }

    public void setValue(Account.AuthValue value) {
        this.value = value;
    }
}

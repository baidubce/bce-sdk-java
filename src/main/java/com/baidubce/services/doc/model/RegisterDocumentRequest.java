package com.baidubce.services.doc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by baidu on 15/12/30.
 */
public class RegisterDocumentRequest extends AbstractBceRequest {
    private String title = null;
    private String format = null;
    private String notification = null;
    private String access = null;

    public RegisterDocumentRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getFormat() { return this.format; }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getNotification() { return this.notification; }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getTitle() { return this.title; }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}

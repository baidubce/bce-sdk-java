package com.baidubce.services.doc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.io.File;

/**
 * Created by baidu on 15/12/30.
 */
public class CreateDocumentRequest extends AbstractBceRequest {
    private File file = null;
    private String title = null;
    private String format = null;
    private String notification = null;
    private String access = null;

    public CreateDocumentRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public File getFile() { return this.file; }

    public void setFile(File file) {
        this.file = file;
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

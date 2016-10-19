package com.baidubce.services.doc.model;

import java.util.Date;

/**
 * Created by baidu on 16/1/4.
 */
public class Document {
    private String documentId = null;
    private String status = null;
    private String title = null;
    private String format = null;
    private String notification = null;
    private String access = null;
    private DocumentError error = null;
    private Date createTime = null;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public DocumentError getError() {
        return error;
    }

    public void setError(DocumentError error) {
        this.error = error;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Docuement {\n");
        sb.append("    documentId: ").append(documentId).append("\n");
        sb.append("    status: ").append(status).append("\n");
        sb.append("    title: ").append(title).append("\n");
        sb.append("    format: ").append(format).append("\n");
        sb.append("    notification: ").append(notification).append("\n");
        sb.append("    access: ").append(access).append("\n");
        sb.append("    createTime: ").append(createTime).append("\n");
        if (error != null) {
            sb.append("    error: ").append(error.toString()).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}

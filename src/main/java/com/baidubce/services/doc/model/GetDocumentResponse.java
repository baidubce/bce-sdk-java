/*
 * Copyright 2015 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.doc.model;

import com.baidubce.model.AbstractBceResponse;

import java.util.Date;

public class GetDocumentResponse extends AbstractBceResponse {
    private String documentId = null;
    private String status = null;
    private String title = null;
    private String format = null;
    private String notification = null;
    private String access = null;
    private String targetType = null;
    private Date createTime = null;
    private DocumentUploadInfo uploadInfo = null;
    private DocumentPublishInfo publishInfo = null;
    private DocumentError error = null;

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

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public DocumentUploadInfo getUploadInfo() {
        return uploadInfo;
    }

    public void setUploadInfo(DocumentUploadInfo uploadInfo) {
        this.uploadInfo = uploadInfo;
    }

    public DocumentPublishInfo getPublishInfo() {
        return publishInfo;
    }

    public void setPublishInfo(DocumentPublishInfo publishInfo) {
        this.publishInfo = publishInfo;
    }

    public DocumentError getError() {
        return error;
    }

    public void setError(DocumentError error) {
        this.error = error;
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
        sb.append("    targetType: ").append(targetType).append("\n");
        sb.append("    createTime: ").append(createTime).append("\n");
        if (this.status.equals("PUBLISHED")) {
            sb.append("    publishInfo:").append(publishInfo.toString()).append("\n");
        }
        if (this.status.equals("UPLOADING")) {
            sb.append("    uploadInfo:").append(uploadInfo.toString()).append("\n");
        }
        if (error != null) {
            sb.append("    error: ").append(error.toString()).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}

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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

import java.io.File;

public class CreateDocumentRequest extends AbstractBceRequest {
    private File file = null;
    private String title = null;
    private String format = null;
    private String notification = null;
    private String access = null;
    private String targetType = null;

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

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
}

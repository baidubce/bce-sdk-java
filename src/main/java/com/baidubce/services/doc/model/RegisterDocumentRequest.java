/*
 * Copyright 2017 Baidu, Inc.
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

/**
 * RegisterDocumentRequest class used to register document request
 * send to API server.
 */

public class RegisterDocumentRequest extends AbstractBceRequest {
    /**
     * document title
     */
    private String title = null;
    /**
     * document format
     */
    private String format = null;
    /**
     * document notification when doc status changed
     */
    private String notification = null;
    /**
     * document access
     */
    private String access = null;
    /**
     * document convert type
     */
    private String targetType = null;

    /**
     * set request credentials
     *
     * @param credentials  request credential.
     */
    public RegisterDocumentRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * get document format
     *
     * @return the document format
     */
    public String getFormat() { return this.format; }

    /**
     * get document format
     *
     * @param format the document format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * get document notification
     *
     * @return the document notification
     */
    public String getNotification() { return this.notification; }

    /**
     * get document notification
     *
     * @param notification the document notification
     */
    public void setNotification(String notification) {
        this.notification = notification;
    }

    /**
     * get document title
     *
     * @return the document title
     */
    public String getTitle() { return this.title; }

    /**
     * get document title
     *
     * @param title the document title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get document access
     *
     * @return the document access
     */
    public String getAccess() {
        return access;
    }

    /**
     * get document access
     *
     * @param access the document access
     */
    public void setAccess(String access) {
        this.access = access;
    }

    /**
     * get document target type
     *
     * @return the document target type
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * get document target type
     *
     * @param targetType the document target type
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
}

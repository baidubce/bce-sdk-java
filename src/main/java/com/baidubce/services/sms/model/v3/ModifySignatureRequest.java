/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.sms.model.v3;

import com.baidubce.services.sms.model.SmsRequest;

public class ModifySignatureRequest extends SmsRequest {

    /**
     * The unique code identifying the signature
     */
    private String signatureId;

    /**
     * Text content of the signature
     */
    private String content;

    /**
     * The type of the signature
     */
    private String contentType;

    /**
     * CountryType indicates the countries or regions in which the signature can be used.
     */
    private String countryType;

    /**
     * User-defined text description of the signature
     */
    private String description;

    /**
     * The base64 encoding string of the signature certificate picture.
     */
    private String signatureFileBase64;

    /**
     * The format of the signature certificate picture, which can only be one of JPG、PNG、JPEG.
     */
    private String signatureFileFormat;

    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setSignatureId(String signatureId) {
        this.signatureId = signatureId;
    }

    public ModifySignatureRequest withSignatureId(String signatureId) {
        this.setSignatureId(signatureId);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ModifySignatureRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ModifySignatureRequest withContent(String content) {
        this.setContent(content);
        return this;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
    }

    public ModifySignatureRequest withCountryType(String countryType) {
        this.setCountryType(countryType);
        return this;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public ModifySignatureRequest withContentType(String contentType) {
        this.setContentType(contentType);
        return this;
    }

    public void setSignatureFileBase64(String signatureFileBase64) {
        this.signatureFileBase64 = signatureFileBase64;
    }

    public ModifySignatureRequest withSignatureFileBase64(String signatureCert) {
        this.setSignatureFileBase64(signatureCert);
        return this;
    }

    public void setSignatureFileFormat(String fmt) {
        this.signatureFileFormat = fmt;
    }

    public ModifySignatureRequest withSignatureFileFmt(String fmt) {
        this.setSignatureFileFormat(fmt);
        return this;
    }

    public String getSignatureId() {
        return this.signatureId;
    }

    public String getDescription() {
        return this.description;
    }

    public String getContent() {
        return this.content;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getCountryType() {
        return this.countryType;
    }

    public String getSignatureFileBase64() {
        return this.signatureFileBase64;
    }

    public String getSignatureFileFormat() {
        return this.signatureFileFormat;
    }

}

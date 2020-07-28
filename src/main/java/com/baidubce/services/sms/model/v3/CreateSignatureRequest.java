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

public class CreateSignatureRequest extends SmsRequest {
    /**
     * Text content of the signature
     * e.g. Baidu
     */
    private String content;
    /**
     * The type of the signature<br/>
     * The value of contentType could be Enterprise or MobileApp or Web or WeChatPublic or Brand or Else.
     */
    private String contentType;
    /**
     * Description of the signature
     */
    private String description;
    /**
     * The countryType indicates the countries or regions in which the signature can be used.<br/>
     * The value of countryType could be DOMESTIC or INTERNATIONAL or GLOBAL.<br/>
     * DOMESTIC means the signature can only be used in Mainland China;<br/>
     * INTERNATIONAL means the signature can only be used out of Mainland China;<br/>
     * GLOBAL means the signature can only be used all over the world.
     */
    private String countryType;
    /**
     * The base64 encoding string of the signature certificate picture.
     */
    private String signatureFileBase64;

    /**
     * The format of the signature certificate picture, which can only be one of JPG、PNG、JPEG.
     */
    private String signatureFileFormat;

    public void setContent(String content) {
        this.content = content;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountryType(String countryType) {
        this.countryType = countryType;
    }

    public void setSignatureFileBase64(String signatureFileBase64) {
        this.signatureFileBase64 = signatureFileBase64;
    }

    public void setSignatureFileFormat(String fmt) {
        this.signatureFileFormat = fmt;
    }

    public String getContent() {
        return this.content;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getDescription() {
        return this.description;
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

    public CreateSignatureRequest withContent(String content) {
        this.setContent(content);
        return this;
    }

    public CreateSignatureRequest withContentType(String contentType) {
        this.setContentType(contentType);
        return this;
    }

    public CreateSignatureRequest withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public CreateSignatureRequest withCountryType(String countryType) {
        this.setCountryType(countryType);
        return this;
    }

    public CreateSignatureRequest withSignatureFileBase64(String signatureCert) {
        this.setSignatureFileBase64(signatureCert);
        return this;
    }

    public CreateSignatureRequest withSignatureFileFormat(String fmt) {
        this.setSignatureFileFormat(fmt);
        return this;
    }

}

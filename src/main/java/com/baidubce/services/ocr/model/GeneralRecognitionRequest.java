/*
 * Copyright 2016 Baidu, Inc.
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

package com.baidubce.services.ocr.model;

import com.baidubce.BceClientException;
import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.UnsupportedEncodingException;

/**
 * Created by lvsiyuan on 16/11/21.
 */
public class GeneralRecognitionRequest extends FormAbstractBceRequest {

    /*
     * Required. The image data which needs to be base64.
     */
    private String image;

    /*
     * Optional. Decide if recognize single character. (big/small)
     */
    @JsonProperty("recognize_granularity")
    private String granularity = "big";

    /*
     * Optional. Represent the gray/white/black shade image area. (base64 data)
     */
    private String mask = "";

    /*
     * Optional. Language type. (ENG/POR/FRE/GER/ITA/SPA/RUS/JAP)
     */
    @JsonProperty("language_type")
    private String langType = "CHN_ENG";

    /*
     * Optional. Decide if the image has been rotated. (true/false)
     */
    @JsonProperty("detect_direction")
    private Boolean direction = false;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public GeneralRecognitionRequest withImage(String image) {
        this.image = image;
        return this;
    }

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(String granularity) {
        this.granularity = granularity;
    }

    public GeneralRecognitionRequest withGranularity(String granularity) {
        this.granularity = granularity;
        return this;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public GeneralRecognitionRequest withMask(String mask) {
        this.mask = mask;
        return this;
    }

    public String getLangType() {
        return langType;
    }

    public void setLangType(String langType) {
        this.langType = langType;
    }

    public GeneralRecognitionRequest withLangType(String langType) {
        this.langType = langType;
        return this;
    }

    public Boolean getDirection() {
        return direction;
    }

    public void setDirection(Boolean direction) {
        this.direction = direction;
    }

    public GeneralRecognitionRequest withDirection(Boolean direction) {
        this.direction = direction;
        return this;
    }

    public String toFormString() {
        try {
            return "image=" + java.net.URLEncoder.encode(image, "UTF-8") + "&"
                    + "recognize_granularity=" + granularity + "&"
                    + "mask=" + mask + "&"
                    + "language_type=" + langType + "&"
                    + "detect_direction=" + direction;
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        return "GeneralRecognitionRequest{"
                + "image='" + image + '\''
                + ", granularity='" + granularity + '\''
                + ", mask='" + mask + '\''
                + ", langType='" + langType + '\''
                + ", direction=" + direction + ""
                + '}';
    }
}

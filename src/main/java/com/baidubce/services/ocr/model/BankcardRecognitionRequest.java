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

import java.io.UnsupportedEncodingException;

/**
 * Created by lvsiyuan on 16/11/23.
 */
public class BankcardRecognitionRequest extends FormAbstractBceRequest {

    /*
     * Required. The image data which needs to be base64.
     */
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BankcardRecognitionRequest withImage(String image) {
        this.image = image;
        return this;
    }

    @Override
    public String toFormString() {
        try {
            return "image=" + java.net.URLEncoder.encode(image, "UTF-8");
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
        return "BankcardRecognitionRequest{"
                + "image='" + image + '\''
                + '}';
    }
}

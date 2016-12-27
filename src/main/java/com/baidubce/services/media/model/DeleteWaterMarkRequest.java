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

package com.baidubce.services.media.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

public class DeleteWaterMarkRequest extends AbstractBceRequest {

    private String watermarkId = null;
    
    public String getWatermarkId() {
        return watermarkId;
    }

    public void setWatermarkId(String watermarkId) {
        this.watermarkId = watermarkId;
    }
    
    public DeleteWaterMarkRequest withWatermarkId(String watermarkId) {
        this.watermarkId = watermarkId;
        return this;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeleteWaterMarkRequest {\n");
        sb.append("    watermarkId: ").append(watermarkId).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}

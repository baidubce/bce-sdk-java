/*
 * Copyright 2019-2020 Baidu, Inc.
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
package com.baidubce.services.bvw.model.matlib;

import com.baidubce.model.AbstractBceResponse;

/**
 * The response of uploading media to material library.
 */
public class MatlibUploadResponse  extends AbstractBceResponse {

    /**
     * The material id.
     */
    private String materialId;

    public MatlibUploadResponse() {
    }

    public MatlibUploadResponse(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    @Override
    public String toString() {
        return "MatlibUploadResponse{" +
                "materialId='" + materialId + '\'' +
                '}';
    }

}

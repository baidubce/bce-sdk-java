/*
 * Copyright 2019-2021 Baidu, Inc.
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

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import lombok.Data;

/**
 * The request of searching material from material library.
 */
@Data
public class MaterialPresetSearchRequest extends AbstractBceRequest {

    /**
     * The source type of material preset.
     */
    private String sourceType;
    /**
     * The status of material preset.
     */
    private String status;
    /**
     * The type of material preset.
     */
    private String type;
    /**
     * The pageNo.
     */
    private Integer pageNo;
    /**
     * The size for one page.
     */
    private Integer pageSize;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

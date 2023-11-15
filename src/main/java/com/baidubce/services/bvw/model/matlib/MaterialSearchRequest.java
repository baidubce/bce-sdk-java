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
public class MaterialSearchRequest extends AbstractBceRequest {

    /**
     * The info type of material.
     */
    private String infoType;
    /**
     * The media type of material.
     */
    private String mediaType;
    /**
     * The source type of material.
     */
    private String sourceType;
    /**
     * The status of material.
     */
    private String status;
    /**
     * A Part of title of target material.
     */
    private String titleKeyword;
    /**
     * The pageNo.
     */
    private Integer pageNo;
    /**
     * The size for one page.
     */
    private Integer size;
    /**
     * The range of createTime.
     * eg. "2019-04-07T16:00:00Z"
     */
    private String begin;
    /**
     * The range of createTime.
     * eg. "2019-04-07T16:00:00Z"
     */
    private String end;

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.dcc.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcc.model.TagModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * The request class for binding tags to dcc
 */
public class DccBindTagsRequest extends AbstractBceRequest {

    /**
     * Dcc id, specify which dedicated host to bind tags.
     */
    @JsonIgnore
    private String dccId;

    /**
     * List of tags to bind.
     */
    private List<TagModel> changeTags;

    public String getDccId() {
        return dccId;
    }

    public void setDccId(String dccId) {
        this.dccId = dccId;
    }

    public List<TagModel> getChangeTags() {
        return changeTags;
    }

    public void setChangeTags(List<TagModel> changeTags) {
        this.changeTags = changeTags;
    }

    @Override
    public DccBindTagsRequest  withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

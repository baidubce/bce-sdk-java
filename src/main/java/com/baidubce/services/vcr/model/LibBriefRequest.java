/*
 * Copyright 2018 Baidu, Inc.
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
package com.baidubce.services.vcr.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * VCR modify lib brief request.
 */
public class LibBriefRequest extends AbstractBceRequest {
    private String lib;
    private String brief;

    public LibBriefRequest() {
    }

    public LibBriefRequest(String lib, String brief) {
        this.lib = lib;
        this.brief = brief;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public String getLib() {
        return lib;
    }

    public String getBrief() {
        return brief;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
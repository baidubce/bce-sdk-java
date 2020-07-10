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
package com.baidubce.services.lss.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Created by yangwenyue on 20/07/07.
 */
public class ResetDomainStreamRequest extends AbstractBceRequest {

    private String domain = null;

    private String app = null;

    private String stream = null;

    private Long resumeTimeInSecond = null;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public ResetDomainStreamRequest withDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public ResetDomainStreamRequest withApp(String app) {
        this.app = app;
        return this;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public ResetDomainStreamRequest withStream(String stream) {
        this.stream = stream;
        return this;
    }

    public Long getResumeTimeInSecond() {
        return resumeTimeInSecond;
    }

    public void setResumeTimeInSecond(Long resumeTimeInSecond) {
        this.resumeTimeInSecond = resumeTimeInSecond;
    }

    public ResetDomainStreamRequest withResumeTimeInSecond(Long resumeTimeInSecond) {
        this.resumeTimeInSecond = resumeTimeInSecond;
        return this;
    }

    public ResetDomainStreamRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("class ResetDomainStreamRequest {\n");
        sb.append("    domain: ").append(domain).append("\n");
        sb.append("    app: ").append(app).append("\n");
        sb.append("    stream: ").append(stream).append("\n");
        sb.append("    resumeTimeInSecond: ").append(resumeTimeInSecond).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}

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
 * Created on 2020/7/7
 *
 * @author yangwenyue
 */
public class ResetDomainStreamRequestBody extends AbstractBceRequest {

    private Long resumeTimestamp;

    public ResetDomainStreamRequestBody(Long resumeTimestamp) {
        super();
        this.resumeTimestamp = resumeTimestamp;
    }

    public Long getResumeTimestamp() {
        return resumeTimestamp;
    }

    public void setResumeTimestamp(Long resumeTimestamp) {
        this.resumeTimestamp = resumeTimestamp;
    }

    public ResetDomainStreamRequestBody withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

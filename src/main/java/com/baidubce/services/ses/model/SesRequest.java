/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.ses.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The Base class of all SES request。 All of SES request should extend it.
 */
public class SesRequest extends AbstractBceRequest {

    @Override
    @JsonIgnore
    public BceCredentials getRequestCredentials() {
        return super.getRequestCredentials();
    }

    @Override
    @JsonIgnore
    public void setRequestCredentials(BceCredentials credentials) {
        super.setRequestCredentials(credentials);
    }

    @Override
    public SesRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

}

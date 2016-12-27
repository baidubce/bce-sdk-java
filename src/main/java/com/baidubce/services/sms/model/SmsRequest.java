/*
 * Copyright 2014 Baidu, Inc.
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
package com.baidubce.services.sms.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The base class of all SMS request
 */
public class SmsRequest extends AbstractBceRequest {
    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.model.AbstractBceRequest#withRequestCredentials(com.baidubce .auth.BceCredentials)
     */
    @Override
    public SmsRequest withRequestCredentials(BceCredentials credentials) {
        setRequestCredentials(credentials);
        return this;
    }

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

}

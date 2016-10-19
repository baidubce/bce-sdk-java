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
package com.baidubce.services.sms;

import static com.google.common.base.Preconditions.checkNotNull;

import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.sms.model.SmsResponse;
import com.baidubce.services.sms.model.UpdateQuotaRequest;
import com.baidubce.services.sms.model.UpdateTemplateRequest;
import com.baidubce.services.sms.model.UpdateTemplateResponse;
import com.baidubce.util.JsonUtils;

/**
 * <B>The entrance class for all client access to the API of SMS(Baidu message Service).</B>
 * <p>
 * <B>All of the API method of SMS is included in this class.</B>
 * <p>
 */
public class SmsInternalClient extends SmsClientSupport {
    /**
     * Constructs a new <code>SmsClient</code> instance with default settings.
     */
    public SmsInternalClient() {
        this(new SmsClientConfiguration());
    }

    /**
     * Constructs a new <code>SmsClient</code> instance with the specified configuration.
     * 
     * @param config the specified configuration, default inherit super class
     *            <code>com.baidubce.BceClientConfiguration</code>
     * 
     * @see com.baidubce.services.sms.SmsClientConfiguration
     */
    public SmsInternalClient(SmsClientConfiguration config) {
        super(config, SMS_HANDLERS);
    }

    /**
     * Update message template
     * 
     * @param request The request object which includes the name, content and status of template which is ready to be
     *            updated
     * @return The response object which is empty
     * 
     * @see com.baidubce.services.sms.model.UpdateTemplateRequest
     * @see com.baidubce.services.sms.model.UpdateTemplateResponse
     */
    public UpdateTemplateResponse updateTemplate(UpdateTemplateRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getTemplateId(), "object templateId should not be null or empty.");

        InternalRequest internalRequest =
                this.createRequest("template", request, HttpMethodName.PUT, request.getTemplateId());

        // fill in the request payload
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));

        return this.invokeHttpClient(internalRequest, UpdateTemplateResponse.class);
    }

    /**
     * Set the sending quota
     * 
     * @param request The request object which includes the detail of sending quota which is ready to be set
     * 
     * @see com.baidubce.services.sms.model.UpdateQuotaRequest
     */
    public void updateQuota(UpdateQuotaRequest request) {
        checkNotNull(request, "object request should not be null.");

        InternalRequest internalRequest = this.createRequest("quota", request, HttpMethodName.PUT);

        // fill in the request payload
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));

        this.invokeHttpClient(internalRequest, SmsResponse.class);
    }

}
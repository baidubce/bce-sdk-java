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
package com.baidubce.services.sms;

import static com.google.common.base.Preconditions.checkNotNull;

import com.baidubce.BceServiceException;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.sms.model.CreateTemplateRequest;
import com.baidubce.services.sms.model.CreateTemplateResponse;
import com.baidubce.services.sms.model.DeleteTemplateRequest;
import com.baidubce.services.sms.model.GetTemplateDetailRequest;
import com.baidubce.services.sms.model.GetTemplateDetailResponse;
import com.baidubce.services.sms.model.ListTemplateResponse;
import com.baidubce.services.sms.model.QueryMessageDetailRequest;
import com.baidubce.services.sms.model.QueryMessageDetailResponse;
import com.baidubce.services.sms.model.QueryQuotaResponse;
import com.baidubce.services.sms.model.SendMessageRequest;
import com.baidubce.services.sms.model.SendMessageResponse;
import com.baidubce.services.sms.model.SendMessageV2Request;
import com.baidubce.services.sms.model.SendMessageV2Response;
import com.baidubce.services.sms.model.SmsRequest;
import com.baidubce.services.sms.model.SmsResponse;
import com.baidubce.services.sms.model.StatReceiverRequest;
import com.baidubce.services.sms.model.StatReceiverResponse;
import com.baidubce.util.JsonUtils;

/**
 * <B>The entrance class for all client access to the API of SMS(Baidu message Service).</B>
 * <p/>
 * <B>All of the API method of SMS is included in this class.</B>
 * <p/>
 */
public class SmsClient extends SmsClientSupport {
    /**
     * Constructs a new <code>SmsClient</code> instance with default settings.
     */
    public SmsClient() {
        this(new SmsClientConfiguration());
    }

    /**
     * Constructs a new <code>SmsClient</code> instance with the specified configuration.
     *
     * @param config the specified configuration, default inherit super class
     *               <code>com.baidubce.BceClientConfiguration</code>
     *
     * @see com.baidubce.services.sms.SmsClientConfiguration
     */
    public SmsClient(SmsClientConfiguration config) {
        super(config, SMS_HANDLERS);
    }

    /**
     * Send message
     * <p/>
     * To send message, you have to specify a template which is built by system, or created by yourself which is audited
     * by BCE administrator.
     * <p/>
     * One or more receiver can be specified while sending message.
     * <p/>
     *
     * @param request The request object which includes the content to send and one or more receiver
     *
     * @return The response object which includes the id of message and the statistics of sending result
     *
     * @see com.baidubce.services.sms.model.SendMessageRequest
     * @see com.baidubce.services.sms.model.SendMessageResponse
     */
    public SendMessageResponse sendMessage(SendMessageRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getTemplateId(),
                "string templateId of request should not be null or empty.");
        assertListNotNullOrEmpty(request.getReceiver(), "list receiver of request should not be null or empty.");
        // allow params is empty
        //  assertStringNotNullOrEmpty(request.getContentVar(), "contentVar should not be null or empty.");

        // validate the receiver
        for (String receiver : request.getReceiver()) {
            assertStringNotNullOrEmpty(receiver, "receiver should not be null or empty.");
        }

        InternalRequest internalRequest = this.createRequest("message", request, HttpMethodName.POST);

        // fill in the request payload
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));

        return this.invokeHttpClient(internalRequest, SendMessageResponse.class);
    }

    public SendMessageV2Response sendMessage(SendMessageV2Request request) {
        checkNotNull(request, "request is required.");
        assertStringNotNullOrEmpty(request.getInvokeId(), "invokeId is required.");
        assertStringNotNullOrEmpty(request.getPhoneNumber(), "phoneNumber is required.");
        assertStringNotNullOrEmpty(request.getTemplateCode(), "templateCode is required.");
        InternalRequest internalRequest = this.createGeneralRequest("bce/v2/message", request, HttpMethodName.POST);
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));
        SendMessageV2Response response = null;
        try {
            response = this.invokeHttpClient(internalRequest, SendMessageV2Response.class);
        } catch (BceServiceException exception) {
            response = new SendMessageV2Response();
            response.setCode(exception.getErrorCode());
            response.setMessage(exception.getErrorMessage());
            response.setRequestId(exception.getRequestId());
        }
        return response;
    }

    /**
     * Query message detail
     *
     * @param request The request object which includes the id of message to query
     *
     * @return The response object which includes all the detail result, for example <code>id</code>,
     * <code>content</code>, <code>receiver</code>, <code>sendTime</code> etc
     *
     * @see com.baidubce.services.sms.model.QueryMessageDetailRequest
     * @see com.baidubce.services.sms.model.QueryMessageDetailResponse
     */
    public QueryMessageDetailResponse queryMessageDetail(QueryMessageDetailRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getMessageId(), "object messageId should not be null or empty.");

        InternalRequest internalRequest =
                this.createRequest("message", request, HttpMethodName.GET, request.getMessageId());
        return this.invokeHttpClient(internalRequest, QueryMessageDetailResponse.class);
    }

    /**
     * Create message template
     * <p/>
     * The template which is created will have a unique id. after audited by BCE administrator, it can be used normally.
     * <p/>
     *
     * @param request The request object which includes the name and content of template which is ready to be created
     *
     * @return The response object which includes the id of template which is created
     *
     * @see com.baidubce.services.sms.model.CreateTemplateRequest
     * @see com.baidubce.services.sms.model.CreateTemplateResponse
     */
    public CreateTemplateResponse createTemplate(CreateTemplateRequest request) {
        checkNotNull(request, "request is required.");
        assertStringNotNullOrEmpty(request.getName(), "template name is required.");
        assertStringNotNullOrEmpty(request.getContent(), "template content is required.");
        assertStringNotNullOrEmpty(request.getInvokeId(), "template invokeId is required.");
        InternalRequest internalRequest = this.createGeneralRequest("bce/v2/applyTemplate",
                request, HttpMethodName.POST);

        // fill in the request payload
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));

        CreateTemplateResponse response = null;
        try {
            response = this.invokeHttpClient(internalRequest, CreateTemplateResponse.class);
        } catch (BceServiceException exception) {
            response = new CreateTemplateResponse();
            response.setCode(exception.getErrorCode());
            response.setMessage(exception.getErrorMessage());
            response.setRequestId(exception.getRequestId());
        }
        return response;
    }

    /**
     * Delete message template
     *
     * @param request The request object which includes the id of template which is ready to be deleted
     *
     * @see com.baidubce.services.sms.model.DeleteTemplateRequest
     */
    public void deleteTemplate(DeleteTemplateRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getTemplateId(), "object templateId should not be null or empty.");

        InternalRequest internalRequest =
                this.createRequest("template", request, HttpMethodName.DELETE, request.getTemplateId());
        this.invokeHttpClient(internalRequest, SmsResponse.class);
    }

    /**
     * Get the detail of message template
     *
     * @param request The request object which includes the id of template which is ready to be get
     *
     * @return The response object which includes all of the detail of message template, refer to
     * <code>com.baidubce.services.sms.model.GetTemplateDetailResponse</code>
     *
     * @see com.baidubce.services.sms.model.GetTemplateDetailRequest
     * @see com.baidubce.services.sms.model.GetTemplateDetailResponse
     */
    public GetTemplateDetailResponse getTemplateDetail(GetTemplateDetailRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getTemplateId(), "object templateId should not be null or empty.");

        InternalRequest internalRequest =
                this.createRequest("template", request, HttpMethodName.GET, request.getTemplateId());
        return this.invokeHttpClient(internalRequest, GetTemplateDetailResponse.class);
    }

    /**
     * Get the list of message template
     *
     * @param request The request object which is empty
     *
     * @return The response object which includes all of the detail of message template,refer to
     * <code>com.baidubce.services.sms.model.ListTemplateResponse</code>
     *
     * @see com.baidubce.services.sms.model.ListTemplateResponse
     */
    public ListTemplateResponse listTemplate(SmsRequest request) {
        checkNotNull(request, "object request should not be null.");

        InternalRequest internalRequest = this.createRequest("template", request, HttpMethodName.GET);
        return this.invokeHttpClient(internalRequest, ListTemplateResponse.class);
    }

    /**
     * Query the sending quota
     *
     * @param request The request object which is empty
     *
     * @return The response object which includes the detail of sending quota, refer to
     * <code>com.baidubce.services.sms.model.QueryQuotaResponse</code>
     *
     * @see com.baidubce.services.sms.model.QueryQuotaResponse
     */
    public QueryQuotaResponse queryQuota(SmsRequest request) {
        checkNotNull(request, "object request should not be null.");

        InternalRequest internalRequest = this.createRequest("quota", request, HttpMethodName.GET);
        return this.invokeHttpClient(internalRequest, QueryQuotaResponse.class);
    }

    /**
     * Get the statistics about receiving message
     *
     * @param request refer to <code>com.baidubce.services.sms.model.StatReceiverRequest</code>
     *
     * @return refer to <code>com.baidubce.services.sms.model.StatReceiverResponse</code>
     *
     * @see com.baidubce.services.sms.model.StatReceiverRequest
     * @see com.baidubce.services.sms.model.StatReceiverResponse
     */
    public StatReceiverResponse statReceiver(StatReceiverRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getPhoneNumber(), "object phoneNumber should not be null or empty.");

        InternalRequest internalRequest =
                this.createRequest("receiver", request, HttpMethodName.GET, request.getPhoneNumber());
        return this.invokeHttpClient(internalRequest, StatReceiverResponse.class);
    }

}
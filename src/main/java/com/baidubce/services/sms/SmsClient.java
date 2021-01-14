/*
 * Copyright (c) 2014-2020 Baidu.com, Inc. All Rights Reserved
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
import com.baidubce.services.sms.model.SendMessageV3Request;
import com.baidubce.services.sms.model.SendMessageV2Response;
import com.baidubce.services.sms.model.SendMessageV3Response;
import com.baidubce.services.sms.model.SmsRequest;
import com.baidubce.services.sms.model.SmsResponse;
import com.baidubce.services.sms.model.StatReceiverRequest;
import com.baidubce.services.sms.model.StatReceiverResponse;
import com.baidubce.services.sms.model.v3.CreateSignatureRequest;
import com.baidubce.services.sms.model.v3.CreateSignatureResponse;
import com.baidubce.services.sms.model.v3.DeleteSignatureRequest;
import com.baidubce.services.sms.model.v3.GetSignatureRequest;
import com.baidubce.services.sms.model.v3.GetSignatureResponse;
import com.baidubce.services.sms.model.v3.GetTemplateRequest;
import com.baidubce.services.sms.model.v3.GetTemplateResponse;
import com.baidubce.services.sms.model.v3.ModifySignatureRequest;
import com.baidubce.services.sms.model.v3.ModifyTemplateRequest;
import com.baidubce.services.sms.model.v3.QueryQuotaRateResponse;
import com.baidubce.services.sms.model.v3.UpdateQuotaRateRequest;
import com.baidubce.services.sms.model.v3.ListTemplateRequest;
import com.baidubce.services.sms.model.v3.ListSignatureRequest;
import com.baidubce.services.sms.model.v3.ListSignatureResponse;
import com.baidubce.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * <B>The entrance class for all client access to the API of SMS(Baidu message Service).</B>
 * <p>
 * <B>All of the API method of SMS is included in this class.</B>
 * <p>
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
     * @see com.baidubce.services.sms.SmsClientConfiguration
     */
    public SmsClient(SmsClientConfiguration config) {
        super(config, SMS_HANDLERS);
    }

    /**
     * Send message
     * <p>
     * To send message, you have to specify a template which is built by system, or created by yourself which is audited
     * by BCE administrator.
     * <p>
     * One or more receiver can be specified while sending message.
     * <p>
     *
     * @param request The request object which includes the content to send and one or more receiver
     * @return The response object which includes the id of message and the statistics of sending result
     * @see com.baidubce.services.sms.model.SendMessageRequest
     * @see com.baidubce.services.sms.model.SendMessageResponse
     * @deprecated This method is deprecated and will be removed from sdk in the future when SMS3.0 is officially released, we suggest you to use sendMessage(SendMessageV3Request) instead.
     */
    @Deprecated
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

    /**
     * Send message with parameter SendMessageV2Request
     *
     * @deprecated This method is deprecated and will be removed from sdk in the future when SMS3.0 is officially released, we suggest you to use sendMessage(SendMessageV3Request) instead.
     */
    @Deprecated
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
     * Send message
     * <p>
     * The interface of sending message by SMS3.0.
     * To send message, you have to specify a template and a signature which are created by yourself.
     * </p>
     *
     * @param request refer to <code>com.baidubce.services.sms.model.SendMessageV3Request</code>
     * @return refer to <code>com.baidubce.services.sms.model.SendMessageV3Response</code>
     */
    public SendMessageV3Response sendMessage(SendMessageV3Request request) {
        checkNotNull(request, "request is required.");
        assertStringNotNullOrEmpty(request.getMobile(), "mobile is required.");
        assertStringNotNullOrEmpty(request.getSignatureId(), "signatureId is required.");
        assertStringNotNullOrEmpty(request.getTemplate(), "template is required.");
        InternalRequest internalRequest = this.createGeneralRequest("api/v3/sendsms", request, HttpMethodName.POST);
        if (!StringUtils.isBlank(request.getClientToken())) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));
        SendMessageV3Response response;
        try {
            response = this.invokeHttpClient(internalRequest, SendMessageV3Response.class);
        } catch (BceServiceException exception) {
            response = new SendMessageV3Response();
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
     * @return The response object which includes all the detail result, for example <code>id</code>,
     * <code>content</code>, <code>receiver</code>, <code>sendTime</code> etc
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
     * <p>
     * The template which is created will have a unique id. after audited by BCE administrator, it can be used normally.
     * <p>
     *
     * @param request The request object which includes the name and content of template which is ready to be created
     * @return The response object which includes the id of template which is created
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
     * Create the template
     *
     * @param request refer to <code>com.baidubce.services.sms.model.v3.CreateTemplateRequest</code>
     * @return The response object which indicates the detail of newly created template, refer to
     * <code>com.baidubce.services.sms.model.v3.CreateTemplateResponse</code>
     */
    public com.baidubce.services.sms.model.v3.CreateTemplateResponse createTemplate(
            com.baidubce.services.sms.model.v3.CreateTemplateRequest request) {
        checkNotNull(request, "object request should not be null");
        assertStringNotNullOrEmpty(request.getContent(), "content should not be null or empty");
        assertStringNotNullOrEmpty(request.getCountryType(), "countryType should not be null or empty");
        assertStringNotNullOrEmpty(request.getName(), "name should not be null or empty");
        assertStringNotNullOrEmpty(request.getSmsType(), "smsType should not be null or empty");
        InternalRequest internalRequest = this.createGeneralRequest("/sms/v3/template", request, HttpMethodName.POST);
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));
        internalRequest.addParameter("clientToken", UUID.randomUUID().toString());
        return this.invokeHttpClient(internalRequest, com.baidubce.services.sms.model.v3.CreateTemplateResponse.class);
    }

    /**
     * Delete message template
     *
     * @param request The request object which includes the id of template which is ready to be deleted
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
     * Delete the template
     *
     * @param request refer to <code>com.baidubce.services.sms.model.v3.DeleteTemplateRequest</code>
     */
    public void deleteTemplate(
            com.baidubce.services.sms.model.v3.DeleteTemplateRequest request) {
        checkNotNull(request, "object request should not be null");
        assertStringNotNullOrEmpty(request.getTemplateId(), "templateId should not be null or empty");
        InternalRequest internalRequest = this.createGeneralRequest("/sms/v3/template",
                request, HttpMethodName.DELETE, request.getTemplateId());
        this.invokeHttpClient(internalRequest, SmsResponse.class);
    }

    /**
     * Get the detail of message template
     *
     * @param request The request object which includes the id of template which is ready to be get
     * @return The response object which includes all of the detail of message template, refer to
     * <code>com.baidubce.services.sms.model.GetTemplateDetailResponse</code>
     * @see com.baidubce.services.sms.model.GetTemplateDetailRequest
     * @see com.baidubce.services.sms.model.GetTemplateDetailResponse
     * @deprecated This method is deprecated and will be removed from sdk in the future when SMS3.0 is officially released, we suggest you to use this.getTemplate(com.baidubce.services.sms.model.v3.GetTemplateRequest) instead.
     */
    @Deprecated
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
     * @return The response object which includes all of the detail of message template,refer to
     * <code>com.baidubce.services.sms.model.ListTemplateResponse</code>
     * @see com.baidubce.services.sms.model.ListTemplateResponse
     * @deprecated This method is deprecated and will be removed from sdk in the future when SMS3.0 is officially released, we suggest you to use this.listTemplate(ListTemplateRequest request) instead.
     */
    public ListTemplateResponse listTemplate(SmsRequest request) {
        checkNotNull(request, "object request should not be null.");

        InternalRequest internalRequest = this.createRequest("template", request, HttpMethodName.GET);
        return this.invokeHttpClient(internalRequest, ListTemplateResponse.class);
    }

    /**
     * Get the list of the templates
     *
     * @param request refer to <code>com.baidubce.services.sms.model.v3.ListTemplateRequest</code>
     * @return The response object indicates the list of the template, refer to <code>com.baidubce.services.sms.model.v3.ListTemplateResponse</code>
     */
    public com.baidubce.services.sms.model.v3.ListTemplateResponse listTemplate(ListTemplateRequest request) {
        InternalRequest internalRequest = this.createGeneralRequest(
                "/sms/v3/template", new SmsRequest(), HttpMethodName.GET);
        if (request.getContent() != null && request.getContent().trim().length() > 0) {
            internalRequest.addParameter("contentLike", request.getContent());
        }
        if (request.getCountryType() != null && request.getCountryType().trim().length() > 0) {
            internalRequest.addParameter("countryType", request.getCountryType());
        }
        if (request.getName() != null && request.getName().trim().length() > 0) {
            internalRequest.addParameter("nameLike", request.getName());
        }
        if (request.getSmsType() != null && request.getSmsType().trim().length() > 0) {
            internalRequest.addParameter("smsType", request.getSmsType());
        }
        if (request.getStatus() != null && request.getSmsType().trim().length() > 0) {
            internalRequest.addParameter("status", request.getStatus());
        }
        if (request.getTemplateId() != null && request.getTemplateId().trim().length() > 0) {
            internalRequest.addParameter("templateIdLike", request.getTemplateId());
        }
        checkNotNull(request.getPageNo(), "Require pageNo not null");
        checkNotNull(request.getPageSize(), "Require pageSize not null");
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        internalRequest.addParameter("isIgnoreDeprecated", "true");
        return this.invokeHttpClient(internalRequest, com.baidubce.services.sms.model.v3.ListTemplateResponse.class);
    }

    /**
     * Query the sending quota
     *
     * @param request The request object which is empty
     * @return The response object which includes the detail of sending quota, refer to
     * <code>com.baidubce.services.sms.model.QueryQuotaResponse</code>
     * @see com.baidubce.services.sms.model.QueryQuotaResponse
     * @deprecated This method is deprecated and will be removed from sdk in the future when SMS3.0 is officially released, we suggest you to use this.queryQuotaRate() instead.
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
     * @return refer to <code>com.baidubce.services.sms.model.StatReceiverResponse</code>
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

    /**
     * Create the signature
     *
     * @param request refer to <code>com.baidubce.services.sms.model.v3.CreateSignatureRequest</code>
     * @return The response object which indicates the detail of newly created signature, refer to <code>com.baidubce.services.sms.model.v3.CreateSignatureResponse</code>
     */
    public CreateSignatureResponse createSignature(CreateSignatureRequest request) {
        checkNotNull(request, "object request should not be null");
        assertStringNotNullOrEmpty(request.getContent(), "content should not be null or empty");
        assertStringNotNullOrEmpty(request.getContentType(), "contentType should not be null or empty");
        assertStringNotNullOrEmpty(request.getCountryType(), "countryType should not be null or empty");
        InternalRequest internalRequest = this.createGeneralRequest(
                "/sms/v3/signatureApply", request, HttpMethodName.POST);
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));
        internalRequest.addParameter("clientToken", UUID.randomUUID().toString());
        return this.invokeHttpClient(internalRequest, CreateSignatureResponse.class);
    }

    /**
     * Delete the signature
     *
     * @param request refer to <code>com.baidubce.services.sms.model.v3.DeleteSignatureRequest</code>
     */
    public void deleteSignature(DeleteSignatureRequest request) {
        checkNotNull(request, "object request should not be null");
        assertStringNotNullOrEmpty(request.getSignatureId(), "signatureId should not be null or empty");
        InternalRequest internalRequest = this.createGeneralRequest(
                "/sms/v3/signatureApply", request, HttpMethodName.DELETE, request.getSignatureId());
        this.invokeHttpClient(internalRequest, SmsResponse.class);
    }

    /**
     * Modify the signature when audit failure
     *
     * @param request refer to <code>com.baidubce.services.sms.model.v3.ModifySignatureRequest</code>
     */
    public void modifySignature(ModifySignatureRequest request) {
        checkNotNull(request, "object request should not be null");
        assertStringNotNullOrEmpty(request.getSignatureId(), "signatureId should not be null or empty");
        assertStringNotNullOrEmpty(request.getContent(), "content should not be null or empty");
        assertStringNotNullOrEmpty(request.getContentType(), "contentType should not be null or empty");
        assertStringNotNullOrEmpty(request.getCountryType(), "countryType should not be null or empty");
        InternalRequest internalRequest = this.createGeneralRequest(
                "/sms/v3/signatureApply", request, HttpMethodName.PUT, request.getSignatureId());
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));
        this.invokeHttpClient(internalRequest, SmsResponse.class);
    }

    /**
     * Get signature detail info
     *
     * @param request refer to <code>com.baidubce.services.sms.model.v3.GetSignatureRequest</code>
     * @return The response object which includes the signature detail info, refer to
     * <code>com.baidubce.services.sms.model.v3.GetSignatureResponse</code>
     */
    public GetSignatureResponse getSignature(GetSignatureRequest request) {
        checkNotNull(request, "object request should not be null");
        assertStringNotNullOrEmpty(request.getSignatureId(), "signatureId should not be null or empty");
        InternalRequest internalRequest = this.createGeneralRequest(
                "/sms/v3/signatureApply", request, HttpMethodName.GET, request.getSignatureId());
        return this.invokeHttpClient(internalRequest, GetSignatureResponse.class);
    }

    /**
     * Modify the template when audit failure
     *
     * @param request refer to <code>com.baidubce.services.sms.model.v3.ModifyTemplateRequest</code>
     */
    public void modifyTemplate(ModifyTemplateRequest request) {
        checkNotNull(request, "object request should not be null");
        assertStringNotNullOrEmpty(request.getTemplateId(), "templateId should not be null or empty");
        InternalRequest internalRequest = this.createGeneralRequest("/sms/v3/template",
                request, HttpMethodName.PUT, request.getTemplateId());
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));
        this.invokeHttpClient(internalRequest, SmsResponse.class);
    }

    /**
     * Get template detail info
     *
     * @param request refer to <code>com.baidubce.services.sms.model.v3.GetTemplateRequest</code>
     * @return The response object which includes the template detail info, refer to
     * <code>com.baidubce.services.sms.model.v3.GetTemplateResponse</code>
     */
    public GetTemplateResponse getTemplate(GetTemplateRequest request) {
        checkNotNull(request, "object request should not be null");
        assertStringNotNullOrEmpty(request.getTemplateId(), "templateId should not be null or empty");
        InternalRequest internalRequest = this.createGeneralRequest(
                "/sms/v3/template", request, HttpMethodName.GET, request.getTemplateId());
        return this.invokeHttpClient(internalRequest, GetTemplateResponse.class);
    }

    /**
     * Update quota and rate-limit
     *
     * @param request refer to <code>com.baidubce.services.sms.model.v3.UpdateQuotaRateRequest</code>
     */
    public void updateQuotaRate(UpdateQuotaRateRequest request) {
        checkNotNull(request, "object request should not be null");
        checkNotNull(request.getQuotaPerDay(), "quotaPerDay should not be null or empty");
        checkNotNull(request.getQuotaPerMonth(), "quotaPerMonth should not be null or empty");
        checkNotNull(request.getRateLimitPerDay(), "rateLimitPerDay should not be null or empty");
        checkNotNull(request.getRateLimitPerHour(), "rateLimitPerHour should not be null or empty");
        checkNotNull(request.getRateLimitPerMinute(), "rateLimitPerMinute should not be null or empty");
        InternalRequest internalRequest = this.createGeneralRequest("/sms/v3/quota", request, HttpMethodName.PUT);
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));
        this.invokeHttpClient(internalRequest, SmsResponse.class);
    }

    /**
     * Query quota and rate-limit detail
     *
     * @return The response object which includes the detail quota and rate-limit info, refer to <code>com.baidubce.services.sms.model.v3.QueryQuotaRateResponse</code>
     */
    public QueryQuotaRateResponse queryQuotaRate() {
        InternalRequest internalRequest = this.createGeneralRequest(
                "/sms/v3/quota", new SmsRequest(), HttpMethodName.GET);
        internalRequest.addParameter("userQuery", "");
        return this.invokeHttpClient(internalRequest, QueryQuotaRateResponse.class);
    }

    /**
     * Get the list of the signatures
     *
     * @param request refer to <code>com.baidubce.services.sms.model.v3.ListSignatureRequest</code>
     * @return The response object indicates the list of the template, refer to <code>com.baidubce.services.sms.model.v3.ListSignatureResponse</code>
     */
    public ListSignatureResponse listSignature(ListSignatureRequest request) {
        InternalRequest internalRequest = this.createGeneralRequest(
                "/sms/v3/signatureApply", new SmsRequest(), HttpMethodName.GET);
        if (request.getContent() != null && request.getContent().trim().length() > 0) {
            internalRequest.addParameter("contentLike", request.getContent());
        }
        if (request.getSignatureId() != null && request.getSignatureId().trim().length() > 0) {
            internalRequest.addParameter("signatureIdLike", request.getSignatureId());
        }
        if (request.getCountryType() != null && request.getCountryType().trim().length() > 0) {
            internalRequest.addParameter("countryType", request.getCountryType());
        }
        if (request.getStatus() != null && request.getStatus().trim().length() > 0) {
            internalRequest.addParameter("status", request.getStatus());
        }
        checkNotNull(request.getPageNo(), "Require pageNo not null");
        checkNotNull(request.getPageSize(), "Require pageSize not null");
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        internalRequest.addParameter("isIgnoreDeprecated", "true");
        return this.invokeHttpClient(internalRequest, ListSignatureResponse.class);
    }
}
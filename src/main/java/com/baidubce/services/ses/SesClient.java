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
package com.baidubce.services.ses;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baidubce.BceClientException;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.ses.model.DeleteVerifiedDomainRequest;
import com.baidubce.services.ses.model.DeleteVerifiedEmailRequest;
import com.baidubce.services.ses.model.EnableDKIMRequest;
import com.baidubce.services.ses.model.GetFailedReasonResponse;
import com.baidubce.services.ses.model.GetFeedbackResponse;
import com.baidubce.services.ses.model.GetQuotaResponse;
import com.baidubce.services.ses.model.GetVerifiedDomainRequest;
import com.baidubce.services.ses.model.GetVerifiedDomainResponse;
import com.baidubce.services.ses.model.GetVerifiedEmailRequest;
import com.baidubce.services.ses.model.GetVerifiedEmailResponse;
import com.baidubce.services.ses.model.IsInRecipientBlacklistRequest;
import com.baidubce.services.ses.model.IsInRecipientBlacklistResponse;
import com.baidubce.services.ses.model.ListRecipientBlacklistResponse;
import com.baidubce.services.ses.model.ListVerifiedDomainResponse;
import com.baidubce.services.ses.model.ListVerifiedEmailResponse;
import com.baidubce.services.ses.model.SendEmailRequest;
import com.baidubce.services.ses.model.SendEmailRequest.Mail;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Attachment;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Attachment.FileData;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Destination;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Destination.Addr;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Message;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Source;
import com.baidubce.services.ses.model.SendEmailRequest.Mail.Subject;
import com.baidubce.services.ses.model.SendEmailResponse;
import com.baidubce.services.ses.model.SesRequest;
import com.baidubce.services.ses.model.SesResponse;
import com.baidubce.services.ses.model.SetFeedbackRequest;
import com.baidubce.services.ses.model.SetQuotaRequest;
import com.baidubce.services.ses.model.VerifyDKIMRequest;
import com.baidubce.services.ses.model.VerifyDKIMResponse;
import com.baidubce.services.ses.model.VerifyDomainRequest;
import com.baidubce.services.ses.model.VerifyDomainResponse;
import com.baidubce.services.ses.model.VerifyEmailRequest;
import com.baidubce.util.JsonUtils;

/**
 * <B>The entrance class for all client access to the API of SES(Baidu email Service).</B>
 * <p>
 * <B>All of the API method of SES is includes in this class.</B>
 * <p>
 */
public class SesClient extends SesClientSupport {
    /**
     * Constructs a new <code>SesClient</code> instance with default settings.
     */
    public SesClient() {
        this(new SesClientConfiguration());
    }

    /**
     * Constructs a new <code>SesClient</code> instance with the specified configuration.
     * 
     * @param config the specified configuration, default inherit super class
     *            <code>com.baidubce.BceClientConfiguration</code>
     */
    public SesClient(SesClientConfiguration config) {
        super(config, SES_HANDLERS);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#verifyEmail(VerifyEmailRequest request)
     */
    public void verifyEmail(String emailAddress) {
        assertStringNotNullOrEmpty(emailAddress, "object emailAddress should not be null or empty");
        verifyEmail(new VerifyEmailRequest().withEmailAddress(emailAddress));
    }

    /**
     * Verify email address
     * 
     * @param request The request object includes the URL address to verify, refer to
     *            <code>com.baidubce.services.ses.model.VerifyEmailRequest</code>
     * 
     * @see com.baidubce.services.ses.model.VerifyEmailRequest
     */
    public void verifyEmail(VerifyEmailRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getEmailAddress(), "object emailAddress should not be null or empty");

        InternalRequest internalRequest =
                this.createRequest("verifiedEmail", request, HttpMethodName.PUT, request.getEmailAddress());

        this.invokeHttpClient(internalRequest, SesResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#listVerifiedEmail(SesRequest request)
     */
    public ListVerifiedEmailResponse listVerifiedEmail() {
        return listVerifiedEmail(new SesRequest());
    }

    /**
     * Get the list of verified email address
     * 
     * @param request The request object which it's <code>credentials</code> can be override
     * @return The response object includes the detail of verified email address, refer to
     *         <code>com.baidubce.services.ses.model.ListVerifiedEmailResponse</code>
     * 
     * @see com.baidubce.services.ses.model.ListVerifiedEmailResponse
     */
    public ListVerifiedEmailResponse listVerifiedEmail(SesRequest request) {
        InternalRequest internalRequest = this.createRequest("verifiedEmail", request, HttpMethodName.GET);
        return this.invokeHttpClient(internalRequest, ListVerifiedEmailResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#getVerifiedEmail(GetVerifiedEmailRequest request)
     */
    public GetVerifiedEmailResponse getVerifiedEmail(String emailAddress) {
        return getVerifiedEmail(new GetVerifiedEmailRequest().withEmailAddress(emailAddress));
    }

    /**
     * Get the detail of verified email address
     * 
     * @param request The request object includes email address to get
     * @return The response object includes the detail of verified email address, refer to
     *         <code>com.baidubce.services.ses.model.GetVerifiedEmailResponse</code>
     * 
     * @see com.baidubce.services.ses.model.GetVerifiedEmailRequest
     * @see com.baidubce.services.ses.model.GetVerifiedEmailResponse
     */
    public GetVerifiedEmailResponse getVerifiedEmail(GetVerifiedEmailRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getEmailAddress(), "object emailAddress should not be null or empty");
        checkIsEmail(request.getEmailAddress());

        InternalRequest internalRequest =
                this.createRequest("verifiedEmail", request, HttpMethodName.GET, request.getEmailAddress());
        return this.invokeHttpClient(internalRequest, GetVerifiedEmailResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#deleteVerifiedEmail( DeleteVerifiedEmailRequest request)
     */
    public void deleteVerifiedEmail(String emailAddress) {
        deleteVerifiedEmail(new DeleteVerifiedEmailRequest().withEmailAddress(emailAddress));
    }

    /**
     * Delete verified email address
     * 
     * @param request The request object includes the email address to delete, refer to
     *            <code>com.baidubce.services.ses.model.DeleteVerifiedEmailRequest</code>
     * 
     * @see com.baidubce.services.ses.model.DeleteVerifiedEmailRequest
     */
    public void deleteVerifiedEmail(DeleteVerifiedEmailRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getEmailAddress(), "object emailAddress should not be null or empty");
        checkIsEmail(request.getEmailAddress());

        InternalRequest internalRequest =
                this.createRequest("verifiedEmail", request, HttpMethodName.DELETE, request.getEmailAddress());
        this.invokeHttpClient(internalRequest, SesResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#verifyDomain( VerifyDomainRequest request)
     */
    public VerifyDomainResponse verifyDomain(String domainName) {
        return verifyDomain(new VerifyDomainRequest().withDomainName(domainName));
    }

    /**
     * Verify the domain
     * <p>
     * When this API is called successfully, it will return a token which is used to verify domain. You should put the
     * token into DSN setting as a TXT record, then SES can finish verifying automatically.
     * 
     * @param request The request object which includes the domain to verify
     * 
     * @see com.baidubce.services.ses.model.VerifyDomainRequest
     */
    public VerifyDomainResponse verifyDomain(VerifyDomainRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getDomainName(), "object domainName should not be null or empty");

        InternalRequest internalRequest =
                this.createRequest("verifiedDomain", request, HttpMethodName.PUT, request.getDomainName());
        return this.invokeHttpClient(internalRequest, VerifyDomainResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#verifyDKIM( VerifyDKIMRequest request)
     */
    public VerifyDKIMResponse verifyDKIM(String domainName) {
        return verifyDKIM(new VerifyDKIMRequest().withDomainName(domainName));
    }

    /**
     * Verify the DKIM
     * <p>
     * When this API is called successfully, it will return a group of tokens,you should use this tokens to create DSN
     * CNAME records, then SES can finish verifying automatically.
     * 
     * @param request The request object which includes the DKIM to verify
     * 
     * @return The response object includes a group of tokens
     * 
     * @see com.baidubce.services.ses.model.VerifyDKIMRequest
     * @see com.baidubce.services.ses.model.VerifyDKIMResponse
     */
    public VerifyDKIMResponse verifyDKIM(VerifyDKIMRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getDomainName(), "object domainName should not be null or empty");

        InternalRequest internalRequest =
                this.createRequest("verifiedDomain", request, HttpMethodName.PUT, request.getDomainName());
        internalRequest.addParameter("dkim", "true");

        return this.invokeHttpClient(internalRequest, VerifyDKIMResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#disableDKIM(EnableDKIMRequest request)
     */
    public void disableDKIM(String domainName) {
        disableDKIM(new EnableDKIMRequest().withDomainName(domainName));
    }

    /**
     * Disable the DKIM
     * <P>
     * Disable or enable DKIM the specifical email or domain, if enabled then SES will add DKIM for all the email about
     * the email and domain.
     * 
     * @param request The request object which includes domain to disable
     * 
     * @see com.baidubce.services.ses.model.EnableDKIMRequest
     */
    public void disableDKIM(EnableDKIMRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getDomainName(), "object domainName should not be null or empty");

        InternalRequest internalRequest =
                this.createRequest("verifiedDomain", request, HttpMethodName.PUT, request.getDomainName());
        internalRequest.addParameter("enable", "disableDkim");

        this.invokeHttpClient(internalRequest, SesResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#enableDKIM(EnableDKIMRequest request)
     */
    public void enableDKIM(String domainName) {
        enableDKIM(new EnableDKIMRequest().withDomainName(domainName));
    }

    /**
     * Enable the DKIM
     * <P>
     * Disable or enable DKIM the specifical email or domain, if enabled then SES will add DKIM for all the email about
     * the email and domain.
     * 
     * @param request The request object which includes domain to enable
     */
    public void enableDKIM(EnableDKIMRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getDomainName(), "object domainName should not be null or empty");

        InternalRequest internalRequest =
                this.createRequest("verifiedDomain", request, HttpMethodName.PUT, request.getDomainName());
        internalRequest.addParameter("enable", "enableDkim");

        this.invokeHttpClient(internalRequest, SesResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#listVerifiedDomain(SesRequest request)
     */
    public ListVerifiedDomainResponse listVerifiedDomain() {
        return listVerifiedDomain(new SesRequest());
    }

    /**
     * Get the list of verified domain
     * 
     * @param request The request object which it's <code>credentials</code> can be override
     * @return The response object which includes the detail of verified domain
     * 
     * @see com.baidubce.services.ses.model.ListVerifiedDomainResponse
     */
    public ListVerifiedDomainResponse listVerifiedDomain(SesRequest request) {
        InternalRequest internalRequest = this.createRequest("verifiedDomain", request, HttpMethodName.GET);
        return this.invokeHttpClient(internalRequest, ListVerifiedDomainResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#getVerifiedDomain( GetVerifiedDomainRequest request)
     */
    public GetVerifiedDomainResponse getVerifiedDomain(String domainName) {
        return getVerifiedDomain(new GetVerifiedDomainRequest().withDomainName(domainName));
    }

    /**
     * Get the detail of specifical verified domain
     * 
     * @param request The request object which includes the domain to get
     * @return The response object which includes the detail of verified domain
     * 
     * @see com.baidubce.services.ses.model.GetVerifiedDomainResponse
     */
    public GetVerifiedDomainResponse getVerifiedDomain(GetVerifiedDomainRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getDomainName(), "object domainName should not be null or empty");

        InternalRequest internalRequest =
                this.createRequest("verifiedDomain", request, HttpMethodName.GET, request.getDomainName());
        return this.invokeHttpClient(internalRequest, GetVerifiedDomainResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#deleteVerifiedDomain( DeleteVerifiedDomainRequest request)
     */
    public void deleteVerifiedDomain(String domainName) {
        deleteVerifiedDomain(new DeleteVerifiedDomainRequest().withDomainName(domainName));
    }

    /**
     * Delete the domain
     * 
     * @param request The request object which includes the domain to delete
     * 
     * @see com.baidubce.services.ses.model.DeleteVerifiedDomainRequest
     */
    public void deleteVerifiedDomain(DeleteVerifiedDomainRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getDomainName(), "object domainName should not be null or empty");

        InternalRequest internalRequest =
                this.createRequest("verifiedDomain", request, HttpMethodName.DELETE, request.getDomainName());
        this.invokeHttpClient(internalRequest, SesResponse.class);
    }

    /**
     * Send email.
     * <p>
     * Simple to send email without display name, all optional parameters use system default value.
     * 
     * @param from The sender, which is required
     * @param toAddr The receive, which is required
     * @param subject The title of the email, which is required
     * @param body The content of the email, which is required
     * @param attachmentFiles The array of attachment file. If you need send attachment then set it, it's optional
     *            parameters
     * 
     * @see com.baidubce.services.ses.SesClient#sendEmail(com.baidubce.services.ses.model.SendEmailRequest request)
     */
    public SendEmailResponse sendEmail(String from, String[] toAddr, String subject, String body,
            File...attachmentFiles) {
        return sendEmail(from, "", toAddr, subject, body, attachmentFiles);
    }

    /**
     * Send email.
     * <p>
     * Simple to send email, all optional parameters use system default value.
     * 
     * @param from The sender, which is required
     * @param displayName The display name of sender, which can be custom by the users themselves
     * @param toAddr The receive, which is required
     * @param subject The title of the email, which is required
     * @param body The content of the email, which is required
     * @param attachmentFiles The array of attachment file. If you need send attachment then set it, it's optional
     *            parameters
     * 
     * @see com.baidubce.services.ses.SesClient#sendEmail(com.baidubce.services.ses.model.SendEmailRequest request)
     */
    public SendEmailResponse sendEmail(String from, String displayName, String[] toAddr, String subject, String body,
            File...attachmentFiles) {
        SendEmailRequest request =
                buildSendEmailRequest(from, from, from, toAddr, new String[] { "" }, new String[] { "" }, subject,
                        body, 1, 1);
        request = fillDisplayName(request, displayName);
        request = fillAttachment(request, attachmentFiles);

        return sendEmail(request);
    }

    /**
     * Send email.
     * <p>
     * Simple to send email, partly optional parameters use system default value.
     * 
     * @param from The sender, which is required
     * @param displayName The display name of sender, which can be custom by the users themselves
     * @param toAddr The receive, which is required
     * @param ccAddr The CC, which is optional
     * @param bccAddr The BCC which is optional
     * @param subject The title of the email, which is required
     * @param body The content of the email, which is required
     * @param attachmentFiles The array of attachment file. If you need send attachment then set it, it's optional
     *            parameters
     * 
     * @see com.baidubce.services.ses.SesClient#sendEmail(com.baidubce.services.ses.model.SendEmailRequest request)
     */
    public SendEmailResponse sendEmail(String from, String displayName, String[] toAddr, String[] ccAddr,
            String[] bccAddr, String subject, String body, File...attachmentFiles) {
        SendEmailRequest request =
                buildSendEmailRequest(from, from, from, toAddr, ccAddr, bccAddr, subject, body, 1, 1);
        request = fillDisplayName(request, displayName);
        request = fillAttachment(request, attachmentFiles);

        return sendEmail(request);
    }

    /**
     * Send email.
     * <p>
     * Full to send email, all optional parameters need you set.
     * 
     * @param from The sender, which is required
     * @param displayName The display name of sender, which can be custom by the users themselves
     * @param returnPath Optional parameters
     * @param replyTo Optional parameters
     * @param toAddr The receive, which is required
     * @param ccAddr The CC, which is optional
     * @param bccAddr The BCC which is optional
     * @param subject The title of the email, which is required
     * @param body The content of the email, which is required
     * @param priority The priority of the email, which is Optional
     * @param charset The charset of the email, which is Optional
     * @param attachmentFiles The array of attachment file. If you need send attachment then set it, it's optional
     *            parameters
     * 
     * @see com.baidubce.services.ses.SesClient#sendEmail(com.baidubce.services.ses.model.SendEmailRequest request)
     */
    public SendEmailResponse sendEmail(String from, String displayName, String returnPath, String replyTo,
            String[] toAddr, String[] ccAddr, String[] bccAddr, String subject, String body, int priority, int charset,
            File...attachmentFiles) {
        SendEmailRequest request =
                buildSendEmailRequest(from, returnPath, replyTo, toAddr, ccAddr, bccAddr, subject, body, priority,
                        charset);
        request = fillDisplayName(request, displayName);
        request = fillAttachment(request, attachmentFiles);

        return sendEmail(request);
    }

    /**
     * Send email
     * <p>
     * This method allow you to construct request object by yourself.
     * 
     * @param request The request object which includes the parameters of sending mail, you can see detail from class
     *            <code>com.baidubce.services.ses.model.SendEmailRequest</code>
     * 
     * @see com.baidubce.services.ses.model.SendEmailRequest
     */
    public SendEmailResponse sendEmail(SendEmailRequest request) {
        checkNotNull(request, "object request should not be null.");
        checkNotNull(request.getMail(), "object mail of request should not be null.");

        // validate source
        checkNotNull(request.getMail().getSource(), "object source of request should not be null.");
        assertStringNotNullOrEmpty(request.getMail().getSource().getFrom(), "from should not be null or empty.");
        checkIsEmail(request.getMail().getSource().getFrom());

        // validate subject
        checkNotNull(request.getMail().getSubject(), "object subject of request should not be null.");
        assertStringNotNullOrEmpty(request.getMail().getSubject().getData(), "subject should not be null or empty.");
        if (request.getMail().getSubject().getCharset() != null
                && !Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 })
                        .contains(request.getMail().getSubject().getCharset())) {
            throw new IllegalArgumentException("illegal charset.");
        }

        // validate body
        checkNotNull(request.getMail().getMessage(), "object message of request should not be null.");
        checkNotNull(request.getMail().getMessage().getHtml(), "object html of request should not be null.");
        assertStringNotNullOrEmpty(request.getMail().getMessage().getHtml().getData(),
                "body should not be null or empty.");
        if (request.getMail().getMessage().getHtml().getCharset() != null
                && !Arrays.asList(new Integer[] { 0, 1, 2, 3, 4 }).contains(
                        request.getMail().getMessage().getHtml().getCharset())) {
            throw new IllegalArgumentException("illegal charset.");
        }

        // validate priority
        if (request.getMail().getPriority() != null
                && (request.getMail().getPriority() <= -1 || request.getMail().getPriority() >= 100)) {
            throw new IllegalArgumentException("illegal priority.");
        }

        // validate destination
        checkNotNull(request.getMail().getDestination(), "object destination of request should not be null.");
        assertListNotNullOrEmpty(request.getMail().getDestination().getToAddr(), "toAddr should not be null or empty.");
        for (Addr toAddr : request.getMail().getDestination().getToAddr()) {
            checkNotNull(toAddr, "object toAddr of request should not be null.");
            checkIsEmail(toAddr.getAddr());
        }

        InternalRequest internalRequest = this.createRequest("email", request, HttpMethodName.POST);
        // fill in the request payload
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));

        // send email
        return this.invokeHttpClient(internalRequest, SendEmailResponse.class);
    }

    /**
     * Set feedback
     * 
     * @param request The request object which includes the feedback information to set
     * 
     * @see com.baidubce.services.ses.model.SetFeedbackRequest
     */
    public void setFeedback(SetFeedbackRequest request) {
        checkNotNull(request, "object request should not be null.");
        if (request.getEmail() != null && request.getEmail().trim().length() > 0) {
            checkIsEmail(request.getEmail());
        }
        if (request.getType() == null) {
            request.setType(1);
        }
        if (!Arrays.asList(new Integer[] { 1, 2, 3 }).contains(request.getType())) {
            throw new IllegalArgumentException("illegal type.");
        }

        InternalRequest internalRequest = this.createRequest("feedback", request, HttpMethodName.PUT);

        // fill in the request payload
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));

        this.invokeHttpClient(internalRequest, SesResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#getFeedback(SesRequest request)
     */
    public GetFeedbackResponse getFeedback() {
        return getFeedback(new SesRequest());
    }

    /**
     * Get feedback
     * 
     * @param request The request object which it's <code>credentials</code> can be override
     * @return The response object which includes the feedback information
     * 
     * @see com.baidubce.services.ses.model.GetFeedbackResponse
     */
    public GetFeedbackResponse getFeedback(SesRequest request) {
        InternalRequest internalRequest = this.createRequest("feedback", request, HttpMethodName.GET);
        return this.invokeHttpClient(internalRequest, GetFeedbackResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#getQuota(SesRequest request)
     */
    public GetQuotaResponse getQuota() {
        return getQuota(new SesRequest());
    }

    /**
     * Get quota
     * 
     * @param request The request object which it's <code>credentials</code> can be override
     * @return The response object which includes the detail of quota
     * 
     * @see com.baidubce.services.ses.model.GetQuotaResponse
     */
    public GetQuotaResponse getQuota(SesRequest request) {
        InternalRequest internalRequest = this.createRequest("quota", request, HttpMethodName.GET);
        return this.invokeHttpClient(internalRequest, GetQuotaResponse.class);
    }

    /**
     * Set quota
     * 
     * @param request The request object which includes the information of quota to set
     * 
     * @see com.baidubce.services.ses.model.SetQuotaRequest
     */
    public void setQuota(SetQuotaRequest request) {
        checkNotNull(request, "object request should not be null.");
        checkIsInteger(request.getMaxPerDay());
        checkIsInteger(request.getMaxPerSecond());

        InternalRequest internalRequest = this.createRequest("quota", request, HttpMethodName.PUT);

        // fill in the request payload
        internalRequest = fillRequestPayload(internalRequest, JsonUtils.toJsonString(request));

        this.invokeHttpClient(internalRequest, SesResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#listRecipientBlacklist(SesRequest request)
     */
    public ListRecipientBlacklistResponse listRecipientBlacklist() {
        return listRecipientBlacklist(new SesRequest());
    }

    /**
     * Get the list of blacklist
     * 
     * @param request The request object which it's <code>credentials</code> can be override
     * @return The response which includes the detail of blacklist
     * 
     * @see com.baidubce.services.ses.model.ListRecipientBlacklistResponse
     */
    public ListRecipientBlacklistResponse listRecipientBlacklist(SesRequest request) {
        InternalRequest internalRequest = this.createRequest("recipientBlacklist", request, HttpMethodName.GET);
        return this.invokeHttpClient(internalRequest, ListRecipientBlacklistResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#isInRecipientBlacklist( IsInRecipientBlacklistRequest request)
     */
    public IsInRecipientBlacklistResponse isInRecipientBlacklist(String emailAddress) {
        return isInRecipientBlacklist(new IsInRecipientBlacklistRequest().withEmailAddress(emailAddress));
    }

    /**
     * Query the special receiver is in blacklist or not
     * 
     * @param request The request object which includes the special receiver
     * @return The response result which includes the result of receiver in blacklist or not
     * 
     * @see com.baidubce.services.ses.model.IsInRecipientBlacklistResponse
     */
    public IsInRecipientBlacklistResponse isInRecipientBlacklist(IsInRecipientBlacklistRequest request) {
        checkNotNull(request, "object request should not be null.");
        assertStringNotNullOrEmpty(request.getEmailAddress(), "object emailAddress should not be null or empty");

        InternalRequest internalRequest =
                this.createRequest("recipientBlacklist", request, HttpMethodName.GET, request.getEmailAddress());
        return this.invokeHttpClient(internalRequest, IsInRecipientBlacklistResponse.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.baidubce.services.ses.SesClient#getFailedReason(SesRequest request)
     */
    public GetFailedReasonResponse getFailedReason() {
        return getFailedReason(new SesRequest());
    }

    /**
     * Query the failed reason
     * 
     * @param request The request object which it's <code>credentials</code> can be override
     * @return The response which includes the detail of failed reason
     * 
     * @see com.baidubce.services.ses.model.GetFailedReasonResponse
     */
    public GetFailedReasonResponse getFailedReason(SesRequest request) {
        InternalRequest internalRequest = this.createRequest("failedReason", request, HttpMethodName.GET);
        return this.invokeHttpClient(internalRequest, GetFailedReasonResponse.class);
    }

    private SendEmailRequest fillAttachment(SendEmailRequest request, File...attachmentFiles) {
        if (attachmentFiles != null && attachmentFiles.length > 0) {
            // fetch attachments from Files
            for (File file : attachmentFiles) {
                try {
                    InputStream in = new FileInputStream(file);

                    String filedata = "";
                    try {
                        filedata = getBASE64EncoderStrFromInputStream(in);
                    } catch (IOException e) {
                        logger.error("fetch attachment filedata failure.", e);
                        throw new BceClientException("fetch attachment filedata failure.", e);
                    }
                    request = fillAttachment(request, file.getName(), filedata);
                } catch (FileNotFoundException e) {
                    logger.error("attachment not found.", e);
                    throw new BceClientException("attachment not found.", e);
                }
            }
        }

        return request;
    }

    private SendEmailRequest fillAttachment(SendEmailRequest request, String filename, String filedata) {
        Mail mail = request.getMail();

        Attachment attachment = new Attachment();
        attachment.setFiledata(new FileData().withData(filedata));
        attachment.setFilename(filename);

        List<Attachment> attachments = mail.getAttachments();
        if (attachments == null) {
            attachments = new ArrayList<SendEmailRequest.Mail.Attachment>();
        }
        attachments.add(attachment);

        mail.setAttachments(attachments);

        return request;
    }

    private SendEmailRequest buildSendEmailRequest(String from, String returnPath, String replyTo, String[] toAddr,
            String[] ccAddr, String[] bccAddr, String subject, String body, int priority, int charset) {
        // fill with source object
        Source source = new Source(from, returnPath, replyTo);

        // fill with destination object
        Destination destination =
                new Destination(Addr.asAddrList(toAddr), Addr.asAddrList(ccAddr), Addr.asAddrList(bccAddr));

        // fill with message object
        Message message = new Message().withHtml(new Subject().withData(body).withCharset(charset));

        // fill with mail object
        Mail mail =
                new Mail(source, destination, new Subject().withData(subject).withCharset(charset), priority, message);

        // fill with request object
        SendEmailRequest request = new SendEmailRequest();
        request.setMail(mail);

        return request;
    }

    private SendEmailRequest fillDisplayName(SendEmailRequest request, String displayName) {
        if (request != null && request.getMail() != null && request.getMail().getSource() != null) {
            request.getMail().getSource().setDisplayName(displayName);
        }
        return request;
    }

}
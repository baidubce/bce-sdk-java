/*
 * Copyright (C) 2026 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cas;

import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.cas.model.request.ApplyCertRequest;
import com.baidubce.services.cas.model.request.BatchRequest;
import com.baidubce.services.cas.model.request.CertDetailRequest;
import com.baidubce.services.cas.model.request.DownloadCertFileRequest;
import com.baidubce.services.cas.model.request.ListCertsRequest;
import com.baidubce.services.cas.model.request.NewOrderConfig;
import com.baidubce.services.cas.model.request.PriceConfig;
import com.baidubce.services.cas.model.request.RenewOrderConfig;
import com.baidubce.services.cas.model.request.ReplaceCertRequest;
import com.baidubce.services.cas.model.response.ApiOrderResponse;
import com.baidubce.services.cas.model.response.CasResponse;
import com.baidubce.services.cas.model.response.CertContactResponse;
import com.baidubce.services.cas.model.response.CertDetails;
import com.baidubce.services.cas.model.response.CertDetailsListResponse;
import com.baidubce.services.cas.model.response.CertFileResponse;
import com.baidubce.services.cas.model.response.CertPkiResponse;
import com.baidubce.services.cas.model.response.CheckPurchasableResponse;
import com.baidubce.services.cas.model.response.PriceResponse;
import com.baidubce.services.cas.model.response.ReplaceResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Client for the CAS (SSL certificate) open api.
 */
public class CasClient extends AbstractBceClient {

    private static final String VERSION = "/v3/openapi";

    private static final String ORDER = "/order";

    private static final String CERTS = "/certs";

    private static final String QUERY = "/query";

    private static final String PRICE = "/price";

    private static final String TRANS = "/trans";

    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    private static final HttpResponseHandler[] HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new CasBinaryResponseHandler(),
            new BceJsonResponseHandler()
    };

    public CasClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new client to invoke service methods on CAS.
     *
     * @param bceClientConfiguration the BCE client configuration options
     */
    public CasClient(BceClientConfiguration bceClientConfiguration) {
        super(bceClientConfiguration, HANDLERS);
    }

    public static CasClient createCasClient(String accessKey, String secretKey, String endpoint) {
        BceClientConfiguration bceClientConfiguration = new BceClientConfiguration().withEndpoint(endpoint)
                .withCredentials(new DefaultBceCredentials(accessKey, secretKey));
        return new CasClient(bceClientConfiguration);
    }

    /**
     * Creates a new certificate order and pays for it.
     *
     * @param config configuration of the order
     * @return ids of the certificates created by the order
     */
    public ApiOrderResponse createOrder(NewOrderConfig config) {
        checkNotNull(config, "config should not be null.");
        InternalRequest internalRequest = this.createRequest(config, HttpMethodName.POST, ORDER);
        internalRequest.addParameter("new", "");
        fillPayload(internalRequest, config);
        return this.invokeHttpClient(internalRequest, ApiOrderResponse.class);
    }

    /**
     * Creates a certificate renewal order and pays for it.
     *
     * @param config configuration of the order
     * @return ids of the certificates created by the order
     */
    public ApiOrderResponse createRenewOrder(RenewOrderConfig config) {
        checkNotNull(config, "config should not be null.");
        InternalRequest internalRequest = this.createRequest(config, HttpMethodName.POST, ORDER);
        internalRequest.addParameter("renew", "");
        fillPayload(internalRequest, config);
        return this.invokeHttpClient(internalRequest, ApiOrderResponse.class);
    }

    /**
     * Applies for a certificate that has already been ordered.
     *
     * @param certId  id of the certificate
     * @param request application parameters
     */
    public void applyCert(String certId, ApplyCertRequest request) {
        checkStringNotEmpty(certId, "certId should not be empty.");
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CERTS, certId);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, CasResponse.class);
    }

    /**
     * Re-issues (replaces) an existing certificate.
     *
     * @param certId  id of the certificate
     * @param request re-issue parameters
     * @return order id of the re-issue request
     */
    public ReplaceResponse replaceCert(String certId, ReplaceCertRequest request) {
        checkStringNotEmpty(certId, "certId should not be empty.");
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CERTS, certId, "/replace");
        request.setCertId(certId);
        fillPayload(internalRequest, request);
        return this.invokeHttpClient(internalRequest, ReplaceResponse.class);
    }

    /**
     * Downloads the confirmation letter template of a certificate.
     *
     * @param certId id of the certificate
     * @return content of the template, in pdf
     */
    public byte[] downloadConfirmLetterTemplate(String certId) {
        checkStringNotEmpty(certId, "certId should not be empty.");
        InternalRequest internalRequest = this.createRequest(null, HttpMethodName.GET, CERTS, certId, "/letter");
        return this.invokeHttpClient(internalRequest, CertFileResponse.class).getContent();
    }

    /**
     * Uploads the signed confirmation letter of a certificate.
     *
     * @param certId id of the certificate
     * @param data   content of the signed letter
     */
    public void uploadConfirmLetter(String certId, byte[] data) {
        checkStringNotEmpty(certId, "certId should not be empty.");
        checkNotNull(data, "data should not be null.");
        InternalRequest internalRequest = this.createRequest(null, HttpMethodName.PUT, CERTS, certId, "/letter");
        attachBytesToBody(data, "application/octet-stream", internalRequest);
        this.invokeHttpClient(internalRequest, CasResponse.class);
    }

    /**
     * Cancels the pending application of a certificate.
     *
     * @param certId id of the certificate
     */
    public void cancelApply(String certId) {
        checkStringNotEmpty(certId, "certId should not be empty.");
        InternalRequest internalRequest = this.createRequest(null, HttpMethodName.DELETE, CERTS, certId);
        internalRequest.addParameter("cancel", "");
        this.invokeHttpClient(internalRequest, CasResponse.class);
    }

    /**
     * Deletes a certificate whose application has failed or which has expired.
     *
     * @param certId id of the certificate
     */
    public void deleteApply(String certId) {
        checkStringNotEmpty(certId, "certId should not be empty.");
        InternalRequest internalRequest = this.createRequest(null, HttpMethodName.DELETE, CERTS, certId);
        internalRequest.addParameter("delete", "");
        this.invokeHttpClient(internalRequest, CasResponse.class);
    }

    /**
     * Downloads the issued certificate package.
     *
     * @param certId  id of the certificate
     * @param request download parameters, mainly the package format
     * @return content of the package
     */
    public byte[] downloadCertFile(String certId, DownloadCertFileRequest request) {
        checkStringNotEmpty(certId, "certId should not be empty.");
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CERTS, certId);
        internalRequest.addParameter("download", "");
        fillPayload(internalRequest, request);
        return this.invokeHttpClient(internalRequest, CertFileResponse.class).getContent();
    }

    /**
     * Queries the detail of a certificate.
     *
     * @param certId  id of the certificate
     * @param request query parameters
     * @return detail of the certificate
     */
    public CertDetails getCertDetails(String certId, CertDetailRequest request) {
        checkStringNotEmpty(certId, "certId should not be empty.");
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CERTS, certId, "/detail");
        request.setCertId(certId);
        fillPayload(internalRequest, request);
        return this.invokeHttpClient(internalRequest, CertDetails.class);
    }

    /**
     * Queries the detail of a certificate.
     *
     * @param certId id of the certificate
     * @return detail of the certificate
     */
    public CertDetails getCertDetails(String certId) {
        CertDetailRequest request = new CertDetailRequest();
        request.setCertId(certId);
        return getCertDetails(certId, request);
    }

    /**
     * Queries the PKI information of a certificate.
     *
     * @param certId id of the certificate
     * @return PKI information of the certificate
     */
    public CertPkiResponse getCertPki(String certId) {
        checkStringNotEmpty(certId, "certId should not be empty.");
        InternalRequest internalRequest = this.createRequest(null, HttpMethodName.GET, CERTS, certId, "/pki");
        return this.invokeHttpClient(internalRequest, CertPkiResponse.class);
    }

    /**
     * Queries the company and contact information of a certificate.
     *
     * @param certId id of the certificate
     * @return contact information of the certificate
     */
    public CertContactResponse getCertContact(String certId) {
        checkStringNotEmpty(certId, "certId should not be empty.");
        InternalRequest internalRequest = this.createRequest(null, HttpMethodName.GET, CERTS, certId, "/contact");
        return this.invokeHttpClient(internalRequest, CertContactResponse.class);
    }

    /**
     * Lists the certificates of the current account filtered by specific conditions.
     *
     * @param request query parameters, supporting pageNo, pageSize, brand, certType, status and order
     * @return a page of certificate details
     */
    public CertDetailsListResponse listCerts(ListCertsRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, QUERY);
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        if (!Strings.isNullOrEmpty(request.getBrand())) {
            internalRequest.addParameter("brand", request.getBrand());
        }
        if (!Strings.isNullOrEmpty(request.getCertType())) {
            internalRequest.addParameter("certType", request.getCertType());
        }
        if (!Strings.isNullOrEmpty(request.getStatus())) {
            internalRequest.addParameter("status", request.getStatus());
        }
        if (!Strings.isNullOrEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        return this.invokeHttpClient(internalRequest, CertDetailsListResponse.class);
    }

    /**
     * Lists the certificates of the current account.
     *
     * @return a page of certificate details
     */
    public CertDetailsListResponse listCerts() {
        return listCerts(new ListCertsRequest());
    }

    /**
     * Checks whether a free certificate can still be purchased.
     *
     * @return the purchasable state of free certificates
     */
    public CheckPurchasableResponse checkFreeCertPurchasable() {
        InternalRequest internalRequest = this.createRequest(null, HttpMethodName.GET, QUERY, "/check");
        return this.invokeHttpClient(internalRequest, CheckPurchasableResponse.class);
    }

    /**
     * Calculates the price of a certificate.
     *
     * @param config configuration to price
     * @return the calculated price
     */
    public PriceResponse calculatePrice(PriceConfig config) {
        checkNotNull(config, "config should not be null.");
        InternalRequest internalRequest = this.createRequest(config, HttpMethodName.POST, PRICE);
        fillPayload(internalRequest, config);
        return this.invokeHttpClient(internalRequest, PriceResponse.class);
    }

    /**
     * Transfers a batch of certificates to another user.
     *
     * @param newUserId id of the user receiving the certificates
     * @param request   ids of the certificates to transfer
     */
    public void transCerts(String newUserId, BatchRequest<String> request) {
        checkStringNotEmpty(newUserId, "newUserId should not be empty.");
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, TRANS, "/batch", newUserId);
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, CasResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest    the original BCE request created by the user, may be null
     * @param httpMethod    the HTTP method to use when sending the request
     * @param pathVariables the optional variables used in the URI path
     *
     * @return a new request object populated with endpoint, resource path and specific parameters to send
     */
    private InternalRequest createRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        if (bceRequest != null) {
            request.setCredentials(bceRequest.getRequestCredentials());
        }

        return request;
    }

    /**
     * Fills the content of the http request with the json serialization of the given request.
     *
     * <p>Only HttpMethodName.POST and HttpMethodName.PUT are supported.
     *
     * @param internalRequest the http request to fill
     * @param bceRequest      the original request, as created by the user
     */
    protected void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() != HttpMethodName.POST
                && internalRequest.getHttpMethod() != HttpMethodName.PUT) {
            return;
        }
        byte[] content;
        try {
            content = JsonUtils.toJsonString(bceRequest).getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }
        attachBytesToBody(content, DEFAULT_CONTENT_TYPE, internalRequest);
    }

    /**
     * Attaches raw bytes as the body of the http request.
     *
     * @param content     the bytes to send
     * @param contentType the content type of the body
     * @param httpRequest the http request to fill
     */
    private void attachBytesToBody(byte[] content, String contentType, InternalRequest httpRequest) {
        httpRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(content.length));
        httpRequest.addHeader(Headers.CONTENT_TYPE, contentType);
        httpRequest.setContent(RestartableInputStream.wrap(content));
    }
}

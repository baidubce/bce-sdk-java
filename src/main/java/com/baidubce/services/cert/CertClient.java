/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cert;

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
import com.baidubce.services.cert.model.CertCreateRequest;
import com.baidubce.services.cert.model.CertCreateResponse;
import com.baidubce.services.cert.model.CertListResponse;
import com.baidubce.services.cert.model.CertRequest;
import com.baidubce.services.cert.model.CertResponse;
import com.baidubce.services.cert.model.CertUpdateNameRequest;
import com.baidubce.services.cert.model.CertificateMeta;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Client for cert public api.
 */
public class CertClient extends AbstractBceClient {

    private static final String CERT_BASE_URL = "/certificate";

    private static final String VERSION = "/v1";

    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date", "x-bce-console-rpc-id"};

    private static final HttpResponseHandler[] handlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    private boolean internal;

    private String accessKey;

    private String secretKey;

    protected CertClient(String accessKey, String secretKey, BceClientConfiguration bceClientConfiguration,
                         boolean internal) {
        super(bceClientConfiguration, handlers);
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.internal = internal;
    }

    public static CertClient createCertClient(String accessKey, String secretKey, String endpoint) {
        BceClientConfiguration bceClientConfiguration = new BceClientConfiguration().withEndpoint(endpoint)
                .withCredentials(new DefaultBceCredentials(accessKey, secretKey));
        return new CertClient(accessKey, secretKey, bceClientConfiguration, false);
    }

    public CertCreateResponse createCert(CertCreateRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CERT_BASE_URL);
        attachRequestToBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, CertCreateResponse.class);

    }

    public CertListResponse listUserCerts() {
        CertRequest request = new CertRequest();
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CERT_BASE_URL);
        return this.invokeHttpClient(internalRequest, CertListResponse.class);

    }

    public CertificateMeta getCertInfo(String certId) {
        CertRequest request = new CertRequest();
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CERT_BASE_URL, certId);
        return this.invokeHttpClient(internalRequest, CertificateMeta.class);
    }

    public void updateCertName(String certId, String certName) {
        CertUpdateNameRequest request = new CertUpdateNameRequest(certName);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT,
                CERT_BASE_URL, certId);
        attachRequestToBody(request, internalRequest);
        internalRequest.addParameter("certName", "");
        this.invokeHttpClient(internalRequest, CertResponse.class);
    }

    public void delete(String certId) {
        CertRequest request = new CertRequest();
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, CERT_BASE_URL, certId);
        this.invokeHttpClient(internalRequest, CertResponse.class);
    }

    public void replaceCertData(String certId, CertCreateRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, CERT_BASE_URL, certId);
        attachRequestToBody(request, internalRequest);
        internalRequest.addParameter("certData", "");
        this.invokeHttpClient(internalRequest, CertResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest    The original BCE request created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     *
     * @return A new request object populated with endpoint, resource path and specific
     * parameters to send.
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
        request.setCredentials(bceRequest.getRequestCredentials());

        return request;
    }

    /**
     * put json object into http content for put or post request.
     *
     * @param request     json object of rest request
     * @param httpRequest http request object
     */
    private void attachRequestToBody(AbstractBceRequest request, InternalRequest httpRequest) {
        byte[] content;
        try {
            content = JsonUtils.toJsonString(request).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("utf-8 encoding not supported!", e);
        }
        httpRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(content.length));
        httpRequest.addHeader(Headers.CONTENT_TYPE, "application/json; charset=utf-8");
        httpRequest.setContent(RestartableInputStream.wrap(content));
    }
}

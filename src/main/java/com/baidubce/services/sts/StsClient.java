/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.sts;

import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkIsTrue;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.services.sts.model.GetSessionTokenRequest;
import com.baidubce.services.sts.model.GetSessionTokenResponse;
import com.baidubce.util.HttpUtils;

/**
 * Provides the client for accessing the Baidu Security Token Service.
 */
public class StsClient extends AbstractBceClient {
    private static final String GET_SESSION_TOKEN_PATH = "sessionToken";

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static HttpResponseHandler[] stsHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceStsJsonResponseHandler()
    };

    /**
     * Constructs a new Sts client.
     */
    public StsClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new client using the client configuration.
     *
     * @param clientConfiguration The client configuration options controlling how this client
     *                            connects to Sts services (e.g. proxy settings, retry counts, etc).
     */
    public StsClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, stsHandlers);
    }

    /**
     * Get a set of temporary security credentials representing your account, with default request options
     *
     */
    public GetSessionTokenResponse getSessionToken() {
        return this.getSessionToken(new GetSessionTokenRequest());
    }

    /**
     * Get a set of temporary security credentials representing your account
     * <p>
     * An extra ACL string can be set in the request, which specify permissions for the returning credentials
     *
     * @param request The object containing options for the request
     *
     */
    public GetSessionTokenResponse getSessionToken(GetSessionTokenRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");
        checkIsTrue(request.getDurationSeconds() > 0, "the durationSeconds parameter should be greater than zero");

        InternalRequest internalRequest = new InternalRequest(HttpMethodName.POST,
                HttpUtils.appendUri(this.getEndpoint(), URL_PREFIX, GET_SESSION_TOKEN_PATH));
        if (request.getDurationSeconds() != null) {
            internalRequest.addParameter("durationSeconds", String.valueOf(request.getDurationSeconds()));
        }
        internalRequest.setCredentials(request.getRequestCredentials());
        internalRequest.addHeader(Headers.CONTENT_LENGTH,
                String.valueOf(request.getAcl() == null ? 0 : request.getAcl().length()));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        if (request.getAcl() != null) {
            internalRequest.setContent(RestartableInputStream.wrap(request.getAcl().getBytes()));
        }
        return this.invokeHttpClient(internalRequest, GetSessionTokenResponse.class);
    }
}

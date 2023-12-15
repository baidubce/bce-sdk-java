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
package com.baidubce.services.probe;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.probe.model.CreateProbeRequest;
import com.baidubce.services.probe.model.CreateProbeResponse;
import com.baidubce.services.probe.model.DeleteProbeRequest;
import com.baidubce.services.probe.model.GetProbeRequest;
import com.baidubce.services.probe.model.GetProbeResponse;
import com.baidubce.services.probe.model.ListProbeRequest;
import com.baidubce.services.probe.model.ListProbeResponse;
import com.baidubce.services.probe.model.UpdateProbeRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class ProbeClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProbeClient.class);

    private static final String VERSION = "v1";
    private static final String PROBE_PREFIX = "probe";

    /**
     * Responsible for handling httpResponses from all network service calls.
     */
    private static final HttpResponseHandler[] PROBE_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on Probe.
     */
    public ProbeClient() {
        this(new ProbeClientConfiguration());
    }

    /**
     * Constructs a new Probe client using the client configuration to access Probe.
     *
     * @param clientConfiguration The Probe client configuration options controlling how this client
     *                            connects to Probe (e.g. proxy settings, retry counts, etc).
     */
    public ProbeClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, PROBE_HANDLERS);
    }


    /**
     * Creates and initializes a new request object for the specified Probe resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest    The original request, as created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();

        path.add(VERSION);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
    }

    /**
     * The method to fill the internalRequest's content field with bceRequest.
     * Only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     *                        any additional headers or parameters, and execute.
     * @param bceRequest      The original request, as created by the user.
     */
    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            String strJson = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson = null;
            try {
                requestJson = strJson.getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Unsupported encode.", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
            internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        }
    }

    /**
     * The default method to generate the random String for clientToken if the optional parameter clientToken
     * is not specified by the user.
     * <p/>
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }


    /**
     * Create a Probe with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     * <p/>
     *
     * @param request The request containing all options for creating a probe.
     * @return probeId newly created
     * @throws BceClientException
     */
    public CreateProbeResponse createProbe(CreateProbeRequest request)
            throws BceClientException {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, PROBE_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateProbeResponse.class);
    }

    /**
     * Return a list of probes owned by the authenticated user.
     *
     * @return The response containing a list of probes owned by the authenticated user.
     */
    public ListProbeResponse listProbes() {
        return listProbes(new ListProbeRequest());
    }

    /**
     * Return a list of probes owned by the authenticated user.
     *
     * @param request The request containing all options for listing own's probe.
     * @return The response containing a list of probes owned by the authenticated user.
     */
    public ListProbeResponse listProbes(ListProbeRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, PROBE_PREFIX);
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }

        return invokeHttpClient(internalRequest, ListProbeResponse.class);
    }

    /**
     * Get the detail information of specified probe.
     *
     * @param probeId The id of the probe.
     * @return A probe detail model for the probe.
     */
    public GetProbeResponse getProbe(String probeId) {
        return getProbe(new GetProbeRequest().setProbeId(probeId));
    }

    private GetProbeResponse getProbe(GetProbeRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getProbeId(), "request probeId should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, PROBE_PREFIX,
                request.getProbeId());
        return this.invokeHttpClient(internalRequest, GetProbeResponse.class);
    }

    /**
     * update probe info
     *
     * @param request you can update :name、description、destIp、destPort
     */
    public void updateProbe(UpdateProbeRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, PROBE_PREFIX,
                request.getProbeId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the specified probe owned by the user.
     *
     * @param probeId The id of the vpc probe delete.
     */
    public void deleteProbe(String probeId) {
        deleteProbe(new DeleteProbeRequest().setProbeId(probeId));
    }

    public void deleteProbe(String probeId, String clientToken) {
        deleteProbe(new DeleteProbeRequest().setProbeId(probeId).setClientToken(clientToken));
    }

    private void deleteProbe(DeleteProbeRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, PROBE_PREFIX,
                request.getProbeId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }
}

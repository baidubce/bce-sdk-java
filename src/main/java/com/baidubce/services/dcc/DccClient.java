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
package com.baidubce.services.dcc;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.dcc.model.GetDedicatedHostRequest;
import com.baidubce.services.dcc.model.GetDedicatedHostResponse;
import com.baidubce.services.dcc.model.ListDedicatedHostsRequest;
import com.baidubce.services.dcc.model.ListDedicatedHostsResponse;
import com.baidubce.util.HttpUtils;
import com.google.common.base.Strings;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Dedicated Cloud Compute Service(DCC).
 */
public class DccClient extends AbstractBceClient {
    /**
     * DCC API pathVersion
     */
    private static final String VERSION = "v1";

    private static final String PREFIX = "dedicatedHost";

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static HttpResponseHandler[] dccHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public DccClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new InstanceClient to invoke service methods on dcc host.
     *
     * @param clientConfiguration The BCE client configuration options.
     */
    public DccClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, dccHandlers);
    }

    /**
     * Get the detail information of specified dcc.
     *
     * @param dedicatedHostId The id of the DCC.
     * @return A dcc detail model for the dedicatedHostId.
     */
    public GetDedicatedHostResponse getDedicatedHost(String dedicatedHostId) {
        return getDedicatedHost(new GetDedicatedHostRequest().withDedicatedHostId(dedicatedHostId));
    }

    /**
     * Get the detail information of specified dcc.
     *
     * @param request The request containing all options for getting the instance info.
     * @return A dcc detail model for the dedicatedHostId.
     */
    public GetDedicatedHostResponse getDedicatedHost(GetDedicatedHostRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getDedicatedHostId(), "request dedicatedHostId should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, request.getDedicatedHostId());
        return this.invokeHttpClient(internalRequest, GetDedicatedHostResponse.class);
    }

    /**
     * get a list of hosts owned by the authenticated user and default conditions
     * @return
     */
    public ListDedicatedHostsResponse listDedicatedHosts() {
        return listDedicatedHosts(new ListDedicatedHostsRequest());
    }

    /**
     * get a list of hosts owned by the authenticated user and specified conditions
     *
     * @param request The request containing all options for query
     * @return
     */
    public ListDedicatedHostsResponse listDedicatedHosts(ListDedicatedHostsRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, null);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() >= 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getZoneName())) {
            internalRequest.addParameter("zoneName", request.getZoneName());
        }
        return invokeHttpClient(internalRequest, ListDedicatedHostsResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest The original BCE request created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific
     *         parameters to send.
     */
    private InternalRequest createRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);
        path.add(PREFIX);

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
}

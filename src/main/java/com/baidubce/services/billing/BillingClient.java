/*
 * Copyright (c) 2014-2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.billing;

import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.baidubce.services.billing.model.ResourceMonthBillRequest;
import com.baidubce.services.billing.model.ResourceMonthBillResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Provides the client for accessing the Baidu Billing Service(billing).
 */
public class BillingClient extends AbstractBceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingClient.class);

    private static final String VERSION_V1 = "v1";
    private static final String BILL = "bill";
    private static final String RESOURCE = "resource";
    private static final String MONTH = "month";

    /**
     * Responsible for handling httpResponses from all billing service calls.
     */
    private static HttpResponseHandler[] billingHandlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on billing with default configuration.
     */
    public BillingClient() {
        this(new BillingClientConfiguration());
    }

    /**
     * Constructs a new client to invoke service methods on billing default configuration.
     */
    public BillingClient(BceClientConfiguration config) {
        super(config, billingHandlers);
    }

    /**
     * Query the detail resource month bills.
     * Set the parameter queryAccountId if want to query the sub accout's bills whole is in finance circle.
     *
     * @param request The request containing all options for getting the bills info.
     * @return detail resource month bills.
     */
    public ResourceMonthBillResponse getResourceMonthBill(ResourceMonthBillRequest request) {
        checkNotNull(request, "The parameter request should NOT be null.");

        checkStringNotEmpty(request.getMonth(), "The parameter month should NOT be null");
        checkStringNotEmpty(request.getProductType(), "The parameter productType should NOT be null");

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VERSION_V1, BILL, RESOURCE, MONTH);
        internalRequest.addParameter(MONTH, request.getMonth());
        internalRequest.addParameter("productType", request.getProductType());

        if (request.getServiceType() != null) {
            internalRequest.addParameter("serviceType", request.getServiceType());
        }
        if (request.getQueryAccountId() != null) {
            internalRequest.addParameter("queryAccountId", request.getQueryAccountId());
        }
        if (request.getPageNo() != null) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() != null) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }

        return invokeHttpClient(internalRequest, ResourceMonthBillResponse.class);
    }

    /**
     * Creates and initializes a new request object for the specified billing resource. This method is responsible
     * for determining HTTP method, URI path,credentials and request body for POST method.
     *
     * @param bceRequest The original request, as created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
            String... pathVariables) {
        List<String> path = new ArrayList<String>();    // build URL paths

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));

        // get a InternalRequest instance and set headers
        InternalRequest request = new InternalRequest(httpMethod, uri);
        request.setCredentials(bceRequest.getRequestCredentials());

        if (httpMethod == HttpMethodName.POST || httpMethod == HttpMethodName.PUT) {
            fillRequestPayload(request, bceRequest);
        }
        return request;
    }

    /**
     * The method to fill the internalRequest's content field with bceRequest.
     * Only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     * @param bceRequest The original request, as created by the user.
     */
    private void fillRequestPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
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
     * <p>
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }

}

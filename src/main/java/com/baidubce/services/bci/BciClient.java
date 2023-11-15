/*
 * Copyright (c) 2023 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bci;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.baidubce.services.bci.model.instance.CreateInstanceRequest;
import com.baidubce.services.bci.model.instance.CreateInstanceResponse;
import com.baidubce.services.bci.model.instance.DeleteInstanceRequest;
import com.baidubce.services.bci.model.instance.DeleteInstanceResponse;

import com.baidubce.services.bci.model.instance.GetInstanceRequest;
import com.baidubce.services.bci.model.instance.GetInstanceResponse;
import com.baidubce.services.bci.model.instance.ListInstancesRequest;
import com.baidubce.services.bci.model.instance.ListInstancesResponse;
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
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the Baidu Cloud network Service bci part.
 */
public class BciClient extends AbstractBceClient {

    /**
     * The logger for this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BciClient.class);


    private static final String MARKER = "marker";
    private static final String MAX_KEYS = "maxKeys";
    private static final String KEYWORD_TYPE = "keywordType";
    private static final String KEYWORD = "keyword";

    /**
     * The version of bci service.
     */
    private static final String VERSION = "v2";

    /**
     * The prefix of bci service.
     */
    private static final String BCI_PREFIX = "instance";

    /**
     * The prefix of bci batch del.
     */
    private static final String BATCH_DEL = "batchDel";

    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String INSTANCE_ID_MESSAGE_KEY = "instanceId";

    /**
     * Responsible for handling httpResponses from all network service calls.
     */
    private static final HttpResponseHandler[] BCI_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on network.
     */
    public BciClient() {
        this(new BciClientConfiguration());
    }

    /**
     * Constructs a new network client using the client configuration to access network.
     *
     * @param clientConfiguration The network client configuration options controlling how this client
     *                            connects to network (e.g. proxy settings, retry counts, etc).
     */
    public BciClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, BCI_HANDLERS);
    }

    /**
     * Creates and initializes a new request object for the specified network resource. This method is responsible
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
     * any additional headers or parameters, and execute.
     * @param bceRequest The original request, as created by the user.
     */
    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT ) {
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
     * Create a bci instance with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     * <p/>
     *
     * @param request The request containing all options for creating a bci instance.
     * @return List of bci instanceId newly created
     * @throws BceClientException
     */
    public CreateInstanceResponse createInstance(CreateInstanceRequest request)
            throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, BCI_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateInstanceResponse.class);
    }

    /**
     * Describe a bci instance with the specified options.
     * @param instanceId
     * @return DeleteInstanceResponse
     * @throws BceClientException
     */
    public DeleteInstanceResponse deleteInstance(String instanceId) throws BceClientException {
        return deleteInstance(instanceId, false);
    }

    /**
     * Delete a bci instance with the specified options.
     * @param instanceId
     * @param relatedReleaseFlag
     * @return DeleteInstanceResponse
     * @throws BceClientException
     */
    public DeleteInstanceResponse deleteInstance(String instanceId, Boolean relatedReleaseFlag)
            throws BceClientException {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat(INSTANCE_ID_MESSAGE_KEY));
        checkNotNull(relatedReleaseFlag, "relatedReleaseFlag should not be null.");
        DeleteInstanceRequest request = new DeleteInstanceRequest();
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, BCI_PREFIX, instanceId);
        internalRequest.addParameter("relatedReleaseFlag", relatedReleaseFlag.toString());
        internalRequest.addParameter("clientToken", request.getClientToken());
        return invokeHttpClient(internalRequest, DeleteInstanceResponse.class);
    }

    /**
     * batch delete bci instance with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param request The request containing all options for batch deleting bci instance.
     * @return List of bci instanceId newly deleted DeleteInstanceResponse void
     * @throws BceClientException
     */
    public DeleteInstanceResponse batchDeleteInstance(DeleteInstanceRequest request)
            throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getInstanceIds(), "instanceId should not be null.");
        checkNotNull(request.getRelatedReleaseFlag(), "relatedReleaseFlag should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, BCI_PREFIX, BATCH_DEL);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, DeleteInstanceResponse.class);
    }

    /**
     * Get the bci instance with the specified options.
     * @param String instanceId
     * @return GetInstanceResponse
     * @throws BceClientException
     */
    public GetInstanceResponse getInstance(String instanceId) throws BceClientException {
        return getInstance(new GetInstanceRequest().setInstanceId(instanceId));
    }

    /**
     * Get the bci instance with the specified options.
     * @param GetInstanceRequest request
     * @return GetInstanceResponse
     * @throws BceClientException
     */
    public GetInstanceResponse getInstance(GetInstanceRequest request) throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getInstanceId(), checkEmptyExceptionMessageFormat(INSTANCE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, BCI_PREFIX, request.getInstanceId());
        return invokeHttpClient(internalRequest, GetInstanceResponse.class);
    }

    /**
     * Get the bci instance with the specified options.
     * @param String instanceId
     * @return GetInstanceResponse
     * @throws BceClientException
     */
    public ListInstancesResponse listInstances() throws BceClientException {
        return listInstances(new ListInstancesRequest());
    }

    /**
     * Get the bci instance with the specified options.
     * @param ListInstancesRequest request
     * @return GetInstanceResponse
     * @throws BceClientException
     */
    public ListInstancesResponse listInstances(ListInstancesRequest request) throws BceClientException {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, BCI_PREFIX);
        if (request.getMarker() != null) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        }
        if (request.getKeywordType() != null) {
            internalRequest.addParameter(KEYWORD_TYPE, request.getKeywordType());
        }
        if (request.getKeyword() != null) {
            internalRequest.addParameter(KEYWORD, request.getKeyword());
        }
        return invokeHttpClient(internalRequest, ListInstancesResponse.class);
    }
}

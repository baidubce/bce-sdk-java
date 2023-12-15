/*
 * Copyright (c) 2020 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.eipbp;

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
import com.baidubce.services.eipbp.model.CreateEipBpRequest;
import com.baidubce.services.eipbp.model.CreateEipBpResponse;
import com.baidubce.services.eipbp.model.EipBpDetailResponse;
import com.baidubce.services.eipbp.model.GetEipBpRequest;
import com.baidubce.services.eipbp.model.ListEipBpsRequest;
import com.baidubce.services.eipbp.model.ListEipBpsResponse;
import com.baidubce.services.eipbp.model.ReleaseEipBpRequest;
import com.baidubce.services.eipbp.model.ResizeEipBpRequest;
import com.baidubce.services.eipbp.model.UpdateEipBpAutoReleaseTimeRequest;
import com.baidubce.services.eipbp.model.UpdateEipBpNameRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.DateUtils.validateUtcDate;
import static com.baidubce.util.Validate.checkMultyParamsNotBothEmpty;
import static com.baidubce.util.Validate.checkNotNull;


/**
 * Provides the client for accessing the Elastic Ip Service (EIPBP).
 */
public class EipBpClient extends AbstractBceClient {
    /**
     * EIPBP API pathVersion.
     */
    private static final String VERSION = "v1";

    private static final String PREFIX = "eipbp";

    private static final String CLIENT_TOKEN_IDENTIFY = "clientToken";

    private static final String RESIZE_ACTION = "resize";

    private static final String RENAME_ACTION = "rename";

    private static final String RETIME_ACTION = "retime";

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static HttpResponseHandler[] eipHandlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public EipBpClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new InstanceClient to invoke service methods on eipbp instance.
     *
     * @param clientConfiguration The BCE client configuration options.
     */
    public EipBpClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, eipHandlers);
    }

    /**
     * Create an eipbp with the specified options.
     * This is an asynchronous interface.
     *
     * @param request The request containing all options for creating an eipbp.
     * @return created eipbp ip.
     */
    public CreateEipBpResponse createEipBp(CreateEipBpRequest request) {
        checkNotNull(request.getBandwidthInMbps(), "bandwidthInMbps should not be blank!");
        checkMultyParamsNotBothEmpty(Arrays.asList(request.getEip(), request.getEipGroupId()),
                "eip and eipGroupId should not be both blank!");
        validateUtcDate(request.getAutoReleaseTime());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateEipBpResponse.class);
    }

    /**
     * Resizing eipbp.
     * This is an asynchronous interface.
     *
     * @param request eipbp id & newBandwidthInMbps must be provided.
     */
    public void resizeEipBp(ResizeEipBpRequest request) {
        checkNotNull(request.getId(), "id should not be blank!");
        checkNotNull(request.getBandwidthInMbps(), "bandwidthInMbps should not be blank!");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getId());
        internalRequest.addParameter(RESIZE_ACTION, null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, CreateEipBpResponse.class);
    }

    /**
     * Resizing eipbp.
     *
     * @param id              eipbp id to be resized.
     * @param bandwidthInMbps specify new bandwidth in Mbps for eipbp.
     */
    public void resizeEipBp(String id, Integer bandwidthInMbps) {
        ResizeEipBpRequest resizeEipBpRequest = new ResizeEipBpRequest();
        resizeEipBpRequest.setId(id);
        resizeEipBpRequest.setBandwidthInMbps(bandwidthInMbps);
        resizeEipBp(resizeEipBpRequest);
    }


    /**
     * Get detail of the eipbp givened id.
     *
     * @param request The request containing eipbp id to query.
     * @return the eipbp detail.
     */
    public EipBpDetailResponse getEipBpDetail(GetEipBpRequest request) {
        checkNotNull(request.getId(), "id should not be blank!");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, request.getId());
        return invokeHttpClient(internalRequest, EipBpDetailResponse.class);
    }

    /**
     * Get detail of the eipbp givened id.
     *
     * @param id The eipbp id to query.
     * @return the eipbp detail.
     */
    public EipBpDetailResponse getEipBpDetail(String id) {
        GetEipBpRequest getEipBpRequest = new GetEipBpRequest();
        getEipBpRequest.setId(id);
        return getEipBpDetail(getEipBpRequest);

    }

    /**
     * Get a list of eipbps owned by the authenticated user and specified conditions.
     *
     * @param request The request containing all options for query.
     * @return the eipbp's list.
     */
    public ListEipBpsResponse listEipBps(ListEipBpsRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, null);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() >= 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getId())) {
            internalRequest.addParameter("id", request.getId());
        }
        if (!Strings.isNullOrEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (!Strings.isNullOrEmpty(request.getBindType())) {
            internalRequest.addParameter("bindType", request.getBindType());
        }
        if (!Strings.isNullOrEmpty(request.getType())) {
            internalRequest.addParameter("type", request.getType());
        }
        return invokeHttpClient(internalRequest, ListEipBpsResponse.class);
    }

    /**
     * Get a list of eipbps owned by the authenticated user.
     *
     * @return the eipbp's list.
     */
    public ListEipBpsResponse listEipBps() {
        ListEipBpsRequest request = new ListEipBpsRequest();
        return listEipBps(request);
    }

    /**
     * Get a list of eipbps owned by the authenticated user and specified conditions.
     *
     * @param id       eipbp's id.
     * @param name     eipbp's name.
     * @param bindType eipbp's bindType, eip or eipgroup.
     * @return the eipbp's list.
     */
    public ListEipBpsResponse listEipBps(String id, String name, String bindType) {
        ListEipBpsRequest request = new ListEipBpsRequest();
        request.withId(id).withName(name).withBindType(bindType);
        return listEipBps(request);
    }

    /**
     * Get a list of eipbps owned by the authenticated user and specified conditions.
     *
     * @param id       eipbp's id.
     * @param name     eipbp's name.
     * @param bindType eipbp's bindType, eip or eipgroup.
     * @param type eipbp's type, BandwidthPackage or AccelerationPackage.
     * @return the eipbp's list.
     */
    public ListEipBpsResponse listEipBps(String id, String name, String bindType, String type) {
        ListEipBpsRequest request = new ListEipBpsRequest();
        request.withId(id).withName(name).withBindType(bindType).withType(type);
        return listEipBps(request);
    }

    /**
     * Rename eipbp.
     *
     * @param request Eipbp id & name must be provided.
     */
    public void renameEipBp(UpdateEipBpNameRequest request) {
        checkNotNull(request.getId(), "id should not be blank!");
        checkNotNull(request.getName(), "bandwidthInMbps should not be blank!");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getId());
        internalRequest.addParameter(RENAME_ACTION, null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, CreateEipBpResponse.class);
    }


    /**
     * Rename eipbp.
     *
     * @param id   eipbp's id.
     * @param name eipbp's  new name.
     */
    public void renameEipBp(String id, String name) {
        UpdateEipBpNameRequest updateEipBpNameRequest = new UpdateEipBpNameRequest();
        updateEipBpNameRequest.withId(id).withName(name);
        renameEipBp(updateEipBpNameRequest);
    }

    /**
     * Update eipbp's autoReleaseTime.
     *
     * @param request Eipbp id & autoReleaseTime must be provided, the autoReleaseTime must be in UTC format.
     */
    public void updateAutoReleaseTime(UpdateEipBpAutoReleaseTimeRequest request) {
        checkNotNull(request.getId(), "id should not be blank!");
        checkNotNull(request.getAutoReleaseTime(), "autoReleaseTime should not be blank!");
        validateUtcDate(request.getAutoReleaseTime());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, request.getId());
        internalRequest.addParameter(RETIME_ACTION, null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, CreateEipBpResponse.class);
    }


    /**
     * Update eipbp's autoReleaseTime.
     *
     * @param id              eipbp's id.
     * @param autoReleaseTime eipbp's  autoReleaseTime, must be in UTC format.
     */
    public void updateAutoReleaseTime(String id, String autoReleaseTime) {
        UpdateEipBpAutoReleaseTimeRequest updateEipBpNameRequest = new UpdateEipBpAutoReleaseTimeRequest();
        updateEipBpNameRequest.withId(id).withAutoReleaseTime(autoReleaseTime);
        updateAutoReleaseTime(updateEipBpNameRequest);
    }


    /**
     * release the eipbp(delete operation).
     *
     * @param request The request containing all options for releasing eipbp.
     */
    public void releaseEipBp(ReleaseEipBpRequest request) {
        checkNotNull(request.getId(), "id should not be blank!");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, request.getId());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        invokeHttpClient(internalRequest, CreateEipBpResponse.class);
    }

    /**
     * release the eipbp(delete operation).
     *
     * @param id The id eipbp to be released.
     */
    public void releaseEipBp(String id) {
        ReleaseEipBpRequest releaseEipBpRequest = new ReleaseEipBpRequest();
        releaseEipBp(releaseEipBpRequest.withId(id));
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest    The original BCE request created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific
     * parameters to send.
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

    /**
     * the method to fill the internalRequest's content field with bceRequest
     * only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     *                        any additional headers or parameters, and execute.
     * @param bceRequest      The original request, as created by the user.
     */
    protected void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
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
    private String generateDefaultClientToken() {
        return UUID.randomUUID().toString();
    }
}

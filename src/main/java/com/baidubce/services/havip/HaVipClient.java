/*
 * Copyright (C) 2023 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.havip;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
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
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.havip.model.BindEipRequest;
import com.baidubce.services.havip.model.BindInstanceRequest;
import com.baidubce.services.havip.model.CreateHaVipRequest;
import com.baidubce.services.havip.model.CreateHaVipResponse;
import com.baidubce.services.havip.model.DeleteHaVipRequest;
import com.baidubce.services.havip.model.HaVipResponse;
import com.baidubce.services.havip.model.ListHaVipRequest;
import com.baidubce.services.havip.model.ListHaVipResponse;
import com.baidubce.services.havip.model.UnBindEipRequest;
import com.baidubce.services.havip.model.UnBindInstanceRequest;
import com.baidubce.services.havip.model.UpdateHaVipRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Validate;
import com.google.common.base.Strings;

public class HaVipClient extends AbstractBceClient {

    private static final String VERSION = "v1";
    private static final String HAVIP_PREFIX = "havip";
    private static final String CLIENT_TOKEN = "clientToken";
    private static final String MARKER = "marker";
    private static final String MAX_KEYS = "maxKeys";
    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String NAME_MESSAGE_KEY = "name";
    private static final String HA_VIP_ID_MESSAGE_KEY = "haVipId";
    private static final String BIND_INSTANCE_TYPE_MESSAGE_KEY = "instanceType";
    private static final String BIND_PUBLIC_IP_ADDRESS_MESSAGE_KEY = "publicIpAddress";

    /**
     * Responsible for handling httpResponses from all HaVip network service calls.
     */
    private static final HttpResponseHandler[] HAVIP_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on havip.
     */
    public HaVipClient() {
        this(new HaVipClientConfiguration());
    }

    /**
     * Constructs a new HaVip client using the client configuration to access esg.
     */
    public HaVipClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, HAVIP_HANDLERS);
    }

    /**
     * Creates and initializes a new request object for the specified bcc resource. This method is responsible
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
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
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
     * This method is used to create HaVip
     *
     * @param createHaVipRequest
     * @return CreateHaVipResponse
     */
    public CreateHaVipResponse createHaVip(CreateHaVipRequest createHaVipRequest) {
        checkNotNull(createHaVipRequest, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(createHaVipRequest.getClientToken())) {
            createHaVipRequest.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(createHaVipRequest.getName(), checkEmptyExceptionMessageFormat(NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(createHaVipRequest, HttpMethodName.POST, HAVIP_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, createHaVipRequest.getClientToken());
        fillPayload(internalRequest, createHaVipRequest);
        return invokeHttpClient(internalRequest, CreateHaVipResponse.class);
    }

    public ListHaVipResponse listHaVip(String vpcId) {
        ListHaVipRequest listHaVipRequest = new ListHaVipRequest();
        listHaVipRequest.setVpcId(vpcId);
        return listHaVip(listHaVipRequest);
    }

    /**
     * This method is used to list your owen HaVips
     *
     * @param listHaVipRequest
     * @return ListHaVipResponse
     */
    public ListHaVipResponse listHaVip(ListHaVipRequest listHaVipRequest) {
        checkNotNull(listHaVipRequest, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(listHaVipRequest.getVpcId(), "VpcId not allow empty");
        InternalRequest internalRequest = this.createRequest(listHaVipRequest, HttpMethodName.GET, HAVIP_PREFIX);
        if (listHaVipRequest.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(listHaVipRequest.getMaxKeys()));
        } else if (listHaVipRequest.getMaxKeys() <= 0) {
            internalRequest.addParameter(MAX_KEYS, "1000");
        }
        internalRequest.addParameter("vpcId", listHaVipRequest.getVpcId());

        return invokeHttpClient(internalRequest, ListHaVipResponse.class);
    }

    /**
     * This method is use to get havip detail
     *
     * @param haVipId
     * @return HaVipResponse
     */
    public HaVipResponse getHaVip(String haVipId) {
        ListHaVipRequest listHaVipRequest = new ListHaVipRequest();
        InternalRequest internalRequest =
                this.createRequest(listHaVipRequest, HttpMethodName.GET, HAVIP_PREFIX, haVipId);
        return invokeHttpClient(internalRequest, HaVipResponse.class);
    }

    /**
     * This method is used to update HaVip's name and description
     *
     * @param updateHaVipRequest
     */
    public void updateHaVip(UpdateHaVipRequest updateHaVipRequest) {
        Validate.checkNotNull(updateHaVipRequest, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(updateHaVipRequest.getHaVipId(), checkEmptyExceptionMessageFormat(HA_VIP_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(updateHaVipRequest.getClientToken())) {
            updateHaVipRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(updateHaVipRequest, HttpMethodName.PUT, HAVIP_PREFIX,
                updateHaVipRequest.getHaVipId());
        internalRequest.addParameter(CLIENT_TOKEN, updateHaVipRequest.getClientToken());
        internalRequest.addParameter("modifyAttribute", null);
        fillPayload(internalRequest, updateHaVipRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * This method is used to delete specified HaVip
     *
     * @param request
     */
    public void deleteHaVip(DeleteHaVipRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getHaVipId(), checkEmptyExceptionMessageFormat(HA_VIP_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, HAVIP_PREFIX, request.getHaVipId());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * This method is used to delete specified HaVip
     *
     * @param haVipId
     */
    public void deleteHaVip(String haVipId) {
        DeleteHaVipRequest deleteHaVipRequest = new DeleteHaVipRequest();
        deleteHaVipRequest.setHaVipId(haVipId);
        deleteHaVip(deleteHaVipRequest);
    }

    /**
     * The method is used to bind instance like bcc/eni.. to HaVip
     *
     * @param bindInstanceRequest
     */
    public void bindInstance(BindInstanceRequest bindInstanceRequest) {
        Validate.checkNotNull(bindInstanceRequest, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(bindInstanceRequest.getHaVipId(), checkEmptyExceptionMessageFormat(HA_VIP_ID_MESSAGE_KEY));
        checkStringNotEmpty(bindInstanceRequest.getInstanceType(),
                checkEmptyExceptionMessageFormat(BIND_INSTANCE_TYPE_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(bindInstanceRequest.getClientToken())) {
            bindInstanceRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(bindInstanceRequest, HttpMethodName.PUT, HAVIP_PREFIX,
                        bindInstanceRequest.getHaVipId());
        internalRequest.addParameter(CLIENT_TOKEN, bindInstanceRequest.getClientToken());
        internalRequest.addParameter("attach", null);
        fillPayload(internalRequest, bindInstanceRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * The method is used to unBind instance like bcc/eni.. from HaVip
     *
     * @param request
     */
    public void unBindInstance(UnBindInstanceRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getHaVipId(), checkEmptyExceptionMessageFormat(HA_VIP_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getInstanceType(),
                checkEmptyExceptionMessageFormat(BIND_INSTANCE_TYPE_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, HAVIP_PREFIX,
                        request.getHaVipId());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        internalRequest.addParameter("detach", null);
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * The method is used to bind eip to HaVip
     *
     * @param bindEipRequest
     */
    public void bindEip(BindEipRequest bindEipRequest) {
        Validate.checkNotNull(bindEipRequest, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(bindEipRequest.getHaVipId(), checkEmptyExceptionMessageFormat(HA_VIP_ID_MESSAGE_KEY));
        checkStringNotEmpty(bindEipRequest.getPublicIpAddress(),
                checkEmptyExceptionMessageFormat(BIND_PUBLIC_IP_ADDRESS_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(bindEipRequest.getClientToken())) {
            bindEipRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(bindEipRequest, HttpMethodName.PUT, HAVIP_PREFIX,
                        bindEipRequest.getHaVipId());
        internalRequest.addParameter(CLIENT_TOKEN, bindEipRequest.getClientToken());
        internalRequest.addParameter("bindPublicIp", null);
        fillPayload(internalRequest, bindEipRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * The method is used to unBind eip from HaVip
     *
     * @param unBindEipRequest
     */
    public void unBindEip(UnBindEipRequest unBindEipRequest) {
        Validate.checkNotNull(unBindEipRequest, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(unBindEipRequest.getHaVipId(), checkEmptyExceptionMessageFormat(HA_VIP_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(unBindEipRequest.getClientToken())) {
            unBindEipRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest =
                this.createRequest(unBindEipRequest, HttpMethodName.PUT, HAVIP_PREFIX,
                        unBindEipRequest.getHaVipId());
        internalRequest.addParameter(CLIENT_TOKEN, unBindEipRequest.getClientToken());
        internalRequest.addParameter("unbindPublicIp", null);
        fillPayload(internalRequest, unBindEipRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
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
}

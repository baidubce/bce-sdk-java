package com.baidubce.services.ipcollection;

import com.baidubce.AbstractBceClient;
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
import com.baidubce.services.ipcollection.emum.IpCollectionAction;
import com.baidubce.services.ipcollection.model.ipgroup.BindIpSetRequest;
import com.baidubce.services.ipcollection.model.ipgroup.CreateIpAddressGroupRequest;
import com.baidubce.services.ipcollection.model.ipgroup.CreateIpAddressGroupResponse;
import com.baidubce.services.ipcollection.model.ipgroup.DeleteIpGroupRequest;
import com.baidubce.services.ipcollection.model.ipgroup.QueryIpGroupDetailRequest;
import com.baidubce.services.ipcollection.model.ipgroup.QueryIpGroupDetailResponse;
import com.baidubce.services.ipcollection.model.ipgroup.QueryIpGroupListRequest;
import com.baidubce.services.ipcollection.model.ipgroup.QueryIpGroupListResponse;
import com.baidubce.services.ipcollection.model.ipgroup.UnBindIpSetRequest;
import com.baidubce.services.ipcollection.model.ipgroup.UpdateIpGroupRequest;
import com.baidubce.services.ipcollection.model.ipset.AddIpAddressToIpSetRequest;
import com.baidubce.services.ipcollection.model.ipset.CreateIpAddressSetRequest;
import com.baidubce.services.ipcollection.model.ipset.CreateIpAddressSetResponse;
import com.baidubce.services.ipcollection.model.ipset.DeleteIpSetRequest;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetDetailRequest;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetDetailResponse;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetListRequest;
import com.baidubce.services.ipcollection.model.ipset.QueryIpSetListResponse;
import com.baidubce.services.ipcollection.model.ipset.RemoveIpAddressFromIpSetRequest;
import com.baidubce.services.ipcollection.model.ipset.UpdateIpSetRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.baidubce.util.Validate;
import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;

/**
 * Provides the client for accessing the Baidu Cloud network Service param template part.
 */
public class IpCollectionClient extends AbstractBceClient {

    /**
     * default version of param template.
     */
    private static final String VERSION = "v1";

    /**
     * The headers to sign for the request.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * The header to sign for the request.
     */
    private static final String CLIENT_TOKEN = "clientToken";

    /**
     * The default max keys value.
     */
    private static final String QUERY_MAX_KEYS_VALUE = "1000";

    /**
     * url prefix.
     */
    private static final String IP_SET_URL_PREFIX = "ipSet";
    private static final String IP_GROUP_URL_PREFIX = "ipGroup";
    private static final String IP_ADDRESS = "ipAddress";
    private static final String DELETE_IP_ADDRESS = "deleteIpAddress";
    private static final String MARKER = "marker";
    private static final String MAX_KEYS = "maxKeys";

    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String IP_SET_NAME = "name";
    private static final String IP_GROUP_NAME = "name";
    private static final String IP_VERSION = "ipVersion";
    private static final String IP_ADDRESS_INFO = "ipAddressInfo";
    private static final String IP_SET_IDS = "ipSetIds";
    private static final String IP_SET_ID = "ipSetId";
    private static final String IP_GROUP_ID = "ipGroupId";
    private static final String BIND_IP_SET = "bindIpSet";
    private static final String UN_BIND_IP_SET = "unbindIpSet";

    private static final HttpResponseHandler[] CONN_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on network.
     */
    public IpCollectionClient() {
        this(new IpCollectionClientConfiguration());
    }

    /**
     * Constructs a new network client using the client configuration to access network.
     *
     * @param clientConfiguration The network client configuration options controlling how this client
     *                            connects to network
     */
    public IpCollectionClient(IpCollectionClientConfiguration clientConfiguration) {
        super(clientConfiguration, CONN_HANDLERS);
    }

    /**
     * create ip address set
     *
     * @param request request
     * @return CreateIpAddressGroupResponse
     */
    public CreateIpAddressSetResponse createIpAddressSet(CreateIpAddressSetRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(IP_SET_NAME));
        Validate.checkStringNotEmpty(request.getIpVersion(), checkEmptyExceptionMessageFormat(IP_VERSION));
        Validate.checkListNotEmpty(request.getIpAddressInfo(), checkEmptyExceptionMessageFormat(IP_ADDRESS_INFO));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, IP_SET_URL_PREFIX);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());

        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateIpAddressSetResponse.class);
    }

    /**
     * add ip address to ip set
     *
     * @param request request
     */
    public void addIpAddressToIpSet(AddIpAddressToIpSetRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getIpSetId(), checkEmptyExceptionMessageFormat(IP_SET_ID));
        Validate.checkListNotEmpty(request.getIpAddressInfo(), checkEmptyExceptionMessageFormat(IP_ADDRESS_INFO));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, IP_SET_URL_PREFIX, request.getIpSetId(), IP_ADDRESS);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * remove ip address from ip set
     *
     * @param request request
     */
    public void removeIpAddressFromIpSet(RemoveIpAddressFromIpSetRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getIpSetId(), checkEmptyExceptionMessageFormat(IP_SET_ID));
        Validate.checkListNotEmpty(request.getIpAddressInfo(), checkEmptyExceptionMessageFormat(IP_ADDRESS_INFO));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, IP_SET_URL_PREFIX, request.getIpSetId(), DELETE_IP_ADDRESS);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * update ip set
     *
     * @param request request
     */
    public void updateIpSet(UpdateIpSetRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getIpSetId(), checkEmptyExceptionMessageFormat(IP_SET_ID));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, IP_SET_URL_PREFIX, request.getIpSetId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        internalRequest.addParameter(IpCollectionAction.modifyAttribute.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());

        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * delete ip set by request
     *
     * @param request request
     */
    public void deleteIpSet(DeleteIpSetRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getIpSetId(), checkEmptyExceptionMessageFormat(IP_SET_ID));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, IP_SET_URL_PREFIX, request.getIpSetId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * delete ip set by ipSetId
     *
     * @param ipSetId ipSetId
     */
    public void deleteIpSet(String ipSetId) {
        DeleteIpSetRequest request = new DeleteIpSetRequest();
        request.setIpSetId(ipSetId);
        deleteIpSet(request);
    }

    /**
     * query ip set list
     *
     * @param request request
     * @return QueryIpSetListResponse
     */
    public QueryIpSetListResponse queryIpSetList(QueryIpSetListRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, IP_SET_URL_PREFIX, null);
        if (!Strings.isNullOrEmpty(request.getIpVersion())) {
            internalRequest.addParameter(IP_VERSION, request.getIpVersion());
        }
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        } else {
            internalRequest.addParameter(MAX_KEYS, QUERY_MAX_KEYS_VALUE);
        }

        return invokeHttpClient(internalRequest, QueryIpSetListResponse.class);
    }

    /**
     * query ip set detail by request
     *
     * @param request request
     * @return QueryIpSetDetailResponse
     */
    public QueryIpSetDetailResponse queryIpSetDetail(QueryIpSetDetailRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getIpSetId(), checkEmptyExceptionMessageFormat(IP_SET_ID));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, IP_SET_URL_PREFIX, request.getIpSetId());

        return invokeHttpClient(internalRequest, QueryIpSetDetailResponse.class);
    }

    /**
     * query ip set detail by ipSetId
     *
     * @param ipSetId ipSetId
     * @return QueryIpSetDetailResponse
     */
    public QueryIpSetDetailResponse queryIpSetDetail(String ipSetId) {
        QueryIpSetDetailRequest request = new QueryIpSetDetailRequest();
        request.setIpSetId(ipSetId);
        return queryIpSetDetail(request);
    }

    /**
     * create ip address group
     *
     * @param request request
     * @return CreateIpAddressGroupResponse
     */
    public CreateIpAddressGroupResponse createIpAddressGroup(CreateIpAddressGroupRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(IP_GROUP_NAME));
        Validate.checkStringNotEmpty(request.getIpVersion(), checkEmptyExceptionMessageFormat(IP_VERSION));
        Validate.checkListNotEmpty(request.getIpSetIds(), checkEmptyExceptionMessageFormat(IP_SET_IDS));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, IP_GROUP_URL_PREFIX);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, CreateIpAddressGroupResponse.class);
    }

    /**
     * bind ip set to group
     *
     * @param request request
     */
    public void bindIpSet(BindIpSetRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID));
        Validate.checkListNotEmpty(request.getIpSetIds(), checkEmptyExceptionMessageFormat(IP_SET_IDS));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, IP_GROUP_URL_PREFIX, request.getIpGroupId(), BIND_IP_SET);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        fillPayload(internalRequest, request);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * unbind ip set from group
     *
     * @param request request
     */
    public void unBindIpSet(UnBindIpSetRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID));
        Validate.checkListNotEmpty(request.getIpSetIds(), checkEmptyExceptionMessageFormat(IP_SET_IDS));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, IP_GROUP_URL_PREFIX, request.getIpGroupId(), UN_BIND_IP_SET);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        fillPayload(internalRequest, request);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * update ip group
     *
     * @param request request
     */
    public void updateIpGroup(UpdateIpGroupRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, IP_GROUP_URL_PREFIX, request.getIpGroupId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        internalRequest.addParameter(IpCollectionAction.modifyAttribute.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * delete ip group by request
     *
     * @param request request
     */
    public void deleteIpGroup(DeleteIpGroupRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, IP_GROUP_URL_PREFIX, request.getIpGroupId());
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * delete ip group by ipGroupId
     *
     * @param ipGroupId ipGroupId
     */
    public void deleteIpGroup(String ipGroupId) {
        DeleteIpGroupRequest request = new DeleteIpGroupRequest();
        request.setIpGroupId(ipGroupId);
        deleteIpGroup(request);
    }

    /**
     * query ip group list
     *
     * @param request request
     * @return QueryIpGroupListResponse
     */
    public QueryIpGroupListResponse queryIpGroupList(QueryIpGroupListRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, IP_GROUP_URL_PREFIX, null);
        if (!Strings.isNullOrEmpty(request.getIpVersion())) {
            internalRequest.addParameter(IP_VERSION, request.getIpVersion());
        }
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter(MARKER, request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter(MAX_KEYS, String.valueOf(request.getMaxKeys()));
        } else {
            internalRequest.addParameter(MAX_KEYS, QUERY_MAX_KEYS_VALUE);
        }

        return invokeHttpClient(internalRequest, QueryIpGroupListResponse.class);
    }

    /**
     * query ip group detail by request
     *
     * @param request request
     * @return QueryIpGroupDetailResponse
     */
    public QueryIpGroupDetailResponse queryIpGroupDetail(QueryIpGroupDetailRequest request) {
        Validate.checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        Validate.checkStringNotEmpty(request.getIpGroupId(), checkEmptyExceptionMessageFormat(IP_GROUP_ID));

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, IP_GROUP_URL_PREFIX, request.getIpGroupId());

        return invokeHttpClient(internalRequest, QueryIpGroupDetailResponse.class);
    }

    /**
     * query ip group detail by ipGroupId
     *
     * @param ipGroupId ipGroupId
     * @return QueryIpGroupDetailResponse
     */
    public QueryIpGroupDetailResponse queryIpGroupDetail(String ipGroupId) {
        QueryIpGroupDetailRequest request = new QueryIpGroupDetailRequest();
        request.setIpGroupId(ipGroupId);
        return queryIpGroupDetail(request);
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
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());
        return request;
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

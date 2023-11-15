package com.baidubce.services.eiptp;

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
import com.baidubce.services.eiptp.model.CreateEipTpRequest;
import com.baidubce.services.eiptp.model.CreateEipTpResponse;
import com.baidubce.services.eiptp.model.EipTpDetailResponse;
import com.baidubce.services.eiptp.model.GetEipTpRequest;
import com.baidubce.services.eiptp.model.ListEipTpsRequest;
import com.baidubce.services.eiptp.model.ListEipTpsResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

/**
 * Provides the client for accessing the Elastic Ip Service (EIP TP).
 */
public class EipTpClient extends AbstractBceClient {

    /**
     * EIP TP API pathVersion.
     */
    private static final String VERSION = "v1";

    /**
     * EIP TP url prefix
     */
    private static final String PREFIX = "eiptp";

    private static final String CLIENT_TOKEN_IDENTIFY = "clientToken";

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static HttpResponseHandler[] eipHandlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public EipTpClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new InstanceClient to invoke service methods on eiptp instance.
     *
     * @param clientConfiguration The BCE client configuration options.
     */
    public EipTpClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, eipHandlers);
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

    /**
     * Create an eiptp with the specified options.
     *
     * @param request The request containing all options for creating an eiptp.
     * @return created eiptp id.
     */
    public CreateEipTpResponse createEipTp(CreateEipTpRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getReservationLength(), "reservationLength should not be null.");
        checkStringNotEmpty(request.getCapacity(), "capacity should not be empty.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateDefaultClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateEipTpResponse.class);
    }

    /**
     * Get detail of the eiptp by the request.
     *
     * @param request The request containing eiptp id for query.
     * @return the eiptp detail.
     */
    public EipTpDetailResponse getEipTpDetail(GetEipTpRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getId(), "id should not be blank!");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, request.getId());
        return invokeHttpClient(internalRequest, EipTpDetailResponse.class);
    }

    /**
     * Get detail of the eiptp by the passed id.
     *
     * @param id The eiptp id for query.
     * @return the eiptp detail.
     */
    public EipTpDetailResponse getEipTpDetail(String id) {
        GetEipTpRequest getEipTpRequest = new GetEipTpRequest();
        getEipTpRequest.setId(id);
        return getEipTpDetail(getEipTpRequest);
    }

    /**
     * Get a list of eiptps owned by the authenticated user filtered by specific conditions.
     *
     * @param request The request containing all options for query.
     * @return the list of eiptps.
     */
    public ListEipTpsResponse listEipTps(ListEipTpsRequest request) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, null);
        if (!Strings.isNullOrEmpty(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0 && request.getMaxKeys() <= 1000) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (!Strings.isNullOrEmpty(request.getId())) {
            internalRequest.addParameter("id", request.getId());
        }
        if (!Strings.isNullOrEmpty(request.getDeductPolicy())) {
            internalRequest.addParameter("deductPolicy", request.getDeductPolicy());
        }
        if (!Strings.isNullOrEmpty(request.getStatus())) {
            internalRequest.addParameter("status", request.getStatus());
        }
        return invokeHttpClient(internalRequest, ListEipTpsResponse.class);
    }

    /**
     * Get a list of eiptps owned by the authenticated user.
     *
     * @return the list of eiptps.
     */
    public ListEipTpsResponse listEipTps() {
        ListEipTpsRequest request = new ListEipTpsRequest();
        return listEipTps(request);
    }
}

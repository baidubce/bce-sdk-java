package com.baidubce.services.etgateway;

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
import com.baidubce.services.etgateway.model.BindEtChannelRequest;
import com.baidubce.services.etgateway.model.CreateEtGatewayHealthCheckRequest;
import com.baidubce.services.etgateway.model.CreateEtGatewayRequest;
import com.baidubce.services.etgateway.model.CreateEtGatewayResponse;
import com.baidubce.services.etgateway.model.DeleteEtGatewayRequest;
import com.baidubce.services.etgateway.model.GetEtGatewayRequest;
import com.baidubce.services.etgateway.model.GetEtGatewayResponse;
import com.baidubce.services.etgateway.model.ListEtGatewayRequest;
import com.baidubce.services.etgateway.model.ListEtGatewayResponse;
import com.baidubce.services.etgateway.model.UnbindEtChannelRequest;
import com.baidubce.services.etgateway.model.UpdateEtGatewayRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.google.common.base.Preconditions.checkNotNull;

public class EtGatewayClient extends AbstractBceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(EtGatewayClient.class);

    private static final String VERSION = "v1";
    private static final String ETGATEWAY_PREFIX = "etGateway";
    private static final String ETGATEWAY_HEALTH_CHECK_PREFIX = "healthCheck";

    /**
     * Responsible for handling httpResponses from all network service calls.
     */
    private static final HttpResponseHandler[] ETGATEWAY_HANDLERS = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on EtGateway.
     */
    public EtGatewayClient() {
        this(new EtGatewayClientConfiguration());
    }

    /**
     * Constructs a new EtGatewayClient client using the client configuration to access EtGateway.
     *
     * @param clientConfiguration The EtGatewayClient client configuration options controlling how this client
     *                            connects to EtGateway (e.g. proxy settings, retry counts, etc).
     */
    public EtGatewayClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, ETGATEWAY_HANDLERS);
    }


    /**
     * Creates and initializes a new request object for the specified EtGateway resource. This method is responsible
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
     * Create a EtGateway with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     * <p/>
     *
     * @param request The request containing all options for creating an EtGateway.
     * @return etGatewayId newly created
     * @throws BceClientException
     */
    public CreateEtGatewayResponse createEtGateway(CreateEtGatewayRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getVpcId(), "request vpcId should not be null.");
        checkNotNull(request.getName(), "request name should not be null.");
        checkNotNull(request.getSpeed(), "request speed should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, ETGATEWAY_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateEtGatewayResponse.class);
    }


    /**
     * In accordance with the query info, return a list of dedicated line gateways.
     *
     * @param request The request containing all options for list EtGateway.
     * @return
     */
    public ListEtGatewayResponse listEtGateways(ListEtGatewayRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getVpcId(), "request vpcId should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, ETGATEWAY_PREFIX);
        internalRequest.addParameter("vpcId", request.getVpcId());
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (StringUtils.isNotEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (StringUtils.isNotEmpty(request.getStatus())) {
            internalRequest.addParameter("status", request.getStatus());
        }
        if (StringUtils.isNotEmpty(request.getEtGatewayId())) {
            internalRequest.addParameter("etGatewayId", request.getEtGatewayId());
        }

        return invokeHttpClient(internalRequest, ListEtGatewayResponse.class);

    }

    /**
     * Query details of the dedicated line gateway
     *
     * @param etGatewayId
     * @return
     */
    public GetEtGatewayResponse getEtGateway(String etGatewayId) {
        return getEtGateway(new GetEtGatewayRequest().setEtGatewayId(etGatewayId));
    }

    private GetEtGatewayResponse getEtGateway(GetEtGatewayRequest request) {
        checkNotNull(request.getEtGatewayId(), "etGatewayId should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, ETGATEWAY_PREFIX,
                request.getEtGatewayId());
        return this.invokeHttpClient(internalRequest, GetEtGatewayResponse.class);
    }

    /**
     * Update the dedicated line gateway.
     *
     * @param request The request containing all options for update EtGateway.
     */
    public void updateEtGateway(UpdateEtGatewayRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getEtGatewayId(), "etGatewayId should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ETGATEWAY_PREFIX,
                request.getEtGatewayId());
        fillPayload(internalRequest, request);
        internalRequest.addParameter("clientToken", request.getClientToken());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Release the dedicated line gateway
     *
     * @param etGatewayId
     */
    public void deleteEtGateway(String etGatewayId) {
        deleteEtGateway(new DeleteEtGatewayRequest().setEtGatewayId(etGatewayId));
    }

    /**
     * Release the dedicated line gateway
     *
     * @param etGatewayId
     * @param clientToken The request will be idempotent if clientToken is provided.
     */
    public void deleteEtGateway(String etGatewayId, String clientToken) {
        deleteEtGateway(new DeleteEtGatewayRequest().setEtGatewayId(etGatewayId).setClientToken(clientToken));
    }

    private void deleteEtGateway(DeleteEtGatewayRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getEtGatewayId(), "etGatewayId should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, ETGATEWAY_PREFIX,
                request.getEtGatewayId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * Binding a dedicated line gateway to a physical dedicated line channel,
     * you can choose to simultaneously set the cloud-side network parameters.
     *
     * @param request
     */
    public void bindEtChannel(BindEtChannelRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getEtGatewayId(), "etGatewayId should not be null.");
        checkNotNull(request.getEtId(), "etGatewayId should not be null.");
        checkNotNull(request.getChannelId(), "etGatewayId should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ETGATEWAY_PREFIX,
                request.getEtGatewayId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        internalRequest.addParameter("bind", "");
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);

    }

    /**
     * Unbinding a dedicated line gateway from a physical dedicated line channel
     *
     * @param request
     */
    public void unbindEtChannel(UnbindEtChannelRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getEtGatewayId(), "etGatewayId should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, ETGATEWAY_PREFIX,
                request.getEtGatewayId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        internalRequest.addParameter("unbind", "");
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create health check for the dedicated line gateway.
     *
     * @param request
     */
    public void createEtGatewayHealthCheck(CreateEtGatewayHealthCheckRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getEtGatewayId(), "etGatewayId should not be null.");
        checkNotNull(request.getHealthCheckInterval(), "healthCheckInterval should not be null.");
        checkNotNull(request.getHealthThreshold(), "healthThreshold should not be null.");
        checkNotNull(request.getUnhealthThreshold(), "unhealthThreshold should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, ETGATEWAY_PREFIX,
                request.getEtGatewayId(), ETGATEWAY_HEALTH_CHECK_PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }
}

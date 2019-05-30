package com.baidubce.services.peerconn;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.AbstractBceClient;
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
import com.baidubce.services.peerconn.model.CreatePeerConnRequest;
import com.baidubce.services.peerconn.model.CreatePeerConnResponse;
import com.baidubce.services.peerconn.model.GetPeerConnRequest;
import com.baidubce.services.peerconn.model.GetPeerConnResponse;
import com.baidubce.services.peerconn.model.ListPeerConnRequest;
import com.baidubce.services.peerconn.model.ListPeerConnResponse;
import com.baidubce.services.peerconn.model.ModifyBandwidthRequest;
import com.baidubce.services.peerconn.model.ModifyPeerConnRequest;
import com.baidubce.services.peerconn.model.PeerConnIdRequest;
import com.baidubce.services.peerconn.model.PurchaseReservedPeerConnRequest;
import com.baidubce.services.peerconn.model.SyncDnsRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the Baidu Cloud network Service peer conn part.
 */
public class PeerConnClient extends AbstractBceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeerConnClient.class);

    private static final String VERSION = "v1";
    private static final String PREFIX = "peerconn";

    /**
     * Responsible for handling httpResponses from all network service calls.
     */
    private static final HttpResponseHandler[] peerConnHandlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on network.
     */
    public PeerConnClient() {
        this(new PeerConnClientConfiguration());
    }

    /**
     * Constructs a new network client using the client configuration to access network.
     *
     * @param clientConfiguration The network client configuration options controlling how this client
     *                            connects to network (e.g. proxy settings, retry counts, etc).
     */
    public PeerConnClient(PeerConnClientConfiguration clientConfiguration) {
        super(clientConfiguration, peerConnHandlers);
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
     * Create a peer connection with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     * <p/>
     *
     * @param request The request containing all options for creating a peer connection.
     * @return peer connection id newly created
     * @throws BceClientException
     */
    public CreatePeerConnResponse createPeerConn(CreatePeerConnRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        checkStringNotEmpty(request.getPeerRegion(), "PeerRegion should not be empty");
        checkStringNotEmpty(request.getPeerVpcId(), "PeerVpcId should not be empty");
        checkStringNotEmpty(request.getLocalVpcId(), "LocalVpcId should not be empty");
        checkNotNull(request.getBilling(), "billing should not be empty");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, PREFIX);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreatePeerConnResponse.class);
    }

    public ListPeerConnResponse listPeerConn(ListPeerConnRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getVpcId(), "vpcId should not be empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, PREFIX);
        if (StringUtils.isNotBlank(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        internalRequest.addParameter("vpcId", request.getVpcId());

        return invokeHttpClient(internalRequest, ListPeerConnResponse.class);
    }

    /**
     * Get the detail information of specified peer conn.
     *
     * @param request The request of the network.
     * @return A peer conn detail model for the request.
     */
    public GetPeerConnResponse getPeerConn(GetPeerConnRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getPeerConnId(), "PeerConnId should not be empty.");
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.GET, PREFIX, request.getPeerConnId());
        return this.invokeHttpClient(internalRequest, GetPeerConnResponse.class);
    }

    /**
     * Modifying the specified peer conn.
     *
     * @param request The request containing all options for modifying the peer conn;
     */
    public void modifyPeerConn(ModifyPeerConnRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getPeerConnId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Accept the apply of the specified peer conn.
     *
     * @param request The request containing all options for accepting apply the peer conn;
     */
    public void accept(PeerConnIdRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getPeerConnId());
        internalRequest.addParameter("accept", null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Reject the apply of the specified peer conn.
     *
     * @param request The request containing all options for rejecting apply the peer conn;
     */
    public void reject(PeerConnIdRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getPeerConnId());
        internalRequest.addParameter("reject", null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Release the specified peer conn.
     *
     * @param request The request containing all options for releasing the peer conn;
     */
    public void release(PeerConnIdRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.DELETE, PREFIX, request.getPeerConnId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modify bandwith for the specified peer conn.
     *
     * @param request The request containing all options for releasing the peer conn;
     */
    public void modifyBandwith(ModifyBandwidthRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getPeerConnId());
        internalRequest.addParameter("resize", null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * PrchaseReserved for the specified peer conn.
     *
     * @param request The request containing all options for releasing the peer conn;
     */
    public void purchaseReserved(PurchaseReservedPeerConnRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getPeerConnId());
        internalRequest.addParameter("purchaseReserved", null);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Open sync dns the specified peer conn.
     *
     * @param request The request containing all options for releasing the peer conn;
     */
    public void openSyncDns(SyncDnsRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getPeerConnId());
        internalRequest.addParameter("open", null);
        internalRequest.addParameter("role", request.getRole());
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Open sync dns the specified peer conn.
     *
     * @param request The request containing all options for releasing the peer conn;
     */
    public void closeSyncDns(SyncDnsRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                request, HttpMethodName.PUT, PREFIX, request.getPeerConnId());
        internalRequest.addParameter("close", null);
        internalRequest.addParameter("role", request.getRole());
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }
}

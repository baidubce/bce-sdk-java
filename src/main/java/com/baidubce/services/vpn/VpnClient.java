package com.baidubce.services.vpn;

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
import com.baidubce.services.eip.model.Billing;
import com.baidubce.services.vpc.model.NetworkAction;
import com.baidubce.services.vpn.model.BatchCreateSslVpnUserRequest;
import com.baidubce.services.vpn.model.BatchCreateSslVpnUserResponse;
import com.baidubce.services.vpn.model.BindEipRequest;
import com.baidubce.services.vpn.model.CreateSslVpnServerRequest;
import com.baidubce.services.vpn.model.CreateSslVpnServerResponse;
import com.baidubce.services.vpn.model.CreateVpnConnRequest;
import com.baidubce.services.vpn.model.CreateVpnConnResponse;
import com.baidubce.services.vpn.model.CreateVpnRequest;
import com.baidubce.services.vpn.model.CreateVpnResponse;
import com.baidubce.services.vpn.model.DeleteSslVpnServerRequest;
import com.baidubce.services.vpn.model.DeleteSslVpnUserRequest;
import com.baidubce.services.vpn.model.DeleteVpnConnRequest;
import com.baidubce.services.vpn.model.DeleteVpnRequest;
import com.baidubce.services.vpn.model.GetSslVpnServerRequest;
import com.baidubce.services.vpn.model.GetSslVpnServerResponse;
import com.baidubce.services.vpn.model.GetVpnRequest;
import com.baidubce.services.vpn.model.GetVpnResponse;
import com.baidubce.services.vpn.model.ListSslVpnUserRequest;
import com.baidubce.services.vpn.model.ListSslVpnUserResponse;
import com.baidubce.services.vpn.model.ListVpnConnRequest;
import com.baidubce.services.vpn.model.ListVpnConnResponse;
import com.baidubce.services.vpn.model.ListVpnRequest;
import com.baidubce.services.vpn.model.ListVpnResponse;
import com.baidubce.services.vpn.model.RenewVpnRequest;
import com.baidubce.services.vpn.model.UnBindEipRequest;
import com.baidubce.services.vpn.model.UpdateSslVpnServerRequest;
import com.baidubce.services.vpn.model.UpdateSslVpnUserRequest;
import com.baidubce.services.vpn.model.UpdateVpnConnRequest;
import com.baidubce.services.vpn.model.UpdateVpnRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.baidubce.services.vpn.util.Preconditions.checkListIsBlank;
import static com.baidubce.services.vpn.util.Preconditions.checkStrIsBlank;
import static com.google.common.base.Preconditions.checkNotNull;

public class VpnClient extends AbstractBceClient {

    private static final String VERSION = "v1";
    private static final String VPN_PREFIX = "vpn";
    private static final String SSL_VPN_SERVER = "sslVpnServer";
    private static final String SSL_VPN_USER = "sslVpnUser";

    /**
     * Responsible for handling httpResponses from all vpn service calls.
     */
    private static final HttpResponseHandler[] vpnHandlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on vpn.
     */
    public VpnClient() {
        this(new VpnClientConfiguration());
    }

    /**
     * Constructs a new vpn client using the client configuration to access vpn.
     */
    public VpnClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, vpnHandlers);
    }

    /**
     * vpn List
     */
    public ListVpnResponse listVpns(ListVpnRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStrIsBlank(request.getVpcId(), "vpcId");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, VPN_PREFIX);
        if (StringUtils.isNotBlank(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (StringUtils.isNotBlank(request.getEip())) {
            internalRequest.addParameter("eip", request.getEip());
        }
        if (StringUtils.isNotBlank(request.getType())) {
            internalRequest.addParameter("type", request.getType());
        }
        internalRequest.addParameter("vpcId", request.getVpcId());

        return invokeHttpClient(internalRequest, ListVpnResponse.class);
    }

    /**
     * create vpn
     *
     * @param createVpnRequest
     * @return
     */
    public CreateVpnResponse createVpn(CreateVpnRequest createVpnRequest) {
        checkNotNull(createVpnRequest, "request should not be null.");
        checkStrIsBlank(createVpnRequest.getVpcId(), "vpcId");
        checkStrIsBlank(createVpnRequest.getVpnName(), "vpnName");
        if (createVpnRequest.getBilling() == null) {
            createVpnRequest.setBilling(generateDefaultBilling());
        }

        InternalRequest internalRequest = this.createRequest(createVpnRequest, HttpMethodName.POST, VPN_PREFIX);
        if (StringUtils.isNotBlank(createVpnRequest.getClientToken())) {
            internalRequest.addParameter("clientToken", createVpnRequest.getClientToken());
        }
        fillPayload(internalRequest, createVpnRequest);

        return invokeHttpClient(internalRequest, CreateVpnResponse.class);
    }

    /**
     * update vpn
     *
     * @param updateVpnRequest
     */
    public AbstractBceResponse updateVpn(UpdateVpnRequest updateVpnRequest) {
        checkNotNull(updateVpnRequest, "request should not be null.");
        checkStrIsBlank(updateVpnRequest.getVpnId(), "vpnId");

        InternalRequest internalRequest = this.createRequest(
                updateVpnRequest, HttpMethodName.PUT, VPN_PREFIX, updateVpnRequest.getVpnId());
        internalRequest.addParameter(NetworkAction.modifyAttribute.name(), null);
        if (StringUtils.isNotBlank(updateVpnRequest.getClientToken())) {
            internalRequest.addParameter("clientToken", updateVpnRequest.getClientToken());
        }
        fillPayload(internalRequest, updateVpnRequest);

        return this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * delete vpn
     *
     * @param deleteVpnResquest
     */
    public AbstractBceResponse deleteVpn(DeleteVpnRequest deleteVpnResquest) {
        checkNotNull(deleteVpnResquest, "request should not be null.");
        checkStrIsBlank(deleteVpnResquest.getVpnId(), "vpnId");

        InternalRequest internalRequest = this.createRequest(
                deleteVpnResquest, HttpMethodName.DELETE, VPN_PREFIX, deleteVpnResquest.getVpnId());
        if (StringUtils.isNotBlank(deleteVpnResquest.getClientToken())) {
            internalRequest.addParameter("clientToken", deleteVpnResquest.getClientToken());
        }

        return this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Obtain vpn Details
     *
     * @param vpnId
     * @return
     */
    public GetVpnResponse getVpn(String vpnId) {
        checkStrIsBlank(vpnId, "vpnId");

        GetVpnRequest getVpnRequest = new GetVpnRequest();
        getVpnRequest.setVpnId(vpnId);
        InternalRequest internalRequest = this.createRequest(
                getVpnRequest, HttpMethodName.GET, VPN_PREFIX, getVpnRequest.getVpnId());

        return invokeHttpClient(internalRequest, GetVpnResponse.class);
    }

    /**
     * bind eip
     *
     * @param bindEipRequest
     */
    public AbstractBceResponse bindEip(BindEipRequest bindEipRequest) {
        checkNotNull(bindEipRequest, "request should not be null.");
        checkStrIsBlank(bindEipRequest.getVpnId(), "vpnId");
        checkStrIsBlank(bindEipRequest.getEip(), "eip");

        InternalRequest internalRequest = this.createRequest(
                bindEipRequest, HttpMethodName.PUT, VPN_PREFIX, bindEipRequest.getVpnId());
        internalRequest.addParameter("bind", null);
        if (StringUtils.isNotBlank(bindEipRequest.getClientToken())) {
            internalRequest.addParameter("clientToken", bindEipRequest.getClientToken());
        }
        fillPayload(internalRequest, bindEipRequest);

        return this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * unbind eip
     *
     * @param unbindEipRequest
     */
    public AbstractBceResponse unBindEip(UnBindEipRequest unbindEipRequest) {
        checkNotNull(unbindEipRequest, "request should not be null.");
        checkStrIsBlank(unbindEipRequest.getVpnId(), "vpnId");

        InternalRequest internalRequest = this.createRequest(
                unbindEipRequest, HttpMethodName.PUT, VPN_PREFIX, unbindEipRequest.getVpnId());
        internalRequest.addParameter("unbind", null);
        if (StringUtils.isNotBlank(unbindEipRequest.getClientToken())) {
            internalRequest.addParameter("clientToken", unbindEipRequest.getClientToken());
        }
        fillPayload(internalRequest, unbindEipRequest);

        return this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Specify VPN Renewal operation to extend the expiration time
     *
     * @param renewVpnRequest
     */
    public AbstractBceResponse renewVpn(RenewVpnRequest renewVpnRequest) {
        checkNotNull(renewVpnRequest, "request should not be null.");
        checkStrIsBlank(renewVpnRequest.getVpnId(), "vpnId");
        if (renewVpnRequest.getBilling() == null) {
            renewVpnRequest.setBilling(generateDefaultBilling());
        }

        InternalRequest internalRequest = this.createRequest(
                renewVpnRequest, HttpMethodName.PUT, VPN_PREFIX, renewVpnRequest.getVpnId());
        internalRequest.addParameter("purchaseReserved", null);
        if (StringUtils.isNotBlank(renewVpnRequest.getClientToken())) {
            internalRequest.addParameter("clientToken", renewVpnRequest.getClientToken());
        }
        fillPayload(internalRequest, renewVpnRequest);

        return this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Inquire VPN tunnel
     *
     * @param listVpnConnRequest
     * @return
     */
    public ListVpnConnResponse listVpnConns(ListVpnConnRequest listVpnConnRequest) {
        checkNotNull(listVpnConnRequest, "request should not be null.");
        checkStrIsBlank(listVpnConnRequest.getVpnId(), "vpnId");

        InternalRequest internalRequest = this.createRequest(listVpnConnRequest, HttpMethodName.GET, VPN_PREFIX,
                "vpnconn", listVpnConnRequest.getVpnId());

        return invokeHttpClient(internalRequest, ListVpnConnResponse.class);
    }

    /**
     * create vpnConn
     *
     * @param createVpnConnRequest
     * @return
     */
    public CreateVpnConnResponse createVpnConn(CreateVpnConnRequest createVpnConnRequest) {
        checkNotNull(createVpnConnRequest, "request should not be null.");
        checkStrIsBlank(createVpnConnRequest.getVpnId(), "vpnId");
        checkStrIsBlank(createVpnConnRequest.getSecretKey(), "secretKey");
        checkListIsBlank(createVpnConnRequest.getLocalSubnets(), "localSubnets");
        checkStrIsBlank(createVpnConnRequest.getRemoteIp(), "remoteIp");
        checkListIsBlank(createVpnConnRequest.getRemoteSubnets(), "remoteSubnets");
        checkStrIsBlank(createVpnConnRequest.getVpnConnName(), "vpnConnName");
        checkNotNull(createVpnConnRequest.getIkeConfig(), "ikeConfig should not be null.");
        checkNotNull(createVpnConnRequest.getIpsecConfig(), "ipsecConfig should not be null.");

        InternalRequest internalRequest = this.createRequest(createVpnConnRequest, HttpMethodName.POST,
                VPN_PREFIX, createVpnConnRequest.getVpnId(), "vpnconn");
        if (StringUtils.isNotBlank(createVpnConnRequest.getClientToken())) {
            internalRequest.addParameter("clientToken", createVpnConnRequest.getClientToken());
        }
        fillPayload(internalRequest, createVpnConnRequest);

        return invokeHttpClient(internalRequest, CreateVpnConnResponse.class);
    }

    /**
     * update vpnConn
     *
     * @param updateVpnConnRequest
     */
    public AbstractBceResponse updateVpnConn(UpdateVpnConnRequest updateVpnConnRequest) {
        checkNotNull(updateVpnConnRequest, "request should not be null.");
        checkStrIsBlank(updateVpnConnRequest.getVpnConnId(), "vpnConnId");
        checkStrIsBlank(updateVpnConnRequest.getVpnId(), "vpnId");
        checkStrIsBlank(updateVpnConnRequest.getSecretKey(), "secretKey");
        checkListIsBlank(updateVpnConnRequest.getLocalSubnets(), "localSubnets");
        checkStrIsBlank(updateVpnConnRequest.getRemoteIp(), "remoteIp");
        checkListIsBlank(updateVpnConnRequest.getRemoteSubnets(), "remoteSubnets");
        checkStrIsBlank(updateVpnConnRequest.getVpnConnName(), "vpnConnName");
        checkNotNull(updateVpnConnRequest.getIkeConfig(), "ikeConfig should not be null.");
        checkNotNull(updateVpnConnRequest.getIpsecConfig(), "ipsecConfig should not be null.");

        InternalRequest internalRequest = this.createRequest(updateVpnConnRequest, HttpMethodName.PUT,
                VPN_PREFIX, "vpnconn", updateVpnConnRequest.getVpnConnId());
        if (StringUtils.isNotBlank(updateVpnConnRequest.getClientToken())) {
            internalRequest.addParameter("clientToken", updateVpnConnRequest.getClientToken());
        }
        fillPayload(internalRequest, updateVpnConnRequest);

        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * delete vpnConn
     *
     * @param deleteVpnConnRequest
     */
    public AbstractBceResponse deleteVpnConn(DeleteVpnConnRequest deleteVpnConnRequest) {
        checkNotNull(deleteVpnConnRequest, "request should not be null.");
        checkStrIsBlank(deleteVpnConnRequest.getVpnConnId(), "vpnConnId");

        InternalRequest internalRequest = this.createRequest(
                deleteVpnConnRequest, HttpMethodName.DELETE, VPN_PREFIX,
                "vpnconn", deleteVpnConnRequest.getVpnConnId());
        if (StringUtils.isNotBlank(deleteVpnConnRequest.getClientToken())) {
            internalRequest.addParameter("clientToken", deleteVpnConnRequest.getClientToken());
        }

        return this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * Creates and initializes a new request object for the specified vpn resource. This method is responsible
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
     * The method to generate a default Billing which is Postpaid.
     *
     * @return The Billing object with Postpaid PaymentTiming.
     */
    private Billing generateDefaultBilling() {
        Billing billing = new Billing();
        billing.setPaymentTiming("Postpaid");
        billing.setBillingMethod("ByBandwidth");
        return billing;
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
     * <p/>
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }

    /**
     *  create an SSL-VPN server
     * @param request The request containing all options for creating a SslVpnServer
     * @return
     */
    public CreateSslVpnServerResponse createSslVpnServer(CreateSslVpnServerRequest request) {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VPN_PREFIX,
                request.getVpnId(), SSL_VPN_SERVER);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateSslVpnServerResponse.class);
    }

    public void updateSslVpnServer(UpdateSslVpnServerRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getVpnId(), "request vpnId should not be null.");
        checkNotNull(request.getSslVpnServerId(), "request sslVpnServerId should not be null.");
        checkNotNull(request.getSslVpnServerName(), "request sslVpnServerName should not be null.");
        checkNotNull(request.getLocalSubnets(), "request localSubnets should not be null.");
        checkNotNull(request.getRemoteSubnet(), "request remoteSubnet should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, VPN_PREFIX,
                request.getVpnId(), SSL_VPN_SERVER, request.getSslVpnServerId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteSslVpnServer(DeleteSslVpnServerRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getVpnId(), "request vpnId should not be null.");
        checkNotNull(request.getSslVpnServerId(), "request sslVpnServerId should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, VPN_PREFIX,
                request.getVpnId(), SSL_VPN_SERVER, request.getSslVpnServerId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public GetSslVpnServerResponse getSslVpnServer(String vpnId) {
        return getSslVpnServer(new GetSslVpnServerRequest().setVpnId(vpnId));
    }

    private GetSslVpnServerResponse getSslVpnServer(GetSslVpnServerRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getVpnId(), "request vpnId should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, VPN_PREFIX,
                request.getVpnId(), SSL_VPN_SERVER);
        return this.invokeHttpClient(internalRequest, GetSslVpnServerResponse.class);
    }

    public BatchCreateSslVpnUserResponse batchCreateSslVpnUser(BatchCreateSslVpnUserRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getVpnId(), "request vpnId should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, VPN_PREFIX,
                request.getVpnId(), SSL_VPN_USER);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BatchCreateSslVpnUserResponse.class);
    }


    public void updateSslVpnUser(UpdateSslVpnUserRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getVpnId(), "request vpnId should not be null.");
        checkNotNull(request.getUserId(), "request userId should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, VPN_PREFIX,
                request.getVpnId(), SSL_VPN_USER, request.getUserId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public void deleteSslVpnUser(DeleteSslVpnUserRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getVpnId(), "request vpnId should not be null.");
        checkNotNull(request.getUserId(), "request userId should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, VPN_PREFIX,
                request.getVpnId(), SSL_VPN_USER, request.getUserId());
        internalRequest.addParameter("clientToken", request.getClientToken());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    public ListSslVpnUserResponse getSslVpnUser(ListSslVpnUserRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getVpnId(), "request vpnId should not be null.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, VPN_PREFIX,
                request.getVpnId(), SSL_VPN_USER);
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        if (StringUtils.isNotBlank(request.getUserName())) {
            internalRequest.addParameter("userName", request.getUserName());
        }

        return this.invokeHttpClient(internalRequest, ListSslVpnUserResponse.class);
    }

}

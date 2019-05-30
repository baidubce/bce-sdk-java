package com.baidubce.services.route;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

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
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.route.model.CreateRouteRequest;
import com.baidubce.services.route.model.CreateRouteResponse;
import com.baidubce.services.route.model.DeleteRouteRequest;
import com.baidubce.services.route.model.GetRouteRequest;
import com.baidubce.services.route.model.GetRouteResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Created by zhangjing60 on 17/8/2.
 */
public class RouteClient extends AbstractBceClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteClient.class);

    private static final String VERSION = "v1";
    private static final String ROUTE_PREFIX = "route";
    private static final String ROUTE_RULE = "rule";

    /**
     * Responsible for handling httpResponses from all network service calls.
     */
    private static final HttpResponseHandler[] vpc_handlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on network.
     */
    public RouteClient() { this(new RouteClientConfiguration()); }

    /**
     * Constructs a new network client using the client configuration to access network.
     *
     * @param clientConfiguration The network client configuration options controlling how this client
     *                            connects to network (e.g. proxy settings, retry counts, etc).
     */
    public RouteClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, vpc_handlers);
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


//    public CreateRouteResponse createRoute(String routeTableId, String sourceAddress, String destinationAddress,
//                                           String nexthopType, String nexthopId, String description) {
//        CreateRouteRequest createRouteRequest = new CreateRouteRequest();
//        createRouteRequest.withRouteTableId(routeTableId).withSourceAddress(sourceAddress)
//                .withDestinationAddress(destinationAddress).withNextHopType(nexthopType).withDescription(description);
//        if (!Strings.isNullOrEmpty(nexthopId)) {
//            createRouteRequest.withNextHopId(nexthopId);
//        }
//        return createRoute(createRouteRequest);
//
//    }
    /**
     * Create a route with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     * <p/>
     *
     * @param request The request containing all options for creating subnet.
     * @return List of subnetId newly created
     * @throws BceClientException
     */
    public CreateRouteResponse createRoute(CreateRouteRequest request)
            throws BceClientException {
        checkNotNull(request, "request should not be null.");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        checkStringNotEmpty(request.getRouteTableId(), "routeTableId should not be empty");
        checkStringNotEmpty(request.getSourceAddress(), "source address should not be empty");
        checkStringNotEmpty(request.getDestinationAddress(), "destination address should not be empty");
        checkStringNotEmpty(request.getNexthopType(), "nexthop type  should not be empty");
        checkStringNotEmpty(request.getDescription(), "description should not be empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, ROUTE_PREFIX, ROUTE_RULE);
        internalRequest.addParameter("clientToken", request.getClientToken());
        fillPayload(internalRequest,request);
        return invokeHttpClient(internalRequest, CreateRouteResponse.class);
    }

    /**
     * Get the detail information of route table for specific route table or/and vpc
     * @param routeTableId id of route table, routeTableId and vpcId cannot be empty at the same time
     * @param vpcId vpcId, routeTableId and vpcId cannot be empty at the same time
     * @return A route table detail model for the  specific route table or/and vpc
     */
    public GetRouteResponse getRoute(String routeTableId, String vpcId) {
        GetRouteRequest request = new GetRouteRequest();
        if (Strings.isNullOrEmpty(vpcId) && Strings.isNullOrEmpty(routeTableId)) {
            throw new IllegalArgumentException("routeTableId and vpcId should not be empty at the same time");
        }
        else if (!Strings.isNullOrEmpty(routeTableId)) {
            request.withRouteTableId(routeTableId);
        }
        else if (!Strings.isNullOrEmpty(vpcId)) {
            request.withVpcId(vpcId);
        }
        return getRoutes(request);
    }

    /**
     *Get the detail information of route table for specific route table or/and vpc
     * @param getRouteRequest
     * @return A route table detail model for the  specific route table or/and vp
     */
    private GetRouteResponse getRoutes(GetRouteRequest getRouteRequest) {
        checkNotNull(getRouteRequest, "route request should not be null");
        if (Strings.isNullOrEmpty(getRouteRequest.getRouteTableId())
                && Strings.isNullOrEmpty(getRouteRequest.getVpcId())) {
            throw new IllegalArgumentException("routeTableId and vpcId cannot be empty at the same time");
        }
        InternalRequest internalRequest = this.createRequest(
                getRouteRequest, HttpMethodName.GET, ROUTE_PREFIX);
        if (!Strings.isNullOrEmpty(getRouteRequest.getVpcId())) {
            internalRequest.addParameter("vpcId", getRouteRequest.getVpcId());
        }
        else if (!Strings.isNullOrEmpty(getRouteRequest.getRouteTableId())) {
            internalRequest.addParameter("routeTableId", getRouteRequest.getRouteTableId());
        }
        return this.invokeHttpClient(internalRequest, GetRouteResponse.class);

    }

    /**
     *  Delete the  specific route rule
     * @param routeRuleId
     */
    public void deleteRouteRule(String routeRuleId) {
        deleteRouteRule(new DeleteRouteRequest().withRouteRuleId(routeRuleId));
    }

    /**
     * Delete the specific route rule
     * @param deleteRouteRequest the request containing all options for deleting route rule.
     */
    public void deleteRouteRule(DeleteRouteRequest deleteRouteRequest) {
        checkNotNull(deleteRouteRequest, "request should not be null.");
        checkNotNull(deleteRouteRequest.getRouteRuleId(), "request routeRuleId should not be null.");
        if (Strings.isNullOrEmpty(deleteRouteRequest.getClientToken())) {
            deleteRouteRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(
                deleteRouteRequest, HttpMethodName.DELETE, ROUTE_PREFIX, ROUTE_RULE,
                deleteRouteRequest.getRouteRuleId());
        internalRequest.addParameter("clientToken", deleteRouteRequest.getClientToken());
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }



}

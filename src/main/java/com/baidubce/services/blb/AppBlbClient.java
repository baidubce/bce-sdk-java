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
package com.baidubce.services.blb;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.baidubce.services.blb.model.ListAppRsMountResponse;
import com.baidubce.services.blb.model.ListAppRsUnMountResponse;
import org.apache.commons.lang3.StringUtils;

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
import com.baidubce.services.blb.model.AllListener;
import com.baidubce.services.blb.model.AppBackendPolicyRequest;
import com.baidubce.services.blb.model.AppBackendServer;
import com.baidubce.services.blb.model.AppIgRequest;
import com.baidubce.services.blb.model.AppIgResponse;
import com.baidubce.services.blb.model.AppIpGroupMember;
import com.baidubce.services.blb.model.AppIpGroupMemberRequest;
import com.baidubce.services.blb.model.AppPolicy;
import com.baidubce.services.blb.model.AppPolicyRequest;
import com.baidubce.services.blb.model.AppRsRequest;
import com.baidubce.services.blb.model.AppSgPortRequest;
import com.baidubce.services.blb.model.AppSgPortResponse;
import com.baidubce.services.blb.model.AppSgRequest;
import com.baidubce.services.blb.model.AppSgResponse;
import com.baidubce.services.blb.model.BlbDetailRequest;
import com.baidubce.services.blb.model.BlbInstance;
import com.baidubce.services.blb.model.BlbListenerAction;
import com.baidubce.services.blb.model.BlbListenerRequest;
import com.baidubce.services.blb.model.CreateAppPolicyResponse;
import com.baidubce.services.blb.model.CreateBlbRequest;
import com.baidubce.services.blb.model.CreateBlbResponse;
import com.baidubce.services.blb.model.DeleteAppPolicyRequest;
import com.baidubce.services.blb.model.DeleteBlbRequest;
import com.baidubce.services.blb.model.DeleteListenerRequest;
import com.baidubce.services.blb.model.EsgOperateRequest;
import com.baidubce.services.blb.model.HttpListener;
import com.baidubce.services.blb.model.HttpsListener;
import com.baidubce.services.blb.model.ListAllListenerRequest;
import com.baidubce.services.blb.model.ListAppIgRequest;
import com.baidubce.services.blb.model.ListAppIgResponse;
import com.baidubce.services.blb.model.ListAppIpGroupMemberRequest;
import com.baidubce.services.blb.model.ListAppIpGroupMemberResponse;
import com.baidubce.services.blb.model.ListAppPolicyRequest;
import com.baidubce.services.blb.model.ListAppPolicyResponse;
import com.baidubce.services.blb.model.ListAppRsRequest;
import com.baidubce.services.blb.model.ListAppRsResponse;
import com.baidubce.services.blb.model.ListAppSgRequest;
import com.baidubce.services.blb.model.ListAppSgResponse;
import com.baidubce.services.blb.model.ListBackendServerStatusRequest;
import com.baidubce.services.blb.model.ListBackendServerStatusResponse;
import com.baidubce.services.blb.model.ListBlbEsgResponse;
import com.baidubce.services.blb.model.ListBlbRequest;
import com.baidubce.services.blb.model.ListBlbResponse;
import com.baidubce.services.blb.model.ListBlbSgRequest;
import com.baidubce.services.blb.model.ListBlbSgResponse;
import com.baidubce.services.blb.model.ListListenerRequest;
import com.baidubce.services.blb.model.ListListenerResponse;
import com.baidubce.services.blb.model.ListenerConstant;
import com.baidubce.services.blb.model.ModifyBlbAttributesRequest;
import com.baidubce.services.blb.model.SgOperateRequest;
import com.baidubce.services.blb.model.SslListener;
import com.baidubce.services.blb.model.TcpListener;
import com.baidubce.services.blb.model.UdpListener;
import com.baidubce.services.tag.model.Tag;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the Baidu Cloud network Service Baidu Application Load Balance (APPBLB).
 */
public class AppBlbClient extends AbstractBceClient {

    /**
     * BLB API pathVersion
     */
    private static final String VERSION = "v1";

    private static final String PREFIX = "appblb";

    private static final String CLIENT_TOKEN_IDENTIFY = "clientToken";

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static final HttpResponseHandler[] blbHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on blb.
     */
    public AppBlbClient() {
        this(new BlbClientConfiguration());
    }

    /**
     * Constructs a new blb client using the client configuration to access network.
     *
     * @param clientConfiguration The blb client configuration options controlling how this client
     *                            connects to network (e.g. proxy settings, retry counts, etc).
     */
    public AppBlbClient(BlbClientConfiguration clientConfiguration) {
        super(clientConfiguration, blbHandlers);
    }

    /**
     * Creates and initializes a new request object for the specified network resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest    The original request, as created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     *
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
     * Create a blb with the specified options.
     *
     * @param name  The name of blb
     * @param desc  The description of blb
     * @param vpcId The vpcId of blb
     *
     * @return The response contains detail of the blb.
     */
    public CreateBlbResponse createBlb(String name, String desc, String vpcId, String subnetId, List<Tag> tags) {
        return createBlb(new CreateBlbRequest().withName(name).withDesc(desc).withVpcId(vpcId)
                .withSubnetId(subnetId).withTags(tags));
    }

    /**
     * Create a blb with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param createBlbRequest The request containing all options for creating a blb.
     *
     * @return The response contains detail of the blb.
     */
    public CreateBlbResponse createBlb(CreateBlbRequest createBlbRequest) {
        checkNotNull(createBlbRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(createBlbRequest.getClientToken())) {
            createBlbRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(createBlbRequest, HttpMethodName.POST, PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, createBlbRequest.getClientToken());
        fillPayload(internalRequest, createBlbRequest);
        return invokeHttpClient(internalRequest, CreateBlbResponse.class);
    }

    /**
     * Return a list of blbs with the specified options.
     *
     * @param address The address of the blb
     * @param name    The name of the blb.
     * @param blbId   The id of the blb.
     * @param bccId   The bcc id of the blb.
     *
     * @return The response containing a list of blbs owned by the specified options.
     */
    public ListBlbResponse listBlbs(String address, String name, String blbId, String bccId) {
        return listBlbs(new ListBlbRequest().withAddress(address).withName(name).withBlbId(blbId).withBccId(bccId));
    }

    /**
     * Return a list of blbs with the specified options.
     *
     * @param listBlbRequest The request containing all options for listing blbs.
     *
     * @return The response containing a list of blbs with the specified options.
     */
    public ListBlbResponse listBlbs(ListBlbRequest listBlbRequest) {
        checkNotNull(listBlbRequest, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(listBlbRequest, HttpMethodName.GET, PREFIX);
        if (StringUtils.isNotEmpty(listBlbRequest.getAddress())) {
            internalRequest.addParameter("address", listBlbRequest.getAddress());
        }
        if (StringUtils.isNotEmpty(listBlbRequest.getName())) {
            internalRequest.addParameter("name", listBlbRequest.getName());
        }
        if (StringUtils.isNotEmpty(listBlbRequest.getBlbId())) {
            internalRequest.addParameter("blbId", listBlbRequest.getBlbId());
        }
        if (StringUtils.isNotEmpty(listBlbRequest.getBccId())) {
            internalRequest.addParameter("bccId", listBlbRequest.getBccId());
        }
        if (listBlbRequest.getMarker() != null) {
            internalRequest.addParameter("marker", listBlbRequest.getMarker());
        }
        if (listBlbRequest.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(listBlbRequest.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListBlbResponse.class);
    }


    /**
     * Return the blb detail with the specified id.
     *
     * @param request The request containing all options for getting blb detail.
     *
     * @return The response containing the blb detail
     */
    public BlbInstance blbDetail(BlbDetailRequest request) {
        checkStringNotEmpty(request.getBlbId(), "blbId should not be empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, PREFIX, request.getBlbId());
        return invokeHttpClient(internalRequest, BlbInstance.class);
    }

    /**
     * Modifying the special attribute to new blb.
     *
     * @param blbId The id of the blb.
     * @param name  The name after modifying.
     * @param desc  The description after modifying.
     */
    public void modifyBlbAttributes(String blbId, String name, String desc) {
        modifyBlbAttributes(new ModifyBlbAttributesRequest(blbId, name, desc));
    }

    /**
     * Modifying the special attribute to new blb.
     *
     * @param modifyBlbAttributesRequest The request containing all options for modifying a blb.
     */
    public void modifyBlbAttributes(ModifyBlbAttributesRequest modifyBlbAttributesRequest) {
        checkNotNull(modifyBlbAttributesRequest, "request should not be null.");
        checkStringNotEmpty(modifyBlbAttributesRequest.getBlbId(), "request blbId should not be null.");
        if (Strings.isNullOrEmpty(modifyBlbAttributesRequest.getClientToken())) {
            modifyBlbAttributesRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(modifyBlbAttributesRequest, HttpMethodName.PUT, PREFIX,
                modifyBlbAttributesRequest.getBlbId());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, modifyBlbAttributesRequest.getClientToken());
        fillPayload(internalRequest, modifyBlbAttributesRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the specified blb.
     *
     * @param blbId The id of the blb to delete.
     */
    public void deleteBlb(String blbId) {
        deleteBlb(new DeleteBlbRequest(blbId));
    }

    /**
     * Delete the specified blb.
     *
     * @param deleteBlbRequest The request containing all options for deleting blb.
     */
    public void deleteBlb(DeleteBlbRequest deleteBlbRequest) {
        checkNotNull(deleteBlbRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(deleteBlbRequest.getClientToken())) {
            deleteBlbRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(deleteBlbRequest, HttpMethodName.DELETE, PREFIX,
                deleteBlbRequest.getBlbId());
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, deleteBlbRequest.getClientToken());
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create a listener with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param blbListenerRequest The request containing all options for creating a listener.
     */
    public void createListener(BlbListenerRequest blbListenerRequest) {
        checkNotNull(blbListenerRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(blbListenerRequest.getClientToken())) {
            blbListenerRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(blbListenerRequest.getType(), "listener type should not be null.");
        if (!ListenerConstant.LISTENER_SET.contains(blbListenerRequest.getType())) {
            throw new IllegalArgumentException("listener type is illegal.");
        }
        InternalRequest internalRequest = this.createRequest(blbListenerRequest, HttpMethodName.POST, PREFIX,
                blbListenerRequest.getBlbId(), blbListenerRequest.getType() + "listener");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, blbListenerRequest.getClientToken());
        fillPayload(internalRequest, blbListenerRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Return a list of tcp listener with the specified options.
     *
     * @param blbId The blb id of the listener.
     *
     * @return The response containing a list of listener owned by the specified options.
     */
    public ListListenerResponse<TcpListener> listTcpListener(String blbId) {
        return listListener(new ListListenerRequest().withBlbId(blbId).withType(ListenerConstant.TCP_LISTENER));
    }

    /**
     * Return a list of udp listener with the specified options.
     *
     * @param blbId The blb id of the listener.
     *
     * @return The response containing a list of listener owned by the specified options.
     */
    public ListListenerResponse<UdpListener> listUdpListener(String blbId) {
        return listListener(new ListListenerRequest().withBlbId(blbId).withType(ListenerConstant.UDP_LISTENER));
    }

    /**
     * Return a list of http listener with the specified options.
     *
     * @param blbId The blb id of the listener.
     *
     * @return The response containing a list of listener owned by the specified options.
     */
    public ListListenerResponse<HttpListener> listHttpListener(String blbId) {
        return listListener(new ListListenerRequest().withBlbId(blbId).withType(ListenerConstant.HTTP_LISTENER));
    }

    /**
     * Return a list of https listener with the specified options.
     *
     * @param blbId The blb id of the listener.
     *
     * @return The response containing a list of listener owned by the specified options.
     */
    public ListListenerResponse<HttpsListener> listHttpsListener(String blbId) {
        return listListener(new ListListenerRequest().withBlbId(blbId).withType(ListenerConstant.HTTPS_LISTENER));
    }

    /**
     * Return a list of ssl listener with the specified options.
     *
     * @param blbId The blb id of the listener.
     *
     * @return The response containing a list of listener owned by the specified options.
     */
    public ListListenerResponse<SslListener> listSslListener(String blbId) {
        return listListener(new ListListenerRequest().withBlbId(blbId).withType(ListenerConstant.SSL_LISTENER));
    }

    /**
     * Return a list of all listener with the specified options.
     *
     * @param blbId The blb id of the listener.
     *
     * @return The response containing a list of listener.
     */
    public ListListenerResponse<AllListener> listAllListener(String blbId) {
        return listAllListener(new ListAllListenerRequest().withBlbId(blbId));
    }

    /**
     * Return a list of listener with the specified options of blb.
     *
     * @param request The request containing all options for listing listeners of blb.
     *
     * @return The response containing a list of listener with the specified options.
     */
    public ListListenerResponse<AllListener> listAllListener(ListAllListenerRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getBlbId(), "blbId should not be empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, PREFIX,
                request.getBlbId(), "listener");
        if (request.getListenerPort() != 0) {
            internalRequest.addParameter("listenerPort", String.valueOf(request.getListenerPort()));
        }
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListListenerResponse.class);
    }

    /**
     * Return a list of listener with the specified options.
     *
     * @param listListenerRequest The request containing all options for listing listeners.
     *
     * @return The response containing a list of listener with the specified options.
     */
    public ListListenerResponse listListener(ListListenerRequest listListenerRequest) {
        checkNotNull(listListenerRequest, "request should not be null.");
        checkNotNull(listListenerRequest.getType(), "listener type should not be null.");
        if (!ListenerConstant.LISTENER_SET.contains(listListenerRequest.getType())) {
            throw new IllegalArgumentException("listener type is illegal.");
        }
        InternalRequest internalRequest = this.createRequest(listListenerRequest, HttpMethodName.GET, PREFIX,
                listListenerRequest.getBlbId(), listListenerRequest.getType() + "listener");
        if (listListenerRequest.getListenerPort() != 0) {
            internalRequest.addParameter("listenerPort", String.valueOf(listListenerRequest.getListenerPort()));
        }
        if (listListenerRequest.getMarker() != null) {
            internalRequest.addParameter("marker", listListenerRequest.getMarker());
        }
        if (listListenerRequest.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(listListenerRequest.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListListenerResponse.class);
    }

    /**
     * Modifying the special attribute to new listener.
     *
     * @param modifyListenerAttributesRequest The request containing all options for modifying listener.
     */
    public void modifyListenerAttributes(BlbListenerRequest modifyListenerAttributesRequest) {
        checkNotNull(modifyListenerAttributesRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(modifyListenerAttributesRequest.getClientToken())) {
            modifyListenerAttributesRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(modifyListenerAttributesRequest.getType(), "listener type should not be null.");
        if (!ListenerConstant.LISTENER_SET.contains(modifyListenerAttributesRequest.getType())) {
            throw new IllegalArgumentException("listener type is illegal.");
        }
        InternalRequest internalRequest =
                this.createRequest(modifyListenerAttributesRequest, HttpMethodName.PUT, PREFIX,
                        modifyListenerAttributesRequest.getBlbId(),
                        modifyListenerAttributesRequest.getType() + "listener");
        internalRequest.addParameter("listenerPort", String.valueOf(modifyListenerAttributesRequest.getListenerPort()));
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, modifyListenerAttributesRequest.getClientToken());
        fillPayload(internalRequest, modifyListenerAttributesRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the specified listener.
     *
     * @param blbId    The blb id of the listener to delete.
     * @param portList The ports of the listener to delete.
     */
    public void deleteListener(String blbId, List<Integer> portList) {
        deleteListener(new DeleteListenerRequest(blbId, portList));
    }

    /**
     * Delete the specified listener.
     *
     * @param deleteListenerRequest The request containing all options for deleting listener.
     */
    public void deleteListener(DeleteListenerRequest deleteListenerRequest) {
        checkNotNull(deleteListenerRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(deleteListenerRequest.getClientToken())) {
            deleteListenerRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(deleteListenerRequest, HttpMethodName.PUT, PREFIX,
                deleteListenerRequest.getBlbId(), "listener");
        internalRequest.addParameter(BlbListenerAction.batchdelete.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, deleteListenerRequest.getClientToken());
        fillPayload(internalRequest, deleteListenerRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Add backend servers to the specified blb appServerGroup.
     *
     * @param blbId  The id of blb to add backend server.
     * @param sgId  The id of blb appServerGroup to add backend server.
     * @param backendServerList The backend servers to add.
     */
    public void createBlbRs(String blbId, String sgId, List<AppBackendServer> backendServerList) {
        createBlbRs(new AppSgRequest().withBlbId(blbId).withSgId(sgId).withBackendServerList(backendServerList));
    }

    /**
     * Add backend servers to the specified blb appServerGroup.
     *
     * @param appSgRequest The request containing all backend servers for adding to the specified blb appServerGroup.
     */
    public void createBlbRs(AppSgRequest appSgRequest) {
        checkNotNull(appSgRequest, "request should not be null.");
        checkNotNull(appSgRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(appSgRequest.getSgId(), "request sgId should not be null.");
        checkNotNull(appSgRequest.getBackendServerList(), "request backendServerList should not be null.");
        if (Strings.isNullOrEmpty(appSgRequest.getClientToken())) {
            appSgRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(appSgRequest, HttpMethodName.POST, PREFIX,
                appSgRequest.getBlbId(), "blbrs");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appSgRequest.getClientToken());
        fillPayload(internalRequest, appSgRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Add member to the specified blb appIpGroup.
     *
     * @param blbId  The id of blb to add ipGroup member.
     * @param ipGroupId  The id of blb appIpGroup to add member.
     * @param memberList The ipGroup members to add.
     */
    public void createIpGroupMember(String blbId, String ipGroupId, List<AppIpGroupMember> memberList) {
        createIpGroupMember(new AppIgRequest().withBlbId(blbId).withIpGroupId(ipGroupId).withMemberList(memberList));
    }

    /**
     * Add member to the specified blb appIpGroup.
     *
     * @param appIgRequest The request containing all members for adding to the specified blb appIpGroup.
     */
    public void createIpGroupMember(AppIgRequest appIgRequest) {
        checkNotNull(appIgRequest, "request should not be null.");
        checkNotNull(appIgRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(appIgRequest.getIpGroupId(), "request ipGroupId should not be null.");
        checkNotNull(appIgRequest.getMemberList(), "request memberList should not be null.");
        if (Strings.isNullOrEmpty(appIgRequest.getClientToken())) {
            appIgRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(appIgRequest, HttpMethodName.POST, PREFIX,
                appIgRequest.getBlbId(), "ipgroup", "member");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appIgRequest.getClientToken());
        fillPayload(internalRequest, appIgRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Return a list of healthStatus of backend servers with the specified blb and listener port.
     *
     * @param blbId        The id of the specified blb.
     * @param listenerPort The specified listener port.
     *
     * @return The response containing a list healthStatus of backend servers.
     */
    public ListBackendServerStatusResponse listBackendServerStatus(String blbId, int listenerPort) {
        return listBackendServerStatus(new ListBackendServerStatusRequest(blbId, listenerPort));
    }

    /**
     * Return a list of healthStatus of backend server with the specified blb and listener port.
     *
     * @param listBackendServerStatusRequest The request containing all options for listing backend server healtStatus.
     *
     * @return The response containing a list healthStatus of backend servers.
     */
    public ListBackendServerStatusResponse listBackendServerStatus(
            ListBackendServerStatusRequest listBackendServerStatusRequest) {
        checkNotNull(listBackendServerStatusRequest, "request should not be null.");
        InternalRequest internalRequest =
                this.createRequest(listBackendServerStatusRequest, HttpMethodName.GET, PREFIX,
                        listBackendServerStatusRequest.getBlbId(), "backendserver");
        internalRequest.addParameter("listenerPort", String.valueOf(listBackendServerStatusRequest.getListenerPort()));
        if (listBackendServerStatusRequest.getMarker() != null) {
            internalRequest.addParameter("marker", listBackendServerStatusRequest.getMarker());
        }
        if (listBackendServerStatusRequest.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(listBackendServerStatusRequest.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListBackendServerStatusResponse.class);
    }


    /**
     * Return a list of appServerGroup of the specified blb.
     *
     * @param blbId The id of the blb.
     * @param name The name of the appServerGroup.
     *
     * @return The response containing a list of backend servers of the specified blb.
     */
    public ListAppSgResponse listAppServerGroup(String blbId, String name) {
        return listAppServerGroup(new ListAppSgRequest(blbId).withName(name));
    }

    /**
     * Return a list of appServerGroup of the specified blb
     *
     * @param listAppSgRequest The request containing all options for listing appServerGroup.
     *
     * @return The response containing a list of appServerGroup of the specified blb.
     */
    public ListAppSgResponse listAppServerGroup(ListAppSgRequest listAppSgRequest) {
        checkNotNull(listAppSgRequest, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(listAppSgRequest, HttpMethodName.GET, PREFIX,
                listAppSgRequest.getBlbId(), "appservergroup");
        if (StringUtils.isNotEmpty(listAppSgRequest.getName())) {
            internalRequest.addParameter("name", listAppSgRequest.getName());
        }
        if (listAppSgRequest.getMarker() != null) {
            internalRequest.addParameter("marker", listAppSgRequest.getMarker());
        }
        if (listAppSgRequest.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(listAppSgRequest.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListAppSgResponse.class);
    }

    /**
     * Return a list of appIpGroup of the specified blb.
     *
     * @param blbId The id of the blb.
     * @param name The name of the appIpGroup.
     *
     * @return The response containing a list of backend servers of the specified blb.
     */
    public ListAppIgResponse listAppIpGroup(String blbId, String name) {
        return listAppIpGroup(new ListAppIgRequest(blbId).withName(name));
    }

    /**
     * Return a list of appIpGroup of the specified blb
     *
     * @param listAppIgRequest The request containing all options for listing appIpGroup.
     *
     * @return The response containing a list of appIpGroup of the specified blb.
     */
    public ListAppIgResponse listAppIpGroup(ListAppIgRequest listAppIgRequest) {
        checkNotNull(listAppIgRequest, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(listAppIgRequest, HttpMethodName.GET, PREFIX,
                listAppIgRequest.getBlbId(), "ipgroup");
        if (StringUtils.isNotEmpty(listAppIgRequest.getName())) {
            internalRequest.addParameter("name", listAppIgRequest.getName());
        }
        if (listAppIgRequest.getMarker() != null) {
            internalRequest.addParameter("marker", listAppIgRequest.getMarker());
        }
        if (listAppIgRequest.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(listAppIgRequest.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListAppIgResponse.class);
    }

    /**
     * Return a list of backend server of the specified blb appServerGroup.
     *
     * @param blbId The id of the blb.
     * @param sgId The id of the appServerGroup.
     *
     * @return The response containing a list of backend servers of the specified blb appServerGroup.
     */
    public ListAppRsMountResponse listBlbRsMount(String blbId, String sgId) {
        return listBlbRsMount(new ListAppRsRequest(blbId).withSgId(sgId));
    }

    /**
     * Return a list of backend server of the specified blb appServerGroup
     *
     * @param listAppRsRequest The request containing all options for listing backend server.
     *
     * @return The response containing a list of backend servers of the specified blb appServerGroup.
     */
    public ListAppRsMountResponse listBlbRsMount(ListAppRsRequest listAppRsRequest) {
        checkNotNull(listAppRsRequest, "request should not be null.");
        checkNotNull(listAppRsRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(listAppRsRequest.getSgId(), "request sgId should not be null.");
        InternalRequest internalRequest = this.createRequest(listAppRsRequest, HttpMethodName.GET, PREFIX,
                listAppRsRequest.getBlbId(), "blbrsmount");
        internalRequest.addParameter("sgId", listAppRsRequest.getSgId());
        return invokeHttpClient(internalRequest, ListAppRsMountResponse.class);

    }

    /**
     * Return a list of backend server of the specified blb appServerGroup.
     *
     * @param blbId The id of the blb.
     * @param sgId The id of the appServerGroup.
     *
     * @return The response containing a list of backend servers of the specified blb appServerGroup.
     */
    public ListAppRsUnMountResponse listBlbRsUnMount(String blbId, String sgId) {
        return listBlbRsUnMount(new ListAppRsRequest(blbId).withSgId(sgId));
    }

    /**
     * Return a list of backend server of the specified blb appServerGroup
     *
     * @param listAppRsRequest The request containing all options for listing backend server.
     *
     * @return The response containing a list of backend servers of the specified blb appServerGroup.
     */
    public ListAppRsUnMountResponse listBlbRsUnMount(ListAppRsRequest listAppRsRequest) {
        checkNotNull(listAppRsRequest, "request should not be null.");
        checkNotNull(listAppRsRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(listAppRsRequest.getSgId(), "request sgId should not be null.");
        InternalRequest internalRequest = this.createRequest(listAppRsRequest, HttpMethodName.GET, PREFIX,
                listAppRsRequest.getBlbId(), "blbrsunmount");
        internalRequest.addParameter("sgId", listAppRsRequest.getSgId());
        return invokeHttpClient(internalRequest, ListAppRsUnMountResponse.class);

    }

    /**
     * Return a list of backend server of the specified blb appServerGroup.
     *
     * @param blbId The id of the blb.
     * @param sgId The id of the appServerGroup.
     *
     * @return The response containing a list of backend servers of the specified blb appServerGroup.
     */
    public ListAppRsResponse listBlbRs(String blbId, String sgId) {
        return listBlbRs(new ListAppRsRequest(blbId).withSgId(sgId));
    }

    /**
     * Return a list of backend server of the specified blb appServerGroup
     *
     * @param listAppRsRequest The request containing all options for listing backend server.
     *
     * @return The response containing a list of backend servers of the specified blb appServerGroup.
     */
    public ListAppRsResponse listBlbRs(ListAppRsRequest listAppRsRequest) {
        checkNotNull(listAppRsRequest, "request should not be null.");
        checkNotNull(listAppRsRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(listAppRsRequest.getSgId(), "request sgId should not be null.");
        InternalRequest internalRequest = this.createRequest(listAppRsRequest, HttpMethodName.GET, PREFIX,
                listAppRsRequest.getBlbId(), "blbrs");
        internalRequest.addParameter("sgId", listAppRsRequest.getSgId());
        if (listAppRsRequest.getMarker() != null) {
            internalRequest.addParameter("marker", listAppRsRequest.getMarker());
        }
        if (listAppRsRequest.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(listAppRsRequest.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListAppRsResponse.class);

    }

    /**
     * Return a list of member of the specified blb appIpGroup.
     *
     * @param blbId The id of the blb.
     * @param ipGroupId The id of the appIpGroup.
     *
     * @return The response containing a list of member of the specified blb appIpGroup.
     */
    public ListAppIpGroupMemberResponse listIpGroupMember(String blbId, String ipGroupId) {
        return listIpGroupMember(new ListAppIpGroupMemberRequest(blbId).withIpGroupId(ipGroupId));
    }

    /**
     * Return a list of member of the specified blb appIpGroup
     *
     * @param listAppIpGroupMemberRequest The request containing all options for listing member.
     *
     * @return The response containing a list of member of the specified blb appIpGroup.
     */
    public ListAppIpGroupMemberResponse listIpGroupMember(ListAppIpGroupMemberRequest listAppIpGroupMemberRequest) {
        checkNotNull(listAppIpGroupMemberRequest, "request should not be null.");
        checkNotNull(listAppIpGroupMemberRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(listAppIpGroupMemberRequest.getIpGroupId(), "request ipGroupId should not be null.");
        InternalRequest internalRequest = this.createRequest(listAppIpGroupMemberRequest, HttpMethodName.GET, PREFIX,
                listAppIpGroupMemberRequest.getBlbId(), "ipgroup", "member");
        internalRequest.addParameter("ipGroupId", listAppIpGroupMemberRequest.getIpGroupId());
        if (listAppIpGroupMemberRequest.getMarker() != null) {
            internalRequest.addParameter("marker", listAppIpGroupMemberRequest.getMarker());
        }
        if (listAppIpGroupMemberRequest.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(listAppIpGroupMemberRequest.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListAppIpGroupMemberResponse.class);

    }




    /**
     * Modifying the special backend servers of the specified blb appServerGroup.
     *
     * @param blbId             The id of the specified blb.
     * @param sgId              The id of blb appServerGroup.
     * @param backendServerList The backend servers to modifying.
     */
    public void modifyBlbRs(String blbId, String sgId, List<AppBackendServer> backendServerList) {
        modifyBlbRs(new AppSgRequest().withBlbId(blbId).withSgId(sgId).withBackendServerList(backendServerList));
    }

    /**
     * Modifying the special backend servers of the specified blb appServerGroup.
     *
     * @param appSgRequest The request containing all options for modifying backend servers.
     */
    public void modifyBlbRs(AppSgRequest appSgRequest) {
        checkNotNull(appSgRequest, "request should not be null.");
        checkNotNull(appSgRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(appSgRequest.getSgId(), "request sgId should not be null.");
        checkNotNull(appSgRequest.getBackendServerList(), "request backendServerList should not be null.");
        if (Strings.isNullOrEmpty(appSgRequest.getClientToken())) {
            appSgRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(appSgRequest, HttpMethodName.PUT, PREFIX,
                appSgRequest.getBlbId(), "blbrs");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appSgRequest.getClientToken());
        fillPayload(internalRequest, appSgRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modifying the special members of the specified blb appIpGroup.
     *
     * @param blbId             The id of the specified blb.
     * @param ipGroupId         The id of blb appIpGroup.
     * @param memberList        The ipGroup members to modifying.
     */
    public void modifyIpGroupMember(String blbId, String ipGroupId, List<AppIpGroupMember> memberList) {
        modifyIpGroupMember(new AppIgRequest().withBlbId(blbId).withIpGroupId(ipGroupId).withMemberList(memberList));
    }

    /**
     * Modifying the special members of the specified blb appIpGroup.
     *
     * @param appIgRequest The request containing all options for modifying members.
     */
    public void modifyIpGroupMember(AppIgRequest appIgRequest) {
        checkNotNull(appIgRequest, "request should not be null.");
        checkNotNull(appIgRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(appIgRequest.getIpGroupId(), "request ipGroupId should not be null.");
        checkNotNull(appIgRequest.getMemberList(), "request memberList should not be null.");
        if (Strings.isNullOrEmpty(appIgRequest.getClientToken())) {
            appIgRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(appIgRequest, HttpMethodName.PUT, PREFIX,
                appIgRequest.getBlbId(), "ipgroup", "member");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appIgRequest.getClientToken());
        fillPayload(internalRequest, appIgRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * Delete the specified backend server from the specified blb appServerGroup.
     *
     * @param blbId             The id of the blb.
     * @param sgId             The id of the appServerGroup.
     * @param backendServerIdList The id list of the backend server id to deleting.
     */
    public void deleteBlbRs(String blbId, String sgId, List<String> backendServerIdList) {
        deleteBlbRs(new AppRsRequest().withBlbId(blbId).withSgId(sgId)
                .withBackendServerIdList(backendServerIdList));
    }

    /**
     * Delete the specified backend server from the specified blb appServerGroup.
     *
     * @param appRsRequest The request containing all options for deleting backend server from the specified blb appServerGroup.
     */
    public void deleteBlbRs(AppRsRequest appRsRequest) {
        checkNotNull(appRsRequest, "request should not be null.");
        checkNotNull(appRsRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(appRsRequest.getSgId(), "request sgId should not be null.");
        checkNotNull(appRsRequest.getBackendServerIdList(), "request backendServerIdList should not be null.");
        if (Strings.isNullOrEmpty(appRsRequest.getClientToken())) {
            appRsRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(appRsRequest, HttpMethodName.PUT, PREFIX,
                appRsRequest.getBlbId(), "blbrs");
        internalRequest.addParameter("batchdelete", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appRsRequest.getClientToken());
        fillPayload(internalRequest, appRsRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the specified member from the specified blb appIpGroup.
     *
     * @param blbId             The id of the blb.
     * @param ipGroupId         The id of the appIpGroup.
     * @param memberIdList      The id list of the member id to deleting.
     */
    public void deleteIpGroupMember(String blbId, String ipGroupId, List<String> memberIdList) {
        deleteIpGroupMember(new AppIpGroupMemberRequest().withBlbId(blbId).withIpGroupId(ipGroupId)
                .withMemberIdList(memberIdList));
    }

    /**
     * Delete the specified member from the specified blb appIpGroup.
     *
     * @param appIpGroupMemberRequest The request containing all options for deleting member
     *                                from the specified blb appIpGroup.
     */
    public void deleteIpGroupMember(AppIpGroupMemberRequest appIpGroupMemberRequest) {
        checkNotNull(appIpGroupMemberRequest, "request should not be null.");
        checkNotNull(appIpGroupMemberRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(appIpGroupMemberRequest.getIpGroupId(), "request ipGroupId should not be null.");
        checkNotNull(appIpGroupMemberRequest.getMemberIdList(), "request memberIdList should not be null.");
        if (Strings.isNullOrEmpty(appIpGroupMemberRequest.getClientToken())) {
            appIpGroupMemberRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(appIpGroupMemberRequest, HttpMethodName.PUT, PREFIX,
                appIpGroupMemberRequest.getBlbId(), "ipgroup", "member");
        internalRequest.addParameter("delete", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appIpGroupMemberRequest.getClientToken());
        fillPayload(internalRequest, appIpGroupMemberRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * Create an appServerGroup with the specified options.
     *
     * @param blbId The id of blb
     * @param name  The name of appServerGroup
     * @param desc  The description of appServerGroup
     * @param backendServerList The backendServers of appServerGroup
     *
     * @return The response contains detail of the appServerGroup.
     */
    public AppSgResponse createAppServerGroup(String blbId, String name, String desc
            , List<AppBackendServer> backendServerList) {
        return createAppServerGroup(new AppSgRequest().withBlbId(blbId).withName(name).withDesc(desc)
                .withBackendServerList(backendServerList));
    }

    /**
     * Create an appServerGroup with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param appSgRequest The request containing all options for creating a appSg.
     */
    public AppSgResponse createAppServerGroup(AppSgRequest appSgRequest) {
        checkNotNull(appSgRequest, "request should not be null.");
        checkNotNull(appSgRequest.getBlbId(), "request blbId should not be null.");
        if (Strings.isNullOrEmpty(appSgRequest.getClientToken())) {
            appSgRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(appSgRequest, HttpMethodName.POST, PREFIX,
                appSgRequest.getBlbId(), "appservergroup");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appSgRequest.getClientToken());
        fillPayload(internalRequest, appSgRequest);
        return invokeHttpClient(internalRequest, AppSgResponse.class);
    }


    /**
     * Create an appIpGroup with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param appIgRequest The request containing all options for creating an appIg.
     */
    public AppIgResponse createAppIpGroup(AppIgRequest appIgRequest) {
        checkNotNull(appIgRequest, "request should not be null.");
        checkNotNull(appIgRequest.getBlbId(), "request blbId should not be null.");
        if (Strings.isNullOrEmpty(appIgRequest.getClientToken())) {
            appIgRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(appIgRequest, HttpMethodName.POST, PREFIX,
                appIgRequest.getBlbId(), "ipgroup");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appIgRequest.getClientToken());
        fillPayload(internalRequest, appIgRequest);
        return invokeHttpClient(internalRequest, AppIgResponse.class);
    }


    /**
     * Create an appIpGroup with the specified options.
     *
     * @param blbId The id of blb
     * @param name  The name of appIpGroup
     * @param desc  The description of appIpGroup
     * @param memberList The backen of appIpGroup
     *
     * @return The response contains detail of the appIpGroup.
     */
    public AppIgResponse createAppIpGroup(String blbId, String name, String desc
            , List<AppIpGroupMember> memberList) {
        return createAppIpGroup(new AppIgRequest().withBlbId(blbId).withName(name).withDesc(desc)
                .withMemberList(memberList));
    }

    /**
     * Update a appServerGroup with the specified options.
     *
     * @param blbId The id of blb
     * @param sgId The id of appServerGroup
     * @param name  The name of appServerGroup
     * @param desc  The description of appServerGroup
     *
     */
    public void modifyAppServerGroupAttributes(String blbId, String sgId, String name, String desc) {
        modifyAppServerGroupAttributes(new AppSgRequest().withBlbId(blbId).withSgId(sgId)
                .withName(name).withDesc(desc));
    }


    /**
     * Modifying the special attribute to new appServerGroup.
     *
     * @param appSgRequest The request containing all options for modifying appSgRequest.
     */
    public void modifyAppServerGroupAttributes(AppSgRequest appSgRequest) {
        checkNotNull(appSgRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(appSgRequest.getClientToken())) {
            appSgRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(appSgRequest.getBlbId(), "blbId should not be null.");
        checkNotNull(appSgRequest.getSgId(), "sgId should not be null.");
        InternalRequest internalRequest =
                this.createRequest(appSgRequest, HttpMethodName.PUT, PREFIX,
                        appSgRequest.getBlbId(), "appservergroup");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appSgRequest.getClientToken());
        fillPayload(internalRequest, appSgRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update an appIpGroup with the specified options.
     *
     * @param blbId The id of blb
     * @param ipGroupId The id of appIpGroup
     * @param name  The name of appIpGroup
     * @param desc  The description of appIpGroup
     *
     */
    public void modifyAppIpGroupAttributes(String blbId, String ipGroupId, String name, String desc) {
        modifyAppIpGroupAttributes(new AppIgRequest().withBlbId(blbId).withIpGroupId(ipGroupId)
                .withName(name).withDesc(desc));
    }


    /**
     * Modifying the special attribute to new appIpGroup.
     *
     * @param appIgRequest The request containing all options for modifying appIgRequest.
     */
    public void modifyAppIpGroupAttributes(AppIgRequest appIgRequest) {
        checkNotNull(appIgRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(appIgRequest.getClientToken())) {
            appIgRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(appIgRequest.getBlbId(), "blbId should not be null.");
        checkNotNull(appIgRequest.getIpGroupId(), "ipGroupId should not be null.");
        InternalRequest internalRequest =
                this.createRequest(appIgRequest, HttpMethodName.PUT, PREFIX,
                        appIgRequest.getBlbId(), "ipgroup");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appIgRequest.getClientToken());
        fillPayload(internalRequest, appIgRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }




    /**
     * Delete a appServerGroup with the specified options.
     *
     * @param blbId The id of blb
     * @param sgId The id of appServerGroup
     *
     */
    public void deleteAppServerGroup(String blbId, String sgId) {
        deleteAppServerGroup(new AppSgRequest().withBlbId(blbId).withSgId(sgId));
    }


    /**
     * Delete the special appServerGroup.
     *
     * @param appSgRequest The request containing all options for deleting appSgRequest.
     */
    public void deleteAppServerGroup(AppSgRequest appSgRequest) {
        checkNotNull(appSgRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(appSgRequest.getClientToken())) {
            appSgRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(appSgRequest.getBlbId(), "blbId should not be null.");
        checkNotNull(appSgRequest.getSgId(), "sgId should not be null.");
        InternalRequest internalRequest = this.createRequest(appSgRequest, HttpMethodName.PUT
                , PREFIX, appSgRequest.getBlbId(), "appservergroup");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appSgRequest.getClientToken());
        internalRequest.addParameter("delete", null);
        fillPayload(internalRequest, appSgRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete an appIpGroup with the specified options.
     *
     * @param blbId The id of blb
     * @param ipGroupId The id of appIpGroup
     *
     */
    public void deleteAppIpGroup(String blbId, String ipGroupId) {
        deleteAppIpGroup(new AppIgRequest().withBlbId(blbId).withIpGroupId(ipGroupId));
    }


    /**
     * Delete the special appIpGroup.
     *
     * @param appIgRequest The request containing all options for deleting appIpGroup.
     */
    public void deleteAppIpGroup(AppIgRequest appIgRequest) {
        checkNotNull(appIgRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(appIgRequest.getClientToken())) {
            appIgRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(appIgRequest.getBlbId(), "blbId should not be null.");
        checkNotNull(appIgRequest.getIpGroupId(), "ipGroupId should not be null.");
        InternalRequest internalRequest = this.createRequest(appIgRequest, HttpMethodName.PUT
                , PREFIX, appIgRequest.getBlbId(), "ipgroup");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appIgRequest.getClientToken());
        internalRequest.addParameter("delete", null);
        fillPayload(internalRequest, appIgRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create an appIpGroupBackendPolicy with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param appBackendPolicyRequest The request containing all options for creating an appIpGroupBackendPolicy.
     */
    public void createAppIpGroupBackendPolicy(AppBackendPolicyRequest appBackendPolicyRequest) {
        checkNotNull(appBackendPolicyRequest, "request should not be null.");
        checkNotNull(appBackendPolicyRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(appBackendPolicyRequest.getIpGroupId(), "request ipGroupId should not be null.");
        checkNotNull(appBackendPolicyRequest.getType(), "request type should not be null.");
        if (Strings.isNullOrEmpty(appBackendPolicyRequest.getClientToken())) {
            appBackendPolicyRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(appBackendPolicyRequest, HttpMethodName.POST, PREFIX,
                appBackendPolicyRequest.getBlbId(), "ipgroup", "backendpolicy");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appBackendPolicyRequest.getClientToken());
        fillPayload(internalRequest, appBackendPolicyRequest);
        invokeHttpClient(internalRequest, AppSgPortResponse.class);
    }

    /**
     * Modifying the special attribute to new appIpGroupBackendPolicy.
     *
     * @param appBackendPolicyRequest The request containing all options for modifying appIpGroupBackendPolicy.
     */
    public void modifyAppIpGroupBackendPolicyAttributes(AppBackendPolicyRequest appBackendPolicyRequest) {
        checkNotNull(appBackendPolicyRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(appBackendPolicyRequest.getClientToken())) {
            appBackendPolicyRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(appBackendPolicyRequest.getBlbId(), "blbId should not be null.");
        checkNotNull(appBackendPolicyRequest.getIpGroupId(), "ipGroupId should not be null.");
        checkNotNull(appBackendPolicyRequest.getId(), "id should not be null.");
        InternalRequest internalRequest =
                this.createRequest(appBackendPolicyRequest, HttpMethodName.PUT, PREFIX,
                        appBackendPolicyRequest.getBlbId(), "ipgroup", "backendpolicy");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appBackendPolicyRequest.getClientToken());
        fillPayload(internalRequest, appBackendPolicyRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete an appIpGroupBackendPolicy with the specified options.
     *
     * @param blbId The id of blb
     * @param ipGroupId The id of appServerGroup
     * @param backendPolicyIdList The id of appIpGroupBackendPolicy list
     *
     */
    public void deleteAppIpGroupBackendPolicy(String blbId, String ipGroupId, List<String> backendPolicyIdList) {
        deleteAppIpGroupBackendPolicy(new AppBackendPolicyRequest().withBlbId(blbId)
                .withIpGroupId(ipGroupId).withBackendPolicyIdList(backendPolicyIdList));
    }


    /**
     * Delete the special AppBackendPolicy.
     *
     * @param appBackendPolicyRequest The request containing all options for deleting appBackendPolicy.
     */
    public void deleteAppIpGroupBackendPolicy(AppBackendPolicyRequest appBackendPolicyRequest) {
        checkNotNull(appBackendPolicyRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(appBackendPolicyRequest.getClientToken())) {
            appBackendPolicyRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(appBackendPolicyRequest.getBlbId(), "blbId should not be null.");
        checkNotNull(appBackendPolicyRequest.getIpGroupId(), "ipGroupId should not be null.");
        checkNotNull(appBackendPolicyRequest.getBackendPolicyIdList(), "backendPolicyIdList should not be null.");
        InternalRequest internalRequest = this.createRequest(appBackendPolicyRequest, HttpMethodName.PUT
                , PREFIX, appBackendPolicyRequest.getBlbId(), "ipgroup", "backendpolicy");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appBackendPolicyRequest.getClientToken());
        internalRequest.addParameter("delete", null);
        fillPayload(internalRequest, appBackendPolicyRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * Create a appServerGroupPort with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param appSgPortRequest The request containing all options for creating a appSgPort.
     */
    public AppSgPortResponse createAppServerGroupPort(AppSgPortRequest appSgPortRequest) {
        checkNotNull(appSgPortRequest, "request should not be null.");
        checkNotNull(appSgPortRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(appSgPortRequest.getSgId(), "request sgId should not be null.");
        checkNotNull(appSgPortRequest.getPort(), "request port should not be null.");
        checkNotNull(appSgPortRequest.getType(), "request type should not be null.");
        if (Strings.isNullOrEmpty(appSgPortRequest.getClientToken())) {
            appSgPortRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(appSgPortRequest, HttpMethodName.POST, PREFIX,
                appSgPortRequest.getBlbId(), "appservergroupport");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appSgPortRequest.getClientToken());
        fillPayload(internalRequest, appSgPortRequest);
        return invokeHttpClient(internalRequest, AppSgPortResponse.class);
    }


    /**
     * Modifying the special attribute to new appServerGroupPort.
     *
     * @param appSgPortRequest The request containing all options for modifying appSgPortRequest.
     */
    public void modifyAppServerGroupPortAttributes(AppSgPortRequest appSgPortRequest) {
        checkNotNull(appSgPortRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(appSgPortRequest.getClientToken())) {
            appSgPortRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(appSgPortRequest.getBlbId(), "blbId should not be null.");
        checkNotNull(appSgPortRequest.getSgId(), "sgId should not be null.");
        checkNotNull(appSgPortRequest.getPortId(), "portId should not be null.");
        InternalRequest internalRequest =
                this.createRequest(appSgPortRequest, HttpMethodName.PUT, PREFIX,
                        appSgPortRequest.getBlbId(), "appservergroupport");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appSgPortRequest.getClientToken());
        fillPayload(internalRequest, appSgPortRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete a appServerGroup with the specified options.
     *
     * @param blbId The id of blb
     * @param sgId The id of appServerGroup
     * @param portIdList The id of appServerGroupPort list
     *
     */
    public void deleteAppServerGroupPort(String blbId, String sgId, List<String> portIdList) {
        deleteAppServerGroupPort(new AppSgPortRequest().withBlbId(blbId).withSgId(sgId).withPortIdList(portIdList));
    }


    /**
     * Delete the special appServerGroupPort.
     *
     * @param appSgPortRequest The request containing all options for deleting AppSgPortRequest.
     */
    public void deleteAppServerGroupPort(AppSgPortRequest appSgPortRequest) {
        checkNotNull(appSgPortRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(appSgPortRequest.getClientToken())) {
            appSgPortRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(appSgPortRequest.getBlbId(), "blbId should not be null.");
        checkNotNull(appSgPortRequest.getSgId(), "sgId should not be null.");
        checkNotNull(appSgPortRequest.getPortIdList(), "portIdList should not be null.");
        InternalRequest internalRequest = this.createRequest(appSgPortRequest, HttpMethodName.PUT
                , PREFIX, appSgPortRequest.getBlbId(), "appservergroupport");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appSgPortRequest.getClientToken());
        internalRequest.addParameter("batchdelete", null);
        fillPayload(internalRequest, appSgPortRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }


    /**
     * Create a policy with the specified options.
     *
     * @param blbId The id of blb
     * @param listenerPort  The listenerPort of policy
     * @param type  The listenerType of Policy
     * @param appPolicyVos The appPolicyVos of policy
     *
     */
    public CreateAppPolicyResponse createPolicys(String blbId, Integer listenerPort, String type,
                                                 List<AppPolicy> appPolicyVos) {
        return createPolicys(new AppPolicyRequest().withBlbId(blbId).withListenerPort(listenerPort).withType(type)
                .withAppPolicyVos(appPolicyVos));
    }

    /**
     * Create a policy with the specified options.
     * You must fill the field of clientToken,which is especially for keeping idempotent.
     *
     * @param appPolicyRequest The request containing all options for creating a policy.
     */
    public CreateAppPolicyResponse createPolicys(AppPolicyRequest appPolicyRequest) {
        checkNotNull(appPolicyRequest, "request should not be null.");
        checkNotNull(appPolicyRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(appPolicyRequest.getListenerPort(), "request listenerPort should not be null.");
        checkNotNull(appPolicyRequest.getAppPolicyVos(), "request appPolicyVos should not be null.");
        if (Strings.isNullOrEmpty(appPolicyRequest.getClientToken())) {
            appPolicyRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(appPolicyRequest, HttpMethodName.POST, PREFIX,
                appPolicyRequest.getBlbId(), "policys");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, appPolicyRequest.getClientToken());
        fillPayload(internalRequest, appPolicyRequest);
        return invokeHttpClient(internalRequest, CreateAppPolicyResponse.class);
    }


    /**
     * Return a list of backend server of the specified blb appServerGroup.
     *
     * @param blbId The id of the blb.
     * @param port The port of the policy.
     *
     * @return The response containing a list of policy of the specified blb.
     */
    public ListAppPolicyResponse listPolicys(String blbId, Integer port) {
        return listPolicys(new ListAppPolicyRequest().withBlbId(blbId).withPort(port));
    }

    /**
     * Return a list of policy of the specified blb
     *
     * @param listAppPolicyRequest The request containing all options for listing policy.
     *
     * @return The response containing a list of policy of the specified blb.
     */
    public ListAppPolicyResponse listPolicys(ListAppPolicyRequest listAppPolicyRequest) {
        checkNotNull(listAppPolicyRequest, "request should not be null.");
        checkNotNull(listAppPolicyRequest.getBlbId(), "request blbId should not be null.");
        checkNotNull(listAppPolicyRequest.getPort(), "request port should not be null.");
        InternalRequest internalRequest = this.createRequest(listAppPolicyRequest, HttpMethodName.GET, PREFIX,
                listAppPolicyRequest.getBlbId(), "policys");
        internalRequest.addParameter("port", String.valueOf(listAppPolicyRequest.getPort()));
        if (listAppPolicyRequest.getMarker() != null) {
            internalRequest.addParameter("marker", listAppPolicyRequest.getMarker());
        }
        if (listAppPolicyRequest.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(listAppPolicyRequest.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListAppPolicyResponse.class);

    }


    /**
     * Delete a appServerGroup with the specified options.
     *
     * @param blbId The id of blb
     * @param port The port of policy
     * @param policyIdList The id of policy list
     *
     */
    public void deletePolicys(String blbId, Integer port, List<String> policyIdList) {
        deletePolicys(new DeleteAppPolicyRequest().withBlbId(blbId)
                .withPort(port).withPolicyIdList(policyIdList));
    }


    /**
     * Delete the special policy.
     *
     * @param deleteAppPolicyRequest The request containing all options for deleting policy.
     */
    public void deletePolicys(DeleteAppPolicyRequest deleteAppPolicyRequest) {
        checkNotNull(deleteAppPolicyRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(deleteAppPolicyRequest.getClientToken())) {
            deleteAppPolicyRequest.setClientToken(this.generateClientToken());
        }
        checkNotNull(deleteAppPolicyRequest.getBlbId(), "blbId should not be null.");
        checkNotNull(deleteAppPolicyRequest.getPort(), "port should not be null.");
        checkNotNull(deleteAppPolicyRequest.getPolicyIdList(), "policyIdList should not be null.");
        InternalRequest internalRequest = this.createRequest(deleteAppPolicyRequest, HttpMethodName.PUT
                , PREFIX, deleteAppPolicyRequest.getBlbId(), "policys");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, deleteAppPolicyRequest.getClientToken());
        internalRequest.addParameter("batchdelete", null);
        fillPayload(internalRequest, deleteAppPolicyRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * bind the securityGroups on the specified blb.
     *
     * @param request   The request containing all options for binding sg.
     */
    public void bindSg(SgOperateRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getBlbId(), "blbId should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, "blb",
                request.getBlbId(), "securitygroup");
        internalRequest.addParameter("bind", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * unbind the securityGroups from the specified blb.
     *
     * @param request   The request containing all options for unbinding sg.
     */
    public void unBindSg(SgOperateRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getBlbId(), "blbId should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, "blb",
                request.getBlbId(), "securitygroup");
        internalRequest.addParameter("unbind", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Return a list of sg info of the specified blb
     *
     * @param request The request containing all options for listing blb's sg.
     *
     * @return The response containing a listing blb's sg.
     */
    public ListBlbSgResponse listBlbSg(ListBlbSgRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getBlbId(), "blbId should not be empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, "blb",
                request.getBlbId(), "securitygroup");
        return invokeHttpClient(internalRequest, ListBlbSgResponse.class);
    }

    /**
     * bind the enterpriseSecurityGroups on the specified blb.
     *
     * @param request   The request containing all options for binding esg.
     */
    public void bindEsg(EsgOperateRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getBlbId(), "blbId should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, "blb",
                request.getBlbId(), "enterprise", "securitygroup");
        internalRequest.addParameter("bind", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * unbind the enterpriseSecurityGroups from the specified blb.
     *
     * @param request   The request containing all options for unbinding esg.
     */
    public void unBindEsg(EsgOperateRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getBlbId(), "blbId should not be empty");
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, "blb",
                request.getBlbId(), "enterprise", "securitygroup");
        internalRequest.addParameter("unbind", null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, request.getClientToken());
        fillPayload(internalRequest, request);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Return a list of esg info of the specified blb
     *
     * @param request The request containing all options for listing blb's esg.
     *
     * @return The response containing a listing blb's esg.
     */
    public ListBlbEsgResponse listBlbEsg(ListBlbSgRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getBlbId(), "blbId should not be empty");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, "blb",
                request.getBlbId(), "enterprise", "securitygroup");
        return invokeHttpClient(internalRequest, ListBlbEsgResponse.class);
    }
}

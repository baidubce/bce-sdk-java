/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
import com.baidubce.services.blb.model.AddBackendServersRequest;
import com.baidubce.services.blb.model.BSAction;
import com.baidubce.services.blb.model.BackendServer;
import com.baidubce.services.blb.model.BlbListenerAction;
import com.baidubce.services.blb.model.BlbListenerRequest;
import com.baidubce.services.blb.model.CreateBlbRequest;
import com.baidubce.services.blb.model.CreateBlbResponse;
import com.baidubce.services.blb.model.DeleteBSRequest;
import com.baidubce.services.blb.model.DeleteBlbRequest;
import com.baidubce.services.blb.model.DeleteListenerRequest;
import com.baidubce.services.blb.model.HttpListener;
import com.baidubce.services.blb.model.HttpsListener;
import com.baidubce.services.blb.model.ListBackendServerRequest;
import com.baidubce.services.blb.model.ListBackendServerResponse;
import com.baidubce.services.blb.model.ListBackendServerStatusRequest;
import com.baidubce.services.blb.model.ListBackendServerStatusResponse;
import com.baidubce.services.blb.model.ListBlbRequest;
import com.baidubce.services.blb.model.ListBlbResponse;
import com.baidubce.services.blb.model.ListListenerRequest;
import com.baidubce.services.blb.model.ListListenerResponse;
import com.baidubce.services.blb.model.ListenerConstant;
import com.baidubce.services.blb.model.ModifyBSAttributesRequest;
import com.baidubce.services.blb.model.ModifyBlbAttributesRequest;
import com.baidubce.services.blb.model.TcpListener;
import com.baidubce.services.blb.model.UdpListener;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;

/**
 * Provides the client for accessing the Baidu Cloud network Service Baidu Load Balance (BLB).
 */
public class BlbClient extends AbstractBceClient {

    /**
     * BLB API pathVersion
     */
    private static final String VERSION = "v1";

    private static final String PREFIX = "blb";

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
    public BlbClient() {
        this(new BlbClientConfiguration());
    }

    /**
     * Constructs a new blb client using the client configuration to access network.
     *
     * @param clientConfiguration The blb client configuration options controlling how this client
     *                            connects to network (e.g. proxy settings, retry counts, etc).
     */
    public BlbClient(BlbClientConfiguration clientConfiguration) {
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
    public CreateBlbResponse createBlb(String name, String desc, String vpcId, String subnetId) {
        return createBlb(new CreateBlbRequest().withName(name).withDesc(desc).withVpcId(vpcId).withSubnetId(subnetId));
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
     * Add backend servers to the specified blb.
     *
     * @param blbId             The id of blb to add backend server.
     * @param backendServerList The backend servers to add.
     */
    public void addBackendServers(String blbId, List<BackendServer> backendServerList) {
        addBackendServers(new AddBackendServersRequest(blbId, backendServerList));
    }

    /**
     * Add backend servers to the specified blb.
     *
     * @param addBackendServersRequest The request containing all backend servers for adding to the specified blb.
     */
    public void addBackendServers(AddBackendServersRequest addBackendServersRequest) {
        checkNotNull(addBackendServersRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(addBackendServersRequest.getClientToken())) {
            addBackendServersRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(addBackendServersRequest, HttpMethodName.POST, PREFIX,
                addBackendServersRequest.getBlbId(), "backendserver");
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, addBackendServersRequest.getClientToken());
        fillPayload(internalRequest, addBackendServersRequest);
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
     * Return a list of backend server of the specified blb.
     *
     * @param blbId The id of the blb.
     *
     * @return The response containing a list of backend servers of the specified blb.
     */
    public ListBackendServerResponse listBackendServers(String blbId) {
        return listBackendServers(new ListBackendServerRequest(blbId));
    }

    /**
     * Return a list of backend server of the specified blb
     *
     * @param listBackendServerRequest The request containing all options for listing backend server.
     *
     * @return The response containing a list of backend servers of the specified blb.
     */
    public ListBackendServerResponse listBackendServers(ListBackendServerRequest listBackendServerRequest) {
        checkNotNull(listBackendServerRequest, "request should not be null.");
        InternalRequest internalRequest = this.createRequest(listBackendServerRequest, HttpMethodName.GET, PREFIX,
                listBackendServerRequest.getBlbId(), "backendserver");
        if (listBackendServerRequest.getMarker() != null) {
            internalRequest.addParameter("marker", listBackendServerRequest.getMarker());
        }
        if (listBackendServerRequest.getMaxKeys() > 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(listBackendServerRequest.getMaxKeys()));
        }
        return invokeHttpClient(internalRequest, ListBackendServerResponse.class);

    }

    /**
     * Modifying the special backend servers of the specified blb.
     *
     * @param blbId             The id of the specified blb.
     * @param backendServerList The backend servers to modifying.
     */
    public void modifyBackendServerAttributes(String blbId, List<BackendServer> backendServerList) {
        modifyBackendServerAttributes(new ModifyBSAttributesRequest(blbId, backendServerList));
    }

    /**
     * Modifying the special backend servers of the specified blb.
     *
     * @param modifyBSAttributesRequest The request containing all options for modifying backend servers.
     */
    public void modifyBackendServerAttributes(ModifyBSAttributesRequest modifyBSAttributesRequest) {
        checkNotNull(modifyBSAttributesRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(modifyBSAttributesRequest.getClientToken())) {
            modifyBSAttributesRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(modifyBSAttributesRequest, HttpMethodName.PUT, PREFIX,
                modifyBSAttributesRequest.getBlbId(), "backendserver");
        internalRequest.addParameter(BSAction.update.name(), null);
        internalRequest.addParameter(CLIENT_TOKEN_IDENTIFY, modifyBSAttributesRequest.getClientToken());
        fillPayload(internalRequest, modifyBSAttributesRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the specified backend server from the specified blb.
     *
     * @param blbId             The id of the blb.
     * @param backendServerList The id list of the backend server to deleting.
     */
    public void deleteBackendServers(String blbId, List<String> backendServerList) {
        deleteBackendServers(new DeleteBSRequest(blbId, backendServerList));
    }

    /**
     * Delete the specified backend server from the specified blb.
     *
     * @param deleteBSRequest The request containing all options for deleting backend server from the specified blb.
     */
    public void deleteBackendServers(DeleteBSRequest deleteBSRequest) {
        checkNotNull(deleteBSRequest, "request should not be null.");
        if (Strings.isNullOrEmpty(deleteBSRequest.getClientToken())) {
            deleteBSRequest.setClientToken(this.generateClientToken());
        }
        InternalRequest internalRequest = this.createRequest(deleteBSRequest, HttpMethodName.PUT, PREFIX,
                deleteBSRequest.getBlbId(), "backendserver");
        fillPayload(internalRequest, deleteBSRequest);
        invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

}

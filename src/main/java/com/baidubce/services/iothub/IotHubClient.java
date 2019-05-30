/*
 * Copyright 2016 Baidu, Inc.
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
package com.baidubce.services.iothub;

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
import com.baidubce.services.iothub.model.AccountMqttUsageRequest;
import com.baidubce.services.iothub.model.AttachPrincipalToPolicyRequest;
import com.baidubce.services.iothub.model.AttachThingToPrincipalRequest;
import com.baidubce.services.iothub.model.BaseRequest;
import com.baidubce.services.iothub.model.BaseResponse;
import com.baidubce.services.iothub.model.BatchGetMqttClientStatusRequest;
import com.baidubce.services.iothub.model.BatchGetMqttClientStatusResponse;
import com.baidubce.services.iothub.model.CreatePrincipalWithCertResponse;
import com.baidubce.services.iothub.model.MqttClientStatusRequest;
import com.baidubce.services.iothub.model.MqttClientStatusResponse;
import com.baidubce.services.iothub.model.CreatePermissionRequest;
import com.baidubce.services.iothub.model.CreatePrincipalResponse;
import com.baidubce.services.iothub.model.DeleteThingRequest;
import com.baidubce.services.iothub.model.ListEndpointsRequest;
import com.baidubce.services.iothub.model.ListPermissionResponse;
import com.baidubce.services.iothub.model.ListPolicyRequest;
import com.baidubce.services.iothub.model.ListPrincipalsRequest;
import com.baidubce.services.iothub.model.ListResponse;
import com.baidubce.services.iothub.model.MqttUsageResponse;
import com.baidubce.services.iothub.model.Operation;
import com.baidubce.services.iothub.model.QueryEndpointDailyMqttUsageRequest;
import com.baidubce.services.iothub.model.QueryEndpointDailyMqttUsageResponse;
import com.baidubce.services.iothub.model.QueryEndpointRequest;
import com.baidubce.services.iothub.model.QueryEndpointResponse;
import com.baidubce.services.iothub.model.QueryPermissionRequest;
import com.baidubce.services.iothub.model.QueryPermissionResponse;
import com.baidubce.services.iothub.model.QueryPolicyRequest;
import com.baidubce.services.iothub.model.QueryPolicyResponse;
import com.baidubce.services.iothub.model.QueryPrincipalRequest;
import com.baidubce.services.iothub.model.QueryPrincipalResponse;
import com.baidubce.services.iothub.model.QueryThingRequest;
import com.baidubce.services.iothub.model.QueryThingResponse;
import com.baidubce.services.iothub.model.RegeneratePasswordRequest;
import com.baidubce.services.iothub.model.RenewCertificateRequest;
import com.baidubce.services.iothub.model.RenewCertificateResponse;
import com.baidubce.services.iothub.model.UpdatePermissionRequest;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import org.joda.time.format.ISODateTimeFormat;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Provides the client for accessing the Iothub(Internet of Things hub).
 */
public class IotHubClient extends AbstractBceClient {

    private static final String ENDPOINT_HOST = "iot.gz.baidubce.com";
    private static final String VERSION = "v1";
    private static final String ENDPOINT = "endpoint";
    private static final String CERT = "cert";
    private static final String RENEW = "renew-certificate";
    private static final String THING = "thing";
    private static final String PRINCIPAL = "principal";
    private static final String POLICY = "policy";
    private static final String PERMISSION = "permission";
    private static final String ACTION = "action";
    private static final String ATTACHTHINGPRINCIPAL = "attach-thing-principal";
    private static final String REMOVETHINGPRINCIPAL = "remove-thing-principal";
    private static final String ATTACHPRINCIPALPOLICY = "attach-principal-policy";
    private static final String REMOVEPRINCIPALPOLICY = "remove-principal-policy";
    private static final String CLIENT = "client";
    private static final String BATCH_CLIENT = "batch-client";
    private static final String STATUS = "status";
    private static final String USAGE = "usage";
    private static final String USAGE_QUERY = "usage-query";
    private static final String START = "start";
    private static final String END = "end";

    private static final String[] HEADERS_TO_SIGN = { Headers.HOST, Headers.BCE_DATE };
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String GZIP = "gzip";
    private static final String UTF8 = "UTF-8";
    /**
     * Responsible for handling HttpResponse from all Iothub service calls.
     */
    private static final HttpResponseHandler[] IOTHUB_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler() };

    public IotHubClient(BceClientConfiguration config) {

        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT_HOST) : config,
                IOTHUB_HANDLERS);
    }

    public ListResponse listEndpoints() {
        return listEndpoints(new ListEndpointsRequest(), null, null, null, null, null);
    }
    public ListResponse listEndpoints(String order,
                                      String orderBy,
                                      String pageNo,
                                      String pageSize,
                                      String q) {
        return listEndpoints(new ListEndpointsRequest(), order, orderBy, pageNo, pageSize, q);
    }
    public ListResponse listEndpoints(ListEndpointsRequest listEndpointsRequest,
                                      String order,
                                      String orderBy,
                                      String pageNo,
                                      String pageSize,
                                      String q) {
        InternalRequest internalRequest = createRequest(listEndpointsRequest, HttpMethodName.GET, ENDPOINT);
        orderAndPagination(internalRequest, order, orderBy, pageNo, pageSize, q);
        fillInHeadAndBody(listEndpointsRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, ListResponse.class);
    }

    public QueryEndpointResponse queryEndpoint(String endpointName) {
        checkNotNull(endpointName, "endpointName should not be null");
        return queryEndpoint(new QueryEndpointRequest().withEndpointName(endpointName));
    }
    public QueryEndpointResponse queryEndpoint(QueryEndpointRequest queryEndpointRequest) {
        checkNotNull(queryEndpointRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(queryEndpointRequest,
                                                        HttpMethodName.GET,
                                                        ENDPOINT,
                                                        queryEndpointRequest.getEndpointName());
        fillInHeadAndBody(queryEndpointRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, QueryEndpointResponse.class);
    }

    public QueryEndpointResponse createEndpoint(String endpointName) {
        checkNotNull(endpointName, "endpointName should not be null");
        return createEndpoint(new BaseRequest().withEndpointName(endpointName));
    }
    public QueryEndpointResponse createEndpoint(BaseRequest createEndpointRequest) {
        checkNotNull(createEndpointRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(createEndpointRequest, HttpMethodName.POST, ENDPOINT);
        fillInHeadAndBody(createEndpointRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, QueryEndpointResponse.class);
    }

    public BaseResponse deleteEndpoint(String endpointName) {
        checkNotNull(endpointName, "endpointName should not be null");
        return deleteEndpoint(new BaseRequest().withEndpointName(endpointName));
    }
    public BaseResponse deleteEndpoint(BaseRequest deleteEndpointRequest) {
        checkNotNull(deleteEndpointRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(deleteEndpointRequest,
                                                        HttpMethodName.DELETE,
                                                        ENDPOINT,
                                                        deleteEndpointRequest.getEndpointName());
        fillInHeadAndBody(deleteEndpointRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    public ListResponse listThings(String endpointName) {
        checkNotNull(endpointName, "endpointName should not be null");
        return listThings(new BaseRequest().withEndpointName(endpointName), null, null, null, null, null);
    }
    public  ListResponse listThings(String endpointName,
                                    String order,
                                    String orderBy,
                                    String pageNo,
                                    String pageSize,
                                    String q) {
        checkNotNull(endpointName, "endpointName should not be null");
        return listThings(new BaseRequest().withEndpointName(endpointName), order, orderBy, pageNo, pageSize, q);
    }
    public ListResponse listThings(BaseRequest listThingsRequest,
                                   String order,
                                   String orderBy,
                                   String pageNo,
                                   String pageSize,
                                   String q) {
        checkNotNull(listThingsRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(listThingsRequest,
                HttpMethodName.GET,
                ENDPOINT,
                listThingsRequest.getEndpointName(),
                THING);
        orderAndPagination(internalRequest, order, orderBy, pageNo, pageSize, q);
        fillInHeadAndBody(listThingsRequest, internalRequest);

        return this.invokeHttpClient(internalRequest, ListResponse.class);
    }

    public QueryThingResponse queryThing(String endpointName, String thingName) {
        checkNotNull(endpointName, "endpointName should not be null");
        return queryThing(new QueryThingRequest().withEndpointName(endpointName).withThingName(thingName));
    }
    public QueryThingResponse queryThing(QueryThingRequest queryThingRequest) {
        checkNotNull(queryThingRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(queryThingRequest,
                HttpMethodName.GET,
                ENDPOINT,
                queryThingRequest.getEndpointName(),
                THING,
                queryThingRequest.getThingName());
        fillInHeadAndBody(queryThingRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, QueryThingResponse.class);
    }

    public QueryThingResponse createThing(String endpointName, String thingName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(thingName, "thingName should not be null");
        return createThing(new QueryThingRequest().withEndpointName(endpointName).withThingName(thingName));
    }
    public QueryThingResponse createThing(QueryThingRequest createThingRequest) {
        checkNotNull(createThingRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(createThingRequest,
                HttpMethodName.POST,
                ENDPOINT,
                createThingRequest.getEndpointName(),
                THING);
        fillInHeadAndBody(createThingRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, QueryThingResponse.class);
    }

    public BaseResponse deleteThing(String endpointName, String thingName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(thingName, "thingName should not be null");
        return deleteThing(new DeleteThingRequest().withEndpointName(endpointName).withThingName(thingName));
    }
    public BaseResponse deleteThing(DeleteThingRequest deleteThingRequest) {
        checkNotNull(deleteThingRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(deleteThingRequest,
                HttpMethodName.DELETE,
                ENDPOINT,
                deleteThingRequest.getEndpointName(),
                THING,
                deleteThingRequest.getThingName());
        fillInHeadAndBody(deleteThingRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    public ListResponse listPrincipals(String endpointName) {
        checkNotNull(endpointName, "endpointName should not be null");
        return listPrincipals(new ListPrincipalsRequest()
                .withEndpointName(endpointName), null, null, null, null, null);
    }
    public ListResponse listPrincipals(String endpointName, String thingName) {
        checkNotNull(endpointName, "endpointName should not be null");
        return listPrincipals(new ListPrincipalsRequest()
                .withEndpointName(endpointName)
                .withThingName(thingName), null, null, null, null, null);
    }
    public  ListResponse listPrincipals(String endpointName, String thingName,
                                        String order,
                                        String orderBy,
                                        String pageNo,
                                        String pageSize,
                                        String q) {
        checkNotNull(endpointName, "endpointName should not be null");
        return listPrincipals(new ListPrincipalsRequest()
                .withEndpointName(endpointName)
                .withThingName(thingName), order, orderBy, pageNo, pageSize, q);
    }
    public ListResponse listPrincipals(ListPrincipalsRequest listPrincipalsRequest,
                                       String order,
                                       String orderBy,
                                       String pageNo,
                                       String pageSize,
                                       String q) {
        checkNotNull(listPrincipalsRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(listPrincipalsRequest,
                HttpMethodName.GET,
                ENDPOINT,
                listPrincipalsRequest.getEndpointName(),
                PRINCIPAL);
        orderAndPagination(internalRequest, order, orderBy, pageNo, pageSize, q);
        if (listPrincipalsRequest.getThingName() != null) {
            internalRequest.addParameter("thingName", listPrincipalsRequest.getThingName());
        }
        fillInHeadAndBody(listPrincipalsRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, ListResponse.class);
    }

    public QueryPrincipalResponse queryPrincipal(String endpointName, String principalName) {
        checkNotNull(endpointName, "endpointName should not be null");
        return queryPrincipal(new QueryPrincipalRequest()
                .withEndpointName(endpointName)
                .withPrincipalName(principalName));
    }
    public QueryPrincipalResponse queryPrincipal(QueryPrincipalRequest queryPrincipalRequest) {
        checkNotNull(queryPrincipalRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(queryPrincipalRequest,
                HttpMethodName.GET,
                ENDPOINT,
                queryPrincipalRequest.getEndpointName(),
                PRINCIPAL,
                queryPrincipalRequest.getPrincipalName());
        fillInHeadAndBody(queryPrincipalRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, QueryPrincipalResponse.class);
    }

    public CreatePrincipalResponse createPrincipal(String endpointName, String principalName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(principalName, "principalName should not be null");
        return createPrincipal(new QueryPrincipalRequest()
                .withEndpointName(endpointName)
                .withPrincipalName(principalName));
    }

    public CreatePrincipalResponse  createPrincipal(QueryPrincipalRequest createPrincipalRequest) {
        checkNotNull(createPrincipalRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(createPrincipalRequest,
                HttpMethodName.POST,
                ENDPOINT,
                createPrincipalRequest.getEndpointName(),
                PRINCIPAL);
        fillInHeadAndBody(createPrincipalRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, CreatePrincipalResponse.class);
    }

    public CreatePrincipalWithCertResponse createPrincipalWithCert(String endpointName, String principalName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(principalName, "principalName should not be null");
        return createPrincipalWithCert(new QueryPrincipalRequest()
                .withEndpointName(endpointName)
                .withPrincipalName(principalName));
    }

    public CreatePrincipalWithCertResponse  createPrincipalWithCert(QueryPrincipalRequest createPrincipalRequest) {
        checkNotNull(createPrincipalRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(createPrincipalRequest,
                HttpMethodName.POST,
                ENDPOINT,
                createPrincipalRequest.getEndpointName(),
                PRINCIPAL);
        internalRequest.addParameter("withCert", "true");
        fillInHeadAndBody(createPrincipalRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, CreatePrincipalWithCertResponse.class);
    }

    public CreatePrincipalResponse regeneratePassword(String endpointName, String principalName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(principalName, "principalName should not be null");
        return regeneratePassword(new RegeneratePasswordRequest()
                .withEndpointName(endpointName)
                .withPrincipalName(principalName));
    }
    public CreatePrincipalResponse regeneratePassword(String endpointName, String principalName, String target) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(principalName, "principalName should not be null");
        return regeneratePassword(new RegeneratePasswordRequest()
                .withEndpointName(endpointName)
                .withPrincipalName(principalName)
                .withTarget(target));
    }
    public CreatePrincipalResponse regeneratePassword(RegeneratePasswordRequest regeneratePasswordRequest) {
        checkNotNull(regeneratePasswordRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(regeneratePasswordRequest,
                HttpMethodName.POST,
                ENDPOINT,
                regeneratePasswordRequest.getEndpointName(),
                PRINCIPAL,
                regeneratePasswordRequest.getPrincipalName() );
        fillInHeadAndBody(regeneratePasswordRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, CreatePrincipalResponse.class);
    }

    public RenewCertificateResponse renewCertificate(String endpointName, String principalName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(principalName, "principalName should not be null");
        return renewCertificate(new RenewCertificateRequest()
                .withEndpointName(endpointName)
                .withPrincipalName(principalName));

    }

    public RenewCertificateResponse renewCertificate(RenewCertificateRequest renewCertificateRequest) {
        checkNotNull(renewCertificateRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(renewCertificateRequest,
                HttpMethodName.POST,
                ENDPOINT,
                renewCertificateRequest.getEndpointName(),
                PRINCIPAL,
                renewCertificateRequest.getPrincipalName(),
                CERT,
                RENEW);
        fillInHeadAndBody(renewCertificateRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, RenewCertificateResponse.class);
    }

    public BaseResponse deletePrincipal(String endpointName, String principalName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(principalName, "principalName should not be null");
        return deletePrincipal(new QueryPrincipalRequest()
                .withEndpointName(endpointName)
                .withPrincipalName(principalName));
    }
    public BaseResponse deletePrincipal(QueryPrincipalRequest deletePrincipalRequest) {
        checkNotNull(deletePrincipalRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(deletePrincipalRequest,
                HttpMethodName.DELETE,
                ENDPOINT,
                deletePrincipalRequest.getEndpointName(),
                PRINCIPAL,
                deletePrincipalRequest.getPrincipalName());
        fillInHeadAndBody(deletePrincipalRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    public ListResponse listPolicy(String endpointName) {
        checkNotNull(endpointName, "endpointName should not be null");
        return listPolicy(new ListPolicyRequest()
                .withEndpointName(endpointName), null, null, null, null, null);
    }

    public ListResponse listPolicy(String endpointName, String principalName) {
        checkNotNull(endpointName, "endpointName should not be null");
        return listPolicy(new ListPolicyRequest()
                .withEndpointName(endpointName)
                .withPrincipalName(principalName), null, null, null, null, null);
    }
    public  ListResponse listPolicy(String endpointName, String principalName,
                                    String order,
                                    String orderBy,
                                    String pageNo,
                                    String pageSize,
                                    String q) {
        checkNotNull(endpointName, "endpointName should not be null");
        return listPolicy(new ListPolicyRequest()
                .withEndpointName(endpointName)
                .withPrincipalName(principalName), order, orderBy, pageNo, pageSize, q);
    }
    public ListResponse listPolicy(ListPolicyRequest listPolicyRequest,
                                   String order,
                                   String orderBy,
                                   String pageNo,
                                   String pageSize,
                                   String q) {
        checkNotNull(listPolicyRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(listPolicyRequest,
                HttpMethodName.GET,
                ENDPOINT,
                listPolicyRequest.getEndpointName(),
                POLICY);
        orderAndPagination(internalRequest, order, orderBy, pageNo, pageSize, q);
        if (listPolicyRequest.getPrincipalName() != null) {
            internalRequest.addParameter("principalName", listPolicyRequest.getPrincipalName());
        }
        fillInHeadAndBody(listPolicyRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, ListResponse.class);
    }

    public QueryPolicyResponse queryPolicy(String endpointName, String policyName) {
        checkNotNull(endpointName, "endpointName should not be null");
        return queryPolicy(new QueryPolicyRequest()
                .withEndpointName(endpointName)
                .withPolicyName(policyName));
    }
    public QueryPolicyResponse queryPolicy(QueryPolicyRequest queryPolicyRequest) {
        checkNotNull(queryPolicyRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(queryPolicyRequest,
                HttpMethodName.GET,
                ENDPOINT,
                queryPolicyRequest.getEndpointName(),
                POLICY,
                queryPolicyRequest.getPolicyName());
        fillInHeadAndBody(queryPolicyRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, QueryPolicyResponse.class);
    }

    public QueryPolicyResponse createPolicy(String endpointName, String policyName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(policyName, "policyName should not be null");
        return createPolicy(new QueryPolicyRequest()
                .withEndpointName(endpointName)
                .withPolicyName(policyName));
    }
    public QueryPolicyResponse createPolicy(QueryPolicyRequest createPolicyRequest) {
        checkNotNull(createPolicyRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(createPolicyRequest,
                HttpMethodName.POST,
                ENDPOINT,
                createPolicyRequest.getEndpointName(),
                POLICY);
        fillInHeadAndBody(createPolicyRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, QueryPolicyResponse.class);
    }

    public BaseResponse deletePolicy(String endpointName, String policyName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(policyName, "policyName should not be null");
        return deletePolicy(new QueryPolicyRequest()
                .withEndpointName(endpointName)
                .withPolicyName(policyName));
    }
    public BaseResponse deletePolicy(QueryPolicyRequest deletePolicyRequest) {
        checkNotNull(deletePolicyRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(deletePolicyRequest,
                HttpMethodName.DELETE,
                ENDPOINT,
                deletePolicyRequest.getEndpointName(),
                POLICY,
                deletePolicyRequest.getPolicyName());
        fillInHeadAndBody(deletePolicyRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    public ListPermissionResponse listPermission(String endpointName, String policyName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(policyName, "policyName should not be null");
        return listPermission(new QueryPolicyRequest()
                .withEndpointName(endpointName)
                .withPolicyName(policyName), null, null, null, null, null);
    }
    public  ListPermissionResponse listPermission(String endpointName, String policyName,
                                        String order,
                                        String orderBy,
                                        String pageNo,
                                        String pageSize,
                                        String q) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(policyName, "policyName should not be null");
        return listPermission(new QueryPolicyRequest()
                .withEndpointName(endpointName)
                .withPolicyName(policyName), order, orderBy, pageNo, pageSize, q);
    }
    public ListPermissionResponse listPermission(QueryPolicyRequest listPermissonRequest,
                                                 String order,
                                                 String orderBy,
                                                 String pageNo,
                                                 String pageSize,
                                                 String q) {
        checkNotNull(listPermissonRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(listPermissonRequest,
                HttpMethodName.GET,
                ENDPOINT,
                listPermissonRequest.getEndpointName(),
                PERMISSION);
        orderAndPagination(internalRequest, order, orderBy, pageNo, pageSize, q);
        internalRequest.addParameter("policyName", listPermissonRequest.getPolicyName());
        fillInHeadAndBody(listPermissonRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, ListPermissionResponse.class);
    }

    public QueryPermissionResponse queryPermission(String endpointName, String permissionUuid) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(permissionUuid, "permissionUuid should not be null");
        return queryPermission(new QueryPermissionRequest()
                .withEndpointName(endpointName)
                .withPermissionUuid(permissionUuid));
    }
    public QueryPermissionResponse queryPermission(QueryPermissionRequest queryPermissionRequest) {
        checkNotNull(queryPermissionRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(queryPermissionRequest,
                HttpMethodName.GET,
                ENDPOINT,
                queryPermissionRequest.getEndpointName(),
                PERMISSION,
                queryPermissionRequest.getPermissionUuid());
        fillInHeadAndBody(queryPermissionRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, QueryPermissionResponse.class);
    }

    public QueryPermissionResponse createPermission(String endpointName,
                                                    String policyName,
                                                    List<Operation> operations,
                                                    String topic) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(policyName, "policyName should not be null");
        checkNotNull(operations, "operations should not be null");
        checkNotNull(topic, "topic should not be null");
        return createPermission(new CreatePermissionRequest()
                .withEndpointName(endpointName)
                .withPolicyName(policyName)
                .withOperations(operations)
                .withTopic(topic));
    }
    public QueryPermissionResponse createPermission(CreatePermissionRequest createPermissionRequest) {
        checkNotNull(createPermissionRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(createPermissionRequest,
                HttpMethodName.POST,
                ENDPOINT,
                createPermissionRequest.getEndpointName(),
                PERMISSION );
        fillInHeadAndBody(createPermissionRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, QueryPermissionResponse.class);
    }

    public QueryPermissionResponse updatePermission(String endpointName,
                                                    String permissionUuid,
                                                    List<Operation> operations,
                                                    String topic) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(permissionUuid, "permissionUuid should not be null");
        return updatePermission(new UpdatePermissionRequest()
                .withEndpointName(endpointName)
                .withPermissionUuid(permissionUuid)
                .withOperations(operations)
                .withTopic(topic));
    }
    public QueryPermissionResponse updatePermission(UpdatePermissionRequest updatePermissionRequest) {
        checkNotNull(updatePermissionRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(updatePermissionRequest,
                HttpMethodName.PUT,
                ENDPOINT,
                updatePermissionRequest.getEndpointName(),
                PERMISSION,
                updatePermissionRequest.getPermissionUuid());
        fillInHeadAndBody(updatePermissionRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, QueryPermissionResponse.class);
    }

    public BaseResponse deletePermission(String endpointName, String permissionUuid) {
        checkNotNull(endpointName, "endpointName should not be null");
        return deletePermission(new BaseRequest().withEndpointName(endpointName), permissionUuid);
    }
    public BaseResponse deletePermission(BaseRequest deletePolicyRequest, String permissionUuid) {
        checkNotNull(deletePolicyRequest, "request should not be null.");
        checkNotNull(permissionUuid, "permissionUuid should not be null");
        InternalRequest internalRequest = createRequest(deletePolicyRequest,
                HttpMethodName.DELETE,
                ENDPOINT,
                deletePolicyRequest.getEndpointName(),
                PERMISSION,
                permissionUuid);
        fillInHeadAndBody(deletePolicyRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    public BaseResponse attachThingToPrincipal(String endpointName, String thingName, String principaName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(thingName, "thingName should not be null");
        checkNotNull(principaName, "principaName should not be null");
        return attachThingToPrincipal(new AttachThingToPrincipalRequest()
                .withEndpointName(endpointName)
                .withThingName(thingName)
                .withPrincipalName(principaName));
    }
    public BaseResponse attachThingToPrincipal(AttachThingToPrincipalRequest attachThingToPrincipalRequest) {
        checkNotNull(attachThingToPrincipalRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(attachThingToPrincipalRequest,
                HttpMethodName.POST,
                ACTION,
                ATTACHTHINGPRINCIPAL);
        fillInHeadAndBody(attachThingToPrincipalRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    public BaseResponse removeThingToPrincipal(String endpointName, String thingName, String principaName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(thingName, "thingName should not be null");
        checkNotNull(principaName, "principaName should not be null");
        return removeThingToPrincipal(new AttachThingToPrincipalRequest()
                .withEndpointName(endpointName)
                .withThingName(thingName)
                .withPrincipalName(principaName));
    }
    public BaseResponse removeThingToPrincipal(AttachThingToPrincipalRequest attachThingToPrincipalRequest) {
        checkNotNull(attachThingToPrincipalRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(attachThingToPrincipalRequest,
                HttpMethodName.POST,
                ACTION ,
                REMOVETHINGPRINCIPAL);
        fillInHeadAndBody(attachThingToPrincipalRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    public BaseResponse attachPrincipalToPolicy(String endpointName, String principaName, String policyName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(policyName, "policyName should not be null");
        checkNotNull(principaName, "principaName should not be null");
        return attachPrincipalToPolicy(new AttachPrincipalToPolicyRequest()
                .withEndpointName(endpointName)
                .withPolicyName(policyName)
                .withPrincipalName(principaName));
    }
    public BaseResponse attachPrincipalToPolicy(AttachPrincipalToPolicyRequest attachPrincipalToPolicyRequest) {
        checkNotNull(attachPrincipalToPolicyRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(attachPrincipalToPolicyRequest,
                HttpMethodName.POST,
                ACTION,
                ATTACHPRINCIPALPOLICY);
        fillInHeadAndBody(attachPrincipalToPolicyRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    public BaseResponse removePrincipalToPolicy(String endpointName, String principaName, String policyName) {
        checkNotNull(endpointName, "endpointName should not be null");
        checkNotNull(policyName, "policyName should not be null");
        checkNotNull(principaName, "principaName should not be null");
        return removePrincipalToPolicy(new AttachPrincipalToPolicyRequest()
                .withEndpointName(endpointName)
                .withPolicyName(policyName)
                .withPrincipalName(principaName));
    }
    public BaseResponse removePrincipalToPolicy(AttachPrincipalToPolicyRequest attachPrincipalToPolicyRequest) {
        checkNotNull(attachPrincipalToPolicyRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(attachPrincipalToPolicyRequest,
                HttpMethodName.POST,
                ACTION,
                REMOVEPRINCIPALPOLICY);
        fillInHeadAndBody(attachPrincipalToPolicyRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, BaseResponse.class);
    }

    public MqttClientStatusResponse getClientStatus(MqttClientStatusRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getEndpointName(), "endpointName should not be null.");
        checkNotNull(request.getClientId(), "clientId should not be null.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, ENDPOINT,
                request.getEndpointName(), CLIENT, request.getClientId(), STATUS);
        return this.invokeHttpClient(internalRequest, MqttClientStatusResponse.class);
    }

    public BatchGetMqttClientStatusResponse batchGetClientStatus(BatchGetMqttClientStatusRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getEndpointName(), "endpointName should not be null.");
        checkNotNull(request.getClientIdList(), "clientIdList should not be null.");
        checkArgument(!request.getClientIdList().isEmpty(), "clientIdList should not be empty.");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, ENDPOINT,
                request.getEndpointName(), BATCH_CLIENT, STATUS);
        fillInHeadAndBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, BatchGetMqttClientStatusResponse.class);
    }

    public MqttUsageResponse getAccountMqttUsageOfCurrentBillingMonth(AccountMqttUsageRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, USAGE);
        return this.invokeHttpClient(internalRequest, MqttUsageResponse.class);
    }

    public MqttUsageResponse getEndpointMqttUsageOfCurrentBillingMonth(BaseRequest request) {
        checkNotNull(request.getEndpointName(), "endpointName should not be null");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, ENDPOINT,
                request.getEndpointName(), USAGE);
        return this.invokeHttpClient(internalRequest, MqttUsageResponse.class);
    }

    public QueryEndpointDailyMqttUsageResponse queryEndpointDailyMqttUsage(QueryEndpointDailyMqttUsageRequest request) {
        checkNotNull(request.getEndpointName(), "endpointName should not be null");
        checkNotNull(request.getStart(), "start should not be null");
        checkNotNull(request.getEnd(), "end should not be null");
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, ENDPOINT,
                request.getEndpointName(), USAGE_QUERY);
        internalRequest.addParameter(START, ISODateTimeFormat.date().print(request.getStart()));
        internalRequest.addParameter(END, ISODateTimeFormat.date().print(request.getEnd()));
        return this.invokeHttpClient(internalRequest, QueryEndpointDailyMqttUsageResponse.class);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        return createRequest(bceRequest, httpMethod, null, pathVariables);
    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest The original BCE request created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param signOptions The options for signature.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific parameters to send.
     */

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          SignOptions signOptions, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(VERSION);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        if (signOptions == null) {
            signOptions = new SignOptions();
            signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        }
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());

        return request;
    }

    private void fillInHeadAndBody(AbstractBceRequest bceRequest, InternalRequest request) {
        byte[] content = toJson(bceRequest);
        request.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
        request.addHeader(Headers.CONTENT_TYPE, CONTENT_TYPE);
        request.setContent(RestartableInputStream.wrap(content));
    }

    private byte[] toJson(AbstractBceRequest bceRequest) {
        String jsonStr = JsonUtils.toJsonString(bceRequest);
        try {
            return jsonStr.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
    }

    private void orderAndPagination(InternalRequest internalRequest,
                                    String order,
                                    String orderBy,
                                    String pageNo,
                                    String pageSize,
                                    String q) {
        if (order != null) {
            internalRequest.addParameter("order", order);
        }
        if (orderBy != null) {
            internalRequest.addParameter("orderBy", orderBy);
        }
        if (pageNo != null) {
            internalRequest.addParameter("pageNo", pageNo);
        }
        if (pageSize != null) {
            internalRequest.addParameter("pageSize", pageSize);
        }
        if (q != null) {
            internalRequest.addParameter("q", q);
        }

    }
}

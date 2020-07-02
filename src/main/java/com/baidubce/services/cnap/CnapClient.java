/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.cnap;

import static com.baidubce.services.cnap.model.access.CreateAccessModel.CLUSTER_NETWORK;
import static com.baidubce.services.cnap.model.access.CreateAccessModel.PUBLIC_NETWORK;
import static com.baidubce.services.cnap.model.application.ApplicationModel.DEFAULT_DEPLOY_TYPE;
import static com.baidubce.services.cnap.model.application.WorkloadType.MICROSERVICE_APPLICATION;
import static com.baidubce.services.cnap.model.application.WorkloadType.SIMPLE_APPLICATION;
import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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
import com.baidubce.services.cnap.handler.CnapHttpResponseHandler;
import com.baidubce.services.cnap.model.access.CreateAccessModel;
import com.baidubce.services.cnap.model.access.CreateAccessRequest;
import com.baidubce.services.cnap.model.access.CreateAccessResponse;
import com.baidubce.services.cnap.model.access.DeleteAccessRequest;
import com.baidubce.services.cnap.model.access.DeleteAccessResponse;
import com.baidubce.services.cnap.model.access.ListAccessRequest;
import com.baidubce.services.cnap.model.access.ListAccessResponse;
import com.baidubce.services.cnap.model.access.NewAccessModel;
import com.baidubce.services.cnap.model.application.CreateApplicationRequest;
import com.baidubce.services.cnap.model.application.CreateApplicationResponse;
import com.baidubce.services.cnap.model.application.DeleteApplicationRequest;
import com.baidubce.services.cnap.model.application.DeleteApplicationResponse;
import com.baidubce.services.cnap.model.application.ListApplicationRequest;
import com.baidubce.services.cnap.model.application.ListApplicationResponse;
import com.baidubce.services.cnap.model.cluster.BindClusterToWorkspaceRequest;
import com.baidubce.services.cnap.model.cluster.BindClusterToWorkspaceResponse;
import com.baidubce.services.cnap.model.cluster.ImportClusterRequest;
import com.baidubce.services.cnap.model.cluster.ImportClusterResponse;
import com.baidubce.services.cnap.model.cluster.ReleaseClusterRequest;
import com.baidubce.services.cnap.model.cluster.ReleaseClusterResponse;
import com.baidubce.services.cnap.model.cluster.UnbindClusterToWorkspaceRequest;
import com.baidubce.services.cnap.model.cluster.UnbindClusterToWorkspaceResponse;
import com.baidubce.services.cnap.model.deploygroup.ContainerModel;
import com.baidubce.services.cnap.model.deploygroup.CreateDeployGroupRequest;
import com.baidubce.services.cnap.model.deploygroup.CreateDeployGroupResponse;
import com.baidubce.services.cnap.model.deploygroup.DeleteDeployGroupRequest;
import com.baidubce.services.cnap.model.deploygroup.DeleteDeployGroupResponse;
import com.baidubce.services.cnap.model.deploygroup.DeployGroupType;
import com.baidubce.services.cnap.model.deploygroup.GetDeployGroupRequest;
import com.baidubce.services.cnap.model.deploygroup.GetDeployGroupResponse;
import com.baidubce.services.cnap.model.deploygroup.ListDeployGroupByImageRequest;
import com.baidubce.services.cnap.model.deploygroup.ListDeployGroupByImageResponse;
import com.baidubce.services.cnap.model.deploygroup.ListDeployGroupByPageRequest;
import com.baidubce.services.cnap.model.deploygroup.ListDeployGroupByPageResponse;
import com.baidubce.services.cnap.model.deploygroup.NewDeployGroupModel;
import com.baidubce.services.cnap.model.deploygroup.ScaleDeployGroupRequest;
import com.baidubce.services.cnap.model.deploygroup.ScaleDeployGroupResponse;
import com.baidubce.services.cnap.model.deploygroup.UpdateDeployGroupRequest;
import com.baidubce.services.cnap.model.deploygroup.UpdateDeployGroupResponse;
import com.baidubce.services.cnap.model.environment.BindClusterToEnvironmentRequest;
import com.baidubce.services.cnap.model.environment.BindClusterToEnvironmentResponse;
import com.baidubce.services.cnap.model.environment.CreateEnvironmentRequest;
import com.baidubce.services.cnap.model.environment.CreateEnvironmentResponse;
import com.baidubce.services.cnap.model.environment.DeleteEnvironmentReponse;
import com.baidubce.services.cnap.model.environment.DeleteEnvironmentRequest;
import com.baidubce.services.cnap.model.environment.ListEnvironmentRequest;
import com.baidubce.services.cnap.model.environment.ListEnvironmentResponse;
import com.baidubce.services.cnap.model.environment.UnbindClusterToEnvironmentRequest;
import com.baidubce.services.cnap.model.environment.UnbindClusterToEnvironmentResponse;
import com.baidubce.services.cnap.model.environment.UpdateEnvironmentRequest;
import com.baidubce.services.cnap.model.environment.UpdateEnvironmentResponse;
import com.baidubce.services.cnap.model.monitoring.CreateAlertRulesRequest;
import com.baidubce.services.cnap.model.monitoring.CreateAlertRulesResponse;
import com.baidubce.services.cnap.model.monitoring.DeleteAlertRulesRequest;
import com.baidubce.services.cnap.model.monitoring.DeleteAlertRulesResponse;
import com.baidubce.services.cnap.model.monitoring.GetMonitorDataRequest;
import com.baidubce.services.cnap.model.monitoring.GetMonitorDataResponse;
import com.baidubce.services.cnap.model.monitoring.ListAlertRecordResponse;
import com.baidubce.services.cnap.model.monitoring.ListAlertRulesRequest;
import com.baidubce.services.cnap.model.monitoring.ListAlertRulesResponse;
import com.baidubce.services.cnap.model.monitoring.ListAlertRecordRequest;
import com.baidubce.services.cnap.model.monitoring.UpdateAlertRulesRequest;
import com.baidubce.services.cnap.model.monitoring.UpdateAlertRulesResponse;
import com.baidubce.services.cnap.model.releaserecord.CreateReleaseRecordRequest;
import com.baidubce.services.cnap.model.releaserecord.CreateReleaseRecordResponse;
import com.baidubce.services.cnap.model.releaserecord.GetReleaseRecordProgressRequest;
import com.baidubce.services.cnap.model.releaserecord.GetReleaseRecordProgressResponse;
import com.baidubce.services.cnap.model.releaserecord.ListReleaseRecordRequest;
import com.baidubce.services.cnap.model.releaserecord.ListReleaseRecordResponse;
import com.baidubce.services.cnap.model.releaserecord.RollbackReleaseRecordRequest;
import com.baidubce.services.cnap.model.releaserecord.RollbackReleaseRecordResponse;
import com.baidubce.services.cnap.model.releaserecord.TaskReqModel;
import com.baidubce.services.cnap.model.repository.CreateRepositoryRequest;
import com.baidubce.services.cnap.model.repository.CreateRepositoryResponse;
import com.baidubce.services.cnap.model.repository.ListImageRequest;
import com.baidubce.services.cnap.model.repository.ListImageResponse;
import com.baidubce.services.cnap.model.workspace.CreateWorkspaceRequest;
import com.baidubce.services.cnap.model.workspace.CreateWorkspaceResponse;
import com.baidubce.services.cnap.model.workspace.DeleteWorkspaceRequest;
import com.baidubce.services.cnap.model.workspace.DeleteWorkspaceResponse;
import com.baidubce.services.cnap.model.workspace.GetWorkspaceRequest;
import com.baidubce.services.cnap.model.workspace.GetWorkspaceResponse;
import com.baidubce.services.cnap.model.workspace.ListWorkspaceRequest;
import com.baidubce.services.cnap.model.workspace.ListWorkspaceResponse;
import com.baidubce.services.cnap.model.workspace.UpdateWorkspaceRequest;
import com.baidubce.services.cnap.model.workspace.UpdateWorkspaceResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

/**
 * Provides the client for accessing the Baidu Cloud-Native Application Platform Service(cnap).
 */
public class CnapClient extends AbstractBceClient {
    private static final String VERSION = "v2";

    private static final String CONSOLE_PREFIX = "console";

    private static final String WORKSPACE_PREFIX = "workspaces";
    private static final String APPLICATION_PREFIX = "applications";
    private static final String DEPLOY_GROUP_PREFIX = "deployGroups";
    private static final String ENVIRONMENT_PREFIX = "environments";
    private static final String CLUSTER_PREFIX = "clusters";
    private static final String REPOSITORY_PREFIX = "repositories";
    private static final String IMAGE_PREFIX = "images";
    private static final String ACCESS_PREFIX = "accesses";
    private static final String RELEASE_RECORD_PREFIX = "releaseRecords";
    private static final String MONITORING_PREFIX = "monitoring";

    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String REQUEST_WORKSPACE_INFO_NULL_ERROR_MESSAGE
            = "request workspace info should not be null.";
    private static final String REQUEST_CLUSTER_INFO_NULL_ERROR_MESSAGE = "request cluster info should not be null.";
    private static final String WORKSPACE_ID_MESSAGE_KEY = "workspace id";
    private static final String WORKSPACE_NAME_MESSAGE_KEY = "workspace name";
    private static final String WORKSPACE_DESCRIPTION_MESSAGE_KEY = "workspace description";

    private static final String ENVIRONMENT_ID_MESSAGE_KEY = "environment id";
    private static final String ENVIRONMENT_NAME_MESSAGE_KEY = "environment name";

    private static final String CLUSTER_ID_MESSAGE_KEY = "cluster id";
    private static final String CLUSTER_IDS_MESSAGE_KEY = "cluster ids";
    private static final String CLUSTER_REGION_MESSAGE_KEY = "cluster region";
    private static final String CLUSTER_TYPE_MESSAGE_KEY = "cluster type";
    private static final String CLUSTER_UNDERLAY_CLUSTER_ID_MESSAGE_KEY = "underlay cluster id";

    private static final String REPOSITORY_ID_MESSAGE_KEY = "repository id";
    private static final String REPOSITORY_NAME_MESSAGE_KEY = "repository name";
    private static final String REPOSITORY_TYPE_MESSAGE_KEY = "repository type";


    private static final String DEPLOY_GROUP_ID_MESSAGE_KEY = "deploy group id";
    private static final String DEPLOY_GROUP_NAME_MESSAGE_KEY = "deploy group name";

    private static final String APPLICATION_NAME_MESSAGE_KEY = "application name";
    private static final String APPLICATION_ID_MESSAGE_KEY = "application id";

    private static final String IMAGE_NAME_MESSAGE_KEY = "image name";
    private static final String IMAGE_PATH_MESSAGE_KEY = "image path";

    private static final String RELEASE_RECORD_ID_MESSAGE_KEY = "release record id";

    private static final String ACCESS_ID_MESSAGE_KEY = "deploy group id";
    private static final String ACCESS_NAME_MESSAGE_KEY = "access name";

    private static final int MIN_PORT = 1;

    private static final int MAX_PORT = 65535;

    private static final int MAX_DESCRIPTION_LENGTH = 140;

    private static final int ILLEGAL_INITIAL = -1;


    /**
     * Responsible for handling httpResponses from all cnap service calls.
     */
    private static final HttpResponseHandler[] cnap_handlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new CnapHttpResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on bcc.
     */
    public CnapClient() {
        this(new CnapClientConfiguration());
    }

    /**
     * Constructs a new cnap client using the client configuration to access cnap.
     * @param clientConfiguration The cnap client configuration options controlling how this client
     *                            connects to cnap.
     */
    public CnapClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, cnap_handlers);
    }


    /**
     * Creates and initializes a new request object for the specified bcc resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest The original request, as created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
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
     * Creates and initializes a new request object for the specified bcc resource. This method is responsible
     * for determining the right way to address resources.
     *
     * @param bceRequest The original request, as created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     */
    private InternalRequest createRequestWithCustomPath(AbstractBceRequest bceRequest, HttpMethodName httpMethod,
                                          String... pathVariables) {
        List<String> path = new ArrayList<String>();

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
     * Create workspace.
     * @param request CreateWorkspaceRequest
     * @return CreateWorkspaceResponse
     */
    public CreateWorkspaceResponse createWorkspace(CreateWorkspaceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getWorkspace(), REQUEST_WORKSPACE_INFO_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspace().getName(),
                checkEmptyExceptionMessageFormat(WORKSPACE_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, WORKSPACE_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateWorkspaceResponse.class);
    }

    /**
     * Query detail information of workspace.
     *
     * @param request GetWorkspaceRequest
     * @return GetWorkspaceResponse
     */
    public GetWorkspaceResponse getWorkspace(GetWorkspaceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, WORKSPACE_PREFIX,
                request.getWorkspaceID());
        return invokeHttpClient(internalRequest, GetWorkspaceResponse.class);
    }

    /**
     * Update information of workspace.
     *
     * @param request GetWorkspaceRequest
     * @return GetWorkspaceResponse
     */
    public UpdateWorkspaceResponse updateWorkspace(UpdateWorkspaceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDescription(),
                checkEmptyExceptionMessageFormat(WORKSPACE_DESCRIPTION_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, WORKSPACE_PREFIX,
                request.getWorkspaceID());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateWorkspaceResponse.class);
    }

    /**
     * Delete workspace
     * @param request DeleteWorkspaceRequest
     * @return DeleteWorkspaceResponse
     */
    public DeleteWorkspaceResponse deleteWorkspace(DeleteWorkspaceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, WORKSPACE_PREFIX,
                request.getWorkspaceID());
        internalRequest.addParameter("skipDeleteUnderlay", String.valueOf(request.getSkipDeleteUnderlay()));
        internalRequest.addParameter("ignoreUnderlayError", String.valueOf(request.getIgnoreUnderlayError()));
        return invokeHttpClient(internalRequest, DeleteWorkspaceResponse.class);
    }


    /**
     * List workspace
     * @param request ListWorkspaceRequest
     * @return ListWorkspaceResponse
     */
    public ListWorkspaceResponse listWorkspace(ListWorkspaceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, WORKSPACE_PREFIX);
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (StringUtils.isNotEmpty(request.getPageSize())) {
            internalRequest.addParameter("pageSize", request.getPageSize());
        }
        if (StringUtils.isNotEmpty(request.getPageNo())) {
            internalRequest.addParameter("pageNo", request.getPageNo());
        }
        if (StringUtils.isNotEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (StringUtils.isNotEmpty(request.getResourceID())) {
            internalRequest.addParameter("resourceID", request.getResourceID());
        }
        return invokeHttpClient(internalRequest, ListWorkspaceResponse.class);
    }

    /**
     * Create Environment.
     * @param request CreateEnvironmentRequest
     * @return CreateEnvironmentResponse
     */
    public CreateEnvironmentResponse createEnvironment(CreateEnvironmentRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(ENVIRONMENT_NAME_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, WORKSPACE_PREFIX,
                request.getWorkspaceID(), ENVIRONMENT_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateEnvironmentResponse.class);
    }

    /**
     * Update environment.
     * @param request UpdateEnvironmentRequest
     * @return UpdateEnvironmentResponse
     */
    public UpdateEnvironmentResponse updateEnvironment(UpdateEnvironmentRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getEnvironmentID(), checkEmptyExceptionMessageFormat(ENVIRONMENT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, WORKSPACE_PREFIX,
                request.getWorkspaceID(), ENVIRONMENT_PREFIX, request.getEnvironmentID());
        internalRequest.addParameter("skipDeleteUnderlay", String.valueOf(request.getSkipDeleteUnderlay()));
        internalRequest.addParameter("ignoreUnderlayError", String.valueOf(request.getIgnoreUnderlayError()));
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateEnvironmentResponse.class);
    }

    /**
     * List Environment.
     * @param request ListEnvironmentRequest
     * @return ListEnvironmentResponse
     */
    public ListEnvironmentResponse listEnvironment(ListEnvironmentRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CONSOLE_PREFIX,
                WORKSPACE_PREFIX, request.getWorkspaceID(), ENVIRONMENT_PREFIX);
        internalRequest.addParameter("orderBy", request.getOrderBy());
        internalRequest.addParameter("order", request.getOrder());
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        if (!StringUtils.isEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        return invokeHttpClient(internalRequest, ListEnvironmentResponse.class);
    }

    /**
     * Delete Environment.
     * @param request DeleteEnvironmentRequest
     * @return DeleteEnvironmentReponse
     */
    public DeleteEnvironmentReponse deleteEnvironment(DeleteEnvironmentRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getEnvironmentID(), checkEmptyExceptionMessageFormat(ENVIRONMENT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, WORKSPACE_PREFIX,
                request.getWorkspaceID(), ENVIRONMENT_PREFIX, request.getEnvironmentID());
        internalRequest.addParameter("skipDeleteUnderlay", String.valueOf(request.getSkipDeleteUnderlay()));
        internalRequest.addParameter("ignoreUnderlayError", String.valueOf(request.getIgnoreUnderlayError()));
        return invokeHttpClient(internalRequest, DeleteEnvironmentReponse.class);
    }

    /**
     * Bind cluster to environment.
     * @param request BindClusterToWorkspaceRequest
     * @return BindClusterToEnvironmentResponse
     */
    public BindClusterToEnvironmentResponse bindClusterToEnvironment(BindClusterToEnvironmentRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getEnvironmentID(), checkEmptyExceptionMessageFormat(ENVIRONMENT_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getClusterID(), checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, WORKSPACE_PREFIX,
                request.getWorkspaceID(), ENVIRONMENT_PREFIX, request.getEnvironmentID());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BindClusterToEnvironmentResponse.class);
    }

    /**
     * Unbind cluster to environment.
     * @param request BindClusterToWorkspaceRequest
     * @return BindClusterToEnvironmentResponse
     */
    public UnbindClusterToEnvironmentResponse unBindClusterToEnvironment(UnbindClusterToEnvironmentRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getEnvironmentID(), checkEmptyExceptionMessageFormat(ENVIRONMENT_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT, WORKSPACE_PREFIX,
                request.getWorkspaceID(), ENVIRONMENT_PREFIX, request.getEnvironmentID());
        internalRequest.addParameter("skipDeleteUnderlay", String.valueOf(request.getSkipDeleteUnderlay()));
        internalRequest.addParameter("ignoreUnderlayError", String.valueOf(request.getIgnoreUnderlayError()));
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UnbindClusterToEnvironmentResponse.class);
    }

    /**
     * Import cluster.
     * @param request ImportClusterRequest
     * @return ImportClusterResponse
     */
    public ImportClusterResponse importCluster(ImportClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request, REQUEST_CLUSTER_INFO_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getCluster().getRegion(),
                checkEmptyExceptionMessageFormat(CLUSTER_REGION_MESSAGE_KEY));
        checkStringNotEmpty(request.getCluster().getUnderlayClusterID(),
                checkEmptyExceptionMessageFormat(CLUSTER_UNDERLAY_CLUSTER_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getCluster().getType(),
                checkEmptyExceptionMessageFormat(CLUSTER_TYPE_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CLUSTER_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ImportClusterResponse.class);
    }

    /**
     * Release cluster.
     * @param request ReleaseClusterRequest
     * @return ReleaseClusterResponse
     */
    public ReleaseClusterResponse releaseCluster(ReleaseClusterRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getClusterID(),
                checkEmptyExceptionMessageFormat(CLUSTER_UNDERLAY_CLUSTER_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, CLUSTER_PREFIX,
                request.getClusterID());
        internalRequest.addParameter("skipDeleteUnderlay", String.valueOf(request.getSkipDeleteUnderlay()));
        internalRequest.addParameter("ignoreUnderlayError", String.valueOf(request.getIgnoreUnderlayError()));
        return invokeHttpClient(internalRequest, ReleaseClusterResponse.class);
    }

    /**
     * Bind Cluster to workspace.
     * @param request BindClusterToWorkspaceRequest
     * @return BindClusterToWorkspaceResponse
     */
    public BindClusterToWorkspaceResponse bindClusterToWorkspace(BindClusterToWorkspaceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkArgument(request.getClusterIDs().size() > 0,
                checkEmptyExceptionMessageFormat(CLUSTER_IDS_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, WORKSPACE_PREFIX,
                request.getWorkspaceID(), CLUSTER_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, BindClusterToWorkspaceResponse.class);
    }

    /**
     * Unbind Cluster to workspace.
     * @param request BindClusterRequest
     * @return BindClusterResponse
     */
    public UnbindClusterToWorkspaceResponse unbindClusterToWorkspace(UnbindClusterToWorkspaceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(),
                checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getClusterID(),
                checkEmptyExceptionMessageFormat(CLUSTER_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, WORKSPACE_PREFIX,
                request.getWorkspaceID(), CLUSTER_PREFIX, request.getClusterID());
        internalRequest.addParameter("skipDeleteUnderlay", String.valueOf(request.getSkipDeleteUnderlay()));
        internalRequest.addParameter("ignoreUnderlayError", String.valueOf(request.getIgnoreUnderlayError()));
        return invokeHttpClient(internalRequest, UnbindClusterToWorkspaceResponse.class);
    }

    /**
     * Create Repository.
     * @param request CreateRepositoryRequest
     * @return CreateRepositoryResponse
     */
    public CreateRepositoryResponse createRepository(CreateRepositoryRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat(REPOSITORY_NAME_MESSAGE_KEY));
        checkArgument(request.getType() > 0 && request.getType() < 4,
                checkEmptyExceptionMessageFormat(REPOSITORY_TYPE_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, WORKSPACE_PREFIX,
                request.getWorkspaceID(), REPOSITORY_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateRepositoryResponse.class);
    }

    /**
     * List image.
     * @param request ListImageRequest
     * @return ListImageResponse
     */
    public ListImageResponse listImage(ListImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getRepositoryID(), checkEmptyExceptionMessageFormat(REPOSITORY_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, WORKSPACE_PREFIX,
                request.getWorkspaceID(), REPOSITORY_PREFIX, request.getRepositoryID(), IMAGE_PREFIX);
        internalRequest.addParameter("orderBy", request.getOrderBy());
        internalRequest.addParameter("order", request.getOrder());
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        if (!StringUtils.isEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        return invokeHttpClient(internalRequest, ListImageResponse.class);
    }

    /**
     * Create access.
     * @param request CreateAccessRequest
     * @return CreateAccessResponse
     */
    public CreateAccessResponse createAccess(CreateAccessRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getAccessList(), "create access model list should not be null.");
        String workspaceID = "";
        String applicationID = "";
        String deployGroupID = "";
        for (CreateAccessModel accessModel : request.getAccessList()) {
            checkStringNotEmpty(accessModel.getWorkspaceID(),
                    checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
            checkStringNotEmpty(accessModel.getApplicationID(),
                    checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
            checkStringNotEmpty(accessModel.getDeployGroupID(),
                    checkEmptyExceptionMessageFormat(DEPLOY_GROUP_ID_MESSAGE_KEY));
            checkStringNotEmpty(accessModel.getName(), checkEmptyExceptionMessageFormat(ACCESS_NAME_MESSAGE_KEY));
            checkArgument(accessModel.getType() >= CLUSTER_NETWORK && accessModel.getType() <= PUBLIC_NETWORK,
                    "type should between 1 and 3.");
            checkArgument("TCP".equals(accessModel.getProtocol()) ||
                    "UDP".equals(accessModel.getProtocol()), "protocol should be TCP or UDP.");
            checkArgument(accessModel.getPort() >= MIN_PORT && accessModel.getPort() <= MAX_PORT,
                    "port should between 1 and 65535.");
            checkArgument(accessModel.getTargetPort() >= MIN_PORT && accessModel.getPort() <= MAX_PORT,
                    "target port should between 1 and 65535.");

            checkArgument(StringUtils.isEmpty(workspaceID) || (StringUtils.isNotEmpty(workspaceID)
                            && !workspaceID.equals(accessModel.getWorkspaceID())),
                    "workspace id should be equal.");
            checkArgument(StringUtils.isEmpty(applicationID) || (StringUtils.isNotEmpty(applicationID)
                            && !applicationID.equals(accessModel.getApplicationID())),
                    "application id should be equal.");
            checkArgument(StringUtils.isEmpty(deployGroupID) || (StringUtils.isNotEmpty(deployGroupID)
                            && !deployGroupID.equals(accessModel.getDeployGroupID())),
                    "id should be equal.");
            workspaceID = accessModel.getWorkspaceID();
            applicationID = accessModel.getApplicationID();
            deployGroupID = accessModel.getDeployGroupID();
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, WORKSPACE_PREFIX,
                workspaceID, APPLICATION_PREFIX, applicationID, DEPLOY_GROUP_PREFIX, deployGroupID, ACCESS_PREFIX);
        String strJson = JsonUtils.toJsonString(request.getAccessList());
        byte[] requestJson = null;
        try {
            requestJson = strJson.getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Unsupported encode.", e);
        }
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
        internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        return invokeHttpClient(internalRequest, CreateAccessResponse.class);
    }

    /**
     * Delete access.
     * @param request DeleteAccessRequest
     * @return DeleteAccessResponse
     */
    public DeleteAccessResponse deleteAccess(DeleteAccessRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDeployGroupID(), checkEmptyExceptionMessageFormat(DEPLOY_GROUP_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAccessID(), checkEmptyExceptionMessageFormat(ACCESS_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE, WORKSPACE_PREFIX,
                request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID(), DEPLOY_GROUP_PREFIX,
                request.getDeployGroupID(), ACCESS_PREFIX, request.getAccessID());
        return invokeHttpClient(internalRequest, DeleteAccessResponse.class);
    }

    /**
     * List access.
     * @param request ListAccessRequest
     * @return ListAccessResponse
     */
    public ListAccessResponse listAccess(ListAccessRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDeployGroupID(), checkEmptyExceptionMessageFormat(DEPLOY_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CONSOLE_PREFIX,
                WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID(),
                DEPLOY_GROUP_PREFIX, request.getDeployGroupID(), ACCESS_PREFIX);
        internalRequest.addParameter("orderBy", request.getOrderBy());
        internalRequest.addParameter("order", request.getOrder());
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        return invokeHttpClient(internalRequest, ListAccessResponse.class);
    }

    /**
     * List application.
     * @param request ListApplicationRequest
     * @return ListApplicationResponse
     */
    public ListApplicationResponse listApplication(ListApplicationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, WORKSPACE_PREFIX,
                request.getWorkspaceID(), APPLICATION_PREFIX);
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (StringUtils.isNotEmpty(request.getPageSize())) {
            internalRequest.addParameter("pageSize", request.getPageSize());
        }
        if (StringUtils.isNotEmpty(request.getPageNo())) {
            internalRequest.addParameter("pageNo", request.getPageNo());
        }
        if (StringUtils.isNotEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (StringUtils.isNotEmpty(request.getResourceID())) {
            internalRequest.addParameter("resourceID", request.getResourceID());
        }
        if (StringUtils.isNotEmpty(request.getDeployType())) {
            internalRequest.addParameter("deployType", request.getDeployType());
        }
        if (request.getWorkloadType() != 0) {
            internalRequest.addParameter("workloadType", String.valueOf(request.getWorkloadType()));
        }
        return invokeHttpClient(internalRequest, ListApplicationResponse.class);
    }

    /**
     * Delete application.
     * @param request DeleteApplicationRequest
     * @return DeleteApplicationResponse
     */
    public DeleteApplicationResponse deleteApplication(DeleteApplicationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE,
                WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID());
        internalRequest.addParameter("ignoreUnderlayError", String.valueOf(request.getIgnoreUnderlayError()));
        internalRequest.addParameter("skipDeleteUnderlay", String .valueOf(request.getSkipDeleteUnderlay()));
        return invokeHttpClient(internalRequest, DeleteApplicationResponse.class);

    }

    /**
     * Create deploy group.
     * @param request CreateDeployGroupRequest
     * @return CreateDeployGroupResponse
     */
    public CreateDeployGroupResponse createDeployGroup(CreateDeployGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        // check access info.
        if (CollectionUtils.isNotEmpty(request.getAccess())) {
            List<NewAccessModel> accessModelList = request.getAccess();
            for (NewAccessModel accessModel : accessModelList) {
                checkStringNotEmpty(accessModel.getName(), checkEmptyExceptionMessageFormat(ACCESS_NAME_MESSAGE_KEY));
                checkArgument(accessModel.getType() >= CLUSTER_NETWORK
                                && accessModel.getType() <= PUBLIC_NETWORK,
                        "type should between 1 and 3.");
                checkArgument("TCP".equals(accessModel.getProtocol()) ||
                        "UDP".equals(accessModel.getProtocol()), "protocol should be TCP or UDP.");
                checkArgument(accessModel.getPort() >= MIN_PORT && accessModel.getPort() <= MAX_PORT,
                        "port should between 1 and 65535.");
                checkArgument(accessModel.getTargetPort() >= MIN_PORT && accessModel.getPort() <= MAX_PORT,
                        "target port should between 1 and 65535.");
            }
        }
        // check deploy group info.
        checkNotNull(request.getDeployGroup(), "request deploy group should not be null.");
        NewDeployGroupModel deployGroupModel = request.getDeployGroup();
        checkStringNotEmpty(deployGroupModel.getName(),
                checkEmptyExceptionMessageFormat(DEPLOY_GROUP_NAME_MESSAGE_KEY));
        checkStringNotEmpty(deployGroupModel.getEnvironmentID(),
                checkEmptyExceptionMessageFormat(ENVIRONMENT_ID_MESSAGE_KEY));
        if (deployGroupModel.getType() == DeployGroupType.NORMAL) {
            checkArgument(deployGroupModel.getReplicas() > 0, "replicas should greater than 0.");
            checkArgument("Recreate".equals(deployGroupModel.getDeployStrategyType())
                            || "RollingUpdate".equals(deployGroupModel.getDeployStrategyType()),
                    "type should be Recreate or RollingUpdate.");
            checkNotNull(request.getDeployGroup().getConf() == null
                            || request.getDeployGroup().getConf().getContainers() == null
                            || request.getDeployGroup().getConf().getContainers().size() == 0,
                    "deploy group conf should not be null.");
            List<ContainerModel> containerModelList = request.getDeployGroup().getConf().getContainers();
            for (ContainerModel containerModel : containerModelList) {
                checkStringNotEmpty(containerModel.getBapRepositoryID(),
                        checkEmptyExceptionMessageFormat(REPOSITORY_ID_MESSAGE_KEY));
                checkStringNotEmpty(containerModel.getName(),
                        checkEmptyExceptionMessageFormat(IMAGE_NAME_MESSAGE_KEY));
                checkStringNotEmpty(containerModel.getName(),
                        checkEmptyExceptionMessageFormat(IMAGE_PATH_MESSAGE_KEY));
                checkArgument(StringUtils.isNotEmpty(containerModel.getResources().requestsInfo().get("cpu"))
                                && StringUtils.isNotEmpty(containerModel.getResources().requestsInfo().get("memory")),
                        "resource requirement should not be empty.");
            }
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, WORKSPACE_PREFIX,
                request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID(), DEPLOY_GROUP_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateDeployGroupResponse.class);
    }

    /**
     * Get deploy group.
     * @param request GetDeployGroupRequest
     * @return GetDeployGroupResponse
     */
    public GetDeployGroupResponse getDeployGroup(GetDeployGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDeployGroupID(), checkEmptyExceptionMessageFormat(DEPLOY_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, WORKSPACE_PREFIX,
                request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID(), DEPLOY_GROUP_PREFIX,
                request.getDeployGroupID());
        return invokeHttpClient(internalRequest, GetDeployGroupResponse.class);
    }

    /**
     * List deploy group by page.
     * @param request ListDeployGroupByPageRequest
     * @return ListDeployGroupByPageResponse
     */
    public ListDeployGroupByPageResponse listDeployGroupByPage(ListDeployGroupByPageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                CONSOLE_PREFIX, WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX,
                request.getApplicationID(), DEPLOY_GROUP_PREFIX);
        internalRequest.addParameter("pageSize", request.getPageSize());
        internalRequest.addParameter("pageNo", request.getPageNo());
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (StringUtils.isNotEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (StringUtils.isNotEmpty(request.getEnvironmentID())) {
            internalRequest.addParameter("environmentID", request.getEnvironmentID());
        }
        return invokeHttpClient(internalRequest, ListDeployGroupByPageResponse.class);
    }

    /**
     * List deploy group by image.
     * @param request ListDeployGroupByImageRequest
     * @return ListDeployGroupByImageResponse
     */
    public ListDeployGroupByImageResponse listDeployGroupByImage(ListDeployGroupByImageRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        // checkArgument(
        //        StringUtils.isNotEmpty(request.getImages()) || StringUtils.isNotEmpty(request.getConfigIDs()),
        //        "images or configIDs is empty.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                CONSOLE_PREFIX, WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX,
                request.getApplicationID(), DEPLOY_GROUP_PREFIX);
        if (StringUtils.isNotEmpty(request.getImages())) {
            internalRequest.addParameter("images", request.getImages());
        }
        if (StringUtils.isNotEmpty(request.getConfigIDs())) {
            internalRequest.addParameter("configIDs", request.getConfigIDs());
        }
        return invokeHttpClient(internalRequest, ListDeployGroupByImageResponse.class);
    }

    /**
     * Delete deploy group.
     * @param request DeleteDeployGroupRequest
     * @return DeleteDeployGroupResponse
     */
    public DeleteDeployGroupResponse deleteDeployGroup(DeleteDeployGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDeployGroupID(), checkEmptyExceptionMessageFormat(DEPLOY_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.DELETE,
                WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID(),
                DEPLOY_GROUP_PREFIX, request.getDeployGroupID());
        internalRequest.addParameter("skipDeleteUnderlay", String.valueOf(request.getSkipDeleteUnderlay()));
        internalRequest.addParameter("ignoreUnderlayError", String.valueOf(request.getIgnoreUnderlayError()));
        return invokeHttpClient(internalRequest, DeleteDeployGroupResponse.class);
    }

    /**
     * Update deploy group.
     * @param request UpdateDeployGroupRequest
     * @return UpdateDeployGroupResponse
     */
    public UpdateDeployGroupResponse updateDeployGroup(UpdateDeployGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDeployGroupID(), checkEmptyExceptionMessageFormat(DEPLOY_GROUP_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT,
                WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID(),
                DEPLOY_GROUP_PREFIX, request.getDeployGroupID());
        return invokeHttpClient(internalRequest, UpdateDeployGroupResponse.class);
    }

    /**
     * Scale deploy group.
     * @param request ScaleDeployGroupRequest
     * @return ScaleDeployGroupResponse
     */
    public ScaleDeployGroupResponse scaleDeployGroup(ScaleDeployGroupRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getDeployGroupID(), checkEmptyExceptionMessageFormat(DEPLOY_GROUP_ID_MESSAGE_KEY));
        checkArgument(request.getReplicas() >= 0, "replicas is illegal");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.PUT,
                WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID(),
                DEPLOY_GROUP_PREFIX, request.getDeployGroupID(), "scale");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ScaleDeployGroupResponse.class);
    }

    /**
     * Create release record.
     * @param request CreateReleaseRecordRequest
     * @return CreateReleaseRecordResponse
     */
    public CreateReleaseRecordResponse createReleaseRecord(CreateReleaseRecordRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        checkArgument("RdType".equals(request.getType()), "type should be RdType.");
        checkArgument(StringUtils.isNotEmpty(request.getDescription())
                && request.getDescription().length() < MAX_DESCRIPTION_LENGTH,
                "description should not be empty or greater than 140 character");
        checkArgument(CollectionUtils.isNotEmpty(request.getImages())
                || CollectionUtils.isNotEmpty(request.getConfigs()),
                "images info or config info shoud not be empty.");
        checkArgument(CollectionUtils.isNotEmpty(request.getTasks()), "task info should not be empty.");
        List<TaskReqModel> taskReqModelList = request.getTasks();
        for (TaskReqModel taskReqModel : taskReqModelList) {
            checkStringNotEmpty(taskReqModel.getDeployGroupID(),
                    checkEmptyExceptionMessageFormat(DEPLOY_GROUP_ID_MESSAGE_KEY));
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID(),
                RELEASE_RECORD_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateReleaseRecordResponse.class);
    }

    /**
     * Rollback release record.
     * @param request RollbackReleaseRecordRequest
     * @return RollbackReleaseRecordResponse
     */
    public RollbackReleaseRecordResponse rollbackReleaseRecord(RollbackReleaseRecordRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getReleaseRecordID(),
                checkEmptyExceptionMessageFormat(RELEASE_RECORD_ID_MESSAGE_KEY));
        checkArgument(StringUtils.isNotEmpty(request.getDescription())
                        && request.getDescription().length() < MAX_DESCRIPTION_LENGTH,
                "description should not be empty or greater than 140 character");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID(),
                RELEASE_RECORD_PREFIX, request.getReleaseRecordID(), "rollbackToVersion");
        checkArgument(CollectionUtils.isNotEmpty(request.getTasks()), "task info should not be empty.");
        List<TaskReqModel> taskReqModelList = request.getTasks();
        for (TaskReqModel taskReqModel : taskReqModelList) {
            checkStringNotEmpty(taskReqModel.getDeployGroupID(),
                    checkEmptyExceptionMessageFormat(DEPLOY_GROUP_ID_MESSAGE_KEY));
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RollbackReleaseRecordResponse.class);
    }

    /**
     * List release record.
     * @param request ListReleaseRecordRequest
     * @return ListReleaseRecordResponse
     */
    public ListReleaseRecordResponse listReleaseRecord(ListReleaseRecordRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CONSOLE_PREFIX,
                WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID(),
                RELEASE_RECORD_PREFIX);
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (StringUtils.isNotEmpty(request.getPageSize())) {
            internalRequest.addParameter("pageSize", request.getPageSize());
        }
        if (StringUtils.isNotEmpty(request.getPageNo())) {
            internalRequest.addParameter("pageNo", request.getPageNo());
        }
        if (StringUtils.isNotEmpty(request.getExecutor())) {
            internalRequest.addParameter("executor", request.getExecutor());
        }
        if (StringUtils.isNotEmpty(request.getStatus())) {
            internalRequest.addParameter("status", request.getStatus());
        }
        if (StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("type", request.getType());
        }
        if (request.getStartTime() != null) {
            internalRequest.addParameter("startTime", String.valueOf(request.getStartTime().getTime()));
        }
        if (request.getEndTime() != null) {
            internalRequest.addParameter("endTime", String.valueOf(request.getEndTime().getTime()));
        }
        return invokeHttpClient(internalRequest, ListReleaseRecordResponse.class);
    }

    /**
     * Get release record progress.
     * @param request GetReleaseRecordProgressRequest
     * @return GetReleaseRecordProgressResponse
     */
    public GetReleaseRecordProgressResponse getReleaseRecordProgress(GetReleaseRecordProgressRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getWorkspaceID(), checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplicationID(), checkEmptyExceptionMessageFormat(APPLICATION_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getReleaseRecordID(),
                checkEmptyExceptionMessageFormat(RELEASE_RECORD_ID_MESSAGE_KEY));
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET,
                WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX, request.getApplicationID(),
                RELEASE_RECORD_PREFIX, request.getReleaseRecordID(), "progress");
        return invokeHttpClient(internalRequest, GetReleaseRecordProgressResponse.class);
    }

    /**
     * Create application.
     * @param request CreateApplicationRequest
     * @return CreateApplicationResponse
     */
    public CreateApplicationResponse createApplication(CreateApplicationRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getApplication(), "application info should not be empty.");
        checkStringNotEmpty(request.getWorkspaceID(),
                checkEmptyExceptionMessageFormat(WORKSPACE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getApplication().getName(),
                checkEmptyExceptionMessageFormat(APPLICATION_NAME_MESSAGE_KEY));
        checkArgument(DEFAULT_DEPLOY_TYPE == request.getApplication().getDeployType(), "deploy type is illegal.");
        checkArgument(SIMPLE_APPLICATION == request.getApplication().getWorkloadType()
                        || MICROSERVICE_APPLICATION == request.getApplication().getWorkloadType(),
                "work load type is illegal.");
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST,
                WORKSPACE_PREFIX, request.getWorkspaceID(), APPLICATION_PREFIX);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateApplicationResponse.class);
    }

    /**
     * Create alert rule.
     * @param request CreateAlertRulesRequest
     * @return CreateAlertRulesResponse
     */
    public CreateAlertRulesResponse createAlertRules(CreateAlertRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getName(), checkEmptyExceptionMessageFormat("name"));
        checkStringNotEmpty(request.getDuration(), checkEmptyExceptionMessageFormat("duration time"));
        checkStringNotEmpty(request.getPromql(), checkEmptyExceptionMessageFormat("promql"));
        checkStringNotEmpty(request.getOp(), checkEmptyExceptionMessageFormat("op"));
        checkStringNotEmpty(request.getRepeatInterval(), checkEmptyExceptionMessageFormat("repeatInterval"));
        checkArgument(ILLEGAL_INITIAL != request.getThreshold(), "threshold is illegal.");
        InternalRequest internalRequest = this.createRequestWithCustomPath(request, HttpMethodName.POST,
                MONITORING_PREFIX, "v1", "alertRules");
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateAlertRulesResponse.class);
    }

    /**
     * Update alert rule.
     * @param request UpdateAlertRulesRequest
     * @return UpdateAlertRulesResponse
     */
    public UpdateAlertRulesResponse updateAlertRules(UpdateAlertRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getAlertRuleID(), checkEmptyExceptionMessageFormat("alert rule id"));
        checkStringNotEmpty(request.getDuration(), checkEmptyExceptionMessageFormat("duration time"));
        checkStringNotEmpty(request.getPromql(), checkEmptyExceptionMessageFormat("promql"));
        checkStringNotEmpty(request.getOp(), checkEmptyExceptionMessageFormat("op"));
        checkStringNotEmpty(request.getRepeatInterval(), checkEmptyExceptionMessageFormat("repeatInterval"));
        InternalRequest internalRequest = this.createRequestWithCustomPath(request, HttpMethodName.PUT,
                MONITORING_PREFIX, "v1", "alertRules", request.getAlertRuleID());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateAlertRulesResponse.class);
    }

    /**
     * Delete alert rules.
     * @param request DeleteAlertRulesRequest
     * @return DeleteAlertRulesResponse
     */
    public DeleteAlertRulesResponse deleteAlertRules(DeleteAlertRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getAlertRuleID(), checkEmptyExceptionMessageFormat("alert rule id"));
        InternalRequest internalRequest = this.createRequestWithCustomPath(request, HttpMethodName.DELETE,
                MONITORING_PREFIX, "v1", "alertRules", request.getAlertRuleID());
        return invokeHttpClient(internalRequest, DeleteAlertRulesResponse.class);
    }

    /**
     * List alert rules.
     * @param request ListAlertRulesRequest
     * @return ListAlertRulesResponse
     */
    public ListAlertRulesResponse listAlertRules(ListAlertRulesRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequestWithCustomPath(request, HttpMethodName.GET,
                MONITORING_PREFIX, "v1", "alertRules");
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        if (StringUtils.isNotEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        return invokeHttpClient(internalRequest, ListAlertRulesResponse.class);
    }

    /**
     * List alert record.
     * @param request ListAlertsRequest
     * @return ListAlertResponse
     */
    public ListAlertRecordResponse listAlertRecord(ListAlertRecordRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = this.createRequestWithCustomPath(request, HttpMethodName.GET,
                MONITORING_PREFIX, "v1", "alerts");
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        if (StringUtils.isNotEmpty(request.getName())) {
            internalRequest.addParameter("name", request.getName());
        }
        if (-1 != request.getStartsAt()) {
            internalRequest.addParameter("startsAt", String.valueOf(request.getStartsAt()));
        }
        if (-1 != request.getEndsAt()) {
            internalRequest.addParameter("endsAt", String.valueOf(request.getEndsAt()));
        }
        return invokeHttpClient(internalRequest, ListAlertRecordResponse.class);
    }

    /**
     * Get monitor data.
     * @param request GetMonitorDataRequest
     * @return GetMonitorDataResponse
     */
    public GetMonitorDataResponse getMonitorData(GetMonitorDataRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getQuery(), checkEmptyExceptionMessageFormat("query"));
        checkArgument(ILLEGAL_INITIAL != request.getStart(), "start is illegal.");
        checkArgument(ILLEGAL_INITIAL != request.getEnd(), "end is illegal.");
        checkArgument(ILLEGAL_INITIAL != request.getStep(), "step is illegal.");

        InternalRequest internalRequest = this.createRequestWithCustomPath(request, HttpMethodName.GET,
                MONITORING_PREFIX, "v1", "query_range");
        internalRequest.addParameter("query", request.getQuery());
        internalRequest.addParameter("start", String.valueOf(request.getStart()));
        internalRequest.addParameter("end", String.valueOf(request.getEnd()));
        internalRequest.addParameter("step", String.valueOf(request.getStep()));
        if (request.getTimeout() != ILLEGAL_INITIAL) {
            internalRequest.addParameter("timeout", String.valueOf(request.getTimeout()));
        }
        return invokeHttpClient(internalRequest, GetMonitorDataResponse.class);
    }

}

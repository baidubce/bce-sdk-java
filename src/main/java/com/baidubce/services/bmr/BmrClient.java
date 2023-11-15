/*
 * Copyright 2014-2019 Baidu, Inc.
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
package com.baidubce.services.bmr;

import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.baidubce.services.bmr.model.AddStepsRequest;
import com.baidubce.services.bmr.model.AddStepsResponse;
import com.baidubce.services.bmr.model.AdditionalFile;
import com.baidubce.services.bmr.model.ApplicationConfig;
import com.baidubce.services.bmr.model.CdsItem;
import com.baidubce.services.bmr.model.CreateClusterRequest;
import com.baidubce.services.bmr.model.CreateClusterResponse;
import com.baidubce.services.bmr.model.CreateSchedulePlanRequest;
import com.baidubce.services.bmr.model.EipBindRequest;
import com.baidubce.services.bmr.model.EipRequest;
import com.baidubce.services.bmr.model.EipUnBindRequest;
import com.baidubce.services.bmr.model.GetClusterRequest;
import com.baidubce.services.bmr.model.GetClusterResponse;
import com.baidubce.services.bmr.model.GetStepRequest;
import com.baidubce.services.bmr.model.GetStepResponse;
import com.baidubce.services.bmr.model.InstanceGroupConfig;
import com.baidubce.services.bmr.model.ListClustersRequest;
import com.baidubce.services.bmr.model.ListClustersResponse;
import com.baidubce.services.bmr.model.ListHistorySchedulePlanResponse;
import com.baidubce.services.bmr.model.ListInstanceGroupsRequest;
import com.baidubce.services.bmr.model.ListInstanceGroupsResponse;
import com.baidubce.services.bmr.model.ListInstancesRequest;
import com.baidubce.services.bmr.model.ListInstancesResponse;
import com.baidubce.services.bmr.model.ListScheduleDetailRequest;
import com.baidubce.services.bmr.model.ListStepsRequest;
import com.baidubce.services.bmr.model.ListStepsResponse;
import com.baidubce.services.bmr.model.ModifyInstanceGroupConfig;
import com.baidubce.services.bmr.model.ModifyInstanceGroupsRequest;
import com.baidubce.services.bmr.model.NormalResponse;
import com.baidubce.services.bmr.model.RenameCluseterRequest;
import com.baidubce.services.bmr.model.ScheduleCreateResponse;
import com.baidubce.services.bmr.model.SchedulePlanDetailListResponse;
import com.baidubce.services.bmr.model.SchedulePlanDetailResponse;
import com.baidubce.services.bmr.model.SchedulePlanRequest;
import com.baidubce.services.bmr.model.ScheduleResultResponse;
import com.baidubce.services.bmr.model.StepConfig;
import com.baidubce.services.bmr.model.TemplateIdRequest;
import com.baidubce.services.bmr.model.TemplateInfoResponse;
import com.baidubce.services.bmr.model.TemplateListRequest;
import com.baidubce.services.bmr.model.TemplateListResponse;
import com.baidubce.services.bmr.model.TerminateClusterRequest;
import com.baidubce.services.bmr.model.UpdateSchedulePlanRequest;
import com.baidubce.services.bmr.model.AmbariRequest;
import com.baidubce.services.bmr.model.AmbariResponse;
import com.baidubce.services.bmr.model.ListClusterHostsRequest;
import com.baidubce.services.bmr.model.ListClusterHostsResponse;
import org.apache.commons.codec.binary.Hex;

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
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonGenerator;

/**
 * Provides the client for accessing the Baidu MapReduce service.
 */
public class BmrClient extends AbstractBceClient {
    private static final String VERSION = "v1";
    private static final String CLUSTER = "cluster";
    private static final String TEMPLATE = "template";
    private static final String GET = "get";
    private static final String LIST = "list";
    private static final String EIP = "eip";
    private static final String DETAIL = "detail";
    private static final String HISTORY = "history";
    private static final String DELETE = "delete";
    private static final String RENAME = "rename";
    private static final String UPDATE = "update";
    private static final String STOP = "stop";
    private static final String START = "start";
    private static final String ADD = "add";
    private static final String INSTANCE_GROUP = "instanceGroup";
    private static final String INSTANCE = "instance";
    private static final String STEP = "step";
    private static final String CREATE = "create";
    private static final String SAVE_TEMPLATE = "save_template";
    private static final String[] HEADERS_TO_SIGN = { "host", "x-bce-date" };
    private static final String EXECUTE_PLAN = "execute_plan";
    private static final String BIND = "bind";
    private static final String UNBIND = "unbind";

    /**
     * Responsible for handling HttpResponse from all BMR service calls.
     */
    private static final HttpResponseHandler[] BMR_HANDLERS =
            new HttpResponseHandler[] { new BceMetadataResponseHandler(), new BceErrorResponseHandler(),
                                        new BceJsonResponseHandler() };

    /**
     * Constructs a new client to invoke service methods on BMR.
     */
    public BmrClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new BMR client using the client configuration to access BMR.
     *
     * @param clientConfiguration The BCE client configuration options.
     */
    public BmrClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, BMR_HANDLERS);
    }

    /**
     * List BMR clusters owned by the authenticated user.
     * Users must authenticate with a valid BCE Access Key ID, and the response
     * contains all the BMR clusters owned by the user.
     *
     * @param request The request containing valid query parameters.
     *
     * @return The response containing a list of the BMR clusters owned by the authenticated sender of the request.
     */
    public ListClustersResponse listClusters(ListClustersRequest request) {
        checkNotNull(request, "request should not be null.");

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, CLUSTER);
        if (request.getMarker() != null) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        if (request.getMaxKeys() >= 0) {
            internalRequest.addParameter("maxKeys", String.valueOf(request.getMaxKeys()));
        }

        return this.invokeHttpClient(internalRequest, ListClustersResponse.class);
    }

    /**
     * List BMR clusters owned by the authenticated user.
     *
     * @return The response containing a list of the BMR clusters owned by the authenticated sender of the request.
     */
    public ListClustersResponse listClusters() {
        return listClusters(new ListClustersRequest());
    }

    /**
     * List BMR clusters owned by the authenticated user.
     *
     * @param maxKeys The maximum number of clusters returned.
     *
     * @return The response containing a list of the BMR clusters owned by the authenticated sender of the request.
     * And the size of list is limited below maxKeys.
     */
    public ListClustersResponse listClusters(int maxKeys) {
        return listClusters(new ListClustersRequest().withMaxKeys(maxKeys));
    }

    /**
     * List BMR clusters owned by the authenticated user.
     *
     * @param marker  The start record of clusters.
     * @param maxKeys The maximum number of clusters returned.
     *
     * @return The response containing a list of the BMR clusters owned by the authenticated sender of the request.
     * The clusters' records start from the marker and the size of list is limited below maxKeys.
     */
    public ListClustersResponse listClusters(String marker, int maxKeys) {
        return listClusters(new ListClustersRequest().withMaxKeys(maxKeys).withMarker(marker));
    }

    /**
     * Describe the detail information of the target cluster.
     *
     * @param request The request object containing the ID of the target cluster.
     *
     * @return response containing the detail information of the target cluster.
     */
    public GetClusterResponse getCluster(GetClusterRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(), "The parameter clusterId should not be null or empty string.");

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, CLUSTER, request.getClusterId());

        return this.invokeHttpClient(internalRequest, GetClusterResponse.class);
    }

    /**
     * Describe the Ambari Password information of the target cluster.
     *
     * @param clusterId  ID of the target cluster.
     *
     * @return response containing the Ambari Password information of the target cluster.
     */
    public AmbariResponse getClusterAmbariPassword(String clusterId) {
        checkNotNull(clusterId, "cluster id  should not be null.");
        AmbariRequest request = new AmbariRequest().withClusterId(clusterId);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, CLUSTER, request.getClusterId(), "ambaripassword");

        return this.invokeHttpClient(internalRequest, AmbariResponse.class);
    }
    /**
     * Describe the detail information of the target cluster.
     *
     * @param clusterId The ID of the target cluster.
     *
     * @return The response containing the detail information of the target cluster.
     */
    public GetClusterResponse getCluster(String clusterId) {
        return getCluster(new GetClusterRequest().withClusterId(clusterId));
    }

    /**
     * Create a cluster with the specified options.
     *
     * @param request The request containing all options for creating a BMR cluster.
     *
     * @return The response containing the ID of the newly created cluster.
     */
    public CreateClusterResponse createCluster(CreateClusterRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getImageType(), "The imageType should not be null or empty string.");
        checkStringNotEmpty(request.getImageVersion(), "The imageVersion should not be null or empty string.");
        checkNotNull(request.getInstanceGroups(), "The instanceGroups should not be null.");

        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("imageType", request.getImageType());
            jsonGenerator.writeStringField("imageVersion", request.getImageVersion());
            jsonGenerator.writeArrayFieldStart("instanceGroups");
            for (InstanceGroupConfig instanceGroup : request.getInstanceGroups()) {
                jsonGenerator.writeStartObject();
                if (instanceGroup.getName() != null) {
                    jsonGenerator.writeStringField("name", instanceGroup.getName());
                }
                jsonGenerator.writeStringField("type", instanceGroup.getType());
                jsonGenerator.writeStringField("instanceType", instanceGroup.getInstanceType());
                jsonGenerator.writeNumberField("instanceCount", instanceGroup.getInstanceCount());
                jsonGenerator.writeNumberField("rootDiskSizeInGB", instanceGroup.getRootDiskSizeInGB());
                jsonGenerator.writeStringField("rootDiskMediumType", instanceGroup.getRootDiskMediumType());
                jsonGenerator.writeArrayFieldStart("cds");
                if (instanceGroup.getCds() != null) {
                    for (CdsItem cdsItem : instanceGroup.getCds()) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeNumberField("sizeInGB", cdsItem.getSizeInGB());
                        jsonGenerator.writeStringField("mediumType", cdsItem.getMediumType());
                        jsonGenerator.writeEndObject();
                    }
                }
                jsonGenerator.writeEndArray();
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            if (request.getName() != null) {
                jsonGenerator.writeStringField("name", request.getName());
            }
            if (request.getLogUri() != null) {
                jsonGenerator.writeStringField("logUri", request.getLogUri());
            }
            jsonGenerator.writeBooleanField("autoTerminate", request.getAutoTerminate());
            jsonGenerator.writeBooleanField("serviceHaEnabled", request.getServiceHaEnabled());
            jsonGenerator.writeBooleanField("safeModeEnabled", request.getSafeModeEnabled());

            if (request.getApplications() != null) {
                jsonGenerator.writeArrayFieldStart("applications");
                for (ApplicationConfig application : request.getApplications()) {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("name", application.getName());
                    jsonGenerator.writeStringField("version", application.getVersion());
                    if (application.getProperties() != null) {
                        jsonGenerator.writeObjectFieldStart("properties");
                        for (Map.Entry<String, Object> entry : application.getProperties().entrySet()) {
                            jsonGenerator.writeObjectField(entry.getKey(), entry.getValue());
                        }
                        jsonGenerator.writeEndObject();
                    }
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndArray();
            }

            if (request.getSteps() != null) {
                jsonGenerator.writeArrayFieldStart("steps");
                for (StepConfig step : request.getSteps()) {
                    jsonGenerator.writeStartObject();
                    if (step.getName() != null) {
                        jsonGenerator.writeStringField("name", step.getName());
                    }
                    jsonGenerator.writeStringField("type", step.getType());
                    jsonGenerator.writeStringField("actionOnFailure", step.getActionOnFailure());
                    jsonGenerator.writeObjectFieldStart("properties");
                    for (Map.Entry<String, String> entry : step.getProperties().entrySet()) {
                        jsonGenerator.writeStringField(entry.getKey(), entry.getValue());
                    }
                    jsonGenerator.writeEndObject();
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeEndArray();
            }

            String requestAdminPwd = request.getAdminPassword();
            if (requestAdminPwd != null) {
                if (requestAdminPwd.length() == 0) {
                    throw new BceClientException("AdminPassword is invalid!");
                }
                if (config.getCredentials() == null) {
                    throw new BceClientException("BceClientConfiguration BceCredentials is null!");
                }
                try {
                    String encryptAdminPassword =
                            aes128EncryptWithFirst16Char(requestAdminPwd, config.getCredentials().getSecretKey());
                    jsonGenerator.writeStringField("adminPassword", encryptAdminPassword);
                } catch (GeneralSecurityException ex) {
                    throw new BceClientException("Fail to encrypt adminPassword", ex);
                }
            }

            if (request.getVpcId() != null) {
                jsonGenerator.writeStringField("vpcId", request.getVpcId());
            }
            if (request.getSubnetId() != null) {
                jsonGenerator.writeStringField("subnetId", request.getSubnetId());
            }
            if (request.getSecurityGroup() != null) {
                jsonGenerator.writeStringField("securityGroup", request.getSecurityGroup());
            }
            if (request.getAvailabilityZone() != null) {
                jsonGenerator.writeStringField("availabilityZone", request.getAvailabilityZone());
            }

            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CLUSTER);
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.setContent(RestartableInputStream.wrap(json));

        if (request.getClientToken() != null) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }

        return this.invokeHttpClient(internalRequest, CreateClusterResponse.class);
    }


    /**
     * Modify the instance groups of the target cluster.
     *
     * @param request The request containing the ID of BMR cluster and the instance groups to be modified.
     */
    public void modifyInstanceGroups(ModifyInstanceGroupsRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(), "The clusterId should not be null or empty string.");
        checkNotNull(request.getInstanceGroups(), "The instanceGroups should not be null.");
        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeArrayFieldStart("instanceGroups");
            for (ModifyInstanceGroupConfig instanceGroup : request.getInstanceGroups()) {
                checkStringNotEmpty(instanceGroup.getId(), "The instanceGroupId should not be null or empty string.");
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("id", instanceGroup.getId());
                jsonGenerator.writeNumberField("instanceCount", instanceGroup.getInstanceCount());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.PUT, CLUSTER, request.getClusterId(), INSTANCE_GROUP);
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.setContent(RestartableInputStream.wrap(json));

        if (request.getClientToken() != null) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Terminate a BMR cluster and release all the virtual machine instances.
     *
     * @param request The request containing the ID of the cluster to be terminated.
     */
    public void terminateCluster(TerminateClusterRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(), "The parameter clusterId should not be null or empty string.");

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, CLUSTER, request.getClusterId());

        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Terminate a BMR cluster and release all the virtual machine instances.
     *
     * @param clusterId The ID of the cluster to be terminated.
     */
    public void terminateCluster(String clusterId) {
        terminateCluster(new TerminateClusterRequest().withClusterId(clusterId));
    }

    /**
     * List the instance groups of the target BMR cluster.
     *
     * @param request containing the ID of target BMR cluster.
     *
     * @return The response containing a list of InstanceGroup objects.
     */
    public ListInstanceGroupsResponse listInstanceGroups(ListInstanceGroupsRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(), "The parameter clusterId should not be null or empty string.");

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, CLUSTER, request.getClusterId(), INSTANCE_GROUP);

        return this.invokeHttpClient(internalRequest, ListInstanceGroupsResponse.class);
    }

    /**
     * List the instance groups of the target BMR cluster.
     *
     * @param clusterId the ID of target BMR cluster.
     *
     * @return The response containing a list of InstanceGroup objects.
     */
    public ListInstanceGroupsResponse listInstanceGroups(String clusterId) {
        return listInstanceGroups(new ListInstanceGroupsRequest().withClusterId(clusterId));
    }

    /**
     * List the instances belonging to the target instance group in the BMR cluster.
     *
     * @param request containing the ID of target BMR cluster and the ID of the instance group.
     *
     * @return The response containing a list of Instance objects.
     */
    public ListInstancesResponse listInstances(ListInstancesRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(), "The parameter clusterId should not be null or empty string.");
        checkStringNotEmpty(request.getInstanceGroupId(),
                "The parameter instanceGroupId should not be null or empty string.");

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, CLUSTER, request.getClusterId(), INSTANCE_GROUP,
                        request.getInstanceGroupId(), INSTANCE);

        return this.invokeHttpClient(internalRequest, ListInstancesResponse.class);
    }

    /**
     * List the instances belonging to the target instance in the BMR cluster.
     *
     * @param clusterId       the ID of target BMR cluster.
     * @param instanceGroupId the ID of target instance group.
     *
     * @return The response containing a list of Instance objects.
     */
    public ListInstancesResponse listInstances(String clusterId, String instanceGroupId) {
        return listInstances(new ListInstancesRequest().withClusterId(clusterId).withInstanceGroupId(instanceGroupId));
    }

    /**
     * Add steps to a BMR cluster.
     *
     * @param request containing the ID of target BMR cluster and several steps to be added.
     *
     * @return The response containing a list of IDs of newly added steps.
     */
    public AddStepsResponse addSteps(AddStepsRequest request) {
        checkNotNull(request, "request should not be null.");
        checkNotNull(request.getSteps(), "The parameter steps should not be null.");
        checkStringNotEmpty(request.getClusterId(), "The parameter clusterId should not be null or empty string.");

        StringWriter writer = new StringWriter();
        List<StepConfig> steps = request.getSteps();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeArrayFieldStart("steps");
            for (StepConfig step : steps) {
                jsonGenerator.writeStartObject();
                if (step.getName() != null) {
                    jsonGenerator.writeStringField("name", step.getName());
                }
                jsonGenerator.writeStringField("type", step.getType());
                jsonGenerator.writeStringField("actionOnFailure", step.getActionOnFailure());
                jsonGenerator.writeObjectFieldStart("properties");
                for (String propertyKey : step.getProperties().keySet()) {
                    jsonGenerator.writeObjectField(propertyKey, step.getProperties().get(propertyKey));
                }
                jsonGenerator.writeEndObject();
                if (null != step.getAdditionalFiles()) {
                    jsonGenerator.writeArrayFieldStart("additionalFiles");
                    for (AdditionalFile additionalFile : step.getAdditionalFiles()) {
                        jsonGenerator.writeStartObject();
                        jsonGenerator.writeStringField("remote", additionalFile.getRemote());
                        jsonGenerator.writeStringField("local", additionalFile.getLocal());
                        jsonGenerator.writeEndObject();
                    }
                    jsonGenerator.writeEndArray();
                }
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }

        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.POST, CLUSTER, request.getClusterId(), STEP);
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");

        internalRequest.setContent(RestartableInputStream.wrap(json));

        if (request.getClientToken() != null) {
            internalRequest.addParameter("clientToken", request.getClientToken());
        }

        return this.invokeHttpClient(internalRequest, AddStepsResponse.class);
    }

    public void cancelSteps(GetStepRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(), "The parameter clusterId should not be null or empty string.");
        checkStringNotEmpty(request.getStepId(), "The parameter stepId should not be null or empty string.");
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.DELETE, CLUSTER, request.getClusterId(), STEP,
                        request.getStepId());
        this.invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * List all the steps of the target BMR cluster.
     *
     * @param request The request containing the ID of target BMR cluster.
     *
     * @return The response containing the list of steps owned by the cluster.
     */
    public ListStepsResponse listSteps(ListStepsRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(), "The parameter clusterId should not be null or empty string.");

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, CLUSTER, request.getClusterId(), STEP);
        internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));

        return this.invokeHttpClient(internalRequest, ListStepsResponse.class);
    }


    /**
     * Describe the detail information of the target step.
     * <p>
     * <p>
     * The request is valid just if the step exists and the step is owned by the cluster
     *
     * @param request The request containing the ID of BMR cluster and the ID of step.
     *
     * @return The response containing the detail information of target step.
     */
    public GetStepResponse getStep(GetStepRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(), "The parameter clusterId should not be null or empty string.");
        checkStringNotEmpty(request.getStepId(), "The parameter stepId should not be null or empty string.");

        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, CLUSTER, request.getClusterId(), STEP,
                        request.getStepId());

        return this.invokeHttpClient(internalRequest, GetStepResponse.class);
    }

    /**
     * Describe the detail information of the target step.
     *
     * @param clusterId The ID of the cluster which owns the step.
     * @param stepId    The ID of the target step.
     *
     * @return The response containing the detail information of the target step.
     */
    public GetStepResponse getStep(String clusterId, String stepId) {
        return getStep(new GetStepRequest().withClusterId(clusterId).withStepId(stepId));
    }

    /**
     * rename a cluster's name
     *
     * @param request contains cluster's ID and cluster's new name
     *
     * @return the success info
     */
    public NormalResponse renameCluster(RenameCluseterRequest request) {
        checkNotNull(request, "request should not be null.");
        checkStringNotEmpty(request.getClusterId(), "The parameter clusterId should not be null or empty string.");
        checkStringNotEmpty(request.getNewName(), "The parameter newNameId should not be null or empty string.");
        StringWriter writer = new StringWriter();
        try {
            JsonGenerator jsonGenerator = JsonUtils.jsonGeneratorOf(writer);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("clusterId", request.getClusterId());
            jsonGenerator.writeStringField("newName", request.getNewName());
            jsonGenerator.writeEndObject();
            jsonGenerator.close();
        } catch (IOException e) {
            throw new BceClientException("Fail to generate json", e);
        }
        byte[] json = null;
        try {
            json = writer.toString().getBytes(DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Fail to get UTF-8 bytes", e);
        }
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, CLUSTER, RENAME);
        internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(json.length));
        internalRequest.addHeader(Headers.CONTENT_TYPE, "application/json");
        internalRequest.setContent(RestartableInputStream.wrap(json));
        return this.invokeHttpClient(internalRequest, NormalResponse.class);

    }

    /**
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest    The original BCE request created by the user.
     * @param httpMethod    The HTTP method to use when sending the request.
     * @param pathVariables The optional variables used in the URI path.
     *
     * @return A new request object populated with endpoint, resource path and specific
     * parameters to send.
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
     * The encryption implement for AES-128 algorithm for BCE password encryption.
     * Only the first 16 bytes of privateKey will be used to encrypt the content.
     * <p>
     * See more detail on
     * <a href = "https://bce.baidu.com/doc/BCC/API.html#.7A.E6.31.D8.94.C1.A1.C2.1A.8D.92.ED.7F.60.7D.AF">
     * BCE API doc</a>
     *
     * @param content    The content String to encrypt.
     * @param privateKey The security key to encrypt.
     *                   Only the first 16 bytes of privateKey will be used to encrypt the content.
     *
     * @return The encrypted string of the original content with AES-128 algorithm.
     *
     * @throws GeneralSecurityException
     */
    private String aes128EncryptWithFirst16Char(String content, String privateKey) throws GeneralSecurityException {
        if (privateKey == null || privateKey.length() < 16) {
            throw new GeneralSecurityException("account secretKey is wrong");
        }
        byte[] crypted = null;
        SecretKeySpec skey = new SecretKeySpec(privateKey.substring(0, 16).getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        crypted = cipher.doFinal(content.getBytes());
        return new String(Hex.encodeHex(crypted));
    }
    /**
     * List host info for a cluster
     *
     * @param clusterId cluster uuid
     */
    public ListClusterHostsResponse listClusterHosts(String clusterId) {
        checkNotNull(clusterId, "cluster id  should not be null.");
        ListClusterHostsRequest request = new ListClusterHostsRequest().withClusterId(clusterId);
        InternalRequest internalRequest =
                this.createRequest(request, HttpMethodName.GET, CLUSTER, request.getClusterId(), "hosts");
        return this.invokeHttpClient(internalRequest, ListClusterHostsResponse.class);
    }
}

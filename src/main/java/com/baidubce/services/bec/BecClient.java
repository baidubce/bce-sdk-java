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
package com.baidubce.services.bec;

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
import com.baidubce.services.bec.model.blb.CreateBecBlbBindingRequest;
import com.baidubce.services.bec.model.blb.CreateBecBlbBindingResponse;
import com.baidubce.services.bec.model.blb.CreateBecBlbMonitorPortRequest;
import com.baidubce.services.bec.model.blb.CreateBecBlbMonitorPortResponse;
import com.baidubce.services.bec.model.blb.CreateBecBlbRequest;
import com.baidubce.services.bec.model.blb.CreateBecBlbResponse;
import com.baidubce.services.bec.model.blb.DeleteBecBlbRequest;
import com.baidubce.services.bec.model.blb.DeleteBecBlbResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbBackendBindingStsListRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbBackendBindingStsListResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbBackendPodListRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbBackendPodListResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbBindingPodListWithStsRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbBindingPodListWithStsResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbInstanceRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbInstanceResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortDetailsRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortDetailsResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortListRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbMonitorPortListResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbResourceMetricsRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbResourceMetricsResponse;
import com.baidubce.services.bec.model.blb.GetBecBlbsRequest;
import com.baidubce.services.bec.model.blb.GetBecBlbsResponse;
import com.baidubce.services.bec.model.blb.UpdateBecBlbBindPodWeightRequest;
import com.baidubce.services.bec.model.blb.UpdateBecBlbBindPodWeightResponse;
import com.baidubce.services.bec.model.blb.UpdateBecBlbMonitorPortRequest;
import com.baidubce.services.bec.model.blb.UpdateBecBlbMonitorPortResponse;
import com.baidubce.services.bec.model.blb.UpdateBecBlbRequest;
import com.baidubce.services.bec.model.blb.UpdateBecBlbResponse;
import com.baidubce.services.bec.model.handler.BecHttpResponseHandler;
import com.baidubce.services.bec.model.overview.GetBecContainerMetricsRequest;
import com.baidubce.services.bec.model.overview.GetBecContainerMetricsResponse;
import com.baidubce.services.bec.model.overview.GetBecContainerSummaryRequest;
import com.baidubce.services.bec.model.overview.GetBecContainerSummaryResponse;
import com.baidubce.services.bec.model.overview.GetBecResourceSummaryRequest;
import com.baidubce.services.bec.model.overview.GetBecResourceSummaryResponse;
import com.baidubce.services.bec.model.overview.GetBecVMSummaryRequest;
import com.baidubce.services.bec.model.overview.GetBecVMSummaryResponse;
import com.baidubce.services.bec.model.overview.GetBecVmMetricsRequest;
import com.baidubce.services.bec.model.overview.GetBecVmMetricsResponse;
import com.baidubce.services.bec.model.overview.GetBecVmNodeLevelMetricsRequest;
import com.baidubce.services.bec.model.overview.GetBecVmNodeLevelMetricsResponse;
import com.baidubce.services.bec.model.resource.ListBecPassThroughDiskPackagesRequest;
import com.baidubce.services.bec.model.resource.ListBecPassThroughDiskPackagesResponse;
import com.baidubce.services.bec.model.resource.ListBecServicePackagesRequest;
import com.baidubce.services.bec.model.resource.ListBecServicePackagesResponse;
import com.baidubce.services.bec.model.vm.instance.DeleteBecVmInstanceRequest;
import com.baidubce.services.bec.model.vm.instance.DeleteBecVmInstanceResponse;
import com.baidubce.services.bec.model.vm.instance.GetBecNodeVmInstanceListRequest;
import com.baidubce.services.bec.model.vm.instance.GetBecNodeVmInstanceListResponse;
import com.baidubce.services.bec.model.vm.instance.GetBecVirtualMachineRequest;
import com.baidubce.services.bec.model.vm.instance.GetBecVirtualMachineResponse;
import com.baidubce.services.bec.model.vm.instance.GetBecVmConfigRequest;
import com.baidubce.services.bec.model.vm.instance.GetBecVmConfigResponse;
import com.baidubce.services.bec.model.vm.instance.GetBecVmInstanceListRequest;
import com.baidubce.services.bec.model.vm.instance.GetBecVmInstanceListResponse;
import com.baidubce.services.bec.model.vm.instance.GetBecVmInstanceMetricsRequest;
import com.baidubce.services.bec.model.vm.instance.GetBecVmInstanceMetricsResponse;
import com.baidubce.services.bec.model.vm.instance.OperateBecVmDeploymentRequest;
import com.baidubce.services.bec.model.vm.instance.OperateBecVmDeploymentResponse;
import com.baidubce.services.bec.model.vm.instance.ReinstallBecVmInstanceRequest;
import com.baidubce.services.bec.model.vm.instance.ReinstallBecVmInstanceResponse;
import com.baidubce.services.bec.model.vm.instance.UpdateBecVmDeploymentRequest;
import com.baidubce.services.bec.model.vm.instance.UpdateBecVmDeploymentResponse;
import com.baidubce.services.bec.model.vm.service.BecVmServiceActionRequest;
import com.baidubce.services.bec.model.vm.service.BecVmServiceActionResponse;
import com.baidubce.services.bec.model.vm.service.CreateBecVmServiceRequest;
import com.baidubce.services.bec.model.vm.service.CreateBecVmServiceResponse;
import com.baidubce.services.bec.model.vm.service.DelBecVmServiceRequest;
import com.baidubce.services.bec.model.vm.service.DelBecVmServiceResponse;
import com.baidubce.services.bec.model.vm.service.GetBecVmServiceMetricsRequest;
import com.baidubce.services.bec.model.vm.service.GetBecVmServiceMetricsResponse;
import com.baidubce.services.bec.model.vm.service.GetBecVmServiceRequest;
import com.baidubce.services.bec.model.vm.service.GetBecVmServiceResponse;
import com.baidubce.services.bec.model.vm.service.GetBecVmServicesRequest;
import com.baidubce.services.bec.model.vm.service.GetBecVmServicesResponse;
import com.baidubce.services.bec.model.vm.service.UpdateBecVmServiceRequest;
import com.baidubce.services.bec.model.vm.service.UpdateBecVmServiceResponse;
import com.baidubce.services.bec.model.vm.template.CreateBecVmTemplateRequest;
import com.baidubce.services.bec.model.vm.template.CreateBecVmTemplateResponse;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateListRequest;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateListResponse;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateRequest;
import com.baidubce.services.bec.model.vm.template.GetBecVmTemplateResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Baidu Cloud Edge Service(bec).
 */
public class BecClient extends AbstractBceClient {

    private static final String VERSION = "v1";
    private static final String OVERVIEW = "overview";
    private static final String SUMMARY = "summary";
    private static final String CONTAINER = "container";
    private static final String BLB_PREFIX = "blb";
    private static final String LB_PREFIX = "lb";
    private static final String VM_PREFIX = "vm";
    private static final String NODE_PREFIX = "node";
    private static final String SERVICE = "service";
    private static final String INSTANCE = "instance";
    private static final String TEMPLATE = "template";
    private static final String METRICS = "metrics";
    private static final String REGIONS = "regions";
    private static final String SERVICE_PROVIDER = "sps";
    private static final String CITIES = "cities";
    private static final String SYSTEM = "system";
    private static final String CONFIG = "config";
    private static final String REINSTALL = "reinstall";
    private static final String MONITOR = "monitor";
    private static final String RESOURCE = "resource";
    private static final String PACKAGE = "package";
    private static final String DISK = "disk";
    private static final String PASS_THROUGH = "passthrough";
    private static final String BINDED = "binded";
    private static final String BINDING = "binding";
    private static final String BINDINGPOD = "bindingpod";
    private static final String PORT = "port";
    private static final String CLIENT_TOKEN = "clientToken";
    private static final String CREATE = "create";
    private static final String LIST = "list";

    /**
     * Exception messages.
     */
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String OFFSET_IN_SECONDS_NULL_ERROR_MESSAGE = "offsetInSeconds should not be null.";
    private static final String BLB_ID_MESSAGE_KEY = "blbId";
    private static final String BLB_TYPE_MESSAGE_KEY = "type";
    private static final String VM_SERVICE_ID_MESSAGE_KEY = "serviceId";
    private static final String VM_ID_MESSAGE_KEY = "vmId";
    private static final String TEMPLATE_ID_MESSAGE_KEY = "templateId";
    private static final String VM_INSTANCE_REGION_MESSAGE_KEY = "region";
    private static final String VM_INSTANCE_SPS_MESSAGE_KEY = "serviceProvider";
    private static final String VM_INSTANCE_CITY_MESSAGE_KEY = "city";
    private static final String VM_ACTION_MESSAGE_KEY = "action";
    private static final String METRICS_TYPE_MESSAGE_KEY = "metricsType";

    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};

    /**
     * Responsible for handling httpResponses from all bec service calls.
     */
    private static final HttpResponseHandler[] bec_handlers = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BecHttpResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new client to invoke service methods on bec.
     */
    public BecClient() {
        this(new BecClientConfiguration());
    }

    /**
     * Constructs a new bec client using the client configuration to access bec.
     *
     * @param clientConfiguration The bec client configuration options controlling how this client
     *                            connects to bec (e.g. proxy settings, retry counts, etc).
     */
    public BecClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, bec_handlers);
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
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
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
     * Create a new BEC virtual machine api.
     *
     * @param request: The request containing all options for creating a bec virtual machine service.
     * @return: The response with id of service newly created.
     */
    public CreateBecVmServiceResponse createBecVmService(CreateBecVmServiceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VM_PREFIX, SERVICE);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecVmServiceResponse.class);
    }

    /**
     * create BEC virtual machine instances for service api
     *
     * @param request: The request contains all options to create a bec virtual machine for the service.
     * @return: The response with id of service.
     */
    public CreateBecVmServiceResponse createBecVmServiceInstances(CreateBecVmServiceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
            , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VM_PREFIX, SERVICE,
            request.getServiceId(), INSTANCE);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecVmServiceResponse.class);
    }

    /**
     * Get BEC virtual machine service list
     *
     * @param request: The request contains all options for getting a list of BEC virtual machine services.
     * @return: paged api list, for brief info.
     */
    public GetBecVmServicesResponse getBecVmServices(GetBecVmServicesRequest request) {

        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET
                , VM_PREFIX, SERVICE);
        if (StringUtils.isNotEmpty(request.getKeywordType())) {
            internalRequest.addParameter("keywordType", request.getKeywordType());
        }
        if (StringUtils.isNotEmpty(request.getKeyword())) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (request.getPageNo() > 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }
        return invokeHttpClient(internalRequest, GetBecVmServicesResponse.class);
    }

    /**
     * Update BEC virtual machine service.
     *
     * @param request: The request containing all options for updating the virtual machine service.
     * @return: The response contains information about whether the service was successfully updated.
     */
    public UpdateBecVmServiceResponse updateBecVmService(UpdateBecVmServiceRequest request) {

        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
                , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                VM_PREFIX, SERVICE, request.getServiceId());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecVmServiceResponse.class);
    }

    /**
     * Get BEC virtual machine service details.
     *
     * @param request: The request containing all options for getting the api details.
     * @return: BEC virtual machine service details.
     */
    public GetBecVmServiceResponse getBecVmService(GetBecVmServiceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
                , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET
                , VM_PREFIX, SERVICE, request.getServiceId());
        return invokeHttpClient(internalRequest, GetBecVmServiceResponse.class);
    }

    /**
     * start/stop/release the BEC virtual machine service.
     *
     * @param request: The request contains all options for operating the virtual machine service.
     * @return: The response contains the result of operating the virtual machine service.
     */
    public BecVmServiceActionResponse becVmServiceAction(BecVmServiceActionRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
                , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAction()
                , checkEmptyExceptionMessageFormat(VM_ACTION_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                VM_PREFIX, SERVICE, request.getServiceId(), request.getAction());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        return invokeHttpClient(internalRequest, BecVmServiceActionResponse.class);
    }

    /**
     * Delete BEC virtual machine service.
     *
     * @param request: The request contains the api ID that should be deleted.
     * @return: The response contains the result of whether the service was successfully deleted.
     */
    public DelBecVmServiceResponse delBecVmService(DelBecVmServiceRequest request) {

        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
                , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE,
                VM_PREFIX, SERVICE, request.getServiceId());
        return invokeHttpClient(internalRequest, DelBecVmServiceResponse.class);
    }

    /**
     * Get BEC service metrics.
     *
     * @param request: The request containing all options for getting BEC service metrics.
     * @return: The response contains BEC service metrics.
     */
    public GetBecVmServiceMetricsResponse getBecVmServiceMetrics(GetBecVmServiceMetricsRequest request) {

        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getServiceId()
                , checkEmptyExceptionMessageFormat(VM_SERVICE_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getType()
                , checkEmptyExceptionMessageFormat(METRICS_TYPE_MESSAGE_KEY));
        checkNotNull(request.getOffsetInSeconds(), OFFSET_IN_SECONDS_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
            MONITOR, SERVICE, VM_PREFIX, request.getServiceId());
        if (StringUtils.isNotEmpty(request.getServiceId())) {
            internalRequest.addParameter("serviceId", request.getServiceId());
        }
        if (StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("metricsType", request.getType());
        }
        if (request.getOffsetInSeconds() > 0) {
            // 获取整分数据
            long end = Calendar.getInstance().getTimeInMillis() / 1000;

            long start = end - request.getOffsetInSeconds();
            internalRequest.addParameter("start", String.valueOf(start));
            internalRequest.addParameter("end", String.valueOf(end));
        }
        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }
        if (request.getStepInMin() != null && request.getStepInMin() > 0) {
            internalRequest.addParameter("stepInMin", String.valueOf(request.getStepInMin()));
        }
        return invokeHttpClient(internalRequest, GetBecVmServiceMetricsResponse.class);

    }

    /**
     * Get the list of BEC virtual machine instances.
     *
     * @param request: optional query parameter, the keyword for deployment name.
     * @return: paged deployment list.
     */
    public GetBecVmInstanceListResponse getBecVmInstanceList(GetBecVmInstanceListRequest request) {

        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                VM_PREFIX, INSTANCE);
        if (request.getListRequest() != null) {
            if (StringUtils.isNotEmpty(request.getListRequest().getKeywordType())) {
                internalRequest.addParameter("keywordType", request.getListRequest().getKeywordType());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getKeyword())) {
                internalRequest.addParameter("keyword", request.getListRequest().getKeyword());
            }
            if (request.getListRequest().getPageNo() > 0) {
                internalRequest.addParameter("pageNo", String.valueOf(request.getListRequest().getPageNo()));
            }
            if (request.getListRequest().getPageSize() > 0) {
                internalRequest.addParameter("pageSize", String.valueOf(request.getListRequest().getPageSize()));
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getOrder())) {
                internalRequest.addParameter("order", request.getListRequest().getOrder());
            }
            if (StringUtils.isNotEmpty(request.getListRequest().getOrderBy())) {
                internalRequest.addParameter("orderBy", request.getListRequest().getOrderBy());
            }
        }
        return invokeHttpClient(internalRequest, GetBecVmInstanceListResponse.class);
    }

    /**
     * Get the BEC virtual machine list of the node.
     *
     * @param request: optional query parameter, the keyword for deployment name.
     * @return: deployment list.
     */
    public GetBecNodeVmInstanceListResponse getBecNodeVmInstanceList(GetBecNodeVmInstanceListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getRegion(), checkEmptyExceptionMessageFormat(VM_INSTANCE_REGION_MESSAGE_KEY));
        checkStringNotEmpty(request.getServiceProvider(),
                checkEmptyExceptionMessageFormat(VM_INSTANCE_SPS_MESSAGE_KEY));
        checkStringNotEmpty(request.getCity(), checkEmptyExceptionMessageFormat(VM_INSTANCE_CITY_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VM_PREFIX, INSTANCE, REGIONS,
                request.getRegion(), SERVICE_PROVIDER, request.getServiceProvider(), CITIES, request.getCity());
        return invokeHttpClient(internalRequest, GetBecNodeVmInstanceListResponse.class);
    }

    /**
     * Get the details of the BEC virtual machine.
     *
     * @param request: The request contains all the options for getting the details of the BEC virtual machine.
     * @return: deployment details.
     */
    public GetBecVirtualMachineResponse getBecVirtualMachine(GetBecVirtualMachineRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VM_PREFIX, INSTANCE,
                request.getVmID());
        return invokeHttpClient(internalRequest, GetBecVirtualMachineResponse.class);
    }

    /**
     * Delete BEC virtual machine.
     *
     * @param request: The request contains all options for deleting the BEC virtual machine.
     * @return: The response contains the result of whether the Instance was successfully deleted.
     */
    public DeleteBecVmInstanceResponse deleteBecVmInstance(DeleteBecVmInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, VM_PREFIX, INSTANCE,
                request.getVmID());
        return invokeHttpClient(internalRequest, DeleteBecVmInstanceResponse.class);
    }

    /**
     * Update BEC virtual machine resources.
     *
     * @param request: The request contains all the options for updating BEC virtual machine resources.
     * @return: The response contains information about whether the instance was successfully updated.
     */
    public UpdateBecVmDeploymentResponse updateBecVmDeployment(UpdateBecVmDeploymentRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, VM_PREFIX, INSTANCE,
                request.getVmID());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecVmDeploymentResponse.class);
    }

    /**
     * Reinstall the BEC virtual machine system.
     *
     * @param request: The request contains all the options for reinstalling the BEC virtual machine system.
     * @return: The response contains information on whether the system reinstallation was successful.
     */
    public ReinstallBecVmInstanceResponse reinstallBecVmInstance(ReinstallBecVmInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, VM_PREFIX, INSTANCE,
                request.getVmID(), SYSTEM, REINSTALL);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ReinstallBecVmInstanceResponse.class);
    }

    /**
     * start/stop/restart the BEC virtual machine instance.
     *
     * @param request: The request contains all options for operating the BEC virtual machine instance.
     * @return: The response contains the result of operating the virtual machine instance.
     */
    public OperateBecVmDeploymentResponse operateBecVmDeployment(OperateBecVmDeploymentRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getAction(), checkEmptyExceptionMessageFormat(VM_ACTION_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, VM_PREFIX, INSTANCE,
                request.getVmID(), request.getAction());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, OperateBecVmDeploymentResponse.class);
    }

    /**
     * Get the BEC virtual machine instance metrics.
     *
     * @param request: The request containing all options for getting bec virtual machine instance metrics.
     * @return: The response contains BEC virtual machine instance metrics.
     */
    public GetBecVmInstanceMetricsResponse getBecVmInstanceMetrics(GetBecVmInstanceMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmId(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getType(), checkEmptyExceptionMessageFormat(METRICS_TYPE_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
            MONITOR, VM_PREFIX, request.getVmId());
        if (StringUtils.isNotEmpty(request.getVmId())) {
            internalRequest.addParameter("vmId", request.getVmId());
        }
        if (StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("metricsType", request.getType());
        }
        if (request.getOffsetInSeconds() > 0) {
            // 获取整分数据
            long end = Calendar.getInstance().getTimeInMillis() / 1000;

            long start = end - request.getOffsetInSeconds();
            internalRequest.addParameter("start", String.valueOf(start));
            internalRequest.addParameter("end", String.valueOf(end));
        }
        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }
        if (request.getStepInMin() != null && request.getStepInMin() > 0) {
            internalRequest.addParameter("stepInMin", String.valueOf(request.getStepInMin()));
        }
        return invokeHttpClient(internalRequest, GetBecVmInstanceMetricsResponse.class);
    }

    /**
     * Get the BEC node level virtual machine instance metrics.
     *
     * @param request: The request containing all options for getting bec node level virtual machine instance metrics.
     * @return: The response contains BEC node level virtual machine instance metrics.
     */
    public GetBecVmNodeLevelMetricsResponse getVmNodeMetrics(GetBecVmNodeLevelMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getType(), checkEmptyExceptionMessageFormat(METRICS_TYPE_MESSAGE_KEY));
        checkStringNotEmpty(request.getRegion(), checkEmptyExceptionMessageFormat(VM_INSTANCE_REGION_MESSAGE_KEY));
        checkStringNotEmpty(request.getCity(), checkEmptyExceptionMessageFormat(VM_INSTANCE_CITY_MESSAGE_KEY));
        checkStringNotEmpty(request.getServiceProvider(),
            checkEmptyExceptionMessageFormat(VM_INSTANCE_SPS_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
            MONITOR, OVERVIEW, VM_PREFIX, NODE_PREFIX, request.getType());

        if (request.getStart() > 0 && request.getEnd() > 0) {
            internalRequest.addParameter("start", String.valueOf(request.getStart()));
            internalRequest.addParameter("end", String.valueOf(request.getEnd()));
        }

        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }

        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("city", request.getCity());
        }

        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("region", request.getRegion());
        }

        if (StringUtils.isNotEmpty(request.getNetwork())) {
            internalRequest.addParameter("network", request.getNetwork());
        }

        if (request.getStepInMin() != null && request.getStepInMin() > 0) {
            internalRequest.addParameter("stepInMin", String.valueOf(request.getStepInMin()));
        }

        return invokeHttpClient(internalRequest, GetBecVmNodeLevelMetricsResponse.class);

    }

    /**
     * Get the BEC virtual machine instance config.
     *
     * @param request: The request containing all options for getting BEC virtual machine instance config.
     * @return: The response contains BEC virtual machine instance config.
     */
    public GetBecVmConfigResponse getBecVmConfig(GetBecVmConfigRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getVmID(), checkEmptyExceptionMessageFormat(VM_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VM_PREFIX, INSTANCE,
                request.getVmID(), CONFIG);
        return invokeHttpClient(internalRequest, GetBecVmConfigResponse.class);
    }

    /**
     * Create a virtual machine template.
     * @param request: The request containing all options for creating the BEC vm template.
     * @return: The response with id of template newly created.
     */
    public CreateBecVmTemplateResponse createBecVmTemplate(CreateBecVmTemplateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, VM_PREFIX, TEMPLATE, CREATE);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecVmTemplateResponse.class);
    }

    /**
     * Get the list of BEC virtual machine templates.
     *
     * @param request: The request containing all options for getting the BEC vm template list.
     * @return: paged template list.
     */
    public GetBecVmTemplateListResponse listBecVmTemplate(GetBecVmTemplateListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VM_PREFIX, TEMPLATE, LIST);

        if (StringUtils.isNotEmpty(request.getKeyword())) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }

        if (StringUtils.isNotEmpty(request.getKeywordType())) {
            internalRequest.addParameter("keywordType", request.getKeywordType());
        }

        if (StringUtils.isNotEmpty(request.getOrder())) {
            internalRequest.addParameter("order", request.getOrder());
        }

        if (StringUtils.isNotEmpty(request.getOrderBy())) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
        }

        if (request.getPageNo() != 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }

        if (request.getPageSize() != 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }

        return invokeHttpClient(internalRequest, GetBecVmTemplateListResponse.class);
    }

    /**
     * Get BEC virtual machine template details.
     *
     * @param request: The request containing all options for getting the teplate details.
     * @return: BEC virtual machine template details.
     */
    public GetBecVmTemplateResponse getBecVmTemplate(GetBecVmTemplateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getTemplateId(), checkEmptyExceptionMessageFormat(TEMPLATE_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, VM_PREFIX, TEMPLATE,
            request.getTemplateId());
        return invokeHttpClient(internalRequest, GetBecVmTemplateResponse.class);
    }

    /**
     * Create a BEC blb with the specified options.
     *
     * @param request: The request containing all options for creating the BEC blb.
     * @return: The response contains whether the BEC blb was successfully created.
     */
    public CreateBecBlbResponse createBecBlb(CreateBecBlbRequest request) {

        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, BLB_PREFIX);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecBlbResponse.class);
    }

    /**
     * Get the BEC blb list.
     *
     * @param request: The request containing all options for getting the BEC blb list.
     * @return: The response contains the created BEC blb list.
     */
    public GetBecBlbsResponse getBecBlbs(GetBecBlbsRequest request) {

        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX);
        if (StringUtils.isNotEmpty(request.getLbType())) {
            internalRequest.addParameter("lbType", request.getLbType());
        }
        if (request.getLbType() != null) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getLbType() != null) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        return invokeHttpClient(internalRequest, GetBecBlbsResponse.class);
    }

    /**
     * Get the specific BEC blb instance.
     *
     * @param request: The request containing all options for getting the BEC blb instance info.
     * @return: The response contains the created BEC blb instance.
     */
    public GetBecBlbInstanceResponse getBecBlb(GetBecBlbInstanceRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET
                , BLB_PREFIX, request.getBlbId());
        return invokeHttpClient(internalRequest, GetBecBlbInstanceResponse.class);
    }

    /**
     * Delete the specific BEC blb instance.
     *
     * @param request: The request containing all options for deleting the BEC blb instance.
     * @return: The response contains information about whether the BEC blb instance was successfully deleted.
     */
    public DeleteBecBlbResponse deleteBecBlb(DeleteBecBlbRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE
                , BLB_PREFIX, request.getBlbId());
        return invokeHttpClient(internalRequest, DeleteBecBlbResponse.class);
    }

    /**
     * Update the the specific BEC blb instance.
     *
     * @param request: The request containing all options for updating the BEC blb instance.
     * @return: The response contains information about whether the BLB instance was successfully updated.
     */
    public UpdateBecBlbResponse updateBecBlb(UpdateBecBlbRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, BLB_PREFIX, request.getBlbId());
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecBlbResponse.class);
    }

    /**
     * Create the BEC blb monitor port for assign blb.
     *
     * @param request: The request containing all options for creating the BEC blb monitor port.
     * @return: The response contains information on whether the BEC blb monitor port was successfully created.
     */
    public CreateBecBlbMonitorPortResponse createBecBlbMonitorPort(CreateBecBlbMonitorPortRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, BLB_PREFIX,
                request.getBlbId(), MONITOR);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecBlbMonitorPortResponse.class);
    }

    /**
     * Get the BEC blb port monitor list for assign blb.
     *
     * @param request: The request containing all options for getting the BEC blb port monitor list.
     * @return paged blb listeners port list, for brief info
     */
    public GetBecBlbMonitorPortListResponse getBlbMonitorPortList(GetBecBlbMonitorPortListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX,
                request.getBlbId(), MONITOR);
        return invokeHttpClient(internalRequest, GetBecBlbMonitorPortListResponse.class);

    }

    /**
     * Get the BEC blb monitor port details for assign blb and assign port.
     *
     * @param request: The request containing all options for getting the BEC blb monitor port details.
     * @return: The response contains the BEC blb monitor port details.
     */
    public GetBecBlbMonitorPortDetailsResponse getBecBlbMonitorPortDetails(GetBecBlbMonitorPortDetailsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX,
                request.getBlbId(), MONITOR, PORT);
        if (request.getProtocol() != null) {
            internalRequest.addParameter("protocol", String.valueOf(request.getProtocol()));
        }
        if (request.getPort() != 0) {
            internalRequest.addParameter("port", String.valueOf(request.getPort()));
        }
        return invokeHttpClient(internalRequest, GetBecBlbMonitorPortDetailsResponse.class);
    }

    /**
     * Update the BEC blb monitor port for assign blb.
     *
     * @param request: The request containing all options for updating the Blb monitor port.
     * @return: The response contains information about whether the BEC blb monitor port was successfully updated.
     */
    public UpdateBecBlbMonitorPortResponse updateBecBlbMonitorPort(UpdateBecBlbMonitorPortRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, BLB_PREFIX,
                request.getBlbId(), MONITOR);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecBlbMonitorPortResponse.class);
    }

    /**
     * Get the bind BEC blb backend Pod/Vm list for assign blb.
     *
     * @param request: The request containing all options for getting the bind BEC blb backend Pod/Vm list.
     * @return: paged blb backend pod list, for brief info.
     */
    public GetBecBlbBackendPodListResponse getBecBlbBackendPodList(GetBecBlbBackendPodListRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX,
                request.getBlbId(), BINDED);
        return invokeHttpClient(internalRequest, GetBecBlbBackendPodListResponse.class);
    }

    /**
     * Get the binding BEC blb backend StatefulSet/VmReplicas list for assign blb.
     *
     * @param request: The request containing all options for getting the binding BEC blb backend
     *                 StatefulSet/VmReplicas list.
     * @return: paged blb backend StatefulSet/VmReplicas list, for brief info.
     */
    public GetBecBlbBackendBindingStsListResponse getBecBlbBackendBindingStsList(GetBecBlbBackendBindingStsListRequest
                                                                                         request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX,
                request.getBlbId(), BINDING);
        return invokeHttpClient(internalRequest, GetBecBlbBackendBindingStsListResponse.class);
    }

    /**
     * Get the binding BEC blb backend Pod/Vm list for assign blb.
     *
     * @param request: The request containing all options for getting the binding BEC blb backend Pod/Vm list.
     * @return: paged blb backend Pod/Vm list, for brief info.
     */
    public GetBecBlbBindingPodListWithStsResponse getBecBlbBindingPodListWithSts(GetBecBlbBindingPodListWithStsRequest
                                                                                         request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, BLB_PREFIX
                , request.getBlbId(), BINDINGPOD);
        if (StringUtils.isNotEmpty(request.getStsName())) {
            internalRequest.addParameter("stsName", request.getStsName());
        }
        return invokeHttpClient(internalRequest, GetBecBlbBindingPodListWithStsResponse.class);
    }

    /**
     * Bind the backend StatefulSet/VmReplicas to the specified BEC blb.
     *
     * @param request: The request containing all options for binding the backend StatefulSet/VmReplicas.
     * @return: The response contains information about whether the binding was successful.
     */
    public CreateBecBlbBindingResponse createBecBlbBinding(CreateBecBlbBindingRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, BLB_PREFIX
                , request.getBlbId(), BINDING);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateBecBlbBindingResponse.class);
    }

    /**
     * Modify the weight of the Pod/Vm bound to the specified BEC BLB backend.
     *
     * @param request: The request containing all options for modifying the weight of the Pod/Vm.
     * @return: The response contains information about whether the weight modification was successful.
     */
    public UpdateBecBlbBindPodWeightResponse updateBecBlbBindPodWeight(UpdateBecBlbBindPodWeightRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        if (Strings.isNullOrEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, BLB_PREFIX
                , request.getBlbId(), BINDED);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateBecBlbBindPodWeightResponse.class);
    }

    /**
     * Get the BEC blb monitor metrics.
     *
     * @param request: The request containing all options for getting the BEC blb monitor metrics.
     * @return: The response contains the BEC blb monitor metrics.
     */
    public GetBecBlbResourceMetricsResponse getBecBlbResourceMetrics(GetBecBlbResourceMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getBlbId(), checkEmptyExceptionMessageFormat(BLB_ID_MESSAGE_KEY));
        checkStringNotEmpty(request.getType(), checkEmptyExceptionMessageFormat(BLB_TYPE_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, MONITOR, LB_PREFIX,
            request.getBlbId());
        if (StringUtils.isNotEmpty(request.getIpType())) {
            internalRequest.addParameter("metricsType", request.getType());
        }
        if (StringUtils.isNotEmpty(request.getIpType())) {
            internalRequest.addParameter("ipType", request.getIpType());
        }
        if (request.getOffsetInSeconds() != 0) {
            // 获取整分数据
            long end = Calendar.getInstance().getTimeInMillis() / 1000;

            long start = end - request.getOffsetInSeconds();
            internalRequest.addParameter("start", String.valueOf(start));
            internalRequest.addParameter("end", String.valueOf(end));
        }
        if (StringUtils.isNotEmpty(request.getPort())) {
            internalRequest.addParameter("port", request.getPort());
        }
        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }
        if (request.getStepInMin() != null && request.getStepInMin() > 0) {
            internalRequest.addParameter("stepInMin", String.valueOf(request.getStepInMin()));
        }
        return invokeHttpClient(internalRequest, GetBecBlbResourceMetricsResponse.class);
    }

    /**
     * Get the BEC user level overview data.
     *
     * @param request: The request containing all options for getting the BEC user level overview data.
     * @return user level data, include service/deployment/pod status
     */
    public GetBecResourceSummaryResponse getBecResourceSummary(GetBecResourceSummaryRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, OVERVIEW, SUMMARY);
        if (StringUtils.isNotEmpty(request.getRegion())) {
            internalRequest.addParameter("region", request.getRegion());
        }
        return invokeHttpClient(internalRequest, GetBecResourceSummaryResponse.class);
    }

    /**
     * Get overview information of container services.
     *
     * @param request: The request containing all options for getting overview information of container services.
     * @return user level data,container service overview information.
     */
    public GetBecContainerSummaryResponse getBecContainerSummary(GetBecContainerSummaryRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, OVERVIEW, SUMMARY, CONTAINER);
        return invokeHttpClient(internalRequest, GetBecContainerSummaryResponse.class);
    }

    /**
     * Get overview information of vm services.
     *
     * @param request: The request containing all options for getting overview information of vm services.
     * @return user level data,vm service overview information.
     */
    public GetBecVMSummaryResponse getBecVmSummary(GetBecVMSummaryRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, OVERVIEW, SUMMARY, VM_PREFIX);
        return invokeHttpClient(internalRequest, GetBecVMSummaryResponse.class);
    }

    /**
     * Get BEC user level container metrics.
     *
     * @param request: The request containing all options for getting BEC user level container metrics.
     * @return metrics data
     */
    public GetBecContainerMetricsResponse getBecContainerMetrics(GetBecContainerMetricsRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        checkNotNull(request.getOffsetInSeconds(), OFFSET_IN_SECONDS_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getType(), checkEmptyExceptionMessageFormat(METRICS_TYPE_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, MONITOR, OVERVIEW,
                CONTAINER, request.getType());
        if (StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("metricsType", request.getType());
        }
        if (request.getOffsetInSeconds() != null && request.getOffsetInSeconds() > 0) {
            // 获取整分数据
            long end = Calendar.getInstance().getTimeInMillis() / 1000;

            long start = end - request.getOffsetInSeconds();
            internalRequest.addParameter("start", String.valueOf(start));
            internalRequest.addParameter("end", String.valueOf(end));
        }
        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }
        if (request.getStepInMin() != null && request.getStepInMin() > 0) {
            internalRequest.addParameter("stepInMin", String.valueOf(request.getStepInMin()));
        }
        return invokeHttpClient(internalRequest, GetBecContainerMetricsResponse.class);
    }

    /**
     * Get BEC user level vm metrics.
     *
     * @param request: The request containing all options for getting BEC user level vm metrics.
     * @return metrics data
     */
    public GetBecVmMetricsResponse getBecVmMetrics(GetBecVmMetricsRequest request) {
        checkNotNull(request.getOffsetInSeconds(), OFFSET_IN_SECONDS_NULL_ERROR_MESSAGE);
        checkStringNotEmpty(request.getType(), checkEmptyExceptionMessageFormat(METRICS_TYPE_MESSAGE_KEY));
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, MONITOR,
                OVERVIEW, VM_PREFIX, request.getType());
        if (StringUtils.isNotEmpty(request.getType())) {
            internalRequest.addParameter("metricsType", request.getType());
        }
        if (request.getOffsetInSeconds() != null && request.getOffsetInSeconds() > 0) {
            // 获取整分数据
            long end = Calendar.getInstance().getTimeInMillis() / 1000;

            long start = end - request.getOffsetInSeconds();
            internalRequest.addParameter("start", String.valueOf(start));
            internalRequest.addParameter("end", String.valueOf(end));
        }
        if (StringUtils.isNotEmpty(request.getServiceProvider())) {
            internalRequest.addParameter("serviceProvider", request.getServiceProvider());
        }
        if (request.getStepInMin() != null && request.getStepInMin() > 0) {
            internalRequest.addParameter("stepInMin", String.valueOf(request.getStepInMin()));
        }
        return invokeHttpClient(internalRequest, GetBecVmMetricsResponse.class);
    }

    /**
     * List bec service packages.
     *
     * @param request: The request containing all options for listing bec service packages.
     * @return bec service packages data
     */
    public ListBecServicePackagesResponse listBecServicePackages(ListBecServicePackagesRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, RESOURCE, PACKAGE
            , request.getType());
        return invokeHttpClient(internalRequest, ListBecServicePackagesResponse.class);
    }

    /**
     * List bec passThrough disk packages.
     *
     * @param request: The request containing all options for listing bec passThrough disk packages.
     * @return bec passThrough disk packages data
     */
    public ListBecPassThroughDiskPackagesResponse listBecPassThroughDiskPackages
        (ListBecPassThroughDiskPackagesRequest request) {
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, RESOURCE, DISK
            , PASS_THROUGH);
        return invokeHttpClient(internalRequest, ListBecPassThroughDiskPackagesResponse.class);
    }


}

package com.baidubce.services.aihc;

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

import com.baidubce.services.aihc.model.inference.AppChangeDetailRequest;
import com.baidubce.services.aihc.model.inference.AppChangeDetailResponse;
import com.baidubce.services.aihc.model.inference.AppDetailsRequest;
import com.baidubce.services.aihc.model.inference.AppDetailsResponse;
import com.baidubce.services.aihc.model.inference.BlockPodRequest;
import com.baidubce.services.aihc.model.inference.BlockPodResponse;
import com.baidubce.services.aihc.model.inference.CreateAppRequest;
import com.baidubce.services.aihc.model.inference.CreateAppResponse;
import com.baidubce.services.aihc.model.inference.DeleteAppRequest;
import com.baidubce.services.aihc.model.inference.DeleteAppResponse;
import com.baidubce.services.aihc.model.inference.DeletePodRequest;
import com.baidubce.services.aihc.model.inference.DeletePodResponse;
import com.baidubce.services.aihc.model.inference.ListAppRequest;
import com.baidubce.services.aihc.model.inference.ListAppResponse;
import com.baidubce.services.aihc.model.inference.ListChangeRequest;
import com.baidubce.services.aihc.model.inference.ListChangeResponse;
import com.baidubce.services.aihc.model.inference.ListPodRequest;
import com.baidubce.services.aihc.model.inference.ListPodResponse;
import com.baidubce.services.aihc.model.inference.ListResPoolBriefRequest;
import com.baidubce.services.aihc.model.inference.ListResPoolBriefResponse;
import com.baidubce.services.aihc.model.inference.ListStatsRequest;
import com.baidubce.services.aihc.model.inference.ListStatsResponse;
import com.baidubce.services.aihc.model.inference.PubAccessRequest;
import com.baidubce.services.aihc.model.inference.PubAccessResponse;
import com.baidubce.services.aihc.model.inference.ResPoolDetailRequest;
import com.baidubce.services.aihc.model.inference.ResPoolDetailResponse;
import com.baidubce.services.aihc.model.inference.ScaleAppRequest;
import com.baidubce.services.aihc.model.inference.ScaleAppResponse;
import com.baidubce.services.aihc.model.inference.UpdateAppRequest;
import com.baidubce.services.aihc.model.inference.UpdateAppResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static com.baidubce.util.Validate.checkNotNull;
import static com.baidubce.util.Validate.checkStringNotEmpty;

public class AihcInferenceClient extends AbstractBceClient {

    /**
     * aihc inference API pathVersion
     */
    private static final String APPPREFIX = "api/aihcpom/app";
    private static final String PODPREFIX = "api/aihcpom/pod";
    private static final String RESPOOLPREFIX = "api/aihcpom/respool";

    private static final String VERSION = "v1";

    /**
     * Responsible for handling httpResponses from all service calls.
     */
    private static HttpResponseHandler[] aihcInferenceHandlers = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public AihcInferenceClient() {
        this(new BceClientConfiguration());
    }

    /**
     * Constructs a new InstanceClient to invoke service methods on eip instance.
     *
     * @param clientConfiguration The BCE client configuration options.
     */
    public AihcInferenceClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, aihcInferenceHandlers);
    }

    /**
     * the method to fill the internalRequest's content field with bceRequest
     * only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     * any additional headers or parameters, and execute.
     * @param bceRequest The original request, as created by the user.
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
     * Creates and initializes a new request object for the specified resource.
     *
     * @param bceRequest The original BCE request created by the user.
     * @param httpMethod The HTTP method to use when sending the request.
     * @param region The region.
     * @param prefix The prefix of URI.
     * @param pathVariables The optional variables used in the URI path.
     * @return A new request object populated with endpoint, resource path and specific
     *         parameters to send.
     */
    private InternalRequest createRequest(
            AbstractBceRequest bceRequest, HttpMethodName httpMethod, String region, String prefix, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        path.add(prefix);
        path.add(VERSION);

        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                path.add(pathVariable);
            }
        }

        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[path.size()]));
        InternalRequest request = new InternalRequest(httpMethod, uri);
        request.setCredentials(bceRequest.getRequestCredentials());
        request.addHeader("X-Region", region);

        return request;
    }

    /**
     * Create an app with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for creating an app.
     * @return created appId
     */
    public CreateAppResponse createApp(CreateAppRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, region, APPPREFIX, "create");
        checkNotNull(request.getAppName(), "appName should not be null");
        checkNotNull(request.getChipType(), "chipType should not be null");
        if (request.getInsCount() <= 0) {
            throw new IllegalArgumentException("insCount should be > 0");
        }
        checkNotNull(request.getResPool(), "resPool should not be null");
        checkNotNull(request.getContainers(), "containers should not be null");
        checkNotNull(request.getAccess(), "access should not be null");
        checkNotNull(request.getLog(), "log should not be null");
        checkNotNull(request.getDeploy(), "deploy should not be null");

        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, CreateAppResponse.class);
    }

    /**
     * List apps with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for list an app.
     * @return apps info
     */
    public ListAppResponse listApp(ListAppRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, region, APPPREFIX, "list");
        if (request.getKeyword() != null) {
            internalRequest.addParameter("keyword", request.getKeyword());
        }
        if (request.getPageNo() < 0) {
            throw new IllegalArgumentException("pageNo should be > 0");
        }
        if (request.getPageNo() > 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() < 0) {
            throw new IllegalArgumentException("pageSize should be > 0");
        }
        if (request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        if (request.getOrderBy() != null && request.getOrder() != null) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
            internalRequest.addParameter("order", request.getOrder());
        }

        return invokeHttpClient(internalRequest, ListAppResponse.class);
    }

    /**
     * List app stats with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for list app stats.
     * @return apps stats info
     */
    public ListStatsResponse listStats(ListStatsRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, region, APPPREFIX, "stats");
        if (request.getAppIds().isEmpty()) {
            throw new IllegalArgumentException("appId should not be null");
        }

        String appIds = String.join("#", request.getAppIds());
        internalRequest.addParameter("appIds", appIds);
        return invokeHttpClient(internalRequest, ListStatsResponse.class);
    }

    /**
     * The details of app with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for app details.
     * @return app details info
     */
    public AppDetailsResponse appDetails(AppDetailsRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, region, APPPREFIX, "details");
        checkStringNotEmpty(request.getAppId(), "appId should not be empty");
        internalRequest.addParameter("appId", request.getAppId());
        return invokeHttpClient(internalRequest, AppDetailsResponse.class);
    }

    /**
     * Update app with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for update app.
     * @return app Id
     */
    public UpdateAppResponse updateApp(UpdateAppRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, region, APPPREFIX, "update");
        checkNotNull(request.getAppId(), "appId should not be null");

        internalRequest.addParameter("appId", request.getAppId());
        if (request.getShortDesc() != null) {
            internalRequest.addParameter("shortDesc", request.getShortDesc());
        }
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, UpdateAppResponse.class);
    }

    /**
     * Scale app with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for scale app.
     * @return
     */
    public ScaleAppResponse scaleApp(ScaleAppRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, region, APPPREFIX, "scale");
        checkNotNull(request.getAppId(), "appId should not be null");
        if (request.getInsCount() < 0) {
            throw new IllegalArgumentException("insCount should be >= 0");
        }

        internalRequest.addParameter("appId", request.getAppId());
        internalRequest.addParameter("insCount", String.valueOf(request.getInsCount()));
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, ScaleAppResponse.class);
    }

    /**
     * Operate the public network with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for operate the public network.
     * @return
     */
    public PubAccessResponse pubAccess(PubAccessRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, region, APPPREFIX, "pubaccess");
        checkNotNull(request.getAppId(), "appId should not be null");
        internalRequest.addParameter("appId", request.getAppId());
        internalRequest.addParameter("publicAccess", String.valueOf(request.isPublicAccess()));
        if (request.getEip() != null) {
            internalRequest.addParameter("eip", request.getEip());
        }

        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, PubAccessResponse.class);
    }

    /**
     * List change with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for list change.
     * @return change list info
     */
    public ListChangeResponse listChange(ListChangeRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, region, APPPREFIX, "listchange");
        checkNotNull(request.getAppId(), "appId should not be null");
        internalRequest.addParameter("appId", request.getAppId());
        internalRequest.addParameter("changeType", String.valueOf(request.getChangeType()));

        if (request.getOrder() != null) {
            internalRequest.addParameter("order", request.getOrder());
        }
        if (request.getPageNo() < 0) {
            throw new IllegalArgumentException("pageNo should be > 0");
        }
        if (request.getPageNo() > 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() < 0) {
            throw new IllegalArgumentException("pageSize should be > 0");
        }
        if (request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }
        if (request.getOrderBy() != null && request.getOrder() != null) {
            internalRequest.addParameter("orderBy", request.getOrderBy());
            internalRequest.addParameter("order", request.getOrder());
        }

        return invokeHttpClient(internalRequest, ListChangeResponse.class);
    }

    /**
     * change detail with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for change detail.
     * @return  change detail info
     */
    public AppChangeDetailResponse appChangeDetail(AppChangeDetailRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, region, APPPREFIX, "changedetail");
        checkNotNull(request.getChangeId(), "changeId should not be null");
        internalRequest.addParameter("changeId", request.getChangeId());

        return invokeHttpClient(internalRequest, AppChangeDetailResponse.class);
    }

    /**
     * delete app with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for delete app.
     * @return
     */
    public DeleteAppResponse deleteApp(DeleteAppRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, region, APPPREFIX, "delete");
        checkNotNull(request.getAppId(), "appId should not be null");

        internalRequest.addParameter("appId", request.getAppId());
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, DeleteAppResponse.class);
    }

    /**
     * List pod with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for list pod.
     * @return pod info
     */
    public ListPodResponse listPod(ListPodRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, region, PODPREFIX, "list");
        checkNotNull(request.getAppId(), "appId should not be null");
        internalRequest.addParameter("appId", request.getAppId());

        return invokeHttpClient(internalRequest, ListPodResponse.class);
    }

    /**
     * block pod with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for block pod.
     * @return
     */
    public BlockPodResponse blockPod(BlockPodRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, region, PODPREFIX, "block");
        checkNotNull(request.getAppId(), "appId should not be null");
        checkNotNull(request.getInsID(), "insID should not be null");

        internalRequest.addParameter("appId", request.getAppId());
        internalRequest.addParameter("insID", request.getInsID());
        internalRequest.addParameter("block", String.valueOf(request.isBlock()));

        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, BlockPodResponse.class);
    }

    /**
     * delete pod with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for delete pod.
     * @return
     */
    public DeletePodResponse deletePod(DeletePodRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.POST, region, PODPREFIX, "delete");
        checkNotNull(request.getAppId(), "appId should not be null");
        checkNotNull(request.getInsID(), "insID should not be null");

        internalRequest.addParameter("appId", request.getAppId());
        internalRequest.addParameter("insID", request.getInsID());
        fillPayload(internalRequest, request);

        return invokeHttpClient(internalRequest, DeletePodResponse.class);
    }

    /**
     * list resPool brief info with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for list resPool brief info.
     * @return list resPool brief info
     */
    public ListResPoolBriefResponse listResPoolBrief(ListResPoolBriefRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, region, RESPOOLPREFIX, "listbrief");
        if (request.getPageNo() < 0) {
            throw new IllegalArgumentException("pageNo should be > 0");
        }
        if (request.getPageNo() > 0) {
            internalRequest.addParameter("pageNo", String.valueOf(request.getPageNo()));
        }
        if (request.getPageSize() < 0) {
            throw new IllegalArgumentException("pageSize should be > 0");
        }
        if (request.getPageSize() > 0) {
            internalRequest.addParameter("pageSize", String.valueOf(request.getPageSize()));
        }

        return invokeHttpClient(internalRequest, ListResPoolBriefResponse.class);
    }

    /**
     * list resPool brief info with the specified options.
     * This is an asynchronous interface
     *
     * @param request The request containing all options for list resPool brief info.
     * @return list resPool brief info
     */
    public ResPoolDetailResponse resPoolDetail(ResPoolDetailRequest request, String region) {
        InternalRequest internalRequest = this.createRequest(request, HttpMethodName.GET, region, RESPOOLPREFIX, "detail");
        checkNotNull(request.getResPoolId(), "resPoolId should not be null");

        internalRequest.addParameter("resPoolId", request.getResPoolId());

        return invokeHttpClient(internalRequest, ResPoolDetailResponse.class);
    }
}

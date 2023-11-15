/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.bvw;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bvw.model.common.ListByPageResponse;
import com.baidubce.services.bvw.model.keyframe.KeyFrameDescAddRequest;
import com.baidubce.services.bvw.model.keyframe.KeyFrameDescGetResponse;
import com.baidubce.services.bvw.model.keyframe.KeyFrameUrlResponse;
import com.baidubce.services.bvw.model.matlib.DraftListRequest;
import com.baidubce.services.bvw.model.matlib.GetDraftRequest;
import com.baidubce.services.bvw.model.matlib.GetDraftResponse;
import com.baidubce.services.bvw.model.matlib.MaterialBaseResponse;
import com.baidubce.services.bvw.model.matlib.MaterialGetRequest;
import com.baidubce.services.bvw.model.matlib.MaterialGetResponse;
import com.baidubce.services.bvw.model.matlib.MaterialPresetGetRequest;
import com.baidubce.services.bvw.model.matlib.MaterialPresetGetResponse;
import com.baidubce.services.bvw.model.matlib.MaterialPresetSearchRequest;
import com.baidubce.services.bvw.model.matlib.MaterialPresetSearchResponse;
import com.baidubce.services.bvw.model.matlib.MaterialPresetUploadResponse;
import com.baidubce.services.bvw.model.matlib.MaterialSearchRequest;
import com.baidubce.services.bvw.model.matlib.MaterialSearchResponse;
import com.baidubce.services.bvw.model.matlib.MatlibConfigBaseRequest;
import com.baidubce.services.bvw.model.matlib.MatlibConfigBaseResponse;
import com.baidubce.services.bvw.model.matlib.MatlibConfigGetResponse;
import com.baidubce.services.bvw.model.matlib.MatlibTaskGetResponse;
import com.baidubce.services.bvw.model.matlib.MatlibTaskRequest;
import com.baidubce.services.bvw.model.matlib.MatlibTaskResponse;
import com.baidubce.services.bvw.model.matlib.MatlibUploadRequest;
import com.baidubce.services.bvw.model.matlib.MatlibUploadResponse;
import com.baidubce.services.bvw.model.matlib.Text2AudioRequest;
import com.baidubce.services.bvw.model.matlib.Text2AudioResponse;
import com.baidubce.services.bvw.model.matlib.VideoGenerationRequest;
import com.baidubce.services.bvw.model.matlib.VideoGenerationResponse;
import com.baidubce.services.bvw.model.media.MediaBaseRequest;
import com.baidubce.services.bvw.model.media.MediaBaseResponse;
import com.baidubce.services.bvw.model.media.MediaBatchDeleteRequest;
import com.baidubce.services.bvw.model.media.MediaGetResponse;
import com.baidubce.services.bvw.model.media.MediaInstanceListResponse;
import com.baidubce.services.bvw.model.media.MediaListRequest;
import com.baidubce.services.bvw.model.media.MediaListResponse;
import com.baidubce.services.bvw.model.media.MediaProcessRequest;
import com.baidubce.services.bvw.model.media.MediaProcessResponse;
import com.baidubce.services.bvw.model.media.MediaUpdateRequest;
import com.baidubce.services.bvw.model.notification.AuthType;
import com.baidubce.services.bvw.model.notification.NotificationBaseRequest;
import com.baidubce.services.bvw.model.notification.NotificationBaseResponse;
import com.baidubce.services.bvw.model.notification.NotificationCreateRequest;
import com.baidubce.services.bvw.model.notification.NotificationGetResponse;
import com.baidubce.services.bvw.model.notification.NotificationListRequest;
import com.baidubce.services.bvw.model.notification.NotificationListResponse;
import com.baidubce.services.bvw.model.notification.NotificationStatus;
import com.baidubce.services.bvw.model.notification.NotificationUpdateRequest;
import com.baidubce.services.bvw.model.videoedit.VideoEditCreateRequest;
import com.baidubce.services.bvw.model.videoedit.VideoEditCreateResponse;
import com.baidubce.services.bvw.model.videoedit.VideoEditPollingRequest;
import com.baidubce.services.bvw.model.videoedit.VideoEditPollingResponse;
import com.baidubce.services.bvw.model.workflow.WorkflowBaseRequest;
import com.baidubce.services.bvw.model.workflow.WorkflowBaseResponse;
import com.baidubce.services.bvw.model.workflow.WorkflowCreateRequest;
import com.baidubce.services.bvw.model.workflow.WorkflowGetResponse;
import com.baidubce.services.bvw.model.workflow.WorkflowListRequest;
import com.baidubce.services.bvw.model.workflow.WorkflowListResponse;
import com.baidubce.services.bvw.model.workflow.WorkflowUpdateRequest;
import com.baidubce.services.bvw.model.workflow.instance.InstanceBaseRequest;
import com.baidubce.services.bvw.model.workflow.instance.InstanceGetResponse;
import com.baidubce.services.bvw.model.workflow.instance.InstanceGetTaskUrlsResponse;
import com.baidubce.services.bvw.model.workflow.task.TaskBaseRequest;
import com.baidubce.services.bvw.model.workflow.task.TaskGetResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides the client for accessing the Baidu VideoWorks(BVW).
 */
public class BvwClient extends AbstractBceClient {

    /**
     * The request paths.
     */
    private static final String VERSION = "v1";
    private static final String WORKFLOW = "workflow";
    private static final String MEDIA = "media";
    private static final String INSTANCE = "instance";
    private static final String TASK = "task";
    private static final String NOTIFICATION = "notification";
    private static final String MATLIB = "matlib";
    private static final String MATERIAL_LIBRARY = "materialLibrary";
    private static final String MATERIAL_LIBRARY_PRESET = "preset";
    private static final String MATLIB_VIDEO_GENERATION = "videoGenerationTask";
    private static final String MATLIB_TIMELINE_RESOURCE = "draftAndTimeline";
    private static final String VIDEO_EDIT = "videoEdit";
    private static final String VIDEO_EDIT_CREATE = "createNewVideo";
    private static final String VIDEO_EDIT_POLLING = "pollingVideo";

    /**
     * The request queries.
     */
    private static final String LIST_PAGE_NO = "pageNo";
    private static final String LIST_PAGE_SIZE = "pageSize";
    private static final String LIST_BEGIN_TIME = "beginTime";
    private static final String LIST_END_TIME = "endTime";
    private static final String WORKFLOW_LIST_STATUS = "status";
    private static final String WORKFLOW_LIST_NAME = "name";
    private static final String WORKFLOW_ENABLE = "enable";
    private static final String WORKFLOW_DISABLE = "disable";
    private static final String MEDIA_PROCESS = "process";
    private static final String MEDIA_INSTANCES = "queryMediaInstanceList";
    private static final String MEDIA_BAN = "ban";
    private static final String MEDIA_UNBAN = "unban";
    private static final String MEDIA_LIST_STATUS = "status";
    private static final String MEDIA_LIST_INSTANCE_STATUS = "instanceStatus";
    private static final String MEDIA_LIST_MEDIA_ID = "mediaId";
    private static final String MEDIA_LIST_TITLE = "title";
    private static final String MEDIA_LIST_ORDER = "order";
    private static final String MEDIA_LIST_ORDER_BY = "orderBy";
    private static final String MEDIA_BATCH_DELETE = "mediaIds";
    private static final String INSTANCE_TASK_URLS = "queryStageTaskUrlList";
    private static final String INSTANCE_KEYFRAME_DESC_URLS = "queryKeyFrameDescList";
    private static final String INSTANCE_KEYFRAME_URLS = "queryKeyFrameUrlList";
    private static final String INSTANCE_KEYFRAME_ADD = "addKeyFrameDesc";
    private static final String INSTANCE_PRE_SIGN_FLAG = "preSign";
    private static final String NOTIFICATION_LIST_STATUS = "status";
    private static final String NOTIFICATION_ENABLE = "enable";
    private static final String NOTIFICATION_DISABLE = "disable";
    private static final String MATLIB_UPLOAD = "upload";
    private static final String MATLIB_CONFIG = "config";
    private static final String MATLIB_SPEECH = "speech";
    private static final String MATERIAL_TITLE_KEYWORD = "titleKeyword";
    private static final String MATERIAL_PRESET_TYPE = "type";
    private static final String MATERIAL_SOURCE_TYPE = "sourceType";
    private static final String MATERIAL_INFO_TYPE = "infoType";
    private static final String MATERIAL_MEDIA_TYPE = "mediaType";
    private static final String MATERIAL_STATUS = "status";
    private static final String MATERIAL_CREATETIME_BEGIN = "begin";
    private static final String MATERIAL_CREATETIME_END = "end";
    private static final String MATERIAL_PAGENO = "pageNo";
    private static final String MATERIAL_SIZE = "size";
    private static final String MATERIAL_PAGESIZE = "pageSize";
    private static final String MATLIB_DRAFT_TIMELINE = "draftAndTimeline";

    /**
     * Responsible for handling httpResponses from all Bos service calls.
     */
    private static final HttpResponseHandler[] BVW_HANDLES = new HttpResponseHandler[]{
            new BceMetadataResponseHandler(),
            new BvwMetadataResponseHandler(),
            new BvwErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    /**
     * Constructs a new bvw client using the client configuration to access bvw.
     *
     * @param config The bvw client configuration options controlling how this client
     *               connects to bvw (e.g. proxy settings, retry counts, etc).
     */
    public BvwClient(BceClientConfiguration config) {
        super(config, BVW_HANDLES, true);
    }

    /**
     * Creates and initializes a new request object for the specified bcc resource. This method is responsible
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
        if (httpMethod == HttpMethodName.POST
                || httpMethod == HttpMethodName.PUT) {
            fillPayload(request, bceRequest);
        }
        return request;
    }

    /**
     * The method to fill the internalRequest's content field with bceRequest.
     * Only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object, populated with endpoint, resource path, ready for callers to populate
     *                        any additional headers or parameters, and execute.
     * @param bceRequest      The original request, as created by the user.
     */
    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT) {
            String strJson = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson;
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
     * Create workflow with specified parameter.
     *
     * @param workflowCreateRequest The creating workflow request
     * @return A creating workflow response
     */
    public WorkflowBaseResponse createWorkflow(WorkflowCreateRequest workflowCreateRequest) {
        InternalRequest request = this.createRequest(workflowCreateRequest, HttpMethodName.POST, WORKFLOW);
        return this.invokeHttpClient(request, WorkflowBaseResponse.class);
    }

    /**
     * Get workflow with specified workflow name.
     *
     * @param workflowName The workflow name
     * @return A getting workflow response
     */
    public WorkflowGetResponse getWorkflow(String workflowName) {
        WorkflowBaseRequest getRequest = WorkflowBaseRequest.of(workflowName);
        return this.getWorkflow(getRequest);
    }

    /**
     * Get workflow with specified getting workflow request.
     *
     * @param getRequest The getting workflow request
     * @return A getting workflow response
     */
    public WorkflowGetResponse getWorkflow(WorkflowBaseRequest getRequest) {
        InternalRequest request = this.createRequest(getRequest, HttpMethodName.GET, WORKFLOW,
                                                     getRequest.getName());
        return this.invokeHttpClient(request, WorkflowGetResponse.class);
    }

    /**
     * Delete workflow with specified workflow name.
     *
     * @param workflowName The workflow name
     * @return A deleting workflow response
     */
    public WorkflowBaseResponse deleteWorkflow(String workflowName) {
        WorkflowBaseRequest deleteRequest = WorkflowBaseRequest.of(workflowName);
        return this.deleteWorkflow(deleteRequest);
    }

    /**
     * Get workflow with specified deleting workflow request.
     *
     * @param deleteRequest The deleting workflow request
     * @return A deleting workflow response
     */
    public WorkflowBaseResponse deleteWorkflow(WorkflowBaseRequest deleteRequest) {
        InternalRequest request = this.createRequest(deleteRequest, HttpMethodName.DELETE, WORKFLOW,
                                                     deleteRequest.getName());
        return this.invokeHttpClient(request, WorkflowBaseResponse.class);
    }

    /**
     * Update workflow with specified updating workflow request.
     *
     * @param updateRequest The updating workflow request
     * @return A updating workflow response
     */
    public WorkflowBaseResponse updateWorkflow(WorkflowUpdateRequest updateRequest) {
        InternalRequest request = this.createRequest(updateRequest, HttpMethodName.PUT, WORKFLOW,
                                                     updateRequest.getName());
        return this.invokeHttpClient(request, WorkflowBaseResponse.class);
    }

    /**
     * List workflow with specified listing workflow request.
     *
     * @param listRequest The listing workflow request
     * @return A listing workflow response
     */
    public ListByPageResponse<WorkflowListResponse> listWorkflow(WorkflowListRequest listRequest) {
        InternalRequest request = this.createRequest(listRequest, HttpMethodName.GET, WORKFLOW);
        request.addParameter(LIST_PAGE_NO, String.valueOf(listRequest.getPageNo()));
        request.addParameter(LIST_PAGE_SIZE, String.valueOf(listRequest.getPageSize()));
        if (null != listRequest.getStatus()) {
            request.addParameter(WORKFLOW_LIST_STATUS, String.valueOf(listRequest.getStatus()));
        }
        if (StringUtils.isNotBlank(listRequest.getBeginTime())) {
            request.addParameter(LIST_BEGIN_TIME, listRequest.getBeginTime());
        }
        if (StringUtils.isNotBlank(listRequest.getEndTime())) {
            request.addParameter(LIST_END_TIME, listRequest.getEndTime());
        }
        if (StringUtils.isNotBlank(listRequest.getName())) {
            request.addParameter(WORKFLOW_LIST_NAME, listRequest.getName());
        }
        return this.invokeHttpClient(request, ListByPageResponse.class);
    }

    /**
     * Enable workflow with specified enabling workflow request.
     *
     * @param enableRequest Enabling workflow request
     * @return A enabling workflow response
     */
    public WorkflowBaseResponse enableWorkflow(WorkflowBaseRequest enableRequest) {
        InternalRequest request = this.createRequest(enableRequest, HttpMethodName.PUT, WORKFLOW,
                                                     enableRequest.getName());
        request.addParameter(WORKFLOW_ENABLE, null);
        return this.invokeHttpClient(request, WorkflowBaseResponse.class);
    }

    /**
     * Enable workflow with specified workflow name.
     *
     * @param workflowName Workflow name
     * @return A enabling workflow response
     */
    public WorkflowBaseResponse enableWorkflow(String workflowName) {
        WorkflowBaseRequest enableRequest = WorkflowBaseRequest.of(workflowName);
        return enableWorkflow(enableRequest);
    }

    /**
     * Disable workflow with specified enabling workflow request.
     *
     * @param disableRequest Disabling workflow request
     * @return A disabling workflow response
     */
    public WorkflowBaseResponse disableWorkflow(WorkflowBaseRequest disableRequest) {
        InternalRequest request = this.createRequest(disableRequest, HttpMethodName.PUT, WORKFLOW,
                                                     disableRequest.getName());
        request.addParameter(WORKFLOW_DISABLE, null);
        return this.invokeHttpClient(request, WorkflowBaseResponse.class);
    }

    /**
     * Disable workflow with specified workflow name.
     *
     * @param workflowName Workflow name
     * @return A disabling workflow response
     */
    public WorkflowBaseResponse disableWorkflow(String workflowName) {
        WorkflowBaseRequest disableRequest = WorkflowBaseRequest.of(workflowName);
        return disableWorkflow(disableRequest);
    }

    /**
     * Process media with specified processing media request.
     *
     * @param processRequest processing media request
     * @return A processing media response
     */
    public MediaProcessResponse processMedia(MediaProcessRequest processRequest) {
        InternalRequest request = this.createRequest(processRequest, HttpMethodName.POST, MEDIA);
        request.addParameter(MEDIA_PROCESS, null);
        return this.invokeHttpClient(request, MediaProcessResponse.class);
    }

    /**
     * Get media with specified getting media request.
     *
     * @param getRequest getting media request
     * @return A getting media response
     */
    public MediaGetResponse getMedia(MediaBaseRequest getRequest) {
        InternalRequest request = this.createRequest(getRequest, HttpMethodName.GET, MEDIA, getRequest.getMediaId());
        return this.invokeHttpClient(request, MediaGetResponse.class);
    }

    /**
     * Get media with specified media id.
     *
     * @param mediaId Media id
     * @return A getting media response
     */
    public MediaGetResponse getMedia(String mediaId) {
        MediaBaseRequest getRequest = MediaBaseRequest.of(mediaId);
        return getMedia(getRequest);
    }

    /**
     * Update media with specified updating media request.
     *
     * @param updateRequest The updating media request
     * @return A updating media response
     */
    public MediaBaseResponse updateMedia(MediaUpdateRequest updateRequest) {
        InternalRequest request = this.createRequest(updateRequest, HttpMethodName.PUT, MEDIA,
                                                     updateRequest.getMediaId());
        return this.invokeHttpClient(request, MediaBaseResponse.class);
    }

    /**
     * Get instance list of media.
     *
     * @param getRequest The getting instance list of media request
     * @return A getting instance list of media response
     */
    public MediaInstanceListResponse getMediaInstanceList(MediaBaseRequest getRequest) {
        InternalRequest request = this.createRequest(getRequest, HttpMethodName.GET, MEDIA, getRequest.getMediaId());
        request.addParameter(MEDIA_INSTANCES, null);
        return this.invokeHttpClient(request, MediaInstanceListResponse.class);
    }

    /**
     * Get instance list of media.
     *
     * @param mediaId The media id
     * @return A getting instance list of media response
     */
    public MediaInstanceListResponse getMediaInstanceList(String mediaId) {
        MediaBaseRequest getRequest = MediaBaseRequest.of(mediaId);
        return getMediaInstanceList(getRequest);
    }

    /**
     * Ban media with specified banning media request.
     *
     * @param banRequest The banning media request
     * @return A banning media response
     */
    public MediaBaseResponse banMedia(MediaBaseRequest banRequest) {
        InternalRequest request = this.createRequest(banRequest, HttpMethodName.PUT, MEDIA, banRequest.getMediaId());
        request.addParameter(MEDIA_BAN, null);
        return this.invokeHttpClient(request, MediaBaseResponse.class);
    }

    /**
     * Ban media with specified media id.
     *
     * @param mediaId The media id
     * @return A banning media response
     */
    public MediaBaseResponse banMedia(String mediaId) {
        MediaBaseRequest banRequest = MediaBaseRequest.of(mediaId);
        return banMedia(banRequest);
    }

    /**
     * Unban media with specified unbanning media request.
     *
     * @param unbanRequest The unbanning media request
     * @return A unbanning media response
     */
    public MediaBaseResponse unbanMedia(MediaBaseRequest unbanRequest) {
        InternalRequest request = this
                .createRequest(unbanRequest, HttpMethodName.PUT, MEDIA, unbanRequest.getMediaId());
        request.addParameter(MEDIA_UNBAN, null);
        return this.invokeHttpClient(request, MediaBaseResponse.class);
    }

    /**
     * Unban media with specified media id.
     *
     * @param mediaId The media id
     * @return A unbanning media response
     */
    public MediaBaseResponse unbanMedia(String mediaId) {
        MediaBaseRequest unbanRequest = MediaBaseRequest.of(mediaId);
        return unbanMedia(unbanRequest);
    }

    /**
     * List media with specified listing media request.
     *
     * @param listRequest The listing media request
     * @return A listing media response
     */
    public ListByPageResponse<MediaListResponse> listMedia(MediaListRequest listRequest) {
        InternalRequest request = this.createRequest(listRequest, HttpMethodName.GET, MEDIA);
        request.addParameter(LIST_PAGE_NO, String.valueOf(listRequest.getPageNo()));
        request.addParameter(LIST_PAGE_SIZE, String.valueOf(listRequest.getPageSize()));
        if (StringUtils.isNotBlank(listRequest.getBeginTime())) {
            request.addParameter(LIST_BEGIN_TIME, listRequest.getBeginTime());
        }
        if (StringUtils.isNotBlank(listRequest.getEndTime())) {
            request.addParameter(LIST_END_TIME, listRequest.getEndTime());
        }
        if (null != listRequest.getStatus()) {
            request.addParameter(MEDIA_LIST_STATUS, String.valueOf(listRequest.getStatus()));
        }
        if (null != listRequest.getInstanceStatus()) {
            request.addParameter(MEDIA_LIST_INSTANCE_STATUS, String.valueOf(listRequest.getInstanceStatus()));
        }
        if (StringUtils.isNotBlank(listRequest.getMediaId())) {
            request.addParameter(MEDIA_LIST_MEDIA_ID, listRequest.getMediaId());
        }
        if (StringUtils.isNotBlank(listRequest.getTitle())) {
            request.addParameter(MEDIA_LIST_TITLE, listRequest.getTitle());
        }
        if (StringUtils.isNotBlank(listRequest.getOrder()) && StringUtils.isNotBlank(listRequest.getOrderBy())) {
            request.addParameter(MEDIA_LIST_ORDER, listRequest.getOrder());
            request.addParameter(MEDIA_LIST_ORDER_BY, listRequest.getOrderBy());
        }
        return this.invokeHttpClient(request, ListByPageResponse.class);
    }

    /**
     * Delete media with specified deleting media request.
     *
     * @param deleteRequest The deleting media request
     * @return A deleting media response
     */
    public MediaBaseResponse deleteMedia(MediaBaseRequest deleteRequest) {
        InternalRequest request = this
                .createRequest(deleteRequest, HttpMethodName.DELETE, MEDIA, deleteRequest.getMediaId());
        return this.invokeHttpClient(request, MediaBaseResponse.class);
    }

    /**
     * Delete media with specified media id.
     *
     * @param mediaId The media id
     * @return A deleting media response
     */
    public MediaBaseResponse deleteMedia(String mediaId) {
        MediaBaseRequest deleteRequest = MediaBaseRequest.of(mediaId);
        return deleteMedia(deleteRequest);
    }

    /**
     * Batch delete media with specified batch deleting media request.
     *
     * @param batchDeleteRequest The batch deleting media request
     * @return A batch deleting media response
     */
    public MediaBaseResponse batchDeleteMedia(MediaBatchDeleteRequest batchDeleteRequest) {
        InternalRequest request = this.createRequest(batchDeleteRequest, HttpMethodName.DELETE, MEDIA);
        if (StringUtils.isNotBlank(batchDeleteRequest.getMediaIds())) {
            request.addParameter(MEDIA_BATCH_DELETE, batchDeleteRequest.getMediaIds());
        }
        return this.invokeHttpClient(request, MediaBaseResponse.class);
    }

    /**
     * Batch delete media with specified media id list.
     *
     * @param mediaIds The media id list
     * @return A batch deleting media response
     */
    public MediaBaseResponse batchDeleteMedia(List<String> mediaIds) {
        MediaBatchDeleteRequest batchDeleteRequest = MediaBatchDeleteRequest.of(mediaIds);
        return batchDeleteMedia(batchDeleteRequest);
    }

    /**
     * Get instance with specified getting instance request.
     *
     * @param getRequest The getting instance request
     * @return A getting instance response
     */
    public InstanceGetResponse getInstance(InstanceBaseRequest getRequest) {
        InternalRequest request = this
                .createRequest(getRequest, HttpMethodName.GET, INSTANCE, getRequest.getInstanceId());
        return this.invokeHttpClient(request, InstanceGetResponse.class);
    }

    /**
     * Get instance with specified instance id.
     *
     * @param instanceId The instance id
     * @return A getting instance response
     */
    public InstanceGetResponse getInstance(String instanceId) {
        InstanceBaseRequest getRequest = InstanceBaseRequest.of(instanceId);
        return getInstance(getRequest);
    }

    /**
     * Get url resource from a instance with specified getting request.
     *
     * @param getRequest The getting instance task urls request
     * @return A getting instance task urls response
     */
    public InstanceGetTaskUrlsResponse getInstanceTaskUrls(InstanceBaseRequest getRequest) {
        InternalRequest request = this
                .createRequest(getRequest, HttpMethodName.GET, INSTANCE, getRequest.getInstanceId());
        request.addParameter(INSTANCE_TASK_URLS, null);
        return this.invokeHttpClient(request, InstanceGetTaskUrlsResponse.class);
    }

    /**
     * Get url resource from a instance with specified instance id.
     *
     * @param instanceId The instance id
     * @return A getting instance task urls response
     */
    public InstanceGetTaskUrlsResponse getInstanceTaskUrls(String instanceId) {
        InstanceBaseRequest getRequest = InstanceBaseRequest.of(instanceId);
        return getInstanceTaskUrls(getRequest);
    }

    /**
     * Get task with specified getting task request.
     *
     * @param getRequest The getting task request
     * @return A getting task request
     */
    public TaskGetResponse getTask(TaskBaseRequest getRequest) {
        InternalRequest request = this
                .createRequest(getRequest, HttpMethodName.GET, TASK, getRequest.getTaskId());
        return this.invokeHttpClient(request, TaskGetResponse.class);
    }

    /**
     * Get task with specified task id.
     *
     * @param taskId The task id
     * @return A getting task request
     */
    public TaskGetResponse getTask(String taskId) {
        TaskBaseRequest getRequest = TaskBaseRequest.of(taskId);
        return getTask(getRequest);
    }

    /**
     * Create notification with specified creating notification request.
     *
     * @param createRequest The creating notification request
     * @return A creating notification response
     */
    public NotificationBaseResponse createNotification(NotificationCreateRequest createRequest) {
        InternalRequest request = this.createRequest(createRequest, HttpMethodName.POST, NOTIFICATION);
        return this.invokeHttpClient(request, NotificationBaseResponse.class);
    }

    /**
     * Create notification with specified parameters.(No authentication)
     *
     * @param name     The notification name
     * @param endpoint The notification endpoint
     * @return A creating notification response
     */
    public NotificationBaseResponse createNotification(String name, String endpoint) {
        NotificationCreateRequest createRequest = NotificationCreateRequest.of(name, endpoint);
        return createNotification(createRequest);
    }

    /**
     * Create notification with specified parameters.
     * @param name     The notification name
     * @param endpoint The notification endpoint
     * @param token    The notification authentication token
     * @param authType The notification auth type
     * @return A creating notification response
     */
    public NotificationBaseResponse createNotification(String name, String endpoint, String token, AuthType authType) {
        NotificationCreateRequest createRequest = NotificationCreateRequest.of(name, endpoint, token, authType);
        return createNotification(createRequest);
    }

    /**
     * Get notification with specified parameters.
     *
     * @param getRequest The getting notification request
     * @return A getting notification response
     */
    public NotificationGetResponse getNotification(NotificationBaseRequest getRequest) {
        InternalRequest request = this
                .createRequest(getRequest, HttpMethodName.GET, NOTIFICATION, getRequest.getName());
        return this.invokeHttpClient(request, NotificationGetResponse.class);
    }

    /**
     * Get notification with specified notification name.
     *
     * @param name The notification name
     * @return A getting notification response
     */
    public NotificationGetResponse getNotification(String name) {
        NotificationBaseRequest getRequest = NotificationBaseRequest.of(name);
        return getNotification(getRequest);
    }

    /**
     * List notification with specified listing request.
     *
     * @param listRequest The listing notification request.
     * @return A listing notification response.
     */
    public NotificationListResponse listNotification(NotificationListRequest listRequest) {
        InternalRequest request = this.createRequest(listRequest, HttpMethodName.GET, NOTIFICATION);
        if (null != listRequest.getStatus()) {
            request.addParameter(NOTIFICATION_LIST_STATUS, String.valueOf(listRequest.getStatus()));
        }
        return this.invokeHttpClient(request, NotificationListResponse.class);
    }

    /**
     * List notification with specified notification status.
     *
     * @param status The notification status
     * @return A listing notification response.
     */
    public NotificationListResponse listNotification(NotificationStatus status) {
        NotificationListRequest listRequest = NotificationListRequest.of(status);
        return listNotification(listRequest);
    }

    /**
     * List all of the notifications.
     *
     * @return A listing notification response.
     */
    public NotificationListResponse listNotification() {
        NotificationListRequest listRequest = NotificationListRequest.of(null);
        return listNotification(listRequest);
    }

    /**
     * Update notification with specified updating request.
     *
     * @param updateRequest The updating notification request
     * @return A updating notification response
     */
    public NotificationBaseResponse updateNotification(NotificationUpdateRequest updateRequest) {
        InternalRequest request = this
                .createRequest(updateRequest, HttpMethodName.PUT, NOTIFICATION, updateRequest.getName());
        return this.invokeHttpClient(request, NotificationBaseResponse.class);
    }

    /**
     * Update notification with specified parameters.
     *
     * @param name      The notification name
     * @param endpoint  The notification endpoint to update
     * @return A updating notification response
     */
    public NotificationBaseResponse updateNotification(String name, String endpoint) {
        NotificationUpdateRequest updateRequest = NotificationUpdateRequest.of(name, endpoint);
        return updateNotification(updateRequest);
    }

    /**
     * Update notification with specified parameters.
     *
     * @param name      The notification name
     * @param endpoint  The notification endpoint to update
     * @param token     The notification token to update
     * @param authType  The notification authentication type to update
     * @return A updating notification response
     */
    public NotificationBaseResponse updateNotification(String name, String endpoint, String token, AuthType authType) {
        NotificationUpdateRequest updateRequest = NotificationUpdateRequest.of(name, endpoint, token, authType);
        return updateNotification(updateRequest);
    }

    /**
     * Enable notification with specified enabling request.
     *
     * @param enableRequest The enabling notification request
     * @return A enabling notification response
     */
    public NotificationBaseResponse enableNotification(NotificationBaseRequest enableRequest) {
        InternalRequest request = this.
                createRequest(enableRequest, HttpMethodName.PUT, NOTIFICATION, enableRequest.getName());
        request.addParameter(NOTIFICATION_ENABLE, null);
        return this.invokeHttpClient(request, NotificationBaseResponse.class);
    }

    /**
     * Enable notification with specified notification name.
     *
     * @param name The notification name
     * @return A enabling notification response
     */
    public NotificationBaseResponse enableNotification(String name) {
        NotificationBaseRequest enableRequest = NotificationBaseRequest.of(name);
        return enableNotification(enableRequest);
    }

    /**
     * Disable notification with specified enabling request.
     *
     * @param disableRequest The disabling notification request
     * @return A disabling notification response
     */
    public NotificationBaseResponse disableNotification(NotificationBaseRequest disableRequest) {
        InternalRequest request = this.
                createRequest(disableRequest, HttpMethodName.PUT, NOTIFICATION, disableRequest.getName());
        request.addParameter(NOTIFICATION_DISABLE, null);
        return this.invokeHttpClient(request, NotificationBaseResponse.class);
    }

    /**
     * Enable notification with specified notification name.
     *
     * @param name The notification name
     * @return A disabling notification response
     */
    public NotificationBaseResponse disableNotification(String name) {
        NotificationBaseRequest disableRequest = NotificationBaseRequest.of(name);
        return disableNotification(disableRequest);
    }

    /**
     * Delete notification with specified deleting request.
     *
     * @param deleteRequest The deleting notification request
     * @return A deleting notification response
     */
    public NotificationBaseResponse deleteNotification(NotificationBaseRequest deleteRequest) {
        InternalRequest request = this
                .createRequest(deleteRequest, HttpMethodName.DELETE, NOTIFICATION, deleteRequest.getName());
        return this.invokeHttpClient(request, NotificationBaseResponse.class);
    }

    /**
     * Delete notification with specified notification name.
     *
     * @param name The notification name
     * @return A deleting notification response
     */
    public NotificationBaseResponse deleteNotification(String name) {
        NotificationBaseRequest deleteRequest = NotificationBaseRequest.of(name);
        return deleteNotification(deleteRequest);
    }

    /**
     * Upload media to material library.
     *
     * @param matlibUploadRequest The uploading request
     * @return A uploading response
     */
    public MatlibUploadResponse upload2Material(MatlibUploadRequest matlibUploadRequest) {
        InternalRequest request = this.createRequest(matlibUploadRequest, HttpMethodName.POST, MATLIB);
        request.addParameter(MATLIB_UPLOAD, null);
        return this.invokeHttpClient(request, MatlibUploadResponse.class);
    }

    /**
     * Get material from material library.
     * @param materialId The material id
     * @return A getting material response
     */
    public MaterialGetResponse getMaterial(String materialId) {
        MaterialGetRequest materialGetRequest = new MaterialGetRequest(materialId);
        InternalRequest request = this.createRequest(materialGetRequest, HttpMethodName.GET, MATERIAL_LIBRARY,
                materialId);
        return invokeHttpClient(request, MaterialGetResponse.class);
    }

    /**
     * Delete material from material library.
     * @param materialId The material id
     * @return A deleting material response
     */
    public MaterialBaseResponse deleteMaterial(String materialId) {
        MaterialGetRequest materialGetRequest = new MaterialGetRequest(materialId);
        InternalRequest request = this.createRequest(materialGetRequest, HttpMethodName.DELETE, MATERIAL_LIBRARY,
                materialId);
        return invokeHttpClient(request, MaterialBaseResponse.class);
    }

    /**
     * Search material from material library.
     * @param materialSearchRequest search parameters
     * @return material list response
     */
    public MaterialSearchResponse searchMaterial(MaterialSearchRequest materialSearchRequest) {
        InternalRequest request = this.createRequest(materialSearchRequest, HttpMethodName.GET, MATERIAL_LIBRARY);
        if (null != materialSearchRequest.getInfoType()) {
            request.addParameter(MATERIAL_INFO_TYPE, materialSearchRequest.getInfoType());
        }
        if (null != materialSearchRequest.getMediaType()) {
            request.addParameter(MATERIAL_MEDIA_TYPE, materialSearchRequest.getMediaType());
        }
        if (null != materialSearchRequest.getSourceType()) {
            request.addParameter(MATERIAL_SOURCE_TYPE, materialSearchRequest.getSourceType());
        }
        if (null != materialSearchRequest.getStatus()) {
            request.addParameter(MATERIAL_STATUS, materialSearchRequest.getStatus());
        }
        if (StringUtils.isNotBlank(materialSearchRequest.getTitleKeyword())) {
            request.addParameter(MATERIAL_TITLE_KEYWORD, materialSearchRequest.getTitleKeyword());
        }
        if (null != materialSearchRequest.getBegin()) {
            request.addParameter(MATERIAL_CREATETIME_BEGIN, materialSearchRequest.getBegin());
        }
        if (null != materialSearchRequest.getEnd()) {
            request.addParameter(MATERIAL_CREATETIME_END, materialSearchRequest.getEnd());
        }
        if (null != materialSearchRequest.getPageNo()) {
            request.addParameter(MATERIAL_PAGENO,
                    materialSearchRequest.getPageNo() + "");
        }
        if (null != materialSearchRequest.getSize()) {
            request.addParameter(MATERIAL_SIZE,
                    materialSearchRequest.getSize() + "");
        }
        return invokeHttpClient(request, MaterialSearchResponse.class);
    }

    /**
     * Upload media preset to material library.
     *
     * @param matlibUploadRequest The uploading request
     * @return A uploading response
     */
    public MaterialPresetUploadResponse uploadMaterialPreset(String type, MatlibUploadRequest matlibUploadRequest) {
        InternalRequest request = this.createRequest(matlibUploadRequest, HttpMethodName.POST,
                MATERIAL_LIBRARY, MATERIAL_LIBRARY_PRESET, type);
        request.addParameter(MATLIB_UPLOAD, null);
        return this.invokeHttpClient(request, MaterialPresetUploadResponse.class);
    }

    /**
     * Get material preset from material library.
     * @param id The material preset id
     * @return A getting material preset response
     */
    public MaterialPresetGetResponse getMaterialPreset(String id) {
        MaterialPresetGetRequest materialPresetGetRequest = new MaterialPresetGetRequest(id);
        InternalRequest request = this.createRequest(materialPresetGetRequest, HttpMethodName.GET, MATERIAL_LIBRARY,
                MATERIAL_LIBRARY_PRESET, id);
        return invokeHttpClient(request, MaterialPresetGetResponse.class);
    }

    /**
     * Delete material from material library.
     * @param id The material preset id
     * @return A deleting material preset response
     */
    public MaterialBaseResponse deleteMaterialPreset(String id) {
        MaterialPresetGetRequest materialPresetGetRequest = new MaterialPresetGetRequest(id);
        InternalRequest request = this.createRequest(materialPresetGetRequest, HttpMethodName.DELETE, MATERIAL_LIBRARY,
                MATERIAL_LIBRARY_PRESET, id);
        return invokeHttpClient(request, MaterialBaseResponse.class);
    }

    /**
     * Search material preset from material library.
     * @param materialPresetSearchRequest search parameters
     * @return material list response
     */
    public MaterialPresetSearchResponse searchMaterialPreset(MaterialPresetSearchRequest materialPresetSearchRequest) {
        InternalRequest request = this.createRequest(materialPresetSearchRequest, HttpMethodName.GET, MATERIAL_LIBRARY,
                MATERIAL_LIBRARY_PRESET);
        if (null != materialPresetSearchRequest.getSourceType()) {
            request.addParameter(MATERIAL_SOURCE_TYPE, materialPresetSearchRequest.getSourceType());
        }
        if (null != materialPresetSearchRequest.getStatus()) {
            request.addParameter(MATERIAL_STATUS, materialPresetSearchRequest.getStatus());
        }
        if (null != materialPresetSearchRequest.getType()) {
            request.addParameter(MATERIAL_PRESET_TYPE, materialPresetSearchRequest.getType());
        }
        if (null != materialPresetSearchRequest.getPageNo()) {
            request.addParameter(MATERIAL_PAGENO, materialPresetSearchRequest.getPageNo() + "");
        }
        if (null != materialPresetSearchRequest.getPageSize()) {
            request.addParameter(MATERIAL_PAGESIZE, materialPresetSearchRequest.getPageSize() + "");
        }
        return invokeHttpClient(request, MaterialPresetSearchResponse.class);
    }

    /**
     * Create matlib config.
     *
     * @param baseRequest The creating matlib config request.
     * @return A creating matlib config response.
     */
    public MatlibConfigBaseResponse createMatlibConfig(MatlibConfigBaseRequest baseRequest) {
        InternalRequest request = this.createRequest(baseRequest, HttpMethodName.POST, MATLIB, MATLIB_CONFIG);
        return invokeHttpClient(request, MatlibConfigBaseResponse.class);
    }

    /**
     * Update matlib config.
     * @param baseRequest The updating matlib config request.
     * @return A updating matlib config response.
     */
    public MatlibConfigBaseResponse updateMatlibConfig(MatlibConfigBaseRequest baseRequest) {
        InternalRequest request = this.createRequest(baseRequest, HttpMethodName.PUT, MATLIB, MATLIB_CONFIG);
        return invokeHttpClient(request, MatlibConfigBaseResponse.class);
    }

    /**
     * Get matlib config.
     *
     * @return A matlib config response.
     */
    public MatlibConfigGetResponse getMatlibConfig() {
        InternalRequest request = this.createRequest(new MatlibConfigBaseRequest(), HttpMethodName.GET, MATLIB,
                MATLIB_CONFIG);
        return invokeHttpClient(request, MatlibConfigGetResponse.class);
    }

    /**
     * Transfer a list of text to audio.
     * If you want to use this function, please set matlib bucket config first,
     *
     * @param text2AudioRequest The text to audio request
     * @return A text to audio response
     */
    public Text2AudioResponse text2Audio(Text2AudioRequest text2AudioRequest) {
        InternalRequest request = this.createRequest(text2AudioRequest, HttpMethodName.POST, MATLIB, MATLIB_SPEECH);
        return invokeHttpClient(request, Text2AudioResponse.class);
    }

    /**
     * create a task of video edit
     * The cmd Object in VideoEditCreateRequest
     * 1> do not need to modify it by your self, you only put the json string to Map<String, Object> to it(From web FE)
     * 2> if you want to construct it by yourself, make object by Map<String, Object>
     *
     * @param videoEditCreateRequest
     * @return A response of job create result
     */
    public VideoEditCreateResponse createVideoEdit(VideoEditCreateRequest videoEditCreateRequest) {
        InternalRequest request = this.createRequest(videoEditCreateRequest, HttpMethodName.POST, VIDEO_EDIT,
                VIDEO_EDIT_CREATE);
        return invokeHttpClient(request, VideoEditCreateResponse.class);
    }

    /**
     * get edit job status
     *
     * @param editId
     * @return A job desc of this editId
     */
    public VideoEditPollingResponse pollingVideoEdit(long editId) {
        VideoEditPollingRequest videoEditPollingRequest = new VideoEditPollingRequest(editId);
        InternalRequest request = this.createRequest(videoEditPollingRequest, HttpMethodName.GET,
                VIDEO_EDIT, VIDEO_EDIT_POLLING, String.format("%d", editId));
        return invokeHttpClient(request, VideoEditPollingResponse.class);
    }

    /**
     * create a video with materials and subtitles
     *
     * @param videoGenerationRequest videoGenerationResponse
     * @return VideoGenerationResponse
     */
    public VideoGenerationResponse createVideoGenerationTask(VideoGenerationRequest videoGenerationRequest) {
        InternalRequest request = this.createRequest(videoGenerationRequest, HttpMethodName.POST, MATLIB,
                    MATLIB_VIDEO_GENERATION);
        return invokeHttpClient(request, VideoGenerationResponse.class);
    }

    /**
     * get keyframe list from the transcoding task or no transcoding task in the instance
     * @param instanceId workflowInstanceId
     * @return keyframe list
     */
    public KeyFrameDescGetResponse queryKeyFrameDescList(String instanceId) {
        InstanceBaseRequest getRequest = InstanceBaseRequest.of(instanceId);
        InternalRequest request = this.createRequest(getRequest, HttpMethodName.GET, INSTANCE, instanceId);
        request.addParameter(INSTANCE_KEYFRAME_DESC_URLS, null);
        return invokeHttpClient(request, KeyFrameDescGetResponse.class);
    }

    /**
     * add keyframe for workflowInstance
     * @param instanceId workflowInstanceId
     * @param keyFrameDescAddRequest keyframe desc
     */
    public void addKeyFrameDesc(String instanceId, KeyFrameDescAddRequest keyFrameDescAddRequest) {
        InternalRequest request = this.createRequest(keyFrameDescAddRequest, HttpMethodName.POST, INSTANCE,
                instanceId);
        request.addParameter(INSTANCE_KEYFRAME_ADD, null);
        invokeHttpClient(request, AbstractBceResponse.class);
    }

    /**
     * get target bos key's preSign url
     * @param instanceId workflowInstanceId
     * @return keyFrame info list
     */
    public KeyFrameUrlResponse queryKeyFrameUrlList(String instanceId) {
        InstanceBaseRequest getRequest = InstanceBaseRequest.of(instanceId);
        InternalRequest request = this.createRequest(getRequest, HttpMethodName.GET, INSTANCE,
                getRequest.getInstanceId());
        request.addParameter(INSTANCE_KEYFRAME_URLS, null);
        request.addParameter(INSTANCE_PRE_SIGN_FLAG, String.valueOf(true));
        return invokeHttpClient(request, KeyFrameUrlResponse.class);
    }

    /**
     * create draft
     * @param matlibTaskRequest
     * @return
     */
    public MatlibTaskResponse createDraft(MatlibTaskRequest matlibTaskRequest) {
        InternalRequest request = this.createRequest(matlibTaskRequest, HttpMethodName.POST, MATLIB);
        return invokeHttpClient(request, MatlibTaskResponse.class);
    }

    /**
     * update draft
     * @return
     */
    public void updateDraft(long draftId, MatlibTaskRequest matlibTaskRequest) {

        InternalRequest request = this.createRequest(matlibTaskRequest, HttpMethodName.PUT, MATLIB,
                String.valueOf(draftId), MATLIB_TIMELINE_RESOURCE);
        invokeHttpClient(request, AbstractBceResponse.class);
    }

    /**
     *  List all of the draft.
     */
    public ListByPageResponse<MatlibTaskGetResponse> getDraftList(DraftListRequest listRequest) {
        InternalRequest request = this.createRequest(listRequest, HttpMethodName.GET, MATLIB);
        request.addParameter(LIST_PAGE_NO, String.valueOf(listRequest.getPageNo()));
        request.addParameter(LIST_PAGE_SIZE, String.valueOf(listRequest.getPageSize()));
        if (listRequest.getStatus() != null) {
            request.addParameter(MATERIAL_STATUS, listRequest.getStatus().toString());
        }
        if (listRequest.getBeginTime() != null) {
            request.addParameter(LIST_BEGIN_TIME,
                    DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(listRequest.getBeginTime()));
        }
        if (listRequest.getEndTime() != null) {
            request.addParameter(LIST_END_TIME,
                    DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(listRequest.getEndTime()));
        }
        return invokeHttpClient(request, ListByPageResponse.class);
    }

    /**
     * Get a single draft.
     */
    public GetDraftResponse getSingleDraft(long id) {
        GetDraftRequest request = GetDraftRequest.of(id);
        InternalRequest draftAndTimeline = this.createRequest(request, HttpMethodName.GET, MATLIB,
                String.valueOf(request.getId()), MATLIB_DRAFT_TIMELINE);
        return invokeHttpClient(draftAndTimeline, GetDraftResponse.class);
    }

}

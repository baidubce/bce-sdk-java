package com.baidubce.services.bvw;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bvw.model.common.ListByPageResponse;
import com.baidubce.services.bvw.model.keyframe.KeyFrameDescAddRequest;
import com.baidubce.services.bvw.model.keyframe.KeyFrameDescGetResponse;
import com.baidubce.services.bvw.model.keyframe.KeyFrameDescVO;
import com.baidubce.services.bvw.model.keyframe.KeyFrameUrlResponse;
import com.baidubce.services.bvw.model.matlib.DraftListRequest;
import com.baidubce.services.bvw.model.matlib.GetDraftResponse;
import com.baidubce.services.bvw.model.matlib.MaterialGetResponse;
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
import com.baidubce.services.bvw.model.matlib.MatlibTaskStatus;
import com.baidubce.services.bvw.model.matlib.MatlibUploadRequest;
import com.baidubce.services.bvw.model.matlib.MatlibUploadResponse;
import com.baidubce.services.bvw.model.matlib.Per;
import com.baidubce.services.bvw.model.matlib.Text2AudioRequest;
import com.baidubce.services.bvw.model.matlib.Text2AudioResponse;
import com.baidubce.services.bvw.model.matlib.VideoGenerationRequest;
import com.baidubce.services.bvw.model.matlib.VideoGenerationResponse;
import com.baidubce.services.bvw.model.matlib.timeline.Timeline;
import com.baidubce.services.bvw.model.media.MediaBaseResponse;
import com.baidubce.services.bvw.model.media.MediaGetResponse;
import com.baidubce.services.bvw.model.media.MediaInstanceListResponse;
import com.baidubce.services.bvw.model.media.MediaListRequest;
import com.baidubce.services.bvw.model.media.MediaListResponse;
import com.baidubce.services.bvw.model.media.MediaProcessRequest;
import com.baidubce.services.bvw.model.media.MediaProcessResponse;
import com.baidubce.services.bvw.model.media.MediaStatus;
import com.baidubce.services.bvw.model.media.MediaUpdateRequest;
import com.baidubce.services.bvw.model.notification.AuthType;
import com.baidubce.services.bvw.model.notification.NotificationBaseResponse;
import com.baidubce.services.bvw.model.notification.NotificationGetResponse;
import com.baidubce.services.bvw.model.notification.NotificationListResponse;
import com.baidubce.services.bvw.model.notification.NotificationModel;
import com.baidubce.services.bvw.model.notification.NotificationStatus;
import com.baidubce.services.bvw.model.videoedit.RefererType;
import com.baidubce.services.bvw.model.videoedit.VideoEditCreateRequest;
import com.baidubce.services.bvw.model.videoedit.VideoEditCreateResponse;
import com.baidubce.services.bvw.model.videoedit.VideoEditPollingRequest;
import com.baidubce.services.bvw.model.videoedit.VideoEditPollingResponse;
import com.baidubce.services.bvw.model.workflow.DagModel;
import com.baidubce.services.bvw.model.workflow.RunnableStatus;
import com.baidubce.services.bvw.model.workflow.StageModel;
import com.baidubce.services.bvw.model.workflow.StageParamModel;
import com.baidubce.services.bvw.model.workflow.StageType;
import com.baidubce.services.bvw.model.workflow.WorkflowBaseResponse;
import com.baidubce.services.bvw.model.workflow.WorkflowCreateRequest;
import com.baidubce.services.bvw.model.workflow.WorkflowGetResponse;
import com.baidubce.services.bvw.model.workflow.WorkflowListRequest;
import com.baidubce.services.bvw.model.workflow.WorkflowListResponse;
import com.baidubce.services.bvw.model.workflow.WorkflowStatus;
import com.baidubce.services.bvw.model.workflow.WorkflowUpdateRequest;
import com.baidubce.services.bvw.model.workflow.instance.InstanceGetResponse;
import com.baidubce.services.bvw.model.workflow.instance.InstanceGetTaskUrlsResponse;
import com.baidubce.services.bvw.model.workflow.task.TaskGetResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.hamcrest.CustomMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.baidubce.services.bvw.model.matlib.Per.BASE_DUXIAOYU;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;

@RunWith(Enclosed.class)
@Slf4j
public class BvwClientTest {

    @Ignore
    public static class BvwBase {

        protected static String ak = "your ak";
        protected static String sk = "your sk";
        protected static String endpoint = "http://bvw.bj.baidubce.com";
        protected static String bosEndpoint =  "http://bj.bcebos.com";

        @Rule
        public ExpectedException thrown = ExpectedException.none();
        protected BvwClientConfiguration config;
        protected BvwClient client;
        protected BosClient bosClient;
        protected SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        @Before
        public void setUp() {
            this.config = new BvwClientConfiguration().withCredentials(new DefaultBceCredentials(ak, sk))
                    .withEndpoint(endpoint);
            this.client = new BvwClient(config);
            BosClientConfiguration bosConfig = new BosClientConfiguration();
            bosConfig.withCredentials(new DefaultBceCredentials(ak, sk)).withEndpoint(bosEndpoint);
            this.bosClient = new BosClient(bosConfig);
        }

        @After
        public void tearDown() {
            this.client.shutdown();
        }

        protected void expectBceServiceException(final int statusCode, final String errorCode) {
            this.thrown.expect(new CustomMatcher<Throwable>(
                    "BceServiceException [ statusCode=" + statusCode + ", errorCode=" + errorCode + "]") {
                @Override
                public boolean matches(Object item) {
                    return (item instanceof BceServiceException)
                            && ((BceServiceException) item).getStatusCode() == statusCode
                            && Objects.equal(((BceServiceException) item).getErrorCode(), errorCode);
                }
            });
        }

        public void toJsonPrettyString(String method, Object object) {
            try {
                log.info("[{}]==>\n{}", method, JsonUtils.toJsonPrettyString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }

    public static class WorkflowTest extends BvwBase {

        private String wfPrefix = "test_";
        private String wfName = wfPrefix + sdf.format(new Date());

        private DagModel createDefaultDag() {
            StageParamModel startStageParam = StageParamModel.of("{}");
            StageModel startStage = StageModel.of("start", startStageParam, StageType.START);
            StageParamModel mediaInfoStageParam = StageParamModel.of("{}");
            StageModel mediaInfoStage = StageModel.of("mediaInfo", mediaInfoStageParam, StageType.MEDIAINFO);
            StageParamModel blackBorderDetectStageParam = StageParamModel.of("{}");
            StageModel blackBorderDetectStage = StageModel.of("blackBorderDetect", blackBorderDetectStageParam,
                                                              StageType.BLACK_BORDER_DETECT);
            StageParamModel transcodingStageParam = StageParamModel.of(
                    "{\"job\":{\"pipelineName\":\"test_transcoding\",\"source\":{},\"target\":{\"presetName\":"
                            + "\"bvwtest\",\"targetBucket\":\"videoworks-source\"}},\"needDetectBlackBoard\":true,"
                            + "\"adjustOrientation\":\"ALL\"}");
            StageModel transcodingStage = StageModel.of("transcoding", transcodingStageParam,
                                                        StageType.TRANSCODING);
            StageParamModel thumbnailStageParam = StageParamModel.of(
                    "{\"notificationName\":\"test\",\"job\":{\"pipelineName\":\"test_thumbnail\",\"target\":"
                            + "{\"targetBucket\":\"videoworks-source\",\"format\":\"jpg\",\"sizingPolicy\":\"keep\"},"
                            + "\"capture\":{\"mode\":\"auto\"}}}");
            StageModel thumbnailStage = StageModel.of("thumbnail", thumbnailStageParam, StageType.THUMBNAIL);
            StageParamModel publishStageParam = StageParamModel.of("{}");
            StageModel publishStage = StageModel.of("publish", publishStageParam, StageType.PUBLISH);

            Map<String, StageModel> stages = Maps.newHashMap();
            stages.put(startStage.getName(), startStage);
            stages.put(mediaInfoStage.getName(), mediaInfoStage);
            stages.put(blackBorderDetectStage.getName(), blackBorderDetectStage);
            stages.put(transcodingStage.getName(), transcodingStage);
            stages.put(thumbnailStage.getName(), thumbnailStage);
            stages.put(publishStage.getName(), publishStage);

            Map<String, List<String>> dependencies = Maps.newHashMap();
            dependencies.put(startStage.getName(), Lists.newArrayList(blackBorderDetectStage.getName(),
                                                                      mediaInfoStage.getName(),
                                                                      thumbnailStage.getName()));
            dependencies.put(blackBorderDetectStage.getName(), Lists.newArrayList(transcodingStage.getName()));
            dependencies.put(transcodingStage.getName(), Lists.newArrayList(publishStage.getName()));
            dependencies.put(mediaInfoStage.getName(), Lists.newArrayList(publishStage.getName()));
            dependencies.put(thumbnailStage.getName(), Lists.newArrayList(publishStage.getName()));
            dependencies.put(publishStage.getName(), Lists.<String>newArrayList());

            return DagModel.of(stages, dependencies);
        }

        private String getATestWorkflowName() {
            ListByPageResponse<WorkflowListResponse> response = client.listWorkflow(WorkflowListRequest.of(1, 1));
            if (CollectionUtils.isEmpty(response.getData())) {
                return null;
            }
            return JsonUtils.fromJsonString(JsonUtils.toJsonString(response.getData().toArray()[0]),
                                            WorkflowListResponse.class).getName();
        }

        private String getATestWorkflowNameWithStatus(WorkflowStatus status) {
            ListByPageResponse<WorkflowListResponse> response = client
                    .listWorkflow(WorkflowListRequest.of(1, 1, status));
            if (CollectionUtils.isEmpty(response.getData())) {
                return null;
            }
            return JsonUtils.fromJsonString(JsonUtils.toJsonString(response.getData().toArray()[0]),
                                            WorkflowListResponse.class).getName();
        }

        private String getATestWorkflowNameIsNotUsing() {
            ListByPageResponse<WorkflowListResponse> response = client.listWorkflow(WorkflowListRequest.of(1, 100));
            if (CollectionUtils.isEmpty(response.getData())) {
                return null;
            }
            Object[] responseArr = response.getData().toArray();
            for (int i = 0; i < responseArr.length; i++) {
                WorkflowListResponse workflow = JsonUtils
                        .fromJsonString(JsonUtils.toJsonString(response.getData().toArray()[i]),
                                        WorkflowListResponse.class);
                if (!workflow.isUsing()) {
                    return workflow.getName();
                }
            }
            return null;
        }

        @Test
        public void testCreateWorkflow() {
            String workflowName;
            try {
                client.getWorkflow(wfName).getName();
                workflowName = wfPrefix + System.currentTimeMillis();
            } catch (BceServiceException e) {
                workflowName = wfName;
            }
            WorkflowCreateRequest request = WorkflowCreateRequest.of(workflowName, createDefaultDag());
            WorkflowBaseResponse response = client.createWorkflow(request);
            assertThat(response, notNullValue(WorkflowBaseResponse.class));
            toJsonPrettyString("createWorkflow", response);
        }

        @Test
        public void testSpeed(){
            Text2AudioRequest request = new Text2AudioRequest();
            request.setTexts(Collections.singletonList("啊啊啊啊啊"));
            request.setBucket("bucket-yu");
            request.setPer(BASE_DUXIAOYU);
            Text2AudioResponse res = client.text2Audio(request);
            toJsonPrettyString("Text2AudioResponse", res);
        }

        @Test
        public void testGetWorkflow() {
            String workflowName = getATestWorkflowName();
            if (null == workflowName) {
                log.info("There is no workflow exist, please create one first.");
            }
            WorkflowGetResponse response = client.getWorkflow(workflowName);
            assertThat(response, notNullValue(WorkflowGetResponse.class));
            assertThat(response.getName(), is(workflowName));
            toJsonPrettyString("getWorkflow", response);
        }

        @Test
        public void testUpdateWorkflow() {
            String workflowName = getATestWorkflowNameIsNotUsing();
            if (null == workflowName) {
                log.info("There is no workflow can be modified in the first 100 workflows, please create one first.");
                return;
            }
            DagModel dag = createDefaultDag();
            dag.getStages().get("mediaInfo").setParam(StageParamModel.of("{}"));
            WorkflowUpdateRequest request = WorkflowUpdateRequest.of(workflowName, dag);
            WorkflowBaseResponse response = client.updateWorkflow(request);
            String updateContent = JsonUtils.fromJsonString(client.getWorkflow(workflowName).getExpression(),
                                                            DagModel.class).getStages().get("mediaInfo")
                    .getParam().getInput();
            assertThat(updateContent, is("{}"));
            toJsonPrettyString("updateWorkflow", response);
        }

        @Test
        public void testListWorkflow() {
            String begin = "2019-06-30T16:00:00Z";
            String end = "2029-06-30T16:00:00Z";
            WorkflowListRequest request = WorkflowListRequest.of(1, 10, WorkflowStatus.NORMAL, wfPrefix, begin, end);
            ListByPageResponse<WorkflowListResponse> response = client.listWorkflow(request);
            assertThat(response, notNullValue());
            toJsonPrettyString("listWorkflow", response);
        }

        @Test
        public void testDeleteWorkflow() {
            String workflowName = getATestWorkflowNameIsNotUsing();
            if (null == workflowName) {
                log.info("There is no workflow can be modified in the first 100 workflows, please create one first.");
                return;
            }
            WorkflowBaseResponse response = client.deleteWorkflow(workflowName);
            assertThat(response, notNullValue(WorkflowBaseResponse.class));
            toJsonPrettyString("deleteWorkflow", response);
        }

        @Test
        public void testEnableWorkflow() {
            String workflowName = getATestWorkflowNameWithStatus(WorkflowStatus.DISABLE);
            if (null == workflowName) {
                log.info("There is no disabled workflow exist, please disable one first.");
                return;
            }
            WorkflowBaseResponse response = client.enableWorkflow(workflowName);
            assertThat(response, notNullValue(WorkflowBaseResponse.class));
            toJsonPrettyString("enableWorkflow", response);
        }

        @Test
        public void testDisableWorkflow() throws InterruptedException {
            String workflowName = getATestWorkflowNameWithStatus(WorkflowStatus.NORMAL);
            if (null == workflowName) {
                log.info("There is no normal exist, please disable one first.");
                return;
            }
            WorkflowBaseResponse response = client.disableWorkflow(workflowName);
            assertThat(response, notNullValue(WorkflowBaseResponse.class));
            toJsonPrettyString("disableWorkflow", response);
        }

    }

    public static class MediaTest extends BvwBase {

        private String sourceBucket = "videoworks-source";
        private String sourceKey = "movie.mp4";
        private String title = "test movie";
        private String description = "videoworks unit test";

        private String getATestMediaId() {
            ListByPageResponse<MediaListResponse> response = client.listMedia(MediaListRequest.of(1, 1));
            if (CollectionUtils.isEmpty(response.getData())) {
                return null;
            }
            return JsonUtils.fromJsonString(JsonUtils.toJsonString(response.getData().toArray()[0]),
                                            MediaListResponse.class).getMediaId();
        }

        private String getATestMediaIdSuccess() {
            ListByPageResponse<MediaListResponse> response = client.
                    listMedia(MediaListRequest.of(1, 1, RunnableStatus.SUCCESS));
            if (CollectionUtils.isEmpty(response.getData())) {
                return null;
            }
            return JsonUtils.fromJsonString(JsonUtils.toJsonString(response.getData().toArray()[0]),
                                            MediaListResponse.class).getMediaId();
        }

        private String getATestWorkflowName() {
            ListByPageResponse<WorkflowListResponse> response = client.listWorkflow(WorkflowListRequest.of(1, 1));
            return JsonUtils.fromJsonString(JsonUtils.toJsonString(response.getData().toArray()[0]),
                                            WorkflowListResponse.class).getName();
        }

        @Test
        public void testProcessMedia() {
            String copyKey = "movie" + System.currentTimeMillis() + ".mp4";
            bosClient.copyObject(sourceBucket, sourceKey, sourceBucket, copyKey);
            String workflowName = getATestWorkflowName();
            MediaProcessRequest request = MediaProcessRequest.
                    of(sourceBucket, copyKey, title, description, workflowName);
            MediaProcessResponse response = client.processMedia(request);
            assertThat(response, notNullValue(MediaProcessResponse.class));
            toJsonPrettyString("processMedia", response);
        }

        @Test
        public void testGetMedia() {
            String mediaId = getATestMediaId();
            if (null == mediaId) {
                log.info("There is no media exist, please create one first.");
                return;
            }
            MediaGetResponse response = client.getMedia(mediaId);
            assertThat(response.getMediaId(), is(mediaId));
            toJsonPrettyString("getMedia", response);
        }

        @Test
        public void testUpdateMedia() {
            String mediaId = getATestMediaId();
            if (null == mediaId) {
                log.info("There is no media exist, please create one first.");
                return;
            }
            MediaUpdateRequest updateRequest = MediaUpdateRequest
                    .of(mediaId, title + "_update", description + "_update");
            MediaBaseResponse response = client.updateMedia(updateRequest);
            assertThat(response, notNullValue(MediaBaseResponse.class));
            assertThat(client.getMedia(mediaId).getTitle(), is(title + "_update"));
            assertThat(client.getMedia(mediaId).getDescription(), is(description + "_update"));
            toJsonPrettyString("updateMedia", response);
        }

        @Test
        public void testGetMediaInstanceList() {
            String mediaId = getATestMediaIdSuccess();
            if (null == mediaId) {
                log.info("There is no successful media exist, please create one first.");
                return;
            }
            MediaInstanceListResponse response = client.getMediaInstanceList(mediaId);
            assertThat(response, notNullValue());
            toJsonPrettyString("getMediaInstanceList", response);
        }

        @Test
        public void testBanMedia() {
            String mediaId = getATestMediaId();
            if (null == mediaId) {
                log.info("There is no media exist, please create one first.");
                return;
            }
            MediaBaseResponse response = client.banMedia(mediaId);
            assertThat(response, notNullValue());
            toJsonPrettyString("banMedia", response);
        }

        @Test
        public void testUnbanMedia() {
            String mediaId = getATestMediaId();
            if (null == mediaId) {
                log.info("There is no media exist, please create one first.");
                return;
            }
            MediaBaseResponse response = client.unbanMedia(mediaId);
            assertThat(response, notNullValue());
            toJsonPrettyString("unbanMedia", response);
        }

        @Test
        public void testListMedia() {
            String begin = "2019-06-30T16:00:00Z";
            String end = "2029-06-30T16:00:00Z";
            MediaListRequest listRequest = MediaListRequest
                    .of(1, 10, begin, end, MediaStatus.NORMAL, RunnableStatus.SUCCESS, null, "movie", "desc",
                        "createTime");
            ListByPageResponse<MediaListResponse> response = client.listMedia(listRequest);
            assertThat(response, notNullValue());
            toJsonPrettyString("listMedia", response);
        }

        @Test
        public void testDeleteMedia() {
            String mediaId = getATestMediaIdSuccess();
            if (null == mediaId) {
                log.info("There is no successful media exist, please create one first.");
                return;
            }
            MediaBaseResponse response = client.deleteMedia(mediaId);
            assertThat(response, notNullValue());
            toJsonPrettyString("deleteMedia", response);
        }

        @Test
        public void testBatchDeleteMedia() {
            String mediaId = getATestMediaIdSuccess();
            if (null == mediaId) {
                log.info("There is no successful media exist, please create one first.");
                return;
            }
            List<String> mediaIds = Lists.newArrayList(mediaId);
            MediaBaseResponse response = client.batchDeleteMedia(mediaIds);
            assertThat(response, notNullValue());
            toJsonPrettyString("batchDeleteMedia", response);
        }

    }

    public static class InstanceTest extends BvwBase {

        private String getATestInstanceId() {
            ListByPageResponse<MediaListResponse> response = client.listMedia(MediaListRequest.of(1, 1));
            if (CollectionUtils.isEmpty(response.getData())) {
                return null;
            }
            return JsonUtils.fromJsonString(JsonUtils.toJsonString(response.getData().toArray()[0]),
                                            MediaListResponse.class).getLatestInstanceId();
        }

        @Test
        public void testGetInstance() {
            String instanceId = getATestInstanceId();
            if (null == instanceId) {
                log.info("There is no instance exist, please process a media first.");
            }
            InstanceGetResponse response = client.getInstance(instanceId);
            assertThat(response, notNullValue());
            assertThat(response.getInstanceId(), is(instanceId));
            toJsonPrettyString("getInstance", response);
        }

        @Test
        public void testGetInstanceTaskUrls() {
            InstanceGetTaskUrlsResponse response = client.getInstanceTaskUrls(getATestInstanceId());
            assertThat(response, notNullValue());
            toJsonPrettyString("getInstanceTaskUrls", response);
        }

        @Test
        public void testQueryKeyFrameDescList() {
            KeyFrameDescGetResponse keyFrameDescGetResponse = client.queryKeyFrameDescList(getATestInstanceId());
            assertThat(keyFrameDescGetResponse, notNullValue());
            toJsonPrettyString("queryKeyFrameDescList", keyFrameDescGetResponse);
        }

        @Test
        public void testQueryKeyFrameUrlList() {
            KeyFrameUrlResponse keyFrameUrlResponse = client.queryKeyFrameUrlList(getATestInstanceId());
            assertThat(keyFrameUrlResponse, notNullValue());
            toJsonPrettyString("queryKeyFrameUrlList", keyFrameUrlResponse);
        }

        @Test
        public void testAddKeyFrameDesc() {
            KeyFrameDescAddRequest keyFrameDescAddRequest = new KeyFrameDescAddRequest();
            KeyFrameDescAddRequest.TaskAndKeyFrameDesc frameDesc = new KeyFrameDescAddRequest.TaskAndKeyFrameDesc();
            List<KeyFrameDescAddRequest.TaskAndKeyFrameDesc> TaskAndKeyFrameDescs = Lists.newArrayList();
            TaskAndKeyFrameDescs.add(frameDesc);
            ArrayList<String> taskIds = Lists.newArrayList();
            taskIds.add("tsk-minc4ux91yzd3y2q");
            frameDesc.setTaskIds(taskIds);

            ArrayList<KeyFrameDescVO> KeyFrameDescVOs = Lists.newArrayList();
            KeyFrameDescVO vo1 = new KeyFrameDescVO();
            vo1.setOffset(5L);
            vo1.setContent("{\"desc\":\"开始计数\",\"objectKey\":\"videoworks/mda-mincb6u4iafih7tn/tsk-minc4ux91yzd3y2q"
                    + "/keyframethumb/源文件_1a2f2093-e95c-466e-c0cb-8be1fe6bb631_4.jpeg\",\"thumb\":\"https://chenwei50.bj.bcebos.com/videoworks/mda-mincb6u4iafih7tn/tsk-minc4ux91yzd3y2q/keyframethumb/源文件_1a2f2093-e95c-466e-c0cb-8be1fe6bb631_4.jpeg\",\"duration\":1}"
            );
            KeyFrameDescVOs.add(vo1);
            frameDesc.setKeyFrameDesc(KeyFrameDescVOs);
            keyFrameDescAddRequest.setTasks(TaskAndKeyFrameDescs);
            client.addKeyFrameDesc(getATestInstanceId(), keyFrameDescAddRequest);
        }

    }

    public static class TaskTest extends BvwBase {

        private String getATestTaskId() {
            ListByPageResponse<MediaListResponse> response = client.
                    listMedia(MediaListRequest.of(1, 1, RunnableStatus.SUCCESS));
            if (CollectionUtils.isEmpty(response.getData())) {
                return null;
            }
            String instanceId = JsonUtils.fromJsonString(JsonUtils.toJsonString(response.getData().toArray()[0]),
                                    MediaListResponse.class).getLatestInstanceId();
            InstanceGetTaskUrlsResponse taskUrlsResponse = client.getInstanceTaskUrls(instanceId);
            if (CollectionUtils.isEmpty(taskUrlsResponse.getStageTaskUrls())) {
                return null;
            }
            return taskUrlsResponse.getStageTaskUrls().get(0).getTaskId();
        }

        @Test
        public void testGetTask() {
            String taskId = getATestTaskId();
            if (null == taskId) {
                log.info("There is no task exist, please process a media first.");
                return;
            }
            System.out.println(taskId);
            TaskGetResponse response = client.getTask(taskId);
            assertThat(response, notNullValue());
            assertThat(response.getTaskId(), is(taskId));
            toJsonPrettyString("getTask", response);
        }

    }

    public static class NotificationTest extends BvwBase {

        private NotificationModel getATestNotification() {
            List<NotificationModel> notifications = client.listNotification().getNotifications();
            if (CollectionUtils.isEmpty(notifications)) {
                return null;
            }
            return notifications.get(0);
        }

        @Test
        public void testCreateNotification() {
            String createName = "test_" + System.currentTimeMillis();
            String endpoint = "http://test.com/callback123";
            NotificationBaseResponse response = client.createNotification(createName, endpoint);
            assertThat(response, notNullValue());
            assertThat(client.getNotification(createName).getEndpoint(), is(endpoint));
            toJsonPrettyString("createNotification", response);

            createName = "test_" + System.currentTimeMillis();
            String token = "123qweASD";
            AuthType authType = AuthType.SIGN;
            response = client.createNotification(createName, endpoint, token, authType);
            toJsonPrettyString("createNotification", response);
        }

        @Test
        public void testGetNotification() {
            NotificationModel notification = getATestNotification();
            if (null == notification) {
                log.info("There is no notification to get, please create a notification first.");
                return;
            }
            NotificationGetResponse response = client.getNotification(notification.getName());
            assertThat(response, notNullValue());
            assertThat(response.getName(), is(notification.getName()));
            toJsonPrettyString("getNotification", response);
        }

        @Test
        public void testGetNotificationException() {
            expectBceServiceException(404, "NotificationException.NoSuchNotification");
            NotificationGetResponse response = client.getNotification("sgwit325f");
            assertThat(response, notNullValue());
            toJsonPrettyString("getNotification", response);
        }

        @Test
        public void testListNotification() {
            NotificationListResponse response = client.listNotification();
            assertThat(response, notNullValue());
            toJsonPrettyString("listNotification", response);
        }

        @Test
        public void testListNotificationWithStatus() {
            NotificationListResponse response = client.listNotification(NotificationStatus.ENABLE);
            assertThat(response, notNullValue());
            toJsonPrettyString("listNotification", response);
        }

        @Test
        public void testUpdateNotification() {
            NotificationModel notification = getATestNotification();
            if (null == notification) {
                log.info("There is no notification to update, please create a notification first.");
                return;
            }
            String name = notification.getName();
            String endpoint = notification.getEndpoint();
            NotificationBaseResponse response = client.updateNotification(name, endpoint + "_update");
            assertThat(response, notNullValue());
            assertThat(client.getNotification(name).getEndpoint(), is(endpoint + "_update"));
            toJsonPrettyString("updateNotification", response);

            String token = "123qweASD";
            AuthType authType = AuthType.SIGN;
            response = client.updateNotification(name, endpoint + "_update", token, authType);
        }

        @Test
        public void testEnableNotification() {
            NotificationModel notification = getATestNotification();
            if (null == notification) {
                log.info("There is no notification to enable, please create a notification first.");
                return;
            }
            NotificationBaseResponse response = client.enableNotification(notification.getName());
            assertThat(response, notNullValue());
            assertThat(client.getNotification(notification.getName()).getStatus(), is(NotificationStatus.ENABLE));
            toJsonPrettyString("enableNotification", response);
        }

        @Test
        public void testDisableNotification() {
            NotificationModel notification = getATestNotification();
            if (null == notification) {
                log.info("There is no notification to disable, please create a notification first.");
                return;
            }
            NotificationBaseResponse response = client.disableNotification(notification.getName());
            assertThat(response, notNullValue());
            assertThat(client.getNotification(notification.getName()).getStatus(), is(NotificationStatus.DISABLE));
            toJsonPrettyString("disableNotification", response);
        }

        @Test
        public void testDeleteNotification() {
            NotificationModel notification = getATestNotification();
            if (null == notification) {
                log.info("There is no notification to delete, please create a notification first.");
                return;
            }
            NotificationBaseResponse response = client.deleteNotification(notification.getName());
            assertThat(response, notNullValue());
            toJsonPrettyString("deleteNotification", response);
        }

    }

    public static class MatlibTest extends BvwBase {

        @Test
        public void testUpload() {
            MatlibUploadRequest request = new MatlibUploadRequest("video", "test upload", "bvwtest", "vidpress/720p" +
                    "/kgjfz4gy4avtb7cp3fp.mp4", "abc");
            MatlibUploadResponse response = client.upload2Material(request);
            assertThat(response, notNullValue());
            toJsonPrettyString("upload2Material", response);
        }

        @Test
        public void testSearchMaterial() {
            MaterialSearchRequest request = new MaterialSearchRequest();
            request.setTitleKeyword("我");
            MaterialSearchResponse response = client.searchMaterial(request);
            assertThat(response, notNullValue());
            toJsonPrettyString("searchMaterial", response);
        }

        @Test
        public void testGetMaterial() {
            MaterialGetResponse response = client.getMaterial("b52ed2705d05fba3ce1d16a2bee21807");
            assertThat(response, notNullValue());
            toJsonPrettyString("getMaterial", response);
        }

        @Test
        public void testUploadMaterial() {
            MatlibUploadRequest request = new MatlibUploadRequest();
            request.setBucket("bucket-yu");
            request.setKey("3s.mp4");
            request.setMediaType("video");
            request.setTitle("test");
            MatlibUploadResponse response = client.upload2Material(request);
            assertThat(response, notNullValue());
            toJsonPrettyString("uploadMaterial", response);
        }

        @Test
        public void testSearchMaterialPreset() {
            MaterialPresetSearchRequest request = new MaterialPresetSearchRequest();
//            request.setType("PICTURE");
//            request.setSourceType("USER");
            MaterialPresetSearchResponse response = client.searchMaterialPreset(request);
            assertThat(response, notNullValue());
            toJsonPrettyString("searchMaterialPreset", response);
        }

        @Test
        public void testGetMaterialPreset() {
            MaterialPresetGetResponse response = client.getMaterialPreset("89355c2e2bd5d22450d965d7381c0517");
            assertThat(response, notNullValue());
            toJsonPrettyString("getMaterialPreset", response);
        }

        @Test
        public void testUploadMaterialPreset() {
            MatlibUploadRequest request = new MatlibUploadRequest();
            request.setBucket("bucket-yu");
            request.setKey("audio/3s.mp3");
            request.setMediaType("audio");
            request.setTitle("test");
            MaterialPresetUploadResponse response = client.uploadMaterialPreset("MUSIC", request);
            assertThat(response, notNullValue());
            toJsonPrettyString("uploadMaterialPreset", response);
        }

        @Test
        public void testGetMatlibConfig() {
            MatlibConfigGetResponse response = client.getMatlibConfig();
            toJsonPrettyString("getMatlibConfig", response);
        }

        @Test
        public void testCreateMatlibConfig() {
            MatlibConfigBaseRequest request = new MatlibConfigBaseRequest("yetanotherbucket");
            MatlibConfigBaseResponse response = client.createMatlibConfig(request);
            toJsonPrettyString("createMatlibConfig", response);
        }

        @Test
        public void testUpdateMatlibConfig() {
            MatlibConfigBaseRequest request = new MatlibConfigBaseRequest("yetanotherbucket");
            MatlibConfigBaseResponse response = client.updateMatlibConfig(request);
            toJsonPrettyString("updateMatlibConfig", response);
        }

        @Test
        public void testText2Audio() {
            Text2AudioRequest request = new Text2AudioRequest(Lists.newArrayList("噢我的天呐，",
                    "我是说，",
                    "如果这是真的话，",
                    "如果你不投币的话我就会在你吃玛丽阿姨的山羊奶酪蛋糕时用汤姆叔叔的靴子狠狠地踢你的屁股的,",
                    "我向你保证,",
                    "看在上帝的份上。",
                    "噢我的天呐，我是说，如果这是真的话，如果你不投币的话我就会在你吃玛丽阿姨的山羊奶酪蛋糕时" +
                            "用汤姆叔叔的靴子狠狠地踢你的屁股的，我向你保证，看在上帝的份上。"
            ), Per.BASE_DUXIAOMEI);
            Text2AudioResponse response = client.text2Audio(request);
            toJsonPrettyString("text2Audio", response);
        }

        @Test
        public void testVideoGeneration() {
            String requestJson = "{\n" +
                        "    \"tracks\": [{\n" +
                        "        \"materials\":[\n" +
                        "            {\n" +
                        "            \"bucket\":\"test-yu\",\n" +
                        "            \"key\":\"tianmai/test.png\",\n" +
                        "            \"type\":\"image\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "            \"bucket\":\"test-yu\",\n" +
                        "            \"key\":\"tianmai/test2.png\",\n" +
                        "            \"type\":\"image\"\n" +
                        "            },\n" +
                        "            {\n" +
                        "            \"bucket\":\"test-yu\",\n" +
                        "            \"key\":\"tianmai/test3.png\",\n" +
                        "            \"type\":\"image\"\n" +
                        "            }\n" +
                        "        ],\n" +
                        "        \"txt\":\"梁王朝末年，国力式微，大梁北境分裂为盛州、西齐、庸临三国。庸临领主孙女贺兰茗玉，自幼聪慧，此时正与哥哥庸临世子贺兰克用一起骑马射箭，侍女凌蓁儿跟随在侧。贺兰可用打趣贺兰茗玉是这草原上最烈的一匹马，发愁将来要把她嫁给一个什么样的人才能把她驯服，记得大祭司说过，贺兰茗玉将来是要母仪天下的。远处西齐国来人，侍女慌张来报，说西齐国来人要把贺兰绾音带走。贺兰绾音正跪在地上求爷爷庸临王贺兰明哲不要把自己嫁出去，贺兰茗玉与荷兰克用赶来，问贺兰明哲为何要把贺兰绾音嫁给西齐那个性情暴躁的大世子，她性情柔弱，嫁过去肯定要受欺负的。\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"materials\":[\n" +
                        "            {\n" +
                        "            \"bucket\":\"test-yu\",\n" +
                        "            \"key\":\"tianmai/test4.png\",\n" +
                        "            \"type\":\"image\"\n" +
                        "            }\n" +
                        "        ],\n" +
                        "        \"txt\":\"大梁天元六年，大梁铁骑频犯北境，将盛州王萧尚远困在永安城。萧尚远等待着三殿下齐王的支援，却迟迟没有看到希望，他只能死守城池等待援军。夜里，一个神秘的身影闯进了大梁营帐，被抓后声称是盛州探子，有重要情报向将军禀告换一条命，关乎到大梁此战役的胜败。此人称盛州三殿下萧承睿已经突破大梁边防，九殿下永安王已经发现了大梁军仓的位置，要带人去烧毁，渡口守军不得不分出半数兵力救火，同时萧承睿会借机渡江，算时辰援军已经快要到大梁粮仓附近了。\"\n" +
                        "    }],\n" +
                        "    \"endBucket\":\"test-yu\",\n" +
                        "    \"endKey\":\"tianmai/default_video_end.mp4\",\n" +
                        "    \"logoBucket\":\"test-yu\",\n" +
                        "    \"logoKey\":\"tianmai/百度云logo.png\",\n" +
                        "    \"resultBucket\":\"test-yu\",\n" +
                        "    \"ttsPer\":1\n" +
                        "}";
            VideoGenerationRequest request = JsonUtils.fromJsonString(requestJson, VideoGenerationRequest.class);

            VideoGenerationResponse response = client.createVideoGenerationTask(request);
            toJsonPrettyString("createVideoGenerationTask", response);
        }

        @Test
        public void testCreateDraft() {
            MatlibTaskRequest request = new MatlibTaskRequest();
            MatlibTaskResponse draft = client.createDraft(request);
            toJsonPrettyString("testCreateDraft", draft);
        }
        @Test
        public void testUpdateDraft() {
            MatlibTaskRequest request = new MatlibTaskRequest();
            String json ="{\"title\":\"新建作品-hahaha1\",\"draftId\":\"19175\","
                    + "\"timeline\":{\"timeline\":{\"video\":[{\"key\":\"name\",\"isMaster\":true,"
                    + "\"list\":[{\"type\":\"video\",\"start\":0,\"end\":9,\"showStart\":0,\"showEnd\":9,\"xpos\":0,"
                    + "\"ypos\":0,\"width\":1280,\"height\":720,\"duration\":9,\"extInfo\":{\"style\":\"\","
                    + "\"lightness\":0,\"contrast\":0,\"saturation\":0,\"hue\":0,\"speed\":1,"
                    + "\"transitionStart\":\"\",\"transitionEnd\":\"\",\"transitionDuration\":1,\"mirror\":\"\","
                    + "\"rotate\":0,\"blankArea\":\"\",\"volume\":1},\"mediaInfo\":{\"mediaType\":\"video\","
                    + "\"key\":\"video-edit/result/split_result_19151.mp4\",\"bucket\":\"tianmai-new\","
                    + "\"mediaId\":\"234a0b22ed0daa0545e07735170a1430\",\"coverImage\":\"https://bj-bos-sandbox"
                    + ".baidu-int.com/v1/tianmai-new/video-edit/result/thumbnail/split_result_1915100000000"
                    + ".jpg?x-bce-security-token=ODgzNjk4N2NiNzZjNGI2NjhhYjBhMDY1NzRhM2M1ODR8AAAAAM0BAAB"
                    + "%2Bks33o4y9ZNO7ZWAn7ZpEnNgC8GAsioc0v32DvZ9hoBMTYatbFEjNrQnmOpE7vkZDbvU4oVAI9j"
                    + "%2FiAzjzc9m41pH7djeOWcAlW1znawmEEicwdGIK836UNj0Ys4PtXesMgd6J714X6YgSoIBfo6LuYMuS2gCXbKzj9j"
                    + "%2BYk9WdCPJGhVcYFjncL8NeS%2FD"
                    + "%2FffGXMKwy2YDInltwjkPSnqCG53D8opkT81Du0etopBEJYcMStRqAtJOzWXlRReS"
                    + "%2BLAwburGORmv3hr8ekZwUi2jDm1X7UtcwYQ"
                    + "%2FwxiRAH8QUITFD8YacBbX0rDoaofCNTTdA8PCTpfkMEB91o6slOwd7pHTNxYLY%2FZVZ8Ek8YA%2FcGQ%3D%3D"
                    + "&authorization=bce-auth-v1%2Ff7a109894b6c11ec92ec4b25e0ca1eff%2F2021-11-22T08%3A19%3A52Z"
                    + "%2F86400%2F%2Fffa0351a935db7a4c89e89ea60ece53da23e7ef8831080a2fa956665c9cdf334\","
                    + "\"sourceUrl\":\"https://bj-bos-sandbox.baidu-int"
                    + ".com/v1/tianmai-new/video-edit/result/360p/split_result_19151"
                    + ".mp4?x-bce-security-token=ODgzNjk4N2NiNzZjNGI2NjhhYjBhMDY1NzRhM2M1ODR8AAAAAM0BAAB"
                    + "%2Bks33o4y9ZNO7ZWAn7ZpEnNgC8GAsioc0v32DvZ9hoBMTYatbFEjNrQnmOpE7vkZDbvU4oVAI9j"
                    + "%2FiAzjzc9m41pH7djeOWcAlW1znawmEEicwdGIK836UNj0Ys4PtXesMgd6J714X6YgSoIBfo6LuYMuS2gCXbKzj9j"
                    + "%2BYk9WdCPJGhVcYFjncL8NeS%2FD"
                    + "%2FffGXMKwy2YDInltwjkPSnqCG53D8opkT81Du0etopBEJYcMStRqAtJOzWXlRReS"
                    + "%2BLAwburGORmv3hr8ekZwUi2jDm1X7UtcwYQ"
                    + "%2FwxiRAH8QUITFD8YacBbX0rDoaofCNTTdA8PCTpfkMEB91o6slOwd7pHTNxYLY%2FZVZ8Ek8YA%2FcGQ%3D%3D"
                    + "&authorization=bce-auth-v1%2Ff7a109894b6c11ec92ec4b25e0ca1eff%2F2021-11-22T08%3A19%3A52Z"
                    + "%2F86400%2F%2F52dd0ffef512a6f22fd82e6a080dd11e24e09ecc94ebdc9d314683dfb7afe98c\","
                    + "\"fileType\":\"video\",\"duration\":9,\"sourceType\":\"SERVICE\",\"status\":\"FINISHED\","
                    + "\"width\":1280,\"height\":720,\"title\":\"新建作品-202111021650\",\"name\":\"新建作品-202111021650\","
                    + "\"createTime\":\"2021-11-02 16:52:31\",\"updateTime\":\"2021-11-02 16:52:53\","
                    + "\"audioKey\":\"video-edit/result/audio/split_result_19151.mp3\","
                    + "\"audioUrl\":\"https://bj-bos-sandbox.baidu-int"
                    + ".com/v1/tianmai-new/video-edit/result/audio/split_result_19151"
                    + ".mp3?x-bce-security-token=ODgzNjk4N2NiNzZjNGI2NjhhYjBhMDY1NzRhM2M1ODR8AAAAAM0BAAB"
                    + "%2Bks33o4y9ZNO7ZWAn7ZpEnNgC8GAsioc0v32DvZ9hoBMTYatbFEjNrQnmOpE7vkZDbvU4oVAI9j"
                    + "%2FiAzjzc9m41pH7djeOWcAlW1znawmEEicwdGIK836UNj0Ys4PtXesMgd6J714X6YgSoIBfo6LuYMuS2gCXbKzj9j"
                    + "%2BYk9WdCPJGhVcYFjncL8NeS%2FD"
                    + "%2FffGXMKwy2YDInltwjkPSnqCG53D8opkT81Du0etopBEJYcMStRqAtJOzWXlRReS"
                    + "%2BLAwburGORmv3hr8ekZwUi2jDm1X7UtcwYQ"
                    + "%2FwxiRAH8QUITFD8YacBbX0rDoaofCNTTdA8PCTpfkMEB91o6slOwd7pHTNxYLY%2FZVZ8Ek8YA%2FcGQ%3D%3D"
                    + "&authorization=bce-auth-v1%2Ff7a109894b6c11ec92ec4b25e0ca1eff%2F2021-11-22T08%3A19%3A52Z"
                    + "%2F86400%2F%2F83a93d65d76847e3a86375e8449bea644b806cbcbc0b5cbe01ac5df163dce2c6\","
                    + "\"thumbnailKeys\":[\"video-edit/result/thumbnail/split_result_1915100000000.jpg\"]},"
                    + "\"uid\":\"3268b533-b38a-47c5-a40a-6e2819883634\"}],\"unlinkMaster\":true}],"
                    + "\"audio\":[{\"key\":\"name\",\"list\":[]}],\"sticker\":[{\"key\":\"name\",\"list\":[]}],"
                    + "\"subtitle\":[{\"key\":\"T1\",\"list\":[]}]}},\"ratio\":\"16:9\","
                    + "\"resourceList\":[{\"id\":\"234a0b22ed0daa0545e07735170a1430\","
                    + "\"userId\":\"4b0c2ee70b3b41bfac0f70f69a46921d\",\"infoType\":\"ENTERTAINMENT\","
                    + "\"mediaType\":\"video\",\"sourceType\":\"SERVICE\",\"status\":\"FINISHED\","
                    + "\"title\":\"新建作品-202111021650\",\"sourceUrl\":\"https://bj-bos-sandbox.baidu-int"
                    + ".com/v1/tianmai-new/video-edit/result/split_result_19151"
                    + ".mp4?x-bce-security-token=ODgzNjk4N2NiNzZjNGI2NjhhYjBhMDY1NzRhM2M1ODR8AAAAAM0BAAB"
                    + "%2Bks33o4y9ZNO7ZWAn7ZpEnNgC8GAsioc0v32DvZ9hoBMTYatbFEjNrQnmOpE7vkZDbvU4oVAI9j"
                    + "%2FiAzjzc9m41pH7djeOWcAlW1znawmEEicwdGIK836UNj0Ys4PtXesMgd6J714X6YgSoIBfo6LuYMuS2gCXbKzj9j"
                    + "%2BYk9WdCPJGhVcYFjncL8NeS%2FD"
                    + "%2FffGXMKwy2YDInltwjkPSnqCG53D8opkT81Du0etopBEJYcMStRqAtJOzWXlRReS"
                    + "%2BLAwburGORmv3hr8ekZwUi2jDm1X7UtcwYQ"
                    + "%2FwxiRAH8QUITFD8YacBbX0rDoaofCNTTdA8PCTpfkMEB91o6slOwd7pHTNxYLY%2FZVZ8Ek8YA%2FcGQ%3D%3D"
                    + "&authorization=bce-auth-v1%2Ff7a109894b6c11ec92ec4b25e0ca1eff%2F2021-11-22T08%3A19%3A52Z"
                    + "%2F86400%2F%2F8dabe499a31ab6f0d6af10b5c203f08e4f2cc3061094296dceffb4b7402bb906\","
                    + "\"sourceUrl360p\":\"https://bj-bos-sandbox.baidu-int"
                    + ".com/v1/tianmai-new/video-edit/result/360p/split_result_19151"
                    + ".mp4?x-bce-security-token=ODgzNjk4N2NiNzZjNGI2NjhhYjBhMDY1NzRhM2M1ODR8AAAAAM0BAAB"
                    + "%2Bks33o4y9ZNO7ZWAn7ZpEnNgC8GAsioc0v32DvZ9hoBMTYatbFEjNrQnmOpE7vkZDbvU4oVAI9j"
                    + "%2FiAzjzc9m41pH7djeOWcAlW1znawmEEicwdGIK836UNj0Ys4PtXesMgd6J714X6YgSoIBfo6LuYMuS2gCXbKzj9j"
                    + "%2BYk9WdCPJGhVcYFjncL8NeS%2FD"
                    + "%2FffGXMKwy2YDInltwjkPSnqCG53D8opkT81Du0etopBEJYcMStRqAtJOzWXlRReS"
                    + "%2BLAwburGORmv3hr8ekZwUi2jDm1X7UtcwYQ"
                    + "%2FwxiRAH8QUITFD8YacBbX0rDoaofCNTTdA8PCTpfkMEB91o6slOwd7pHTNxYLY%2FZVZ8Ek8YA%2FcGQ%3D%3D"
                    + "&authorization=bce-auth-v1%2Ff7a109894b6c11ec92ec4b25e0ca1eff%2F2021-11-22T08%3A19%3A52Z"
                    + "%2F86400%2F%2F52dd0ffef512a6f22fd82e6a080dd11e24e09ecc94ebdc9d314683dfb7afe98c\","
                    + "\"audioUrl\":\"https://bj-bos-sandbox.baidu-int"
                    + ".com/v1/tianmai-new/video-edit/result/audio/split_result_19151"
                    + ".mp3?x-bce-security-token=ODgzNjk4N2NiNzZjNGI2NjhhYjBhMDY1NzRhM2M1ODR8AAAAAM0BAAB"
                    + "%2Bks33o4y9ZNO7ZWAn7ZpEnNgC8GAsioc0v32DvZ9hoBMTYatbFEjNrQnmOpE7vkZDbvU4oVAI9j"
                    + "%2FiAzjzc9m41pH7djeOWcAlW1znawmEEicwdGIK836UNj0Ys4PtXesMgd6J714X6YgSoIBfo6LuYMuS2gCXbKzj9j"
                    + "%2BYk9WdCPJGhVcYFjncL8NeS%2FD"
                    + "%2FffGXMKwy2YDInltwjkPSnqCG53D8opkT81Du0etopBEJYcMStRqAtJOzWXlRReS"
                    + "%2BLAwburGORmv3hr8ekZwUi2jDm1X7UtcwYQ"
                    + "%2FwxiRAH8QUITFD8YacBbX0rDoaofCNTTdA8PCTpfkMEB91o6slOwd7pHTNxYLY%2FZVZ8Ek8YA%2FcGQ%3D%3D"
                    + "&authorization=bce-auth-v1%2Ff7a109894b6c11ec92ec4b25e0ca1eff%2F2021-11-22T08%3A19%3A52Z"
                    + "%2F86400%2F%2F83a93d65d76847e3a86375e8449bea644b806cbcbc0b5cbe01ac5df163dce2c6\","
                    + "\"thumbnailList\":[\"https://bj-bos-sandbox.baidu-int"
                    + ".com/v1/tianmai-new/video-edit/result/thumbnail/split_result_1915100000000"
                    + ".jpg?x-bce-security-token=ODgzNjk4N2NiNzZjNGI2NjhhYjBhMDY1NzRhM2M1ODR8AAAAAM0BAAB"
                    + "%2Bks33o4y9ZNO7ZWAn7ZpEnNgC8GAsioc0v32DvZ9hoBMTYatbFEjNrQnmOpE7vkZDbvU4oVAI9j"
                    + "%2FiAzjzc9m41pH7djeOWcAlW1znawmEEicwdGIK836UNj0Ys4PtXesMgd6J714X6YgSoIBfo6LuYMuS2gCXbKzj9j"
                    + "%2BYk9WdCPJGhVcYFjncL8NeS%2FD"
                    + "%2FffGXMKwy2YDInltwjkPSnqCG53D8opkT81Du0etopBEJYcMStRqAtJOzWXlRReS"
                    + "%2BLAwburGORmv3hr8ekZwUi2jDm1X7UtcwYQ"
                    + "%2FwxiRAH8QUITFD8YacBbX0rDoaofCNTTdA8PCTpfkMEB91o6slOwd7pHTNxYLY%2FZVZ8Ek8YA%2FcGQ%3D%3D"
                    + "&authorization=bce-auth-v1%2Ff7a109894b6c11ec92ec4b25e0ca1eff%2F2021-11-22T08%3A19%3A52Z"
                    + "%2F86400%2F%2Fffa0351a935db7a4c89e89ea60ece53da23e7ef8831080a2fa956665c9cdf334\"],"
                    + "\"subtitleUrls\":[],\"createTime\":\"2021-11-02 16:52:31\",\"updateTime\":\"2021-11-02 "
                    + "16:52:53\",\"duration\":9,\"height\":720,\"width\":1280,\"fileSizeInByte\":2331301,"
                    + "\"thumbnailKeys\":[\"video-edit/result/thumbnail/split_result_1915100000000.jpg\"],"
                    + "\"subtitles\":[\"\"],\"bucket\":\"tianmai-new\",\"key\":\"video-edit/result/split_result_19151"
                    + ".mp4\",\"key360p\":\"video-edit/result/360p/split_result_19151.mp4\","
                    + "\"key720p\":\"video-edit/result/720p/split_result_19151.mp4\","
                    + "\"audioKey\":\"video-edit/result/audio/split_result_19151.mp3\",\"unused\":false,"
                    + "\"used\":true}],\"coverBucket\":\"tianmai-new\","
                    + "\"coverKey\":\"video-edit/result/thumbnail/split_result_1915100000000.jpg\"}";
            request= JsonUtils.fromJsonString(json, MatlibTaskRequest.class);
            client.updateDraft(19175, request);
            toJsonPrettyString("testUpdateDraft", request);
        }
        @Test
        public void testListDraftList() {
            DraftListRequest listRequest = DraftListRequest.of(1, 5);
//            listRequest.setBeginTime(DateUtils.addDays(new Date(), -6));
//            listRequest.setEndTime(DateUtils.addDays(new Date(), -2));
            listRequest.setStatus(MatlibTaskStatus.EDITING);
            ListByPageResponse<MatlibTaskGetResponse> draftList = client.getDraftList(listRequest);
            toJsonPrettyString("testListDraftList", draftList);

        }

        @Test
        public void testGetDraft() {
            long id = 1015316L;
            GetDraftResponse response = client.getSingleDraft(id);
            toJsonPrettyString("getSingleDraft",response);
        }
    }

    public static class VideoEditTest extends BvwBase {
        private String jsonString = "{\n" +
                "    \"bucket\":\"testbucket222\",\n" +
                "    \"taskId\":0,\n" +
                "    \"title\": \"changyanlong\",\n" +
                "    \"referer\":\"tianmai\",\n" +
                "    \"notification\" : \"notify1\",\n" +
                "    \"extInfo\" : {\n" +
                "        \"getCover\" : true,\n" +
                "        \"coverTime\" : 5.0,\n" +
                "        \"aspect\" : \"vert9x16\",\n" +
                "        \"duration\" : 10\n" +
                "    },\n" +
                "    \"cmd\":{\n" +
                "        \"timeline\":{\n" +
                "            \"video\":[\n" +
                "                {\n" +
                "                    \"key\": \"name\",\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"type\": \"image\",\n" +
                "                            \"start\": 0,\n" +
                "                            \"end\": 7,\n" +
                "                            \"showStart\": 0,\n" +
                "                            \"showEnd\": 7,\n" +
                "                            \"width\": 0.8,\n" +
                "                            \"height\": 0.8,\n" +
                "                            \"xpos\": 0.1,\n" +
                "                            \"ypos\": 0.1,\n" +
                "                            \"duration\": 7,\n" +
                "                            \"extInfo\": {\n" +
                "                                \"style\": \"\",\n" +
                "                                \"lightness\": 0,\n" +
                "                                \"contrast\": 0,\n" +
                "                                \"saturation\": 0,\n" +
                "                                \"hue\": 0,\n" +
                "                                \"speed\": 1,\n" +
                "                                \"transitionStart\": \"white\",\n" +
                "                                \"transitionEnd\": \"black\",\n" +
                "                                \"transitionDuration\": 1,\n" +
                "                                \"rotate\" : 0,\n" +
                "                                \"volume\" : 0,\n" +
                "                                \"zoom\":\"right\",\n" +
                "                                \"blankArea\":\"gauss\"\n" +
                "                            },\n" +
                "                            \"mediaInfo\": {\n" +
                "                                \"key\": \"test2.jpg\",\n" +
                "                                \"bucket\": \"testbucket222\",\n" +
                "                                \"fileType\": \"image\",\n" +
                "                                \"width\": 1600,\n" +
                "                                \"height\": 900,\n" +
                "                                \"offstandard\": true\n" +
                "                            }\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"audio\":[],\n" +
                "            \"sticker\":[],\n" +
                "            \"subtitle\" : [\n" +
                "                {\n" +
                "                    \"key\": \"name\",\n" +
                "                    \"list\": [\n" +
                "                        {\n" +
                "                            \"templateId\":0,\n" +
                "                            \"tag\":\"center-with-bottom-margin\",\n" +
                "                            \"tagExtInfo\" : {\n" +
                "                                \"marginBottom\" : 0.5\n" +
                "                            },\n" +
                "                            \"xpos\":0.0,\n" +
                "                            \"ypos\":0.0,\n" +
                "                            \"showStart\":0,\n" +
                "                            \"showEnd\":10,\n" +
                "                            \"duration\":10,\n" +
                "                            \"config\":[\n" +
                "                                {\n" +
                "                                    \"fontx\":0.0,\n" +
                "                                    \"fonty\":0.5,\n" +
                "                                    \"fontSize\":45,\n" +
                "                                    \"fontType\":\"PingFangSC-Regular\",\n" +
                "                                    \"fontColor\":\"#FFFFFF\",\n" +
                "                                    \"text\":\"新区网信系统将通过专题\",\n" +
                "                                    \"alpha\":0.1,\n" +
                "                                    \"fontStyle\":\"normal\",\n" +
                "                                    \"textAlign\":\"bottomCenter\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    }\n" +
                "}";

        private String notification = "notify1";
        private long editId = 15529;

        @Test
        public void testCreateVideoEdit() {
            JsonNode jsonNode = JsonUtils.jsonNodeOf(jsonString);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> result = mapper.convertValue(jsonNode, Map.class);

            VideoEditCreateRequest request = new VideoEditCreateRequest(
                    result, RefererType.tianmai, notification);
            log.info(result.toString());

            VideoEditCreateResponse response = client.createVideoEdit(request);
            assertThat(response, notNullValue());
            toJsonPrettyString("createVideoEdit", response);
        }

        @Test
        public void testPollingVideoEdit() {
            VideoEditPollingResponse response = client.pollingVideoEdit(editId);
            assertThat(response, notNullValue());
            toJsonPrettyString("pollingVideoEdit", response);
        }

    }


    public static class TimelineTest extends BvwBase {

        private String timeline = "{\"timeline\":{\"video\":[{\"key\":\"name\",\"isMaster\":\"true\",\"list\":[{\"start\":0,\"end\":6,\"showStart\":0," +
                    "\"showEnd\":6,\"duration\":6,\"width\":1,\"height\":1,\"mediaInfo\":{\"fileType\":\"video\",\"sourceType\":\"SERVICE\"," +
                    "\"bucket\":\"videoworks-system-preprocess\",\"key\":\"vidpress/timeline/kiajw3dquk4nme0uypb.mov\"," +
                    "\"instanceId\":\"ins-kiaj2pd35wfv1bau\",\"coverImage\":\"\",\"duration\":6,\"name\":\"\",\"thumbnailList\":[],\"mediaId\":\"\"}," +
                    "\"type\":\"video\",\"uid\":\"uid-kiadh1htsrpsekrs\",\"name\":\"\",\"extInfo\":{\"style\":\"\",\"lightness\":0,\"contrast\":0," +
                    "\"saturation\":0,\"hue\":0,\"speed\":1,\"transitionStart\":\"\",\"transitionEnd\":\"\",\"transitionDuration\":1,\"volume\":0," +
                    "\"rotate\":0,\"crop\":{\"width\":1,\"height\":1}}}]}],\"audio\":[{\"key\":\"name\",\"list\":[{\"start\":0,\"end\":3.89,\"showStart\":5," +
                    "\"showEnd\":8.89,\"duration\":3.89,\"mediaInfo\":{\"fileType\":\"audio\",\"sourceType\":\"SERVICE\",\"bucket\":\"videoworks-system-preprocess\"," +
                    "\"key\":\"vidpress/timeline/kiaji6uvxsqgf2dqu15.mp3\",\"instanceId\":\"ins-kiajpjeyfx10nq1k\",\"coverImage\":\"\",\"duration\":3.89,\"name\":\"\"," +
                    "\"thumbnailList\":[],\"mediaId\":\"\"},\"type\":\"audio\",\"uid\":\"uid-kiadmh3ed7j1dd0b\",\"name\":\"\",\"extInfo\":{\"style\":\"\",\"lightness\":0," +
                    "\"contrast\":0,\"saturation\":0,\"hue\":0,\"speed\":1,\"transitionStart\":\"\",\"transitionEnd\":\"\",\"transitionDuration\":1," +
                    "\"volume\":1,\"rotate\":0}}]}],\"sticker\":[{\"key\":\"name\",\"list\":[]}],\"subtitle\":[{\"key\":\"name\",\"list\":[{\"duration\":3.89," +
                    "\"showStart\":5,\"showEnd\":8.89,\"type\":\"text\",\"uid\":\"uid-kiadmgw832i70vie\",\"" +
                    "xpos\":\"center\",\"ypos\":\"bottom\",\"config\":[{\"fontColor\":\"0xFFFFFF\",\"fontSize\":46," +
                    "\"fontStyle\":\"caption\",\"fontType\":\"Source-Han-Sans-CN\",\"text\":\"融雪剂，是指可以降低冰雪融化温度的药剂。\"}]}]}]}}";


        @Test
        public void TimelineTest() {
            Timeline bvwTimeline = JsonUtils.fromJsonString(timeline, Timeline.class);
            bvwTimeline.generalSignedUrl(bosClient);
            assertThat(bvwTimeline.getTimeline().getVideo()
                        .get(0).getList()
                        .get(0).getMediaInfo()
                        .getSourceUrl(), notNullValue());
            assertEquals(bvwTimeline.getTimeline().getVideo()
                        .get(0).getList()
                        .get(0).getMediaInfo()
                        .getAudioKey(), "vidpress/timeline/audio/kiajw3dquk4nme0uypb.mp3");
            assertThat(bvwTimeline.getTimeline().getVideo()
                        .get(0).getList()
                        .get(0).getMediaInfo()
                        .getAudioUrl(), notNullValue());
            String jsonString = JsonUtils.toJsonString(bvwTimeline);
            bvwTimeline = JsonUtils.fromJsonString(jsonString, Timeline.class);
            assertThat(bvwTimeline.getTimeline().getVideo()
                        .get(0).getList()
                        .get(0).getExtInfo(),
                        notNullValue());

        }
    }

}

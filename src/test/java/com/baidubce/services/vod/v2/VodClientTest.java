package com.baidubce.services.vod.v2;

import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.vod.v2.model.ComposeCreateRequest;
import com.baidubce.services.vod.v2.model.FileUploadRequest;
import com.baidubce.services.vod.v2.model.GenericMediaResponse;
import com.baidubce.services.vod.v2.model.GenericTaskResponse;
import com.baidubce.services.vod.v2.model.ListByMarkerResponse;
import com.baidubce.services.vod.v2.model.MediaBasicInfo;
import com.baidubce.services.vod.v2.model.MediaCompleteUploadRequest;
import com.baidubce.services.vod.v2.model.MediaDetail;
import com.baidubce.services.vod.v2.model.MediaFetchRequest;
import com.baidubce.services.vod.v2.model.MediaListRequest;
import com.baidubce.services.vod.v2.model.MediaOutputDeleteRequest;
import com.baidubce.services.vod.v2.model.MediaPresetTaskInput;
import com.baidubce.services.vod.v2.model.MediaProcessRequest;
import com.baidubce.services.vod.v2.model.MediaTaskBasicInfo;
import com.baidubce.services.vod.v2.model.MediaTaskDetail;
import com.baidubce.services.vod.v2.model.MediaTaskListRequest;
import com.baidubce.services.vod.v2.model.MediaUpdateRequest;
import com.baidubce.services.vod.v2.model.MediaUploadResponse;
import com.baidubce.services.vod.v2.model.MediaWorkflowTaskInput;
import com.baidubce.services.vod.v2.model.VodUploadProgressCallback;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Objects;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.CustomMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
@Slf4j
public class VodClientTest {

    @Ignore
    public static class VodBase {

        protected static String ak = "this is your ak";
        protected static String sk = "this is your sk";
        protected static String endpoint = "vod.bj.baidubce.com";

        @Rule
        public ExpectedException thrown = ExpectedException.none();
        protected VodClientConfiguration config;
        protected VodClient client;

        @Before
        public void setUp() {
            this.config = new VodClientConfiguration().withCredentials(new DefaultBceCredentials(ak, sk)).withEndpoint(endpoint);
            this.client = new VodClient(config);
        }

        @After
        public void tearDown() {
            this.client.shutdown();
        }

        protected void expectBceServiceException(final int statusCode, final String errorCode) {
            this.thrown.expect(new CustomMatcher<Throwable>("BceServiceException [ statusCode=" + statusCode + ", errorCode=" + errorCode + "]") {
                @Override
                public boolean matches(Object item) {
                    return (item instanceof BceServiceException) &&
                            ((BceServiceException) item).getStatusCode() == statusCode &&
                            Objects.equal(((BceServiceException) item).getErrorCode(), errorCode);
                }
            });
        }

        public void toJsonPrettyString(String method, Object object) {
            try {
                System.out.println("method:\n\t " + method + "\n\t" + " response:\n\t " + JsonUtils.toJsonPrettyString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }

    public static class MediaTest extends VodBase {

        @Test
        public void testCreateUpload() {
            MediaUploadResponse response = client.createUpload("demo", "mp4");
            toJsonPrettyString("createUpload", response);
        }

        @Test
        public void testCreateMultipartUpload() {
            MediaUploadResponse response = client.createUpload("demo", "mp4", 10);
            toJsonPrettyString("createMultipartUpload", response);
        }

        @Test
        public void testCompleteUpload() {
            MediaCompleteUploadRequest mediaCompleteUploadRequest = new MediaCompleteUploadRequest();
            mediaCompleteUploadRequest.setSessionKey("ZEcxd0wyVm9kbkZvYzNSaGJuSmpaR2RxTldjeWRub3dMMlZxYlhGbk0yWXdjSGQzZFhCa2RHbG1jRE0zTG0xd05BPT1fX1pHVnRidz09");
            GenericMediaResponse response = client.completeUpload(mediaCompleteUploadRequest);
            toJsonPrettyString("completeUpload", response);
        }

        @Test
        public void testCreateMediaFromFile() {
            FileUploadRequest request = new FileUploadRequest();
            request.setFile(new File("/Users/guichen01/Downloads/test1.mp4"));
            GenericMediaResponse genericMediaResponse = client.createMedia(request, "full1", "mp4");
            toJsonPrettyString("testCreateMediaFromFile", genericMediaResponse);
        }

        @Test
        public void testCreateMediaFromStream() {
            FileUploadRequest request = new FileUploadRequest();
            request.setInputStream(new ByteArrayInputStream("test_data".getBytes()));
            GenericMediaResponse genericMediaResponse = client.createMedia(request, "full1", "mp4");
            toJsonPrettyString("testCreateMediaFromFile", genericMediaResponse);
        }

        @Test
        public void testCreateMediaWithProgressCallback() {
            VodUploadProgressCallback<Object> callback = new VodUploadProgressCallback<Object>() {
                @Override
                public void onProgress(long currentSize, long totalSize, Object data) {
                    System.out.println("put " + currentSize + "/" + totalSize);
                }
            };
            FileUploadRequest request = new FileUploadRequest();
            request.setFile(new File("/Users/guichen01/Downloads/test1.mp4"));
            request.setProgressCallback(callback);
            GenericMediaResponse genericMediaResponse = client.createMedia(request, "full1", "mp4");
            toJsonPrettyString("testFileUploadWithProgressCallback", genericMediaResponse);
        }

        @Test
        public void testCreateMediaWithTrafficLimit() {
            FileUploadRequest request = new FileUploadRequest();
            request.setFile(new File("/Users/guichen01/Downloads/test1.mp4"));
            request.setTrafficLimitBitPS(819200);
            VodUploadProgressCallback<Object> callback = new VodUploadProgressCallback<Object>() {
                @Override
                public void onProgress(long currentSize, long totalSize, Object data) {
                    System.out.println(System.currentTimeMillis() + ", put " + currentSize + "/" + totalSize);
                }
            };
            request.setProgressCallback(callback);
            GenericMediaResponse genericMediaResponse = client.createMedia(request, "full1", "mp4");
            toJsonPrettyString("testCreateMediaWithTrafficLimit", genericMediaResponse);
        }

        @Test
        public void testCreateMediaMultipartInSeries() {
            FileUploadRequest request = new FileUploadRequest();
            request.setFile(new File("/Users/guichen01/Downloads/test1.mp4"));
            List<VodUploadProgressCallback<?>> callbacks = new ArrayList<>();
            int numParts = 5;
            for (int i = 0; i < numParts; i++) {
                int finalI = i;
                VodUploadProgressCallback<Object> callback = new VodUploadProgressCallback<Object>() {
                    @Override
                    public void onProgress(long currentSize, long totalSize, Object data) {
                        System.out.println(System.currentTimeMillis() + ", part " + finalI + ", put " + currentSize + "/" + totalSize);
                    }
                };
                callbacks.add(callback);
            }
            request.setMultipartProgressCallbacks(callbacks);
            GenericMediaResponse genericMediaResponse = client.createMedia(request, "demopart", "mp4", numParts);
            toJsonPrettyString("testCreateMediaMultipartInSeries", genericMediaResponse);
        }

        @Test
        public void testCreateMediaMultipartInParallel() {
            FileUploadRequest request = new FileUploadRequest();
            request.setFile(new File("/Users/guichen01/Downloads/test1.mp4"));
            List<VodUploadProgressCallback<?>> callbacks = new ArrayList<>();
            int numParts = 5;
            for (int i = 0; i < numParts; i++) {
                int finalI = i;
                VodUploadProgressCallback<Object> callback = new VodUploadProgressCallback<Object>() {
                    @Override
                    public void onProgress(long currentSize, long totalSize, Object data) {
                        System.out.println(System.currentTimeMillis() + ", part " + finalI + ", put " + currentSize + "/" + totalSize);
                    }
                };
                callbacks.add(callback);
            }
            request.setMultipartUploadParallelism(5);
            request.setMultipartProgressCallbacks(callbacks);
            GenericMediaResponse genericMediaResponse = client.createMedia(request, "demopart", "mp4", numParts);
            toJsonPrettyString("testCreateMediaMultipartInParallel", genericMediaResponse);
        }

        @Test
        public void testFetchMedia() {
            MediaFetchRequest request = new MediaFetchRequest();
            request.setUrl("http://vod.com/test/test_2.mp4");
            GenericTaskResponse response = client.fetchMedia(request);
            toJsonPrettyString("testFetchMedia", response);
        }

        @Test
        public void testGetMedia() {
            MediaDetail media = client.getMedia("mda-emfq3hthsxm1smb8");
            toJsonPrettyString("getMedia", media);
        }

        @Test
        public void testListMedia() {
            MediaListRequest mediaSearchRequest = new MediaListRequest().withMaxSize(2);
            ListByMarkerResponse<MediaBasicInfo> markerResponse = client.listMedia(mediaSearchRequest);
            toJsonPrettyString("searchMedia", markerResponse);
        }

        @Test
        public void testUpdateMedia() {
            MediaUpdateRequest mediaUpdateRequest = new MediaUpdateRequest().withName("test");
            client.updateMedia("mda-emfmfetbjn78zbzv", mediaUpdateRequest);
        }

        @Test
        public void testDeleteMediaOutput() {
            MediaOutputDeleteRequest mediaDeleteOutputRequest = new MediaOutputDeleteRequest().withTranscodeOutputIds(Collections.singletonList("ehxsinzcqxvaf96ayzz1"));
            client.deleteMediaOutput("mda-eievu3qxas29khqz", mediaDeleteOutputRequest);
        }

        @Test
        public void testDeleteMedia() {
            client.deleteMedia("mda-eievu3qxas29khqz");
        }

        @Test
        public void testBanMedia() {
            client.banMedia("mda-eiethsi0sfc076ut");
        }

        @Test
        public void testUnBanMedia() {
            client.unBanMedia("mda-eiethsi0sfc076ut");
        }

    }

    public static class MediaProcessTest extends VodBase {

        @Test
        public void testProcessMediaByWorkflow() {
            MediaProcessRequest processRequest =
                    new MediaProcessRequest().withMediaId("mda-ejmtptptju5qra8b").withWorkflow(new MediaWorkflowTaskInput().withWorkflowId("ehvqys1z26jdr9hrretk"));
            GenericTaskResponse response = client.processMedia(processRequest);
            toJsonPrettyString("testProcessMediaByWorkflow", response);
        }

        @Test
        public void testProcessMediaByPreset() {
            MediaProcessRequest processRequest = new MediaProcessRequest().withMediaId("mda-ejmtptptju5qra8b")
                    .withPreset(new MediaPresetTaskInput().withPresetIds(Collections.singletonList("ehxsinzcqxvaf96ayzz1")));
            GenericTaskResponse response = client.processMedia(processRequest);
            toJsonPrettyString("testProcessMediaByWorkflow", response);
        }

        @Test
        public void testComposeMedia() {
            ComposeCreateRequest composeCreateRequest = JsonUtils.fromJsonString("{\n" +
                    "    \"timeline\": {\n" +
                    "        \"videoTracks\": [\n" +
                    "            {\n" +
                    "                \"videoItems\": [\n" +
                    "                    {\n" +
                    "                        \"mediaId\": \"\",\n" +
                    "                        \"sourceUrl\": \"https://hellolyq.bj.bcebos.com/aipe.mov\",\n" +
                    "                        \"type\": \"video\",\n" +
                    "                        \"start\": 0,\n" +
                    "                        \"duration\": 3,\n" +
                    "                        \"showStart\": 0,\n" +
                    "                        \"showDuration\": 3,\n" +
                    "                        \"audioOperations\": [\n" +
                    "                            {\n" +
                    "                                \"volume\": 1\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"imageOperations\": [\n" +
                    "                            {\n" +
                    "                                \"crop\": {\n" +
                    "                                    \"xpos\": 0.9174552190373486,\n" +
                    "                                    \"ypos\": 0.11957680757504985,\n" +
                    "                                    \"width\": 0.6454682729670298,\n" +
                    "                                    \"height\": 0.30077290133126655\n" +
                    "                                }\n" +
                    "                            }\n" +
                    "                        ],\n" +
                    "                        \"xpos\": 1,\n" +
                    "                        \"ypos\": 1,\n" +
                    "                        \"width\": 1,\n" +
                    "                        \"height\": 1\n" +
                    "                    }\n" +
                    "                ]\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"audioTracks\": [\n" +
                    "            {\n" +
                    "                \"audioItems\": [\n" +
                    "                    {\n" +
                    "                        \"sourceUrl\": \"https://hellolyq.bj.bcebos.com/aipe.mov\",\n" +
                    "                        \"type\": \"audio\",\n" +
                    "                        \"start\": 0,\n" +
                    "                        \"duration\": 3,\n" +
                    "                        \"showStart\": 0,\n" +
                    "                        \"showDuration\": 3,\n" +
                    "                        \"audioOperations\": [\n" +
                    "                            {\n" +
                    "                                \"volume\": 1\n" +
                    "                            }\n" +
                    "                        ]\n" +
                    "                    }\n" +
                    "                ]\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    },\n" +
                    "    \"output\": {\n" +
                    "        \"fileName\": \"test\",\n" +
                    "        \"compressionType\": \"low\"\n" +
                    "    }\n" +
                    "}", ComposeCreateRequest.class);
            GenericTaskResponse response = client.composeMedia(composeCreateRequest);
            toJsonPrettyString("composeMedia", response);
        }

    }

    public static class MediaTaskTest extends VodBase {

        @Test
        public void testListTasks() {
            MediaTaskListRequest mediaTaskListRequest = new MediaTaskListRequest().withMaxSize(2);
            ListByMarkerResponse<MediaTaskBasicInfo> response = client.listMediaTask(mediaTaskListRequest);
            toJsonPrettyString("listTasks", response);
        }

        @Test
        public void testGetMediaTask() {
            MediaTaskDetail response = client.getMediaTask("tsk-emcqunct3va5p9et");
            toJsonPrettyString("getMediaTask", response);
        }

    }

}

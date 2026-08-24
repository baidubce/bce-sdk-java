package com.baidubce.services.bes.v2;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.services.bes.v2.model.cluster.*;
import com.baidubce.services.bes.v2.model.config.*;
import com.baidubce.services.bes.v2.model.inspect.*;
import com.baidubce.services.bes.v2.model.instance.*;
import com.baidubce.services.bes.v2.model.log.*;
import com.baidubce.services.bes.v2.model.plugin.*;
import com.baidubce.services.bes.v2.model.renew.*;
import com.baidubce.services.bes.v2.model.schedule.*;
import com.baidubce.services.bes.v2.model.tag.*;
import com.baidubce.util.JsonUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.UUID;

public class BesClientV2Test {

    @Test
    public void shouldBuildCreateClusterRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        CreateClusterRequest request = validCreateClusterRequest();

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST, "/api/bes/cluster", "/create");

        Assert.assertEquals(HttpMethodName.POST, internalRequest.getHttpMethod());
        Assert.assertEquals("bj", internalRequest.getHeaders().get("x-Region"));
        Assert.assertEquals("application/json;charset=UTF-8", internalRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertNotNull(internalRequest.getHeaders().get(Headers.CONTENT_LENGTH));
        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/create"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectCreateClusterWithNullRequest() {
        newClient().createCluster(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectCreateClusterWithoutName() {
        CreateClusterRequest request = validCreateClusterRequest();
        request.setName(null);
        newClient().createCluster(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectCreateClusterWithoutModules() {
        CreateClusterRequest request = validCreateClusterRequest();
        request.setModules(null);
        newClient().createCluster(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectCreateClusterWithoutBilling() {
        CreateClusterRequest request = validCreateClusterRequest();
        request.setBilling(null);
        newClient().createCluster(request);
    }

    @Test
    public void shouldBuildListClustersRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        ClusterListRequest request = new ClusterListRequest();
        request.setPageNo(1);
        request.setPageSize(10);

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/v2", "/list");

        Assert.assertEquals(HttpMethodName.POST, internalRequest.getHttpMethod());
        Assert.assertEquals("bj", internalRequest.getHeaders().get("x-Region"));
        Assert.assertEquals("application/json;charset=UTF-8", internalRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/v2/list"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectListClustersWithoutPageNo() {
        ClusterListRequest request = new ClusterListRequest();
        request.setPageSize(10);
        newClient().listClusters(request);
    }

    @Test
    public void shouldBuildStartClusterRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        ClusterStartRequest request = new ClusterStartRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/start");

        Assert.assertEquals(HttpMethodName.POST, internalRequest.getHttpMethod());
        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/start"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectStartClusterWithoutClusterId() {
        newClient().startCluster(new ClusterStartRequest());
    }

    @Test
    public void shouldBuildStopInstanceRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InstanceStopRequest request = new InstanceStopRequest();
        request.setClusterId("cluster-123");
        request.setInstanceId("instance-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/v2", "/instance", "/stop");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/v2/instance/stop"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectStopInstanceWithoutInstanceId() {
        InstanceStopRequest request = new InstanceStopRequest();
        request.setClusterId("cluster-123");
        newClient().stopInstance(request);
    }

    @Test
    public void shouldBuildBatchStopInstanceRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InstanceBatchStopRequest request = new InstanceBatchStopRequest();
        request.setClusterId("cluster-123");
        request.setInstanceIdList(Arrays.asList("instance-123"));

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/v2", "/instance", "/batchStop");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/v2/instance/batchStop"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBatchStopInstanceWithoutInstanceIdList() {
        InstanceBatchStopRequest request = new InstanceBatchStopRequest();
        request.setClusterId("cluster-123");
        newClient().batchStopInstance(request);
    }

    @Test
    public void shouldBuildDeleteInstanceRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InstanceDeleteRequest request = new InstanceDeleteRequest();
        request.setClusterId("cluster-123");
        request.setInstanceIdList(Arrays.asList("instance-123"));
        request.setModuleType("es_node");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/v2", "/instance", "/delete");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/v2/instance/delete"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectDeleteInstanceWithoutModuleType() {
        InstanceDeleteRequest request = new InstanceDeleteRequest();
        request.setClusterId("cluster-123");
        request.setInstanceIdList(Arrays.asList("instance-123"));
        newClient().deleteInstance(request);
    }

    @Test
    public void shouldBuildGetScaleInInstancesRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        ScaleInInstancesRequest request = new ScaleInInstancesRequest();
        request.setClusterId("cluster-123");
        request.setModuleType("es_node");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/v2", "/scalein", "/instances");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/v2/scalein/instances"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectGetScaleInInstancesWithoutModuleType() {
        ScaleInInstancesRequest request = new ScaleInInstancesRequest();
        request.setClusterId("cluster-123");
        newClient().getScaleInInstances(request);
    }

    @Test
    public void shouldBuildConfirmDataMigrationRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        DataMigrationConfirmRequest request = new DataMigrationConfirmRequest();
        request.setClusterId("cluster-123");
        request.setModuleType("es_node");
        request.setInstanceIdList(Arrays.asList("instance-123"));

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/migrate/v1", "/confirm");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/migrate/v1/confirm"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectConfirmDataMigrationWithoutInstanceIdList() {
        DataMigrationConfirmRequest request = new DataMigrationConfirmRequest();
        request.setClusterId("cluster-123");
        request.setModuleType("es_node");
        newClient().confirmDataMigration(request);
    }

    @Test
    public void shouldBuildSuggestMigrationInstancesRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        DataMigrationSuggestRequest request = new DataMigrationSuggestRequest();
        request.setClusterId("cluster-123");
        request.setModuleType("es_node");
        request.setMigrateCount(2);

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/migrate/v1", "/suggestInstances");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/migrate/v1/suggestInstances"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectSuggestMigrationInstancesWithoutMigrateCount() {
        DataMigrationSuggestRequest request = new DataMigrationSuggestRequest();
        request.setClusterId("cluster-123");
        request.setModuleType("es_node");
        newClient().suggestMigrationInstances(request);
    }

    @Test
    public void shouldBuildInstallDefaultPluginRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        DefaultPluginInstallRequest request = new DefaultPluginInstallRequest();
        request.setClusterId("cluster-123");
        request.setPluginName("baidu-rate-limiting");
        request.setModuleType("es_node");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/default_plugin", "/install");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/default_plugin/install"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInstallDefaultPluginWithoutPluginName() {
        DefaultPluginInstallRequest request = new DefaultPluginInstallRequest();
        request.setClusterId("cluster-123");
        request.setModuleType("es_node");
        newClient().installDefaultPlugin(request);
    }

    @Test
    public void shouldBuildGetPluginInfoRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        PluginInfoRequest request = new PluginInfoRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/plugin", "/info");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/plugin/info"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectGetPluginInfoWithoutClusterId() {
        newClient().getPluginInfo(new PluginInfoRequest());
    }

    @Test
    public void shouldBuildGetNlpDictRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        NlpDictDisplayRequest request = new NlpDictDisplayRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/nlp_dict", "/display");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/nlp_dict/display"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectGetNlpDictWithoutClusterId() {
        newClient().getNlpDict(new NlpDictDisplayRequest());
    }

    @Test
    public void shouldBuildUploadCustomPluginMultipartRequest() throws Exception {
        BesClientV2 client = newClient();
        File tempFile = File.createTempFile("custom-plugin", ".zip");
        tempFile.deleteOnExit();
        Files.write(tempFile.toPath(), "zip-bytes".getBytes(StandardCharsets.UTF_8));

        Method buildBody = BesClientV2.class.getDeclaredMethod("buildFileOnlyMultipartBody", File.class);
        buildBody.setAccessible(true);
        Object multipartBody = buildBody.invoke(client, tempFile);
        String body = readMultipartBody(multipartBody);
        String boundary = readMultipartBoundary(multipartBody);
        long contentLength = readMultipartContentLength(multipartBody);

        Assert.assertTrue(body.contains("Content-Disposition: form-data; name=\"file\"; filename=\""
                + tempFile.getName() + "\""));
        Assert.assertTrue(body.contains("Content-Type: application/zip"));
        Assert.assertTrue(body.contains("zip-bytes"));
        Assert.assertTrue(body.contains("--" + boundary + "--"));
        Assert.assertEquals(body.getBytes(StandardCharsets.UTF_8).length, contentLength);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectUploadCustomPluginWithoutFile() {
        CustomPluginUploadRequest request = new CustomPluginUploadRequest();
        request.setClusterId("cluster-123");
        request.setModuleType("es_node");
        newClient().uploadCustomPlugin(request);
    }

    @Test
    public void shouldBuildUpdateNlpDictMultipartRequest() throws Exception {
        BesClientV2 client = newClient();
        File tempFile = File.createTempFile("nlp-dict", ".txt");
        tempFile.deleteOnExit();
        Files.write(tempFile.toPath(), "dict-content".getBytes(StandardCharsets.UTF_8));
        NlpDictUpdateRequest request = new NlpDictUpdateRequest();
        request.setClusterId("cluster-123");
        request.setSeparator("tab");
        request.setFile(tempFile);

        Method buildBody = BesClientV2.class.getDeclaredMethod("buildNlpDictUpdateMultipartBody", NlpDictUpdateRequest.class);
        buildBody.setAccessible(true);
        Object multipartBody = buildBody.invoke(client, request);
        String body = readMultipartBody(multipartBody);
        String boundary = readMultipartBoundary(multipartBody);
        long contentLength = readMultipartContentLength(multipartBody);

        Assert.assertTrue(body.contains("Content-Disposition: form-data; name=\"clusterId\""));
        Assert.assertTrue(body.contains("cluster-123"));
        Assert.assertTrue(body.contains("Content-Disposition: form-data; name=\"separator\""));
        Assert.assertTrue(body.contains("tab"));
        Assert.assertTrue(body.contains("Content-Disposition: form-data; name=\"file\"; filename=\""
                + tempFile.getName() + "\""));
        Assert.assertTrue(body.contains("Content-Type: text/plain"));
        Assert.assertTrue(body.contains("dict-content"));
        Assert.assertTrue(body.contains("--" + boundary + "--"));
        Assert.assertEquals(body.getBytes(StandardCharsets.UTF_8).length, contentLength);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectUpdateNlpDictWithoutSeparator() {
        NlpDictUpdateRequest request = new NlpDictUpdateRequest();
        request.setClusterId("cluster-123");
        newClient().updateNlpDict(request);
    }

    @Test
    public void shouldRestartMultipartBodyAndReplayIdenticalBytes() throws Exception {
        BesClientV2 client = newClient();
        File tempFile = File.createTempFile("nlp-dict-restart", ".txt");
        tempFile.deleteOnExit();
        Files.write(tempFile.toPath(), "dict-content-for-restart-test".getBytes(StandardCharsets.UTF_8));
        NlpDictUpdateRequest request = new NlpDictUpdateRequest();
        request.setClusterId("cluster-123");
        request.setSeparator("tab");
        request.setFile(tempFile);

        Method buildBody = BesClientV2.class.getDeclaredMethod("buildNlpDictUpdateMultipartBody", NlpDictUpdateRequest.class);
        buildBody.setAccessible(true);
        Object multipartBody = buildBody.invoke(client, request);

        java.lang.reflect.Field contentField = multipartBody.getClass().getDeclaredField("content");
        contentField.setAccessible(true);
        RestartableInputStream content = (RestartableInputStream) contentField.get(multipartBody);

        String firstRead = readBody(content);
        content.restart();
        String secondRead = readBody(content);

        Assert.assertEquals(firstRead, secondRead);
        Assert.assertTrue(firstRead.contains("dict-content-for-restart-test"));
    }

    @Test
    public void shouldRejectUploadWithMissingFile() throws Exception {
        BesClientV2 client = newClient();
        File missingFile = new File("/tmp/does-not-exist-" + UUID.randomUUID() + ".txt");
        Method buildBody = BesClientV2.class.getDeclaredMethod("buildFileOnlyMultipartBody", File.class);
        buildBody.setAccessible(true);
        try {
            buildBody.invoke(client, missingFile);
            Assert.fail("expected IllegalArgumentException");
        } catch (java.lang.reflect.InvocationTargetException e) {
            Assert.assertTrue(e.getCause() instanceof IllegalArgumentException);
        }
    }

    @Test
    public void shouldRejectUploadWithOversizedFile() throws Exception {
        BesClientV2 client = newClient();
        File tempFile = File.createTempFile("oversized", ".bin");
        tempFile.deleteOnExit();
        File oversizedFile = new File(tempFile.getPath()) {
            @Override
            public long length() {
                return 5L * 1024 * 1024 * 1024 + 1;
            }
        };
        Method buildBody = BesClientV2.class.getDeclaredMethod("buildFileOnlyMultipartBody", File.class);
        buildBody.setAccessible(true);
        try {
            buildBody.invoke(client, oversizedFile);
            Assert.fail("expected IllegalArgumentException");
        } catch (java.lang.reflect.InvocationTargetException e) {
            Assert.assertTrue(e.getCause() instanceof IllegalArgumentException);
        }
    }

    @Test
    public void shouldSanitizeFilenameWithQuotesAndControlChars() throws Exception {
        Method sanitize = BesClientV2.class.getDeclaredMethod("sanitizeFilename", String.class);
        sanitize.setAccessible(true);
        String result = (String) sanitize.invoke(null, "evil\"name\r\n.txt");
        Assert.assertEquals("evil\\\"name.txt", result);
    }

    private static String readMultipartBody(Object multipartBody) throws Exception {
        java.lang.reflect.Field contentField = multipartBody.getClass().getDeclaredField("content");
        contentField.setAccessible(true);
        RestartableInputStream content = (RestartableInputStream) contentField.get(multipartBody);
        return readBody(content);
    }

    private static String readMultipartBoundary(Object multipartBody) throws Exception {
        java.lang.reflect.Field boundaryField = multipartBody.getClass().getDeclaredField("boundary");
        boundaryField.setAccessible(true);
        return (String) boundaryField.get(multipartBody);
    }

    private static long readMultipartContentLength(Object multipartBody) throws Exception {
        java.lang.reflect.Field lengthField = multipartBody.getClass().getDeclaredField("contentLength");
        lengthField.setAccessible(true);
        return (Long) lengthField.get(multipartBody);
    }

    @Test
    public void shouldBuildUpdateLogSettingsRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        LogSettingsRequest request = new LogSettingsRequest();
        request.setClusterId("cluster-123");
        request.setIndexSlowLog(true);
        request.setSearchSlowLog(true);
        request.setAuditLog(true);

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/es_log_view_new", "/settings");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/es_log_view_new/settings"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectUpdateLogSettingsWithoutAuditLog() {
        LogSettingsRequest request = new LogSettingsRequest();
        request.setClusterId("cluster-123");
        request.setIndexSlowLog(true);
        request.setSearchSlowLog(true);
        newClient().updateLogSettings(request);
    }

    @Test
    public void shouldBuildSearchLogRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        LogSearchRequest request = new LogSearchRequest();
        request.setClusterId("cluster-123");
        request.setPageNo(1);
        request.setPageSize(10);
        request.setStartTime("2023-11-02 14:24:21");
        request.setEndTime("2023-11-09 14:24:21");
        request.setType("mainLog");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/es_log_view_new", "/search");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/es_log_view_new/search"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectSearchLogWithoutType() {
        LogSearchRequest request = new LogSearchRequest();
        request.setClusterId("cluster-123");
        request.setPageNo(1);
        request.setPageSize(10);
        request.setStartTime("2023-11-02 14:24:21");
        request.setEndTime("2023-11-09 14:24:21");
        newClient().searchLog(request);
    }

    @Test
    public void shouldBuildCreateLogExportTaskRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        LogExportRequest request = new LogExportRequest();
        request.setClusterId("cluster-123");
        request.setTypes(Arrays.asList("mainLog"));

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/es_log_view_new", "/export");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/es_log_view_new/export"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectCreateLogExportTaskWithNullRequest() {
        newClient().createLogExportTask(null);
    }

    @Test
    public void shouldBuildGetLogExportRecordRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        LogExportRecordRequest request = new LogExportRecordRequest();
        request.setClusterId("cluster-123");
        request.setTaskId("task-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/es_log_view_new", "/export_record");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/es_log_view_new/export_record"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectGetLogExportRecordWithNullRequest() {
        newClient().getLogExportRecord(null);
    }

    @Test
    public void shouldBuildGetClusterConfigRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        ClusterConfigInfoRequest request = new ClusterConfigInfoRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/config", "/info");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/config/info"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectGetClusterConfigWithoutClusterId() {
        newClient().getClusterConfig(new ClusterConfigInfoRequest());
    }

    @Test
    public void shouldBuildUpdateClusterConfigRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        ClusterConfigUpdateRequest request = new ClusterConfigUpdateRequest();
        request.setClusterId("cluster-123");
        request.setEsConfigsMap(java.util.Collections.singletonMap("cluster.publish.timeout", "180s"));
        request.setKibanaConfigsMap(java.util.Collections.singletonMap("ops.interval", 100));

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/config", "/update");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/config/update"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectUpdateClusterConfigWithoutEsConfigsMap() {
        ClusterConfigUpdateRequest request = new ClusterConfigUpdateRequest();
        request.setClusterId("cluster-123");
        request.setKibanaConfigsMap(java.util.Collections.singletonMap("ops.interval", 100));
        newClient().updateClusterConfig(request);
    }

    @Test
    public void shouldBuildListSynonymDictsRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        SynonymDictListRequest request = new SynonymDictListRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/synonym_dict", "/list");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/synonym_dict/list"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectListSynonymDictsWithoutClusterId() {
        newClient().listSynonymDicts(new SynonymDictListRequest());
    }

    @Test
    public void shouldBuildDeleteSynonymDictRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        SynonymDictDeleteRequest request = new SynonymDictDeleteRequest();
        request.setClusterId("cluster-123");
        request.setSynonymDictName("xxx.txt");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/synonym_dict", "/delete");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/synonym_dict/delete"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectDeleteSynonymDictWithoutSynonymDictName() {
        SynonymDictDeleteRequest request = new SynonymDictDeleteRequest();
        request.setClusterId("cluster-123");
        newClient().deleteSynonymDict(request);
    }

    @Test
    public void shouldBuildUploadSynonymDictMultipartRequest() throws Exception {
        BesClientV2 client = newClient();
        File tempFile = File.createTempFile("synonym-dict", ".txt");
        tempFile.deleteOnExit();
        Files.write(tempFile.toPath(), "syn-content".getBytes(StandardCharsets.UTF_8));

        Method buildBody = BesClientV2.class.getDeclaredMethod("buildFileOnlyMultipartBody", File.class);
        buildBody.setAccessible(true);
        Object multipartBody = buildBody.invoke(client, tempFile);
        String body = readMultipartBody(multipartBody);
        String boundary = readMultipartBoundary(multipartBody);

        Assert.assertTrue(body.contains("Content-Disposition: form-data; name=\"file\"; filename=\""
                + tempFile.getName() + "\""));
        Assert.assertTrue(body.contains("Content-Type: text/plain"));
        Assert.assertTrue(body.contains("syn-content"));
        Assert.assertTrue(body.contains("--" + boundary + "--"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectUploadSynonymDictWithoutFile() {
        SynonymDictUploadRequest request = new SynonymDictUploadRequest();
        request.setClusterId("cluster-123");
        newClient().uploadSynonymDict(request);
    }

    @Test
    public void shouldBuildAuthorizeInspectRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectAuthorizeRequest request = new InspectAuthorizeRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/authorize");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/authorize"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectAuthorizeInspectWithoutClusterId() {
        newClient().authorizeInspect(new InspectAuthorizeRequest());
    }

    @Test
    public void shouldBuildSwitchAutoInspectRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectAutoSwitchRequest request = new InspectAutoSwitchRequest();
        request.setClusterId("cluster-123");
        request.setSwitchOn(true);

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/switch_auto");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/switch_auto"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectSwitchAutoInspectWithoutSwitchOn() {
        InspectAutoSwitchRequest request = new InspectAutoSwitchRequest();
        request.setClusterId("cluster-123");
        newClient().switchAutoInspect(request);
    }

    @Test
    public void shouldBuildCheckAutoInspectRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectCheckAutoRequest request = new InspectCheckAutoRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/check_auto");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/check_auto"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectCheckAutoInspectWithoutClusterId() {
        newClient().checkAutoInspect(new InspectCheckAutoRequest());
    }

    @Test
    public void shouldBuildCreateManualInspectTaskRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectCreateManualRequest request = new InspectCreateManualRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/create_manual");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/create_manual"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectCreateManualInspectTaskWithoutClusterId() {
        newClient().createManualInspectTask(new InspectCreateManualRequest());
    }

    @Test
    public void shouldBuildCheckInspectBusyRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectCheckBusyRequest request = new InspectCheckBusyRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/check_busy");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/check_busy"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectCheckInspectBusyWithoutClusterId() {
        newClient().checkInspectBusy(new InspectCheckBusyRequest());
    }

    @Test
    public void shouldBuildGetManualInspectCountRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectManualCountRequest request = new InspectManualCountRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/manual_count");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/manual_count"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectGetManualInspectCountWithoutClusterId() {
        newClient().getManualInspectCount(new InspectManualCountRequest());
    }

    @Test
    public void shouldBuildGetManualInspectConfigRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectManualConfigGetRequest request = new InspectManualConfigGetRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/get_manual_conf");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/get_manual_conf"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectGetManualInspectConfigWithoutClusterId() {
        newClient().getManualInspectConfig(new InspectManualConfigGetRequest());
    }

    @Test
    public void shouldBuildUpdateManualInspectConfigRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectManualConfigUpdateRequest request = new InspectManualConfigUpdateRequest();
        request.setClusterId("cluster-123");
        request.setIndices("*");
        request.setItems(Arrays.asList("ClusterHealth", "ClusterPayload"));

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/update_manual_conf");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/update_manual_conf"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectUpdateManualInspectConfigWithoutIndices() {
        InspectManualConfigUpdateRequest request = new InspectManualConfigUpdateRequest();
        request.setClusterId("cluster-123");
        newClient().updateManualInspectConfig(request);
    }

    @Test
    public void shouldBuildListInspectItemsRequestWithExpectedUri() throws Exception {
        BesClientV2 client = newClient();

        Class<?> emptyRequestClass = Class.forName(
                "com.baidubce.services.bes.v2.BesClientV2$EmptyBesV2Request");
        java.lang.reflect.Constructor<?> constructor = emptyRequestClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object emptyRequest = constructor.newInstance();

        InternalRequest internalRequest = buildRequest(client, (com.baidubce.model.AbstractBceRequest) emptyRequest,
                HttpMethodName.POST, "/api/bes/cluster", "/inspect", "/list_items");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/list_items"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(emptyRequest), body);
    }

    @Test
    public void shouldBuildGetInspectTaskRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectTaskGetRequest request = new InspectTaskGetRequest();
        request.setClusterId("cluster-123");
        request.setTaskId("task-10");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/get_task");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/get_task"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectGetInspectTaskWithoutTaskId() {
        InspectTaskGetRequest request = new InspectTaskGetRequest();
        request.setClusterId("cluster-123");
        newClient().getInspectTask(request);
    }

    @Test
    public void shouldBuildListInspectTasksRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectTaskListRequest request = new InspectTaskListRequest();
        request.setClusterId("cluster-123");
        request.setPageNo(1);
        request.setPageSize(10);

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/list_tasks");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/list_tasks"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectListInspectTasksWithoutPageSize() {
        InspectTaskListRequest request = new InspectTaskListRequest();
        request.setClusterId("cluster-123");
        request.setPageNo(1);
        newClient().listInspectTasks(request);
    }

    @Test
    public void shouldBuildGetLatestInspectOverviewRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectOverviewLatestRequest request = new InspectOverviewLatestRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/overview", "/latest");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/overview/latest"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectGetLatestInspectOverviewWithoutClusterId() {
        newClient().getLatestInspectOverview(new InspectOverviewLatestRequest());
    }

    @Test
    public void shouldBuildGetWeeklyInspectOverviewRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        InspectOverviewWeeklyRequest request = new InspectOverviewWeeklyRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/inspect", "/overview", "/weekly");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/inspect/overview/weekly"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectGetWeeklyInspectOverviewWithoutClusterId() {
        newClient().getWeeklyInspectOverview(new InspectOverviewWeeklyRequest());
    }

    @Test
    public void shouldBuildCreateAutoRenewRuleRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        AutoRenewRuleCreateRequest request = new AutoRenewRuleCreateRequest();
        request.setClusterIds(Arrays.asList("cluster-123"));
        request.setRenewTimeUnit("month");
        request.setRenewTime(1);
        request.setServiceType("BES");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/auto_renew_rule", "/create");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/auto_renew_rule/create"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectCreateAutoRenewRuleWithoutClusterIds() {
        AutoRenewRuleCreateRequest request = new AutoRenewRuleCreateRequest();
        request.setRenewTimeUnit("month");
        request.setRenewTime(1);
        request.setServiceType("BES");
        newClient().createAutoRenewRule(request);
    }

    @Test
    public void shouldTreatEmptyStringResultAsNullWhenParsingAutoRenewRuleCreateResponse() {
        AutoRenewRuleCreateResponse response = JsonUtils.fromJsonString(
                "{\"success\":true,\"status\":200,\"result\":\"\"}", AutoRenewRuleCreateResponse.class);

        Assert.assertTrue(response.getSuccess());
        Assert.assertEquals(Integer.valueOf(200), response.getStatus());
        Assert.assertNull(response.getResult());
    }

    @Test
    public void shouldParseAutoRenewRuleCreateResponseResultObject() {
        AutoRenewRuleCreateResponse response = JsonUtils.fromJsonString(
                "{\"success\":true,\"status\":200,\"result\":{\"orderId\":\"order-123\"}}",
                AutoRenewRuleCreateResponse.class);

        Assert.assertNotNull(response.getResult());
        Assert.assertEquals("order-123", response.getResult().getOrderId());
    }

    @Test
    public void shouldBuildGetAutoRenewRuleDetailRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        AutoRenewRuleDetailRequest request = new AutoRenewRuleDetailRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/auto_renew_rule", "/detail");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/auto_renew_rule/detail"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectGetAutoRenewRuleDetailWithoutClusterId() {
        newClient().getAutoRenewRuleDetail(new AutoRenewRuleDetailRequest());
    }

    @Test
    public void shouldBuildListAutoRenewRulesRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        AutoRenewRuleListRequest request = new AutoRenewRuleListRequest();
        request.setClusterId("cluster-123");
        request.setServiceType("BES");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/auto_renew_rule", "/list");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/auto_renew_rule/list"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectListAutoRenewRulesWithoutServiceType() {
        AutoRenewRuleListRequest request = new AutoRenewRuleListRequest();
        request.setClusterId("cluster-123");
        newClient().listAutoRenewRules(request);
    }

    @Test
    public void shouldBuildUpdateAutoRenewRuleRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        AutoRenewRuleUpdateRequest request = new AutoRenewRuleUpdateRequest();
        request.setClusterId("cluster-123");
        request.setRenewTimeUnit("month");
        request.setRenewTime(1);

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/auto_renew_rule", "/update");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/auto_renew_rule/update"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectUpdateAutoRenewRuleWithoutRenewTime() {
        AutoRenewRuleUpdateRequest request = new AutoRenewRuleUpdateRequest();
        request.setClusterId("cluster-123");
        request.setRenewTimeUnit("month");
        newClient().updateAutoRenewRule(request);
    }

    @Test
    public void shouldBuildDeleteAutoRenewRuleRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        AutoRenewRuleDeleteRequest request = new AutoRenewRuleDeleteRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/auto_renew_rule", "/delete");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/auto_renew_rule/delete"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectDeleteAutoRenewRuleWithNullRequest() {
        newClient().deleteAutoRenewRule(null);
    }

    @Test
    public void shouldBuildRenewClusterRequestWithQueryParamAndJsonBody() throws Exception {
        BesClientV2 client = newClient();
        ClusterRenewRequest request = new ClusterRenewRequest();
        request.setClusterId("cluster-123");
        request.setTime(1);

        Method createRequest = BesClientV2.class.getDeclaredMethod(
                "createRequest", com.baidubce.model.AbstractBceRequest.class, HttpMethodName.class, String[].class);
        createRequest.setAccessible(true);
        InternalRequest internalRequest = (InternalRequest) createRequest.invoke(
                client, request, HttpMethodName.POST, new String[] {"/api/bes/cluster", "/renew"});
        internalRequest.addParameter("orderType", "RENEW");
        Method addJsonBody = BesClientV2.class.getDeclaredMethod(
                "addJsonBody", InternalRequest.class, com.baidubce.model.AbstractBceRequest.class);
        addJsonBody.setAccessible(true);
        addJsonBody.invoke(client, internalRequest, request);

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/renew"));
        Assert.assertEquals("RENEW", internalRequest.getParameters().get("orderType"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectRenewClusterWithoutTime() {
        ClusterRenewRequest request = new ClusterRenewRequest();
        request.setClusterId("cluster-123");
        newClient().renewCluster(request);
    }

    @Test
    public void shouldBuildListRenewalsRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        RenewListRequest request = new RenewListRequest();
        request.setOrder("desc");
        request.setOrderBy("expireTime");
        request.setPageNo(1);
        request.setPageSize(15);
        request.setDaysToExpiration(60);

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/renew", "/list");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/renew/list"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectListRenewalsWithoutDaysToExpiration() {
        RenewListRequest request = new RenewListRequest();
        request.setOrder("desc");
        request.setOrderBy("expireTime");
        request.setPageNo(1);
        request.setPageSize(15);
        newClient().listRenewals(request);
    }

    @Test
    public void shouldBuildListSchedulesRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        ScheduleListRequest request = new ScheduleListRequest();
        request.setClusterId("cluster-123");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/schedule", "/list");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/schedule/list"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectListSchedulesWithoutClusterId() {
        newClient().listSchedules(new ScheduleListRequest());
    }

    @Test
    public void shouldBuildCreateScheduleRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        ScheduleCreateRequest request = new ScheduleCreateRequest();
        request.setClusterId("cluster-123");
        request.setSchedule("0 1 0 * * ?");
        request.setScheduleName("CreateIndexApi1");
        request.setTaskType("CREATE_INDEX");
        request.setTask(java.util.Collections.singletonMap("indexPrefix", "apiindex1-"));

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/schedule", "/create");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/schedule/create"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectCreateScheduleWithoutTask() {
        ScheduleCreateRequest request = new ScheduleCreateRequest();
        request.setClusterId("cluster-123");
        request.setSchedule("0 1 0 * * ?");
        request.setScheduleName("CreateIndexApi1");
        request.setTaskType("CREATE_INDEX");
        newClient().createSchedule(request);
    }

    @Test
    public void shouldBuildUpdateScheduleRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        ScheduleUpdateRequest request = new ScheduleUpdateRequest();
        request.setClusterId("cluster-123");
        request.setSchedule("0 1 0 * * ?");
        request.setScheduleName("CreateIndexApi1");
        request.setTaskType("CREATE_INDEX");
        request.setTask(java.util.Collections.singletonMap("indexPrefix", "apiindex1-"));

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/schedule", "/update");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/schedule/update"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectUpdateScheduleWithoutScheduleName() {
        ScheduleUpdateRequest request = new ScheduleUpdateRequest();
        request.setClusterId("cluster-123");
        request.setSchedule("0 1 0 * * ?");
        request.setTaskType("CREATE_INDEX");
        request.setTask(java.util.Collections.singletonMap("indexPrefix", "apiindex1-"));
        newClient().updateSchedule(request);
    }

    @Test
    public void shouldBuildDeleteScheduleRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        ScheduleDeleteRequest request = new ScheduleDeleteRequest();
        request.setClusterId("cluster-123");
        request.setScheduleName("BackupApi1");

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/schedule", "/delete");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/schedule/delete"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectDeleteScheduleWithoutScheduleName() {
        ScheduleDeleteRequest request = new ScheduleDeleteRequest();
        request.setClusterId("cluster-123");
        newClient().deleteSchedule(request);
    }

    @Test
    public void shouldBuildBatchInsertTagsRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        TagBatchInsertRequest request = new TagBatchInsertRequest();
        request.setClusterIdList(Arrays.asList("111", "222"));
        com.baidubce.services.bes.v2.model.tag.Tag tag = new com.baidubce.services.bes.v2.model.tag.Tag();
        tag.setTagKey("key");
        tag.setTagValue("value");
        request.setInsertTags(Arrays.asList(tag));

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/batchInsertTags");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/batchInsertTags"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectBatchInsertTagsWithoutInsertTags() {
        TagBatchInsertRequest request = new TagBatchInsertRequest();
        request.setClusterIdList(Arrays.asList("111", "222"));
        newClient().batchInsertTags(request);
    }

    @Test
    public void shouldBuildUpdateClusterTagsRequestWithJsonBody() throws Exception {
        BesClientV2 client = newClient();
        TagUpdateRequest request = new TagUpdateRequest();
        request.setClusterId("cluster-123");
        com.baidubce.services.bes.v2.model.tag.Tag tag = new com.baidubce.services.bes.v2.model.tag.Tag();
        tag.setTagKey("key");
        tag.setTagValue("value");
        request.setTags(Arrays.asList(tag));

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/updateTags");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/updateTags"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test
    public void shouldAllowUpdateClusterTagsWithEmptyTagsList() throws Exception {
        BesClientV2 client = newClient();
        TagUpdateRequest request = new TagUpdateRequest();
        request.setClusterId("cluster-123");
        request.setTags(java.util.Collections.<com.baidubce.services.bes.v2.model.tag.Tag>emptyList());

        InternalRequest internalRequest = buildRequest(client, request, HttpMethodName.POST,
                "/api/bes/cluster", "/updateTags");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/updateTags"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(request), body);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectUpdateClusterTagsWithNullTags() {
        TagUpdateRequest request = new TagUpdateRequest();
        request.setClusterId("cluster-123");
        newClient().updateClusterTags(request);
    }

    @Test
    public void shouldBuildListTagsRequestWithExpectedUri() throws Exception {
        BesClientV2 client = newClient();

        Class<?> emptyRequestClass = Class.forName(
                "com.baidubce.services.bes.v2.BesClientV2$EmptyBesV2Request");
        java.lang.reflect.Constructor<?> constructor = emptyRequestClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Object emptyRequest = constructor.newInstance();

        InternalRequest internalRequest = buildRequest(client, (com.baidubce.model.AbstractBceRequest) emptyRequest,
                HttpMethodName.POST, "/api/bes/cluster", "/tagList");

        Assert.assertTrue(internalRequest.getUri().toString().endsWith("/api/bes/cluster/tagList"));
        String body = readBody(internalRequest.getContent());
        Assert.assertEquals(JsonUtils.toJsonString(emptyRequest), body);
    }

    private static BesClientV2 newClient() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setEndpoint("https://bes.bj.baidubce.com");
        return new BesClientV2(config, "bj");
    }

    private static CreateClusterRequest validCreateClusterRequest() {
        CreateClusterRequest request = new CreateClusterRequest();
        request.setName("test-cluster");
        request.setPassword("placeholder-password");
        request.setSecurityGroupId("g-123");
        request.setSubnetUuid("subnet-123");
        request.setAvailableZone("cn-bd-b");
        request.setVpcId("vpc-123");
        request.setVersion("7.4.2");
        request.setIsOldPackage(Boolean.FALSE);
        request.setEnableSSL(Boolean.TRUE);
        request.setResGroupId("RESG-1");

        DiskSlotInfo diskSlotInfo = new DiskSlotInfo();
        diskSlotInfo.setSize(50);
        diskSlotInfo.setType("premium_ssd");
        diskSlotInfo.setCdsExtraIo(11);

        ModuleInfo moduleInfo = new ModuleInfo();
        moduleInfo.setType("es_node");
        moduleInfo.setInstanceNum(2);
        moduleInfo.setSlotType("bes.c3.c2m4");
        moduleInfo.setDiskSlotInfo(diskSlotInfo);
        request.setModules(Arrays.asList(moduleInfo));

        AutoRenewInfo autoRenewInfo = new AutoRenewInfo();
        autoRenewInfo.setRenewTimeUnit("month");
        autoRenewInfo.setRenewTime(java.math.BigDecimal.ONE);

        Billing billing = new Billing();
        billing.setPaymentType("postpay");
        billing.setTime(0);
        billing.setEnableAutoRenew(Boolean.TRUE);
        billing.setAutoRenewInfo(autoRenewInfo);
        billing.setCoupon("coupon-1");
        request.setBilling(billing);

        com.baidubce.services.bes.v2.model.cluster.Tag tag = new com.baidubce.services.bes.v2.model.cluster.Tag();
        tag.setTagKey("env");
        tag.setTagValue("test");
        request.setTags(Arrays.asList(tag));

        return request;
    }

    private static InternalRequest buildRequest(BesClientV2 client, com.baidubce.model.AbstractBceRequest request,
                                                  HttpMethodName httpMethod, String... pathVariables) throws Exception {
        Method createRequest = BesClientV2.class.getDeclaredMethod(
                "createRequest", com.baidubce.model.AbstractBceRequest.class, HttpMethodName.class, String[].class);
        createRequest.setAccessible(true);
        InternalRequest internalRequest = (InternalRequest) createRequest.invoke(
                client, request, httpMethod, pathVariables);

        Method addJsonBody = BesClientV2.class.getDeclaredMethod(
                "addJsonBody", InternalRequest.class, com.baidubce.model.AbstractBceRequest.class);
        addJsonBody.setAccessible(true);
        addJsonBody.invoke(client, internalRequest, request);

        return internalRequest;
    }

    private static String readBody(RestartableInputStream inputStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int count;
        while ((count = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, count);
        }
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }
}

package com.baidubce.services.bes.v3;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bes.v3.model.action.*;
import com.baidubce.services.bes.v3.model.audit.*;
import com.baidubce.services.bes.v3.model.bct.*;
import com.baidubce.services.bes.v3.model.billing.*;
import com.baidubce.services.bes.v3.model.cluster.*;
import com.baidubce.services.bes.v3.model.diagnosis.*;
import com.baidubce.services.bes.v3.model.index.*;
import com.baidubce.services.bes.v3.model.logmanage.*;
import com.baidubce.services.bes.v3.model.plugin.*;
import com.baidubce.services.bes.v3.model.schedule.*;
import com.baidubce.services.bes.v3.model.snapshot.*;
import com.baidubce.services.bes.v3.model.user.*;
import com.baidubce.services.bes.v3.model.cluster.NodeSpec;
import com.baidubce.util.JsonUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class BesClientV3Test {

    @Test
    public void testCreateClusterBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(configWithTestCredentials(), "bj");
        CreateClusterRequest request = createValidCreateClusterRequest();

        CreateClusterResponse response = client.createCluster(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters", client.lastRequest.getUri().getPath());
        Assert.assertEquals("bj", client.lastRequest.getHeaders().get("X-Region"));
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals(TEST_ACCESS_KEY_ID, client.lastRequest.getHeaders().get("x-bce-accesskey"));

        String body = readContent(client.lastRequest.getContent());
        Assert.assertTrue(body.contains("\"payment\":\"Postpaid\""));
        Assert.assertTrue(body.contains("\"name\":\"testOpenapiMain\""));
        Assert.assertTrue(body.contains("\"engine\":\"OPENSEARCH\""));
        Assert.assertTrue(body.contains("\"engineType\":\"COMMUNITY\""));
        Assert.assertTrue(body.contains("\"nodeSpecs\""));
        Assert.assertFalse(body.contains("\"adminPassword\":\"encoded-password\""));
        Assert.assertEquals("encoded-password",
                aes128DecryptWithFirst16Char(request.getAdminPassword(), TEST_SECRET_KEY));
    }

    @Test
    public void testCreateClusterModelsMatchApiDocument() {
        Assert.assertEquals(new HashSet<String>(Arrays.asList(
                        "payment", "paymentInfo", "couponIds", "name", "engine", "engineType", "version",
                        "adminPassword", "enableHttps", "deletionProtection", "logicalZones", "vpcId", "subnetId",
                        "enableDeploySet", "nodeSpecs", "tags")),
                declaredFieldNames(CreateClusterRequest.class));
        Assert.assertEquals(new HashSet<String>(Arrays.asList(
                        "type", "nodeType", "nodeCount", "diskType", "diskSize", "diskCount")),
                declaredFieldNames(NodeSpec.class));
        Assert.assertEquals(declaredFieldNames(NodeSpec.class),
                declaredFieldNames(com.baidubce.services.bes.v3.model.billing.NodeSpec.class));
    }

    private static Set<String> declaredFieldNames(Class<?> type) {
        Set<String> fieldNames = new HashSet<String>();
        for (Field field : type.getDeclaredFields()) {
            fieldNames.add(field.getName());
        }
        return fieldNames;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateClusterRejectsNullRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        client.createCluster(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateClusterRejectsEmptyName() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateClusterRequest request = createValidCreateClusterRequest();
        request.setName(" ");

        client.createCluster(request);
    }

    @Test
    public void testDeleteClusterBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        DeleteClusterRequest request = new DeleteClusterRequest();
        request.setClusterId("search-mAbWIKfpMPpr10y624Xm");
        request.setRecycle(Boolean.TRUE);

        DeleteClusterResponse response = client.deleteCluster(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.DELETE, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-mAbWIKfpMPpr10y624Xm", client.lastRequest.getUri().getPath());
        Assert.assertEquals("bj", client.lastRequest.getHeaders().get("X-Region"));
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals("true", client.lastRequest.getParameters().get("recycle"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteClusterRejectsNullRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        client.deleteCluster(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteClusterRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        DeleteClusterRequest request = new DeleteClusterRequest();
        request.setClusterId(" ");

        client.deleteCluster(request);
    }

    @Test
    public void testListClustersBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListClustersRequest request = new ListClustersRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        request.setOrder("desc");
        request.setOrderBy("createTime");
        request.setPayment("Postpaid");
        request.setEngine("OPENSEARCH");
        request.setStatus("ACTIVE");
        request.setClusterHealth("GREEN");
        request.setLogicalZone("zoneA");
        request.setTagKey("tagKey");
        request.setTagValue("tagValue");

        ListClustersResponse response = client.listClusters(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters", client.lastRequest.getUri().getPath());
        Assert.assertEquals("bj", client.lastRequest.getHeaders().get("X-Region"));
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals("1", client.lastRequest.getParameters().get("pageNo"));
        Assert.assertEquals("10", client.lastRequest.getParameters().get("pageSize"));
        Assert.assertEquals("desc", client.lastRequest.getParameters().get("order"));
        Assert.assertEquals("createTime", client.lastRequest.getParameters().get("orderBy"));
        Assert.assertEquals("Postpaid", client.lastRequest.getParameters().get("payment"));
        Assert.assertEquals("OPENSEARCH", client.lastRequest.getParameters().get("engine"));
        Assert.assertEquals("ACTIVE", client.lastRequest.getParameters().get("status"));
        Assert.assertEquals("GREEN", client.lastRequest.getParameters().get("clusterHealth"));
        Assert.assertEquals("zoneA", client.lastRequest.getParameters().get("logicalZone"));
        Assert.assertEquals("tagKey", client.lastRequest.getParameters().get("tagKey"));
        Assert.assertEquals("tagValue", client.lastRequest.getParameters().get("tagValue"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListClustersRejectsNullRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        client.listClusters(null);
    }

    @Test
    public void testListClustersResponseDeserializesExtendedClusterSummaryFields() {
        String json = "{"
                + "\"clusters\":[{"
                + "\"clusterId\":\"search-xxxx\","
                + "\"name\":\"testOpenapiMain\","
                + "\"createTime\":\"2026-07-09T03:15:16Z\","
                + "\"engine\":\"OPENSEARCH\","
                + "\"engineType\":\"COMMUNITY\","
                + "\"version\":\"2.19.4\","
                + "\"kernelVersion\":\"1.0.3\","
                + "\"status\":\"ACTIVE\","
                + "\"clusterHealth\":{\"status\":\"GREEN\",\"message\":\"healthy\",\"totalDiskSize\":\"146.7GB\",\"usedDiskSize\":\"7.6GB\"},"
                + "\"region\":\"bj\","
                + "\"logicalZonesNumber\":1,"
                + "\"logicalZones\":[\"cn-bj-a\"],"
                + "\"runningTime\":{\"day\":1,\"hour\":2,\"minute\":3,\"second\":4,\"runningTimeMs\":1000},"
                + "\"deletionProtection\":false,"
                + "\"payment\":\"Postpaid\","
                + "\"expirationTime\":\"2027-07-09T03:15:16Z\","
                + "\"isPrepayToPostpay\":false"
                + "}],"
                + "\"pageNo\":1,"
                + "\"pageSize\":10,"
                + "\"totalCount\":1"
                + "}";

        ListClustersResponse response = JsonUtils.fromJsonString(json, ListClustersResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(Integer.valueOf(1), response.getPageNo());
        Assert.assertEquals(Integer.valueOf(10), response.getPageSize());
        Assert.assertEquals(Integer.valueOf(1), response.getTotalCount());
        Assert.assertNotNull(response.getClusters());
        Assert.assertEquals(1, response.getClusters().size());

        ClusterSummary cluster = response.getClusters().get(0);
        Assert.assertEquals("search-xxxx", cluster.getClusterId());
        Assert.assertEquals("testOpenapiMain", cluster.getName());
        Assert.assertEquals("2026-07-09T03:15:16Z", cluster.getCreateTime());
        Assert.assertEquals("OPENSEARCH", cluster.getEngine());
        Assert.assertEquals("COMMUNITY", cluster.getEngineType());
        Assert.assertEquals("2.19.4", cluster.getVersion());
        Assert.assertEquals("1.0.3", cluster.getKernelVersion());
        Assert.assertEquals("ACTIVE", cluster.getStatus());
        Assert.assertEquals("GREEN", cluster.getClusterHealth().getStatus());
        Assert.assertEquals("146.7GB", cluster.getClusterHealth().getTotalDiskSize());
        Assert.assertEquals("bj", cluster.getRegion());
        Assert.assertEquals(Integer.valueOf(1), cluster.getLogicalZonesNumber());
        Assert.assertEquals("cn-bj-a", cluster.getLogicalZones().get(0));
        Assert.assertEquals(Integer.valueOf(1), cluster.getRunningTime().getDay());
        Assert.assertEquals(Boolean.FALSE, cluster.getDeletionProtection());
        Assert.assertEquals("Postpaid", cluster.getPayment());
        Assert.assertEquals("2027-07-09T03:15:16Z", cluster.getExpirationTime());
        Assert.assertEquals(Boolean.FALSE, cluster.getIsPrepayToPostpay());
    }

    @Test
    public void testGetClusterDetailBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetClusterDetailRequest request = new GetClusterDetailRequest();
        request.setClusterId("search-xxxx");

        ClusterDetailResponse response = client.getClusterDetail(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx", client.lastRequest.getUri().getPath());
        Assert.assertEquals("bj", client.lastRequest.getHeaders().get("X-Region"));
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
    }

    @Test
    public void testListClusterNodesBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetNodeListRequest request = new GetNodeListRequest();
        request.setClusterId("search-xxxx");

        GetNodeListResponse response = client.getNodeList(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/nodes", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
    }

    @Test
    public void testDownloadCertsBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        DownloadCertRequest request = new DownloadCertRequest();
        request.setClusterId("search-xxxx");

        DownloadCertResponse response = client.downloadCert(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/certs", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/zip", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals("application/zip", client.lastRequest.getHeaders().get("Accept"));
    }

    @Test
    public void testRenameClusterBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        RenameClusterRequest request = new RenameClusterRequest();
        request.setClusterId("search-xxxx");
        request.setNewName("renamed-cluster");

        RenameClusterResponse response = client.renameCluster(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/name", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"newName\":\"renamed-cluster\""));
    }

    @Test
    public void testSwitchDeletionProtectionBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        SwitchDeletionProtectionRequest request = new SwitchDeletionProtectionRequest();
        request.setClusterId("search-xxxx");
        request.setDeletionProtection(Boolean.TRUE);

        SwitchDeletionProtectionResponse response = client.switchDeletionProtection(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/deletion-protection", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"deletionProtection\":true"));
    }

    @Test
    public void testUpdateMaintenanceDurationBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateMaintenanceDurationRequest request = new UpdateMaintenanceDurationRequest();
        request.setClusterId("search-xxxx");
        request.setMaintenanceTimeZone("Asia/Shanghai");
        request.setMaintenancePeriods(Collections.singletonList("MONDAY"));
        request.setMaintenanceStartTime("02:00");
        request.setMaintenanceEndTime("06:00");

        UpdateMaintenanceDurationResponse response = client.updateMaintenanceDuration(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/maintenance-duration", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"maintenanceTimeZone\":\"Asia/Shanghai\""));
        Assert.assertTrue(body.contains("\"maintenancePeriods\":[\"MONDAY\"]"));
        Assert.assertTrue(body.contains("\"maintenanceStartTime\":\"02:00\""));
        Assert.assertTrue(body.contains("\"maintenanceEndTime\":\"06:00\""));
    }

    @Test
    public void testRecoverClusterBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        RecoveryClusterRequest request = new RecoveryClusterRequest();
        request.setClusterId("search-xxxx");

        RecoveryClusterResponse response = client.recoverCluster(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/recovery", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
    }

    @Test
    public void testListAvailableKernelsBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");

        ListAvailableKernelsResponse response = client.listAvailableKernels(new ListAvailableKernelsRequest());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/kernels", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
    }

    @Test
    public void testQuerySpecsBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        QuerySpecsRequest request = new QuerySpecsRequest();
        request.setEngine("OPENSEARCH");
        request.setEngineType("COMMUNITY");
        request.setVersion("2.19.4");
        request.setFeatures(Collections.singletonList("ml"));
        request.setLogicalZones(Collections.singletonList("cn-bj-a"));

        QuerySpecsResponse response = client.querySpecs(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/specs", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"engine\":\"OPENSEARCH\""));
        Assert.assertTrue(body.contains("\"engineType\":\"COMMUNITY\""));
        Assert.assertTrue(body.contains("\"version\":\"2.19.4\""));
        Assert.assertTrue(body.contains("\"features\":[\"ml\"]"));
        Assert.assertTrue(body.contains("\"logicalZones\":[\"cn-bj-a\"]"));
    }

    @Test
    public void testDownloadCertsResponseParsesBinaryContent() throws Exception {
        byte[] zipBytes = "fake-zip-content".getBytes("UTF-8");
        TestCloseableHttpResponse httpResponse = new TestCloseableHttpResponse();
        HttpEntity entity = new InputStreamEntity(new ByteArrayInputStream(zipBytes), zipBytes.length);
        httpResponse.setEntity(entity);
        httpResponse.addHeader(new BasicHeader(Headers.CONTENT_DISPOSITION,
                "attachment; filename=\"bes-search-xxxx-https-certs-v0001.zip\""));
        httpResponse.addHeader(new BasicHeader("Cache-Control",
                "no-store, no-cache, must-revalidate, private"));
        httpResponse.addHeader(new BasicHeader("Pragma", "no-cache"));
        httpResponse.addHeader(new BasicHeader("X-Content-Type-Options", "nosniff"));

        BceHttpResponse bceHttpResponse = new BceHttpResponse(httpResponse);
        DownloadCertResponse response = new DownloadCertResponse();

        boolean handled = new BesCertResponseHandler().handle(bceHttpResponse, response);

        Assert.assertTrue(handled);
        Assert.assertArrayEquals(zipBytes, IOUtils.toByteArray(response.getContent()));
        Assert.assertEquals("no-store, no-cache, must-revalidate, private", response.getMetadata().getCacheControl());
        Assert.assertEquals("no-cache", response.getMetadata().getPragma());
        Assert.assertEquals("nosniff", response.getMetadata().getxContentTypeOptions());
    }

    @Test
    public void testClusterDetailResponseDeserializesExtendedFields() {
        String json = "{"
                + "\"clusterId\":\"search-xxxx\","
                + "\"name\":\"test-cluster\","
                + "\"engine\":\"OPENSEARCH\","
                + "\"engineType\":\"COMMUNITY\","
                + "\"status\":\"ACTIVE\","
                + "\"logicalZonesCount\":1,"
                + "\"logicalZones\":[\"cn-bj-a\"],"
                + "\"runningTime\":{\"day\":1,\"hour\":2,\"minute\":3,\"second\":4,\"runningTimeMs\":1000},"
                + "\"nodeSpecs\":[{\"type\":\"data\",\"nodeType\":\"opensearch.c2m8\",\"nodeCount\":3}],"
                + "\"tags\":[{\"tagKey\":\"env\",\"tagValue\":\"test\"}],"
                + "\"publicIpWhitelist\":[\"1.1.1.1/32\"],"
                + "\"inRecycleBin\":false,"
                + "\"deleteExpireTime\":\"2026-08-16T03:15:16Z\","
                + "\"recycleCount\":0,"
                + "\"isPrepayToPostpay\":false"
                + "}";

        ClusterDetailResponse response = JsonUtils.fromJsonString(json, ClusterDetailResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals("search-xxxx", response.getClusterId());
        Assert.assertEquals("OPENSEARCH", response.getEngine());
        Assert.assertEquals("COMMUNITY", response.getEngineType());
        Assert.assertEquals(Integer.valueOf(1), response.getLogicalZonesCount());
        Assert.assertEquals(Integer.valueOf(1), response.getRunningTime().getDay());
        Assert.assertEquals(1, response.getNodeSpecs().size());
        Assert.assertEquals("data", response.getNodeSpecs().get(0).getType());
        Assert.assertEquals(1, response.getTags().size());
        Assert.assertEquals("env", response.getTags().get(0).getTagKey());
        Assert.assertEquals("1.1.1.1/32", response.getPublicIpWhitelist().get(0));
        Assert.assertEquals(Boolean.FALSE, response.getInRecycleBin());
        Assert.assertEquals(Boolean.FALSE, response.getIsPrepayToPostpay());
    }

    @Test
    public void testNodeListResponseDeserializesNodes() {
        String json = "{"
                + "\"nodeSpecs\":[{\"type\":\"data\",\"nodeType\":\"opensearch.c2m8\",\"nodeCount\":3}],"
                + "\"nodes\":[{\"nodeId\":\"i-123\",\"nodeName\":\"node-1\",\"status\":\"ALIVE\",\"logicalZone\":\"cn-bj-a\"}]"
                + "}";

        GetNodeListResponse response = JsonUtils.fromJsonString(json, GetNodeListResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(1, response.getNodeSpecs().size());
        Assert.assertEquals("opensearch.c2m8", response.getNodeSpecs().get(0).getNodeType());
        Assert.assertEquals(1, response.getNodes().size());
        Assert.assertEquals("i-123", response.getNodes().get(0).getNodeId());
        Assert.assertEquals("cn-bj-a", response.getNodes().get(0).getLogicalZone());
    }

    @Test
    public void testRecoveryClusterResponseDeserializesFields() {
        String json = "{\"clusterId\":\"search-xxxx\",\"actionId\":\"action-123\"}";

        RecoveryClusterResponse response = JsonUtils.fromJsonString(json, RecoveryClusterResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals("search-xxxx", response.getClusterId());
        Assert.assertEquals("action-123", response.getActionId());
    }

    @Test
    public void testListAvailableKernelsResponseDeserializesVersions() {
        String json = "{"
                + "\"versions\":[{\"engine\":\"OPENSEARCH\",\"engineType\":\"COMMUNITY\",\"version\":\"2.19.5\",\"status\":\"TRIAL\",\"features\":[],\"scenes\":[\"1\"]}]"
                + "}";

        ListAvailableKernelsResponse response = JsonUtils.fromJsonString(json, ListAvailableKernelsResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(1, response.getVersions().size());
        Assert.assertEquals("OPENSEARCH", response.getVersions().get(0).getEngine());
        Assert.assertEquals("COMMUNITY", response.getVersions().get(0).getEngineType());
        Assert.assertEquals("2.19.5", response.getVersions().get(0).getVersion());
    }

    @Test
    public void testQuerySpecsResponseDeserializesNestedFields() {
        String json = "{"
                + "\"nodeAvailableSpecs\":[{\"type\":\"data\",\"nodeMinSize\":2,\"nodeMaxSize\":200,\"nodeTypes\":[\"opensearch.c2m8\"]}],"
                + "\"nodeSpecs\":[{\"nodeType\":\"opensearch.c2m8\",\"displayName\":\"2核8G\",\"cpu\":2,\"memory\":8,\"status\":\"available\"}],"
                + "\"diskSpecs\":[{\"diskType\":\"enhanced_ssd_pl1\",\"displayName\":\"增强型SSD_PL1\"}]"
                + "}";

        QuerySpecsResponse response = JsonUtils.fromJsonString(json, QuerySpecsResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(1, response.getNodeAvailableSpecs().size());
        Assert.assertEquals("data", response.getNodeAvailableSpecs().get(0).getType());
        Assert.assertEquals(1, response.getNodeSpecs().size());
        Assert.assertEquals("opensearch.c2m8", response.getNodeSpecs().get(0).getNodeType());
        Assert.assertEquals(1, response.getDiskSpecs().size());
        Assert.assertEquals("enhanced_ssd_pl1", response.getDiskSpecs().get(0).getDiskType());
    }

    @Test
    public void testChangeProtocolBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ChangeProtocolRequest request = new ChangeProtocolRequest();
        request.setClusterId("search-xxxx");
        request.setEnableHttps(Boolean.TRUE);

        ChangeProtocolResponse response = client.changeProtocol(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/protocol", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"enableHttps\":true"));
    }

    @Test
    public void testStopNodesBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        StopNodesRequest request = new StopNodesRequest();
        request.setClusterId("search-xxxx");
        request.setNodes(Arrays.asList("n-1", "n-2"));

        StopNodesResponse response = client.stopNodes(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/nodes/stop", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"nodes\":[\"n-1\",\"n-2\"]"));
    }

    @Test
    public void testSuspendClusterBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        SuspendClusterRequest request = new SuspendClusterRequest();
        request.setClusterId("search-xxxx");

        SuspendClusterResponse response = client.suspendCluster(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/stop", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
    }

    @Test
    public void testUpgradeBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpgradeClusterRequest request = new UpgradeClusterRequest();
        request.setClusterId("search-xxxx");
        request.setUpgradeType("ENGINE");
        request.setTargetVersion("2.11.0");
        request.setOperation("UPGRADE");
        request.setUpgradeMode("ROLLING");
        request.setSkipHealthCheck(Boolean.TRUE);
        request.setSkipDeprecationCheck(Boolean.FALSE);
        request.setUpgradeVisual(Boolean.TRUE);
        request.setEnableDeploySet(Boolean.TRUE);

        UpgradeClusterResponse response = client.upgrade(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/upgrades", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"upgradeType\":\"ENGINE\""));
        Assert.assertTrue(body.contains("\"targetVersion\":\"2.11.0\""));
        Assert.assertTrue(body.contains("\"operation\":\"UPGRADE\""));
        Assert.assertTrue(body.contains("\"upgradeMode\":\"ROLLING\""));
        Assert.assertTrue(body.contains("\"skipHealthCheck\":true"));
        Assert.assertTrue(body.contains("\"upgradeVisual\":true"));
    }

    @Test
    public void testUpdateClusterBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateClusterRequest request = new UpdateClusterRequest();
        request.setClusterId("search-xxxx");
        request.setMode("DIRECT");
        request.setLogicalZones(Arrays.asList("cn-bj-a", "cn-bj-b"));
        request.setEnableDeploySet(Boolean.TRUE);
        request.setDeleteNodeTypes(Collections.singletonList("coordinating_only"));

        NodeSpec nodeSpec = new NodeSpec();
        nodeSpec.setType("data");
        nodeSpec.setNodeType("opensearch.c2m8");
        nodeSpec.setNodeCount(3);
        request.setNodeSpecs(Collections.singletonList(nodeSpec));

        UpdateClusterResponse response = client.updateCluster(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"mode\":\"DIRECT\""));
        Assert.assertTrue(body.contains("\"logicalZones\":[\"cn-bj-a\",\"cn-bj-b\"]"));
        Assert.assertTrue(body.contains("\"enableDeploySet\":true"));
        Assert.assertTrue(body.contains("\"deleteNodeTypes\":[\"coordinating_only\"]"));
        Assert.assertTrue(body.contains("\"nodeSpecs\":[{"));
        Assert.assertTrue(body.contains("\"nodeType\":\"opensearch.c2m8\""));
    }

    @Test
    public void testStartNodesBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        StartNodesRequest request = new StartNodesRequest();
        request.setClusterId("search-xxxx");
        request.setNodes(Collections.singletonList("n-1"));

        StartNodesResponse response = client.startNodes(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/nodes/start", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"nodes\":[\"n-1\"]"));
    }

    @Test
    public void testResumeClusterBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ResumeClusterRequest request = new ResumeClusterRequest();
        request.setClusterId("search-xxxx");

        ResumeClusterResponse response = client.resumeCluster(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/start", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
    }

    @Test
    public void testSwitchPublicAccessBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        SwitchPublicAccessRequest request = new SwitchPublicAccessRequest();
        request.setClusterId("search-xxxx");
        request.setEnabled(Boolean.TRUE);
        request.setPublicIp("1.2.3.4");
        request.setAccessType("DASHBOARDS");

        SwitchPublicAccessResponse response = client.switchPublicAccess(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/public-access", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"enabled\":true"));
        Assert.assertTrue(body.contains("\"publicIp\":\"1.2.3.4\""));
        Assert.assertTrue(body.contains("\"accessType\":\"DASHBOARDS\""));
    }

    @Test
    public void testEnableCerebroBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        SwitchCerebroRequest request = new SwitchCerebroRequest();
        request.setClusterId("search-xxxx");
        request.setEnabled(Boolean.TRUE);

        SwitchCerebroResponse response = client.enableCerebro(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/cerebro", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"enabled\":true"));
    }

    @Test
    public void testListUpgradeVersionsBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"clusterId\":\"search-xxxx\","
                + "\"name\":\"bes-cluster\","
                + "\"engine\":\"elasticsearch\","
                + "\"currentVersion\":\"2.9.0\","
                + "\"currentKernel\":\"1.0.2\","
                + "\"enableDeploySet\":true,"
                + "\"versionUpgrades\":[{\"version\":\"2.11.0\",\"upgradeModes\":[\"ROLLING\"]}],"
                + "\"kernelUpgrades\":[{\"kernel\":\"1.0.3\",\"upgradeModes\":[\"COLD\",\"ROLLING\"]}]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        ListUpgradeVersionsRequest request = new ListUpgradeVersionsRequest();
        request.setClusterId("search-xxxx");

        ListUpgradeVersionsResponse response = client.listUpgradeVersions(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/upgrades/versions", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals("search-xxxx", response.getClusterId());
        Assert.assertEquals("bes-cluster", response.getName());
        Assert.assertEquals("elasticsearch", response.getEngine());
        Assert.assertEquals("2.9.0", response.getCurrentVersion());
        Assert.assertEquals("1.0.2", response.getCurrentKernel());
        Assert.assertEquals(Boolean.TRUE, response.getEnableDeploySet());
        Assert.assertEquals(1, response.getVersionUpgrades().size());
        Assert.assertEquals("2.11.0", response.getVersionUpgrades().get(0).getVersion());
        Assert.assertEquals("ROLLING", response.getVersionUpgrades().get(0).getUpgradeModes().get(0));
        Assert.assertEquals(1, response.getKernelUpgrades().size());
        Assert.assertEquals("1.0.3", response.getKernelUpgrades().get(0).getKernel());
        Assert.assertEquals("COLD", response.getKernelUpgrades().get(0).getUpgradeModes().get(0));
    }

    @Test
    public void testListMigrationNodesBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"nodes\":[{\"nodeId\":\"n-1\",\"nodeName\":\"node-1\",\"status\":\"RUNNING\","
                + "\"logicalZone\":\"cn-bj-a\"}]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        ListMigrationNodesRequest request = new ListMigrationNodesRequest();
        request.setClusterId("search-xxxx");
        request.setType("hot");

        ListMigrationNodesResponse response = client.listMigrationNodes(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/migrations/nodes", client.lastRequest.getUri().getPath());
        Assert.assertEquals("hot", client.lastRequest.getParameters().get("type"));
        Assert.assertEquals(1, response.getNodes().size());
        Assert.assertEquals("n-1", response.getNodes().get(0).getNodeId());
        Assert.assertEquals("node-1", response.getNodes().get(0).getNodeName());
        Assert.assertEquals("RUNNING", response.getNodes().get(0).getStatus());
        Assert.assertEquals("cn-bj-a", response.getNodes().get(0).getLogicalZone());
    }

    @Test
    public void testSuggestMigrationNodesBuildsExpectedRequestAndParsesResponse() {
        // String responseJson = "{\"suggestedNodes\":[\"n-1\",\"n-2\"]}";
        String responseJson = "{"
                + "\"nodes\":[{\"nodeId\":\"n-1\",\"nodeName\":\"node-1\",\"status\":\"RUNNING\","
                + "\"logicalZone\":\"cn-bj-a\"}]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        SuggestMigrationNodesRequest request = new SuggestMigrationNodesRequest();
        request.setClusterId("search-xxxx");
        request.setType("hot");
        request.setMigrateCount(2);

        SuggestMigrationNodesResponse response = client.suggestMigrationNodes(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/migrations/suggested-nodes", client.lastRequest.getUri().getPath());
        Assert.assertEquals("hot", client.lastRequest.getParameters().get("type"));
        Assert.assertEquals("2", client.lastRequest.getParameters().get("migrateCount"));
        Assert.assertEquals(1, response.getNodes().size());
        Assert.assertEquals("n-1", response.getNodes().get(0).getNodeId());
        Assert.assertEquals("node-1", response.getNodes().get(0).getNodeName());
        Assert.assertEquals("RUNNING", response.getNodes().get(0).getStatus());
        Assert.assertEquals("cn-bj-a", response.getNodes().get(0).getLogicalZone());
    }

    @Test
    public void testSetAccessWhitelistBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        AccessWhitelistRequest request = new AccessWhitelistRequest();
        request.setClusterId("search-xxxx");
        request.setAccessType("OPENSEARCH");
        request.setNetworkType("PRIVATE");
        request.setIpWhitelist(Collections.singletonList("10.0.0.0/8"));

        AccessWhitelistResponse response = client.setAccessWhitelist(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/access-white-ips", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"accessType\":\"OPENSEARCH\""));
        Assert.assertTrue(body.contains("\"networkType\":\"PRIVATE\""));
        Assert.assertTrue(body.contains("\"ipWhitelist\":[\"10.0.0.0/8\"]"));
    }

    @Test
    public void testMigrateDataBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        MigrateDataRequest request = new MigrateDataRequest();
        request.setClusterId("search-xxxx");
        request.setNodes(Collections.singletonList("n-1"));
        request.setType("hot");

        MigrateDataResponse response = client.migrateData(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/migrations", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"nodes\":[\"n-1\"]"));
        Assert.assertTrue(body.contains("\"type\":\"hot\""));
    }

    @Test
    public void testRestartBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        RestartClusterRequest request = new RestartClusterRequest();
        request.setClusterId("search-xxxx");
        request.setRestartType("NODE");
        request.setMode("FORCE");
        request.setNodes(Collections.singletonList("n-1"));
        request.setBatchCount(50D);
        request.setBatchUnit("PERCENT");

        RestartClusterResponse response = client.restart(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/restart", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"restartType\":\"NODE\""));
        Assert.assertTrue(body.contains("\"mode\":\"FORCE\""));
        Assert.assertTrue(body.contains("\"nodes\":[\"n-1\"]"));
        Assert.assertTrue(body.contains("\"batchCount\":50"));
        Assert.assertTrue(body.contains("\"batchUnit\":\"PERCENT\""));
    }

    @Test
    public void testListActionsBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListActionsRequest request = new ListActionsRequest();
        request.setPageNo(1);
        request.setPageSize(20);
        request.setName("DEPLOY_CLUSTER");
        request.setStatus("RUNNING");
        request.setOrderBy("createTime");
        request.setOrder("desc");
        request.setClusterName("test-cluster");
        request.setClusterId("search-xxxx");

        ListActionsResponse response = client.listActions(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/actions", client.lastRequest.getUri().getPath());
        Assert.assertEquals("bj", client.lastRequest.getHeaders().get("X-Region"));
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals("1", client.lastRequest.getParameters().get("pageNo"));
        Assert.assertEquals("20", client.lastRequest.getParameters().get("pageSize"));
        Assert.assertEquals("DEPLOY_CLUSTER", client.lastRequest.getParameters().get("name"));
        Assert.assertEquals("RUNNING", client.lastRequest.getParameters().get("status"));
        Assert.assertEquals("createTime", client.lastRequest.getParameters().get("orderBy"));
        Assert.assertEquals("desc", client.lastRequest.getParameters().get("order"));
        Assert.assertEquals("test-cluster", client.lastRequest.getParameters().get("clusterName"));
        Assert.assertEquals("search-xxxx", client.lastRequest.getParameters().get("clusterId"));
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testListActionTypesBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");

        ListActionTypesResponse response = client.listActionTypes(new ListActionTypesRequest());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/actions/action-types", client.lastRequest.getUri().getPath());
        Assert.assertEquals("bj", client.lastRequest.getHeaders().get("X-Region"));
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testListOperationsBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListOperationsRequest request = new ListOperationsRequest();
        request.setClusterId("search-xxxx");
        request.setActionId("action-xxxx");

        ListOperationsResponse response = client.listOperations(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/actions/action-xxxx/operations", client.lastRequest.getUri().getPath());
        Assert.assertEquals("bj", client.lastRequest.getHeaders().get("X-Region"));
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testGetOperationBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetOperationRequest request = new GetOperationRequest();
        request.setClusterId("search-xxxx");
        request.setActionId("action-xxxx");
        request.setOperationId("550e8400-e29b-41d4-a716-446655440000");

        GetOperationResponse response = client.getOperation(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/actions/action-xxxx/operations/550e8400-e29b-41d4-a716-446655440000", client.lastRequest.getUri().getPath());
        Assert.assertEquals("bj", client.lastRequest.getHeaders().get("X-Region"));
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testListOperationAnalysisDetailsBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListOperationAnalysisDetailsRequest request = new ListOperationAnalysisDetailsRequest();
        request.setClusterId("search-xxxx");
        request.setActionId("action-xxxx");
        request.setOperationId("550e8400-e29b-41d4-a716-446655440000");
        request.setGroupNames("hot,warm");
        request.setPageNo(1);
        request.setPageSize(20);

        ListOperationAnalysisDetailsResponse response = client.listOperationAnalysisDetails(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/actions/action-xxxx/operations/550e8400-e29b-41d4-a716-446655440000/groups/hot,warm", client.lastRequest.getUri().getPath());
        Assert.assertEquals("bj", client.lastRequest.getHeaders().get("X-Region"));
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals("1", client.lastRequest.getParameters().get("pageNo"));
        Assert.assertEquals("20", client.lastRequest.getParameters().get("pageSize"));
        Assert.assertNull(client.lastRequest.getContent());
    }

    private static final String TEST_ACCESS_KEY_ID = "ak-test-0000000000000000";
    private static final String TEST_SECRET_KEY = "sk-test-0000000000000000";

    private static BceClientConfiguration configWithTestCredentials() {
        return new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(TEST_ACCESS_KEY_ID, TEST_SECRET_KEY));
    }

    private static String aes128DecryptWithFirst16Char(String cipherHex, String privateKey) throws GeneralSecurityException {
        javax.crypto.spec.SecretKeySpec skey =
                new javax.crypto.spec.SecretKeySpec(privateKey.substring(0, 16).getBytes(StandardCharsets.UTF_8), "AES");
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, skey);
        byte[] cipherBytes;
        try {
            cipherBytes = org.apache.commons.codec.binary.Hex.decodeHex(cipherHex.toCharArray());
        } catch (org.apache.commons.codec.DecoderException e) {
            throw new GeneralSecurityException(e);
        }
        return new String(cipher.doFinal(cipherBytes), StandardCharsets.UTF_8);
    }

    private static CreateClusterRequest createValidCreateClusterRequest() {
        CreateClusterRequest request = new CreateClusterRequest();
        request.setPayment("Postpaid");
        request.setName("testOpenapiMain");
        request.setEngine("OPENSEARCH");
        request.setEngineType("COMMUNITY");
        request.setVersion("2.19.4");
        request.setAdminPassword("encoded-password");
        request.setEnableHttps(Boolean.FALSE);
        request.setDeletionProtection(Boolean.FALSE);
        request.setEnableDeploySet(Boolean.FALSE);
        request.setLogicalZones(Collections.singletonList("cn-bj-a"));
        request.setVpcId("vpc-pekgs85bpt95");
        request.setSubnetId("sbn-gcv4r388upuq");

        NodeSpec dataNode = new NodeSpec();
        dataNode.setType("data");
        dataNode.setNodeType("opensearch.c2m8");
        dataNode.setNodeCount(3);
        dataNode.setDiskType("enhanced_ssd_pl1");
        dataNode.setDiskSize(50);
        dataNode.setDiskCount(1);

        NodeSpec dashboardsNode = new NodeSpec();
        dashboardsNode.setType("dashboards");
        dashboardsNode.setNodeType("opensearch.c2m4");
        dashboardsNode.setNodeCount(1);

        request.setNodeSpecs(Arrays.asList(dataNode, dashboardsNode));
        request.setTags(Collections.<Tag>emptyList());
        return request;
    }

    @Test
    public void testListIndicesBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListIndicesRequest request = new ListIndicesRequest();
        request.setClusterId("search-xxxx");
        request.setIndexName("my-index");
        request.setHealth("green");
        request.setIncludeSystem(Boolean.TRUE);
        request.setPageNo(1);
        request.setPageSize(10);
        request.setOrderBy("createTime");
        request.setOrder("desc");

        ListIndicesResponse response = client.listIndices(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices", client.lastRequest.getUri().getPath());
        Assert.assertEquals("my-index", client.lastRequest.getParameters().get("indexName"));
        Assert.assertEquals("green", client.lastRequest.getParameters().get("health"));
        Assert.assertEquals("true", String.valueOf(client.lastRequest.getParameters().get("includeSystem")));
        Assert.assertEquals("1", String.valueOf(client.lastRequest.getParameters().get("pageNo")));
        Assert.assertEquals("10", String.valueOf(client.lastRequest.getParameters().get("pageSize")));
        Assert.assertEquals("createTime", client.lastRequest.getParameters().get("orderBy"));
        Assert.assertEquals("desc", client.lastRequest.getParameters().get("order"));
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testGetIndexDetailBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetIndexDetailRequest request = new GetIndexDetailRequest();
        request.setClusterId("search-xxxx");
        request.setIndexName("openapi_test_index");

        IndexDetailResponse response = client.getIndexDetail(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/openapi_test_index", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testIndexDetailResponseDeserializesNestedIndexFields() {
        String json = "{"
                + "\"index\":{\"indexName\":\"openapi_test_index\",\"health\":\"green\",\"status\":\"open\","
                + "\"primaryShards\":\"1\",\"replicas\":\"0\",\"documentCount\":\"0\",\"storageSize\":\"208b\","
                + "\"createTime\":\"1783567858578\",\"category\":\"default\"},"
                + "\"mappings\":\"{\\\"properties\\\":{\\\"title\\\":{\\\"type\\\":\\\"text\\\"}}}\","
                + "\"settings\":\"{\\\"index.number_of_shards\\\":\\\"1\\\"}\","
                + "\"aliases\":\"{}\""
                + "}";

        IndexDetailResponse response = JsonUtils.fromJsonString(json, IndexDetailResponse.class);

        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getIndex());
        Assert.assertEquals("openapi_test_index", response.getIndex().getIndexName());
        Assert.assertEquals("green", response.getIndex().getHealth());
        Assert.assertEquals("open", response.getIndex().getStatus());
        Assert.assertEquals("1", response.getIndex().getPrimaryShards());
        Assert.assertEquals("0", response.getIndex().getReplicas());
        Assert.assertEquals("0", response.getIndex().getDocumentCount());
        Assert.assertEquals("208b", response.getIndex().getStorageSize());
        Assert.assertEquals("1783567858578", response.getIndex().getCreateTime());
        Assert.assertEquals("default", response.getIndex().getCategory());
        Assert.assertTrue(response.getMappings().contains("\"title\""));
        Assert.assertTrue(response.getSettings().contains("number_of_shards"));
        Assert.assertEquals("{}", response.getAliases());
    }

    @Test
    public void testCreateIndexBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateIndexRequest request = new CreateIndexRequest();
        request.setClusterId("search-xxxx");
        request.setIndexName("openapi_test_index");
        request.setSettings(Collections.singletonMap("index", Collections.singletonMap("number_of_shards", "1")));
        request.setMappings(Collections.singletonMap("properties", Collections.singletonMap("title", Collections.singletonMap("type", "text"))));
        request.setAliases(Collections.emptyMap());

        IndexNameResponse response = client.createIndex(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/openapi_test_index", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"number_of_shards\":\"1\""));
        Assert.assertTrue(body.contains("\"title\""));
    }

    @Test
    public void testDeleteIndicesBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        DeleteIndicesRequest request = new DeleteIndicesRequest();
        request.setClusterId("search-xxxx");
        request.setIndexNames(Arrays.asList("index-a", "index-b"));

        IndexOperationSuccessResponse response = client.deleteIndices(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/_delete", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"indexNames\""));
        Assert.assertTrue(body.contains("index-a"));
        Assert.assertTrue(body.contains("index-b"));
    }

    @Test
    public void testUpdateIndexSettingsBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateIndexSettingsRequest request = new UpdateIndexSettingsRequest();
        request.setClusterId("search-xxxx");
        request.setIndexName("openapi_test_index");
        request.setSettings(Collections.singletonMap("index", Collections.singletonMap("refresh_interval", "1s")));

        IndexNameResponse response = client.updateIndexSettings(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/openapi_test_index/settings", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"refresh_interval\":\"1s\""));
    }

    @Test
    public void testUpdateIndexMappingsBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateIndexMappingsRequest request = new UpdateIndexMappingsRequest();
        request.setClusterId("search-xxxx");
        request.setIndexName("openapi_test_index");
        request.setType("_doc");
        request.setMappings(Collections.singletonMap("properties", Collections.singletonMap("summary", Collections.singletonMap("type", "text"))));

        IndexNameResponse response = client.updateIndexMappings(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/openapi_test_index/mappings", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"type\":\"_doc\""));
        Assert.assertTrue(body.contains("\"summary\""));
    }

    @Test
    public void testUpdateIndexAliasesBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateIndexAliasesRequest request = new UpdateIndexAliasesRequest();
        request.setClusterId("search-xxxx");
        request.setIndexName("openapi_test_index");
        UpdateIndexAliasesRequest.AliasAction action = new UpdateIndexAliasesRequest.AliasAction();
        action.setAction("add");
        action.setAlias("openapi_test_alias");
        request.setActions(Collections.singletonList(action));

        IndexNameResponse response = client.updateIndexAliases(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/openapi_test_index/aliases", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"actions\""));
        Assert.assertTrue(body.contains("openapi_test_alias"));
    }

    @Test
    public void testGetIndexStatsBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetIndexStatsRequest request = new GetIndexStatsRequest();
        request.setClusterId("search-xxxx");
        request.setIndexName("openapi_test_index");

        IndexStatsResponse response = client.getIndexStats(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/openapi_test_index/stats", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testListIndexTemplatesBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListIndexTemplatesRequest request = new ListIndexTemplatesRequest();
        request.setClusterId("search-xxxx");

        ListIndexTemplatesResponse response = client.listIndexTemplates(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/index-templates", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testGetIndexTemplateDetailBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetIndexTemplateDetailRequest request = new GetIndexTemplateDetailRequest();
        request.setClusterId("search-xxxx");
        request.setTemplateName("openapi_test_template");

        IndexTemplateDetailResponse response = client.getIndexTemplateDetail(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/index-templates/openapi_test_template", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testCreateIndexTemplateBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateIndexTemplateRequest request = new CreateIndexTemplateRequest();
        request.setClusterId("search-xxxx");
        request.setTemplateName("openapi_test_template");
        request.setIndexPatterns(Collections.singletonList("openapi_test_*"));
        request.setPriority("1");
        request.setOrder("1");
        request.setIsLegacy("true");
        Map<String, Object> template = new LinkedHashMap<>();
        template.put("settings", Collections.singletonMap("index", Collections.singletonMap("number_of_shards", "1")));
        template.put("mappings", Collections.singletonMap("properties", Collections.singletonMap("title", Collections.singletonMap("type", "text"))));
        template.put("aliases", Collections.emptyMap());
        request.setTemplate(template);

        IndexTemplateNameResponse response = client.createIndexTemplate(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/index-templates", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("openapi_test_template"));
        Assert.assertTrue(body.contains("openapi_test_*"));
        Assert.assertTrue(body.contains("\"priority\":\"1\""));
        Assert.assertTrue(body.contains("\"isLegacy\":\"true\""));
        Assert.assertTrue(body.contains("\"template\":{"));
        Assert.assertTrue(body.contains("number_of_shards"));
    }

    @Test
    public void testUpdateIndexTemplateBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateIndexTemplateRequest request = new UpdateIndexTemplateRequest();
        request.setClusterId("search-xxxx");
        request.setTemplateName("openapi_test_template");
        request.setIndexPatterns(Collections.singletonList("openapi_test_*"));
        request.setPriority("1");
        request.setOrder("1");
        request.setIsLegacy("true");
        Map<String, Object> template = new LinkedHashMap<>();
        template.put("settings", Collections.singletonMap("index", Collections.singletonMap("refresh_interval", "1s")));
        template.put("mappings", Collections.singletonMap("properties", Collections.singletonMap("title", Collections.singletonMap("type", "text"))));
        template.put("aliases", Collections.emptyMap());
        request.setTemplate(template);

        IndexTemplateNameResponse response = client.updateIndexTemplate(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/index-templates/openapi_test_template", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("openapi_test_*"));
        Assert.assertTrue(body.contains("refresh_interval"));
        Assert.assertTrue(body.contains("\"priority\":\"1\""));
        Assert.assertTrue(body.contains("\"isLegacy\":\"true\""));
        Assert.assertTrue(body.contains("\"template\":{"));
    }

    @Test
    public void testDeleteIndexTemplateBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        DeleteIndexTemplateRequest request = new DeleteIndexTemplateRequest();
        request.setClusterId("search-xxxx");
        request.setTemplateName("openapi_test_template");

        IndexTemplateNameResponse response = client.deleteIndexTemplate(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.DELETE, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/index-templates/openapi_test_template", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testOpenIndicesBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        OpenIndicesRequest request = new OpenIndicesRequest();
        request.setClusterId("search-xxxx");
        request.setIndexNames(Arrays.asList("index-a", "index-b"));

        IndexOperationSuccessResponse response = client.openIndices(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/_open", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("index-a"));
        Assert.assertTrue(body.contains("index-b"));
    }

    @Test
    public void testCloseIndicesBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CloseIndicesRequest request = new CloseIndicesRequest();
        request.setClusterId("search-xxxx");
        request.setIndexNames(Arrays.asList("index-a", "index-b"));

        IndexOperationSuccessResponse response = client.closeIndices(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/_close", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("index-a"));
        Assert.assertTrue(body.contains("index-b"));
    }

    @Test
    public void testRefreshIndicesBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        RefreshIndicesRequest request = new RefreshIndicesRequest();
        request.setClusterId("search-xxxx");
        request.setIndexNames(Arrays.asList("index-a", "index-b"));

        IndexOperationSuccessResponse response = client.refreshIndices(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/_refresh", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("index-a"));
        Assert.assertTrue(body.contains("index-b"));
    }

    @Test
    public void testFlushIndicesBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        FlushIndicesRequest request = new FlushIndicesRequest();
        request.setClusterId("search-xxxx");
        request.setIndexNames(Arrays.asList("index-a", "index-b"));

        IndexOperationSuccessResponse response = client.flushIndices(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/_flush", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("index-a"));
        Assert.assertTrue(body.contains("index-b"));
    }

    @Test
    public void testForcemergeIndicesBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ForcemergeIndicesRequest request = new ForcemergeIndicesRequest();
        request.setClusterId("search-xxxx");
        request.setIndexNames(Collections.singletonList("index-a"));
        request.setMaxNumSegments(1);
        request.setOnlyExpungeDeletes(Boolean.TRUE);

        IndexOperationSuccessResponse response = client.forcemergeIndices(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/_forcemerge", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"maxNumSegments\":1"));
        Assert.assertTrue(body.contains("\"onlyExpungeDeletes\":true"));
    }

    @Test
    public void testClearCacheIndicesBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ClearCacheIndicesRequest request = new ClearCacheIndicesRequest();
        request.setClusterId("search-xxxx");
        request.setIndexNames(Collections.singletonList("index-a"));

        IndexOperationSuccessResponse response = client.clearCacheIndices(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/_clear_cache", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("index-a"));
    }

    @Test
    public void testCheckIndexExistBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CheckIndexExistRequest request = new CheckIndexExistRequest();
        request.setClusterId("search-xxxx");
        request.setIndexName("openapi_test_index");

        IndexExistResponse response = client.checkIndexExist(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/indices/openapi_test_index/_exist", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testIsIndexTemplateExistsBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CheckIndexTemplateExistRequest request = new CheckIndexTemplateExistRequest();
        request.setClusterId("search-xxxx");
        request.setTemplateName("openapi_test_template");

        IndexTemplateExistResponse response = client.isIndexTemplateExists(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/index-templates/openapi_test_template/_exist", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testGetIndexFieldTypesBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetIndexFieldTypesRequest request = new GetIndexFieldTypesRequest();
        request.setClusterId("search-xxxx");

        IndexFieldTypesResult response = client.getIndexFieldTypes(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/index-field-types", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testListMonitorIndicesBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListMonitorIndicesRequest request = new ListMonitorIndicesRequest();
        request.setClusterId("search-xxxx");
        request.setIndexName("top_queries");
        request.setIncludeSystem(Boolean.TRUE);
        request.setPageNo(1);
        request.setPageSize(100);

        MonitorIndicesResponse response = client.listMonitorIndices(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/monitor/indices", client.lastRequest.getUri().getPath());
        Assert.assertEquals("top_queries", client.lastRequest.getParameters().get("indexName"));
        Assert.assertEquals("true", String.valueOf(client.lastRequest.getParameters().get("includeSystem")));
        Assert.assertEquals("1", String.valueOf(client.lastRequest.getParameters().get("pageNo")));
        Assert.assertEquals("100", String.valueOf(client.lastRequest.getParameters().get("pageSize")));
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testCreateIsmPolicyBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateIsmPolicyRequest request = new CreateIsmPolicyRequest();
        request.setClusterId("search-xxxx");
        request.setPolicyId("openapi_test_policy");
        request.setDescription("OpenAPI smoke policy");
        IsmTemplate ismTemplate = new IsmTemplate();
        ismTemplate.setIndexPatterns(Collections.singletonList("openapi_test_*"));
        ismTemplate.setPriority("1");
        request.setIsmTemplate(Collections.singletonList(ismTemplate));
        IsmDelete delete = new IsmDelete();
        delete.setEnabled(Boolean.TRUE);
        delete.setMinIndexAge("30d");
        request.setDelete(delete);

        IsmPolicyIdResponse response = client.createIsmPolicy(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/ism-policies/openapi_test_policy", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("OpenAPI smoke policy"));
        Assert.assertTrue(body.contains("openapi_test_*"));
    }

    @Test
    public void testUpdateIsmPolicyBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateIsmPolicyRequest request = new UpdateIsmPolicyRequest();
        request.setClusterId("search-xxxx");
        request.setPolicyId("openapi_test_policy");
        request.setDescription("OpenAPI smoke policy updated");
        IsmTemplate ismTemplate = new IsmTemplate();
        ismTemplate.setIndexPatterns(Collections.singletonList("openapi_test_*"));
        ismTemplate.setPriority("1");
        request.setIsmTemplate(Collections.singletonList(ismTemplate));

        IsmPolicyIdResponse response = client.updateIsmPolicy(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/ism-policies/openapi_test_policy", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("OpenAPI smoke policy updated"));
    }

    @Test
    public void testDeleteIsmPolicyBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        DeleteIsmPolicyRequest request = new DeleteIsmPolicyRequest();
        request.setClusterId("search-xxxx");
        request.setPolicyId("openapi_test_policy");

        IsmPolicyIdResponse response = client.deleteIsmPolicy(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.DELETE, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/ism-policies/openapi_test_policy", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testListIsmPoliciesBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListIsmPoliciesRequest request = new ListIsmPoliciesRequest();
        request.setClusterId("search-xxxx");

        ListIsmPoliciesResult response = client.listIsmPolicies(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/ism-policies", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testGetIsmPolicyDetailBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetIsmPolicyDetailRequest request = new GetIsmPolicyDetailRequest();
        request.setClusterId("search-xxxx");
        request.setPolicyId("openapi_test_policy");

        IsmPolicyDetailResult response = client.getIsmPolicyDetail(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/ism-policies/openapi_test_policy", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testCheckIsmPolicyExistBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CheckIsmPolicyExistRequest request = new CheckIsmPolicyExistRequest();
        request.setClusterId("search-xxxx");
        request.setPolicyId("openapi_test_policy");

        IsmPolicyExistResponse response = client.checkIsmPolicyExist(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/index-management/ism-policies/openapi_test_policy/_exist", client.lastRequest.getUri().getPath());
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testResetAdminPasswordBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(configWithTestCredentials(), "bj");
        ResetAdminPasswordRequest request = new ResetAdminPasswordRequest();
        request.setClusterId("search-xxxx");
        request.setNewPassword("dGVzdE9wZW5hcGky");

        ResetAdminPasswordResponse response = client.resetAdminPassword(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/users/admins/passwords", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals(TEST_ACCESS_KEY_ID, client.lastRequest.getHeaders().get("x-bce-accesskey"));
        Assert.assertFalse(body.contains("clusterId"));
        Assert.assertFalse(body.contains("\"newPassword\":\"dGVzdE9wZW5hcGky\""));

        java.util.regex.Matcher matcher =
                java.util.regex.Pattern.compile("\"newPassword\":\"([0-9a-fA-F]+)\"").matcher(body);
        Assert.assertTrue(matcher.find());
        Assert.assertEquals("dGVzdE9wZW5hcGky", aes128DecryptWithFirst16Char(matcher.group(1), TEST_SECRET_KEY));
    }

    @Test
    public void testGetLogSwitchBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"enabled\":true,"
                + "\"types\":{\"main\":true,\"gc\":false,\"search-slow\":true,\"index-slow\":true,\"audit\":false}"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        GetLogSwitchRequest request = new GetLogSwitchRequest();
        request.setClusterId("search-xxxx");

        LogSwitchResponse response = client.getLogSwitch(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/logs/switch", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals(Boolean.TRUE, response.getEnabled());
        Assert.assertEquals(Boolean.TRUE, response.getTypes().get("main"));
        Assert.assertEquals(Boolean.FALSE, response.getTypes().get("gc"));
    }

    @Test
    public void testUpdateLogSwitchBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateLogSwitchRequest request = new UpdateLogSwitchRequest();
        request.setClusterId("search-xxxx");
        request.setEnabled(Boolean.TRUE);
        Map<String, Boolean> types = new LinkedHashMap<String, Boolean>();
        types.put("main", Boolean.TRUE);
        types.put("gc", Boolean.FALSE);
        request.setTypes(types);

        LogSwitchResponse response = client.updateLogSwitch(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/logs/switch", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"enabled\":true"));
        Assert.assertTrue(body.contains("\"main\":true"));
        Assert.assertTrue(body.contains("\"gc\":false"));
    }

    @Test
    public void testUpdateLogSwitchOnlyEnabledBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateLogSwitchRequest request = new UpdateLogSwitchRequest();
        request.setClusterId("search-xxxx");
        request.setEnabled(Boolean.FALSE);

        client.updateLogSwitch(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertTrue(body.contains("\"enabled\":false"));
        Assert.assertFalse(body.contains("types"));
    }

    @Test
    public void testGetLogUsageBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetLogUsageRequest request = new GetLogUsageRequest();
        request.setClusterId("search-xxxx");

        LogUsageResponse response = client.getLogUsage(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/logs/usage", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertNull(client.lastRequest.getContent());
    }

    @Test
    public void testLogUsageResponseDeserializesNestedIndices() {
        String json = "{"
                + "\"totalSizeBytes\":104857600,"
                + "\"totalShards\":12,"
                + "\"indices\":{"
                + "\"main\":{\"sizeBytes\":52428800,\"shards\":4,\"docCount\":10240},"
                + "\"search-slow\":{\"sizeBytes\":20971520,\"shards\":2,\"docCount\":2560}"
                + "}"
                + "}";

        LogUsageResponse response = JsonUtils.fromJsonString(json, LogUsageResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(Long.valueOf(104857600), response.getTotalSizeBytes());
        Assert.assertEquals(Integer.valueOf(12), response.getTotalShards());
        Assert.assertEquals(2, response.getIndices().size());
        Assert.assertEquals(Long.valueOf(52428800), response.getIndices().get("main").getSizeBytes());
        Assert.assertEquals(Integer.valueOf(4), response.getIndices().get("main").getShards());
        Assert.assertEquals(Long.valueOf(10240), response.getIndices().get("main").getDocCount());
        Assert.assertEquals(Long.valueOf(2560), response.getIndices().get("search-slow").getDocCount());
    }

    @Test
    public void testGetLogCollectorStatusBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"deployed\":true,"
                + "\"operationStatus\":\"FINISHED\","
                + "\"operationType\":\"DEPLOY_LOG_COLLECTOR\","
                + "\"operationId\":\"op-xxxxxxxx\""
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        GetLogCollectorStatusRequest request = new GetLogCollectorStatusRequest();
        request.setClusterId("search-xxxx");

        LogCollectorStatusResponse response = client.getLogCollectorStatus(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/logs/collector/status", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals(Boolean.TRUE, response.getDeployed());
        Assert.assertEquals("FINISHED", response.getOperationStatus());
        Assert.assertEquals("DEPLOY_LOG_COLLECTOR", response.getOperationType());
        Assert.assertEquals("op-xxxxxxxx", response.getOperationId());
    }

    @Test
    public void testSearchLogsBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        SearchLogsRequest request = new SearchLogsRequest();
        request.setClusterId("search-xxxx");
        request.setLogType("main");
        request.setKeyword("error");
        request.setLevel("ERROR");
        request.setNodeName("bes-node-1");
        request.setStartTime("2026-07-10T00:00:00Z");
        request.setEndTime("2026-07-10T01:00:00Z");
        request.setPage(1);
        request.setPageSize(20);

        SearchLogsResponse response = client.searchLogs(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/logs/search", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"logType\":\"main\""));
        Assert.assertTrue(body.contains("\"keyword\":\"error\""));
        Assert.assertTrue(body.contains("\"level\":\"ERROR\""));
        Assert.assertTrue(body.contains("\"nodeName\":\"bes-node-1\""));
        Assert.assertTrue(body.contains("\"startTime\":\"2026-07-10T00:00:00Z\""));
        Assert.assertTrue(body.contains("\"endTime\":\"2026-07-10T01:00:00Z\""));
        Assert.assertTrue(body.contains("\"page\":1"));
        Assert.assertTrue(body.contains("\"pageSize\":20"));
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchLogsRejectsEmptyLogType() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        SearchLogsRequest request = new SearchLogsRequest();
        request.setClusterId("search-xxxx");
        request.setLogType(" ");
        request.setStartTime("2026-07-10T00:00:00Z");
        request.setEndTime("2026-07-10T01:00:00Z");

        client.searchLogs(request);
    }

    @Test
    public void testSearchLogsResponseDeserializesLogsList() {
        String json = "{"
                + "\"total\":1,"
                + "\"page\":1,"
                + "\"pageSize\":20,"
                + "\"logs\":[{\"timestamp\":\"2026-07-10T00:00:01Z\",\"level\":\"ERROR\","
                + "\"nodeName\":\"bes-node-1\",\"message\":\"failed to execute search request\"}]"
                + "}";

        SearchLogsResponse response = JsonUtils.fromJsonString(json, SearchLogsResponse.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(Long.valueOf(1), response.getTotal());
        Assert.assertEquals(Integer.valueOf(1), response.getPage());
        Assert.assertEquals(Integer.valueOf(20), response.getPageSize());
        Assert.assertEquals(1, response.getLogs().size());
        Assert.assertEquals("ERROR", response.getLogs().get(0).get("level"));
        Assert.assertEquals("bes-node-1", response.getLogs().get(0).get("nodeName"));
        Assert.assertEquals("failed to execute search request", response.getLogs().get(0).get("message"));
    }

    @Test
    public void testListSystemPluginsBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"devPlugins\":[{\"pluginName\":\"analysis-ik\",\"engine\":\"OPENSEARCH\","
                + "\"description\":\"中文分词插件\",\"order\":1,"
                + "\"versionInfos\":[{\"version\":\"1.0.0\",\"status\":\"INSTALLED\"}]}],"
                + "\"prePlugins\":[]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        ListSystemPluginsRequest request = new ListSystemPluginsRequest();
        request.setClusterId("search-xxxx");

        ListSystemPluginsResponse response = client.listSystemPlugins(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/system-plugins", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals(1, response.getDevPlugins().size());
        Assert.assertEquals("analysis-ik", response.getDevPlugins().get(0).getPluginName());
        Assert.assertEquals("1.0.0", response.getDevPlugins().get(0).getVersionInfos().get(0).getVersion());
        Assert.assertTrue(response.getPrePlugins().isEmpty());
    }

    @Test
    public void testListCustomPluginsBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"customPlugins\":[{\"pluginName\":\"custom-analyzer\","
                + "\"versionInfos\":[{\"version\":\"1.0.0\",\"status\":\"AVAILABLE\",\"uploadTime\":1783699200000}]}]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        ListCustomPluginsRequest request = new ListCustomPluginsRequest();
        request.setClusterId("search-xxxx");

        ListCustomPluginsResponse response = client.listCustomPlugins(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/custom-plugins", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertEquals(1, response.getCustomPlugins().size());
        Assert.assertEquals("custom-analyzer", response.getCustomPlugins().get(0).getPluginName());
        Assert.assertEquals(Long.valueOf(1783699200000L),
                response.getCustomPlugins().get(0).getVersionInfos().get(0).getUploadTime());
    }

    @Test
    public void testUpdateSystemPluginsBuildsExpectedRequest() throws Exception {
        String responseJson = "{\"clusterId\":\"search-xxxx\",\"clusterName\":\"test-cluster\","
                + "\"actionId\":\"action-xxxxxxxx\",\"orderId\":\"order-xxxxxxxx\"}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        UpdateSystemPluginsRequest request = new UpdateSystemPluginsRequest();
        request.setClusterId("search-xxxx");
        PluginItem installItem = new PluginItem();
        installItem.setName("analysis-ik");
        installItem.setVersion("1.0.0");
        request.setInstall(Collections.singletonList(installItem));

        UpdatePluginResponse response = client.updateSystemPlugins(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals("action-xxxxxxxx", response.getActionId());
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/system-plugins", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"name\":\"analysis-ik\""));
        Assert.assertTrue(body.contains("\"version\":\"1.0.0\""));
    }

    @Test
    public void testUpdateCustomPluginsBuildsExpectedRequest() throws Exception {
        String responseJson = "{\"clusterId\":\"search-xxxx\",\"clusterName\":\"test-cluster\","
                + "\"actionId\":\"action-xxxxxxxx\",\"orderId\":\"order-xxxxxxxx\"}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        UpdateCustomPluginsRequest request = new UpdateCustomPluginsRequest();
        request.setClusterId("search-xxxx");
        PluginItem uninstallItem = new PluginItem();
        uninstallItem.setName("custom-analyzer");
        uninstallItem.setVersion("1.0.0");
        request.setUninstall(Collections.singletonList(uninstallItem));

        UpdatePluginResponse response = client.updateCustomPlugins(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertNotNull(response);
        Assert.assertEquals("action-xxxxxxxx", response.getActionId());
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/custom-plugins", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"name\":\"custom-analyzer\""));
        Assert.assertFalse(body.contains("\"install\""));
    }

    @Test
    public void testDeleteCustomPluginVersionBuildsExpectedRequest() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"pluginName\":\"custom-analyzer\",\"version\":\"1.0.0\"}");
        DeleteCustomPluginVersionRequest request = new DeleteCustomPluginVersionRequest();
        request.setClusterId("search-xxxx");
        request.setPluginName("custom-analyzer");
        request.setVersion("1.0.0");

        DeleteCustomPluginVersionResponse response = client.deleteCustomPluginVersion(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpMethodName.DELETE, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/custom-plugins/custom-analyzer/versions/1.0.0",
                client.lastRequest.getUri().getPath());
        Assert.assertEquals("custom-analyzer", response.getPluginName());
        Assert.assertEquals("1.0.0", response.getVersion());
    }

    @Test
    public void testUploadFileBuildsExpectedMultipartRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"url\":\"bos://bucket/custom-analyzer.zip\",\"size\":102400}")
                .captureContentBeforeClose();
        File tempFile = File.createTempFile("custom-analyzer", ".zip");
        tempFile.deleteOnExit();
        writeFile(tempFile, "zip-bytes");
        UploadFileRequest request = new UploadFileRequest();
        request.setClusterId("search-xxxx");
        request.setFile(tempFile);
        request.setType("PluginZip");

        UploadFileResponse response = client.uploadFile(request);
        String body = client.capturedContent;
        String contentType = client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE);
        String contentLength = client.lastRequest.getHeaders().get(Headers.CONTENT_LENGTH);

        Assert.assertNotNull(response);
        Assert.assertEquals("bos://bucket/custom-analyzer.zip", response.getUrl());
        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/files", client.lastRequest.getUri().getPath());
        Assert.assertTrue(contentType.startsWith("multipart/form-data; boundary="));
        String boundary = contentType.substring("multipart/form-data; boundary=".length());
        Assert.assertTrue(body.contains("Content-Disposition: form-data; name=\"type\""));
        Assert.assertTrue(body.contains("PluginZip"));
        Assert.assertTrue(body.contains("Content-Disposition: form-data; name=\"file\"; filename=\""
                + tempFile.getName() + "\""));
        Assert.assertTrue(body.contains("Content-Type: application/zip"));
        Assert.assertTrue(body.contains("zip-bytes"));
        Assert.assertTrue(body.contains("--" + boundary + "--"));
        Assert.assertFalse(body.contains("name=\"subtype\""));
        Assert.assertEquals(body.getBytes(StandardCharsets.UTF_8).length, Long.parseLong(contentLength));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUploadFileRejectsNullFile() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UploadFileRequest request = new UploadFileRequest();
        request.setClusterId("search-xxxx");

        client.uploadFile(request);
    }

    @Test
    public void testUploadFileRestartReplaysIdenticalBytes() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"url\":\"bos://bucket/custom-analyzer.zip\",\"size\":102400}");
        File tempFile = File.createTempFile("custom-analyzer-restart", ".zip");
        tempFile.deleteOnExit();
        writeFile(tempFile, "zip-bytes-for-restart-test");
        UploadFileRequest request = new UploadFileRequest();
        request.setClusterId("search-xxxx");
        request.setFile(tempFile);
        request.setType("PluginZip");

        client.uploadFile(request);
        RestartableInputStream content = client.lastRequest.getContent();
        content.restart();

        String firstRead = readContent(content);
        content.restart();
        String secondRead = readContent(content);

        Assert.assertEquals(firstRead, secondRead);
        Assert.assertTrue(firstRead.contains("zip-bytes-for-restart-test"));
    }

    @Test
    public void testUploadFileRejectsMissingFile() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UploadFileRequest request = new UploadFileRequest();
        request.setClusterId("search-xxxx");
        request.setFile(new File("/tmp/does-not-exist-" + UUID.randomUUID() + ".zip"));
        request.setType("PluginZip");

        try {
            client.uploadFile(request);
            Assert.fail("expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    @Test
    public void testUploadFileRejectsOversizedFile() throws Exception {
        BesClientV3 client = new BesClientV3(new BceClientConfiguration(), "bj");
        File tempFile = File.createTempFile("oversized", ".bin");
        tempFile.deleteOnExit();
        File oversizedFile = new File(tempFile.getPath()) {
            @Override
            public long length() {
                return 5L * 1024 * 1024 * 1024 + 1;
            }
        };

        Method validate = BesClientV3.class.getDeclaredMethod("validateUploadFile", File.class);
        validate.setAccessible(true);
        try {
            validate.invoke(client, oversizedFile);
            Assert.fail("expected IllegalArgumentException");
        } catch (java.lang.reflect.InvocationTargetException e) {
            Assert.assertTrue(e.getCause() instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testSanitizeFilenameWithQuotesAndControlChars() throws Exception {
        Method sanitize = BesClientV3.class.getDeclaredMethod("sanitizeFilename", String.class);
        sanitize.setAccessible(true);
        String result = (String) sanitize.invoke(null, "evil\"name\r\n.txt");
        Assert.assertEquals("evil\\\"name.txt", result);
    }

    @Test
    public void testGetBctAuthSwitchBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"enabled\":false}");
        GetBctAuthSwitchRequest request = new GetBctAuthSwitchRequest();

        BctAuthSwitchResponse response = client.getBctAuthSwitch(request);

        Assert.assertNotNull(response);
        Assert.assertEquals(Boolean.FALSE, response.getEnabled());
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/bct/auth/switch", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
    }

    @Test
    public void testUpdateBctAuthSwitchBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"enabled\":true}");
        UpdateBctAuthSwitchRequest request = new UpdateBctAuthSwitchRequest();
        request.setEnabled(Boolean.TRUE);

        BctAuthSwitchResponse response = client.updateBctAuthSwitch(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(Boolean.TRUE, response.getEnabled());
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/bct/auth/switch", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"enabled\":true"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateBctAuthSwitchRejectsNullEnabled() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateBctAuthSwitchRequest request = new UpdateBctAuthSwitchRequest();

        client.updateBctAuthSwitch(request);
    }

    @Test
    public void testSearchBctEventsBuildsExpectedRequestAndParsesResponse() throws Exception {
        String responseJson = "{"
                + "\"pageSize\":10,"
                + "\"truncated\":true,"
                + "\"nextMarker\":\"CJ_luKf0MxCygIHtuKf0MyAL\","
                + "\"data\":[{\"eventType\":\"console\",\"eventSource\":\"bes\","
                + "\"eventName\":\"createCluster\",\"eventTimeInMilliseconds\":1784191943000,"
                + "\"eventTime\":\"2026-07-16T08:52:23Z\",\"userIpAddress\":\"220.181.3.170\","
                + "\"userAgent\":\"Mozilla/5.0\",\"regionId\":\"bj\","
                + "\"requestId\":\"fbaa6891-d9ce\",\"orderId\":\"\",\"errorCode\":\"200\","
                + "\"errorMessage\":\"\",\"success\":true,"
                + "\"userIdentity\":{\"iamDomainId\":\"domain-xxxxxxxx\",\"iamUserId\":\"user-xxxxxxxx\","
                + "\"userDisplayName\":\"userName\"}}]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        SearchBctEventsRequest request = new SearchBctEventsRequest();
        request.setStartTime("2026-07-14T01:51:33.355981Z");
        request.setEndTime("2026-07-24T01:51:33.355981Z");
        request.setPageSize(20);
        SearchBctEventsRequest.BctQueryFilter filter = new SearchBctEventsRequest.BctQueryFilter();
        filter.setField("eventSource");
        filter.setValue("bes");
        request.setFilters(Collections.singletonList(filter));

        SearchBctEventsResponse response = client.searchBctEvents(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/bct/events/query", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"pageSize\":20"));
        Assert.assertTrue(body.contains("\"field\":\"eventSource\""));
        Assert.assertEquals(Integer.valueOf(10), response.getPageSize());
        Assert.assertEquals(Boolean.TRUE, response.getTruncated());
        Assert.assertEquals("CJ_luKf0MxCygIHtuKf0MyAL", response.getNextMarker());
        Assert.assertEquals(1, response.getData().size());
        Assert.assertEquals("createCluster", response.getData().get(0).getEventName());
        Assert.assertEquals("console", response.getData().get(0).getEventType());
        Assert.assertEquals("domain-xxxxxxxx", response.getData().get(0).getUserIdentity().getIamDomainId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchBctEventsRejectsNullStartTime() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        SearchBctEventsRequest request = new SearchBctEventsRequest();
        request.setEndTime("2026-07-24T01:51:33.355981Z");

        client.searchBctEvents(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSearchBctEventsRejectsNullEndTime() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        SearchBctEventsRequest request = new SearchBctEventsRequest();
        request.setStartTime("2026-07-14T01:51:33.355981Z");

        client.searchBctEvents(request);
    }

    @Test
    public void testGetAuditStatusBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"enabled\":true}");
        GetAuditStatusRequest request = new GetAuditStatusRequest();
        request.setClusterId("search-xxxx");

        AuditStatusResponse response = client.getAuditStatus(request);

        Assert.assertEquals(Boolean.TRUE, response.getEnabled());
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/audit", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
    }

    @Test
    public void testEnableAuditBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"enabled\":true}");
        EnableAuditRequest request = new EnableAuditRequest();
        request.setClusterId("search-xxxx");
        request.setTargetClusterId("search-yyyyyyyy");

        EnableAuditResponse response = client.enableAudit(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(Boolean.TRUE, response.getEnabled());
        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/audit", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"targetClusterId\":\"search-yyyyyyyy\""));
    }

    @Test
    public void testGetAuditEventTypesBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"eventTypes\":[\"LOGIN\",\"QUERY\"]}");
        GetAuditEventTypesRequest request = new GetAuditEventTypesRequest();
        request.setClusterId("search-xxxx");

        AuditEventTypesResponse response = client.getAuditEventTypes(request);

        Assert.assertEquals(Arrays.asList("LOGIN", "QUERY"), response.getEventTypes());
        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/audit/event-types", client.lastRequest.getUri().getPath());
    }

    @Test
    public void testSearchAuditEventsBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"logs\":[{\"category\":\"SSL_EXCEPTION\",\"timestamp\":\"2026-07-21T11:28:29.349Z\","
                + "\"content\":\"{\\\"audit_node_id\\\":\\\"abc\\\"}\"}],"
                + "\"cursor\":\"WzE3ODQ2MzMzMDkzNDksIlZoZ2xpWjhCWS1SRUVwQWpzR2dvIl0\""
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        SearchAuditEventsRequest request = new SearchAuditEventsRequest();
        request.setClusterId("search-xxxx");
        request.setStartTime("2026-07-17T02:35:31.234891Z");
        request.setEndTime("2026-07-24T02:35:31.234891Z");
        request.setCategory("FAILED_LOGIN");
        request.setIndexName(".opendistro_security_auditlog-*");
        request.setPageSize(20);
        request.setCursor("prev-cursor");

        SearchAuditEventsResponse response = client.searchAuditEvents(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/audit/events", client.lastRequest.getUri().getPath());
        Assert.assertEquals("2026-07-17T02:35:31.234891Z", client.lastRequest.getParameters().get("startTime"));
        Assert.assertEquals("2026-07-24T02:35:31.234891Z", client.lastRequest.getParameters().get("endTime"));
        Assert.assertEquals("FAILED_LOGIN", client.lastRequest.getParameters().get("category"));
        Assert.assertEquals(".opendistro_security_auditlog-*", client.lastRequest.getParameters().get("indexName"));
        Assert.assertEquals("20", client.lastRequest.getParameters().get("pageSize"));
        Assert.assertEquals("prev-cursor", client.lastRequest.getParameters().get("cursor"));
        Assert.assertEquals(1, response.getLogs().size());
        Assert.assertEquals("SSL_EXCEPTION", response.getLogs().get(0).getCategory());
        Assert.assertEquals("WzE3ODQ2MzMzMDkzNDksIlZoZ2xpWjhCWS1SRUVwQWpzR2dvIl0", response.getCursor());
    }

    @Test
    public void testConvertToPrepayBuildsExpectedRequestAndParsesResponse() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"orderId\":\"4d60bca6e30c42fe858031cf517bc65b\"}");
        ConvertToPrepayRequest request = new ConvertToPrepayRequest();
        request.setClusterId("search-xxxx");
        request.setTimeLength(1);
        request.setTimeUnit("MONTH");

        OrderIdResponse response = client.convertToPrepay(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/orders/to-prepay", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"timeLength\":1"));
        Assert.assertTrue(body.contains("\"timeUnit\":\"MONTH\""));
        Assert.assertEquals("4d60bca6e30c42fe858031cf517bc65b", response.getOrderId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToPrepayRejectsNullTimeLength() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ConvertToPrepayRequest request = new ConvertToPrepayRequest();
        request.setClusterId("search-xxxx");

        client.convertToPrepay(request);
    }

    @Test
    public void testConvertToPostpayBuildsExpectedRequestAndParsesResponse() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"orderId\":\"fc9f1ca0e3d04f0aa1023fe3be7f032c\"}");
        ConvertToPostpayRequest request = new ConvertToPostpayRequest();
        request.setClusterId("search-xxxx");

        OrderIdResponse response = client.convertToPostpay(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/orders/to-postpay", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertEquals("fc9f1ca0e3d04f0aa1023fe3be7f032c", response.getOrderId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToPostpayRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ConvertToPostpayRequest request = new ConvertToPostpayRequest();

        client.convertToPostpay(request);
    }

    @Test
    public void testCancelConvertToPostpayBuildsExpectedRequestAndParsesResponse() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"orderId\":\"fc9f1ca0e3d04f0aa1023fe3be7f032c\"}");
        CancelConvertToPostpayRequest request = new CancelConvertToPostpayRequest();
        request.setClusterId("search-xxxx");

        OrderIdResponse response = client.cancelConvertToPostpay(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/orders/to-postpay/cancel", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertEquals("fc9f1ca0e3d04f0aa1023fe3be7f032c", response.getOrderId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCancelConvertToPostpayRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CancelConvertToPostpayRequest request = new CancelConvertToPostpayRequest();

        client.cancelConvertToPostpay(request);
    }

    @Test
    public void testRenewClusterBuildsExpectedRequestAndParsesResponse() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"orderId\":\"26344cf2459745c980b06466a414a42a\"}");
        RenewClusterRequest request = new RenewClusterRequest();
        request.setClusterId("search-xxxx");
        request.setTimeLength(1);
        request.setTimeUnit("MONTH");

        OrderIdResponse response = client.renewCluster(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/order/renew/confirm", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"timeLength\":1"));
        Assert.assertEquals("26344cf2459745c980b06466a414a42a", response.getOrderId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRenewClusterRejectsNullTimeLength() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        RenewClusterRequest request = new RenewClusterRequest();
        request.setClusterId("search-xxxx");

        client.renewCluster(request);
    }

    @Test
    public void testQueryConfigPriceBuildsExpectedRequestAndParsesResponse() throws Exception {
        String responseJson = "{"
                + "\"price\":0.022076928,"
                + "\"catalogPrice\":0.044153856,"
                + "\"nodeTypePrices\":[{\"type\":\"data\",\"nodePrice\":0.016580586,"
                + "\"nodeCatalogPrice\":0.033161172,\"diskPrice\":0.0017325,\"diskCatalogPrice\":0.003465}]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        QueryConfigPriceRequest request = new QueryConfigPriceRequest();
        request.setPayment("Postpaid");
        com.baidubce.services.bes.v3.model.billing.NodeSpec nodeSpec =
                new com.baidubce.services.bes.v3.model.billing.NodeSpec();
        nodeSpec.setType("data");
        nodeSpec.setNodeType("opensearch.c2m8");
        nodeSpec.setNodeCount(3);
        request.setNodeSpecs(java.util.Collections.singletonList(nodeSpec));

        QueryConfigPriceResponse response = client.queryConfigPrice(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/orders/prices", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"payment\":\"Postpaid\""));
        Assert.assertTrue(body.contains("\"nodeType\":\"opensearch.c2m8\""));
        Assert.assertEquals(new java.math.BigDecimal("0.022076928"), response.getPrice());
        Assert.assertEquals(1, response.getNodeTypePrices().size());
        Assert.assertEquals("data", response.getNodeTypePrices().get(0).getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryConfigPriceRejectsEmptyPayment() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        QueryConfigPriceRequest request = new QueryConfigPriceRequest();
        request.setNodeSpecs(java.util.Collections.emptyList());

        client.queryConfigPrice(request);
    }

    @Test
    public void testQueryClusterMarginPriceBuildsExpectedRequestAndParsesResponse() throws Exception {
        String responseJson = "{"
                + "\"currentComponentPrices\":{\"Postpaid\":{\"price\":0.022076928,"
                + "\"catalogPrice\":0.044153856,\"discountRate\":0.5}},"
                + "\"updateComponentPrices\":{\"Postpaid\":{\"price\":0.037800048,"
                + "\"catalogPrice\":0.075600096,\"discountRate\":0.5}},"
                + "\"marginComponentPrices\":{\"Postpaid\":{\"price\":0.01572312,"
                + "\"catalogPrice\":0.03144624,\"discountRate\":0.5}},"
                + "\"expirationTime\":null"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        QueryClusterMarginPriceRequest request = new QueryClusterMarginPriceRequest();
        request.setClusterId("search-xxxx");
        request.setLogicalZones(java.util.Collections.singletonList("cn-bj-a"));
        request.setSubnetId("sbn-gcv4r388upuq");
        request.setEnableDeploySet(Boolean.FALSE);
        com.baidubce.services.bes.v3.model.billing.NodeSpec nodeSpec =
                new com.baidubce.services.bes.v3.model.billing.NodeSpec();
        nodeSpec.setType("data");
        nodeSpec.setNodeType("opensearch.c4m16");
        nodeSpec.setNodeCount(3);
        request.setNodeSpecs(java.util.Collections.singletonList(nodeSpec));
        request.setMode("BLUE_GREEN");

        QueryClusterMarginPriceResponse response = client.queryClusterMarginPrice(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/margin-prices", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"mode\":\"BLUE_GREEN\""));
        Assert.assertEquals(new java.math.BigDecimal("0.022076928"),
                response.getCurrentComponentPrices().get("Postpaid").getPrice());
        Assert.assertEquals(new java.math.BigDecimal("0.01572312"),
                response.getMarginComponentPrices().get("Postpaid").getPrice());
        Assert.assertNull(response.getExpirationTime());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryClusterMarginPriceRejectsEmptyMode() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        QueryClusterMarginPriceRequest request = new QueryClusterMarginPriceRequest();
        request.setClusterId("search-xxxx");
        com.baidubce.services.bes.v3.model.billing.NodeSpec nodeSpec =
                new com.baidubce.services.bes.v3.model.billing.NodeSpec();
        nodeSpec.setType("data");
        request.setNodeSpecs(java.util.Collections.singletonList(nodeSpec));

        client.queryClusterMarginPrice(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryClusterMarginPriceRejectsEmptyNodeSpecs() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        QueryClusterMarginPriceRequest request = new QueryClusterMarginPriceRequest();
        request.setClusterId("search-xxxx");
        request.setMode("BLUE_GREEN");

        client.queryClusterMarginPrice(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQueryClusterMarginPriceRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        QueryClusterMarginPriceRequest request = new QueryClusterMarginPriceRequest();
        com.baidubce.services.bes.v3.model.billing.NodeSpec nodeSpec =
                new com.baidubce.services.bes.v3.model.billing.NodeSpec();
        nodeSpec.setType("data");
        request.setNodeSpecs(java.util.Collections.singletonList(nodeSpec));
        request.setMode("BLUE_GREEN");

        client.queryClusterMarginPrice(request);
    }

    @Test
    public void testCreateManualSnapshotBuildsExpectedRequestAndParsesResponse() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"configId\":31}");
        CreateManualSnapshotRequest request = new CreateManualSnapshotRequest();
        request.setClusterId("search-xxxx");
        request.setName("openapi_test_manual_snapshot");
        request.setAllIndex(Boolean.TRUE);

        ConfigIdResponse response = client.createManualSnapshot(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/manual-snapshot-configs",
                client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"name\":\"openapi_test_manual_snapshot\""));
        Assert.assertEquals(Long.valueOf(31), response.getConfigId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateManualSnapshotRejectsEmptyName() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateManualSnapshotRequest request = new CreateManualSnapshotRequest();
        request.setClusterId("search-xxxx");

        client.createManualSnapshot(request);
    }

    @Test
    public void testListSnapshotsBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"snapshots\":[{\"historyId\":\"27\",\"configId\":\"31\",\"type\":\"MANUAL\","
                + "\"snapshotStatus\":\"SUCCESS\",\"totalSize\":\"2.1MB\",\"increSize\":\"110.32KB\","
                + "\"snapshotDetail\":{\"snapshotName\":\"openapi_test_manual_snapshot\","
                + "\"startTimestamp\":\"1784549607961\",\"total\":\"6\",\"successful\":\"6\",\"failed\":\"0\"}}],"
                + "\"pageNo\":1,\"pageSize\":10,\"totalCount\":8"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        ListSnapshotsRequest request = new ListSnapshotsRequest();
        request.setClusterId("search-xxxx");
        request.setPageNo(1);
        request.setPageSize(10);

        ListSnapshotsResponse response = client.listSnapshots(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/snapshots", client.lastRequest.getUri().getPath());
        Assert.assertEquals("1", client.lastRequest.getParameters().get("pageNo"));
        Assert.assertEquals(Integer.valueOf(8), response.getTotalCount());
        Assert.assertEquals(1, response.getSnapshots().size());
        Assert.assertEquals("SUCCESS", response.getSnapshots().get(0).getSnapshotStatus());
        Assert.assertEquals("6", response.getSnapshots().get(0).getSnapshotDetail().getTotal());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListSnapshotsRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListSnapshotsRequest request = new ListSnapshotsRequest();

        client.listSnapshots(request);
    }

    @Test
    public void testGetSnapshotDetailBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"historyId\":\"27\",\"configId\":\"31\",\"type\":\"MANUAL\","
                + "\"snapshotName\":\"openapi_test_manual_snapshot\",\"snapshotStatus\":\"SUCCESS\","
                + "\"totalSize\":\"2.1MB\",\"increSize\":\"110.32KB\",\"expireAfter\":\"7d\","
                + "\"snapshotShardsTotal\":\"6\",\"snapshotShardsSuccessful\":\"6\",\"snapshotShardsFailed\":\"0\""
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        GetSnapshotDetailRequest request = new GetSnapshotDetailRequest();
        request.setClusterId("search-xxxx");
        request.setSnapshotId(27L);

        SnapshotDetailResponse response = client.getSnapshotDetail(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/snapshots/27", client.lastRequest.getUri().getPath());
        Assert.assertEquals("openapi_test_manual_snapshot", response.getSnapshotName());
        Assert.assertEquals("6", response.getSnapshotShardsTotal());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSnapshotDetailRejectsNullSnapshotId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetSnapshotDetailRequest request = new GetSnapshotDetailRequest();
        request.setClusterId("search-xxxx");

        client.getSnapshotDetail(request);
    }

    @Test
    public void testDeleteSnapshotBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"snapshotId\":27}");
        DeleteSnapshotRequest request = new DeleteSnapshotRequest();
        request.setClusterId("search-xxxx");
        request.setSnapshotId(27L);

        SnapshotIdResponse response = client.deleteSnapshot(request);

        Assert.assertEquals(HttpMethodName.DELETE, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/snapshots/27", client.lastRequest.getUri().getPath());
        Assert.assertEquals(Long.valueOf(27), response.getSnapshotId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteSnapshotRejectsNullSnapshotId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        DeleteSnapshotRequest request = new DeleteSnapshotRequest();
        request.setClusterId("search-xxxx");

        client.deleteSnapshot(request);
    }

    @Test
    public void testGetSnapshotRuleBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"snapshotId\":\"26\",\"configId\":\"29\",\"snapshotName\":\"openapi_test_manual_snapshot\","
                + "\"snapshotStatus\":\"SUCCESS\",\"expireAfter\":\"7d\",\"expireAfterText\":\"7天\","
                + "\"indexDataReady\":true,\"pageNo\":1,\"pageSize\":10,\"totalCount\":6,"
                + "\"indices\":[{\"indexName\":\"openapi-test-2\",\"totalSize\":\"3.41KB\",\"increSize\":\"0B\"}]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        GetSnapshotRuleRequest request = new GetSnapshotRuleRequest();
        request.setClusterId("search-xxxx");
        request.setSnapshotId(26L);

        SnapshotRuleResponse response = client.getSnapshotRule(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/snapshots/26/rule",
                client.lastRequest.getUri().getPath());
        Assert.assertEquals(Boolean.TRUE, response.getIndexDataReady());
        Assert.assertEquals(1, response.getIndices().size());
        Assert.assertEquals("openapi-test-2", response.getIndices().get(0).getIndexName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSnapshotRuleRejectsNullSnapshotId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetSnapshotRuleRequest request = new GetSnapshotRuleRequest();
        request.setClusterId("search-xxxx");

        client.getSnapshotRule(request);
    }

    @Test
    public void testListSnapshotIndicesByPatternBuildsExpectedRequestAndParsesResponse() throws Exception {
        String responseJson = "{"
                + "\"indices\":[{\"index\":\"openapi-test-2\",\"indexSize\":\"9.9kb\",\"indexPriSize\":\"3.4kb\"}]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        ListSnapshotIndicesByPatternRequest request = new ListSnapshotIndicesByPatternRequest();
        request.setClusterId("search-xxxx");
        request.setPattern("openapi-test-*");

        ListSnapshotIndicesByPatternResponse response = client.listSnapshotIndicesByPattern(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/indices/_list_by_pattern",
                client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"clusterId\":\"search-xxxx\""));
        Assert.assertTrue(body.contains("\"pattern\":\"openapi-test-*\""));
        Assert.assertEquals(1, response.getIndices().size());
        Assert.assertEquals("openapi-test-2", response.getIndices().get(0).getIndex());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListSnapshotIndicesByPatternRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListSnapshotIndicesByPatternRequest request = new ListSnapshotIndicesByPatternRequest();

        client.listSnapshotIndicesByPattern(request);
    }

    @Test
    public void testCreateSnapshotRestoreBuildsExpectedRequestAndParsesResponse() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"restoreId\":4}");
        CreateSnapshotRestoreRequest request = new CreateSnapshotRestoreRequest();
        request.setClusterId("search-xxxx");
        request.setSnapshotId(27L);
        request.setDestClusterId("search-xxxx");
        request.setAllIndex(Boolean.FALSE);
        request.setRestoreIndexPattern("openapi-test-*");

        RestoreIdResponse response = client.createSnapshotRestore(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/snapshots/27/restores",
                client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"destClusterId\":\"search-xxxx\""));
        Assert.assertEquals(Long.valueOf(4), response.getRestoreId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateSnapshotRestoreRejectsEmptyDestClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateSnapshotRestoreRequest request = new CreateSnapshotRestoreRequest();
        request.setClusterId("search-xxxx");
        request.setSnapshotId(27L);

        client.createSnapshotRestore(request);
    }

    @Test
    public void testListRestoresBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"restores\":[{\"historyId\":\"27\",\"restoreId\":\"4\",\"destClusterId\":\"search-xxxx\","
                + "\"destClusterName\":\"testOpenToPost1\",\"destEngineVersion\":\"2.19.4\","
                + "\"restoreStatus\":\"IN_PROGRESS\",\"restoreDetail\":{\"startTimestamp\":\"1784549683257\","
                + "\"restoreIndexPattern\":\"openapi-test-*\"}}],"
                + "\"pageNo\":1,\"pageSize\":10,\"totalCount\":1,\"orderBy\":\"startTimestamp\",\"order\":\"desc\""
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        ListRestoresRequest request = new ListRestoresRequest();
        request.setClusterId("search-xxxx");
        request.setSnapshotId(27L);
        request.setPageNo(1);
        request.setPageSize(10);

        ListRestoresResponse response = client.listRestores(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/snapshots/27/restores",
                client.lastRequest.getUri().getPath());
        Assert.assertEquals(1, response.getRestores().size());
        Assert.assertEquals("IN_PROGRESS", response.getRestores().get(0).getRestoreStatus());
        Assert.assertEquals("openapi-test-*",
                response.getRestores().get(0).getRestoreDetail().getRestoreIndexPattern());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListRestoresRejectsNullSnapshotId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListRestoresRequest request = new ListRestoresRequest();
        request.setClusterId("search-xxxx");

        client.listRestores(request);
    }

    @Test
    public void testListRestoreClustersBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"clusters\":[{\"clusterId\":\"search-xxxx\",\"name\":\"testOpenToPost1\","
                + "\"engine\":\"OPENSEARCH\",\"status\":\"ACTIVE\",\"available\":true}]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        ListRestoreClustersRequest request = new ListRestoreClustersRequest();
        request.setClusterId("search-xxxx");
        request.setSnapshotId(27L);
        request.setIncludeSelf(Boolean.TRUE);

        ListRestoreClustersResponse response = client.listRestoreClusters(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/snapshots/27/restore-clusters",
                client.lastRequest.getUri().getPath());
        Assert.assertEquals("true", client.lastRequest.getParameters().get("includeSelf"));
        Assert.assertEquals(1, response.getClusters().size());
        Assert.assertEquals(Boolean.TRUE, response.getClusters().get(0).getAvailable());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListRestoreClustersRejectsNullSnapshotId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListRestoreClustersRequest request = new ListRestoreClustersRequest();
        request.setClusterId("search-xxxx");

        client.listRestoreClusters(request);
    }

    @Test
    public void testCreateAutoSnapshotConfigBuildsExpectedRequestAndParsesResponse() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"configId\":32}");
        CreateAutoSnapshotConfigRequest request = new CreateAutoSnapshotConfigRequest();
        request.setClusterId("search-xxxx");
        request.setName("openapi_test_auto_snapshot7");
        request.setPeriod("0 2 * * *");
        request.setAllIndex(Boolean.TRUE);
        request.setEnabled(Boolean.TRUE);

        ConfigIdResponse response = client.createAutoSnapshotConfig(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/auto-snapshot-configs",
                client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"period\":\"0 2 * * *\""));
        Assert.assertEquals(Long.valueOf(32), response.getConfigId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateAutoSnapshotConfigRejectsEmptyPeriod() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateAutoSnapshotConfigRequest request = new CreateAutoSnapshotConfigRequest();
        request.setClusterId("search-xxxx");
        request.setName("openapi_test_auto_snapshot7");

        client.createAutoSnapshotConfig(request);
    }

    @Test
    public void testUpdateAutoSnapshotConfigBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"configId\":1}");
        UpdateAutoSnapshotConfigRequest request = new UpdateAutoSnapshotConfigRequest();
        request.setClusterId("search-xxxx");
        request.setConfigId(1L);
        request.setName("openapi_test_auto_snapshot5_updated");
        request.setPeriod("0 2 * * *");
        request.setEnabled(Boolean.TRUE);

        ConfigIdResponse response = client.updateAutoSnapshotConfig(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/auto-snapshot-configs/1",
                client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"name\":\"openapi_test_auto_snapshot5_updated\""));
        Assert.assertEquals(Long.valueOf(1), response.getConfigId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAutoSnapshotConfigRejectsNullConfigId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateAutoSnapshotConfigRequest request = new UpdateAutoSnapshotConfigRequest();
        request.setClusterId("search-xxxx");
        request.setName("openapi_test_auto_snapshot5_updated");
        request.setPeriod("0 2 * * *");

        client.updateAutoSnapshotConfig(request);
    }

    @Test
    public void testDeleteAutoSnapshotConfigBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"configId\":1}");
        DeleteAutoSnapshotConfigRequest request = new DeleteAutoSnapshotConfigRequest();
        request.setClusterId("search-xxxx");
        request.setConfigId(1L);

        ConfigIdResponse response = client.deleteAutoSnapshotConfig(request);

        Assert.assertEquals(HttpMethodName.DELETE, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/auto-snapshot-configs/1",
                client.lastRequest.getUri().getPath());
        Assert.assertEquals(Long.valueOf(1), response.getConfigId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteAutoSnapshotConfigRejectsNullConfigId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        DeleteAutoSnapshotConfigRequest request = new DeleteAutoSnapshotConfigRequest();
        request.setClusterId("search-xxxx");

        client.deleteAutoSnapshotConfig(request);
    }

    @Test
    public void testListAutoSnapshotConfigsBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"configList\":[{\"configId\":\"28\",\"type\":\"AUTO\","
                + "\"name\":\"openapi_test_auto_snapshot5_updated\",\"snapshotType\":\"SNAPSHOT_BOS\","
                + "\"period\":\"0 2 * * *\",\"expireAfter\":\"7d\",\"enabled\":true,\"allIndex\":true}]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        ListAutoSnapshotConfigsRequest request = new ListAutoSnapshotConfigsRequest();
        request.setClusterId("search-xxxx");

        ListAutoSnapshotConfigsResponse response = client.listAutoSnapshotConfigs(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/auto-snapshot-configs",
                client.lastRequest.getUri().getPath());
        Assert.assertEquals(1, response.getConfigList().size());
        Assert.assertEquals("AUTO", response.getConfigList().get(0).getType());
        Assert.assertEquals(Boolean.TRUE, response.getConfigList().get(0).getEnabled());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListAutoSnapshotConfigsRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListAutoSnapshotConfigsRequest request = new ListAutoSnapshotConfigsRequest();

        client.listAutoSnapshotConfigs(request);
    }

    @Test
    public void testBatchUpdateAutoSnapshotConfigStatusBuildsExpectedRequestAndParsesResponse() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"enabled\":true,\"configIdList\":[1,2]}");
        BatchUpdateAutoSnapshotConfigStatusRequest request = new BatchUpdateAutoSnapshotConfigStatusRequest();
        request.setClusterId("search-xxxx");
        request.setEnabled(Boolean.TRUE);
        request.setConfigIdList(Arrays.asList(1L, 2L));

        BatchUpdateAutoSnapshotConfigStatusResponse response =
                client.batchUpdateAutoSnapshotConfigStatus(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.PATCH, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/auto-snapshot-configs",
                client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        Assert.assertTrue(body.contains("\"enabled\":true"));
        Assert.assertTrue(body.contains("\"configIdList\":[1,2]"));
        Assert.assertEquals(Boolean.TRUE, response.getEnabled());
        Assert.assertEquals(Arrays.asList(1L, 2L), response.getConfigIdList());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBatchUpdateAutoSnapshotConfigStatusRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        BatchUpdateAutoSnapshotConfigStatusRequest request = new BatchUpdateAutoSnapshotConfigStatusRequest();
        request.setEnabled(Boolean.TRUE);
        request.setConfigIdList(Arrays.asList(1L, 2L));

        client.batchUpdateAutoSnapshotConfigStatus(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBatchUpdateAutoSnapshotConfigStatusRejectsNullEnabled() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        BatchUpdateAutoSnapshotConfigStatusRequest request = new BatchUpdateAutoSnapshotConfigStatusRequest();
        request.setClusterId("search-xxxx");
        request.setConfigIdList(Arrays.asList(1L, 2L));

        client.batchUpdateAutoSnapshotConfigStatus(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBatchUpdateAutoSnapshotConfigStatusRejectsEmptyConfigIdList() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        BatchUpdateAutoSnapshotConfigStatusRequest request = new BatchUpdateAutoSnapshotConfigStatusRequest();
        request.setClusterId("search-xxxx");
        request.setEnabled(Boolean.TRUE);

        client.batchUpdateAutoSnapshotConfigStatus(request);
    }

    @Test
    public void testGetSnapshotConfigDetailBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"configId\":\"30\",\"type\":\"AUTO\",\"name\":\"openapi_test_auto_snapshot6_updated\","
                + "\"snapshotType\":\"SNAPSHOT_BOS\",\"period\":\"0 2 * * *\",\"expireAfter\":\"7d\","
                + "\"repository\":\"snapshot-repo-search-xxxx\",\"enabled\":true,\"allIndex\":true"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        GetSnapshotConfigDetailRequest request = new GetSnapshotConfigDetailRequest();
        request.setClusterId("search-xxxx");
        request.setConfigId(30L);

        SnapshotConfigDetailResponse response = client.getSnapshotConfigDetail(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/backup/snapshot-configs/30",
                client.lastRequest.getUri().getPath());
        Assert.assertEquals("openapi_test_auto_snapshot6_updated", response.getName());
        Assert.assertEquals("snapshot-repo-search-xxxx", response.getRepository());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetSnapshotConfigDetailRejectsNullConfigId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetSnapshotConfigDetailRequest request = new GetSnapshotConfigDetailRequest();
        request.setClusterId("search-xxxx");

        client.getSnapshotConfigDetail(request);
    }

    @Test
    public void testGetAutoDiagnosisStatusBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"enabled\":true}");
        GetAutoDiagnosisStatusRequest request = new GetAutoDiagnosisStatusRequest();
        request.setClusterId("search-xxxx");

        AutoDiagnosisStatusResponse response = client.getAutoDiagnosisStatus(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/auto", client.lastRequest.getUri().getPath());
        Assert.assertEquals(Boolean.TRUE, response.getEnabled());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAutoDiagnosisStatusRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetAutoDiagnosisStatusRequest request = new GetAutoDiagnosisStatusRequest();

        client.getAutoDiagnosisStatus(request);
    }

    @Test
    public void testUpdateAutoDiagnosisStatusBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"enabled\":true}");
        UpdateAutoDiagnosisStatusRequest request = new UpdateAutoDiagnosisStatusRequest();
        request.setClusterId("search-xxxx");
        request.setEnabled(Boolean.TRUE);

        AutoDiagnosisStatusResponse response = client.updateAutoDiagnosisStatus(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/auto", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"enabled\":true"));
        Assert.assertEquals(Boolean.TRUE, response.getEnabled());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateAutoDiagnosisStatusRejectsNullEnabled() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateAutoDiagnosisStatusRequest request = new UpdateAutoDiagnosisStatusRequest();
        request.setClusterId("search-xxxx");

        client.updateAutoDiagnosisStatus(request);
    }

    @Test
    public void testGetManualDiagnosisConfigBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"indices\":\"*\",\"items\":[]}");
        GetManualDiagnosisConfigRequest request = new GetManualDiagnosisConfigRequest();
        request.setClusterId("search-xxxx");

        ManualDiagnosisConfigResponse response = client.getManualDiagnosisConfig(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/manual", client.lastRequest.getUri().getPath());
        Assert.assertEquals("*", response.getIndices());
        Assert.assertEquals(0, response.getItems().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetManualDiagnosisConfigRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetManualDiagnosisConfigRequest request = new GetManualDiagnosisConfigRequest();

        client.getManualDiagnosisConfig(request);
    }

    @Test
    public void testUpdateManualDiagnosisConfigBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"success\":true}");
        UpdateManualDiagnosisConfigRequest request = new UpdateManualDiagnosisConfigRequest();
        request.setClusterId("search-xxxx");
        request.setIndices("*");
        request.setItems(java.util.Collections.singletonList("cluster_health"));

        UpdateManualDiagnosisConfigResponse response = client.updateManualDiagnosisConfig(request);
        String body = readContent(client.lastRequest.getContent());

        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/manual", client.lastRequest.getUri().getPath());
        Assert.assertTrue(body.contains("\"indices\":\"*\""));
        Assert.assertTrue(body.contains("\"items\":[\"cluster_health\"]"));
        Assert.assertEquals(Boolean.TRUE, response.getSuccess());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateManualDiagnosisConfigRejectsNullItems() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateManualDiagnosisConfigRequest request = new UpdateManualDiagnosisConfigRequest();
        request.setClusterId("search-xxxx");

        client.updateManualDiagnosisConfig(request);
    }

    @Test
    public void testCreateManualDiagnosisBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"success\":true}");
        CreateManualDiagnosisRequest request = new CreateManualDiagnosisRequest();
        request.setClusterId("search-xxxx");

        CreateManualDiagnosisResponse response = client.createManualDiagnosis(request);

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/manual", client.lastRequest.getUri().getPath());
        Assert.assertEquals(Boolean.TRUE, response.getSuccess());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateManualDiagnosisRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateManualDiagnosisRequest request = new CreateManualDiagnosisRequest();

        client.createManualDiagnosis(request);
    }

    @Test
    public void testGetManualDiagnosisCountBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"count\":1}");
        GetManualDiagnosisCountRequest request = new GetManualDiagnosisCountRequest();
        request.setClusterId("search-xxxx");

        ManualDiagnosisCountResponse response = client.getManualDiagnosisCount(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/manual/count",
                client.lastRequest.getUri().getPath());
        Assert.assertEquals(Integer.valueOf(1), response.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetManualDiagnosisCountRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetManualDiagnosisCountRequest request = new GetManualDiagnosisCountRequest();

        client.getManualDiagnosisCount(request);
    }

    @Test
    public void testGetDiagnosisAuthorizeStatusBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"authorized\":true}");
        GetDiagnosisAuthorizeStatusRequest request = new GetDiagnosisAuthorizeStatusRequest();
        request.setClusterId("search-xxxx");

        DiagnosisAuthorizeStatusResponse response = client.getDiagnosisAuthorizeStatus(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/authorize", client.lastRequest.getUri().getPath());
        Assert.assertEquals(Boolean.TRUE, response.getAuthorized());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDiagnosisAuthorizeStatusRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetDiagnosisAuthorizeStatusRequest request = new GetDiagnosisAuthorizeStatusRequest();

        client.getDiagnosisAuthorizeStatus(request);
    }

    @Test
    public void testAuthorizeDiagnosisBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"authorized\":true}");
        AuthorizeDiagnosisRequest request = new AuthorizeDiagnosisRequest();
        request.setClusterId("search-xxxx");

        AuthorizeDiagnosisResponse response = client.authorizeDiagnosis(request);

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/authorize", client.lastRequest.getUri().getPath());
        Assert.assertEquals(Boolean.TRUE, response.getAuthorized());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAuthorizeDiagnosisRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        AuthorizeDiagnosisRequest request = new AuthorizeDiagnosisRequest();

        client.authorizeDiagnosis(request);
    }

    @Test
    public void testGetDiagnosisBusyStatusBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"busy\":false}");
        GetDiagnosisBusyStatusRequest request = new GetDiagnosisBusyStatusRequest();
        request.setClusterId("search-xxxx");

        DiagnosisBusyStatusResponse response = client.getDiagnosisBusyStatus(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/busy", client.lastRequest.getUri().getPath());
        Assert.assertEquals(Boolean.FALSE, response.getBusy());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDiagnosisBusyStatusRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetDiagnosisBusyStatusRequest request = new GetDiagnosisBusyStatusRequest();

        client.getDiagnosisBusyStatus(request);
    }

    @Test
    public void testListDiagnosisItemsBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"items\":[{\"id\":\"ClusterHealth\",\"type\":\"cluster\"},"
                + "{\"id\":\"NodeShardNum\",\"type\":\"node\"}]"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        ListDiagnosisItemsRequest request = new ListDiagnosisItemsRequest();
        request.setClusterId("search-xxxx");

        ListDiagnosisItemsResponse response = client.listDiagnosisItems(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/items", client.lastRequest.getUri().getPath());
        Assert.assertEquals(2, response.getItems().size());
        Assert.assertEquals("ClusterHealth", response.getItems().get(0).getId());
        Assert.assertEquals("node", response.getItems().get(1).getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListDiagnosisItemsRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListDiagnosisItemsRequest request = new ListDiagnosisItemsRequest();

        client.listDiagnosisItems(request);
    }

    @Test
    public void testGetWeeklyDiagnosisOverviewBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"count\":0,\"riskItems\":[]}");
        GetWeeklyDiagnosisOverviewRequest request = new GetWeeklyDiagnosisOverviewRequest();
        request.setClusterId("search-xxxx");

        WeeklyDiagnosisOverviewResponse response = client.getWeeklyDiagnosisOverview(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/overview/weekly",
                client.lastRequest.getUri().getPath());
        Assert.assertEquals(Integer.valueOf(0), response.getCount());
        Assert.assertEquals(0, response.getRiskItems().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetWeeklyDiagnosisOverviewRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetWeeklyDiagnosisOverviewRequest request = new GetWeeklyDiagnosisOverviewRequest();

        client.getWeeklyDiagnosisOverview(request);
    }

    @Test
    public void testGetLatestDiagnosisOverviewBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"reportId\":null,\"status\":null,\"execTime\":null,\"countSafe\":null,"
                + "\"countLowRisk\":null,\"countHighRisk\":null,\"countError\":null"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        GetLatestDiagnosisOverviewRequest request = new GetLatestDiagnosisOverviewRequest();
        request.setClusterId("search-xxxx");

        LatestDiagnosisOverviewResponse response = client.getLatestDiagnosisOverview(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/overview/latest",
                client.lastRequest.getUri().getPath());
        Assert.assertNull(response.getReportId());
        Assert.assertNull(response.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetLatestDiagnosisOverviewRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetLatestDiagnosisOverviewRequest request = new GetLatestDiagnosisOverviewRequest();

        client.getLatestDiagnosisOverview(request);
    }

    @Test
    public void testListDiagnosisReportsBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"reports\":[{\"reportId\":\"2081801793085411330\",\"type\":\"auto\","
                        + "\"execTime\":\"2026-07-28 02:00:00\",\"lowRiskCount\":1,\"highRiskCount\":1,"
                        + "\"errorCount\":0}],\"pageNo\":1,\"pageSize\":10,\"totalCount\":1}");
        ListDiagnosisReportsRequest request = new ListDiagnosisReportsRequest();
        request.setClusterId("search-xxxx");
        request.setPageNo(1);
        request.setPageSize(10);

        ListDiagnosisReportsResponse response = client.listDiagnosisReports(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/reports", client.lastRequest.getUri().getPath());
        Assert.assertEquals(Integer.valueOf(1), response.getTotalCount());
        Assert.assertEquals(1, response.getReports().size());
        Assert.assertEquals("2081801793085411330", response.getReports().get(0).getReportId());
        Assert.assertEquals("auto", response.getReports().get(0).getType());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListDiagnosisReportsRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListDiagnosisReportsRequest request = new ListDiagnosisReportsRequest();

        client.listDiagnosisReports(request);
    }

    @Test
    public void testGetDiagnosisReportDetailBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"state\":\"success\",\"type\":\"auto\",\"indices\":\"*\",\"totalCount\":18,"
                        + "\"execTime\":\"2026-07-28 02:00:00\",\"groups\":[{\"grade\":\"HIGH_RISK\","
                        + "\"itemCount\":1,\"results\":[{\"id\":\"ClusterPublicAccess\",\"type\":\"cluster\","
                        + "\"conclusion\":\"当前可视化节点已开启公网访问，未配置IP白名单。\","
                        + "\"hasLowRiskAdvice\":false,\"notExistIndex\":false}]}]}");
        GetDiagnosisReportDetailRequest request = new GetDiagnosisReportDetailRequest();
        request.setClusterId("search-xxxx");
        request.setReportId("report-xxxx");

        DiagnosisReportDetailResponse response = client.getDiagnosisReportDetail(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/diagnosis/reports/report-xxxx",
                client.lastRequest.getUri().getPath());
        Assert.assertEquals("success", response.getState());
        Assert.assertEquals(Integer.valueOf(18), response.getTotalCount());
        Assert.assertEquals(1, response.getGroups().size());
        Assert.assertEquals("HIGH_RISK", response.getGroups().get(0).getGrade());
        Assert.assertEquals("ClusterPublicAccess", response.getGroups().get(0).getResults().get(0).getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetDiagnosisReportDetailRejectsEmptyReportId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        GetDiagnosisReportDetailRequest request = new GetDiagnosisReportDetailRequest();
        request.setClusterId("search-xxxx");

        client.getDiagnosisReportDetail(request);
    }

    @Test
    public void testCreateScheduleBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateScheduleRequest request = new CreateScheduleRequest();
        request.setClusterId("search-xxxx");
        request.setScheduleName("my-delete-task");
        request.setScheduleType("DELETE");
        request.setCronExpr("0 30 1 * * ?");
        request.setParams(Collections.singletonMap("indexPattern", "logs-*"));

        client.createSchedule(request);

        Assert.assertEquals(HttpMethodName.POST, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/schedules", client.lastRequest.getUri().getPath());
        Assert.assertEquals("application/json;charset=UTF-8", client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));

        String body = readContent(client.lastRequest.getContent());
        Assert.assertTrue(body.contains("\"scheduleName\":\"my-delete-task\""));
        Assert.assertTrue(body.contains("\"scheduleType\":\"DELETE\""));
        Assert.assertTrue(body.contains("\"cronExpr\":\"0 30 1 * * ?\""));
        Assert.assertTrue(body.contains("\"indexPattern\":\"logs-*\""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateScheduleRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateScheduleRequest request = new CreateScheduleRequest();

        client.createSchedule(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateScheduleRejectsEmptyScheduleName() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateScheduleRequest request = new CreateScheduleRequest();
        request.setClusterId("search-xxxx");
        request.setScheduleType("DELETE");
        request.setCronExpr("0 30 1 * * ?");
        request.setParams(Collections.singletonMap("indexPattern", "logs-*"));

        client.createSchedule(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateScheduleRejectsEmptyScheduleType() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateScheduleRequest request = new CreateScheduleRequest();
        request.setClusterId("search-xxxx");
        request.setScheduleName("my-delete-task");
        request.setCronExpr("0 30 1 * * ?");
        request.setParams(Collections.singletonMap("indexPattern", "logs-*"));

        client.createSchedule(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateScheduleRejectsEmptyCronExpr() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateScheduleRequest request = new CreateScheduleRequest();
        request.setClusterId("search-xxxx");
        request.setScheduleName("my-delete-task");
        request.setScheduleType("DELETE");
        request.setParams(Collections.singletonMap("indexPattern", "logs-*"));

        client.createSchedule(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateScheduleRejectsNullParams() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        CreateScheduleRequest request = new CreateScheduleRequest();
        request.setClusterId("search-xxxx");
        request.setScheduleName("my-delete-task");
        request.setScheduleType("DELETE");
        request.setCronExpr("0 30 1 * * ?");

        client.createSchedule(request);
    }

    @Test
    public void testListSchedulesBuildsExpectedRequestAndParsesResponse() {
        String responseJson = "{"
                + "\"schedules\":[{\"scheduleId\":\"9e1f6b2a-1c34-4e7a-9d3a-2f6a7b0c1d2e\","
                + "\"scheduleName\":\"my-delete-task\",\"scheduleType\":\"DELETE\",\"cronExpr\":\"0 30 1 * * ?\","
                + "\"enabled\":true,\"createTime\":\"2026-07-28 02:00:00\",\"lastTriggerTime\":\"2026-07-28 02:00:00\","
                + "\"lastStatus\":\"SUCCESS\",\"lastErrorMessage\":null,\"executedCount\":12,\"failedCount\":0,"
                + "\"params\":{\"indexPattern\":\"logs-*\"}}],"
                + "\"pageNo\":1,\"pageSize\":20,\"totalCount\":1"
                + "}";
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson(responseJson);
        ListSchedulesRequest request = new ListSchedulesRequest();
        request.setClusterId("search-xxxx");
        request.setPageNo(1);
        request.setPageSize(20);

        ListSchedulesResponse response = client.listSchedules(request);

        Assert.assertEquals(HttpMethodName.GET, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/schedules", client.lastRequest.getUri().getPath());
        Assert.assertEquals("1", client.lastRequest.getParameters().get("pageNo"));
        Assert.assertEquals("20", client.lastRequest.getParameters().get("pageSize"));
        Assert.assertEquals(Integer.valueOf(1), response.getTotalCount());
        Assert.assertEquals(1, response.getSchedules().size());
        Assert.assertEquals("my-delete-task", response.getSchedules().get(0).getScheduleName());
        Assert.assertEquals("SUCCESS", response.getSchedules().get(0).getLastStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListSchedulesRejectsEmptyClusterId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        ListSchedulesRequest request = new ListSchedulesRequest();

        client.listSchedules(request);
    }

    @Test
    public void testUpdateScheduleBuildsExpectedRequest() throws Exception {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateScheduleRequest request = new UpdateScheduleRequest();
        request.setClusterId("search-xxxx");
        request.setScheduleId("9e1f6b2a-1c34-4e7a-9d3a-2f6a7b0c1d2e");
        request.setCronExpr("0 30 1 * * ?");
        request.setParams(Collections.singletonMap("indexPattern", "logs-*"));

        client.updateSchedule(request);

        Assert.assertEquals(HttpMethodName.PUT, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/schedules/9e1f6b2a-1c34-4e7a-9d3a-2f6a7b0c1d2e",
                client.lastRequest.getUri().getPath());

        String body = readContent(client.lastRequest.getContent());
        Assert.assertTrue(body.contains("\"cronExpr\":\"0 30 1 * * ?\""));
        Assert.assertTrue(body.contains("\"indexPattern\":\"logs-*\""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateScheduleRejectsEmptyScheduleId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateScheduleRequest request = new UpdateScheduleRequest();
        request.setClusterId("search-xxxx");

        client.updateSchedule(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateScheduleRejectsEmptyCronExpr() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateScheduleRequest request = new UpdateScheduleRequest();
        request.setClusterId("search-xxxx");
        request.setScheduleId("9e1f6b2a-1c34-4e7a-9d3a-2f6a7b0c1d2e");
        request.setParams(Collections.singletonMap("indexPattern", "logs-*"));

        client.updateSchedule(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateScheduleRejectsNullParams() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        UpdateScheduleRequest request = new UpdateScheduleRequest();
        request.setClusterId("search-xxxx");
        request.setScheduleId("9e1f6b2a-1c34-4e7a-9d3a-2f6a7b0c1d2e");
        request.setCronExpr("0 30 1 * * ?");

        client.updateSchedule(request);
    }

    @Test
    public void testDeleteScheduleBuildsExpectedRequestAndParsesResponse() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj")
                .withResponseJson("{\"success\":true}");
        DeleteScheduleRequest request = new DeleteScheduleRequest();
        request.setClusterId("search-xxxx");
        request.setScheduleId("9e1f6b2a-1c34-4e7a-9d3a-2f6a7b0c1d2e");

        DeleteScheduleResponse response = client.deleteSchedule(request);

        Assert.assertEquals(HttpMethodName.DELETE, client.lastRequest.getHttpMethod());
        Assert.assertEquals("/v3/clusters/search-xxxx/schedules/9e1f6b2a-1c34-4e7a-9d3a-2f6a7b0c1d2e",
                client.lastRequest.getUri().getPath());
        Assert.assertTrue(response.getSuccess());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteScheduleRejectsEmptyScheduleId() {
        CapturingBesClientV3 client = new CapturingBesClientV3(new BceClientConfiguration(), "bj");
        DeleteScheduleRequest request = new DeleteScheduleRequest();
        request.setClusterId("search-xxxx");

        client.deleteSchedule(request);
    }

    private static void writeFile(File file, String content) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
        } finally {
            fileOutputStream.close();
        }
    }

    private static String readContent(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int read;
        while ((read = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, read);
        }
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }

    private static class TestCloseableHttpResponse extends BasicHttpResponse implements CloseableHttpResponse {
        TestCloseableHttpResponse() {
            super(HttpVersion.HTTP_1_1, 200, "OK");
        }

        @Override
        public void close() {
        }
    }

    private static class CapturingBesClientV3 extends BesClientV3 {
        private InternalRequest lastRequest;
        private String responseJson;
        private boolean captureContentBeforeClose;
        private String capturedContent;

        CapturingBesClientV3(BceClientConfiguration configuration, String region) {
            super(configuration, region);
        }

        CapturingBesClientV3 withResponseJson(String responseJson) {
            this.responseJson = responseJson;
            return this;
        }

        CapturingBesClientV3 captureContentBeforeClose() {
            this.captureContentBeforeClose = true;
            return this;
        }

        @Override
        protected <T extends AbstractBceResponse> T invokeHttpClient(InternalRequest request, Class<T> responseClass) {
            this.lastRequest = request;
            try {
                if (captureContentBeforeClose && request.getContent() != null) {
                    this.capturedContent = readContent(request.getContent());
                }
                if (responseJson != null) {
                    return JsonUtils.loadFrom(
                            new java.io.ByteArrayInputStream(responseJson.getBytes(StandardCharsets.UTF_8)),
                            responseClass);
                }
                return responseClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

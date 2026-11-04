package com.baidubce.services.bos;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bos.model.GetBucketObjectSetConfigurationResponse;
import com.baidubce.services.bos.model.GetObjectSetQuotaResponse;
import com.baidubce.services.bos.model.GetObjectSetResponse;
import com.baidubce.services.bos.model.GetObjectSetStorageResponse;
import com.baidubce.services.bos.model.ListObjectSetRequest;
import com.baidubce.services.bos.model.ListObjectSetResponse;
import com.baidubce.util.JsonUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class BosObjectSetTest {
    private CapturingBosClient client;

    private static class CapturingBosClient extends BosClient {
        private InternalRequest lastRequest;
        private final List<Class<?>> responseClasses = new ArrayList<Class<?>>();

        CapturingBosClient(BosClientConfiguration configuration) {
            super(configuration);
        }

        @Override
        protected <T extends AbstractBceResponse> T invokeHttpClient(InternalRequest request, Class<T> responseClass) {
            this.lastRequest = request;
            this.responseClasses.add(responseClass);
            try {
                return responseClass.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Before
    public void setUp() {
        BosClientConfiguration configuration = new BosClientConfiguration()
                .withEndpoint("bj.bcebos.com")
                .withCredentials(new DefaultBceCredentials("ak", "sk"));
        client = new CapturingBosClient(configuration);
    }

    @After
    public void tearDown() {
        client.shutdown();
    }

    @Test
    public void shouldBuildBucketObjectSetConfigurationRequests() throws IOException {
        client.putBucketObjectSetConfiguration("test-bucket", 2, 1024L, 1000L);
        assertRequest(HttpMethodName.PUT, "objectsetconfiguration");
        assertJsonBody("{\"pathLevel\":2,\"storageQuota\":1024,\"objectCountQuota\":1000}");

        GetBucketObjectSetConfigurationResponse response =
                client.getBucketObjectSetConfiguration("test-bucket");
        assertNotNull(response);
        assertRequest(HttpMethodName.GET, "objectsetconfiguration");
        assertSame(GetBucketObjectSetConfigurationResponse.class,
                client.responseClasses.get(client.responseClasses.size() - 1));
    }

    @Test
    public void shouldBuildObjectSetCrudRequests() {
        client.putObjectSet("test-bucket", "a/b/");
        assertObjectSetRequest(HttpMethodName.PUT, "objectset", "a/b/");
        assertEquals("0", client.lastRequest.getHeaders().get(Headers.CONTENT_LENGTH));

        GetObjectSetResponse getResponse = client.getObjectSet("test-bucket", "a/b/");
        assertNotNull(getResponse);
        assertObjectSetRequest(HttpMethodName.GET, "objectset", "a/b/");

        client.deleteObjectSet("test-bucket", "a/b/");
        assertObjectSetRequest(HttpMethodName.DELETE, "objectset", "a/b/");
    }

    @Test
    public void shouldBuildGetStorageRequest() {
        GetObjectSetStorageResponse response = client.getObjectSetStorage("test-bucket", "a/b/");
        assertNotNull(response);
        assertObjectSetRequest(HttpMethodName.GET, "objectsetstorage", "a/b/");
        assertSame(GetObjectSetStorageResponse.class,
                client.responseClasses.get(client.responseClasses.size() - 1));
    }

    @Test
    public void shouldBuildListObjectSetRequest() {
        ListObjectSetRequest request = new ListObjectSetRequest("test-bucket")
                .withPrefix("a/")
                .withMarker("a/b/")
                .withMaxKeys(100);
        ListObjectSetResponse response = client.listObjectSet(request);

        assertNotNull(response);
        assertRequest(HttpMethodName.GET, "objectsets");
        assertEquals("a/", client.lastRequest.getParameters().get("prefix"));
        assertEquals("a/b/", client.lastRequest.getParameters().get("marker"));
        assertEquals("100", client.lastRequest.getParameters().get("max-keys"));
        assertSame(ListObjectSetResponse.class,
                client.responseClasses.get(client.responseClasses.size() - 1));

        client.listObjectSet(new ListObjectSetRequest("test-bucket").withMaxKeys(1001));
        assertEquals("1001", client.lastRequest.getParameters().get("max-keys"));
    }

    @Test
    public void shouldBuildObjectSetQuotaRequests() throws IOException {
        client.putObjectSetQuota("test-bucket", "a/b/", Long.MAX_VALUE, 1000L);
        assertObjectSetRequest(HttpMethodName.PUT, "objectsetquota", "a/b/");
        assertJsonBody("{\"storageQuota\":9223372036854775807,\"objectCountQuota\":1000}");

        GetObjectSetQuotaResponse response = client.getObjectSetQuota("test-bucket", "a/b/");
        assertNotNull(response);
        assertObjectSetRequest(HttpMethodName.GET, "objectsetquota", "a/b/");
        assertSame(GetObjectSetQuotaResponse.class,
                client.responseClasses.get(client.responseClasses.size() - 1));
    }

    @Test
    public void shouldDeserializeDocumentedResponses() throws Exception {
        GetObjectSetStorageResponse storage = JsonUtils.getObjectMapper().readValue(
                "{\"totalStorageStat\":{\"Storage\":10050,\"ObjectCount\":807},"
                        + "\"standardStorageStat\":{\"storage\":50,\"objectCount\":7}}",
                GetObjectSetStorageResponse.class);
        assertEquals(Long.valueOf(10050), storage.getTotalStorageStat().getStorage());
        assertEquals(Long.valueOf(807), storage.getTotalStorageStat().getObjectCount());
        assertEquals(Long.valueOf(50), storage.getStandardStorageStat().getStorage());

        ListObjectSetResponse list = JsonUtils.getObjectMapper().readValue(
                "{\"objectSets\":[{\"ObjectSetName\":\"a/b/\"}],"
                        + "\"isTruncated\":false,\"nextMarker\":\"\"}",
                ListObjectSetResponse.class);
        assertFalse(list.isTruncated());
        assertEquals("a/b/", list.getObjectSets().get(0).getObjectSetName());

        GetObjectSetResponse objectSet = JsonUtils.getObjectMapper().readValue(
                "{\"ObjectSetName\":\"a/b/\"}", GetObjectSetResponse.class);
        assertEquals("a/b/", objectSet.getObjectSetName());

        GetBucketObjectSetConfigurationResponse configuration = JsonUtils.getObjectMapper().readValue(
                "{\"pathLevel\":2,\"storageQuota\":1024,\"objectCountQuota\":1000}",
                GetBucketObjectSetConfigurationResponse.class);
        assertEquals(Integer.valueOf(2), configuration.getPathLevel());
        assertEquals(Long.valueOf(1024), configuration.getStorageQuota());

        GetObjectSetQuotaResponse quota = JsonUtils.getObjectMapper().readValue(
                "{\"storageQuota\":9223372036854775807,\"objectCountQuota\":1000}",
                GetObjectSetQuotaResponse.class);
        assertEquals(Long.valueOf(Long.MAX_VALUE), quota.getStorageQuota());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInvalidPathLevel() {
        client.putBucketObjectSetConfiguration("test-bucket", 6, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNegativeQuota() {
        client.putObjectSetQuota("test-bucket", "a/b/", -1L, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectInvalidObjectSetNameLength() {
        client.getObjectSet("test-bucket", "a");
    }

    private void assertRequest(HttpMethodName method, String operation) {
        assertEquals(method, client.lastRequest.getHttpMethod());
        assertTrue(client.lastRequest.getParameters().containsKey(operation));
        assertNull(client.lastRequest.getParameters().get(operation));
    }

    private void assertObjectSetRequest(HttpMethodName method, String operation, String objectSetName) {
        assertRequest(method, operation);
        assertEquals(objectSetName, client.lastRequest.getParameters().get("ObjectSetName"));
    }

    private void assertJsonBody(String expected) throws IOException {
        assertEquals(com.baidubce.services.bos.model.Constants.APPLICATION_JSON,
                client.lastRequest.getHeaders().get(Headers.CONTENT_TYPE));
        byte[] body = readAll(client.lastRequest.getContent());
        assertEquals(String.valueOf(body.length), client.lastRequest.getHeaders().get(Headers.CONTENT_LENGTH));
        assertEquals(expected, new String(body, "UTF-8"));
    }

    private static byte[] readAll(RestartableInputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[256];
        int count;
        while ((count = input.read(buffer)) != -1) {
            output.write(buffer, 0, count);
        }
        return output.toByteArray();
    }
}

package com.baidubce.services.mongodb;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.mongodb.model.MongodbBackupListRequest;
import com.baidubce.services.mongodb.model.MongodbBackupPolicyRequest;
import com.baidubce.services.mongodb.model.MongodbBackupRequest;
import com.baidubce.services.mongodb.model.MongodbCreateNodeRequest;
import com.baidubce.services.mongodb.model.MongodbCreateOrderRequest;
import com.baidubce.services.mongodb.model.MongodbCreateReplicaRequest;
import com.baidubce.services.mongodb.model.MongodbCreateShardingRequest;
import com.baidubce.services.mongodb.model.MongodbCreateSecurityIpGroupRequest;
import com.baidubce.services.mongodb.model.MongodbInstanceListRequest;
import com.baidubce.services.mongodb.model.MongodbInstanceRequest;
import com.baidubce.services.mongodb.model.MongodbInstanceResizeRequest;
import com.baidubce.services.mongodb.model.MongodbNodeRequest;
import com.baidubce.services.mongodb.model.MongodbResetPasswordRequest;
import com.baidubce.services.mongodb.model.MongodbResizeNodeRequest;
import com.baidubce.services.mongodb.model.MongodbSecurityIpRequest;
import com.baidubce.services.mongodb.model.MongodbUserDatabaseRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MongodbClientTest {
    private CapturingMongodbClient client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new MongodbClientConfiguration();
        config.setEndpoint("http://mongodb.bj.baidubce.com");
        client = new CapturingMongodbClient(config);
    }

    @Test
    public void testInstanceLifecycleRoutes() {
        client.createReplicaInstance(new MongodbCreateReplicaRequest());
        assertRequest(HttpMethodName.POST, "/v1/instance");
        // every request must carry from=api (OpenAPI entry)
        Assert.assertEquals("api", client.lastRequest.getParameters().get("from"));

        client.createShardInstance(new MongodbCreateShardingRequest());
        assertRequest(HttpMethodName.POST, "/v1/instance");
        Assert.assertEquals("api", client.lastRequest.getParameters().get("from"));

        client.listInstances(new MongodbInstanceListRequest());
        assertRequest(HttpMethodName.GET, "/v1/instance");
        Assert.assertEquals("marker", client.lastRequest.getParameters().get("manner"));

        client.getInstance("mongodb-1");
        assertRequest(HttpMethodName.GET, "/v1/instance/mongodb-1");

        client.recycleInstances("mongodb-1,mongodb-2");
        assertRequest(HttpMethodName.DELETE, "/v1/instance");
        Assert.assertEquals("mongodb-1,mongodb-2", client.lastRequest.getParameters().get("dbInstanceIds"));

        client.deleteInstancesPermanent("mongodb-1");
        assertRequest(HttpMethodName.POST, "/v1/instance");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("deletePermanent"));

        client.recoverInstances("mongodb-1");
        assertRequest(HttpMethodName.POST, "/v1/instance");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("recover"));
    }

    @Test
    public void testResizeRoutes() {
        client.resizeInstance(new MongodbCreateOrderRequest<MongodbInstanceResizeRequest>());
        assertRequest(HttpMethodName.PUT, "/v1/instance");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("resize"));

        client.createNode("mongodb-1", new MongodbCreateOrderRequest<MongodbCreateNodeRequest>());
        assertRequest(HttpMethodName.POST, "/v1/instance/mongodb-1/node");

        client.resizeNode("mongodb-1", new MongodbCreateOrderRequest<MongodbResizeNodeRequest>());
        assertRequest(HttpMethodName.PUT, "/v1/instance/mongodb-1/node");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("resize"));
    }

    @Test
    public void testUserDatabaseRoutes() {
        client.createUser("mongodb-1", new MongodbUserDatabaseRequest());
        assertRequest(HttpMethodName.PUT, "/v1/instance/mongodb-1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("createUser"));

        client.dropUser("mongodb-1", new MongodbUserDatabaseRequest());
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("dropUser"));

        client.updateUserRole("mongodb-1", new MongodbUserDatabaseRequest());
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("updateRole"));

        client.resetPassword("mongodb-1", new MongodbResetPasswordRequest());
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("resetPassword"));

        client.listUsers("mongodb-1", "-1", 10);
        assertRequest(HttpMethodName.GET, "/v1/instance/mongodb-1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("listUsers"));

        client.createDatabase("mongodb-1", new MongodbUserDatabaseRequest());
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("createDatabase"));

        client.dropDatabase("mongodb-1", new MongodbUserDatabaseRequest());
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("dropDatabase"));

        client.listDatabases("mongodb-1", "-1", 10);
        assertRequest(HttpMethodName.GET, "/v1/instance/mongodb-1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("listDatabases"));
    }

    @Test
    public void testSecurityIpRoutes() {
        client.describeSecurityIps("mongodb-1");
        assertRequest(HttpMethodName.GET, "/v1/instance/mongodb-1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("describeSecurityIps"));

        client.addSecurityIps("mongodb-1", new MongodbSecurityIpRequest());
        assertRequest(HttpMethodName.PUT, "/v1/instance/mongodb-1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("addSecurityIps"));

        client.deleteSecurityIps("mongodb-1", new MongodbSecurityIpRequest());
        assertRequest(HttpMethodName.PUT, "/v1/instance/mongodb-1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("deleteSecurityIps"));
    }

    @Test
    public void testSecurityIpGroupRoutes() {
        client.createSecurityIpGroup("mongodb-1", new MongodbCreateSecurityIpGroupRequest());
        assertRequest(HttpMethodName.POST, "/v1/api/mongo/instance/mongodb-1/securityIpGroup");

        client.getSecurityIpGroupList("mongodb-1");
        assertRequest(HttpMethodName.GET, "/v1/api/mongo/instance/mongodb-1/securityIpGroup");

        client.updateSecurityIpGroup("mongodb-1", "grp-1", new MongodbCreateSecurityIpGroupRequest());
        assertRequest(HttpMethodName.PUT, "/v1/api/mongo/instance/mongodb-1/securityIpGroup/grp-1");

        client.deleteSecurityIpGroup("mongodb-1", "grp-1");
        assertRequest(HttpMethodName.DELETE, "/v1/api/mongo/instance/mongodb-1/securityIpGroup/grp-1");
    }

    @Test
    public void testBackupRoutes() {
        client.createBackup("mongodb-1", new MongodbBackupRequest());
        assertRequest(HttpMethodName.POST, "/v1/instance/mongodb-1/backup");

        MongodbBackupListRequest listRequest = new MongodbBackupListRequest();
        client.listBackups("mongodb-1", listRequest);
        assertRequest(HttpMethodName.GET, "/v1/instance/mongodb-1/backup");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("listBackupList"));

        client.describeBackup("mongodb-1", "backup-1");
        assertRequest(HttpMethodName.GET, "/v1/instance/mongodb-1/backup/backup-1");

        client.getBackupPolicy("mongodb-1");
        assertRequest(HttpMethodName.GET, "/v1/instance/mongodb-1/backupPolicy");

        client.modifyBackupPolicy("mongodb-1", new MongodbBackupPolicyRequest());
        assertRequest(HttpMethodName.PUT, "/v1/instance/mongodb-1/backupPolicy");
    }

    @Test
    public void testRecoveryAndHaRoutes() {
        client.getRecoverableTimeRange("mongodb-1");
        assertRequest(HttpMethodName.GET, "/v1/instance/mongodb-1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("getRecovableTimeRange"));

        client.switchInstanceHA("mongodb-1", new MongodbInstanceRequest());
        assertRequest(HttpMethodName.PUT, "/v1/instance/mongodb-1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("switchHA"));

        client.switchNodeHA("mongodb-1", "node-1", new MongodbNodeRequest());
        assertRequest(HttpMethodName.PUT, "/v1/instance/mongodb-1/node/node-1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("switchHA"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyInstanceIdRejected() {
        client.getInstance("");
    }

    @Test(expected = NullPointerException.class)
    public void testNullBodyRejected() {
        client.createReplicaInstance(null);
    }

    private void assertRequest(HttpMethodName method, String path) {
        Assert.assertEquals(method, client.lastRequest.getHttpMethod());
        Assert.assertEquals(path, client.lastRequest.getUri().getPath());
    }

    private static class CapturingMongodbClient extends MongodbClient {
        private InternalRequest lastRequest;

        CapturingMongodbClient(BceClientConfiguration clientConfiguration) {
            super(clientConfiguration);
        }

        @Override
        protected <T extends AbstractBceResponse> T invokeHttpClient(InternalRequest request, Class<T> responseClass) {
            this.lastRequest = request;
            try {
                return responseClass.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

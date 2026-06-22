package com.baidubce.services.gaiadb;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.gaiadb.model.Account;
import com.baidubce.services.gaiadb.model.AccountPrivilegesRequest;
import com.baidubce.services.gaiadb.model.ClusterCreateRequest;
import com.baidubce.services.gaiadb.model.ClusterListRequest;
import com.baidubce.services.gaiadb.model.ClusterResizeRequest;
import com.baidubce.services.gaiadb.model.Database;
import com.baidubce.services.gaiadb.model.GaiadbInterfaceUpdateDnsNameRequest;
import com.baidubce.services.gaiadb.model.GaiadbInterfaceUpdateRequest;
import com.baidubce.services.gaiadb.model.RenewClusterRequest;
import com.baidubce.services.gaiadb.model.SnapshotPolicyListRequest;
import com.baidubce.services.gaiadb.model.SnapshotPolicyUpdateRequest;
import com.baidubce.services.gaiadb.model.UpdateAccountAuthIpRequest;
import com.baidubce.services.gaiadb.model.UpdateAccountPasswordRequest;
import com.baidubce.services.gaiadb.model.UpdateComputeModuleParamsRequest;
import com.baidubce.services.gaiadb.model.WhitelistRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GaiadbClientTest {
    private CapturingGaiadbClient client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new GaiadbClientConfiguration();
        config.setEndpoint("http://gaiadb.bj.baidubce.com");
        client = new CapturingGaiadbClient(config);
    }

    @Test
    public void testClusterRoutes() {
        client.createCluster(new ClusterCreateRequest());
        assertRequest(HttpMethodName.POST, "/v1/gaiadb/cluster");

        client.resizeCluster("gaiadb-1", new ClusterResizeRequest());
        assertRequest(HttpMethodName.PUT, "/v1/gaiadb/cluster/gaiadb-1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("resize"));

        ClusterListRequest listRequest = new ClusterListRequest();
        listRequest.setMarker("m1");
        listRequest.setMaxKeys(10);
        listRequest.setClusterIpList("10.0.0.1");
        client.listClusters(listRequest);
        assertRequest(HttpMethodName.GET, "/v1/gaiadb/cluster");
        Assert.assertEquals("m1", client.lastRequest.getParameters().get("marker"));
        Assert.assertEquals("10", client.lastRequest.getParameters().get("maxKeys"));
        Assert.assertEquals("10.0.0.1", client.lastRequest.getParameters().get("clusterIpList"));

        client.getCluster("gaiadb-1");
        assertRequest(HttpMethodName.GET, "/v1/gaiadb/cluster/gaiadb-1");

        client.deleteCluster("gaiadb-1");
        assertRequest(HttpMethodName.DELETE, "/v1/gaiadb/cluster/gaiadb-1");

        client.renewCluster("gaiadb-1", new RenewClusterRequest());
        assertRequest(HttpMethodName.PUT, "/v1/gaiadb/cluster/gaiadb-1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("renew"));
    }

    @Test
    public void testPriceAndInterfaceRoutes() {
        client.getClusterPrice(new ClusterCreateRequest());
        assertRequest(HttpMethodName.POST, "/v1/gaiadb/price");

        client.getResizePrice(new ClusterResizeRequest());
        assertRequest(HttpMethodName.POST, "/v1/gaiadb/price/diff");

        client.listInterfaces("gaiadb-1");
        assertRequest(HttpMethodName.GET, "/v1/gaiadb/gaiadb-1/interface");

        client.updateInterface("gaiadb-1", new GaiadbInterfaceUpdateRequest());
        assertRequest(HttpMethodName.PUT, "/v1/gaiadb/gaiadb-1/interface");

        client.updateInterfaceDnsName("gaiadb-1", new GaiadbInterfaceUpdateDnsNameRequest());
        assertRequest(HttpMethodName.PUT, "/v1/gaiadb/gaiadb-1/interface/dns-name");
    }

    @Test
    public void testAccountAndDatabaseRoutes() {
        client.createAccount("gaiadb-1", new Account());
        assertRequest(HttpMethodName.POST, "/v1/gaiadb/gaiadb-1/account");

        client.listAccounts("gaiadb-1", "admin");
        assertRequest(HttpMethodName.GET, "/v1/gaiadb/gaiadb-1/account");
        Assert.assertEquals("admin", client.lastRequest.getParameters().get("type"));

        client.updateAccountPassword("gaiadb-1", "user1", new UpdateAccountPasswordRequest());
        assertRequest(HttpMethodName.PUT, "/v1/gaiadb/gaiadb-1/account/user1");
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("password"));

        client.updateAccountPrivileges("gaiadb-1", "user1", new AccountPrivilegesRequest());
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("privileges"));

        client.updateAccountAuthIp("gaiadb-1", "user1", new UpdateAccountAuthIpRequest());
        Assert.assertTrue(client.lastRequest.getParameters().containsKey("authip"));

        client.deleteAccount("gaiadb-1", "user1");
        assertRequest(HttpMethodName.DELETE, "/v1/gaiadb/gaiadb-1/account/user1");

        client.createDatabase("gaiadb-1", new Database());
        assertRequest(HttpMethodName.POST, "/v1/gaiadb/gaiadb-1/database");

        client.listDatabases("gaiadb-1");
        assertRequest(HttpMethodName.GET, "/v1/gaiadb/gaiadb-1/database");

        client.deleteDatabase("gaiadb-1", "db1");
        assertRequest(HttpMethodName.DELETE, "/v1/gaiadb/gaiadb-1/database/db1");
    }

    @Test
    public void testSnapshotWhitelistAndParamsRoutes() {
        client.listSnapshots("gaiadb-1");
        assertRequest(HttpMethodName.GET, "/v1/gaiadb/gaiadb-1/snapshot");

        client.updateSnapshotPolicy("gaiadb-1", new SnapshotPolicyUpdateRequest());
        assertRequest(HttpMethodName.PUT, "/v1/gaiadb/gaiadb-1/snapshot/policy");

        SnapshotPolicyListRequest policyListRequest = new SnapshotPolicyListRequest();
        policyListRequest.setDataType("data");
        client.getSnapshotPolicy("gaiadb-1", policyListRequest);
        assertRequest(HttpMethodName.GET, "/v1/gaiadb/gaiadb-1/snapshot/policy");
        Assert.assertEquals("data", client.lastRequest.getParameters().get("dataType"));

        client.updateWhitelist("gaiadb-1", new WhitelistRequest());
        assertRequest(HttpMethodName.PUT, "/v1/gaiadb/gaiadb-1/whitelist");

        client.getWhitelist("gaiadb-1");
        assertRequest(HttpMethodName.GET, "/v1/gaiadb/gaiadb-1/whitelist");

        client.getComputeModuleParams("gaiadb-1");
        assertRequest(HttpMethodName.GET, "/v1/gaiadb/cluster/gaiadb-1/compute/params");

        client.updateComputeModuleParams("gaiadb-1", new UpdateComputeModuleParamsRequest());
        assertRequest(HttpMethodName.PUT, "/v1/gaiadb/cluster/gaiadb-1/compute/params");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyClusterIdRejected() {
        client.getCluster("");
    }

    @Test(expected = NullPointerException.class)
    public void testNullBodyRejected() {
        client.createCluster(null);
    }

    private void assertRequest(HttpMethodName method, String path) {
        Assert.assertEquals(method, client.lastRequest.getHttpMethod());
        Assert.assertEquals(path, client.lastRequest.getUri().getPath());
    }

    private static class CapturingGaiadbClient extends GaiadbClient {
        private InternalRequest lastRequest;

        CapturingGaiadbClient(BceClientConfiguration clientConfiguration) {
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

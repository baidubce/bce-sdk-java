package com.baidubce.services.iothisk;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.iothisk.model.GetSubCertResponse;
import com.baidubce.services.iothisk.model.RenewSubCertRequest;

public class ServerCertTest extends BaseIotPkiManageClientTest {

    private String rootCACertId;
    private String groupId;

    @Before
    public void initRootCertAndCertGroup() {
        rootCACertId = createRootCACert().getCertId();
        groupId = createCertGroup(rootCACertId).getGroupId();
    }

    @After
    public void clean() {
        client.deleteCertGroup(groupId);
        client.deleteRootCACert(rootCACertId);
    }

    @Test
    public void createAndDeleteServerCertTest() {
        String serverCertId = createServerCert(groupId).getCertId();
        Assert.assertNotNull(serverCertId);
        client.deleteServerCert(serverCertId);

        expectBceServiceException(404, "ResourceNotFound");
        client.getServerCert(serverCertId);
    }

    @Test
    public void getServerCertTest() {
        String serverCertId = createServerCert(groupId).getCertId();
        Assert.assertNotNull(serverCertId);
        GetSubCertResponse response = client.getServerCert(serverCertId);
        Assert.assertNotNull(response.getDownloadUrl());
        Assert.assertEquals(groupId, response.getGroupId());
        Assert.assertEquals(rootCACertId, response.getRootCertId());

        client.deleteServerCert(serverCertId);
    }

    @Test
    public void queryServerCertTest() {
        String serverCertId = createServerCert(groupId).getCertId();
        Assert.assertNotNull(serverCertId);

        Assert.assertEquals(1, client.queryServerCerts(rootCACertId, null).getServerCerts().size());
        Assert.assertEquals(1, client.queryServerCerts(null, groupId).getServerCerts().size());

        List<String> serverCertIds = client.queryServerCerts(rootCACertId, groupId).getServerCerts();
        Assert.assertTrue(serverCertIds.contains(serverCertId));

        client.deleteServerCert(serverCertId);
    }

    @Test
    public void renewServerCertTest() {
        String serverCertId = createServerCert(groupId).getCertId();
        Assert.assertNotNull(serverCertId);

        RenewSubCertRequest request = new RenewSubCertRequest()
                .withCsr(csr)
                .withDuration(3650)
                .withNewAddress(Arrays.asList("newAddress"))
                .withNewDeviceId("deviceId1");

        String downloadUrl = client.renewServerCert(request, serverCertId, generateClientToken()).getDownloadUrl();
        Assert.assertNotNull(downloadUrl);

        client.deleteServerCert(serverCertId);
    }

    @Test
    public void downloadServerCertTest() {
        String serverCertId = createServerCert(groupId).getCertId();
        Assert.assertNotNull(serverCertId);

        String certContent = client.downloadServerCert(serverCertId);
        Assert.assertTrue(certContent.contains(BEGIN_CERTIFICATE));

        client.deleteServerCert(serverCertId);
    }

}

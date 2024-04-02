package com.baidubce.services.iothisk;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.iothisk.model.GetSubCertResponse;
import com.baidubce.services.iothisk.model.RenewSubCertRequest;

public class ClientCertTest extends BaseIotPkiManageClientTest {

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
    public void createAndDeleteClientCertTest() {
        String clientCertId = createClientCert(groupId).getCertId();
        Assert.assertNotNull(clientCertId);
        client.deleteClientCert(clientCertId);

        expectBceServiceException(404, "ResourceNotFound");
        client.getClientCert(clientCertId);
    }

    @Test
    public void getClientCertTest() {
        String clientCertId = createClientCert(groupId).getCertId();
        Assert.assertNotNull(clientCertId);
        GetSubCertResponse response = client.getClientCert(clientCertId);
        Assert.assertNotNull(response.getDownloadUrl());
        Assert.assertEquals(groupId, response.getGroupId());
        Assert.assertEquals(rootCACertId, response.getRootCertId());

        client.deleteClientCert(clientCertId);
    }

    @Test
    public void queryClientCertTest() {
        String clientCertId = createClientCert(groupId).getCertId();
        Assert.assertNotNull(clientCertId);

        Assert.assertEquals(1, client.queryClientCerts(rootCACertId, null).getClientCerts().size());
        Assert.assertEquals(1, client.queryClientCerts(null, groupId).getClientCerts().size());

        List<String> clientCertIds = client.queryClientCerts(rootCACertId, groupId).getClientCerts();
        Assert.assertTrue(clientCertIds.contains(clientCertId));

        client.deleteClientCert(clientCertId);
    }

    @Test
    public void renewClientCertTest() {
        String clientCertId = createClientCert(groupId).getCertId();
        Assert.assertNotNull(clientCertId);

        RenewSubCertRequest request = new RenewSubCertRequest()
                .withCsr(csr)
                .withDuration(3650)
                .withNewDeviceId("deviceId1");

        String downloadUrl = client.renewClientCert(request, clientCertId, generateClientToken()).getDownloadUrl();
        Assert.assertNotNull(downloadUrl);

        client.deleteClientCert(clientCertId);
    }

    @Test
    public void downloadClientCertTest() {
        String clientCertId = createClientCert(groupId).getCertId();
        Assert.assertNotNull(clientCertId);

        String certContent = client.downloadClientCert(clientCertId);
        Assert.assertTrue(certContent.contains(BEGIN_CERTIFICATE));

        client.deleteClientCert(clientCertId);
    }

}

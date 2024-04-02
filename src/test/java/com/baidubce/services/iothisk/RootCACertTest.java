package com.baidubce.services.iothisk;

import org.junit.Assert;
import org.junit.Test;

import com.baidubce.services.iothisk.model.GetRootCACertResponse;

public class RootCACertTest extends BaseIotPkiManageClientTest {

    @Test
    public void createAndDeleteRootCACertTest() {
        String rootCACertId = createRootCACert().getCertId();
        Assert.assertNotNull(rootCACertId);
        client.deleteRootCACert(rootCACertId);

        expectBceServiceException(404, "ResourceNotFound");
        client.getRootCACert(rootCACertId);
    }

    @Test
    public void getRootCACertTest() {
        String rootCACertId = createRootCACert().getCertId();
        Assert.assertNotNull(rootCACertId);
        GetRootCACertResponse response = client.getRootCACert(rootCACertId);
        Assert.assertNotNull(response.getCrl());
        Assert.assertNotNull(response.getDownloadUrl());

        client.deleteRootCACert(rootCACertId);
    }

    @Test
    public void downloadRootCACertTest() {
        String rootCACertId = createRootCACert().getCertId();
        Assert.assertNotNull(rootCACertId);

        String certContent = client.downloadRootCert(rootCACertId);
        Assert.assertTrue(certContent.contains(BEGIN_CERTIFICATE));

        client.deleteRootCACert(rootCACertId);
    }

}

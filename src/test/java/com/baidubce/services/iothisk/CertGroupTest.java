package com.baidubce.services.iothisk;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.iothisk.model.GetCertGroupResponse;

public class CertGroupTest extends BaseIotPkiManageClientTest {

    private String rootCACertId;

    @Before
    public void initRootCACert() {
        rootCACertId = createRootCACert().getCertId();
    }

    @After
    public void cleanRootCACert() {
        client.deleteRootCACert(rootCACertId);
    }

    @Test
    public void createAndDeleteRootCACertTest() {
        String groupId = createCertGroup(rootCACertId).getGroupId();
        Assert.assertTrue(StringUtils.isNotEmpty(groupId));
        client.deleteCertGroup(groupId);

        expectBceServiceException(404, "ResourceNotFound");
        client.getCertGroup(groupId);
    }

    @Test
    public void getRootCACertTest() {
        String groupId = createCertGroup(rootCACertId).getGroupId();
        Assert.assertTrue(StringUtils.isNotEmpty(groupId));
        GetCertGroupResponse response = client.getCertGroup(groupId);
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getClientCerts().isEmpty());
        Assert.assertTrue(response.getServerCerts().isEmpty());
        Assert.assertEquals(rootCACertId, response.getRootCertId());

        client.deleteCertGroup(groupId);
    }

}

package com.baidubce.services.iothisk;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.iothisk.model.BatchCreateClientCertRequest;
import com.baidubce.services.iothisk.model.GetBatchCreateStatusResponse;
import com.google.common.util.concurrent.Uninterruptibles;

public class BatchCreateClientCertTest extends BaseIotPkiManageClientTest {

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
    public void testCreateAndDownload() throws Exception {
        Map<String, String> deviceIdAndCsr = new HashMap<String, String>();
        String deviceId = "device012333";
        deviceIdAndCsr.put(deviceId, csr);
        BatchCreateClientCertRequest request = BatchCreateClientCertRequest.create(groupId, 365, deviceIdAndCsr);
        String jobId = client.batchCreateClientCert(request, generateClientToken()).getJobId();
        waitForCreateComplete(jobId);
        GetBatchCreateStatusResponse response = client.getBatchCreateStatus(jobId);
        Assert.assertTrue(response.isSucceed());
        Assert.assertEquals(deviceId, response.getCertIds().get(0).getDeviceId());

        Map<String, String> deviceIdCertMap = client.downloadBatchCreateCerts(jobId);
        Assert.assertNotNull(deviceIdCertMap);
        Assert.assertEquals(1, deviceIdCertMap.size());
        Assert.assertTrue(deviceIdCertMap.containsKey(deviceId));
        Assert.assertTrue(deviceIdCertMap.get(deviceId).contains(BEGIN_CERTIFICATE));

        client.deleteClientCert(response.getCertIds().get(0).getCertId());
    }

    private void waitForCreateComplete(String jobId) {
        int lastRetryTime = 60;
        while (lastRetryTime >= 0) {
            lastRetryTime --;
            GetBatchCreateStatusResponse response = client.getBatchCreateStatus(jobId);
            if (response.isSucceed()) {
                return;
            }
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
    }

}

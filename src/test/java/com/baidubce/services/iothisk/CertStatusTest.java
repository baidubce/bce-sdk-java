package com.baidubce.services.iothisk;

import org.junit.Assert;
import org.junit.Test;

import com.baidubce.services.iothisk.model.CertStatus;
import com.baidubce.services.iothisk.model.GetCertStatusRequest;
import com.baidubce.services.iothisk.model.GetCertStatusResponse;

public class CertStatusTest extends BaseIotPkiManageClientTest {

    private static final String BEGIN_CRL = "-----BEGIN X509 CRL-----";

    private static final String deleteSn = "510f113f1f4cfd03";
    private static final String deleteDn = "C=cn, O=bd, OU=iot, CN=comI4Qc2, EMAILADDRESS=www@baidu";
    private static final byte[] ocspRequest = new byte[] { 48, 101, 48, 99, 48, 69, 48, 67, 48, 65, 48, 9, 6, 5, 43, 14,
            3, 2, 26, 5, 0, 4, 20, -44, -16, 16, 89, 14, -115, 96, -82, 107, -112, 14, 111, -109, -27, 20, -14, 87, 72,
            -122, 80, 4, 20, -63, -11, -1, -70, -39, 35, -52, -31, -21, 61, -39, 36, -64, 96, 126, -108, 45, -99, 28,
            59, 2, 8, 8, 81, -48, -105, -128, 0, -14, 64, -94, 26, 48, 24, 48, 22, 6, 9, 43, 6, 1, 5, 5, 7, 48, 1, 2, 1,
            1, -1, 4, 6, 1, 102, 41, 41, -44, 55 };

    @Test
    public void testDownloadCrl() {
        Assert.assertTrue(client.downloadCrl(deleteDn).getCrlContent().contains(BEGIN_CRL));
    }

    @Test
    public void testGetStatusWithDeleteCert() {
        GetCertStatusRequest request = new GetCertStatusRequest()
                .withCertificateSN(deleteSn)
                .withIssuerDN(deleteDn);
        GetCertStatusResponse response = client.getCertStatus(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(CertStatus.Revoked, response.getStatus());
    }

    @Test
    public void testGetStatusWithNotExistCert() {
        GetCertStatusRequest request = new GetCertStatusRequest()
                .withCertificateSN("not exist cert")
                .withIssuerDN(deleteDn);
        GetCertStatusResponse response = client.getCertStatus(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(CertStatus.CertNotFound, response.getStatus());
    }

    @Test
    public void testGetStatusWithNotExistDN() {
        GetCertStatusRequest request = new GetCertStatusRequest()
                .withCertificateSN(deleteSn)
                .withIssuerDN("not exist DN");
        GetCertStatusResponse response = client.getCertStatus(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(CertStatus.CANotFount, response.getStatus());
    }

    @Test
    public void testGetOcspResponse() {
        byte[] ocspResponse = client.getOcspResponse(ocspRequest).getOcspResponse();
        Assert.assertNotNull(ocspResponse);
        Assert.assertTrue(ocspResponse.length > 10);
    }

}

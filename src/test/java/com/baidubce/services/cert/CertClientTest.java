/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.cert;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.cert.model.CertCreateRequest;
import com.baidubce.services.cert.model.CertCreateResponse;
import com.baidubce.services.cert.model.CertListResponse;
import com.baidubce.services.cert.model.CertificateMeta;

public class CertClientTest {

    public static final String data = "-----BEGIN CERTIFICATE-----\n"
            + "MIIF3jCCBMagAwIBAgIRAKd3odetjuMN1XfDMMVaFNEwDQYJKoZIhvcNAQELBQAw\n"
            + "TDELMAkGA1UEBhMCTFYxDTALBgNVBAcTBFJpZ2ExETAPBgNVBAoTCEdvR2V0U1NM\n"
            + "MRswGQYDVQQDExJHb0dldFNTTCBSU0EgRFYgQ0EwHhcNMTkwODA2MDAwMDAwWhcN\n"
            + "MjAwODA1MjM1OTU5WjBXMSEwHwYDVQQLExhEb21haW4gQ29udHJvbCBWYWxpZGF0\n"
            + "ZWQxHDAaBgNVBAsTE0dvR2V0U1NMIERvbWFpbiBTU0wxFDASBgNVBAMTC3dhcC5w\n"
            + "YWtzLmNuMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApt4Jc1KJcn/k\n"
            + "ktdqNLRo5uv4ajpHCOVLoAW1igjn/HO7o/QEQjTXZK7J+31+UXoZ6VFxJIaB2K93\n"
            + "5F7919IMMuvBaHOIoQLlaVctUi71pLLPDYi1VJfsqYA4qZ7X8zr5vWSiYrfsqu1l\n"
            + "tcWOxhYUSMRGh6CpMsQQ/a3Sf3mkraa0OuWJfyA0RPD/0DilGNhcFTqfAOAIF3qH\n"
            + "vjL3mrqshPQ4RlKp+RCqJ+dsXD01JZyU+aP7E7YA8tGx2RZdd8M/vZ1JzJOno+Fx\n"
            + "/stm5lptCFhvHoTNwPHsISg2kBW1166s3H5eLMTSUu+CTxzO24UkkovNPwbMX/oo\n"
            + "NpZwlzPmaQIDAQABo4ICrjCCAqowHwYDVR0jBBgwFoAU+ftQxItnu2dk/oMhpqnO\n"
            + "P1WEk5kwHQYDVR0OBBYEFPHjx8Fvb0RDy48s27jJqE3F46WsMA4GA1UdDwEB/wQE\n"
            + "AwIFoDAMBgNVHRMBAf8EAjAAMB0GA1UdJQQWMBQGCCsGAQUFBwMBBggrBgEFBQcD\n"
            + "AjBLBgNVHSAERDBCMDYGCysGAQQBsjEBAgJAMCcwJQYIKwYBBQUHAgEWGWh0dHBz\n"
            + "Oi8vY3BzLnVzZXJ0cnVzdC5jb20wCAYGZ4EMAQIBMD0GA1UdHwQ2MDQwMqAwoC6G\n"
            + "LGh0dHA6Ly9jcmwudXNlcnRydXN0LmNvbS9Hb0dldFNTTFJTQURWQ0EuY3JsMG8G\n"
            + "CCsGAQUFBwEBBGMwYTA4BggrBgEFBQcwAoYsaHR0cDovL2NydC51c2VydHJ1c3Qu\n"
            + "Y29tL0dvR2V0U1NMUlNBRFZDQS5jcnQwJQYIKwYBBQUHMAGGGWh0dHA6Ly9vY3Nw\n"
            + "LnVzZXJ0cnVzdC5jb20wJwYDVR0RBCAwHoILd2FwLnBha3MuY26CD3d3dy53YXAu\n"
            + "cGFrcy5jbjCCAQMGCisGAQQB1nkCBAIEgfQEgfEA7wB2ALIeBcyLos2KIE6HZvkr\n"
            + "uYolIGdr2vpw57JJUy3vi5BeAAABbGY6HH4AAAQDAEcwRQIgHN0mqqABN9n4fJST\n"
            + "sEivAH2qbuFDWYu5XaxRvxiOf84CIQCWhPfnuV9TwPO5V806kRpM2XcehZ7wZQLU\n"
            + "9T9UPKL6rAB1AF6nc/nfVsDntTZIfdBJ4DJ6kZoMhKESEoQYdZaBcUVYAAABbGY6\n"
            + "HKUAAAQDAEYwRAIgCDJ4GhtyYi6Zl6VvBIlBYBZV/+I/AqUoAkW0qf1T1gECIHoZ\n"
            + "6trF2wxK2xeYRYT8JAbRy+zrVwIw7P7AaCO4tpTfMA0GCSqGSIb3DQEBCwUAA4IB\n"
            + "AQBLSX2fNb7HUxsBmRuvzZOptP+9koI/U2IqEjz89/lZB0kkjavQqc78I8TW0bXj\n"
            + "NpIQEWvpxDd9Ky6kdBCsW8hXP+0iLLdbg45EFDDxF9zLxODr/+AHnZDHB2GfmyeX\n"
            + "ou6G3/X9MMKbBfqTHfJ7hWSJxpnCDUXhsqTTSAwM8QzHEOKGSOPtLWoXhKMzo5gR\n"
            + "m1I/0LcnIk5eE5Z7LMibfRz9EKVaArJI7ziJ9s52emtXveRzQvaxaAzBU1ZIscXQ\n"
            + "C6n1kdUjEOvcVPlIOiE8lrfJqewFJ2K0jEps0zZZWiptRmZJYiRHCAPrH/nomSZD\n"
            + "lTn9M1KG444ZatTUExaX4gXP\n"
            + "-----END CERTIFICATE-----";
    public static final String pri = "-----BEGIN PRIVATE KEY-----\n"
            + "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCm3glzUolyf+SS\n"
            + "12o0tGjm6/hqOkcI5UugBbWKCOf8c7uj9ARCNNdkrsn7fX5RehnpUXEkhoHYr3fk\n"
            + "Xv3X0gwy68Foc4ihAuVpVy1SLvWkss8NiLVUl+ypgDipntfzOvm9ZKJit+yq7WW1\n"
            + "xY7GFhRIxEaHoKkyxBD9rdJ/eaStprQ65Yl/IDRE8P/QOKUY2FwVOp8A4AgXeoe+\n"
            + "MveauqyE9DhGUqn5EKon52xcPTUlnJT5o/sTtgDy0bHZFl13wz+9nUnMk6ej4XH+\n"
            + "y2bmWm0IWG8ehM3A8ewhKDaQFbXXrqzcfl4sxNJS74JPHM7bhSSSi80/Bsxf+ig2\n"
            + "lnCXM+ZpAgMBAAECggEAbMJNUI/cK/e1qAulGmL3+IKWb846HE8u4ka2UxiI858P\n"
            + "DsZtvCUqumG+Hh9mJH67wrmlT9Qnb262au+K7Y7nCo1kisq6o476rZT3YhhfXJB6\n"
            + "qWyQG2nGA5gh1lREljC7M7kSqdsM8z81yqrP6sXbRK5A9gc+IGtwU5dmmlVTgyYV\n"
            + "QrJfu1Y2hDWMc3a+Og9AnRfsDYGxKx5n1FXrRoWIidm5DtnL4DkaytWCAX2OZP8U\n"
            + "DTeFhKcUA9dTEtl+5j5RUj/R1XYsp0I9QXYSgPS07s3v6Oz/6FHCqsG/XLmWY15E\n"
            + "QwoZfdXgJJiDfhZGhTpoxi7mUYRRzLCdWHzZeAzuLQKBgQDdfHS2Scbg9WJ5TGAm\n"
            + "g+0cSo1SLaTZ1FSUca065OJt2oWzbpVNBi7pQMapfCW546WFw13NvLOcsLlSOC35\n"
            + "1kJn01a1KhW/2KF8Hv1hzrdJ84aehMtqZsF2XMNBlHHO9Aa4dWb4aDGnc6Tfjj/e\n"
            + "oGw1iaBArCNHx+X8DNWArigBgwKBgQDA3rjruOyY9JFCqOmbX9Dz85hrO2suqjvo\n"
            + "6gshYCq6nvutdI2NBrOWdZQsZ2EMf/S9YQIeqt5nhmRNUwEtuTeVw8GppS7Gwbu3\n"
            + "mDbB6IRyS+jzSj4lC4Mtq/arT8RqFkC5Tsx6rbbt2LaLATIP9LUigEC9VooOLh3J\n"
            + "EJM0BhZQowKBgAy78xnn8/9LHHZM5dpRC3js8GMhLLE5fANs5cDVGA+o2LcQTJU5\n"
            + "HclbaBCmiqS9ae7e5K3Hg6wmnQEVKc+LehXuGLYhzU5SbjZssbTF5dPipILIr8lW\n"
            + "Txk3DCLhHgoADcLdPSZbFz/BbmFMFHAAWThayVPukbi2zYpIUp4rZTFxAoGAd7EA\n"
            + "c5EEi3ujNgobR0g0JWCz40AxqPkOSuIL3D+Q5NXK/H8LZUyWYLZBKTlSue4ilPBB\n"
            + "34RMl1SIpI/xeE2sWC3u3nfbSo9qDmrQ+I9yunevbmRRoq29T7ebUf8jtm5u5lEn\n"
            + "hq6Nipdl2Wf5iHtlea67/oRZEqm95Ex7/doVOR8CgYA9bgIvjF38dSh4H/vX+h3a\n"
            + "vjWPWotTcpZMn8zdLZ26ROEW9TSW7XbkANWWav0JpEVZ9IIEPbkZ6u4yP3N+zkf5\n"
            + "YYNyrl3Z/TPwKHecP5040z7SsfauZYP/XYt9ODQnjE58HUnYnFNg7J7xnCLTF4JR\n"
            + "+fPBkKbkc/IaNOKrMi8jCg==\n"
            + "-----END PRIVATE KEY-----";
    public static final String link = "-----BEGIN CERTIFICATE-----\n"
            + "MIIF1zCCA7+gAwIBAgIRAJOLsI5imHtPdfmMtqUEXJYwDQYJKoZIhvcNAQEMBQAw\n"
            + "gYgxCzAJBgNVBAYTAlVTMRMwEQYDVQQIEwpOZXcgSmVyc2V5MRQwEgYDVQQHEwtK\n"
            + "ZXJzZXkgQ2l0eTEeMBwGA1UEChMVVGhlIFVTRVJUUlVTVCBOZXR3b3JrMS4wLAYD\n"
            + "VQQDEyVVU0VSVHJ1c3QgUlNBIENlcnRpZmljYXRpb24gQXV0aG9yaXR5MB4XDTE4\n"
            + "MDkwNjAwMDAwMFoXDTI4MDkwNTIzNTk1OVowTDELMAkGA1UEBhMCTFYxDTALBgNV\n"
            + "BAcTBFJpZ2ExETAPBgNVBAoTCEdvR2V0U1NMMRswGQYDVQQDExJHb0dldFNTTCBS\n"
            + "U0EgRFYgQ0EwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCfwF4hD6E1\n"
            + "kLglXs1n2fH5vMQukCGyyD4LqLsc3pSzeh8we7njU4TB85BH5YXqcfwiH1Sf78aB\n"
            + "hk1FgXoAZ3EQrF49We8mnTtTPFRnMwEHLJRpY9I/+peKeAZNL0MJG5zM+9gmcSpI\n"
            + "OTI6p7MPela72g0pBQjwcExYLqFFVsnroEPTRRlmfTBTRi9r7rYcXwIct2VUCRmj\n"
            + "jR1GX13op370YjYwgGv/TeYqUWkNiEjWNskFDEfxSc0YfoBwwKdPNfp6t/5+RsFn\n"
            + "lgQKstmFLQbbENsdUEpzWEvZUpDC4qPvRrxEKcF0uLoZhEnxhskwXSTC64BNtc+l\n"
            + "VEk7/g/be8svAgMBAAGjggF1MIIBcTAfBgNVHSMEGDAWgBRTeb9aqitKz1SA4dib\n"
            + "wJ3ysgNmyzAdBgNVHQ4EFgQU+ftQxItnu2dk/oMhpqnOP1WEk5kwDgYDVR0PAQH/\n"
            + "BAQDAgGGMBIGA1UdEwEB/wQIMAYBAf8CAQAwHQYDVR0lBBYwFAYIKwYBBQUHAwEG\n"
            + "CCsGAQUFBwMCMCIGA1UdIAQbMBkwDQYLKwYBBAGyMQECAkAwCAYGZ4EMAQIBMFAG\n"
            + "A1UdHwRJMEcwRaBDoEGGP2h0dHA6Ly9jcmwudXNlcnRydXN0LmNvbS9VU0VSVHJ1\n"
            + "c3RSU0FDZXJ0aWZpY2F0aW9uQXV0aG9yaXR5LmNybDB2BggrBgEFBQcBAQRqMGgw\n"
            + "PwYIKwYBBQUHMAKGM2h0dHA6Ly9jcnQudXNlcnRydXN0LmNvbS9VU0VSVHJ1c3RS\n"
            + "U0FBZGRUcnVzdENBLmNydDAlBggrBgEFBQcwAYYZaHR0cDovL29jc3AudXNlcnRy\n"
            + "dXN0LmNvbTANBgkqhkiG9w0BAQwFAAOCAgEAXXRDKHiA5DOhNKsztwayc8qtlK4q\n"
            + "Vt2XNdlzXn4RyZIsC9+SBi0Xd4vGDhFx6XX4N/fnxlUjdzNN/BYY1gS1xK66Uy3p\n"
            + "rw9qI8X12J4er9lNNhrsvOcjB8CT8FyvFu94j3Bs427uxcSukhYbERBAIN7MpWKl\n"
            + "VWxT3q8GIqiEYVKa/tfWAvnOMDDSKgRwMUtggr/IE77hekQm20p7e1BuJODf1Q7c\n"
            + "FPt7T74m3chg+qu0xheLI6HsUFuOxc7R5SQlkFvaVY5tmswfWpY+rwhyJW+FWNbT\n"
            + "uNXkxR4v5KOQPWrY100/QN68/j17paKuSXNcsr56snuB/Dx+MACLBdsF35HxPadx\n"
            + "78vkfQ37WcVmKZtHrHJQ/QUyjxdG8fezMsh0f+puUln/O+NlsFtipve8qYa9h/K5\n"
            + "yD0oZN93ChWve78XrV4vCpjO75Nk5B8O9CWQqGTHbhkgvjyb9v/B+sYJqB22/NLl\n"
            + "R4RPvbmqDJGeEI+4u6NJ5YiLIVVsX+dyfFP8zUbSsj6J34RyCYKBbQ4L+r7k8Srs\n"
            + "LY51WUFP292wkFDPSDmV7XsUNTDOZoQcBh2Fycf7xFfxeA+6ERx2d8MpPPND7yS2\n"
            + "1dkf+SY5SdpSbAKtYmbqb9q8cZUDEImNWJFUVHBLDOrnYhGwJudE3OBXRTxNhMDm\n"
            + "IXnjEeWrFvAZQhk=\n"
            + "-----END CERTIFICATE-----\n";

    private static final String accessKey = "6a739f8c6b5f454e87bc97db8f5edf74";
    private static final String secretKey = "5c559d28c66848999e0ea094367400ac";
    private static final String endpoint = "https://certificate.baidubce.com";

    private CertClient certClient;

    @Before
    public void setUp() {
        certClient = CertClient.createCertClient(accessKey, secretKey, endpoint);
    }

    @Test
    public void testCreateCert() {
        String certName = "test-create";                          // 证书名
        String certServerData = data;                          // 证书服务器数据
        String certPrivateData = pri;                          // 证书私钥数据
        String certLinkData = link;                            // 证书链数据

        CertCreateRequest request = new CertCreateRequest();
        request.setCertName(certName);
        request.setCertPrivateData(certPrivateData);
        request.setCertServerData(certServerData);
        request.setCertLinkData(certLinkData);

        CertCreateResponse createResponse = certClient.createCert(request);
        Assert.assertTrue(createResponse.getCertId().startsWith("cert-"));
    }

    @Test
    public void testListCert() {
        String certName = "test-list";                          // 证书名
        String certServerData = data;                          // 证书服务器数据
        String certPrivateData = pri;                          // 证书私钥数据
        String certLinkData = link;                            // 证书链数据

        CertCreateRequest request = new CertCreateRequest();
        request.setCertName(certName);
        request.setCertPrivateData(certPrivateData);
        request.setCertServerData(certServerData);
        request.setCertLinkData(certLinkData);

        CertCreateResponse createResponse = certClient.createCert(request);
        CertListResponse listResponse = certClient.listUserCerts();
        Assert.assertTrue(listResponse.getCerts().size() > 0);
    }

    @Test
    public void testGetCertInfo() {
        String certId = "cert-npvqggzm6q2j";
        CertificateMeta certificateMeta = certClient.getCertInfo(certId);
        Assert.assertTrue(certificateMeta.getCertName().equals("test_api"));
    }

    @Test
    public void testUpdateCertName() {
        // 不抛出异常就表示成功
        String certId = "cert-yk8rsck7f6vi";
        String newName = "test-list";
        certClient.updateCertName(certId, newName);
    }

    @Test
    public void testDelete() {
        String certName = "test-list";                          // 证书名
        String certServerData = data;                          // 证书服务器数据
        String certPrivateData = pri;                          // 证书私钥数据
        String certLinkData = link;                            // 证书链数据

        CertCreateRequest request = new CertCreateRequest();
        request.setCertName(certName);
        request.setCertPrivateData(certPrivateData);
        request.setCertServerData(certServerData);
        request.setCertLinkData(certLinkData);

        CertCreateResponse createResponse = certClient.createCert(request);
        certClient.delete(createResponse.getCertId());
    }

    @Test
    public void testReplaceCertData() {
        // 不抛出异常就表示成功
        String certName = "test-list";                          // 证书名
        String certServerData = data;                          // 证书服务器数据
        String certPrivateData = pri;                          // 证书私钥数据
        String certLinkData = link;                            // 证书链数据

        CertCreateRequest request = new CertCreateRequest();
        request.setCertName(certName);
        request.setCertPrivateData(certPrivateData);
        request.setCertServerData(certServerData);
        request.setCertLinkData(certLinkData);

        certClient.replaceCertData("cert-yk8rsck7f6vi", request);
    }
}

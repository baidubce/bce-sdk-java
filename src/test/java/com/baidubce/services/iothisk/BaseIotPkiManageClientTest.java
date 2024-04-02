package com.baidubce.services.iothisk;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.hamcrest.CustomMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothisk.model.CreateCertGroupRequest;
import com.baidubce.services.iothisk.model.CreateCertGroupResponse;
import com.baidubce.services.iothisk.model.CreateRootCACertRequest;
import com.baidubce.services.iothisk.model.CreateRootCACertResponse;
import com.baidubce.services.iothisk.model.CreateSubCertRequest;
import com.baidubce.services.iothisk.model.CreateSubCertResponse;
import com.google.common.base.Objects;

/**
 * Unit test for iot pki service
 */
public abstract class BaseIotPkiManageClientTest {

    // qa-sandbox
    private static final String ACCESSKEY = "701107a8de6f41c29f74ec3f19da6c97";
    private static final String SECRETKEY = "ccc32a43dcb7495cb820919e2597e99b";
    private static final String ENDPOINT = "nmg01-hpc-w1134.nmg01.baidu.com:8023";
    private static final String PKI = "pki";
    protected static final String BEGIN_CERTIFICATE = "-----BEGIN CERTIFICATE-----";

    protected static final String csr = "-----BEGIN CERTIFICATE REQUEST-----\n"
            + "MIICqDCCAZACAQAwYzELMAkGA1UEBhMCQ04xDjAMBgNVBAoMBWJhaWR1MQwwCgYD"
            + "VQQLDANhaWQxFzAVBgNVBAMMDmNsaWVudDNhZmFmODE5MR0wGwYJKoZIhvcNAQkB"
            + "Fg50ZXN0QGJhaWR1LmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB"
            + "ALjfDeQ2qwpkJnJEieE2HyRiEX+rkAmYg6ahJ4CRjcu4gAfYMJe6W/dx2s3aV1oK"
            + "PnbaIxMKqjJ3pka6EtieaUyT7hlsIvZVrc80caUS6Y93olDQr122mnjllX4moKOw"
            + "PkLa6sUoK4HB9dPYfxyoyzoK03daYXyjXptuOhIArAltRoSzuMGodB4W3Wn6aKcR"
            + "da3OmTZ+BquPtYE8ASQ7kzcTpd2tBQxL/NxtCHr+eaCq7DxU/SAKJwjL1vqtelJp"
            + "l2SUiITK93xHLUAYy7wUt5lbKskNq3iBjCLNsBdf/CvgHgRliXmrxtyCdA09nHO1"
            + "wlQ+Fo7Ptwve5k2gYsCVsc8CAwEAAaAAMA0GCSqGSIb3DQEBDQUAA4IBAQCGYfOV"
            + "APUTOCvyusTBKZ2YMgv+9m+DRWmqbFImDzTmH9wJM+DhxW62ukZWNqKZQmpiapSa"
            + "AkEXrkvUk5m+t/p4LsOC2Q0u6r4iC+MwyWmYI+/muUempnMfA2Gb2pZzujtqMIbu"
            + "Rldl8CS6VxORHVUaW1pbCadUJoT3jOdbm67UdLX2bcWTpPtHhagUGzxiRFRsD8vr"
            + "oYstIj+UZnF/KOGSM3EaBALtNeYvYmh9YIeEb7Akfd5pM6lYGNuvRMuc70Y1tKLm"
            + "svqaDdsQ3TzQdDOKZDijPBrrJIdUScjvjrzkPRZBxy4WQseoSmaCyvtiXyK1HfKp"
            + "D3G6ok2bnpJlioaE"
            + "\n-----END CERTIFICATE REQUEST-----";

    protected IotPkiManageClient client;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ACCESSKEY, SECRETKEY))
                .withEndpoint(ENDPOINT);
        client = new IotPkiManageClient(config);
    }

    protected CreateRootCACertResponse createRootCACert() {
        String country = "CN";
        String commonName = generateRandomCommonName();
        String organization = "baidu";
        String unit = "iot";
        String emailAddress = "user@baidu.com";

        int duration = 3650;
        String clientToken = generateClientToken();

        CreateRootCACertRequest.CertRequestInfo certRequestInfo = new CreateRootCACertRequest.CertRequestInfo()
                .withOrganization(organization)
                .withCommonName(commonName)
                .withCountry(country)
                .withUnit(unit)
                .withEmailAddress(emailAddress);

        CreateRootCACertRequest request = new CreateRootCACertRequest()
                .withCertRequestInfo(certRequestInfo)
                .withDuration(duration);

        return client.createRootCACert(request, clientToken);
    }

    protected CreateCertGroupResponse createCertGroup(String rootCertId) {
        String groupName = "group";
        String clientToken = generateClientToken();

        CreateCertGroupRequest request = new CreateCertGroupRequest()
                .withRootCertId(rootCertId)
                .withGroupName(groupName);

        return client.createCertGroup(request, clientToken);
    }

    protected CreateSubCertResponse createServerCert(String groupId) {
        return createSubCert(groupId, Arrays.asList("address1", "address2"));
    }

    protected CreateSubCertResponse createClientCert(String groupId) {
        return createSubCert(groupId, null);
    }

    private String generateRandomCommonName() {
        String suffix = UUID.randomUUID().toString().substring(0, 5);
        return PKI + suffix;
    }

    protected String generateClientToken() {
        return UUID.randomUUID().toString();
    }

    protected void expectBceServiceException(final int statusCode, final String errorCode) {
        expectedException.expect(new CustomMatcher<Throwable>(
                "BceServiceException [ statusCode=" + statusCode + ", errorCode="
                        + errorCode + "]") {
            @Override
            public boolean matches(Object item) {
                return (item instanceof BceServiceException)
                        && ((BceServiceException) item).getStatusCode() == statusCode
                        && Objects.equal(((BceServiceException) item).getErrorCode(), errorCode);
            }
        });
    }

    protected CreateSubCertResponse createSubCert(String groupId, List<String> address) {
        String deviceId = "device0";
        int duration = 3650;
        String clientToken = generateClientToken();

        CreateSubCertRequest request = new CreateSubCertRequest()
                .withGroupId(groupId)
                .withDeviceId(deviceId)
                .withAddress(address)
                .withDuration(duration)
                .withCsr(csr);
        if (address == null || address.isEmpty()) {
            return client.createClientCert(request, clientToken);
        } else {
            return client.createServerCert(request, clientToken);
        }
    }

}

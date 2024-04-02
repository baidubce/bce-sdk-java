package com.baidubce.services.eiptp;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.eiptp.model.CreateEipTpRequest;
import com.baidubce.services.eiptp.model.CreateEipTpResponse;
import com.baidubce.services.eiptp.model.GetEipTpRequest;
import com.baidubce.services.eiptp.model.ListEipTpsRequest;
import com.baidubce.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;


/**
 * The unit test for the eip tp client.
 */
@Slf4j
public class EipTpClientTest {

    private static final String EIP_AK = "";
    private static final String EIP_SK = "";
    private static final String END_POINT = "";

    protected EipTpClient eipTpClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(EIP_AK, EIP_SK));
        config.setEndpoint(END_POINT);
        eipTpClient = new EipTpClient(config);
    }

    @Test
    public void createEipTpTest() {
        CreateEipTpRequest request = new CreateEipTpRequest();
        request.setReservationLength(1);
        request.setCapacity("10G");
        request.setDeductPolicy("TimeDurationPackage");
        request.setPackageType("WebOutBytes");
        CreateEipTpResponse response = eipTpClient.createEipTp(request);
        log.info(JsonUtils.toJsonString(response));
    }

    @Test
    public void getEipTpDetailTest1() {
        GetEipTpRequest request = new GetEipTpRequest();
        request.setId("");
        log.info(JsonUtils.toJsonString(eipTpClient.getEipTpDetail(request)));
    }

    @Test
    public void getEipTpDetailTest2() {
        log.info(JsonUtils.toJsonString(eipTpClient.getEipTpDetail("")));
    }

    @Test
    public void listEipTpsTest1() {
        log.info(JsonUtils.toJsonString(eipTpClient.listEipTps()));
    }

    @Test
    public void listEipTpsTest2() {
        ListEipTpsRequest request = new ListEipTpsRequest();
        request.setMaxKeys(2);
        request.setDeductPolicy("TimeDurationPackage");
        request.setStatus("RUNNING");
        log.info(JsonUtils.toJsonString(eipTpClient.listEipTps(request)));
    }
}

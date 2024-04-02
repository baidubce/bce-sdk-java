package com.baidubce.services.etgateway;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.etgateway.model.BindEtChannelRequest;
import com.baidubce.services.etgateway.model.CreateEtGatewayHealthCheckRequest;
import com.baidubce.services.etgateway.model.CreateEtGatewayRequest;
import com.baidubce.services.etgateway.model.CreateEtGatewayResponse;
import com.baidubce.services.etgateway.model.GetEtGatewayResponse;
import com.baidubce.services.etgateway.model.ListEtGatewayRequest;
import com.baidubce.services.etgateway.model.ListEtGatewayResponse;
import com.baidubce.services.etgateway.model.UnbindEtChannelRequest;
import com.baidubce.services.etgateway.model.UpdateEtGatewayRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EtGatewayClientTest {

    private static final Logger logger = LoggerFactory.getLogger(EtGatewayClientTest.class);
    private static final String AK = "";
    private static final String SK = "";

    protected EtGatewayClient etGatewayClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(AK, SK));
        config.setEndpoint("");
        etGatewayClient = new EtGatewayClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void listEtGateways() {
        ListEtGatewayRequest listEtGatewayRequest = new ListEtGatewayRequest();
        listEtGatewayRequest.setVpcId("g");
        ListEtGatewayResponse listEtGatewayResponse = etGatewayClient.listEtGateways(listEtGatewayRequest);
        toJsonPrettyString("listEtGateways: ", listEtGatewayResponse);
    }

    @Test
    public void createEtGateway() {
        CreateEtGatewayRequest createEtGatewayRequest = new CreateEtGatewayRequest();
        createEtGatewayRequest.setVpcId("g");
        createEtGatewayRequest.setName("api-create");
        createEtGatewayRequest.setSpeed(30);
        CreateEtGatewayResponse etGateway = etGatewayClient.createEtGateway(createEtGatewayRequest);
        toJsonPrettyString("createEtGateway:", etGateway);
    }

    @Test
    public void getEtGateway() {
        String etGatewayId = "";
        GetEtGatewayResponse etGateway = etGatewayClient.getEtGateway(etGatewayId);
        toJsonPrettyString("getEtGateway:", etGateway);
    }

    @Test
    public void updateEtGateway() {
        UpdateEtGatewayRequest request = new UpdateEtGatewayRequest();
        request.setEtGatewayId("");
        request.setSpeed(40);
        request.setName("api-create-update");
        request.setDescription("description-update");
        etGatewayClient.updateEtGateway(request);

    }

    @Test
    public void deleteEtGateway() {
        String etGatewayId = "";
        etGatewayClient.deleteEtGateway(etGatewayId);
    }

    @Test
    public void bindEtChannel() {
        BindEtChannelRequest bindEtChannelRequest = new BindEtChannelRequest();
        bindEtChannelRequest.setEtId("");
        bindEtChannelRequest.setChannelId("");
        bindEtChannelRequest.setEtGatewayId("");
        etGatewayClient.bindEtChannel(bindEtChannelRequest);
    }

    @Test
    public void unbindEtChannel() {
        UnbindEtChannelRequest unbindEtChannelRequest = new UnbindEtChannelRequest();
        unbindEtChannelRequest.setEtGatewayId("");
        etGatewayClient.unbindEtChannel(unbindEtChannelRequest);
    }

    @Test
    public void createEtGatewayHealthCheck() {
        CreateEtGatewayHealthCheckRequest request = new CreateEtGatewayHealthCheckRequest();
        request.setEtGatewayId("");
        request.setHealthCheckInterval(40);
        request.setHealthThreshold(2);
        request.setUnhealthThreshold(4);
        etGatewayClient.createEtGatewayHealthCheck(request);
    }
}

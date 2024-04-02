package com.baidubce.services.localdns;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.localdns.model.AddRecordRequest;
import com.baidubce.services.localdns.model.BindVpcRequest;
import com.baidubce.services.localdns.model.CreatePrivateZoneRequest;
import com.baidubce.services.localdns.model.UnbindVpcRequest;
import com.baidubce.services.localdns.model.UpdateRecordRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


/**
 * LdClient test
 *
 * @author ccq
 * @since 2023/7/10
 */
public class LdClientTest {

    private static final Logger logger = LoggerFactory.getLogger(LdClientTest.class);
    private static final String ak = "3251d60a16f94c839f7aa4b87ed4913b";
    private static final String sk = "21d8c3cdfe1242148e29465fca734e1f";
    private LdClient ldClient;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ak, sk));
        config.setEndpoint("bcc.bj.qasandbox.baidu-int.com");
        ldClient = new LdClient(config);
    }

    public void toJsonPrettyString(String method, Object object) {
        try {
            logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createZoneTest() {
        CreatePrivateZoneRequest createZoneRequest = new CreatePrivateZoneRequest();
        createZoneRequest.setZoneName("javaSdk.com");
        ldClient.createPrivateZone(createZoneRequest, "");
    }

    @Test
    public void listZoneTest() {
        toJsonPrettyString("list ld result", ldClient.listPrivateZone("", 1000));
    }

    @Test
    public void deleteZoneTest() {
        ldClient.deletePrivateZone("zone-a15wns86jnff", null);
    }

    @Test
    public void getZoneTest() {
        toJsonPrettyString("list ld result", ldClient.getPrivateZone("zone-eej8ps6qp5ic"));
    }

    @Test
    public void bindVpcTest() {
        BindVpcRequest bindVpcRequest = new BindVpcRequest();
        bindVpcRequest.setRegion("bj");
        bindVpcRequest.setVpcIds(Arrays.asList("vpc-4kzjwxgvx4fi"));
        ldClient.bindVpc("zone-nqa0uqyse51z", bindVpcRequest, "");
    }

    @Test
    public void unbindVpcTest() {
        UnbindVpcRequest unbindVpcRequest = new UnbindVpcRequest();
        unbindVpcRequest.setRegion("bj");
        unbindVpcRequest.setVpcIds(Arrays.asList("vpc-4kzjwxgvx4fi"));
        ldClient.unbindVpc("zone-nqa0uqyse51z", unbindVpcRequest, "");
    }

    @Test
    public void addRecordTest() {
        AddRecordRequest addRecordRequest = new AddRecordRequest();
        addRecordRequest.setRr("www");
        addRecordRequest.setType("A");
        addRecordRequest.setValue("1.1.1.1");
        ldClient.addRecord("zone-nqa0uqyse51z", addRecordRequest, "");
    }

    @Test
    public void updateRecordTest() {
        UpdateRecordRequest updateRecordRequest = new UpdateRecordRequest();
        updateRecordRequest.setRr("www");
        updateRecordRequest.setType("A");
        updateRecordRequest.setValue("1.1.1.2");
        ldClient.updateRecord("rc-byx2936gep0s", updateRecordRequest, "");
    }

    @Test
    public void enableRecordTest() {
        ldClient.enableRecord("rc-byx2936gep0s", "");
    }

    @Test
    public void disableRecordTest() {
        ldClient.disableRecord("rc-byx2936gep0s", "");
    }

    @Test
    public void listRecordTest() {
        toJsonPrettyString("list record result", ldClient.listRecord("zone-nqa0uqyse51z"));
    }

    @Test
    public void deleteRecordTest() {
        ldClient.deleteRecord("rc-byx2936gep0s", "");
    }









}

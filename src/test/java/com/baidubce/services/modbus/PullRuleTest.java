package com.baidubce.services.modbus;

import com.baidubce.services.modbus.model.pullrule.CreatePullRuleRequest;
import com.baidubce.services.modbus.model.pullrule.CreatePullRuleResponse;
import com.baidubce.services.modbus.model.pullrule.ListPullRuleRequest;
import com.baidubce.services.modbus.model.pullrule.ListPullRuleResponse;
import com.baidubce.services.modbus.model.pullrule.PullRuleResponse;
import com.baidubce.services.modbus.model.pullrule.PullRuleResponseWithDevice;
import com.baidubce.services.modbus.model.pullrule.UpdatePullRuleRequest;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PullRuleTest extends ModbusClientTest {

    @Test
    public void createPullRule() throws Exception {
        CreatePullRuleRequest request = new CreatePullRuleRequest();
        request.setParserObjectUuid("de803947-f5ed-4875-afca-7f913bb89560");
        request.setDeviceUuids(Arrays.asList("8b56d72e-e255-4182-aaaa-e0c16b4ca462",
                                                "85729854-8040-44e6-8a9f-9249ebbc89a8"));
        request.setPullInterval(689);
        request.setLength(65);
        request.setFunctionCode(2);
        request.setStartAddress(125);
        CreatePullRuleResponse response = modbusClient.createPullRule(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void listPullRule() throws Exception {
        ListPullRuleRequest request = new ListPullRuleRequest();
        request.setWithDevice(true);
        ListPullRuleResponse response = modbusClient.listPullRule(request);
        assertNotNull(response.getResult().get(0).getDevice());
        System.out.println(mapper.writeValueAsString(response));
        assertEquals(11, response.getTotalCount());
        request.setParserObjectUuid("de803947-f5ed-4875-afca-7f913bb89560");
        ListPullRuleResponse response2 = modbusClient.listPullRule(request);
        assertEquals(10, response2.getTotalCount());
    }

    @Test
    public void getPullRule() throws Exception {
        PullRuleResponseWithDevice response = modbusClient.getPullRule("8532b090-3d1c-4963-b358-079ddabb1b51");
        System.out.println(mapper.writeValueAsString(response));
        assertEquals(677, response.getPullInterval());
    }

    @Test
    public void updatePullRule() throws Exception {
        PullRuleResponseWithDevice response = modbusClient.getPullRule("8532b090-3d1c-4963-b358-079ddabb1b51");
        System.out.println(mapper.writeValueAsString(response));
        assertEquals(668, response.getPullInterval());
        UpdatePullRuleRequest request = new UpdatePullRuleRequest();
        request.setPullInterval(669);
        PullRuleResponse updateResponse = modbusClient.updatePullRule("8532b090-3d1c-4963-b358-079ddabb1b51", request);
        assertEquals(669, updateResponse.getPullInterval());
    }

    @Test
    public void deletePullRule() throws Exception {
        ListPullRuleRequest request = new ListPullRuleRequest();
        ListPullRuleResponse response1 = modbusClient.listPullRule(request);
        assertEquals(4, response1.getTotalCount());
        modbusClient.deletePullRule(response1.getResult().get(0).getUuid());
        ListPullRuleResponse response2 = modbusClient.listPullRule(request);
        assertEquals(3, response2.getTotalCount());
    }

}

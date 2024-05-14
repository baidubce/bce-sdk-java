/*
 * Copyright 2016 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidubce.services.modbus;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.modbus.model.BaseResponse;
import com.baidubce.services.modbus.model.CreateDataDescRequest;
import com.baidubce.services.modbus.model.DataDescRequest;
import com.baidubce.services.modbus.model.ListCustomFieldRespons;
import com.baidubce.services.modbus.model.ListDataDescResponse;
import com.baidubce.services.modbus.model.ListParserObjectResponse;
import com.baidubce.services.modbus.model.ParserObjectRequest;
import com.baidubce.services.modbus.model.QueryDataDescResponse;
import com.baidubce.services.modbus.model.UpdateDataDescRequest;
import com.baidubce.services.modbus.model.device.CreateDevice;
import com.baidubce.services.modbus.model.device.DeviceResponse;
import com.baidubce.services.modbus.model.gateway.CreateGatewayRequest;
import com.baidubce.services.modbus.model.gateway.GatewayResponse;
import com.baidubce.services.modbus.model.gateway.ListGatewayRequest;
import com.baidubce.services.modbus.model.gateway.ListGatewayResponse;
import com.baidubce.services.modbus.model.gateway.UpdateGatewayRequest;
import com.baidubce.services.modbus.model.parserobject.CreateParserObjectRequest;
import com.baidubce.services.modbus.model.parserobject.ParserObjectResponse;
import com.baidubce.services.modbus.model.pullrule.CreatePullRuleRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for modbus client.
 */
public class ModbusClientTest  {
    // qa-sandbox
//    private static final String AK = "";
//    private static final String SK = "";
//    private static final String ENDPOINT = "http://nmg01-hpc-w1134.nmg01.baidu.com:8008";

    // online
    private static final String AK = "";
    private static final String SK = "";
    private static final String ENDPOINT = "http://parser.iot.gz.baidubce.com";
    public ModbusClient modbusClient;
    public ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        modbusClient = new ModbusClient(config);
    }

    @Test
    public void dataDescTest() throws Exception {
        CreateDataDescRequest createDataDescRequest = (new CreateDataDescRequest())
                .withName("hl2")
                .withLength(32)
                .withAddress(310)
                .withKind("REAL")
                .withParserObjectUuid("33eb4537-9d2c-40d2-8967-c9eaaf267419");
        QueryDataDescResponse createDataDescResponse = modbusClient.createDataDesc(createDataDescRequest);
        DataDescRequest dataDescRequest = (new DataDescRequest())
                .withParserObjectUuid("33eb4537-9d2c-40d2-8967-c9eaaf267419")
                .withState("ENABLED");
        ListDataDescResponse listDataDescResponse = modbusClient.listDataDesc(dataDescRequest);
        assertThat(listDataDescResponse, hasProperty("result"));

        String dataDescUuid = listDataDescResponse.getResult().get(0).getUuid();
        UpdateDataDescRequest updateDataDescRequest = (new UpdateDataDescRequest())
                .withDataDescriptionUuid(dataDescUuid)
                .withLength(8);
        QueryDataDescResponse queryDataDescResponse = modbusClient.updateDataDesc(updateDataDescRequest);
        queryDataDescResponse = modbusClient.queryDataDesc(dataDescUuid);
        assertThat(queryDataDescResponse.getLength(), is(8));

        AbstractBceResponse response = modbusClient.deleteDataDesc("2fba14db-96e4-4163-a4eb-8bf37132c910");
    }

    @Test
    public void parserObjTest() throws Exception {
        ParserObjectRequest parserObjectRequest = (new ParserObjectRequest())
                .withState("RUNNING");
        ListParserObjectResponse listParserObjectResponse = modbusClient.listParserObject(parserObjectRequest);
        System.out.println(mapper.writeValueAsString(listParserObjectResponse));
        assertThat(listParserObjectResponse, hasProperty("result"));
    }

    @Test
    public void customFieldTest() throws Exception {
        BaseResponse response = modbusClient.createCustomField("hl");
        assertThat(response.getSuccess(), is("true"));
        ListCustomFieldRespons listResponse = modbusClient.listCustomField();
        assertThat(listResponse.getFields().isEmpty(), is(false));
        response = modbusClient.deleteCustomField("hl");
        assertThat(response.getSuccess(), is("true"));
        listResponse = modbusClient.listCustomField();
        assertThat(listResponse.getFields().isEmpty(), is(true));
    }

    @Test
    public void listGateway() throws Exception {
        ListGatewayRequest request = new ListGatewayRequest();
        ListGatewayResponse response = modbusClient.listGateway(request);
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void createGateway() throws Exception {
        CreateGatewayRequest request = new CreateGatewayRequest();
        request.setCode("ldwtest32");
        request.setDescription("ceshi");
        request.setUseSsl(true);
        GatewayResponse response = modbusClient.createGateway(request);
        assertTrue(response.isUseSsl());
        assertNotEquals(null, response.getBackControlTopic());
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void deleteGateway() throws Exception {
        modbusClient.deleteGateway("6cfd3c58-6311-41d8-bb41-84eed80b924f");
        ListGatewayRequest request = new ListGatewayRequest();
        ListGatewayResponse response = modbusClient.listGateway(request);
        assertEquals(3, response.getTotalCount());
    }

    @Test
    public void getGateway() throws Exception {
        GatewayResponse response = modbusClient.getGateway("acdfb029-5cca-4e74-bb51-707355e9c12f");
        assertEquals(2, response.getDeviceNum());
    }

    @Test
    public void updateGateway() throws Exception {
        UpdateGatewayRequest request = new UpdateGatewayRequest();
        request.setDescription("testsdk");
        GatewayResponse response = modbusClient.updateGateway("acdfb029-5cca-4e74-bb51-707355e9c12f", request);
        assertEquals(2, response.getDeviceNum());
        assertTrue(response.isUseSsl());
    }

    @Test
    public void regenGatewayPassword() throws Exception {
        GatewayResponse response1 = modbusClient.getGateway("acdfb029-5cca-4e74-bb51-707355e9c12f");
        GatewayResponse response2 = modbusClient.regenGatewayPassword("acdfb029-5cca-4e74-bb51-707355e9c12f");
        assertEquals(2, response1.getDeviceNum());
        assertEquals(2, response2.getDeviceNum());
        assertNotEquals(response1.getPassword(), response2.getPassword());
        assertTrue(response1.isUseSsl());
        System.out.println(response1.getPassword());
        System.out.println(response2.getPassword());
    }



    // 创建物解析整个项目
    @Test
    public void happyPass() throws Exception {
        // 1、创建网关 返回的结果中包含了mqtt的用户名密码等信息
        CreateGatewayRequest createGatewayRequest = new CreateGatewayRequest();
        createGatewayRequest.setCode("sdkTestGateway1");
        createGatewayRequest.setDescription("gateway");
        createGatewayRequest.setUseSsl(false);
        GatewayResponse gatewayResponse = modbusClient.createGateway(createGatewayRequest);

        // 2、创建属于该网关的子设备
        CreateDevice createDevice = new CreateDevice();
        createDevice.setGatewayUuid(gatewayResponse.getUuid());
        createDevice.setCode("sdkTestDevice1");
        createDevice.setSlaveId(12);
        createDevice.setDescription("test");
        createDevice.setAddress("1.2.3.4:502"); // ip:port
        createDevice.setMode("TCP");
        DeviceResponse deviceResponse = modbusClient.createDevice(createDevice);

        // 3、创建解析项目，绑定该网关
        CreateParserObjectRequest parserObjectRequest = new CreateParserObjectRequest();
        parserObjectRequest.setGatewayUuid(gatewayResponse.getUuid());
        parserObjectRequest.setName("sdkTestParserObject1");
        parserObjectRequest.setProtocol("MODBUS");
        parserObjectRequest.setStorage("bos://ldwldw-test/"); // 存储到bos，注意格式
        parserObjectRequest.setDestTopic("testTopic");
        ParserObjectResponse parserObjectResponse = modbusClient.createParserObject(parserObjectRequest);

        // 4、在该解析项目下面，针对该device创建一条轮询规则
        CreatePullRuleRequest createPullRuleRequest = new CreatePullRuleRequest();
        createPullRuleRequest.setParserObjectUuid(parserObjectResponse.getUuid());
        createPullRuleRequest.setDeviceUuids(Arrays.asList(deviceResponse.getUuid())); // 可以批量创建
        createPullRuleRequest.setStartAddress(12);
        createPullRuleRequest.setFunctionCode(2);
        createPullRuleRequest.setLength(200);
        createPullRuleRequest.setPullInterval(800);
        modbusClient.createPullRule(createPullRuleRequest);

        // 5、为解析项目添加一条通讯地址表
        CreateDataDescRequest createDataDescRequest = new CreateDataDescRequest();
        createDataDescRequest.setParserObjectUuid(parserObjectResponse.getUuid());
        createDataDescRequest.setAddress(40010);
        createDataDescRequest.setName("testDataDes1");
        createDataDescRequest.setKind("INT");
        createDataDescRequest.setLength(16);
        modbusClient.createDataDesc(createDataDescRequest);

        // 6、下发配置
        modbusClient.deployGateway();
    }

    // 禁用启用网关(网关状态改变需要下发配置生效)
    @Test
    public void enableAndDisableGateway() throws Exception {
        // 禁用网关
        String uuid = "acdfb029-5cca-4e74-bb51-707355e9c12f"; // 已存在的网关uuid
        UpdateGatewayRequest updateGatewayRequest = new UpdateGatewayRequest();
        updateGatewayRequest.setState("DISABLED");
        modbusClient.updateGateway(uuid, updateGatewayRequest);
        modbusClient.deployGateway();

        // 启用网关
        updateGatewayRequest.setState("ENABLED");
        modbusClient.updateGateway(uuid, updateGatewayRequest);
        modbusClient.deployGateway();
    }

    @Test
    public void deploySingleGateway() {
        // 1、创建网关 返回的结果中包含了mqtt的用户名密码等信息
        CreateGatewayRequest createGatewayRequest = new CreateGatewayRequest();
        createGatewayRequest.setCode("sdkTestGateway2");
        createGatewayRequest.setDescription("gateway create by sdk test case");
        createGatewayRequest.setUseSsl(false);
        GatewayResponse gatewayResponse = modbusClient.createGateway(createGatewayRequest);
        modbusClient.deployGateway(gatewayResponse.getUuid());
    }

}

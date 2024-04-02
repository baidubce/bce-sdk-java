/*
 * Copyright 2016 Baidu
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
import com.baidubce.services.modbus.model.BaseResponse;
import com.baidubce.services.modbus.model.CreateDataDescRequest;
import com.baidubce.services.modbus.model.DataDescRequest;
import com.baidubce.services.modbus.model.DataDescription;
import com.baidubce.services.modbus.model.ListCustomFieldRespons;
import com.baidubce.services.modbus.model.ListDataDescResponse;
import com.baidubce.services.modbus.model.ListParserObjectResponse;
import com.baidubce.services.modbus.model.ParserObject;
import com.baidubce.services.modbus.model.ParserObjectRequest;
import com.baidubce.services.modbus.model.QueryDataDescResponse;
import com.baidubce.services.modbus.model.UpdateDataDescRequest;
import com.baidubce.services.modbus.model.device.CreateDevice;
import com.baidubce.services.modbus.model.device.DeviceResponse;
import com.baidubce.services.modbus.model.gateway.CreateGatewayRequest;
import com.baidubce.services.modbus.model.gateway.GatewayResponse;
import com.baidubce.services.modbus.model.gateway.UpdateGatewayRequest;
import com.baidubce.services.modbus.model.parserobject.CreateParserObjectRequest;
import com.baidubce.services.modbus.model.parserobject.ParserObjectResponse;
import com.baidubce.services.modbus.model.pullrule.CreatePullRuleRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Sample for modbus client.
 */
public class ModBusClientSample {

    // 用户的AK、SK
    private static final String AK = "/*用户AK*/";
    private static final String SK = "/*用户SK*/";
    private static final String ENDPOINT = "http://parser.iot.gz.baidubce.com";


    private ModbusClient modbusClient;

    @Before
    public void setUp() {
        // 初始化配置，得到client
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(AK, SK))
                .withEndpoint(ENDPOINT);
        modbusClient = new ModbusClient(config);
    }

    // 创建物解析整个项目
    @Test
    public void happyPass() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // 1、创建网关 返回的结果中包含了mqtt的用户名密码等信息
        CreateGatewayRequest createGatewayRequest = new CreateGatewayRequest();
        createGatewayRequest.setCode("sdkTestGateway");
        createGatewayRequest.setDescription("gateway");
        createGatewayRequest.setUseSsl(false);
        GatewayResponse gatewayResponse = modbusClient.createGateway(createGatewayRequest);
        System.out.println(mapper.writeValueAsString(gatewayResponse));

        // 2、创建属于该网关的子设备
        CreateDevice createDevice = new CreateDevice();
        createDevice.setGatewayUuid(gatewayResponse.getUuid());
        createDevice.setCode("sdkTestDevice");
        createDevice.setSlaveId(12);
        createDevice.setDescription("test");
        createDevice.setAddress("1.2.3.4:502"); // ip:port
        createDevice.setMode("TCP");
        DeviceResponse deviceResponse = modbusClient.createDevice(createDevice);

        // 3、创建解析项目，绑定该网关
        CreateParserObjectRequest parserObjectRequest = new CreateParserObjectRequest();
        parserObjectRequest.setGatewayUuid(gatewayResponse.getUuid());
        parserObjectRequest.setName("sdkTestParserObject");
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
        createDataDescRequest.setName("testDataDes");
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
        GatewayResponse response1 = modbusClient.updateGateway(uuid, updateGatewayRequest);
        System.out.println(response1.getState());
        modbusClient.deployGateway();

        // 启用网关
        updateGatewayRequest.setState("ENABLED");
        GatewayResponse response2 = modbusClient.updateGateway(uuid, updateGatewayRequest);
        System.out.println(response2.getState());
        modbusClient.deployGateway();
    }

    // 重置网关的mqtt密钥
    @Test
    public void regenGatewayPassword() throws Exception {
        String uuid = "xxxx"; // 已存在的网关uuid
        GatewayResponse getResponse = modbusClient.getGateway(uuid);
        System.out.println(getResponse.getPassword());
        GatewayResponse gatewayResponse = modbusClient.regenGatewayPassword(uuid);
        System.out.println(gatewayResponse.getPassword());
        // 可以观察前后两次的密码
    }

    @Test
    public void sample() throws Exception {

        // 1，展示如何添加、获取、删除自定义属性；
        String propertyName = "testName";
        BaseResponse response = modbusClient.createCustomField(propertyName);
        assertThat(response.getSuccess(), is("true"));

        ListCustomFieldRespons listResponse = modbusClient.listCustomField();
        assertThat(listResponse.getFields().isEmpty(), is(false));

        response = modbusClient.deleteCustomField("propertyName");
        assertThat(response.getSuccess(), is("true"));

        listResponse = modbusClient.listCustomField();
        assertThat(listResponse.getFields().isEmpty(), is(true));


        // 2，展示如何根据解析项目的名称，获取解析项目id；
        String parserObjName = "TEST";
        String parserObjUuid = null;
        ParserObjectRequest parserObjectRequest = (new ParserObjectRequest())
                .withState("RUNNING");
        ListParserObjectResponse listParserObjectResponse = modbusClient.listParserObject(parserObjectRequest);
        for (ParserObject po : listParserObjectResponse.getResult()) {
            if (po.getName().equals(parserObjName)) {
                parserObjUuid = po.getUuid();
                break;
            }
        }
        System.out.println(parserObjUuid);

        // 3，展示根据解析项目的id，获取所有的点表；
        List<DataDescription> dataDescriptions = new ArrayList<DataDescription>();
        DataDescRequest dataDescRequest = (new DataDescRequest())
                .withParserObjectUuid(parserObjUuid)
                .withState("ENABLED");
        ListDataDescResponse listDataDescResponse = modbusClient.listDataDesc(dataDescRequest);
        dataDescriptions = listDataDescResponse.getResult();
        System.out.println(dataDescriptions);

        // 4，展示在创建点表的时候，指定用户自定义属性；
        modbusClient.createCustomField(propertyName); // 先创建用户属性key
        HashMap<String, String> userProperty = new HashMap<String, String>();
        String propertyValue = "testValue";
        userProperty.put(propertyName, propertyValue);
        CreateDataDescRequest createDataDescRequest = (new CreateDataDescRequest())
                .withUserProperties(userProperty)
                .withName("testDD2")    // 创建时这些属性必须加上
                .withLength(32)   // 创建时这些属性必须加上
                .withAddress(00310)   // 创建时这些属性必须加上
                .withKind("REAL")   // 创建时这些属性必须加上
                .withParserObjectUuid(parserObjUuid);   // 创建时这些属性必须加上
        QueryDataDescResponse createDataDescResponse = modbusClient.createDataDesc(createDataDescRequest);
        System.out.println(createDataDescResponse.getUserProperties());

        // 5，展示在修改点表的时候，指定用户自定义属性；
        HashMap<String, String> newUserProperty = new HashMap<String, String>();
        String newPropertyValue = "newTestValue";
        newUserProperty.put(propertyName, newPropertyValue);
        String dataDescUuid = createDataDescResponse.getUuid();
        UpdateDataDescRequest updateDataDescRequest = (new UpdateDataDescRequest())
                .withDataDescriptionUuid(dataDescUuid)
                .withUserProperties(newUserProperty);
        QueryDataDescResponse queryDataDescResponse = modbusClient.updateDataDesc(updateDataDescRequest);
        System.out.println(createDataDescResponse.getUserProperties());

        // 6，展示如何访问点表中的自定义属性；
        queryDataDescResponse = modbusClient.queryDataDesc(dataDescUuid);
        System.out.println(queryDataDescResponse.getUserProperties());

        // 7, 删除点表;
        modbusClient.deleteDataDesc(dataDescUuid);
    }

}

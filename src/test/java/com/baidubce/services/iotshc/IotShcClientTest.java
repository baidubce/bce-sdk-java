/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotshc;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iotshc.model.CommonListRequest;
import com.baidubce.services.iotshc.model.CommonResponse;
import com.baidubce.services.iotshc.model.CommonResult;
import com.baidubce.services.iotshc.model.ai.TtsServiceRequest;
import com.baidubce.services.iotshc.model.ai.TtsServiceResponse;
import com.baidubce.services.iotshc.model.ai.UnitServiceRequest;
import com.baidubce.services.iotshc.model.ai.UnitServiceResponse;
import com.baidubce.services.iotshc.model.deivce.DeviceInfo;
import com.baidubce.services.iotshc.model.deivce.DeviceInfoListRequest;
import com.baidubce.services.iotshc.model.deivce.DeviceInfoListResponse;
import com.baidubce.services.iotshc.model.deivce.ImportBatchDevicesRequest;
import com.baidubce.services.iotshc.model.factory.FactoryInfo;
import com.baidubce.services.iotshc.model.factory.FactoryInfoListResponse;
import com.baidubce.services.iotshc.model.factory.UpdateFactoryInfoRequest;
import com.baidubce.services.iotshc.model.mqtt.GetMqttInfoRequest;
import com.baidubce.services.iotshc.model.mqtt.GetMqttInfoResponse;
import com.baidubce.services.iotshc.model.mqtt.SendMessageRequest;
import com.baidubce.services.iotshc.model.product.ProductKeyInfo;
import com.baidubce.services.iotshc.model.product.ProductKeyInfoListRequest;
import com.baidubce.services.iotshc.model.product.ProductKeyInfoListResponse;
import com.baidubce.services.iotshc.model.product.ProductKeyTypeListResponse;
import com.baidubce.services.iotshc.model.product.UpdateProductKeyInfoRequest;
import com.baidubce.services.iotshc.model.token.GetTokenResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

/**
 * unit test for iot shc client
 */
public class IotShcClientTest {

    // qa-sandbox
    private static final String Your_AK = "";
    private static final String Your_SK = "";
    private static final String ENDPOINT = "https://smarthome.baidubce.com";
    private static final String TEST_ACCOUNT = "611f366b29f64d04ad2ed130d32835d2";
    private static final String TEST_FC = "43n60h";
    private static final String TEST_PK = "xn0msd5q";
    private static final String TEST_DEVICE_AK = "mock-ak";
    private static final String TEST_DEVICE_SK = "mock-sk";

    private IotShcClient client;

    @Before
    public void setUp() {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(Your_AK, Your_SK))
                .withEndpoint(ENDPOINT);
        client = new IotShcClient(config);
    }

    @Test
    @Ignore
    public void deleteFactoryInfoTest() {
        String fc = "mock-fc";
        String exp = "OK";
        CommonResult result = client.deleteFactoryInfo(fc);

        Assert.assertEquals(exp, result.getResult());
    }

    @Test
    @Ignore
    public void updateFactoryInfoTest() {
        FactoryInfo factoryInfo;
        UpdateFactoryInfoRequest request = new UpdateFactoryInfoRequest();

        request.setName("name1");
        request.setDescription("description1");
        factoryInfo = client.updateFactoryInfo(TEST_FC, request);
        Assert.assertEquals(request.getName(), factoryInfo.getName());
        Assert.assertEquals(request.getDescription(), factoryInfo.getDescription());

        request.setName("name2");
        request.setDescription("description2");
        factoryInfo = client.updateFactoryInfo(TEST_FC, request);
        Assert.assertEquals(request.getName(), factoryInfo.getName());
        Assert.assertEquals(request.getDescription(), factoryInfo.getDescription());
    }

    @Test
    @Ignore
    public void getFactoryInfoTest() {
        FactoryInfo factoryInfo = client.getFactoryInfo(TEST_FC);
        Assert.assertEquals(TEST_ACCOUNT, factoryInfo.getAccountUuid());
    }

    @Test
    @Ignore
    public void listFactoryInfoTest() {
        CommonListRequest request = new CommonListRequest();
        request.setPageNo(1);
        request.setPageSize(2);
        FactoryInfoListResponse response = client.listFactoryInfo(request);
        Assert.assertEquals(request.getPageNo(), response.getPageNo());
        Assert.assertEquals(request.getPageSize(), response.getPageSize());
        if (response.getTotalCount() > 0) {
            Assert.assertEquals(true, !response.getResult().isEmpty());
        }
    }

    @Test
    @Ignore
    public void deleteProductKeyTest() {
        String fc = "mock-fc";
        String pk = "mock-pk";
        String exp = "OK";
        CommonResult result = client.deleteProductKey(fc, pk);

        Assert.assertEquals(exp, result.getResult());
    }

    @Test
    @Ignore
    public void updateProductKeyInfoTest() {
        ProductKeyInfo productKeyInfo;
        UpdateProductKeyInfoRequest request = new UpdateProductKeyInfoRequest();

        request.setDescription("description1");
        productKeyInfo = client.updateProductKeyInfo(TEST_FC, TEST_PK, request);
        Assert.assertEquals(request.getDescription(), productKeyInfo.getDescription());

        request.setDescription("description2");
        productKeyInfo = client.updateProductKeyInfo(TEST_FC, TEST_PK, request);
        Assert.assertEquals(request.getDescription(), productKeyInfo.getDescription());
    }

    @Test
    @Ignore
    public void getProductKeyInfoTest() {
        ProductKeyInfo productKeyInfo = client.getProductKeyInfo(TEST_FC, TEST_PK);
        Assert.assertEquals(TEST_ACCOUNT, productKeyInfo.getAccountUuid());
    }

    @Test
    @Ignore
    public void listProductKeyInfoTest() {
        ProductKeyInfoListRequest request = new ProductKeyInfoListRequest();
        request.setFc(TEST_FC);
        request.setPageNo(1);
        request.setPageSize(2);

        ProductKeyInfoListResponse response = client.listProductKeyInfo(request);
        Assert.assertEquals(request.getFc(), response.getFc());
        Assert.assertEquals(request.getPageNo(), response.getPageNo());
        Assert.assertEquals(request.getPageSize(), response.getPageSize());
        if (response.getTotalCount() > 0) {
            Assert.assertEquals(true, !response.getResult().isEmpty());
        }
    }

    @Test
    @Ignore
    public void listProductKeyTypeTest() {
        CommonListRequest request = new CommonListRequest();
        request.setPageNo(1);
        request.setPageSize(20);

        ProductKeyTypeListResponse response = client.listProductKeyType(request);
        Assert.assertEquals(request.getPageNo(), response.getPageNo());
        Assert.assertEquals(request.getPageSize(), response.getPageSize());
        if (response.getTotalCount() > 0) {
            Assert.assertEquals(true, !response.getResult().isEmpty());
        }
    }

    @Test
    @Ignore
    public void importBatchDevicesTest() {
        ImportBatchDevicesRequest request = new ImportBatchDevicesRequest(
                TEST_FC, TEST_PK,
                Arrays.asList("device-ak,device-sk"));

        CommonResponse response = client.importBatchDevices(request);
        Assert.assertEquals(0, response.getCode());
        Assert.assertEquals(true, StringUtils.isEmpty(response.getMessage()));
    }

    @Test
    @Ignore
    public void deleteDeviceInfoTest() {
        String deviceAk = "device-ak";

        CommonResult result = client.deleteDeviceInfo(TEST_FC, TEST_PK, deviceAk);
        Assert.assertEquals(true, result.getResult().equals("OK"));
    }

    @Test
    @Ignore
    public void deleteDevicesTest() {
        String pk = "mock-pk";

        CommonResult result = client.deleteDevices(TEST_FC, pk);
        Assert.assertEquals(true, result.getResult().equals("OK"));
    }

    @Test
    @Ignore
    public void banDeviceTest() {
        CommonResult result = client.banDevice(TEST_FC, TEST_PK, TEST_DEVICE_AK);
        Assert.assertEquals(true, result.getResult().equals("OK"));

        DeviceInfo deviceInfo = client.getDeviceInfo(TEST_FC, TEST_PK, TEST_DEVICE_AK);
        Assert.assertEquals(true, deviceInfo.getState().equals("BAN"));
    }

    @Test
    @Ignore
    public void activeDeviceTest() {
        CommonResult result = client.activeDevice(TEST_FC, TEST_PK, TEST_DEVICE_AK);
        Assert.assertEquals(true, result.getResult().equals("OK"));

        DeviceInfo deviceInfo = client.getDeviceInfo(TEST_FC, TEST_PK, TEST_DEVICE_AK);
        Assert.assertEquals(true, deviceInfo.getState().contains("ACTIVE"));
    }

    @Test
    @Ignore
    public void getDeviceInfoTest() {
        DeviceInfo deviceInfo = client.getDeviceInfo(TEST_FC, TEST_PK, TEST_DEVICE_AK);
        Assert.assertEquals(TEST_ACCOUNT, deviceInfo.getAccountUuid());
    }

    @Test
    @Ignore
    public void listDeviceInfoTest() {
        DeviceInfoListRequest request = new DeviceInfoListRequest(TEST_FC, TEST_PK);
        request.setPageNo(1);
        request.setPageSize(2);
        request.setState("NOT_ACTIVE");
        request.setOrder("desc");

        DeviceInfoListResponse response = client.listDeviceInfo(request);
        Assert.assertEquals(request.getFc(), response.getFc());
        Assert.assertEquals(true, StringUtils.isNotEmpty(response.getFcName()));
        Assert.assertEquals(request.getPk(), response.getPk());
        Assert.assertEquals(true, StringUtils.isNotEmpty(response.getDevSku()));
        Assert.assertEquals(request.getPageNo(), response.getPageNo());
        Assert.assertEquals(request.getPageSize(), response.getPageSize());
        Assert.assertEquals(request.getOrder(), response.getOrder());
        Assert.assertEquals(request.getState(), response.getState());
        if (response.getTotalCount() > 0) {
            Assert.assertEquals(true, !response.getResult().isEmpty());
        }
    }

    @Test
    @Ignore
    public void getTokenTest() {
        int tokenLifeSpanInDays = 30;

        GetTokenResponse response = client.getToken(tokenLifeSpanInDays);
        Assert.assertTrue(StringUtils.isNotEmpty(response.getToken()));
    }

    @Test
    @Ignore
    public void getMqttInfoTest() {
        GetMqttInfoRequest request = new GetMqttInfoRequest(
                TEST_FC, TEST_PK, TEST_DEVICE_AK, TEST_DEVICE_SK);
        GetMqttInfoResponse response = client.getMqttInfo(request);

        Assert.assertEquals(true, StringUtils.isNotEmpty(response.getBroker()));
        Assert.assertEquals(true, StringUtils.isNotEmpty(response.getClientID()));
        Assert.assertEquals(true, StringUtils.isNotEmpty(response.getUser()));
        Assert.assertEquals(true, StringUtils.isNotEmpty(response.getPass()));
    }

    @Test
    @Ignore
    public void sendMqttMessageTest() {
        String message = "mock-message";
        SendMessageRequest request = new SendMessageRequest();
        request.setFc(TEST_FC);
        request.setPk(TEST_PK);
        request.setAk(TEST_DEVICE_AK);
        request.setMessage(message);

        CommonResponse response = client.sendMqttMessage(request);
        Assert.assertEquals(0, response.getCode());
        Assert.assertEquals(true, StringUtils.isEmpty(response.getMessage()));
    }

    @Test
    @Ignore
    public void broadcastMqttMessageTest() {
        String message = "mock-message";
        SendMessageRequest request = new SendMessageRequest();
        request.setFc(TEST_FC);
        request.setPk(TEST_PK);
        request.setMessage(message);

        CommonResponse response = client.broadcastMqttMessage(request);
        Assert.assertEquals(0, response.getCode());
        Assert.assertEquals(true, StringUtils.isEmpty(response.getMessage()));
    }

    @Test
    @Ignore
    public void sendTtsMessageTest() {
        String message = "mock-message";
        SendMessageRequest request = new SendMessageRequest();
        request.setFc(TEST_FC);
        request.setPk(TEST_PK);
        request.setAk(TEST_DEVICE_AK);
        request.setMessage(message);
        request.setTranNum(102);

        CommonResponse response = client.sendTtsMessage(request);
        Assert.assertEquals(0, response.getCode());
        Assert.assertEquals(true, StringUtils.isEmpty(response.getMessage()));
    }

    @Test
    @Ignore
    public void broadcastTtsMessageTest() {
        String message = "mock-message";
        SendMessageRequest request = new SendMessageRequest();
        request.setFc(TEST_FC);
        request.setPk(TEST_PK);
        request.setMessage(message);
        request.setTranNum(102);

        CommonResponse response = client.broadcastTtsMessage(request);
        Assert.assertEquals(0, response.getCode());
        Assert.assertEquals(true, StringUtils.isEmpty(response.getMessage()));
    }

    @Test
    @Ignore
    public void invokeUnitTest() {
        String query = "今天的天气";
        UnitServiceRequest request = new UnitServiceRequest(
                TEST_FC, TEST_PK, TEST_DEVICE_AK, query);
        request.setDetail(true);
        UnitServiceResponse response = client.invokeUnit(request);

        Assert.assertEquals(true, !response.getContent().getQuery().isEmpty());
        Assert.assertEquals(query, response.getContent().getQuery().get(0));
        Assert.assertEquals(true, StringUtils.isNotEmpty(response.getContent().getDetail()));
    }

    @Test
    @Ignore
    public void invokeTtsTest() {
        String test = "合成语音测试";
        TtsServiceRequest request = new TtsServiceRequest(
                TEST_FC, TEST_PK, TEST_DEVICE_AK, test);
        TtsServiceResponse response = client.invokeTts(request);

        Assert.assertEquals(0, response.getError_code());
        Assert.assertEquals(true, StringUtils.isNotEmpty(response.getUrl()));
    }
}

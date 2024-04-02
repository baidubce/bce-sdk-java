package com.baidubce.services.modbus;

import com.baidubce.services.modbus.model.device.CreateDevice;
import com.baidubce.services.modbus.model.device.DeviceResponse;
import com.baidubce.services.modbus.model.device.ListDeviceRequest;
import com.baidubce.services.modbus.model.device.ListDeviceResponse;
import com.baidubce.services.modbus.model.device.UpdateDeviceRequest;
import com.baidubce.services.modbus.model.gateway.GatewayResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeviceTest extends ModbusClientTest {

    @Test
    public void createDevice() throws Exception {
        CreateDevice createDevice = new CreateDevice();
        createDevice.setGatewayUuid("acdfb029-5cca-4e74-bb51-707355e9c12f");
        createDevice.setDescription("sdk-test11");
        createDevice.setCode("sdktest111");
        createDevice.setSlaveId(23);
        createDevice.setAddress("1.1.1.1:502");
        createDevice.setMode("TCP");
        GatewayResponse response1 = modbusClient.getGateway("acdfb029-5cca-4e74-bb51-707355e9c12f");
        DeviceResponse response2 = modbusClient.createDevice(createDevice);
        GatewayResponse response3 = modbusClient.getGateway("acdfb029-5cca-4e74-bb51-707355e9c12f");
        System.out.println(mapper.writeValueAsString(response2));
        assertEquals(2, response1.getDeviceNum());
        assertEquals(3, response3.getDeviceNum());
        assertTrue(response1.isUseSsl());
    }

    @Test
    public void listDevice() throws Exception {
        ListDeviceRequest request = new ListDeviceRequest();
        request.setGatewayUuid("acdfb029-5cca-4e74-bb51-707355e9c12f");
        ListDeviceResponse listDeviceResponse1 = modbusClient.listDevice(request);
        GatewayResponse gateway1 = modbusClient.getGateway("acdfb029-5cca-4e74-bb51-707355e9c12f");
        // modbusClient.deleteDevice("38f0a452-467b-4bf2-90c8-788ecab1fe8b");
        GatewayResponse gateway2 = modbusClient.getGateway("acdfb029-5cca-4e74-bb51-707355e9c12f");
        ListDeviceResponse listDeviceResponse2 = modbusClient.listDevice(request);

        System.out.println(mapper.writeValueAsString(listDeviceResponse1));
        System.out.println(mapper.writeValueAsString(listDeviceResponse2));

        assertEquals(2, listDeviceResponse1.getTotalCount());
        assertEquals(2, gateway1.getDeviceNum());
        assertEquals(2, gateway2.getDeviceNum());
    }

    @Test
    public void getDevice() throws Exception {
        ListDeviceRequest request = new ListDeviceRequest();
        request.setGatewayUuid("acdfb029-5cca-4e74-bb51-707355e9c12f");
        ListDeviceResponse listDeviceResponse1 = modbusClient.listDevice(request);
        System.out.println(mapper.writeValueAsString(listDeviceResponse1));
        DeviceResponse deviceResponse = modbusClient.getDevice("85729854-8040-44e6-8a9f-9249ebbc89a8");
        assertEquals(3, listDeviceResponse1.getTotalCount());
        assertEquals("TCP", deviceResponse.getMode());
    }

    @Test
    public void updateDevice() throws Exception {
        DeviceResponse deviceResponse = modbusClient.getDevice("8b56d72e-e255-4182-aaaa-e0c16b4ca462");
        assertEquals("TCP", deviceResponse.getMode());
        UpdateDeviceRequest request = new UpdateDeviceRequest();
        request.setMode("RTU");
        request.setAddress("1.1.2.3:502");
        DeviceResponse updateResponse = modbusClient.updateDevice("8b56d72e-e255-4182-aaaa-e0c16b4ca462", request);
        assertEquals("RTU", updateResponse.getMode());
    }

}

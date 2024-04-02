package com.baidubce.services.modbus;

import com.baidubce.services.modbus.model.ListParserObjectResponse;
import com.baidubce.services.modbus.model.ParserObjectRequest;
import com.baidubce.services.modbus.model.parserobject.CreateParserObjectRequest;
import com.baidubce.services.modbus.model.parserobject.ParserObjectResponse;
import com.baidubce.services.modbus.model.parserobject.UpdateParserObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserObjectTest extends ModbusClientTest {

    @Test
    public void createParserObject() throws Exception {
        ParserObjectRequest listRequest = new ParserObjectRequest();
        ListParserObjectResponse listResponse1 = modbusClient.listParserObject(listRequest);
        assertEquals(4, listResponse1.getTotalCount());
        CreateParserObjectRequest request = new CreateParserObjectRequest();
        request.setGatewayUuid("acdfb029-5cca-4e74-bb51-707355e9c12f");
        request.setDestTopic("ldw12345");
        request.setName("sdk12345");
        request.setStorage("bos://ldw-ldw-ldw-ldw/");
        modbusClient.createParserObject(request);
        ListParserObjectResponse listResponse2 = modbusClient.listParserObject(listRequest);
        assertEquals(5, listResponse2.getTotalCount());
        System.out.println(mapper.writeValueAsString(listResponse2));
    }

    @Test
    public void getParserObject() throws Exception {
        ParserObjectResponse response = modbusClient.getParserObject("058d0bfd-a1ec-4787-9ec2-b5a007633e18");
        assertEquals("sdk12345", response.getName());
        assertEquals("newgate", response.getGatewayCode());
        System.out.println(mapper.writeValueAsString(response));
    }

    @Test
    public void updateParserObject() throws Exception {
        ParserObjectResponse response = modbusClient.getParserObject("d06bc886-4b6a-43a8-bdc9-3ab41ea8dbc7");
        assertEquals("ldw", response.getName());
        UpdateParserObject request = new UpdateParserObject();
        request.setName("ldwupdate");
        ParserObjectResponse updateResponse = modbusClient.updateParserObject(
                "d06bc886-4b6a-43a8-bdc9-3ab41ea8dbc7",
                request);
        assertEquals("ldwupdate", updateResponse.getName());
    }

    @Test
    public void deleteParserObject() throws Exception {
        modbusClient.deleteParserObject("d06bc886-4b6a-43a8-bdc9-3ab41ea8dbc7");
        ParserObjectRequest listRequest = new ParserObjectRequest();
        ListParserObjectResponse listResponse1 = modbusClient.listParserObject(listRequest);
        assertEquals(4, listResponse1.getTotalCount());
    }

}

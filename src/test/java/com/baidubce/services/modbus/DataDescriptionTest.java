package com.baidubce.services.modbus;

import com.baidubce.services.modbus.model.DataDescRequest;
import com.baidubce.services.modbus.model.ListDataDescResponse;
import com.baidubce.services.modbus.model.QueryDataDescResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataDescriptionTest extends ModbusClientTest {

    @Test
    public void listDataDesc() throws Exception {
        DataDescRequest dataDescRequest = new DataDescRequest();
        dataDescRequest.setParserObjectUuid("de803947-f5ed-4875-afca-7f913bb89560");
        dataDescRequest.setState("ENABLED");
        ListDataDescResponse listDataDescResponse = modbusClient.listDataDesc(dataDescRequest);
        assertEquals(4, listDataDescResponse.getTotalCount());
        System.out.println(mapper.writeValueAsString(listDataDescResponse));
    }


    @Test
    public void deleteDataDesc() throws Exception {
        modbusClient.deleteDataDesc("b0e829e2-7f4a-435a-b7df-8e1f11dc54fd");
    }

    @Test
    public void getDataDesc() throws Exception {
        QueryDataDescResponse response = modbusClient.queryDataDesc("dd0c6d77-4a42-407f-ac54-3c9143a12abc");
        System.out.println(mapper.writeValueAsString(response));

    }
}

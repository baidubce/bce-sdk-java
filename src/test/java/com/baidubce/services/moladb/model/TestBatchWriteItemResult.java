package com.baidubce.services.moladb.model;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import com.baidubce.services.moladb.model.transform.BatchWriteItemResponseUnmarshaller;

public class TestBatchWriteItemResult {

    @Test
    public void testUnmarshall() {
        String table1 = "blog";
        String table2 = "userData";
        String resp = "{\"errors\":{\"blog\":[{\"deleteRequest\":{\"key\":{\"subject\":"
                + "{\"S\":\"howaboutbcc\"},\"date\":{\"S\":\"2015/01/01\"}},\"errorNo\":-404,"
                + "\"errorMsg\":\"itemnotexist.\"}},{\"putRequest\":{\"key\":{\"subject\":"
                + "{\"S\":\"howtousemoladb\"},\"date\":{\"S\":\"2015/02/12\"}},\"errorNo\":0,"
                + "\"errorMsg\":\"Succ.\"}}]},\"unprocessedItems\":{\"userData\":[{\"deleteRequest\":"
                + "{\"key\":{\"subject\":{\"S\":\"performanceofbos\"},\"date\":{\"S\":\"2013/04/12\"}}}},"
                + "{\"putRequest\":{\"item\":{\"subject\":{\"S\":\"performanceofsms\"},"
                + "\"date\":{\"S\":\"2013/04/12\"},\"body\":{\"B\":\"123==\"}}}}]}}";

        BatchWriteItemResponse result = new BatchWriteItemResponse();
        BatchWriteItemResponseUnmarshaller unmarshaller = new BatchWriteItemResponseUnmarshaller(
                result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str1 = result.toString();
        System.out.println("str1:" + str1);
        Map<String, List<WriteRequest>> items = result.getUnprocessedItems();
        Assert.assertEquals(null, items.get(table1));
        DeleteRequest delReq = (DeleteRequest) items.get(table2).get(0);
        PutRequest putReq = (PutRequest) items.get(table2).get(1);
        Assert.assertTrue(null != delReq);
        Assert.assertTrue(null != putReq);
        Key keyToDel = delReq.getKey();
        Map<String, AttributeValue> itemToPut = putReq.getItem();
        Assert.assertEquals("performanceofbos",
                            keyToDel.getAttributes().get("subject").getS());
        Assert.assertEquals("performanceofbos",
                            keyToDel.getAttributes().get("subject").getAttributeValue());
        Assert.assertEquals(null,
                            itemToPut.get("columnName2"));
        
        BatchWriteItemRequest req = new BatchWriteItemRequest(); 
        req.withRequestItems(result.getUnprocessedItems());
        String reqStr = req.toString();
        System.out.println("req str:" + reqStr);
        
    }
}
package com.baidubce.services.moladb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class TestBatchWriteItemRequest {

    @Test
    public void testMarshall() {
        String tableName1 = "table1";
        String tableName2 = "table2";

        // Create a map for the requests in the batch
        Map<String, List<WriteRequest>> requestItems = new HashMap<String, List<WriteRequest>>();

        // Create a PutRequest for a new Forum item
        Map<String, AttributeValue> item1 = new HashMap<String, AttributeValue>();
        item1.put("name", new AttributeValue().withS("BatchPutNewName"));
        item1.put("age", new AttributeValue().withN(99));
        item1.put("subject", new AttributeValue().withN(400));

        List<WriteRequest> actionOnTable1 = new ArrayList<WriteRequest>();
        actionOnTable1.add(new PutRequest().withItem(item1));
        requestItems.put(tableName1, actionOnTable1);

        // Create a PutRequest for a new Thread item
        Map<String, AttributeValue> item2 = new HashMap<String, AttributeValue>();
        item2.put("value", new AttributeValue().withB("emhhbmdiaWFvDQpxaXlhbg".getBytes()));
        item2.put("subject", new AttributeValue().withN(410));

        Map<String, AttributeValue> item3 = new HashMap<String, AttributeValue>();
        item3.put("value", new AttributeValue().withB("emhhbmdiaWFvDQpxaXlhbg".getBytes()));
        item3.put("subject", new AttributeValue().withN(410));

        List<WriteRequest> actionOnTable2 = new ArrayList<WriteRequest>();
        actionOnTable2.add(new PutRequest().withItem(item2));
        actionOnTable2.add(new PutRequest().withItem(item3));

        // Create a DeleteRequest for a Thread item
        Key delKey = new Key();
        delKey.withAttribute("subject", new AttributeValue().withN(211));

        actionOnTable2.add(new DeleteRequest().withKey(delKey));
        requestItems.put(tableName2, actionOnTable2);

        BatchWriteItemRequest batchWriteItemRequest = new BatchWriteItemRequest();
        batchWriteItemRequest.withRequestItems(requestItems);
        String jsonStr = batchWriteItemRequest.toString();
        System.out.println("json str is: " + jsonStr);
        String expect = "{\"requestItems\":{\"table2\":[{\"putRequest\":{\"item\":"
                + "{\"subject\":{\"N\":\"410\"},\"value\":{\"B\":\"ZW1oaGJtZGlhV0Z2RFFweGFYbGhiZw==\"}}}},"
                + "{\"putRequest\":{\"item\":{\"subject\":{\"N\":\"410\"},\"value\":"
                + "{\"B\":\"ZW1oaGJtZGlhV0Z2RFFweGFYbGhiZw==\"}}}},{\"deleteRequest\":"
                + "{\"key\":{\"subject\":{\"N\":\"211\"}}}}],\"table1\":[{\"putRequest\":"
                + "{\"item\":{\"subject\":{\"N\":\"400\"},\"name\":{\"S\":\"BatchPutNewName\"},"
                + "\"age\":{\"N\":\"99\"}}}}]}}";
        Assert.assertEquals(expect, jsonStr);
    }
}
package com.baidubce.services.moladb.model;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class TestPutItemRequest {
    @Test
    public void testMarshall() {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("subject", new AttributeValue("howtousemoladb"));
        item.put("count", new AttributeValue().withN("1234"));

        String tableName = "table1";
        PutItemRequest putItemRequest = new PutItemRequest(tableName);
        putItemRequest.withTableName(tableName);
        putItemRequest.withItem(item);
        String jsonStr = putItemRequest.toString();
        System.out.println("jsonStr:" + jsonStr);
        String expect = "{\"item\":{\"subject\":{\"S\":\"howtousemoladb\"},"
                + "\"count\":{\"N\":\"1234\"}}}";

        Assert.assertEquals(expect, jsonStr);
        System.out.println("req in string:" + putItemRequest.toString());

        PutItemRequest putItemRequest2 = new PutItemRequest(tableName);
        Map<String, AttributeValue> attrs = new HashMap<String, AttributeValue>();
        attrs.put("attr1", new AttributeValue().withN(1));
        
        putItemRequest2.withItem(attrs);
        jsonStr = putItemRequest2.toString();
        System.out.println("jsonStr2:" + jsonStr);

        expect = "{\"item\":{\"attr1\":{\"N\":\"1\"}}}";
        Assert.assertEquals(expect, jsonStr);

    }

    @Test
    public void testMarshallWithExpection() {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("attr1", new AttributeValue("binaryValue"));
        item.put("attr2", new AttributeValue().withN("1234"));

        String tableName = "table1";
        PutItemRequest putItemRequest = new PutItemRequest(tableName);
        putItemRequest.withItem(item);
        String jsonStr = putItemRequest.toString();
        System.out.println("jsonStr3:" + jsonStr);
        String expect = "{\"item\":{\"attr2\":{\"N\":\"1234\"},"
                + "\"attr1\":{\"S\":\"binaryValue\"}}}";

        Assert.assertEquals(expect, jsonStr);
        System.out.println("req in string:" + putItemRequest.toString());
    }
    
    @Test
    public void testExpectNotExist() {
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("attr1", new AttributeValue("binaryValue"));
        item.put("attr2", new AttributeValue().withN("1234"));

        String tableName = "table1";
        PutItemRequest putItemRequest = new PutItemRequest(tableName);
        putItemRequest.withItem(item);

        String jsonStr = putItemRequest.toString();
        System.out.println("jsonStr4:" + jsonStr);
        String expect = "{\"item\":{\"attr2\":"
                + "{\"N\":\"1234\"},\"attr1\":{\"S\":\"binaryValue\"}}}";
        Assert.assertEquals(expect, jsonStr);
        System.out.println("req in string:" + putItemRequest.toString());
    }
}
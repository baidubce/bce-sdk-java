package com.baidubce.services.moladb.model;

import org.junit.Assert;
import org.junit.Test;

public class TestDeleteItemRequest {
    @Test
    public void testMarshaller() {
        DeleteItemRequest req = new DeleteItemRequest();
        Key keyToDel = new Key();
        AttributeValue value = new AttributeValue();
        value.withN(123);
        keyToDel.withAttribute("key", value);
        req.withKey(keyToDel).withTableName("table1");
        String jsonStr = req.toString();
        String expect = "{\"key\":{\"key\":{\"N\":\"123\"}},\"tableName\":\"table1\"}";
        Assert.assertEquals(expect, jsonStr);

        String reqInStr = req.toString();
        System.out.println("req in str:" + reqInStr);
    }
}
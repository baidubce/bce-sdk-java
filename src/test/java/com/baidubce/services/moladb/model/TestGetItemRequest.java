package com.baidubce.services.moladb.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TestGetItemRequest {
    
    @Test
    public void testMarshaller() {
        String tableName = "table1";
        Key keyToGet = new Key();
        keyToGet.withAttribute("key", new AttributeValue().withN(104));
        List<String> attrs = new ArrayList<String>();
        attrs.add("name");
        attrs.add("articals");
        attrs.add("age");
        
        GetItemRequest request = new GetItemRequest(tableName).withKey(keyToGet)
                .withConsistentRead(true).withAttributesToGet(attrs);
        String reqJson = request.toString();
        String expect = "{\"consistentRead\":\"true\","
                + "\"attributesToGet\":[\"name\",\"articals\",\"age\"]}";

        Assert.assertEquals(expect, reqJson);
        String toStr = request.toString();
        System.out.println("request in str:" + toStr);

        request = new GetItemRequest();
        request.withTableName("table2");
        request.withConsistentRead(false);
        request.withKey(keyToGet);
        reqJson = request.toString();
        System.out.println("json str:" + reqJson);
        Assert.assertEquals("", reqJson);

    }
}
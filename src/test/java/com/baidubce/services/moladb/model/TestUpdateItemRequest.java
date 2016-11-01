package com.baidubce.services.moladb.model;

import java.io.IOException;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

public class TestUpdateItemRequest {
    
    @Test
    public void testMarshall() throws IOException {
        String tableName = "proxyserver_test_2columns";

        HashMap<String, AttributeValueUpdate> updateItems = new HashMap<String, AttributeValueUpdate>();
        Key keyToUpdate = new Key();
        keyToUpdate.withAttribute("key", new AttributeValue().withN(104));

        updateItems.put("name", new AttributeValueUpdate().withAction(AttributeValueUpdate.ACTION_PUT)
                                    .withValue(new AttributeValue().withS("UpdateNewName")));
        updateItems.put("value", new AttributeValueUpdate().withAction(AttributeValueUpdate.ACTION_DELETE)
                                    .withValue(new AttributeValue().withN(123)));

        UpdateItemRequest req = new UpdateItemRequest();
        req.withTableName(tableName);
        req.withKey(keyToUpdate);
        req.withAttributeUpdates(updateItems);
        String jsonStr = req.toString();
        System.out.println("json str:" + jsonStr);
        String expect = "{\"attributeUpdates\":{\"name\":{\"action\":\"Put\","
                + "\"value\":{\"S\":\"UpdateNewName\"}},"
                + "\"value\":{\"action\":\"Delete\"}},\"key\":{\"key\":{\"N\":\"104\"}}}";

        Assert.assertEquals(expect, jsonStr);
    }

    @Test
    public void testMarshallWithExpect() throws IOException {
        String tableName = "blog";

        HashMap<String, AttributeValueUpdate> updateItems = new HashMap<String, AttributeValueUpdate>();
        Key keyToUpdate = new Key();
        keyToUpdate.withAttribute("key", new AttributeValue().withN(104));

        updateItems.put("name", new AttributeValueUpdate().withAction(AttributeValueUpdate.ACTION_PUT)
                                    .withValue(new AttributeValue().withS("UpdateNewName")));
        updateItems.put("value", new AttributeValueUpdate().withAction(AttributeValueUpdate.ACTION_DELETE)
                                    .withValue(new AttributeValue().withN(123)));

        UpdateItemRequest req = new UpdateItemRequest();
        req.withTableName(tableName);
        req.withKey(keyToUpdate);
        req.withAttributeUpdates(updateItems);

        String jsonStr = req.toString();
        System.out.println("json str with expect:" + jsonStr);
        String expect = "{\"attributeUpdates\":{\"name\":"
                + "{\"action\":\"Put\",\"value\":{\"S\":\"UpdateNewName\"}},"
                + "\"value\":{\"action\":\"Delete\"}},\"key\":{\"key\":{\"N\":\"104\"}}}";
        Assert.assertEquals(expect, jsonStr);
    }
    
    @Test
    public void testExpectNotExist() throws IOException {
        String tableName = "proxyserver_test_2columns";

        HashMap<String, AttributeValueUpdate> updateItems = new HashMap<String, AttributeValueUpdate>();
        Key keyToUpdate = new Key();
        keyToUpdate.withAttribute("key", new AttributeValue().withN(104));

        updateItems.put("name", new AttributeValueUpdate().withAction(AttributeValueUpdate.ACTION_PUT)
                                    .withValue(new AttributeValue().withS("UpdateNewName")));
        updateItems.put("value", new AttributeValueUpdate().withAction(AttributeValueUpdate.ACTION_DELETE)
                                    .withValue(new AttributeValue().withN(123)));

        UpdateItemRequest req = new UpdateItemRequest();
        req.withTableName(tableName);
        req.withKey(keyToUpdate);
        req.withAttributeUpdates(updateItems);
        
        String jsonStr = req.toString();
        System.out.println("json str with expect:" + jsonStr);
        String expect = "{\"attributeUpdates\":"
                + "{\"name\":{\"action\":\"Put\",\"value\":{\"S\":\"UpdateNewName\"}},"
                + "\"value\":{\"action\":\"Delete\"}},\"key\":{\"key\":{\"N\":\"104\"}}}";
        Assert.assertEquals(expect, jsonStr);
    }
}
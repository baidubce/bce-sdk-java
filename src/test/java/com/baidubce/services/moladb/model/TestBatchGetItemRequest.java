package com.baidubce.services.moladb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class TestBatchGetItemRequest {

    @Test
    public void testLimitExceed() {
        String tableName1 = "blog";
        String tableName2 = "userData";

        HashMap<String, KeysAndAttributes> requestItems = new HashMap<String, KeysAndAttributes>();

        ArrayList<Key> keys1 = new ArrayList<Key>();

        Key table1key1 = new Key();
        table1key1.withAttribute("subject", new AttributeValue().withN("1101"));
        table1key1.withAttribute("date", new AttributeValue().withS("2015/02/12"));
        keys1.add(table1key1);
        Key table1key2 = new Key();
        table1key2.withAttribute("subject", new AttributeValue().withN("111"));
        table1key2.withAttribute("date", new AttributeValue().withS("2013/04/12"));
        keys1.add(table1key2);
        List<String> attrs = new ArrayList<String>();
        attrs.add("name");
        attrs.add("gender");
        attrs.add("address");

        requestItems.put(tableName1, new KeysAndAttributes()
                            .withKeys(keys1)
                            .withAttributesToGet(attrs));

        ArrayList<Key> keys2 = new ArrayList<Key>();

        Key table2key1 = new Key();
        table2key1.withAttribute("city", new AttributeValue().withN("210"));
        keys2.add(table2key1);
        Key table2key2 = new Key();
        table2key2.withAttribute("city", new AttributeValue().withN("211"));
        keys2.add(table2key2);

        Key table2key3 = new Key();
        table2key3.withAttribute("city", new AttributeValue().withN(212));
        keys2.add(table2key3);

        requestItems.put(tableName2,
                         new KeysAndAttributes().withKeys(keys2).withConsistentRead(true));

    }

    @Test
    public void testToJson() {
        String tableName1 = "blog";
        String tableName2 = "userData";

        HashMap<String, KeysAndAttributes> requestItems = new HashMap<String, KeysAndAttributes>();

        ArrayList<Key> keys1 = new ArrayList<Key>();

        Key table1key1 = new Key();
        table1key1.withAttribute("subject", new AttributeValue().withN(1101));
        table1key1.withAttribute("date", new AttributeValue().withS("2015/02/12"));
        keys1.add(table1key1);
        Key table1key2 = new Key();
        table1key2.withAttribute("subject", new AttributeValue().withN(111));
        table1key2.withAttribute("date", new AttributeValue().withS("2013/04/12"));
        keys1.add(table1key2);
        List<String> attrs = new ArrayList<String>();
        attrs.add("name");
        attrs.add("gender");
        attrs.add("address");

        requestItems.put(tableName1, new KeysAndAttributes()
                            .withKeys(keys1).withAttributesToGet(attrs));

        ArrayList<Key> keys2 = new ArrayList<Key>();

        Key table2key1 = new Key();
        table2key1.withAttribute("city", new AttributeValue().withN(210));
        keys2.add(table2key1);
        Key table2key2 = new Key();
        table2key2.withAttribute("city", new AttributeValue().withN(211));
        keys2.add(table2key2);

        Key table2key3 = new Key();
        table2key3.withAttribute("city", new AttributeValue().withN(212));
        keys2.add(table2key3);

        requestItems.put(tableName2,
                         new KeysAndAttributes().withKeys(keys2).withConsistentRead(true));

        BatchGetItemRequest batchGetItemRequest = new BatchGetItemRequest().withRequestItems(requestItems);
        String jsonStr = batchGetItemRequest.toString();
        System.out.println("jsonStr:" + jsonStr);
        String expected = "{\"requestItems\":{\"userData\":{\"keys\":[{\"city\":{\"N\":\"210\"}},"
                + "{\"city\":{\"N\":\"211\"}},{\"city\":{\"N\":\"212\"}}],\"consistentRead\":\"true\"},"
                + "\"blog\":{\"keys\":[{\"date\":{\"S\":\"2015/02/12\"},\"subject\":{\"N\":\"1101\"}},"
                + "{\"date\":{\"S\":\"2013/04/12\"},\"subject\":{\"N\":\"111\"}}],"
                + "\"attributesToGet\":[\"name\",\"gender\",\"address\"]}}}";
        Assert.assertEquals(expected, jsonStr);
    }
}
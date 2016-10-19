package com.baidubce.services.moladb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.baidubce.services.moladb.MolaDbConstants;

public class TestQueryRequest {
    @BeforeClass
    public static void before() {
    }

    @AfterClass
    public static void after() {
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testToJson() {
        QueryRequest req = new QueryRequest("table1");
        Key key1 = new Key();
        key1.withAttribute("keyAttr1", new AttributeValue().withN(123));
        key1.withAttribute("keyAttr2", new AttributeValue().withN(456));
        req.withConsistentRead(true);
        req.withExclusiveStartKey(key1);
        req.withLimit(1000);
        List<String> attrs = new ArrayList<String>();
        attrs.add("attr1");
        req.withAttributesToGet(attrs);
        
        String keyExpression = "hashKey" + " = " + MolaDbConstants.JSON_HASH_KEY_VALUE + " AND " 
                + "rangeKey" + " BETWEEN " + MolaDbConstants.JSON_LOW_BOUND 
                + " AND " + MolaDbConstants.JSON_UP_BOUND;
        Map<String, AttributeValue> expressionAttributes = new HashMap<String, AttributeValue>();

        expressionAttributes.put(MolaDbConstants.JSON_HASH_KEY_VALUE, new AttributeValue().withN(123));
        expressionAttributes.put(MolaDbConstants.JSON_LOW_BOUND, new AttributeValue().withN(456));
        expressionAttributes.put(MolaDbConstants.JSON_UP_BOUND, new AttributeValue().withS("789"));

        req.withKeyConditionExpression(keyExpression)
                .withExpressionAttributeValues(expressionAttributes);
        String json = null;
        boolean meetExcept = false;
        try {
            json = req.toString();
        } catch (IllegalArgumentException e) {
            meetExcept = true; 
        }
        Assert.assertFalse(meetExcept);
        Assert.assertEquals(1, req.getAttributesToGet().size());
        Assert.assertTrue(req.isConsistentRead());
        Assert.assertEquals(2, req.getExclusiveStartKey().getAttributes().size());
        Assert.assertEquals(1000, req.getLimit());
        Assert.assertEquals("table1", req.getTableName());
        String expect = "{\"keyConditionExpression\":\"hashKey = :HashKeyValue AND rangeKey BETWEEN "
                + ":LowBound AND :UpBound\","
                + "\"expressionAttributeValues\":{\":HashKeyValue\":{\"N\":\"123\"},"
                + "\":LowBound\":{\"N\":\"456\"},\":UpBound\":"
                + "{\"S\":\"789\"}},\"exclusiveStartKey\":{\"keyAttr2\":{\"N\":\"456\"},"
                + "\"keyAttr1\":{\"N\":\"123\"}},"
                + "\"consistentRead\":\"true\",\"attributesToGet\":[\"attr1\"],\"limit\":\"1000\","
                + "\"tableName\":\"table1\",\"order\":\"Asc\"}";

        Assert.assertEquals(expect, json);
        req.orderByAsc();
        Assert.assertEquals(expect, req.toString());
        String expect2 = "{\"keyConditionExpression\":\"hashKey = :HashKeyValue "
                + "AND rangeKey BETWEEN :LowBound AND :UpBound\","
                + "\"expressionAttributeValues\":{\":HashKeyValue\":{\"N\":\"123\"},\":LowBound\":{\"N\":\"456\"},"
                + "\":UpBound\":{\"S\":\"789\"}},\"exclusiveStartKey\":{\"keyAttr2\":{\"N\":\"456\"},"
                + "\"keyAttr1\":{\"N\":\"123\"}},\"consistentRead\":\"true\",\"attributesToGet\":[\"attr1\"],"
                + "\"limit\":\"1000\",\"tableName\":\"table1\",\"order\":\"Desc\"}";
        req.orderByDesc();
        Assert.assertEquals(expect2, req.toString());
    }
}
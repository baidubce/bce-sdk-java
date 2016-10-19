package com.baidubce.services.moladb.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCreateTableRequest {

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
    public void testArugmentNoCheck() {
        CreateTableRequest req = new CreateTableRequest("table1");
        boolean meetExcept = false;
        try {
            List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
            keySchema.add(new KeySchemaElement().withAttributeName("attr1")
                              .withKeyType(KeySchemaElement.HASH_KEY_TYPE));
            keySchema.add(new KeySchemaElement().withAttributeName("attr2").withKeyType("range"));
        } catch (IllegalArgumentException e) {
            meetExcept = true;
        }
        Assert.assertFalse(meetExcept);
        meetExcept = false;
        try {
            List<AttributeDefinition> attrs = new ArrayList<AttributeDefinition>();
            attrs.add(new AttributeDefinition().withAttributeName("attr3").withAttributeType("b"));
            req.withAttributeDefinitions(attrs);
        } catch (IllegalArgumentException e) {
            meetExcept = true;
        }
        Assert.assertFalse(meetExcept);
    }

    @Test
    public void testToJsonStr() {
        CreateTableRequest req = new CreateTableRequest("table1");
        List<AttributeDefinition> attrs = new ArrayList<AttributeDefinition>();
        attrs.add(new AttributeDefinition().withAttributeName("attr1")
                      .withAttributeType(AttributeValue.ATTRIBUTE_TYPE_BINARY));
        attrs.add(new AttributeDefinition().withAttributeName("attr2")
                      .withAttributeType(AttributeValue.ATTRIBUTE_TYPE_STRING));
        attrs.add(new AttributeDefinition().withAttributeName("attr3")
                      .withAttributeType(AttributeValue.ATTRIBUTE_TYPE_BINARY));
        attrs.add(new AttributeDefinition().withAttributeName("attr4")
                      .withAttributeType(AttributeValue.ATTRIBUTE_TYPE_NUMBER));

        req.withAttributeDefinitions(attrs);
        List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
        keySchema.add(new KeySchemaElement().withAttributeName("attr1").withKeyType(KeySchemaElement.HASH_KEY_TYPE));
        keySchema.add(new KeySchemaElement().withAttributeName("attr2").withKeyType(KeySchemaElement.RANGE_KEY_TYPE));
        req.withKeySchema(keySchema);
        req.withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(100L)
                                                .withWriteCapacityUnits(100L));

        String jsonStr = req.toString();
        System.out.println("json str:" + jsonStr);
        String expect = "{\"attributeDefinitions\":[{\"attributeType\":\"B\",\"attributeName\":\"attr1\"},"
                + "{\"attributeType\":\"S\",\"attributeName\":\"attr2\"},{\"attributeType\":\"B\","
                + "\"attributeName\":\"attr3\"},{\"attributeType\":\"N\",\"attributeName\":\"attr4\"}],"
                + "\"keySchema\":[{\"attributeName\":\"attr1\",\"keyType\":\"Hash\"},{\"attributeName\":"
                + "\"attr2\",\"keyType\":\"Range\"}],"
                + "\"provisionedThroughput\":{\"writeCapacityUnits\":100,\"readCapacityUnits\":100},"
                + "\"tableName\":\"table1\"}";
        Assert.assertEquals(expect, jsonStr);
    }
}

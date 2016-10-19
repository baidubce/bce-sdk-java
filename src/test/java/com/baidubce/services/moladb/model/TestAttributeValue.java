package com.baidubce.services.moladb.model;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.baidubce.services.moladb.model.transform.AttributeValueUnmarshaller;
import com.baidubce.services.moladb.model.AttributeValue;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class TestAttributeValue {
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
    public void testConstruction() {
        AttributeValue attr1 = new AttributeValue("string1");
        Assert.assertEquals("string1", attr1.getAttributeValue());
        Assert.assertEquals(AttributeValue.ATTRIBUTE_TYPE_STRING, attr1.getAttributeType());

        AttributeValue attr2 = new AttributeValue();
        attr2.withN("123");
        Assert.assertEquals(AttributeValue.ATTRIBUTE_TYPE_NUMBER, attr2.getAttributeType());
        
        AttributeValue attr3 = new AttributeValue();
        attr3.withN("123");
        Assert.assertEquals(AttributeValue.ATTRIBUTE_TYPE_NUMBER, attr3.getAttributeType());
        
        AttributeValue attr4 = new AttributeValue(123L);
        Assert.assertEquals(AttributeValue.ATTRIBUTE_TYPE_NUMBER, attr4.getAttributeType());
        Assert.assertEquals(123L, attr4.getLong());
        
        AttributeValue attr5 = new AttributeValue(123.3);
        Assert.assertEquals(AttributeValue.ATTRIBUTE_TYPE_NUMBER, attr4.getAttributeType());
        int result = (int) (123.3 - attr5.getDouble());
        Assert.assertEquals(0, result);

        String bin = "binary";
        byte[] byte2 = bin.getBytes();
        AttributeValue attr6 = new AttributeValue(byte2);
        Assert.assertEquals(AttributeValue.ATTRIBUTE_TYPE_BINARY, attr6.getAttributeType());
        String str3 = new String(attr6.getB());
        Assert.assertEquals(bin, str3);
    }

    @Test
    public void testSetB() {
        String bin = "binary";
        AttributeValue value = new AttributeValue();
        value.withB(bin.getBytes());
        Assert.assertEquals(AttributeValue.ATTRIBUTE_TYPE_BINARY, value.getAttributeType());
        Assert.assertEquals("YmluYXJ5", value.getAttributeValue());

        byte[] byte2 = bin.getBytes();
        value.withB(byte2);
        String str3 = new String(value.getB());
        Assert.assertEquals(bin, str3);
    }

    @Test
    public void testToString() {
        String bin = "binary";
        AttributeValue value = new AttributeValue();
        value.withB(bin.getBytes());
        String str = value.toString();
        Assert.assertEquals("{\"B\":\"YmluYXJ5\"}", str);
    }

    @Test
    public void testToJsonObj() {
        String bin = "binary";
        AttributeValue value = new AttributeValue();
        value.withB(bin.getBytes());
        Map<String, String> jsonObj = value.toJsonObj();
        Assert.assertEquals(1, jsonObj.size());
        Assert.assertEquals("YmluYXJ5", jsonObj.get("B"));
    }

    @Test
    public void testMarshallerAndUnmarshaller() {
        AttributeValue value = new AttributeValue();
        value.withS("strValue");
        String jsonStr = JsonUtils.toJsonString(value.toJsonObj());
        AttributeValueUnmarshaller unmarshaller = new AttributeValueUnmarshaller();
        JsonNode jsonObj = JsonUtils.jsonNodeOf(jsonStr);
        AttributeValue value1 = null;
        try {
            value1 = unmarshaller.unmarshall(jsonObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(value.toString(), value1.toString());
    }
}

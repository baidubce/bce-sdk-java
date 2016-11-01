package com.baidubce.services.moladb.model;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import com.baidubce.services.moladb.model.transform.AttributeValueMapUnmarshaller;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class TestAttributeValueMap {
    @Test
    public void testToString() {
        Map<String, AttributeValue> values = new HashMap<String, AttributeValue>();
        String attrName = "name1";
        AttributeValue value = new AttributeValue();
        value.withN("123");
        values.put(attrName, value);
        String expect = "{name1={\"N\":\"123\"}}";
        Assert.assertEquals(expect, values.toString());
    }

    @Test
    public void testMarshallerAndUnmarshaller() {
        Map<String, AttributeValue> values = new HashMap<String, AttributeValue>();
        String attrName = "name1";
        AttributeValue value = new AttributeValue();
        value.withN("123");
        values.put(attrName, value);
        Map<String, Object> obj = new HashMap<String, Object>();
        for (Map.Entry<String, AttributeValue> entry : values.entrySet()) {
            obj.put(entry.getKey(), entry.getValue().toJsonObj());
        }
        String jsonStr = JsonUtils.toJsonString(obj);
        AttributeValueMapUnmarshaller unmarshaller = new AttributeValueMapUnmarshaller();
        JsonNode jsonObj = JsonUtils.jsonNodeOf(jsonStr);
        Map<String, AttributeValue> value1 = null;
        try {
            value1 = unmarshaller.unmarshall(jsonObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(values.toString(), value1.toString());
        boolean meetExcept = false;
        try {
            unmarshaller.unmarshall(null);
        } catch (Exception e) {
            meetExcept = true;
        }
        Assert.assertTrue(meetExcept);

        meetExcept = false;
        try {
            unmarshaller.unmarshall(JsonUtils.jsonNodeOf("[]"));
        } catch (Exception e) {
            meetExcept = true;
        }
        Assert.assertTrue(meetExcept);
    }
}

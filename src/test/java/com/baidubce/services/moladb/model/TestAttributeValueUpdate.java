package com.baidubce.services.moladb.model;

import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import com.baidubce.util.JsonUtils;

public class TestAttributeValueUpdate {
    @Test
    public void testToJson() {
        AttributeValueUpdate updateValue = new AttributeValueUpdate();
        AttributeValue value = new AttributeValue();
        value.withN("123");
        updateValue.withAction(AttributeValueUpdate.ACTION_PUT);
        updateValue.withValue(value);

        Map<String, Object> jsonObj = updateValue.toJsonObj();
        String jsonStr = JsonUtils.toJsonString(jsonObj);
        String expect = "{\"action\":\"Put\",\"value\":{\"N\":\"123\"}}";
        Assert.assertEquals(expect, jsonStr);

        updateValue.withAction(AttributeValueUpdate.ACTION_DELETE);
        expect = "{\"action\":\"Delete\"}";
        jsonStr = JsonUtils.toJsonString(updateValue.toJsonObj());
        Assert.assertEquals(expect, jsonStr);
    }
}

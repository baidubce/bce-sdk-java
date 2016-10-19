package com.baidubce.services.moladb.model;

import java.io.ByteArrayInputStream;
import org.junit.Assert;
import org.junit.Test;
import com.baidubce.services.moladb.model.transform.GetItemResponseUnmarshaller;

public class TestGetItemResult {
    @Test
    public void testUnmarshaller() {

        String resp = "{\"item\":{\"subject\":{\"S\":\"howtousemoladb?\"},"
                + "\"date\":{\"S\":\"2015/01/02\"},\"body\":{\"B\":\"c28gZWFzeQ==\"},"
                + "\"auther\":{\"S\":\"moladb\"}}}";

        GetItemResponse result = new GetItemResponse();
        GetItemResponseUnmarshaller unmarshaller = new GetItemResponseUnmarshaller(
                result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String value1 = result.getItem().get("subject").getAttributeValue();
        Assert.assertEquals("howtousemoladb?", value1);
        AttributeValue value2 = result.getItem().get("notExistAttr");
        System.out.println("not exist attr:" + value2);
        Assert.assertEquals(null, value2);
        String str = result.toString();
        System.out.println("result:" + str);
    }
}
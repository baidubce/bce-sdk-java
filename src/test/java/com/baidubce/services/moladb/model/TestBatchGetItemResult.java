package com.baidubce.services.moladb.model;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import com.baidubce.services.moladb.model.transform.BatchGetItemResponseUnmarshaller;

public class TestBatchGetItemResult {

    @Test
    public void testUnmarshallWithOutUnprocess() {
        String table1 = "blog";
        String jsonStr = "{\"responses\":{\"blog\":[{\"item\":{\"body\":{\"B\":\"123==\"},"
                + "\"auther\":{\"S\":\"moladb\"},\"subject\":{\"S\":\"howtousemoladb\"},"
                + "\"date\":{\"S\":\"2015/02/12\"}}},{\"item\":{\"body\":{\"B\":\"123==\"},"
                + "\"auther\":{\"S\":\"moladb\"},\"subject\":{\"S\":\"performanceofmoladb\"},"
                + "\"date\":{\"S\":\"2015/02/12\"}}}]},\"status\":{\"blog\":[{\"key\":"
                + "{\"subject\":{\"S\":\"performanceofbcc\"},\"date\":{\"S\":\"2013/04/02\"}},"
                + "\"errorNo\":-404,\"errorMsg\":\"itemnotexist.\"},{\"key\":{\"subject\":"
                + "{\"S\":\"performanceofmoladb\"},\"date\":{\"S\":\"2013/04/02\"}},"
                + "\"errorNo\":0,\"errorMsg\":\"Succ.\"},{\"key\":{\"subject\":{\"S\":\"howtousemoladb\"},"
                + "\"date\":{\"S\":\"2015/02/12\"}},\"errorNo\":0,\"errorMsg\":\"Succ.\"}]}}";
        BatchGetItemResponse result = new BatchGetItemResponse();
        BatchGetItemResponseUnmarshaller unmarshaller = new BatchGetItemResponseUnmarshaller(
                result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(
                    jsonStr.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, KeysAndAttributes> unprocess = result.getUnprocessedItems();
        Map<String, List<Map<String, AttributeValue>>> resp = result.getResponses();
        Assert.assertEquals(null, unprocess);
        Assert.assertEquals(2, resp.get(table1).size());
    }

    @Test
    public void testUnmarshallWithUnprocess() {
        String table3 = "userData";
        String jsonStr = "{\"responses\":{\"blog\":[{\"item\":{\"body\":{\"B\":\"123==\"},"
                + "\"auther\":{\"S\":\"moladb\"},\"subject\":{\"S\":\"howtousemoladb\"},"
                + "\"date\":{\"S\":\"2015/02/12\"}}},{\"item\":{\"body\":{\"B\":\"123==\"},"
                + "\"auther\":{\"S\":\"moladb\"},\"subject\":{\"S\":\"performanceofmoladb\"},"
                + "\"date\":{\"S\":\"2015/02/12\"}}}]},\"errors\":{\"blog\":[{\"key\":{\"subject\":"
                + "{\"S\":\"performanceofbcc\"},\"date\":{\"S\":\"2013/04/02\"}},\"errorNo\":-404,"
                + "\"errorMsg\":\"itemnotexist.\"},{\"key\":{\"subject\":{\"S\":\"performanceofmoladb\"},"
                + "\"date\":{\"S\":\"2013/04/02\"}},\"errorNo\":0,\"errorMsg\":\"Succ.\"},"
                + "{\"key\":{\"subject\":{\"S\":\"howtousemoladb\"},\"date\":{\"S\":\"2015/02/12\"}},"
                + "\"errorNo\":0,\"errorMsg\":\"Succ.\"}]},\"unprocessedItems\":{\"userData\":"
                + "{\"attributesToGet\":[\"city\",\"name\"],\"consistentRead\":\"false\","
                + "\"keys\":[{\"city\":{\"S\":\"nanjing\"}},{\"city\":{\"S\":\"beijing\"}}]}}}";
        BatchGetItemResponse result = new BatchGetItemResponse();
        BatchGetItemResponseUnmarshaller unmarshaller = new BatchGetItemResponseUnmarshaller(
                result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(
                    jsonStr.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, KeysAndAttributes> unprocess = result.getUnprocessedItems();
        Map<String, List<Map<String, AttributeValue>>> resp = result.getResponses();
        Assert.assertEquals(false, resp.isEmpty());
        Assert.assertEquals(2, unprocess.get(table3).getKeys().size());
        Assert.assertEquals(false, unprocess.get(table3).isConsistentRead());
        Assert.assertEquals(2,
                            unprocess.get(table3).getAttributesToGet().size());
        
        BatchGetItemRequest req = new BatchGetItemRequest();
        req.withRequestItems(result.getUnprocessedItems());
        Assert.assertFalse(req.getRequestItems().get(table3).isConsistentRead());
        String resultStr = result.toString();
        System.out.println("result in str:" + resultStr);
    }
}
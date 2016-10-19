package com.baidubce.services.moladb.model;

import java.io.ByteArrayInputStream;
import org.junit.Assert;
import org.junit.Test;
import com.baidubce.services.moladb.model.transform.DescribeTableUnmarshaller;

public class TestDescribeTableResult {

    @Test
    public void testUnmarshaller() {
        String jsonStr = "{\"attributeDefinitions\":[{\"attributeName\":\"subject\","
                + "\"attributeType\":\"S\"},{\"attributeName\":\"date\",\"attributeType\":\"S\"},"
                + "{\"attributeName\":\"body\",\"attributeType\":\"B\"},{\"attributeName\":\"auther\","
                + "\"attributeType\":\"S\"},{\"attributeName\":\"count\",\"attributeType\":\"N\"},"
                + "{\"attributeName\":\"desc\",\"attributeType\":\"S\"},{\"attributeName\":\"comments\","
                + "\"attributeType\":\"S\"},{\"attributeName\":\"tags\",\"attributeType\":\"S\"}],"
                + "\"account\":\"fa6fe47154ef478cb5defa7267fd6482\",\"creationDateTime\":\"2013-06-01T23:00:10Z\","
                + "\"itemCount\":14,\"engineType\":\"atomdb\",\"keySchema\":[{\"attributeName\":\"subject\","
                + "\"keyType\":\"Hash\"},{\"attributeName\":\"date\",\"keyType\":\"Range\"}],"
                + "\"provisionedThroughput\":{\"lastDecreaseDateTime\":\"2014-06-01T23:00:10Z\","
                + "\"lastIncreaseDateTime\":\"2014-06-01T23:00:10Z\",\"numberOfDecreasesToday\":0,"
                + "\"readCapacityUnits\":200,\"writeCapacityUnits\":400},\"tableName\":\"blog\","
                + "\"tableSizeInBytes\":897787877878,"
                + "\"tableStatus\":\"Active\"}";

        GetTableResponse result = new GetTableResponse();
        DescribeTableUnmarshaller unmarshaller = new DescribeTableUnmarshaller(
                result);
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(
                    jsonStr.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String resultStr = result.toString();
        System.out.println("describe table result:" + resultStr);

        Assert.assertEquals(14L, result.getItemCount().longValue());
        Assert.assertEquals("blog", result.getTableName());
        Assert.assertEquals(897787877878L,
                            result.getTableSizeInBytes().longValue());
        Assert.assertEquals("Active", result.getTableStatus());
        Assert.assertEquals(0,
                            result.getProvisionedThroughput().getNumberOfDecreasesToday().intValue());
        Assert.assertEquals(200, result.getProvisionedThroughput().getReadCapacityUnits());
        Assert.assertEquals(400, result.getProvisionedThroughput().getWriteCapacityUnits());
        Assert.assertEquals("subject", result.getKeySchema().get(0).getAttributeName());
        Assert.assertEquals("date", result.getKeySchema().get(1).getAttributeName());
        Assert.assertEquals(8, result.getAttributeDefinitions().size());
    }
    
    @Test
    public void testInvalidKeySchema() {
        String jsonStr = "{\"AttributeDefinitions\":[{\"AttributeName\":\"time\",\"AttributeType\":\"N\"},"
                + "{\"AttributeName\":\"error\",\"AttributeType\":\"S\"},{\"AttributeName\":\"message\","
                + "\"AttributeType\":\"S\"}],\"CreationDateTime\":\"2014-06-01T23:00:10Z\",\"ItemCount\":\"1000\","
                + "\"KeySchema\":[{\"AttributeName\":\"attrName1\",\"KeyType\":\"HASH1\"}],"
                + "\"ProvisionedThroughput\":{\"LastDecreaseDateTime\":\"2014-06-01T23:00:10Z\","
                + "\"LastIncreaseDateTime\":\"2014-06-01T23:00:10Z\",\"NumberOfDecreasesToday\":\"3\","
                + "\"ReadCapacityUnits\":\"1000\",\"WriteCapacityUnits\":\"100\"},"
                + "\"TableName\":\"tableName1\",\"TableSizeInBytes\":\"897787877878\",\"token\":\"rwToken\","
                + "\"ReservedStorageCapacityInBytes\":10,\"TableStatus\":\"Active\"}";
        GetTableResponse result = new GetTableResponse();
        DescribeTableUnmarshaller unmarshaller = new DescribeTableUnmarshaller(
                result);
        boolean meetExcept = false;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(
                    jsonStr.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            meetExcept = true;
        }
        Assert.assertTrue(meetExcept);
    }
}
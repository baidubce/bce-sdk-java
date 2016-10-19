package com.baidubce.services.moladb.model;

import java.io.ByteArrayInputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.baidubce.services.moladb.model.transform.QueryResponseUnmarshaller;

public class TestQueryResult {
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
    public void testInvalidRequest() {
        String resp = "{}";
        
        QueryResponse result = new QueryResponse();
        QueryResponseUnmarshaller unmarshaller = new QueryResponseUnmarshaller(
                result);
        boolean meetExcept = false;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(
                    resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
            meetExcept = true;
        }
        Assert.assertTrue(meetExcept);
        Assert.assertEquals(null, result.getLastEvaluatedKey());
        Assert.assertEquals(null, result.getItems());
    }

    @Test
    public void testEmptyRequest() {
        String resp = "{\"items\":[]}";
        
        QueryResponse result = new QueryResponse();
        QueryResponseUnmarshaller unmarshaller = new QueryResponseUnmarshaller(
                result);
        boolean meetExcept = false;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(
                    resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
            meetExcept = true;
        }
        Assert.assertFalse(meetExcept);
        Assert.assertEquals(null, result.getLastEvaluatedKey());
        Assert.assertEquals(0, result.getItems().size());
    }
    
    @Test
    public void testResponseNoLastEvaluatedKey() {
        String resp = "{\"items\":[{\"item\":{\"body\":{\"B\":\"123==\"},\"auther\":{\"S\":\"moladb\"}}}]}";
        
        QueryResponse result = new QueryResponse();
        QueryResponseUnmarshaller unmarshaller = new QueryResponseUnmarshaller(
                result);
        boolean meetExcept = false;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(
                    resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
            meetExcept = true;
        }
        Assert.assertFalse(meetExcept);
        Assert.assertEquals(null, result.getLastEvaluatedKey());
        Assert.assertEquals(1, result.getItems().size());
    }

    @Test
    public void testResponseWithLastEvaluatedKey() {
        String resp = "{\"items\":[{\"item\":{\"body\":{\"B\":\"123==\"},\"auther\":{\"S\":\"moladb\"}}},"
                + "{\"item\":{\"body\":{\"B\":\"123==\"},\"auther\":{\"S\":\"moladb\"}}}],"
                + "\"lastEvaluatedKey\":{\"subject\":{\"S\":\"performanceofsms\"},\"date\":{\"S\":\"2013/04/12\"}}}";
        
        QueryResponse result = new QueryResponse();
        QueryResponseUnmarshaller unmarshaller = new QueryResponseUnmarshaller(
                result);
        boolean meetExcept = false;
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(
                    resp.getBytes());
            result = unmarshaller.unmarshall(in);
        } catch (Exception e) {
            e.printStackTrace();
            meetExcept = true;
        }
        Assert.assertFalse(meetExcept);
        Assert.assertEquals("performanceofsms", 
                            result.getLastEvaluatedKey().getAttributes().get("subject").getAttributeValue());
        Assert.assertEquals(2, result.getItems().size());
    }
}
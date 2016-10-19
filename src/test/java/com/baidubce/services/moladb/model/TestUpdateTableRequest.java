package com.baidubce.services.moladb.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

public class TestUpdateTableRequest {

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
    public void testToJsonStrWithProvision() {
        UpdateTableRequest req = new UpdateTableRequest("table1");
        req.withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1000L));
        String jsonStr = req.toString();
        System.out.println("provision str:" + jsonStr);
        String expect = "{\"provisionedThroughput\":{\"readCapacityUnits\":1000}}";
        Assert.assertEquals(expect, jsonStr);
    }

    @Test
    public void testToJsonStr() {
        UpdateTableRequest req = new UpdateTableRequest("table1");
        req.withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(1000L)
                .withWriteCapacityUnits(10L));
        String jsonStr = req.toString();
        System.out.println("json str:" + jsonStr);
        String expect = "{\"provisionedThroughput\":"
                        + "{\"writeCapacityUnits\":10,\"readCapacityUnits\":1000}}";
        Assert.assertEquals(expect, jsonStr);
    }
}

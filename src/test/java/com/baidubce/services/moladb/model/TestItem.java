package com.baidubce.services.moladb.model;

import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.baidubce.services.moladb.model.AttributeValue;

public class TestItem {
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
        Map<String, AttributeValue> item = new HashMap<String, AttributeValue>();
        item.put("attr1", new AttributeValue("binaryValue"));
        item.put("attr2", new AttributeValue().withN("1234"));
        item.put("attr3", new AttributeValue().withB("1234".getBytes()));
        
        Assert.assertEquals(1234, Integer.parseInt(item.get("attr2").getN()));
    }
}
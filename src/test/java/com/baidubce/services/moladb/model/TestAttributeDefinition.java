package com.baidubce.services.moladb.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAttributeDefinition {
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
    public void testAttribute() {
        AttributeDefinition attr1 = new AttributeDefinition();
        attr1.withAttributeName("attr1");
        attr1.withAttributeType("N");
        Assert.assertEquals("attr1", attr1.getAttributeName());
        Assert.assertEquals("N", attr1.getAttributeType());
    }
}

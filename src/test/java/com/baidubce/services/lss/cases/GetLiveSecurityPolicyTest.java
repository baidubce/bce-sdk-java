package com.baidubce.services.lss.cases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.GetSecurityPolicyResponse;

/**
 * Created by shidaiting01 on 2016/1/13.
 */
public class GetLiveSecurityPolicyTest extends AbstractLssTest {
    private String sessionId;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule
    public ExpectedException bceEx = ExpectedException.none();

    @Test
    public void test() {
        GetSecurityPolicyResponse resp = lssClient.getSecurityPolicy("default");
        System.out.println(resp.toString());
        Assert.assertEquals(resp.getName(), "default");
    }
}

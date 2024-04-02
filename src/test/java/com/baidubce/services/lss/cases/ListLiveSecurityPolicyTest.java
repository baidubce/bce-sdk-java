package com.baidubce.services.lss.cases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.ListSecurityPoliciesResponse;

/**
 * Created by shidaiting01 on 2016/1/13.
 */
public class ListLiveSecurityPolicyTest extends AbstractLssTest {
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
        ListSecurityPoliciesResponse resp = lssClient.listSecurityPolicies();
        Assert.assertTrue(resp.getSecurityPolicies() != null);
        Assert.assertTrue(resp.getSecurityPolicies().size() > 0);
    }
}

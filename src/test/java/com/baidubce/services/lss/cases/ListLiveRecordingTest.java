package com.baidubce.services.lss.cases;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by shidaiting01 on 2016/3/15.
 */
public class ListLiveRecordingTest extends AbstractLssTest {
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
    public void testNormal() {
        System.out.println(lssClient.listRecordings());
    }
}

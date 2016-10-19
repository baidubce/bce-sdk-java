package com.baidubce.services.lss.cases;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.baidubce.services.lss.model.GetRecordingResponse;

/**
 * Created by shidaiting01 on 2016/3/15.
 */
public class GetLiveRecordingTest extends AbstractLssTest {
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
        GetRecordingResponse response = lssClient.getRecording("lss_java_sdk");
        System.out.println(response);
        assertEquals(response.getName(), "lss_java_sdk");
    }

}

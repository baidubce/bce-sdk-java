package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.PauseAppStreamRequest;
import com.baidubce.services.lss.model.ResumeAppStreamRequest;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by wuyafei on 16/6/26.
 */
public class PauseAppStreamTest extends AbstractLssTest {

    @Before
    public void setUp() throws Exception {
        AK = "6107180b80ae42c0a821a5fe53ba615e";
        SK = "a88ed698c0fd4774a4f1bdcebcae5922";
        END_POINT = "http://10.65.3.79:80";
        super.setUp();
    }

    @Test
    public void testPauseAppStreamByRequest() {
        PauseAppStreamRequest request = new PauseAppStreamRequest();
        request.setApp("test_app");
        request.setStream("teststream");
        lssClient.pauseAppStream(request);
    }

    @Test
    public void testResumeAppStreamByRequest() {
        ResumeAppStreamRequest request = new ResumeAppStreamRequest();
        request.setApp("test_app");
        request.setStream("teststream");
        lssClient.resumeAppStream(request);
    }

    @Test
    public void testPauseAppStreamByAppAndStream() {
        lssClient.pauseAppStream("test_app", "hello");
    }

    @Test
    public void testResumeAppStreamByAppAndStream() {
        lssClient.resumeAppStream("test_app", "hello");
    }
}

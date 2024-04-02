package com.baidubce.services.lss.cases;

import com.baidubce.BceServiceException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wuyafei on 2017/3/13.
 */
public class DeleteStreamTest extends AbstractLssTest {

    private String playDomain = "play.testsdk.iefay.xyz";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testDeleteStream() {
        String app = "app_test_delete_stream";
        String stream = "test" + System.currentTimeMillis() / 1000;

        lssClient.createStream(playDomain, app, stream);

        lssClient.getStream(playDomain, app, stream);

        lssClient.deleteStream(playDomain, app, stream);

        boolean isThrown = false;

        try {
            lssClient.getStream(playDomain, app, stream);
        } catch (BceServiceException ex) {
            isThrown = true;
        }
        assertEquals(true, isThrown);
    }
}

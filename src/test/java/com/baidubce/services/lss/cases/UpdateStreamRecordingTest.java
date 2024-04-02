package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.CreateStreamResponse;
import com.baidubce.services.lss.model.GetStreamResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by wuyafei on 2017/3/13.
 */
public class UpdateStreamRecordingTest extends AbstractLssTest {

    private String playDomain = "play.testsdk.iefay.xyz";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testUpdateRecording() {
        String app = "test_update_recording";
        String stream = "test" + System.currentTimeMillis() / 1000;
        CreateStreamResponse response = lssClient.createStream(playDomain, app, stream);

        Assert.assertEquals("lss_java_sdk", response.getRecording());

        String newRecording = "lss_java_sdk_m3u8";
        lssClient.updateStreamRecording(playDomain, app, stream, newRecording);

        GetStreamResponse streamResponse = lssClient.getStream(playDomain, app, stream);
        Assert.assertEquals("lss_java_sdk_m3u8", streamResponse.getRecording());
        lssClient.deleteStream(playDomain, app, stream);
    }
}

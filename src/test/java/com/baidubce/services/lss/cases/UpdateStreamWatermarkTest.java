package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.GetStreamResponse;
import com.baidubce.services.lss.model.Watermarks;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

/**
 * Created by wuyafei on 2017/3/13.
 */
public class UpdateStreamWatermarkTest extends AbstractLssTest {

    private String playDomain = "play.testsdk.iefay.xyz";

    private String app = "test_update_watermark";

    private String stream = "test" + System.currentTimeMillis() / 1000;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        lssClient.createStream(playDomain, app, stream);
    }

    @Test
    public void testUpdateStreamWatermark() {

        GetStreamResponse response = lssClient.getStream(playDomain, app, stream);
        Assert.assertNull(response.getWatermarks());

        Watermarks watermarks = new Watermarks();
        watermarks.setImage(Collections.singletonList("lss_sdk_java_imw"));
        watermarks.setTimestamp(Collections.singletonList("lss_sdk_java_tsw"));
        lssClient.updateStreamWatermark(playDomain, app, stream, watermarks);

        GetStreamResponse streamResponse = lssClient.getStream(playDomain, app, stream);

        Assert.assertNotNull(streamResponse.getWatermarks());
        Assert.assertEquals("lss_sdk_java_tsw", streamResponse.getWatermarks().getTimestamp().get(0));
        Assert.assertEquals("lss_sdk_java_imw", streamResponse.getWatermarks().getImage().get(0));
    }

    @After
    public void tearDown() {
        lssClient.deleteStream(playDomain, app, stream);
    }
}

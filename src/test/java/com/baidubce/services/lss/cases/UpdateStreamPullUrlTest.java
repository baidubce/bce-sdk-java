package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.GetStreamResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by wuyafei on 2017/3/13.
 */
public class UpdateStreamPullUrlTest extends AbstractLssTest {

    private String playDomain = "play.testsdk.iefay.xyz";

    private String app = "test_update_pull_url";

    private String stream = "test" + System.currentTimeMillis() / 1000;

    @Before
    public void setup() throws Exception {
        this.setUp();
        lssClient.createStream(playDomain, app, stream);
    }

    @Test
    public void testUpdateStreamPullUrl() {
        GetStreamResponse response = lssClient.getStream(playDomain, app, stream);
        Assert.assertNull(response.getPublish().getPullUrl());

        String pullUrl = "rtmp://rtmp.testsdk.iefay.xyz";
        lssClient.updateStreamPullUrl(playDomain, app, stream, pullUrl);

        response = lssClient.getStream(playDomain, app, stream);
        Assert.assertEquals(pullUrl, response.getPublish().getPullUrl());
    }

    @After
    public void tearDown() {
        lssClient.deleteStream(playDomain, app, stream);
    }
}

package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.CreateStreamRequest;
import com.baidubce.services.lss.model.CreateStreamResponse;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

/**
 * Created by wuyafei on 16/10/14.
 */
public class CreateStreamTest extends AbstractLssTest {

    private String playDomain = "play.testsdk.iefay.xyz";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void createStreamWithLeastParams() {
        String app = "test";
        String stream = "test" + System.currentTimeMillis() / 1000;
        CreateStreamResponse response = lssClient.createStream(playDomain, app, stream);

        assertEquals(playDomain, response.getPlayDomain());
        assertEquals(app, response.getApp());
        assertEquals(stream, response.getPublish().getPushStream());
        assertEquals(response.getThumbnail(), "lss_java_sdk_thumbnail");
        assertEquals(response.getRecording(), "lss_java_sdk");

        lssClient.deleteStream(playDomain, app, stream);
    }

    @Test
    public void createStream() {
        String app = "test";
        String stream = "test" + System.currentTimeMillis() / 1000;

        CreateStreamRequest request = new CreateStreamRequest();
        request.withPlayDomain(playDomain)
                .withApp(app)
                .withPublish(new CreateStreamRequest.PublishInfo().withPushStream(stream));
        CreateStreamResponse response = lssClient.createStream(request);
        // System.out.println(response);
        assertEquals(playDomain, response.getPlayDomain());
        assertEquals(app, response.getApp());
        assertEquals(stream, response.getPublish().getPushStream());
        assertEquals(response.getThumbnail(), "lss_java_sdk_thumbnail");
        assertEquals(response.getRecording(), "lss_java_sdk");

        lssClient.deleteStream(playDomain, app, stream);
    }
}

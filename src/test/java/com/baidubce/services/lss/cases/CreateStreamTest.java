package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.CreateStreamRequest;
import com.baidubce.services.lss.model.CreateStreamResponse;
import com.baidubce.services.lss.model.Watermarks;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

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
    }

    @Test
    public void createStream() {
        String app = "test";
        String stream = "test" + System.currentTimeMillis() / 1000;

        CreateStreamRequest request = new CreateStreamRequest();
        Watermarks watermarks = new Watermarks();
        watermarks.setTimestamp(Arrays.asList("lss_sdk_java_tsw2"));
        watermarks.setImage(Arrays.asList("lss_sdk_java_imw"));
        request.withPlayDomain(playDomain)
                .withApp(app)
                .withPublish(new CreateStreamRequest.PublishInfo().withPushStream(stream))
                .withWatermarks(watermarks);
        CreateStreamResponse response = lssClient.createStream(request);
        // System.out.println(response);
        assertEquals(playDomain, response.getPlayDomain());
        assertEquals(app, response.getApp());
        assertEquals(stream, response.getPublish().getPushStream());
        assertEquals(response.getThumbnail(), "lss_java_sdk_thumbnail");
        assertEquals(response.getRecording(), "lss_java_sdk");
        assertEquals(response.getWatermarks().getTimestamp().contains("lss_sdk_java_tsw2"), true);
        assertEquals(response.getWatermarks().getImage().contains("lss_sdk_java_imw"), true);
    }
}

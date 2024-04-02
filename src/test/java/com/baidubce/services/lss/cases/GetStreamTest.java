package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.GetStreamRequest;
import com.baidubce.services.lss.model.GetStreamResponse;
import com.baidubce.services.lss.model.ListStreamResponse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by wuyafei on 16/10/14.
 */
public class GetStreamTest extends AbstractLssTest {

    private String playDomain = "play.testsdk.iefay.xyz";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testGetStream() {
        String app = "test";
        ListStreamResponse listStreamResponse = lssClient.listStream(playDomain);
        if (listStreamResponse.getStreams().size() >= 1) {
            String stream = listStreamResponse.getStreams().get(0).getPublish().getPushStream();
            GetStreamResponse response = lssClient.getStream(playDomain, app, stream);
            // System.out.println(response);
            assertEquals(response.getApp(), app);
            assertEquals(response.getPublish().getPushStream(), stream);
            assertEquals(response.getPlayDomain(), playDomain);
        }
    }

    @Test
    public void testGetStreamWithRequest() {
        String app = "test";
        ListStreamResponse listStreamResponse = lssClient.listStream(playDomain);
        if (listStreamResponse.getStreams().size() >= 1) {
            String stream = listStreamResponse.getStreams().get(0).getPublish().getPushStream();
            GetStreamRequest request = new GetStreamRequest();
            request.withPlayDomain(playDomain).withApp(app).withStream(stream);
            GetStreamResponse response = lssClient.getStream(request);
            // System.out.println(response);
            assertEquals(response.getPlayDomain(), playDomain);
            assertEquals(response.getAudit(), "default");
            String rtmpPlayUrl = "rtmp://" + playDomain + "/" + app + "/" + stream;
            if (response.getPlay().getRtmpUrl() != null) {
                assertEquals(response.getPlay().getRtmpUrl(), rtmpPlayUrl);
            } else if (!response.getPlay().getRtmpUrls().isEmpty()) {
                assertEquals(response.getPlay().getRtmpUrls().get("L0"), rtmpPlayUrl);
            }
            assertEquals(response.getStatistics().getBandwidthInBps() == 0, true);
            assertEquals(response.getStatistics().getPlayCount() == 0, true);
        }
    }
}

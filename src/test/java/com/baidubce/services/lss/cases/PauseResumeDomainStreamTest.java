package com.baidubce.services.lss.cases;

import com.baidubce.services.lss.model.GetStreamResponse;
import com.baidubce.services.lss.model.ListStreamResponse;
import com.baidubce.services.lss.model.LiveStream;
import com.baidubce.services.lss.model.ResumeDomainStreamRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by wuyafei on 16/10/17.
 */
public class PauseResumeDomainStreamTest extends AbstractLssTest {

    private String domain = "play.testsdk.iefay.xyz";

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testPauseResumeStream() {
        ListStreamResponse response = lssClient.listStream(domain);
        for (LiveStream liveStream : response.getStreams()) {
            if (!("PAUSED".equals(liveStream.getStatus()) || "CLOSED".equals(liveStream.getStatus()))) {
                String app = liveStream.getApp();
                String stream = liveStream.getPublish().getPushStream();
                lssClient.pauseDomainStream(domain, app, stream);
                GetStreamResponse getStreamResponse = lssClient.getStream(domain, app, stream);
                // System.out.println(getStreamResponse);
                assertEquals(getStreamResponse.getStatus(), "PAUSED");
                ResumeDomainStreamRequest request = new ResumeDomainStreamRequest();
                request.withApp(app).withDomain(domain).withStream(stream);
                lssClient.resumeDomainStream(request);
                getStreamResponse = lssClient.getStream(domain, app, stream);
                // System.out.println(getStreamResponse);
                assertEquals(getStreamResponse.getStatus(), "READY");
                break;
            }
        }

    }
}

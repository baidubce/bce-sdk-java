package com.baidubce.services.vod.ut;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.GetMediaResourceResponse;
import com.baidubce.services.vod.model.ReTranscodeResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReTranscodeTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gaqi5dznriez85n3";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReTranscode() {
        GetMediaResourceResponse r1 = vodClient.getMediaResource(mediaId);
        assertEquals(r1.getStatus(), "PUBLISHED");
        ReTranscodeResponse response = vodClient.reTranscode(mediaId);
        GetMediaResourceResponse r2 = vodClient.getMediaResource(mediaId);
        assertEquals(r2.getStatus(), "RUNNING");
    }
}

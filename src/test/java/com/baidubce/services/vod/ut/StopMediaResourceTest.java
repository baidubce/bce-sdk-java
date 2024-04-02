package com.baidubce.services.vod.ut;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.vod.AbstractVodTest;

public class StopMediaResourceTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gaqi5dznriez85n3";
    }

    @After
    public void tearDown() throws Exception {
        vodClient.publishMediaResource(mediaId);
    }

    @Test
    public void testStopMediaResource() {
        assertEquals(vodClient.getMediaResource(mediaId).getStatus(), "PUBLISHED");
        vodClient.stopMediaResource(mediaId);
        assertEquals(vodClient.getMediaResource(mediaId).getStatus(), "DISABLED");

    }

}

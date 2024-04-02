package com.baidubce.services.vod.ut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.vod.AbstractVodTest;

public class PublishMediaResourceTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gaqi5dznriez85n3";
        vodClient.stopMediaResource(mediaId);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPublishMediaResource() {
        assertNotEquals(vodClient.getMediaResource(mediaId).getStatus(), "PUBLISHED");
        vodClient.publishMediaResource(mediaId);
        assertEquals(vodClient.getMediaResource(mediaId).getStatus(), "PUBLISHED");
    }

}

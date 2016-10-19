package com.baidubce.services.vod.ut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.GetMediaResourceResponse;

public class GetMediaResourceTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gbbdvcve3e3238k1";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetMediaSource() {
        GetMediaResourceResponse response = vodClient.getMediaResource(mediaId);
        System.out.println(response);
        assertEquals("PUBLISHED", response.getStatus());
        assertEquals("Gail Sophicha", response.getAttributes().getTitle());
        assertEquals("Back To December", response.getAttributes().getDescription());
        assertTrue(61767567 == response.getMeta().getSizeInBytes());
        assertTrue(286 == response.getMeta().getDurationInSeconds());
    }

}

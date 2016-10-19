package com.baidubce.services.vod.ut;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.GetMediaResourceResponse;

public class DeleteMediaResourceTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        createSampleMedia();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDeleteMediaResource() {

        assertEquals("PUBLISHED", vodClient.getMediaResource(mediaId).getStatus());
        vodClient.deleteMediaResource(mediaId);
        try {
            GetMediaResourceResponse response = vodClient.getMediaResource(mediaId);
            System.out.println(response);
            fail("Failed to delete resource " + mediaId);
        } catch (Exception e) {
            // Should throw out exception here

        }
    }

}

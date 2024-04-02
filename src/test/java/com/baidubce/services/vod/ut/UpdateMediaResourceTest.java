package com.baidubce.services.vod.ut;


import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.GetMediaResourceResponse;

public class UpdateMediaResourceTest extends AbstractVodTest {
    String oldTitle;
    String oldDescription;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gaqi5dznriez85n3";
        GetMediaResourceResponse response = vodClient.getMediaResource(mediaId);
        oldTitle = response.getAttributes().getTitle();
        oldDescription = response.getAttributes().getDescription();
        System.out.println("[titel] = " + oldTitle + ", [description] = " + oldDescription);
    }

    @After
    public void tearDown() throws Exception {
        vodClient.updateMediaResource(mediaId, oldTitle, oldDescription);
    }

    @Test
    public void testUpdateMediaResource() {
        String title = "new Title";
        String description = "new description";
        vodClient.updateMediaResource(mediaId, title, description);
        GetMediaResourceResponse response = vodClient.getMediaResource(mediaId);
        assertEquals(title, response.getAttributes().getTitle());
        assertEquals(description, response.getAttributes().getDescription());
    }

}

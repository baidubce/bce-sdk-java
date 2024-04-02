package com.baidubce.services.vod.ut;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.GetMediaResourceResponse;

public class GetMediaResourceTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gmtywt93a9bvz688";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetMediaSource() {
        GetMediaResourceResponse response = vodClient.getMediaResource(mediaId);
        System.out.println(response);
    }

}

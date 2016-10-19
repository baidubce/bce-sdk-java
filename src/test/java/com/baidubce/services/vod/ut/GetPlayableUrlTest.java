package com.baidubce.services.vod.ut;


import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.GetPlayableUrlResponse;

public class GetPlayableUrlTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gaqi5dznriez85n3";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetPlayableUrl() {
        GetPlayableUrlResponse response = vodClient.getPlayableUrl(mediaId);
        assertTrue(response.getSuccess());
        System.out.println(response);
    }

}

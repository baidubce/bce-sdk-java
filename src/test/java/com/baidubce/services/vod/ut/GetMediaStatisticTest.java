package com.baidubce.services.vod.ut;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.GetMediaStatisticResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GetMediaStatisticTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gfxj1qfuis44avyt";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetMediaStatisticWithAggregate() {
        GetMediaStatisticResponse response = vodClient.getMediaStatistic(mediaId, null, null, true);
        System.out.println(response);
    }
    @Test
    public void testGetMediaStatisticWithoutAggregate() {
        GetMediaStatisticResponse response = vodClient.getMediaStatistic(mediaId, null, null, false);
        System.out.println(response);
    }
}

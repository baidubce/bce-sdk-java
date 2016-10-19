package com.baidubce.services.vod.ut;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.GenerateMediaPlayerCodeResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GenerateMediaPlayerCodeTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gfxj1qfuis44avyt";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGenerateMediaPlayerCode() {
        GenerateMediaPlayerCodeResponse response = vodClient.generateMediaPlayerCode(mediaId, 160, 90, true);
        System.out.println(response);

    }
}

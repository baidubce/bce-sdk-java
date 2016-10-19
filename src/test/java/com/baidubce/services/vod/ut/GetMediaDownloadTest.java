package com.baidubce.services.vod.ut;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.GetMediaSourceDownloadResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class GetMediaDownloadTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        mediaId = "mda-gaqi5dznriez85n3";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetMediaDownload() {
        GetMediaSourceDownloadResponse response = vodClient.getMediaSourceDownload(mediaId, -1);
        System.out.println(response.getSourceUrl());
    }
}

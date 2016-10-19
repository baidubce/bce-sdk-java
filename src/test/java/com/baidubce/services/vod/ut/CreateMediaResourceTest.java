package com.baidubce.services.vod.ut;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.CreateMediaResourceResponse;

public class CreateMediaResourceTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void uploadSmallFileTest() throws FileNotFoundException {
        File file = new File("/Users/baidu/Downloads/a.mov");
        String title = "Gail Sophicha";
        String description = "Back To December";
        CreateMediaResourceResponse response = vodClient.createMediaResource(title, description, file);
        String mediaId = response.getMediaId();
        System.out.println("meidaId = " + mediaId);
    }

    @Test
    public void uploadLargeFileTest() throws FileNotFoundException {
        File file = new File("/Volumes/sd/video/Martian.mp4");
        String title = "Gail Sophicha";
        String description = "Back To December";
        CreateMediaResourceResponse response = vodClient.createMediaResource(title, description, file);
        String mediaId = response.getMediaId();
        System.out.println("meidaId = " + mediaId);
    }

}

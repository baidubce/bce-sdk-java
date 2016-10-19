package com.baidubce.services.vod.ut;

import com.baidubce.util.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.ListMediaResourceResponse;
import com.baidubce.services.vod.model.MediaResource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListMediaResourceTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testListMediaResources() {
        ListMediaResourceResponse responce = vodClient.listMediaResources();

        for (MediaResource mediaResource : responce.getMedia()) {
            String mediaId = mediaResource.getMediaId();
            String status = mediaResource.getStatus();
            String createTime = mediaResource.getCreateTime();
            String publishTime = mediaResource.getPublishTime();
            String title = mediaResource.getAttributes().getTitle();
            String description = mediaResource.getAttributes().getDescription();
            long duration = mediaResource.getMeta().getDurationInSeconds();
            long size = mediaResource.getMeta().getSizeInBytes();
            System.out.println(mediaResource);
        }
    }

    @Test
    public void testListMediaResourcesWithPage() {
        ListMediaResourceResponse responce = vodClient.listMediaResources(1, 2, null, null, null, null);
        assertEquals(responce.getMedia().size(), 2);
        for (MediaResource mediaResource : responce.getMedia()) {
            String mediaId = mediaResource.getMediaId();
            String status = mediaResource.getStatus();
            String createTime = mediaResource.getCreateTime();
            String publishTime = mediaResource.getPublishTime();
            String title = mediaResource.getAttributes().getTitle();
            String description = mediaResource.getAttributes().getDescription();
            long duration = mediaResource.getMeta().getDurationInSeconds();
            long size = mediaResource.getMeta().getSizeInBytes();
            System.out.println(mediaResource);
        }
    }

    @Test
    public void testListMediaResourcesWithPageBeginEnd() {
        Date begin = DateUtils.parseAlternateIso8601Date("2016-01-15T08:46:56Z");
        Date end = DateUtils.parseAlternateIso8601Date("2016-01-18T08:46:56Z");
        ListMediaResourceResponse responce = vodClient.listMediaResources(1, 20, null, begin, end, null);
        assertTrue(responce.getMedia().size() > 0);
        for (MediaResource mediaResource : responce.getMedia()) {
            String mediaId = mediaResource.getMediaId();
            String status = mediaResource.getStatus();
            String createTime = mediaResource.getCreateTime();
            String publishTime = mediaResource.getPublishTime();
            String title = mediaResource.getAttributes().getTitle();
            String description = mediaResource.getAttributes().getDescription();
            long duration = mediaResource.getMeta().getDurationInSeconds();
            long size = mediaResource.getMeta().getSizeInBytes();
            System.out.println(mediaResource);
        }
    }
}

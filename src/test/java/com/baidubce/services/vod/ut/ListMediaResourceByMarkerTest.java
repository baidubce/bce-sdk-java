/*
 * Copyright 2016 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.vod.ut;

import com.baidubce.services.vod.AbstractVodTest;
import com.baidubce.services.vod.model.ListMediaResourceByMarkerResponse;
import com.baidubce.services.vod.model.MediaResource;
import com.baidubce.util.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertTrue;

public class ListMediaResourceByMarkerTest extends AbstractVodTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
    }



    @Test
    public void testListMediaResourcesByMarkerWithPage() {
        ListMediaResourceByMarkerResponse responce = vodClient.listMediaResourcesByMarker(null,
                1, null, null, null, null);
        System.out.println(responce.getMedia().size());
        System.out.println(responce.getMarker());
        System.out.println(responce.getNextMarker());
        System.out.println(responce.getIsTruncated());
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
    public void testListMediaResourcesByMarkerWithPageBeginEnd() {
        Date begin = DateUtils.parseAlternateIso8601Date("2016-01-15T08:46:56Z");
        Date end = DateUtils.parseAlternateIso8601Date("2016-01-18T08:46:56Z");
        ListMediaResourceByMarkerResponse responce = vodClient.listMediaResourcesByMarker(null,
                2, null, begin, end, null);
        System.out.println("testListMediaResourcesByMarkerWithPageBeginEnd");
        System.out.println(responce.getMedia().size());
        assertTrue(responce.getMedia().size() > 0);
        System.out.println(responce.getMarker());
        System.out.println(responce.getNextMarker());
        System.out.println(responce.getIsTruncated());
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

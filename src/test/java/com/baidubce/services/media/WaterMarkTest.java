/*
 * Copyright (c) 2015 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.media;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.media.model.GetWaterMarkResponse;
import com.baidubce.services.media.model.WaterMark;

public class WaterMarkTest extends AbstractMediaTest {
    String watermarkId = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        if (watermarkId != null) {
            mediaClient.deleteWaterMark(watermarkId);
            watermarkId = null;
        }

    }

    @Test
    public void testListWaterMark() {
        List<WaterMark> watermarks = mediaClient.listWaterMark().getWatermarks();
        assertTrue("Failed to get watermarks.", watermarks.size() > 0);
        for (WaterMark wm : watermarks) {
            System.out.println("watermarkId = " + wm.getWatermarkId());
            System.out.println("bucket = " + wm.getBucket());
            System.out.println("key = " + wm.getKey());
            System.out.println("createTime = " + wm.getCreateTime());
            System.out.println("horizontalOffsetInPixel = " + wm.getHorizontalOffsetInPixel());
            System.out.println("VerticalOffsetInPixel = " + wm.getVerticalOffsetInPixel());
            System.out.println();
        }
    }

    @Test
    public void testGetWaterMark() {
        String watermarkId = "wmk-fgnhmagnyzfj2gze";
        GetWaterMarkResponse wm = mediaClient.getWaterMark(watermarkId);
        assertEquals("Failed to verify watermarkId", watermarkId, wm.getWatermarkId());
        System.out.println("\nbucket = " + wm.getBucket());
        System.out.println("key = " + wm.getKey());
        System.out.println("createTime = " + wm.getCreateTime());
        System.out.println("horizontalAlignment = " + wm.getHorizontalAlignment());
        System.out.println("verticalAlignment = " + wm.getVerticalAlignment());
        System.out.println("horizontalOffsetInPixel = " + wm.getHorizontalOffsetInPixel());
        System.out.println("verticalOffsetInPixel = " + wm.getVerticalOffsetInPixel());
    }
    
    @Test
    public void testCreateWaterMark1() {
        String bucket = "dapeng-test";
        String key = "media-test/sdk-overview.png";
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;

        watermarkId =
                mediaClient.createWaterMark(bucket, key, horizontalOffsetInPixel, verticalOffsetInPixel)
                        .getWatermarkId();
        System.out.println(watermarkId);
        
        GetWaterMarkResponse wm = mediaClient.getWaterMark(watermarkId);
        System.out.println(wm);
        assertEquals("Failed verify bucket.", wm.getWatermarkId(), watermarkId);
        assertEquals("Failed verify bucket.", wm.getBucket(), bucket);
        assertEquals("Failed verify bucket.", wm.getKey(), key);
        assertEquals("Failed verify bucket.", wm.getHorizontalOffsetInPixel(), new Integer(horizontalOffsetInPixel));
        assertEquals("Failed verify bucket.", wm.getVerticalOffsetInPixel(), new Integer(verticalOffsetInPixel));
    }

    @Test
    public void testCreateWaterMark2() {
        String bucket = "dapeng-test";
        String key = "media-test/sdk-overview.png";
        String horizontalAlignment = "left";
        String verticalAlignment = "top";

        watermarkId =
                mediaClient.createWaterMark(bucket, key, horizontalAlignment, verticalAlignment)
                        .getWatermarkId();
        
        GetWaterMarkResponse wm = mediaClient.getWaterMark(watermarkId);
        System.out.println(wm);
        assertEquals("Failed verify bucket.", wm.getWatermarkId(), watermarkId);
        assertEquals("Failed verify bucket.", wm.getBucket(), bucket);
        assertEquals("Failed verify bucket.", wm.getKey(), key);
        assertEquals("Failed verify bucket.", wm.getHorizontalAlignment(), horizontalAlignment);
        assertEquals("Failed verify bucket.", wm.getVerticalAlignment(), verticalAlignment);
    }

    @Test
    public void testCreateWaterMark3() {
        String bucket = "dapeng-test";
        String key = "media-test/sdk-overview.png";
        String horizontalAlignment = "left";
        String verticalAlignment = "top";
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;

        watermarkId =
                mediaClient.createWaterMark(bucket, key, horizontalOffsetInPixel, verticalOffsetInPixel)
                        .getWatermarkId();
        
        GetWaterMarkResponse wm = mediaClient.getWaterMark(watermarkId);
        System.out.println(wm);
        assertEquals("Failed verify bucket.", wm.getWatermarkId(), watermarkId);
        assertEquals("Failed verify bucket.", wm.getBucket(), bucket);
        assertEquals("Failed verify bucket.", wm.getKey(), key);
        assertEquals("Failed verify bucket.", wm.getHorizontalOffsetInPixel(), new Integer(horizontalOffsetInPixel));
        assertEquals("Failed verify bucket.", wm.getVerticalOffsetInPixel(), new Integer(verticalOffsetInPixel));
        assertEquals("Failed verify bucket.", wm.getHorizontalAlignment(), horizontalAlignment);
        assertEquals("Failed verify bucket.", wm.getVerticalAlignment(), verticalAlignment);
    }
    
    @Test
    public void testDeleteWaterMark() {
        String bucket = "dapeng-test";
        String key = "media-test/sdk-overview.png";
        String horizontalAlignment = "left";
        String verticalAlignment = "top";
        int horizontalOffsetInPixel = 20;
        int verticalOffsetInPixel = 10;

        watermarkId =
                mediaClient.createWaterMark(bucket, key, horizontalAlignment, verticalAlignment,
                        horizontalOffsetInPixel, verticalOffsetInPixel)
                        .getWatermarkId();
        System.out.println(mediaClient.getWaterMark(watermarkId));
        
        mediaClient.deleteWaterMark(watermarkId);
        try {
            System.out.println(mediaClient.getWaterMark(watermarkId));
            fail("The water mark " + watermarkId + "failed to be deleted.");
        } catch (Exception e) {
            // expect to catch Exception.
        } finally {
            watermarkId = null;
        }

    }
    
}

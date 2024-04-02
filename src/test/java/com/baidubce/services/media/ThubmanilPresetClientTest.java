/*
 * Copyright 2020 Baidu, Inc.
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

import com.baidubce.services.media.model.CreateThumbnailPresetResponse;
import com.baidubce.services.media.model.GetThumbnailPresetResponse;
import com.baidubce.services.media.model.HighlightOutputCfg;
import com.baidubce.services.media.model.ListThumbnailPresetsResponse;
import com.baidubce.services.media.model.SpriteOutputCfg;
import com.baidubce.services.media.model.ThumbnailPresetCapture;
import com.baidubce.services.media.model.ThumbnailPresetTarget;
import com.baidubce.services.media.model.UpdateThumbnailPresetRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ThubmanilPresetClientTest extends AbstractMediaTest {
   
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    } 

    @Test
    public void testCreateThumbnailPreset() {
        thumbnailPresetName = "test_thumbnail_preset";
        createThumbnailPreset();
    }

    @Test
    public void testCreateThumbnailPreset1() {
        thumbnailPresetName = "test_thumbnail_preset";
        mediaClient.createThumbnailPreset(thumbnailPresetName, "", 
                new ThumbnailPresetTarget().withFormat("png"), 
                new ThumbnailPresetCapture().withMode("auto"));
        checkThumbnailPresetExist(thumbnailPresetName, true);
    }

    @Test
    public void testCreateThumbnailPreset2() {
        thumbnailPresetName = "test_thumbnail_preset";
        mediaClient.createThumbnailPreset(thumbnailPresetName, "", 
                new ThumbnailPresetTarget().withFormat("png").withSizingPolicy("keep")
                        .withWidthInPixel(400).withHeightInPixel(400), 
                new ThumbnailPresetCapture().withMode("manual").withStartTimeInSecond(0.0)
                        .withEndTimeInSecond(10.0).withIntervalInSecond(0.04).withSkipBlackFrame(true));
        checkThumbnailPresetExist(thumbnailPresetName, true);
    }

    @Test
    public void testCreateThumbnailPreset3() {
        thumbnailPresetName = "test_thumbnail_preset";
        HighlightOutputCfg highlightOutputCfg = new HighlightOutputCfg();
        highlightOutputCfg.setDurationInSecond(new Float(5.0));
                
        mediaClient.createThumbnailPreset(thumbnailPresetName, "", 
                new ThumbnailPresetTarget().withFormat("gif").withSizingPolicy("keep")
                        .withWidthInPixel(400).withHeightInPixel(400), 
                new ThumbnailPresetCapture().withMode("highlight").withHighlightOutputCfg(highlightOutputCfg));
        checkThumbnailPresetExist(thumbnailPresetName, true);
    }

    @Test
    public void testGetThumbnailPreset1() {
        thumbnailPresetName = "test_thumbnail_preset";
        createThumbnailPreset();
        GetThumbnailPresetResponse resp = mediaClient.getThumbnailPreset(thumbnailPresetName);
        assertEquals(resp.getPresetName(), thumbnailPresetName);
        assertEquals(resp.getDescription(), thumbnailPresetName);
        assertEquals(resp.getCapture().getMode(), "split");
        assertEquals(resp.getCapture().getFrameNumber(), new Integer(20));
        assertEquals(resp.getCapture().getStartTimeInSecond(), new Double(0.5));
        assertEquals(resp.getCapture().getEndTimeInSecond(), new Double(10.5));
        assertEquals(resp.getCapture().getMinIntervalInSecond(), new Double(0.01));
        assertEquals(resp.getTarget().getFormat(), "gif");
        assertEquals(resp.getTarget().getFrameRate(), new Double(10.0));
        assertEquals(resp.getTarget().getGifQuality(), "high");
        assertEquals(resp.getTarget().getSizingPolicy(), "keep");
        assertEquals(resp.getTarget().getWidthInPixel(), new Integer(400));
        assertEquals(resp.getTarget().getHeightInPixel(), new Integer(400));
    }

    @Test
    public void testGetThumbnailPreset2() {
        thumbnailPresetName = "test_thumbnail_preset";
        SpriteOutputCfg spriteOutputCfg = new SpriteOutputCfg();
        spriteOutputCfg.setColumns(4);
        spriteOutputCfg.setRows(5);

        mediaClient.createThumbnailPreset(thumbnailPresetName, "", 
                new ThumbnailPresetTarget().withFormat("png").withSizingPolicy("keep")
                        .withWidthInPixel(400).withHeightInPixel(400).withSpriteOutputCfg(spriteOutputCfg), 
                new ThumbnailPresetCapture().withMode("manual").withStartTimeInSecond(0.0)
                        .withEndTimeInSecond(10.0).withIntervalInSecond(0.04).withSkipBlackFrame(true));

        GetThumbnailPresetResponse resp = mediaClient.getThumbnailPreset(thumbnailPresetName);
        assertEquals(resp.getPresetName(), thumbnailPresetName);
        assertEquals(resp.getCapture().getMode(), "manual");
        assertEquals(resp.getCapture().getStartTimeInSecond(), new Double(0.0));
        assertEquals(resp.getCapture().getEndTimeInSecond(), new Double(10.0));
        assertEquals(resp.getCapture().getIntervalInSecond(), new Double(0.04));
        assertEquals(resp.getTarget().getFormat(), "png");
        assertEquals(resp.getTarget().getSizingPolicy(), "keep");
        assertEquals(resp.getTarget().getWidthInPixel(), new Integer(400));
        assertEquals(resp.getTarget().getHeightInPixel(), new Integer(400));
        assertEquals(resp.getTarget().getSpriteOutputCfg().getColumns(), new Integer(4));
        assertEquals(resp.getTarget().getSpriteOutputCfg().getRows(), new Integer(5));
    }

    @Test
    public void testGetThumbnailPreset3() {
        thumbnailPresetName = "test_thumbnail_preset";
        HighlightOutputCfg highlightOutputCfg = new HighlightOutputCfg();
        highlightOutputCfg.setDurationInSecond(new Float(5.0));
                
        mediaClient.createThumbnailPreset(thumbnailPresetName, "", 
                new ThumbnailPresetTarget().withFormat("gif").withSizingPolicy("keep")
                        .withWidthInPixel(400).withHeightInPixel(400), 
                new ThumbnailPresetCapture().withMode("highlight").withHighlightOutputCfg(highlightOutputCfg));
        checkThumbnailPresetExist(thumbnailPresetName, true);

        GetThumbnailPresetResponse resp = mediaClient.getThumbnailPreset(thumbnailPresetName);
        assertEquals(resp.getPresetName(), thumbnailPresetName);
        assertEquals(resp.getCapture().getMode(), "highlight");
        assertEquals(resp.getCapture().getHighlightOutputCfg().getDurationInSecond(), new Float(5.0));
        assertEquals(resp.getTarget().getFormat(), "gif");
        assertEquals(resp.getTarget().getSizingPolicy(), "keep");
        assertEquals(resp.getTarget().getWidthInPixel(), new Integer(400));
        assertEquals(resp.getTarget().getHeightInPixel(), new Integer(400));
    }

    @Test
    public void testDeleteThumbnailPreset() {
        thumbnailPresetName = "test_thumbnail_preset";
        createThumbnailPreset();
        mediaClient.deleteThumbnailPreset(thumbnailPresetName);
        checkThumbnailPresetExist(thumbnailPresetName, false);
        thumbnailPresetName = null;
    }

    @Test
    public void testUpdateThumbnailPreset1() {
        thumbnailPresetName = "test_thumbnail_preset";
        createThumbnailPreset();
        UpdateThumbnailPresetRequest request = new UpdateThumbnailPresetRequest();
        request.withPreset(mediaClient.getThumbnailPreset(thumbnailPresetName));
        request.setDescription("updated_description");
        request.getCapture().withMode("manual")
                .withStartTimeInSecond(0.0)
                .withEndTimeInSecond(10.0)
                .withIntervalInSecond(1.0);
        request.getTarget().withFormat("gif")
                .withGifQuality("high")
                .withHeightInPixel(100)
                .withWidthInPixel(200)
                .withFrameRate(1.0);
        mediaClient.updateThumbnailPreset(request);

        GetThumbnailPresetResponse resp = mediaClient.getThumbnailPreset(thumbnailPresetName);
        assertEquals(resp.getDescription(), request.getDescription());
        assertEquals(resp.getCapture().getMode(), request.getCapture().getMode());
        assertEquals(resp.getCapture().getStartTimeInSecond(), request.getCapture().getStartTimeInSecond());
        assertEquals(resp.getCapture().getEndTimeInSecond(), request.getCapture().getEndTimeInSecond());
        assertEquals(resp.getCapture().getIntervalInSecond(), request.getCapture().getIntervalInSecond());
        assertEquals(resp.getTarget().getFormat(), request.getTarget().getFormat());
        assertEquals(resp.getTarget().getGifQuality(), request.getTarget().getGifQuality());
        assertEquals(resp.getTarget().getHeightInPixel(), request.getTarget().getHeightInPixel());
        assertEquals(resp.getTarget().getWidthInPixel(), request.getTarget().getWidthInPixel());
        assertEquals(resp.getTarget().getFrameRate(), request.getTarget().getFrameRate());
    }

    @Test
    public void testUpdateThumbnailPreset2() {
        thumbnailPresetName = "test_thumbnail_preset";
        createThumbnailPreset();
        UpdateThumbnailPresetRequest request = new UpdateThumbnailPresetRequest();
        request.withPreset(mediaClient.getThumbnailPreset(thumbnailPresetName));
        request.getCapture().withMode("splitss0")
                .withStartTimeInSecond(0.0)
                .withEndTimeInSecond(10.0)
                .withFrameNumber(30)
                .withMinIntervalInSecond(0.05);
        request.getTarget().withFormat("png")
                .withGifQuality(null)
                .withHeightInPixel(100)
                .withWidthInPixel(200)
                .withFrameRate(null);
        mediaClient.updateThumbnailPreset(request);

        GetThumbnailPresetResponse resp = mediaClient.getThumbnailPreset(thumbnailPresetName);
        assertEquals(resp.getCapture().getMode(), request.getCapture().getMode());
        assertEquals(resp.getCapture().getStartTimeInSecond(), request.getCapture().getStartTimeInSecond());
        assertEquals(resp.getCapture().getEndTimeInSecond(), request.getCapture().getEndTimeInSecond());
        assertEquals(resp.getCapture().getMinIntervalInSecond(), request.getCapture().getMinIntervalInSecond());
        assertEquals(resp.getTarget().getFormat(), request.getTarget().getFormat());
        assertEquals(resp.getTarget().getGifQuality(), request.getTarget().getGifQuality());
        assertEquals(resp.getTarget().getHeightInPixel(), request.getTarget().getHeightInPixel());
        assertEquals(resp.getTarget().getWidthInPixel(), request.getTarget().getWidthInPixel());
        assertEquals(resp.getTarget().getFrameRate(), request.getTarget().getFrameRate());
    }

    @Test
    public void testUpdateThumbnailPreset3() {
        thumbnailPresetName = "test_thumbnail_preset";
        createThumbnailPreset();
        checkThumbnailPresetExist(thumbnailPresetName, true);

        UpdateThumbnailPresetRequest request = new UpdateThumbnailPresetRequest();
        HighlightOutputCfg highlightOutputCfg = new HighlightOutputCfg();
        highlightOutputCfg.setDurationInSecond(new Float(5.0));
                
        request.withPresetName(thumbnailPresetName);
        request.setTarget(new ThumbnailPresetTarget().withFormat("gif").withSizingPolicy("keep")
                .withWidthInPixel(400).withHeightInPixel(400));
        request.setCapture(new ThumbnailPresetCapture().withMode("highlight")
                .withHighlightOutputCfg(highlightOutputCfg));

        mediaClient.updateThumbnailPreset(request);

        GetThumbnailPresetResponse resp = mediaClient.getThumbnailPreset(thumbnailPresetName);
        assertEquals(resp.getCapture().getMode(), request.getCapture().getMode());
        assertEquals(resp.getCapture().getHighlightOutputCfg().getDurationInSecond(), 
                request.getCapture().getHighlightOutputCfg().getDurationInSecond());
        assertEquals(resp.getTarget().getFormat(), request.getTarget().getFormat());
        assertEquals(resp.getTarget().getHeightInPixel(), request.getTarget().getHeightInPixel());
        assertEquals(resp.getTarget().getWidthInPixel(), request.getTarget().getWidthInPixel());
    }

    @Test
    public void testListThumbnailPresets() {
        thumbnailPresetName = "test_thumbnail_preset";
        createThumbnailPreset();
        boolean presetExist = false;
        ListThumbnailPresetsResponse resp = mediaClient.listThumbnailPresets();
        for (GetThumbnailPresetResponse preset : resp.getPresets()) {
            System.out.println(preset.getPresetName());
            presetExist = presetExist || preset.getPresetName().equals(thumbnailPresetName);
        }
        assertTrue("list presets size should be greater than 0. ", presetExist);
    }

}
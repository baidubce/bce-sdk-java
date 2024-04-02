/*
 * Copyright 2015 Baidu, Inc.
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
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.media.model.Area;
import com.baidubce.services.media.model.CreateThumbnailJobRequest;
import com.baidubce.services.media.model.GetThumbnailJobResponse;
import com.baidubce.services.media.model.ListThumbnailJobsResponse;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.ThumbnailCapture;
import com.baidubce.services.media.model.ThumbnailJobStatus;
import com.baidubce.services.media.model.ThumbnailTarget;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ThumbnailTest extends AbstractMediaTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreateThumbnailJobs1() throws JsonProcessingException {
        String pipelineName = "wlq";
        String sourceKey = "beijing.mp4";
        String jobId = mediaClient.createThumbnailJob(pipelineName, sourceKey).getJobId();

        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        assertEquals("sourceKey wrong, should be: " + sourceKey, resp.getSource().getKey(), sourceKey);
    }

    @Test
    public void testCreateThumbnailJobs3() throws JsonProcessingException {
        String pipelineName = "wlq";
        String sourceKey = "beijing.mp4";
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture =
                new ThumbnailCapture().withMode("manual").withStartTimeInSecond(0).withEndTimeInSecond(5)
                        .withIntervalInSecond(1);
        String jobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();

        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        assertEquals("sourceKey wrong, should be: " + sourceKey, resp.getSource().getKey(), sourceKey);
        assertEquals("capture mode wrong, should be: manual", resp.getCapture().getMode(), "manual");
    }

    @Test
    public void testCreateThumbnailJobs4() throws JsonProcessingException {
        String pipelineName = "wlq";
        String sourceKey = "beijing.mp4";
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep")
                .withKeyPrefix("beijing_delogo_splitss0");
        ThumbnailCapture capture =
                new ThumbnailCapture().withMode("split").withFrameNumber(30);
        Area delogoArea = new Area().withX(10).withY(20).withWidth(100).withHeight(200);
        String jobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture, delogoArea).getJobId();

        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        System.out.println(JsonUtils.toJsonPrettyString(resp));
        assertEquals("sourceKey wrong, should be: " + sourceKey, resp.getSource().getKey(), sourceKey);
        assertEquals("capture mode wrong, should be: split", resp.getCapture().getMode(), "split");
        assertEquals("delogoArea wrong, input/output delogoArea.height not equal.",
                resp.getDelogoArea().getHeight(), delogoArea.getHeight());
    }

    @Test
    public void testCreateThumbnailJobs5() throws JsonProcessingException {
        String pipelineName = "wlq";
        String sourceKey = "beijing.mp4";
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep")
                .withKeyPrefix("beijing_delogo_splitss0_crop");
        ThumbnailCapture capture =
                new ThumbnailCapture().withMode("splitss0").withFrameNumber(10);
        Area delogoArea = new Area().withX(10).withY(20).withWidth(50).withHeight(100);
        Area crop = new Area().withX(100).withY(0).withWidth(150).withHeight(200);
        String jobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture, delogoArea, crop)
                .getJobId();

        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        System.out.println(JsonUtils.toJsonPrettyString(resp));
        assertEquals("sourceKey wrong, should be: " + sourceKey, resp.getSource().getKey(), sourceKey);
        assertEquals("capture mode wrong, should be: splitss0", resp.getCapture().getMode(), "splitss0");
        assertEquals("delogoArea wrong, input/output delogoArea.height not equal.",
                resp.getDelogoArea().getHeight(), delogoArea.getHeight());
    }

    @Test
    public void testCreateThumbnailJobs6() throws JsonProcessingException {
        String pipelineName = "wlq";
        String sourceKey = "beijing.mp4";
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep")
                .withKeyPrefix("beijing_delogo_splitss0_crop");
        ThumbnailCapture capture =
                new ThumbnailCapture().withMode("splitss0").withFrameNumber(10).withMinIntervalInSecond(0.5);
        Area delogoArea = new Area().withX(10).withY(20).withWidth(50).withHeight(100);
        String jobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture, delogoArea, null)
                .getJobId();

        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        System.out.println(JsonUtils.toJsonPrettyString(resp));
        assertEquals("sourceKey wrong, should be: " + sourceKey, resp.getSource().getKey(), sourceKey);
        assertEquals("capture mode wrong, should be: splitss0", resp.getCapture().getMode(), "splitss0");
        assertEquals("delogoArea wrong, input/output delogoArea.height not equal.",
                resp.getDelogoArea().getHeight(), delogoArea.getHeight());
        assertEquals("min interval in second wrong, should be: 0.5", resp.getCapture().getMinIntervalInSecond(), new Double(0.5));
    }

    @Test
    public void testCreateThumbnailJobs7() throws JsonProcessingException {
        String pipelineName = "wlq";
        String sourceKey = "beijing.mp4";
        ThumbnailTarget target = new ThumbnailTarget().withFormat("gif").withSizingPolicy("keep")
                .withFrameRate(20.0).withGifQuality("high")
                .withKeyPrefix("beijing_manual_gif_20fps_highquality");
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual").withStartTimeInSecondDouble(0.5)
                .withEndTimeInSecondDouble(10.5).withIntervalInSecondDouble(0.05);
        String jobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture, null)
                .getJobId();

        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        System.out.println(JsonUtils.toJsonPrettyString(resp));
        assertEquals("sourceKey wrong, should be: " + sourceKey, resp.getSource().getKey(), sourceKey);
        assertEquals("gif quality wrong, should be: high", resp.getTarget().getGifQuality(), "high");
        assertEquals("frame rate wrong, should be: 20", resp.getTarget().getFrameRate(), new Double(20.0));
        assertEquals("format wrong, should be: gif", resp.getTarget().getFormat(), "gif");
        assertEquals("capture mode wrong, should be: manual", resp.getCapture().getMode(), "manual");
        assertEquals("start time wrong, should be: 0.5", resp.getCapture().getStartTimeInSecondDouble(), new Double(0.5));
        assertEquals("end time wrong, should be: 10.5", resp.getCapture().getEndTimeInSecondDouble(), new Double(10.5));
        assertEquals("interval wrong, should be: 0.05", resp.getCapture().getIntervalInSecondDouble(), new Double(0.05));
    }

    @Test
    public void testCreateThumbnailJobs8() throws JsonProcessingException {
        thumbnailPresetName = "test_thumbnail_preset";
        createThumbnailPreset(); 
        String pipelineName = "wlq";
        String sourceKey = "beijing.mp4";
        String targetKeyPrefix = "beijing_manual_gif_20fps_highquality";
        CreateThumbnailJobRequest request = new CreateThumbnailJobRequest();
        String jobId = mediaClient.createThumbnailJob(pipelineName, thumbnailPresetName, sourceKey, targetKeyPrefix)
                .getJobId();

        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        System.out.println(JsonUtils.toJsonPrettyString(resp));
        assertEquals("presetName wrong, should be: " + thumbnailPresetName, resp.getPresetName(), thumbnailPresetName);
    }

    @Test
    public void testListThumbnailJobs() throws JsonProcessingException {

        for (PipelineStatus ps : mediaClient.listPipelines().getPipelines()) {
            ListThumbnailJobsResponse resp = mediaClient.listThumbnailJobs(ps.getPipelineName());
            if (resp.getThumbnails().size() == 0) {
                continue;
            }

            System.out.println(JsonUtils.toJsonPrettyString(resp));
            for (ThumbnailJobStatus job : resp.getThumbnails()) {
                // capture can be null when use thumbnail preset
                assertNotNull("capture wrong, should not be null.", job.getJobId());
            }
        }
    }

    @Test
    public void testGetThumbnailJob() throws JsonProcessingException {
        String jobId = "job-jhynfk2ca1s8b9cy";
        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        System.out.println(JsonUtils.toJsonPrettyString(resp));
        assertEquals("jobId wrong, should be: " + jobId, resp.getJobId(), jobId);
        assertNotNull("capture wrong, should not be null.", resp.getCapture());
    }
}

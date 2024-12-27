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

import com.baidubce.BceServiceException;
import com.baidubce.services.media.model.Area;
import com.baidubce.services.media.model.CreateTranscodingJobRequest;
import com.baidubce.services.media.model.Font;
import com.baidubce.services.media.model.GetTranscodingEncryptionKeyResponse;
import com.baidubce.services.media.model.GetTranscodingJobResponse;
import com.baidubce.services.media.model.Insert;
import com.baidubce.services.media.model.Job;
import com.baidubce.services.media.model.Layout;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.Source;
import com.baidubce.services.media.model.SourceClip;
import com.baidubce.services.media.model.Target;
import com.baidubce.services.media.model.Timeline;
import com.baidubce.util.JsonUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class JobClientTeset extends AbstractMediaTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testGetJobWithPipelineName() {
        List<PipelineStatus> pipelines = mediaClient.listPipelines().getPipelines();
        for (PipelineStatus ps : pipelines) {
            List<Job> jobs = mediaClient.listTranscodingJobs(ps.getPipelineName()).getJobs();
            for (Job job : jobs) {
                System.out.println(job);
                assertEquals("pipelineName wrong. ", job.getPipelineName(), ps.getPipelineName());
            }
        }
    }

    @Test
    public void testCreateJob() throws Exception {
        String pipelineName = "wlq";
        String sourceKey = "beijing.mp4";
        String targetKey = "36A2014_1.m3u8";
        String presetName = "bce.video_hls_320x240_240kbps";
        String jobId = mediaClient.createTranscodingJob(pipelineName, sourceKey, targetKey, presetName).getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(jobId);
        assertEquals("sourceKey wrong, should be: " + sourceKey, resp.getSource().getSourceKey(), sourceKey);
        assertNull("clips should be null. ", resp.getSource().getClips());
    }

    @Test
    public void testCreateJob1() throws Exception {
        String pipelineName = "wlq";
        List<SourceClip> clips = new ArrayList<SourceClip>();
        SourceClip sourceClip1 = new SourceClip().withSourceKey("beijing.mp4").withStartTimeInSecond(0)
                .withDurationInSecond(3);
        SourceClip sourceClip2 = new SourceClip().withSourceKey("beijing.mp4").withStartTimeInSecond(8)
                .withDurationInSecond(200);
        clips.add(sourceClip1);
        clips.add(sourceClip2);
        String targetKey = "clipresult_bj.m3u8";
        String presetName = "bce.video_hls_320x240_240kbps";
        String jobId = mediaClient.createTranscodingJob(pipelineName, clips, targetKey, presetName)
                .getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(jobId);
        assertNull("sourceKey wrong, should be null. ", resp.getSource().getSourceKey());
        assertEquals("clips size should be: " + clips.size(), resp.getSource().getClips().size(), clips.size());
    }

    @Test
    public void testCreateJob2() throws Exception {
        String pipelineName = "wlq";
        List<SourceClip> clips = new ArrayList<SourceClip>();
        SourceClip sourceClip1 = new SourceClip().withSourceKey("beijing.mp4").withStartTimeInSecond(0)
                .withDurationInSecond(10);
        SourceClip sourceClip2 = new SourceClip().withSourceKey("beijing.mp4").withStartTimeInSecond(0)
                .withDurationInSecond(200);
        clips.add(sourceClip1);
        clips.add(sourceClip2);
        String targetKey = "clipresultmp5.mp4";
        String presetName = "bce.video_mp4_320x240_240kbps";
        Area delogoArea = new Area().withX(10).withY(20).withWidth(100).withHeight(200);
        String jobId = mediaClient.createTranscodingJob(pipelineName, clips, targetKey, presetName, null, delogoArea)
                .getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(jobId);
        assertNull("sourceKey wrong, should be null. ", resp.getSource().getSourceKey());
        assertEquals("clips size should be: " + clips.size(), resp.getSource().getClips().size(), clips.size());
        assertEquals("delogoArea wrong, input/output delogoArea.height not equal. ",
                resp.getTarget().getDelogoArea().getHeight(), delogoArea.getHeight());
    }

    @Test
    public void testCreateJob3() throws Exception {
        String pipelineName = "wlq";
        // String sourceKey = "2634_41367.mp4";
        List<SourceClip> clips = new ArrayList<SourceClip>();
        SourceClip sourceClip1 = new SourceClip().withSourceKey("2634_41367.mp4").withStartTimeInSecond(0)
                .withDurationInSecond(10);
        SourceClip sourceClip2 = new SourceClip().withSourceKey("2634_41367.mp4");
        clips.add(sourceClip1);
        clips.add(sourceClip2);
        String targetKey = "clips_playback_watermark_delogo2.mp4";
        String presetName = "presetplayback_1517821734621";
        String watermarkId = "wmk-ibeu31zcankhsxa3";
        Area delogoArea = new Area().withX(10).withY(200).withWidth(300).withHeight(400);
        String jobId = mediaClient.createTranscodingJob(pipelineName, clips, targetKey, presetName, watermarkId,
                delogoArea).getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(jobId);
        System.out.println(JsonUtils.toJsonPrettyString(resp));
        assertNull("sourceKey wrong, should be null. ", resp.getSource().getSourceKey());
        assertEquals("clips size should be: " + clips.size(), resp.getSource().getClips().size(), clips.size());
        assertEquals("delogoArea wrong, input/output delogoArea.height not equal. ",
                resp.getTarget().getDelogoArea().getHeight(), delogoArea.getHeight());
        assertEquals("watermarkIds wrong, should contain: " + watermarkId,
                resp.getTarget().getWatermarkIds().get(0), watermarkId);
    }

    @Test
    public void testCreateJob4() throws Exception {
        String pipelineName = "wlq";
        // String sourceKey = "2634_41367.mp4";
        List<SourceClip> clips = new ArrayList<SourceClip>();
        SourceClip sourceClip1 = new SourceClip().withSourceKey("2634_41367.mp4").withEnableLogo(false)
                .withDurationInMillisecond(15000).withStartTimeInMillisecond(2000);
        clips.add(sourceClip1);
        String targetKey = "clips_playback_watermark_delogo_crop2.mp4";
        String presetName = "presetplayback_1517821734621";
        String watermarkId = "wmk-ibeu31zcankhsxa3";
        Area delogoArea = new Area().withX(10).withY(200).withWidth(300).withHeight(400);
        Area crop = new Area().withX(100).withY(0).withWidth(800).withHeight(600);
        String jobId = mediaClient.createTranscodingJob(pipelineName, clips, targetKey, presetName, watermarkId,
                delogoArea, crop, null).getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(jobId);
        System.out.println(JsonUtils.toJsonPrettyString(resp));
        assertNull("sourceKey wrong, should be null. ", resp.getSource().getSourceKey());
        assertEquals("clips size should be: " + clips.size(), resp.getSource().getClips().size(), clips.size());
        assertEquals("delogoArea wrong, input/output delogoArea.height not equal. ",
                resp.getTarget().getDelogoArea().getHeight(), delogoArea.getHeight());
        assertEquals("durationInMillisecond wrong, input/output clips.durationInMillisecond not equal. ",
                resp.getSource().getClips().get(0).getDurationInMillisecond(), sourceClip1.getDurationInMillisecond());
        // crop info is not returned for now.
        // assertEquals("delogoArea wrong, input/output delogoArea.height not equal. ",
        //         resp.getTarget().getCrop().getHeight(), crop.getHeight());
        assertEquals("watermarkIds wrong, should contain: " + watermarkId,
                resp.getTarget().getWatermarkIds().get(0), watermarkId);
    }

    @Test
    public void testCreateJob5() throws Exception {
        String pipelineName = "wlq";
        // String sourceKey = "2634_41367.mp4";
        List<SourceClip> clips = new ArrayList<SourceClip>();
        SourceClip sourceClip1 = new SourceClip().withSourceKey("2634_41367.mp4").withEnableLogo(true)
                .withDurationInMillisecond(15000).withStartTimeInMillisecond(2000);
        clips.add(sourceClip1);
        String targetKey = "clips_playback_delogo_inserts4.mp4";
        String presetName = "presetplayback_1517821734621";
        // String watermarkId = "wmk-ibeu31zcankhsxa3";
        Area delogoArea = new Area().withX(10).withY(200).withWidth(100).withHeight(150);
        // Area crop = new Area().withX(100).withY(0).withWidth(800).withHeight(600);
        Layout layout1 = new Layout().withHorizontalAlignment("right").withHorizontalOffsetInPixel(10)
                .withVerticalAlignment("top").withVerticalOffsetInPixel(100);
        Timeline timeline1 = new Timeline().withStartTimeInMillisecond(4000).withDurationInMillisecond(3000);
        final Insert insert1 = new Insert().withBucket("jianbininput").withKey("bce.png").withType("image")
                .withLayout(layout1).withTimeline(timeline1);

        Layout layout2 = new Layout().withHorizontalAlignment("center").withHorizontalOffsetInPixel(100)
                .withVerticalAlignment("center").withVerticalOffsetInPixel(100);
        Font font2 = new Font().withSizeInPoint(12).withFamily("custom");
        Timeline timeline2 = new Timeline().withStartTimeInMillisecond(5000).withDurationInMillisecond(4000);
        final Insert insert2 = new Insert().withType("text").withText("this is a test txt insert")
                .withLayout(layout2).withTimeline(timeline2).withFont(font2);
        List<Insert> inserts = new ArrayList<Insert>() {
            {
                add(insert1);
                add(insert2);
            }
        };
        String jobId = mediaClient.createTranscodingJob(pipelineName, clips, targetKey, presetName, null,
                delogoArea, null, inserts).getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(jobId);
        System.out.println(JsonUtils.toJsonPrettyString(resp));
        assertNull("sourceKey wrong, should be null. ", resp.getSource().getSourceKey());
        assertEquals("clips size should be: " + clips.size(), resp.getSource().getClips().size(), clips.size());
        assertEquals("delogoArea wrong, input/output delogoArea.height not equal. ",
                resp.getTarget().getDelogoArea().getHeight(), delogoArea.getHeight());
        assertEquals("durationInMillisecond wrong, input/output clips.durationInMillisecond not equal. ",
                resp.getSource().getClips().get(0).getDurationInMillisecond(), sourceClip1.getDurationInMillisecond());
        // crop and inserts info is not returned for now.
    }

    @Test
    public void testCreateJob6() throws Exception {
        CreateTranscodingJobRequest createTranscodingJobRequest = new CreateTranscodingJobRequest();
        createTranscodingJobRequest.withPipelineName("wlq")
                .withSource(new Source().withSourceKey("beijing.mp4"))
                .withTarget(new Target().withTargetKey("36A2014_1.m3u8")
                        .withAutoDelogo(true)
                        .withDelogoMode("Normal")
                        .withPresetName("bce.video_hls_320x240_240kbps"));

        String jobId = mediaClient.createTranscodingJob(createTranscodingJobRequest).getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(jobId);
        assertTrue("autoDelogo should be true", resp.getTarget().getAutoDelogo());
        assertEquals("delogo mode should be Normal", resp.getTarget().getDelogoMode(), "Normal");
    }
   
    @Test
    public void testCreateJob7() throws Exception {
        CreateTranscodingJobRequest createTranscodingJobRequest = new CreateTranscodingJobRequest();
        createTranscodingJobRequest.withPipelineName("wlq")
                .withSource(new Source().withSourceKey("beijing.mp4"))
                .withTarget(new Target().withTargetKey("36A2014_1.m3u8")
                        .withDelogoMode("Normal")
                        .withDelogoArea(new Area().withX(5).withY(5).withWidth(100).withHeight(100))
                        .withPresetName("bce.video_hls_320x240_240kbps"));

        String jobId = mediaClient.createTranscodingJob(createTranscodingJobRequest).getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(jobId);
        assertEquals("delogo mode should be Normal", resp.getTarget().getDelogoMode(), "Normal");
        assertNotNull("delogo area should not be null", resp.getTarget().getDelogoArea());
    } 


    @Test
    public void testCreateJob8() throws Exception {
        CreateTranscodingJobRequest createTranscodingJobRequest = new CreateTranscodingJobRequest();
        createTranscodingJobRequest.withPipelineName("wlq")
                .withSource(new Source().withSourceKey("beijing.mp4"))
                .withTarget(new Target().withTargetKey("36A2014_1.m3u8")
                        .withDelogoMode("Inpainting")
                        .addDelogoArea(new Area().withX(5).withY(5).withWidth(100).withHeight(100))
                        .addDelogoArea(new Area().withX(200).withY(200).withWidth(100).withHeight(100))
                        .withPresetName("bce.video_hls_320x240_240kbps"));

        String jobId = mediaClient.createTranscodingJob(createTranscodingJobRequest).getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(jobId);
        assertEquals("delogo mode should be Normal", resp.getTarget().getDelogoMode(), "Inpainting");
        assertEquals("delogo area should not be null", resp.getTarget().getDelogoAreas().size(), 2);
    }

    @Test
    public void testCreateJob9() throws Exception {
        CreateTranscodingJobRequest createTranscodingJobRequest = new CreateTranscodingJobRequest();
        Insert insert = new Insert().withBucket("bvw-pag-test").withKey("mu.mp3").withType("audio").withTimeline(new Timeline().
                withStartTimeInMillisecond(0).withDurationInMillisecond(5000));
        List<Insert> inserts = new ArrayList<>();
        inserts.add(insert);
        createTranscodingJobRequest.withPipelineName("a_lyq_test")
                .withSource(new Source().withSourceKey("60s.mp4"))
                .withTarget(new Target().withTargetBucket("adapter-test").withTargetKey("a/out.mp4").withPresetName("test_1111_insert").withAutoDelogo(true)
                        .withInserts(inserts));
        Target.JobCfg jobCfg = new Target.JobCfg();
        jobCfg.setNotification("http://www.baidu.com");
        createTranscodingJobRequest.getTarget().setJobCfg(jobCfg);
        String jobId = mediaClient.createTranscodingJob(createTranscodingJobRequest).getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        System.out.println(jobId);
    }

    @Test
    public void testCreateJob10() throws Exception {
        CreateTranscodingJobRequest createTranscodingJobRequest = new CreateTranscodingJobRequest();
        Font font = new Font();
        font.setColor("#000000");
        Insert insert = new Insert().withBucket("bvw-pag-test").withKey("zj.srt").withType("subtitle").withFont(font);
        List<Insert> inserts = new ArrayList<>();
        inserts.add(insert);
        List<SourceClip> clips = new ArrayList<>();
        createTranscodingJobRequest.withPipelineName("a_lyq_test")
                .withSource(new Source().withSourceKey("60s.mp4").withClips(clips))
                .withTarget(new Target().withTargetKey("a/sub1.mp4").withPresetName("mct.video_mp4_640x360_600kbps").withAutoDelogo(true)
                        .withInserts(inserts).withJobCfg(new Target.JobCfg().withNotification("http://www.baidu.com")));

        String jobId = mediaClient.createTranscodingJob(createTranscodingJobRequest).getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        System.out.println(jobId);
    }

    @Test
    public void testGetJob() throws Exception {
        List<PipelineStatus> pipelines = mediaClient.listPipelines().getPipelines();
        for (PipelineStatus ps : pipelines) {
            List<Job> jobs = mediaClient.listTranscodingJobs(ps.getPipelineName()).getJobs();
            for (Job job : jobs) {
                GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(job.getJobId());
                System.out.println(JsonUtils.toJsonPrettyString(resp));
                assertEquals("pipelineName wrong. ", resp.getPipelineName(), ps.getPipelineName());
                if (resp.getJobStatus() == "SUCCESS" && resp.getOutput() != null && resp.getOutput().getVideo() != null 
                        && resp.getOutput().getVideo().getSizeInKiloByte() != null 
                        && resp.getOutput().getVideo().getDurationInSeconds() != null) {
                    assertNotNull(resp.getOutput().getBitRateInKBps());
                }
            }
        }
    }

    @Test
    public void getAllPipelines() {
        List<PipelineStatus> list = mediaClient.listPipelines().getPipelines();
        for (PipelineStatus ps : list) {
            System.out.println(ps);
        }
        assertTrue("pipeline size wrong, should be greater than 0.", list.size() > 0);
    }

    @Test
    public void testGetTranscodingEncryptionKey() {
        List<PipelineStatus> pipelines = mediaClient.listPipelines().getPipelines();
        for (PipelineStatus ps : pipelines) {
            System.out.println(ps.getPipelineName());
            List<Job> jobs = mediaClient.listTranscodingJobs(ps.getPipelineName()).getJobs();
            for (Job job : jobs) {
                try {
                    GetTranscodingEncryptionKeyResponse response = 
                            mediaClient.getTranscodingEncryptionKey(job.getJobId());
                    assertEquals("encryption key length must be 16", 16, response.getEncryptionKey().length());
                    System.out.println("AES key of job " + job.getJobId() + " is " + response.getEncryptionKey());
                } catch (BceServiceException e) {
                    if (e.getStatusCode() == 404) {
                        System.out.println("AES key of job " + job.getJobId() + " dose not exist!");
                    } else if (e.getStatusCode() == 400) {
                        System.out.println("This server dose not support get AES key!");
                    } else {
                        throw e;
                    }
                }
            }
        }
    }

}

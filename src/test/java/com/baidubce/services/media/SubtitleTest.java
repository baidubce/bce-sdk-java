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
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeNoException;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.media.model.Area;
import com.baidubce.services.media.model.CreateSubtitleJobRequest;
import com.baidubce.services.media.model.GetSubtitleJobResponse;
import com.baidubce.services.media.model.ListSubtitleJobsResponse;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.SubtitleConfig;
import com.baidubce.services.media.model.SubtitleJobStatus;
import com.baidubce.services.media.model.SubtitleSource;
import com.baidubce.services.media.model.SubtitleTarget;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class SubtitleTest extends AbstractMediaTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreateSubtitleJobs1() throws JsonProcessingException {
        String pipelineName = "wlq";
        String sourceKey = "beijing.mp4";
        String targetKeyPrefix = "subtitle/beijing";
        String jobId = mediaClient.createSubtitleJob(pipelineName, sourceKey, targetKeyPrefix).getJobId();

        GetSubtitleJobResponse resp = mediaClient.getSubtitleJob(jobId);
        assertEquals(resp.getSource().getKey(), sourceKey);
    }

    @Test
    public void testCreateSubtitleJobs2() throws JsonProcessingException {
        String pipelineName = "wlq";
        String sourceKey = "beijing.mp4";
        String targetKeyPrefix = "subtitle/beijing";
        String format = "json";
        String jobId = mediaClient.createSubtitleJob(pipelineName, sourceKey, targetKeyPrefix, format).getJobId();

        GetSubtitleJobResponse resp = mediaClient.getSubtitleJob(jobId);
        assertEquals(resp.getSource().getKey(), sourceKey);
        assertEquals(resp.getTarget().getFormats().get(0), format);
    }

    @Test
    public void testCreateSubtitleJobs3() throws JsonProcessingException {
        String pipelineName = "wlq";
        String sourceKey = "beijing.mp4";
        String targetKeyPrefix = "subtitle/beijing";
        ArrayList<String> formats = new ArrayList<String>();
        formats.add("json");
        formats.add("srt");
        String jobId = mediaClient.createSubtitleJob(pipelineName, sourceKey, targetKeyPrefix, formats).getJobId();

        GetSubtitleJobResponse resp = mediaClient.getSubtitleJob(jobId);
        assertEquals(resp.getSource().getKey(), sourceKey);
        assertTrue(resp.getTarget().getFormats().contains(formats.get(0)));
        assertTrue(resp.getTarget().getFormats().contains(formats.get(1)));
    }

    @Test
    public void testCreateSubtitleJobs4() throws JsonProcessingException {
        CreateSubtitleJobRequest request = new CreateSubtitleJobRequest();
        request.withPipelineName("wlq")
                .withSource(new SubtitleSource().withKey("beijing.mp4"))
                .withTarget(new SubtitleTarget().withKeyPrefix("subtitle/beijing")
                        .addFormat("json").addFormat("srt"))
                .withConfig(new SubtitleConfig().withMaxSentenceLen(20));
        String jobId = mediaClient.createSubtitleJob(request).getJobId();

        GetSubtitleJobResponse resp = mediaClient.getSubtitleJob(jobId);
        assertEquals(resp.getPipelineName(), "wlq");
        assertEquals(resp.getSource().getKey(), "beijing.mp4");
        assertEquals(resp.getTarget().getKeyPrefix(), "subtitle/beijing");
        assertTrue(resp.getTarget().getFormats().contains("json"));
        assertTrue(resp.getTarget().getFormats().contains("srt"));
        assertEquals(resp.getConfig().getMaxSentenceLen(), new Integer(20));
    }

    @Test
    public void testGetSubtitleJobs() throws JsonProcessingException {
        GetSubtitleJobResponse resp = mediaClient.getSubtitleJob("job-kgnnrx84q7udhnuf");
        assertEquals(resp.getJobId(), "job-kgnnrx84q7udhnuf");
        assertEquals(resp.getPipelineName(), "normal");
        assertEquals(resp.getJobStatus(), "FAILED");
        assertEquals(resp.getError().getCode(), "BadRequest");
        assertNotNull(resp.getError().getMessage());
        assertEquals(resp.getSource().getKey(), "subittle/HD_1080p_4352k_29.97r_1h_1800s.aac");
        assertEquals(resp.getTarget().getKeyPrefix(), "subtitle/HD_1080p_");
        assertEquals(resp.getTarget().getFormats().size(), 1);
        assertEquals(resp.getTarget().getFormats().get(0), "json");
        assertEquals(resp.getConfig().getMaxSentenceLen(), new Integer(15));
    }

    @Test
    public void testGetSubtitleJobs1() throws JsonProcessingException {
        GetSubtitleJobResponse resp = mediaClient.getSubtitleJob("job-kg4p1tgzhhyx4g2y");
        assertEquals(resp.getTarget().getKeys().size(), 1);
        assertEquals(resp.getTarget().getKeys().get(0), "subtitle/yiqing_5min.m4a.json");
    }

    @Test
    public void testListSubtitleJobs() throws JsonProcessingException {

        for (PipelineStatus ps : mediaClient.listPipelines().getPipelines()) {
            ListSubtitleJobsResponse resp = mediaClient.listSubtitleJobs(ps.getPipelineName());
            if (resp.getSubtitles().size() == 0) {
                continue;
            }

            System.out.println(JsonUtils.toJsonPrettyString(resp));
            for (SubtitleJobStatus job : resp.getSubtitles()) {
                System.out.println(job.getJobId());
            }
        }
    }
    
}

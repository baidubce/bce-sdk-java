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

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.media.model.GetJobResponse;
import com.baidubce.services.media.model.GetTranscodingJobResponse;
import com.baidubce.services.media.model.Job;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

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
            }
        }
    }

    @Test
    public void testCreateJob() throws Exception {
        String pipelineName = "chaipipe";
        String sourceKey = "36A2014.mp4";
        String targetKey = "36A2014_1.mp4";
        String presetName = "bce.video_hls_320x240_240kbps";
        String jobId = mediaClient.createTranscodingJob(pipelineName, sourceKey, targetKey, presetName).getJobId();
        assertTrue("Invalid job ID: " + jobId, jobId != null
                && jobId.trim().length() > 0);
        GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(jobId);
        System.out.println(JsonUtils.toJsonPrettyString(resp));
    }

    @Test
    public void testGetJob() throws Exception {
        List<PipelineStatus> pipelines = mediaClient.listPipelines().getPipelines();
        for (PipelineStatus ps : pipelines) {
            List<Job> jobs = mediaClient.listTranscodingJobs(ps.getPipelineName()).getJobs();
            for (Job job : jobs) {
                GetTranscodingJobResponse resp = mediaClient.getTranscodingJob(job.getJobId());
                System.out.println(JsonUtils.toJsonPrettyString(resp));
            }
        }
    }

    @Test
    public void getAllPipelines() {
        List<PipelineStatus> list = mediaClient.listPipelines().getPipelines();
        for (PipelineStatus ps : list) {
            System.out.println(ps);
        }
    }

}

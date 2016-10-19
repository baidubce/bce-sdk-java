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

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void testCreateThumbnailJobs1() {
        String pipelineName = "chaipipe";
        String sourceKey = "10706_csdn.mp4";
        String jobId = mediaClient.createThumbnailJob(pipelineName, sourceKey).getJobId();

        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        try {
            System.out.println(JsonUtils.toJsonPrettyString(resp));
            System.out.println("  jobId = " + resp.getJobId());
            System.out.println("  pipelineName = " + resp.getPipelineName());
            System.out.println("  jobStatus = " + resp.getJobStatus());
            System.out.println("  source.key = " + resp.getSource().getKey());
            System.out.println("  target.keyPrefix = " + resp.getTarget().getKeyPrefix());
            System.out.println("  target.format = " + resp.getTarget().getFormat());
            System.out.println("  target.sizingPolicy = " + resp.getTarget().getSizingPolicy());
            System.out.println("  target.heightInPixel = " + resp.getTarget().getHeightInPixel());
            System.out.println("  target.widthInPixel = " + resp.getTarget().getWidthInPixel());
            System.out.println("  target.keys = " + resp.getTarget().getKeys());
            System.out.println("  capture.mode = " + resp.getCapture().getMode());
            System.out.println("  capture.startTimeInSecond = " + resp.getCapture().getStartTimeInSecond());
            System.out.println("  capture.endTimeInSecond = " + resp.getCapture().getEndTimeInSecond());
            System.out.println("  capture.IntervalInSecond = " + resp.getCapture().getIntervalInSecond());

        } catch (JsonProcessingException e) {
            fail("Failed to read the result.");
        }

    }

    @Test
    public void testCreateThumbnailJobs2() {
        String pipelineName = "chaipipe";
        String sourceKey = "10706_csdn.mp4";
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture = new ThumbnailCapture().withMode("manual");

        try {
            String jobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();
            fail("This test case should fail.");
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }
    @Test
    public void testCreateThumbnailJobs3() {
        String pipelineName = "chaipipe";
        String sourceKey = "10706_csdn.mp4";
        ThumbnailTarget target = new ThumbnailTarget().withFormat("jpg").withSizingPolicy("keep");
        ThumbnailCapture capture =
                new ThumbnailCapture().withMode("manual").withStartTimeInSecond(0).withEndTimeInSecond(5)
                        .withIntervalInSecond(1);
        String jobId = mediaClient.createThumbnailJob(pipelineName, sourceKey, target, capture).getJobId();

        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        try {
            System.out.println(JsonUtils.toJsonPrettyString(resp));
            System.out.println("  jobId = " + resp.getJobId());
            System.out.println("  pipelineName = " + resp.getPipelineName());
            System.out.println("  jobStatus = " + resp.getJobStatus());
            System.out.println("  source.key = " + resp.getSource().getKey());
            System.out.println("  target.keyPrefix = " + resp.getTarget().getKeyPrefix());
            System.out.println("  target.format = " + resp.getTarget().getFormat());
            System.out.println("  target.sizingPolicy = " + resp.getTarget().getSizingPolicy());
            System.out.println("  target.heightInPixel = " + resp.getTarget().getHeightInPixel());
            System.out.println("  target.widthInPixel = " + resp.getTarget().getWidthInPixel());
            System.out.println("  target.keys = " + resp.getTarget().getKeys());
            System.out.println("  capture.mode = " + resp.getCapture().getMode());
            System.out.println("  capture.startTimeInSecond = " + resp.getCapture().getStartTimeInSecond());
            System.out.println("  capture.endTimeInSecond = " + resp.getCapture().getEndTimeInSecond());
            System.out.println("  capture.IntervalInSecond = " + resp.getCapture().getIntervalInSecond());

        } catch (JsonProcessingException e) {
            fail("Failed to read the result.");
        }

    }

    @Test
    public void testListThumbnailJobs() {

        for (PipelineStatus ps : mediaClient.listPipelines().getPipelines()) {
            try {
                ListThumbnailJobsResponse resp = mediaClient.listThumbnailJobs(ps.getPipelineName());
                if (resp.getThumbnails().size() == 0) {
                    continue;
                }

                System.out.println(JsonUtils.toJsonPrettyString(resp));
                System.out.println();
                for (ThumbnailJobStatus job : resp.getThumbnails()) {

                    System.out.println("  jobId = " + job.getJobId());
                    System.out.println("  pipelineName = " + job.getPipelineName());
                    System.out.println("  jobStatus = " + job.getJobStatus());
                    System.out.println("  source.key = " + job.getSource().getKey());
                    System.out.println("  target.keyPrefix = " + job.getTarget().getKeyPrefix());
                    System.out.println("  target.format = " + job.getTarget().getFormat());
                    System.out.println("  target.sizingPolicy = " + job.getTarget().getSizingPolicy());
                    System.out.println("  target.heightInPixel = " + job.getTarget().getHeightInPixel());
                    System.out.println("  target.widthInPixel = " + job.getTarget().getWidthInPixel());
                    System.out.println("  target.keys = " + job.getTarget().getKeys());
                    System.out.println("  capture.mode = " + job.getCapture().getMode());
                    System.out.println("  capture.startTimeInSecond = " + job.getCapture().getStartTimeInSecond());
                    System.out.println("  capture.endTimeInSecond = " + job.getCapture().getEndTimeInSecond());
                    System.out.println("  capture.IntervalInSecond = " + job.getCapture().getIntervalInSecond());
                    System.out.println();

                }
            } catch (Exception e) {
                fail("Failed to read the result.");
            }
        }
    }

    @Test
    public void testGetThumbnailJob() {
        String jobId = "job-ffam2ne6b3k6i66x";
        GetThumbnailJobResponse resp = mediaClient.getThumbnailJob(jobId);
        try {
            System.out.println(JsonUtils.toJsonPrettyString(resp));
            System.out.println("  jobId = " + resp.getJobId());
            System.out.println("  pipelineName = " + resp.getPipelineName());
            System.out.println("  jobStatus = " + resp.getJobStatus());
            System.out.println("  source.key = " + resp.getSource().getKey());
            System.out.println("  target.keyPrefix = " + resp.getTarget().getKeyPrefix());
            System.out.println("  target.format = " + resp.getTarget().getFormat());
            System.out.println("  target.sizingPolicy = " + resp.getTarget().getSizingPolicy());
            System.out.println("  target.heightInPixel = " + resp.getTarget().getHeightInPixel());
            System.out.println("  target.widthInPixel = " + resp.getTarget().getWidthInPixel());
            System.out.println("  target.keys = " + resp.getTarget().getKeys());
            System.out.println("  capture.mode = " + resp.getCapture().getMode());
            System.out.println("  capture.startTimeInSecond = " + resp.getCapture().getStartTimeInSecond());
            System.out.println("  capture.endTimeInSecond = " + resp.getCapture().getEndTimeInSecond());
            System.out.println("  capture.IntervalInSecond = " + resp.getCapture().getIntervalInSecond());

        } catch (JsonProcessingException e) {
            fail("Failed to read the result.");
        }
    }
}

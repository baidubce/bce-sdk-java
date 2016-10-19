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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.media.model.Audio;
import com.baidubce.services.media.model.Clip;
import com.baidubce.services.media.model.CreateJobResponse;
import com.baidubce.services.media.model.CreatePipelineRequest;
import com.baidubce.services.media.model.CreatePresetRequest;
import com.baidubce.services.media.model.GetPresetResponse;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.PipelineConfig;
import com.baidubce.services.media.model.PipelineStatus;
import com.baidubce.services.media.model.Video;

public abstract class AbstractMediaTest {
    // jianbing's key
//    static String   MEDIA_AK        = "46bd9968a6194b4bbdf0341f2286ccce";
//    static String   MEDIA_SK        = "ec7f4e0174254f6f9020f9680fb1da9f";

    // hudapeng's key
    static String   MEDIA_AK        = "5976ebbb80ff49e7984bb3c1061a657d";
    static String   MEDIA_SK        = "6a15cc0d7083446590a6a46df55a5e98";

    
    // hudapeng's key
    // static String MEDIA_AK = "5976ebbb80ff49e7984bb3c1061a657d";
    // static String MEDIA_SK = "6a15cc0d7083446590a6a46df55a5e98";
    // static String END_POINT = "http://multimedia.bce-testinternal.baidu.com";
    static String   MEDIA_END_POINT = "http://multimedia.bce-testinternal.baidu.com/";
    static String   BOS_END_POINT   = "http://10.105.97.15";
    
    MediaClient mediaClient;
    BosClient   bosClient;

    String          pipelineName    = null;
    String          jobId           = null;
    String          sourceBucket    = null;
    String          targetBucket    = null;
    String          sourceKey       = null;
    String          targetKey       = null;
    String          presetName      = null;

    public void setUp() throws Exception {
        DefaultBceCredentials cred = new DefaultBceCredentials(MEDIA_AK,
                MEDIA_SK);
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(cred).withEndpoint(MEDIA_END_POINT)
                .withMaxConnections(10);
        mediaClient = new MediaClient(config);

        BosClientConfiguration bosConfig = new BosClientConfiguration()
                .withCredentials(cred).withEndpoint(BOS_END_POINT);
        bosClient = new BosClient(bosConfig);
    }

    public void tearDown() throws Exception {
        if (presetName != null) {
            mediaClient.deletePreset(presetName);
            checkPresetExist(presetName, false);
            presetName = null;
        }

        if (jobId != null) {
            jobId = null;
        }

        if (pipelineName != null) {
            mediaClient.deletePipeline(pipelineName);
            checkPipelineExist(pipelineName, false);
            pipelineName = null;
        }

    }

    protected void createPipeline() {
        CreatePipelineRequest request = new CreatePipelineRequest();
        request.setPipelineName(pipelineName);
        request.setDescription("For testing");
        request.setSourceBucket("jianbininput");
        request.setTargetBucket("jianbinoutput");
        PipelineConfig config = new PipelineConfig();
        config.setCapacity(10);
        request.setConfig(config);
        mediaClient.createPipeline(request);

    }

    protected void createPreset() {

        CreatePresetRequest request = new CreatePresetRequest();
        request.setPresetName(presetName);
        request.setDescription("HDP - For sandbox testing");
        request.setContainer("m4a");
        request.setTransmux(true);
        Clip clip = new Clip();
        clip.setStartTimeInSecond(10);
        clip.setDurationInSecond(100);
        request.setClip(clip);
        Audio audio = new Audio();
        audio.setBitRateInBps(2000);
        audio.setSampleRateInHz(22050);
        audio.setChannels(2);
        request.setAudio(audio);
        Video video = new Video();
        video.setCodec("h264");
        video.setBitRateInBps(64000);
        video.setMaxFrameRate(30f);
        video.setMaxWidthInPixel(4096);
        video.setMaxHeightInPixel(3072);
        video.setSizingPolicy("keep");
        request.setVideo(video);

        mediaClient.createPreset(request);
        checkPresetExist(presetName, true);
    }

    protected String createJob() {
        CreateJobResponse resp = mediaClient.createJob(pipelineName, sourceKey,
                targetKey, presetName);
        return resp.getJobId();
    }

    /*
     * 1. pipeline don't support capital letters 2. in current version, pipeline
     * cannot be duplicated even after it is removed
     */
    protected String convertName(String key) {
        return (key.toLowerCase() + "_" + System.currentTimeMillis());
    }

    protected void checkPipelineExist(String expectedPipelineName,
            boolean expected) {
        ListPipelinesResponse resp = mediaClient.listPipelines();
        System.err.println(resp);

        Set<String> names = new HashSet<String>();
        for (PipelineStatus pipe : resp.getPipelines()) {
            names.add(pipe.getPipelineName());
        }

        if (expected) {
            assertTrue("Failed to find expected pipeline: "
                    + expectedPipelineName + ". \n " + resp,
                    names.contains(expectedPipelineName));
        } else {
            assertFalse("The pipeline: " + expectedPipelineName
                    + " still exists. \n " + resp,
                    names.contains(expectedPipelineName));
        }
    }

    protected void checkPresetExist(String expectedPresetName, boolean expected) {
        GetPresetResponse resp = mediaClient.getPreset(expectedPresetName);

        if (expected) {
            assertTrue("Failed to find expected preset: " + expectedPresetName,
                    resp.getState().equalsIgnoreCase("ACTIVE"));
        } else {
            assertTrue(
                    "The pipeline: " + expectedPresetName + " still exists.",
                    resp.getState().equalsIgnoreCase("INACTIVE"));
        }
    }

}

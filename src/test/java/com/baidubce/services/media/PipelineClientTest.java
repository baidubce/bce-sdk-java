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

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.services.media.model.GetPipelineResponse;
import com.baidubce.services.media.model.ListPipelinesResponse;
import com.baidubce.services.media.model.PipelineStatus;

public class PipelineClientTest extends AbstractMediaTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void showAllPipelines() {
        ListPipelinesResponse resp = mediaClient.listPipelines();
        System.err.println(resp);
    }

    @Test
    public void testGetAllPipelines() {
        pipelineName = convertName("ForTestGetAllPipelines");
        checkPipelineExist(pipelineName, false);

        createPipeline();

        ListPipelinesResponse resp = mediaClient.listPipelines();
        System.err.println(resp);

        Set<String> names = new HashSet<String>();
        for (PipelineStatus pipe : resp.getPipelines()) {
            names.add(pipe.getPipelineName());
        }
        assertTrue("Failed to get expected pipelines: " + pipelineName,
                names.contains(pipelineName));
    }

    @Test
    public void testGetPipeline() {
        pipelineName = convertName("ForTestGetPipeline");
        createPipeline();
        checkPipelineExist(pipelineName, true);

        GetPipelineResponse resp = mediaClient.getPipeline(pipelineName);
        System.out.println(resp);
        assertTrue("Failed to get pipeline: " + pipelineName, resp != null
                && resp.getPipelineName().equals(pipelineName));
    }

    @Test
    public void testCreatePipeline() {
        pipelineName = convertName("ForTestCreatePipeline");
        createPipeline();
        checkPipelineExist(pipelineName, true);
    }

    @Test
    public void testRemovePipeline() {
        pipelineName = convertName("ForTestRemovePipeline");
        createPipeline();
        // pipelineName = "fortestremovepipeline_1429001608289";
        checkPipelineExist(pipelineName, true);

        mediaClient.deletePipeline(pipelineName);

        checkPipelineExist(pipelineName, false);
        pipelineName = null;
    }

}

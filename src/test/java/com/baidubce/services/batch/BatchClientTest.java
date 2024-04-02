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
package com.baidubce.services.batch;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.batch.model.CreateJobRequest;
import com.baidubce.services.batch.model.CreateJobResponse;
import com.baidubce.services.batch.model.GetJobResponse;
import com.baidubce.services.batch.model.ListJobsRequest;
import com.baidubce.services.batch.model.ListJobsResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class BatchClientTest {

    //  qasandbox, bce-batch-compute-rd@baidu.com
    public static final String BATCH_ENDPOINT = "http://nmg02-bce-test10.nmg02.baidu.com:8687";
    public static final String BATCH_AK = "b20dca422ef64ae39429af6f8b010444";
    public static final String BATCH_SK = "e78654df9c624a029effeb90c6c3d75e";

    protected BatchClient batchClient;

    private String jobId = null;
    private String jobName = "sdk_ut";
    private String vmType = "batch.g.small";
    private int vmCount = 2;
    private String jobDagJson;
    private int jobTimeoutInSeconds = 1000;
    private String memo = "sdk_ut_memo";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() throws IOException {
        BceClientConfiguration config = new BceClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(BATCH_AK, BATCH_SK));
        config.setEndpoint(BATCH_ENDPOINT);
        this.batchClient = new BatchClient(config);
        jobDagJson = IOUtils.toString(getClass().getClassLoader().getResourceAsStream("batch/demoJobDag.json"));
    }

    @Test
    public void testCreateJob() {
        CreateJobResponse response = this.batchClient.createJob(
                new CreateJobRequest()
                        .withName(this.jobName)
                        .withVmType(this.vmType)
                        .withVmCount(this.vmCount)
                        .withJobDagJson(this.jobDagJson)
                        .withJobTimeoutInSeconds(this.jobTimeoutInSeconds)
                        .withMemo(this.memo)
                        .withClientToken("mbd")
        );
        try {
            System.out.println(JsonUtils.toJsonPrettyString(response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertThat(response, hasProperty("jobId"));
        this.jobId = response.getJobId();

    }

    @Test
    public void testListJobs() {
        int maxKeys = 1;
        ListJobsResponse response = this.batchClient.listJobs(
                new ListJobsRequest().withMaxKeys(maxKeys)
        );
        try {
            System.out.println(JsonUtils.toJsonPrettyString(response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(response);
        assertThat(response.getMaxKeys(), is(maxKeys));
        assertThat(response.getJobs(), hasSize(maxKeys));
    }

    @Test
    public void testGetJob() throws JsonProcessingException {
        if (this.jobId != null) {
            GetJobResponse response = this.batchClient.getJob(this.jobId);
            System.out.println(new ObjectMapper().writeValueAsString(response));
            assertThat(response.getJob().getJobId(), is(this.jobId));
            assertThat(response.getJob().getJobDescription().getName(), is(this.jobName));
            assertThat(response.getJob().getJobDescription().getVmType(), is(this.vmType));
            assertThat(response.getJob().getJobDescription().getVmCount(), is(this.vmCount));
            assertThat(response.getJob().getJobDescription().getJobDagJson(), is(this.jobDagJson));
            assertThat(response.getJob().getJobDescription().getMemo(), is(this.memo));
        } else {
            this.thrown.expect(IllegalArgumentException.class);
            this.thrown.expectMessage("The parameter clusterId should not be null or empty string.");
        }
    }

    @Test
    public void testCancelJob() {
        if (this.jobId != null) {
            boolean hasCancelled = false;
            int tryTime = 0;
            while (!hasCancelled && tryTime <= 5) {
                try {
                    this.batchClient.cancelJob(this.jobId);
                    hasCancelled = true;
                } catch (BceServiceException e) {
                    System.out.println("Try to terminate cluster failed: " + e.getErrorMessage());
                    tryTime += 1;
                    try {
                        Thread.sleep(60 * 3);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}

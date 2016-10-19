/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bmr;

import com.baidubce.BceServiceException;
import com.baidubce.services.bmr.model.JavaStepConfig;
import com.baidubce.services.bmr.model.AddStepsRequest;
import com.baidubce.services.bmr.model.AddStepsResponse;
import com.baidubce.services.bmr.model.CreateClusterRequest;
import com.baidubce.services.bmr.model.CreateClusterResponse;
import com.baidubce.services.bmr.model.GetClusterResponse;
import com.baidubce.services.bmr.model.GetStepResponse;
import com.baidubce.services.bmr.model.InstanceGroupConfig;
import com.baidubce.services.bmr.model.ListClustersRequest;
import com.baidubce.services.bmr.model.ListClustersResponse;
import com.baidubce.services.bmr.model.ListStepsRequest;
import com.baidubce.services.bmr.model.ListStepsResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class BmrClientTest extends AbstractBmrClientTest {
    private String clusterId = null;
    private String clusterName;
    private String imageType;
    private String imageVersion;
    private boolean autoTerminate;
    private String logUri;

    private JavaStepConfig javaStepConfig;
    private String stepId = null;
    private int stepCount;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        super.setUp();
        this.clusterName = "java-sdk-cluster";
        this.imageType = "hadoop";
        this.imageVersion = "0.1.0";
        this.autoTerminate = true;
        this.logUri = "bos://liukun01/sdk/";

        this.stepCount = 1;
        this.javaStepConfig = new JavaStepConfig()
                .withName("java-step")
                .withActionOnFailure("Continue")
                .withJar("bos://benchmark/hadoop/hadoop-mapreduce-examples.jar")
                .withMainClass("org.apache.hadoop.examples.WordCount")
                .withArguments("bos://liukun01/add_minus_shell.sh bos://liukun01/sdk/output_java/out2");
    }

    @Test
    public void testCreateCluster() {
        CreateClusterResponse response = this.bmrClient.createCluster(
                new CreateClusterRequest()
                        .withName(this.clusterName)
                        .withImageType(this.imageType)
                        .withImageVersion(this.imageVersion)
                        .withAutoTerminate(this.autoTerminate)
                        .withLogUri(this.logUri)
                        .withInstanceGroup(new InstanceGroupConfig()
                                .withType("Master")
                                .withInstanceType("g.small")
                                .withInstanceCount(1))
                        .withInstanceGroup(new InstanceGroupConfig()
                                .withType("Core")
                                .withInstanceType("g.small")
                                .withInstanceCount(2))
                        .withStep(new JavaStepConfig()
                                .withName("init-step")
                                .withActionOnFailure("Continue")
                                .withJar("bos://bmr/samples/mapreduce/libs/hadoop-mapreduce-examples.jar")
                                .withMainClass("org.apache.hadoop.examples.WordCount")
                                .withArguments("bos://bmr/samples/mapreduce/wordcount/hamlet.txt bos://liukun01/out"))
        );
        try {
            System.out.println(JsonUtils.toJsonPrettyString(response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertThat(response, hasProperty("clusterId"));
        this.clusterId = response.getClusterId();

        testAddStep();
        testGetStep();
        testListSteps();
    }

    @Test
    public void testListClusters() {
        int maxKeys = 1;
        ListClustersResponse response = this.bmrClient.listClusters(
                new ListClustersRequest().withMaxKeys(maxKeys)
        );
        try {
            System.out.println(JsonUtils.toJsonPrettyString(response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        assertThat(response.getMaxKeys(), is(maxKeys));
        assertThat(response.getClusters(), hasSize(maxKeys));
    }

    @Test
    public void testGetCluster() {
        if (this.clusterId != null) {
            GetClusterResponse response = this.bmrClient.getCluster(this.clusterId);
            assertThat(response.getId(), is(this.clusterId));
            assertThat(response.getImageType(), is(this.imageType));
            assertThat(response.getImageVersion(), is(this.imageVersion));
        } else {
            this.thrown.expect(IllegalArgumentException.class);
            this.thrown.expectMessage("The parameter clusterId should not be null or empty string.");
            GetClusterResponse response = this.bmrClient.getCluster(this.clusterId);
        }
    }

    public void testAddStep() {
        AddStepsResponse response = this.bmrClient.addSteps(
                new AddStepsRequest().withClusterId(this.clusterId)
                        .withStep(this.javaStepConfig)
        );
        assertThat(response.getStepIds(), hasSize(this.stepCount));
        this.stepId = response.getStepIds().get(0);
    }

    public void testListSteps() {
        ListStepsResponse response = this.bmrClient.listSteps(
                new ListStepsRequest().withClusterId(this.clusterId).withMaxKeys(this.stepCount)
        );
        assertThat(response.getSteps(), hasSize(this.stepCount));
        String stepId = response.getSteps().get(0).getId();
        GetStepResponse response2 = this.bmrClient.getStep(this.clusterId, stepId);
        assertThat(response2, hasProperty("id"));
        assertThat(response2, hasProperty("name"));
        assertThat(response2, hasProperty("actionOnFailure"));
        assertThat(response2, hasProperty("type"));
        assertThat(response2, hasProperty("status"));
        assertThat(response2, hasProperty("id"));
    }

    public void testGetStep() {
        if (this.stepId != null) {
            GetStepResponse response = this.bmrClient.getStep(this.clusterId, this.stepId);
            assertThat(response.getId(), is(this.stepId));
        } else {
            this.thrown.expect(IllegalArgumentException.class);
            this.thrown.expectMessage("The parameter stepId should not be null or empty string.");
            GetStepResponse response = this.bmrClient.getStep(this.clusterId, this.stepId);
        }
    }

    @Test
    public void testTerminateCluster() {
        if (this.clusterId != null) {
            boolean terminated = false;
            int tryTime = 0;
            while (!terminated && tryTime <= 5) {
                try {
                    this.bmrClient.terminateCluster(this.clusterId);
                    terminated = true;
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

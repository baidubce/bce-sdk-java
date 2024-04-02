/*
 * Copyright 2014 Baidu, Inc.
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
import com.baidubce.services.bmr.model.ApplicationConfig;
import com.baidubce.services.bmr.model.GetClusterRequest;
import com.baidubce.services.bmr.model.InstanceGroup;
import com.baidubce.services.bmr.model.JavaStepConfig;
import com.baidubce.services.bmr.model.AddStepsRequest;
import com.baidubce.services.bmr.model.AddStepsResponse;
import com.baidubce.services.bmr.model.StreamingStepConfig;
import com.baidubce.services.bmr.model.CreateClusterRequest;
import com.baidubce.services.bmr.model.CreateClusterResponse;
import com.baidubce.services.bmr.model.GetClusterResponse;
import com.baidubce.services.bmr.model.GetStepResponse;
import com.baidubce.services.bmr.model.InstanceGroupConfig;
import com.baidubce.services.bmr.model.ListClustersRequest;
import com.baidubce.services.bmr.model.ListClustersResponse;
import com.baidubce.services.bmr.model.ListInstanceGroupsRequest;
import com.baidubce.services.bmr.model.ListInstanceGroupsResponse;
import com.baidubce.services.bmr.model.ListInstancesRequest;
import com.baidubce.services.bmr.model.ListInstancesResponse;
import com.baidubce.services.bmr.model.ListStepsRequest;
import com.baidubce.services.bmr.model.ListStepsResponse;
import com.baidubce.services.bmr.model.ModifyInstanceGroupConfig;
import com.baidubce.services.bmr.model.ModifyInstanceGroupsRequest;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;

public class BmrClientTest extends AbstractBmrClientTest {
    private String clusterId = null;
    private String clusterName;
    private String imageType;
    private String imageVersion;
    private boolean autoTerminate;
    private String logUri;

    private InstanceGroupConfig masterInstanceGroup;
    private String masterInstanceGroupId = null;
    private InstanceGroupConfig coreInstanceGroup;
    private String coreInstanceGroupId = null;

    private JavaStepConfig javaStepConfig;
    private StreamingStepConfig streamingStepConfig;
    private String stepId = null;
    private int stepCount;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        super.setUp();
        this.clusterName = "java-sdk-cluster";
        this.imageType = "hadoop";
        this.imageVersion = "2.2.2";
        this.autoTerminate = true;
        this.logUri = "bos://data-python-sdk-fwh/";

        this.stepCount = 1;
        this.javaStepConfig = new JavaStepConfig()
                .withName("java-step")
                .withActionOnFailure("Continue")
                .withJar("bos://data-python-sdk-fwh/Java/hadoop-mapreduce-examples.ja")
                .withMainClass("pi")
                .withArguments("bos://data-python-sdk-fwh/input/accesslog-1k.log bos://data-python-sdk-fwh/output/out1");

        this.streamingStepConfig = new StreamingStepConfig()
                .withName("streaming-step")
                .withInput("bos://bce-bmr-sdk-wjx/input/accesslog-1k.log")
                .withOutput("bos://bce-bmr-sdk-wjx/output/out1")
                .withMapper("mapper.py")
                .withReducer("reducer.py")
                .withActionOnFailure("Continue")
                .withArguments("-files bos://bce-bmr-sdk-wjx/mapper.py,bos://bce-bmr-sdk-wjx/reduce.py"
                        + " -libjars input.jar");

        this.masterInstanceGroup = new InstanceGroupConfig()
                .withRootDiskMediumType("ssd")
                .withRootDiskSizeInGB(50)
                .withType("Master")
                .withInstanceType("bmr.g1.xlarge")
                .withInstanceCount(1);
        this.coreInstanceGroup = new InstanceGroupConfig()
                .withRootDiskMediumType("ssd")
                .withRootDiskSizeInGB(50)
                .withType("Core")
                .withInstanceType("bmr.g1.xlarge")
                .withInstanceCount(2);
    }

    @Test
    public void testCreateCluster() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("hive");
        applicationConfig.setVersion("3.1.0");

        CreateClusterResponse response = this.bmrClient.createCluster(
                new CreateClusterRequest()
                        .withAdminPassword("*123456ying")
                        .withApplication(applicationConfig)
                        .withName(this.clusterName)
                        .withImageType(this.imageType)
                        .withImageVersion(this.imageVersion)
                        .withAutoTerminate(this.autoTerminate)
                        .withLogUri(this.logUri)
                        .withInstanceGroup(masterInstanceGroup)
                        .withInstanceGroup(coreInstanceGroup)
                        .withStep(new JavaStepConfig()
                                .withName("init-step")
                                .withActionOnFailure("Continue")
                                .withJar("bos://data-python-sdk-fwh/Java/hadoop-mapreduce-examples.jar")
                                .withMainClass("pi")
                                .withArguments("2 2"))
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
        testAddStepWithAdditionalFiles();
    }

    @Test
    public void testListClusters() {
        int maxKeys = 10;
        ListClustersResponse response = this.bmrClient.listClusters(
                new ListClustersRequest().withMaxKeys(maxKeys)
        );
        try {
            System.out.println(JsonUtils.toJsonPrettyString(response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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

    public void testAddStepWithAdditionalFiles() {
        AddStepsResponse response = this.bmrClient.addSteps(
                new AddStepsRequest().withClusterId(this.clusterId)
                        .withStep(this.streamingStepConfig)
        );
        assertThat(response.getStepIds(), hasSize(this.stepCount));
    }

    public void testListSteps() {
        ListStepsResponse response = this.bmrClient.listSteps(
                new ListStepsRequest().withClusterId(this.clusterId));
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

    @Test
    public void testListInstanceGroups() {
        if (this.clusterId != null) {
            ListInstanceGroupsResponse response = this.bmrClient.listInstanceGroups(
                    new ListInstanceGroupsRequest().withClusterId(this.clusterId)
            );
            assertThat(response.getInstanceGroups(), hasSize(2));
            for (InstanceGroup instanceGroup : response.getInstanceGroups()) {
                if (instanceGroup.getType().equalsIgnoreCase("master")) {
                    this.masterInstanceGroupId = instanceGroup.getId();
                } else if (instanceGroup.getType().equalsIgnoreCase("core")) {
                    this.coreInstanceGroupId = instanceGroup.getId();
                }
            }
        } else {
            this.thrown.expect(IllegalArgumentException.class);
            this.thrown.expectMessage("The parameter clusterId should not be null or empty string.");
            ListInstanceGroupsResponse response = this.bmrClient.listInstanceGroups(this.clusterId);
        }
    }

    @Test
    public void testListInstances() {
        if (this.clusterId != null && this.masterInstanceGroupId != null) {
            ListInstancesResponse response = this.bmrClient.listInstances(
                    new ListInstancesRequest().withClusterId(this.clusterId).withInstanceGroupId(
                            this.masterInstanceGroupId)
            );
            assertThat(response, hasProperty("instances"));
        } else {
            this.thrown.expect(IllegalArgumentException.class);
            if (clusterId == null) {
                this.thrown.expectMessage("The parameter clusterId should not be null or empty string.");
            } else {
                this.thrown.expectMessage("The parameter instanceGroupId should not be null or empty string.");
            }
            ListInstancesResponse response = this.bmrClient.listInstances(this.clusterId, this.masterInstanceGroupId);
        }
    }

    @Test
    public void testModifyInstanceGroups() {
        ModifyInstanceGroupsRequest request = new ModifyInstanceGroupsRequest();
        request.setClusterId(this.clusterId);
        ModifyInstanceGroupConfig coreGroup = new ModifyInstanceGroupConfig();
        coreGroup.setId(this.coreInstanceGroupId);
        coreGroup.setInstanceCount(4);
        request.setInstanceGroups(Arrays.asList(coreGroup));
        if (this.clusterId != null && this.coreInstanceGroupId != null) {
            this.bmrClient.modifyInstanceGroups(request);

            String state = this.bmrClient.getCluster(
                    new GetClusterRequest().withClusterId(this.clusterId)).getStatus().getState();
            assertThat(state, equalToIgnoringCase("Scaling"));
        } else {
            this.thrown.expect(IllegalArgumentException.class);
            if (clusterId == null) {
                this.thrown.expectMessage("The parameter clusterId should not be null or empty string.");
            } else {
                this.thrown.expectMessage("The parameter instanceGroupId should not be null or empty string.");
            }
            this.bmrClient.getCluster(new GetClusterRequest().withClusterId(this.clusterId));
        }
    }

}

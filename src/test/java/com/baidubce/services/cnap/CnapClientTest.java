/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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

package com.baidubce.services.cnap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.baidubce.services.cnap.model.workspace.GetWorkspaceNameRequest;
import com.baidubce.services.cnap.model.workspace.GetWorkspaceNameResponse;
import org.hamcrest.CustomMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.BceServiceException;
import com.baidubce.Protocol;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.cnap.model.access.CreateAccessModel;
import com.baidubce.services.cnap.model.access.CreateAccessRequest;
import com.baidubce.services.cnap.model.access.CreateAccessResponse;
import com.baidubce.services.cnap.model.access.DeleteAccessRequest;
import com.baidubce.services.cnap.model.access.DeleteAccessResponse;
import com.baidubce.services.cnap.model.access.ListAccessRequest;
import com.baidubce.services.cnap.model.access.ListAccessResponse;
import com.baidubce.services.cnap.model.access.NewAccessModel;
import com.baidubce.services.cnap.model.application.ApplicationModel;
import com.baidubce.services.cnap.model.application.CreateApplicationRequest;
import com.baidubce.services.cnap.model.application.CreateApplicationResponse;
import com.baidubce.services.cnap.model.application.DeleteApplicationRequest;
import com.baidubce.services.cnap.model.application.DeleteApplicationResponse;
import com.baidubce.services.cnap.model.application.DeployGroupModel;
import com.baidubce.services.cnap.model.application.ListApplicationRequest;
import com.baidubce.services.cnap.model.application.ListApplicationResponse;
import com.baidubce.services.cnap.model.application.WorkloadType;
import com.baidubce.services.cnap.model.cluster.BindClusterToWorkspaceRequest;
import com.baidubce.services.cnap.model.cluster.BindClusterToWorkspaceResponse;
import com.baidubce.services.cnap.model.cluster.ImportClusterRequest;
import com.baidubce.services.cnap.model.cluster.ImportClusterResponse;
import com.baidubce.services.cnap.model.cluster.NewClusterModel;
import com.baidubce.services.cnap.model.cluster.ReleaseClusterRequest;
import com.baidubce.services.cnap.model.cluster.ReleaseClusterResponse;
import com.baidubce.services.cnap.model.cluster.UnbindClusterToWorkspaceRequest;
import com.baidubce.services.cnap.model.deploygroup.ContainerModel;
import com.baidubce.services.cnap.model.deploygroup.CreateDeployGroupRequest;
import com.baidubce.services.cnap.model.deploygroup.CreateDeployGroupResponse;
import com.baidubce.services.cnap.model.deploygroup.DataSourceModel;
import com.baidubce.services.cnap.model.deploygroup.DeleteDeployGroupRequest;
import com.baidubce.services.cnap.model.deploygroup.DeleteDeployGroupResponse;
import com.baidubce.services.cnap.model.deploygroup.DeployGroupType;
import com.baidubce.services.cnap.model.deploygroup.DeploySpecModel;
import com.baidubce.services.cnap.model.deploygroup.EnvVarModel;
import com.baidubce.services.cnap.model.deploygroup.GetDeployGroupRequest;
import com.baidubce.services.cnap.model.deploygroup.GetDeployGroupResponse;
import com.baidubce.services.cnap.model.deploygroup.InputConfigModel;
import com.baidubce.services.cnap.model.deploygroup.LamaConfigModel;
import com.baidubce.services.cnap.model.deploygroup.ListDeployGroupByImageRequest;
import com.baidubce.services.cnap.model.deploygroup.ListDeployGroupByImageResponse;
import com.baidubce.services.cnap.model.deploygroup.ListDeployGroupByPageRequest;
import com.baidubce.services.cnap.model.deploygroup.ListDeployGroupByPageResponse;
import com.baidubce.services.cnap.model.deploygroup.LogConfigModel;
import com.baidubce.services.cnap.model.deploygroup.MetricConfigModel;
import com.baidubce.services.cnap.model.deploygroup.NewComponentModel;
import com.baidubce.services.cnap.model.deploygroup.NewDeployGroupModel;
import com.baidubce.services.cnap.model.deploygroup.PodLogsConfigModel;
import com.baidubce.services.cnap.model.deploygroup.PodMetricsConfigModel;
import com.baidubce.services.cnap.model.deploygroup.ResourceRequirementsModel;
import com.baidubce.services.cnap.model.deploygroup.ScaleDeployGroupRequest;
import com.baidubce.services.cnap.model.deploygroup.ScaleDeployGroupResponse;
import com.baidubce.services.cnap.model.environment.BindClusterToEnvironmentRequest;
import com.baidubce.services.cnap.model.environment.BindClusterToEnvironmentResponse;
import com.baidubce.services.cnap.model.environment.CNAPRegion;
import com.baidubce.services.cnap.model.environment.CreateEnvironmentRequest;
import com.baidubce.services.cnap.model.environment.CreateEnvironmentResponse;
import com.baidubce.services.cnap.model.environment.DeleteEnvironmentReponse;
import com.baidubce.services.cnap.model.environment.DeleteEnvironmentRequest;
import com.baidubce.services.cnap.model.environment.ListEnvironmentRequest;
import com.baidubce.services.cnap.model.environment.ListEnvironmentResponse;
import com.baidubce.services.cnap.model.environment.PageEnvironmentModel;
import com.baidubce.services.cnap.model.environment.UnbindClusterToEnvironmentRequest;
import com.baidubce.services.cnap.model.environment.UnbindClusterToEnvironmentResponse;
import com.baidubce.services.cnap.model.environment.UpdateEnvironmentRequest;
import com.baidubce.services.cnap.model.monitoring.CreateAlertRulesRequest;
import com.baidubce.services.cnap.model.monitoring.CreateAlertRulesResponse;
import com.baidubce.services.cnap.model.monitoring.DeleteAlertRulesRequest;
import com.baidubce.services.cnap.model.monitoring.DeleteAlertRulesResponse;
import com.baidubce.services.cnap.model.monitoring.GetMonitorDataRequest;
import com.baidubce.services.cnap.model.monitoring.GetMonitorDataResponse;
import com.baidubce.services.cnap.model.monitoring.ListAlertRecordResponse;
import com.baidubce.services.cnap.model.monitoring.ListAlertRulesRequest;
import com.baidubce.services.cnap.model.monitoring.ListAlertRulesResponse;
import com.baidubce.services.cnap.model.monitoring.ListAlertRecordRequest;
import com.baidubce.services.cnap.model.monitoring.UpdateAlertRulesRequest;
import com.baidubce.services.cnap.model.monitoring.UpdateAlertRulesResponse;
import com.baidubce.services.cnap.model.releaserecord.CreateReleaseRecordRequest;
import com.baidubce.services.cnap.model.releaserecord.CreateReleaseRecordResponse;
import com.baidubce.services.cnap.model.releaserecord.GetReleaseRecordProgressRequest;
import com.baidubce.services.cnap.model.releaserecord.GetReleaseRecordProgressResponse;
import com.baidubce.services.cnap.model.releaserecord.ImageAndExpectVersionModel;
import com.baidubce.services.cnap.model.releaserecord.ListReleaseRecordRequest;
import com.baidubce.services.cnap.model.releaserecord.ListReleaseRecordResponse;
import com.baidubce.services.cnap.model.releaserecord.RollbackReleaseRecordRequest;
import com.baidubce.services.cnap.model.releaserecord.RollbackReleaseRecordResponse;
import com.baidubce.services.cnap.model.releaserecord.TaskReqModel;
import com.baidubce.services.cnap.model.repository.CreateRepositoryRequest;
import com.baidubce.services.cnap.model.repository.CreateRepositoryResponse;
import com.baidubce.services.cnap.model.repository.ListImageRequest;
import com.baidubce.services.cnap.model.repository.ListImageResponse;
import com.baidubce.services.cnap.model.workspace.CreateWorkspaceRequest;
import com.baidubce.services.cnap.model.workspace.CreateWorkspaceResponse;
import com.baidubce.services.cnap.model.workspace.DeleteWorkspaceRequest;
import com.baidubce.services.cnap.model.workspace.DeleteWorkspaceResponse;
import com.baidubce.services.cnap.model.workspace.GetWorkspaceRequest;
import com.baidubce.services.cnap.model.workspace.GetWorkspaceResponse;
import com.baidubce.services.cnap.model.workspace.ListWorkspaceRequest;
import com.baidubce.services.cnap.model.workspace.ListWorkspaceResponse;
import com.baidubce.services.cnap.model.workspace.NewWorkspaceModel;
import com.baidubce.services.cnap.model.workspace.UpdateWorkspaceRequest;
import com.baidubce.services.cnap.model.workspace.UpdateWorkspaceResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Objects;

/**
 * Test class for testing cnap service.
 */
@RunWith(Enclosed.class)
public class CnapClientTest {

    @Ignore
    public static class Base {

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        protected static final Logger logger = LoggerFactory.getLogger(CnapClientTest.class);

        protected final String ak = "YourAK";
        protected final String sk = "YourSK";
        protected static String endpoint = "gzns-inf-matrix0.gzns.baidu.com:8097";

        protected CnapClientConfiguration config;

        protected String workspaceID;
        protected String clusterID;
        protected String workspaceName;
        protected String importedClusterID;
        protected String environmentName;
        protected String environmentID;
        protected String repositoryName;
        protected String repositoryID;
        protected String username;
        protected String password;
        protected String accessName;
        protected int port;
        protected int targetPort;
        protected String deployGroupName;
        protected String bapRepositoryID;
        protected String imageName;
        protected String imagePath;
        protected double cpuRequirement;
        protected int memoryRequirement;
        protected String applicationID;
        protected String deployGroupID;
        protected String simpleImagePath;
        protected String applicationName;
        protected String promql;

        public void setUp() {
            this.config = new CnapClientConfiguration();
            this.config.setCredentials(new DefaultBceCredentials(ak, sk));
            this.config.setProtocol(Protocol.HTTP);
            this.config.setEndpoint(endpoint);

            this.workspaceID = "ws-qqHXXpBp";
            this.clusterID = "c-B3KcHo0v";
            this.workspaceName = "ws-for-sdk";
            this.importedClusterID = "clus-bj-gyepGZQn";
            this.environmentName = "env-for-sdk";
            this.environmentID = "env-Qyw93g7Y";
            this.repositoryName = "repository-for-sdk-use";
            this.repositoryID = "r-XEJDwYaV";
            this.username = "beiming";
            this.password = "MhxzKhl@123";
            this.accessName = "access-for-sdk";
            this.port = 9999;
            this.targetPort = 9999;
            this.deployGroupName = "deploy-group-for-sdk";
            this.bapRepositoryID = "r-XEJDwYaV";
            this.imageName = "consumer-demo";
            this.imagePath = "hub.baidubce.com/zwt-repo-demo/consumer-demo:demo-1.2.2.5";
            this.cpuRequirement = 0.88;
            this.memoryRequirement = 1818;
            this.applicationID = "app-33AaPegt";
            this.deployGroupID = "d-gpcqGS56";
            this.simpleImagePath = "hub.baidubce.com/zwt-repo-demo/consumer-demo";
            this.applicationName = "app-for-sdk";
            this.promql = "(1-(node_memory_MemAvailable / node_memory_MemTotal)) * 100";
        }

        public void tearDown() {
            // do something
            logger.info("Base test tearDown");
        }

        public void toJsonPrettyString(String method, Object object) {
            try {
                logger.info("[{}]==>{}", method, JsonUtils.toJsonPrettyString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        protected void expectBceServiceException(final int statusCode, final String errorCode) {
            this.thrown.expect(new CustomMatcher<Throwable>(
                    "BceServiceException [ statusCode=" + statusCode + ", errorCode=" + errorCode + "]") {
                @Override
                public boolean matches(Object item) {
                    return (item instanceof BceServiceException)
                            && ((BceServiceException) item).getStatusCode() == statusCode
                            && Objects.equal(((BceServiceException) item).getErrorCode(), errorCode);
                }
            });
        }
    }

    /**
     * Test case about workspace.
     */
    public static class WorkspaceTest extends Base {
        protected CnapClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new CnapClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void testCreateWorkspace() {
            NewWorkspaceModel newWorkspaceModel = new NewWorkspaceModel().withName(workspaceName).withDescription(
                    "the workspace created by sdk api.");
            CreateWorkspaceRequest request = new CreateWorkspaceRequest().withNewWorkspaceModel(newWorkspaceModel);
            CreateWorkspaceResponse response = client.createWorkspace(request);
            toJsonPrettyString("createWorkspace", response);
        }

        @Test
        public void testGetWorkspace() {
            GetWorkspaceRequest request = new GetWorkspaceRequest().withWorkspaceID("ws-Kz1429qE");
            GetWorkspaceResponse response = client.getWorkspace(request);
            toJsonPrettyString("getWorkspace", response);
        }

        @Test
        public void testGetWorkspaceName() {
            GetWorkspaceNameRequest request = new GetWorkspaceNameRequest().withWorkspaceID("ws-Kz1429qE").withUserID("beiming");
            GetWorkspaceNameResponse response = client.getWorkspaceName(request);
            toJsonPrettyString("getWorkspaceName", response);
        }

        @Test
        public void testUpdateWorkspace() {
            UpdateWorkspaceRequest request = new UpdateWorkspaceRequest()
                    .withWorkspaceID("ws-Kz1429qE").withDescription("new description");
            UpdateWorkspaceResponse response = client.updateWorkspace(request);
            toJsonPrettyString("updateWorkspace", response);
        }

        @Test
        public void testListWorkspace() {
            ListWorkspaceRequest request = new ListWorkspaceRequest();
            ListWorkspaceResponse response = client.listWorkspace(request);
            toJsonPrettyString("listWorkspace", response);
        }

        @Test
        public void testDeleteWorkspace() {
            DeleteWorkspaceRequest request = new DeleteWorkspaceRequest().withWorkspaceID("ws-Kz1429qE");
            DeleteWorkspaceResponse response = client.deleteWorkspace(request);
            toJsonPrettyString("deleteWorkspace", response);
        }

    }

    /**
     * Test case about cluster.
     */
    public static class ClusterModelTest extends Base {
        protected CnapClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new CnapClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void testImportCluster() {
            NewClusterModel newClusterModel = new NewClusterModel().withUnderlayClusterID(clusterID);
            ImportClusterRequest importClusterRequest = new ImportClusterRequest()
                    .withNewClusterModel(newClusterModel).
                            withWorkspaceList(Arrays.asList(workspaceID));
            ImportClusterResponse response = client.importCluster(importClusterRequest);
            toJsonPrettyString("importCluster", response);
        }

        @Test
        public void testReleaseCluster() {
            ReleaseClusterRequest request = new ReleaseClusterRequest().withClusterID(clusterID);
            ReleaseClusterResponse response = client.releaseCluster(request);
            toJsonPrettyString("releaseCluste", response);
        }

        @Test
        public void testBindClusterToWorkspace() {
            BindClusterToWorkspaceRequest bindClusterToWorkspaceRequest = new BindClusterToWorkspaceRequest();
            bindClusterToWorkspaceRequest.withWorkspaceID(workspaceID).withClusterIDs(Arrays.asList(importedClusterID));
            BindClusterToWorkspaceResponse response = client.bindClusterToWorkspace(bindClusterToWorkspaceRequest);
            toJsonPrettyString("bindClusterToWorkspace", response);
        }

        @Test
        public void testUnbindClusterToWorkspace() {
            UnbindClusterToWorkspaceRequest unbindClusterToWorkspaceRequest = new UnbindClusterToWorkspaceRequest();
            unbindClusterToWorkspaceRequest.
                    withWorkspaceID(workspaceID).
                    withClusterID(importedClusterID);
            client.unbindClusterToWorkspace(unbindClusterToWorkspaceRequest);
        }

    }

    /**
     * Test case about environment.
     */
    public static class EnvironmentModelTest extends Base {
        protected CnapClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new CnapClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void testCreateEnvironment() {
            CreateEnvironmentRequest request = new CreateEnvironmentRequest();
            request.withName(environmentName).withWorkspaceID(workspaceID);
            CreateEnvironmentResponse response = client.createEnvironment(request);
            toJsonPrettyString("createEnvironment", response);
        }

        @Test
        public void testBindClusterToEnvironment() {
            BindClusterToEnvironmentRequest request = new BindClusterToEnvironmentRequest();
            request.withWorkspaceID(workspaceID).withClusterID("clus-bj-dTVnWzf7").withEnvironmentID("env-y9MCf7Kq");
            BindClusterToEnvironmentResponse response = client.bindClusterToEnvironment(request);
            toJsonPrettyString("bindClusterToEnvironment", response);
        }


        @Test
        public void testUnbindClusterToEnvironment() {
            UnbindClusterToEnvironmentRequest request = new UnbindClusterToEnvironmentRequest();
            request.withWorkspaceID(workspaceID).withEnvironmentID("env-y9MCf7Kq");
            UnbindClusterToEnvironmentResponse response = client.unBindClusterToEnvironment(request);
            toJsonPrettyString("bindClusterToEnvironment", response);
        }

        @Test
        public void testDeleteEnvironment() {
            DeleteEnvironmentRequest request = new DeleteEnvironmentRequest();
            request.withWorkspaceID(workspaceID).withEnvironmentId("env-y9MCf7Kq");
            DeleteEnvironmentReponse response = client.deleteEnvironment(request);
            toJsonPrettyString("deleteEnvironment", response);

        }

        @Test
        public void testListEnvironment() {
            ListEnvironmentRequest request = new ListEnvironmentRequest();
            request.withWorkspaceID(workspaceID);
            ListEnvironmentResponse response = client.listEnvironment(request);
            toJsonPrettyString("listEnvironment", response);
        }
    }

    /**
     * Test case about repository.
     */
    public static class RepositoryTest extends Base {
        protected CnapClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new CnapClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void testCreateRepository() {
            CreateRepositoryRequest repositoryRequest = new CreateRepositoryRequest();
            repositoryRequest.withWorkspaceID(workspaceID).withUsername(username).withPassword(password)
                    .withName(repositoryName);
            CreateRepositoryResponse response = client.createRepository(repositoryRequest);
            toJsonPrettyString("createRepository", response);
        }

        @Test
        public void listImage() {
            ListImageRequest request = new ListImageRequest();
            request.withWorkspaceID(workspaceID).withRepositoryID(repositoryID);
            ListImageResponse response = client.listImage(request);
            toJsonPrettyString("listImage", response);
        }
    }

    /**
     * Test case about access.
     */
    public static class AccessTest extends Base {
        protected CnapClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new CnapClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void testCreateAccess() {
            CreateAccessRequest request = new CreateAccessRequest();
            CreateAccessModel accessModel = new CreateAccessModel();
            accessModel.withWorkspaceID("ws-qqHXXpBp").withApplicationID("app-33AaPegt").withDeployGroupID("d-gpcqGS56")
                    .withName(accessName).withPort(port).withTargetPort(targetPort);
            request.addAccess(accessModel);
            CreateAccessResponse response = client.createAccess(request);
            toJsonPrettyString("createAccess", response);
        }

        @Test
        public void testDeleteAccess() {
            DeleteAccessRequest request = new DeleteAccessRequest();
            request.withWorkspaceID("ws-qqHXXpBp").withApplicationID("app-33AaPegt").withDeployGroupID("d-gpcqGS56")
                    .withAccessID("ac-bRy3a9J0");
            DeleteAccessResponse response = client.deleteAccess(request);
            toJsonPrettyString("deleteAccess", response);
        }


        @Test
        public void testListAccess() {
            ListAccessRequest request = new ListAccessRequest();
            request.withWorkspaceID("ws-qqHXXpBp").withApplicationID("app-33AaPegt").withDeployGroupID("d-gpcqGS56");
            ListAccessResponse response = client.listAccess(request);
            toJsonPrettyString("listAccess", response);
        }
    }

    /**
     * Test case about deploy group.
     */
    public static class DeployGroupTest extends Base {
        protected CnapClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new CnapClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void testCreateDeployGroup() {
            NewAccessModel access = new NewAccessModel().withName(accessName).withPort(port).withTargetPort(targetPort);
            ResourceRequirementsModel resource = new ResourceRequirementsModel()
                    .withCpuRequirement(cpuRequirement).withMemoryRequirement(memoryRequirement);
            ContainerModel container = new ContainerModel()
                    .withName(imageName).withBapRepositoryID(bapRepositoryID)
                    .withImage(imagePath).withResources(resource);
            DeploySpecModel conf = new DeploySpecModel().addContainerModel(container);
            NewDeployGroupModel deployGroup = new NewDeployGroupModel();
            deployGroup.withEnvironmentID(environmentID).withName(deployGroupName).withDeploySpecModel(conf);
            CreateDeployGroupRequest request = new CreateDeployGroupRequest();
            request.withWorkspaceID("ws-qqHXXpBp").withApplicationID("app-33AaPegt")
                    .addAccess(access).withDeployGroup(deployGroup);
            CreateDeployGroupResponse response = client.createDeployGroup(request);
            toJsonPrettyString("createDeployGroup", response);
        }

        @Test
        public void testGetDeployGroup() {
            GetDeployGroupRequest request = new GetDeployGroupRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(applicationID).withDeployGroupID(deployGroupID);
            GetDeployGroupResponse response = client.getDeployGroup(request);
            toJsonPrettyString("getDeployGroup", response);
        }

        @Test
        public void testListDeplyGroupByPage() {
            ListDeployGroupByPageRequest request = new ListDeployGroupByPageRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(applicationID);
            ListDeployGroupByPageResponse response = client.listDeployGroupByPage(request);
            toJsonPrettyString("listDeployGroupByPage", response);
        }

        @Test
        public void testListDeployGroupByImage() {
            ListDeployGroupByImageRequest request = new ListDeployGroupByImageRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(applicationID);
            ListDeployGroupByImageResponse response = client.listDeployGroupByImage(request);
            toJsonPrettyString("listDeployGroupByImage", response);
        }

        @Test
        public void testDeleteDeployGroup() {
            DeleteDeployGroupRequest request = new DeleteDeployGroupRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(applicationID).withDeployGroupID("d-qFEK8cqF");
            DeleteDeployGroupResponse response = client.deleteDeployGroup(request);
            toJsonPrettyString("deleteDeployGroup", response);
        }

        @Test
        public void testScaleDeployGroup() {
            ScaleDeployGroupRequest request = new ScaleDeployGroupRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(applicationID).withDeployGroupID(deployGroupID)
                    .withReplicas(1);
            ScaleDeployGroupResponse response = client.scaleDeployGroup(request);
            toJsonPrettyString("scaleDeployGroup", response);
        }
    }

    /**
     * Test case about deploy group.
     */
    public static class ApplicationTest extends Base {
        protected CnapClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new CnapClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void createApplication() {
            ApplicationModel applicationModel = new ApplicationModel().withName(applicationName);
            CreateApplicationRequest request =
                    new CreateApplicationRequest().withApplication(applicationModel).withWorkspaceID(workspaceID);
            CreateApplicationResponse response = client.createApplication(request);
            toJsonPrettyString("createApplication", response);
        }

        @Test
        public void listApplication() {
            ListApplicationRequest request = new ListApplicationRequest().withWorkspaceID(workspaceID);
            ListApplicationResponse response = client.listApplication(request);
            toJsonPrettyString("listApplication", response);
        }

        @Test
        public void deleteApplication() {
            DeleteApplicationRequest request =
                    new DeleteApplicationRequest().withWorkspaceID(workspaceID).withApplicationID("app-XYMp39qh")
                            .withIgnoreUnderlayError(true).withSkipDeleteUnderlay(true);
            DeleteApplicationResponse response = client.deleteApplication(request);
            toJsonPrettyString("deleteApplication", response);
        }
    }


    /**
     * Test case about release record.
     */
    public static class ReleaseRecordTest extends Base {
        protected CnapClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new CnapClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void testCreateReleaseRecord() {
            ImageAndExpectVersionModel imageModel =
                    new ImageAndExpectVersionModel().withImage(simpleImagePath).withExpectImageVersion("demo-1.2.2.2");
            TaskReqModel taskReqModel = new TaskReqModel().withDeployGroupID(deployGroupID);
            CreateReleaseRecordRequest request =
                    new CreateReleaseRecordRequest().withWorkspaceID(workspaceID).withApplicationID(applicationID)
                            .withDescription("上线变更").addImageAndExpectVersionModel(imageModel)
                            .addTaskReqModel(taskReqModel);
            CreateReleaseRecordResponse response = client.createReleaseRecord(request);
            toJsonPrettyString("createReleaseRecord", response);
        }

        @Test
        public void testRollbackReleaseRecord() {
            TaskReqModel taskReqModel = new TaskReqModel().withDeployGroupID(deployGroupID);
            RollbackReleaseRecordRequest request = new RollbackReleaseRecordRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(applicationID)
                    .withReleaseRecordID("3eb7f301-a3d8-4fd8-ab10-ac7771cffd60")
                    .withDescription("回滚变更").addTaskReqModel(taskReqModel);
            RollbackReleaseRecordResponse response = client.rollbackReleaseRecord(request);
            toJsonPrettyString("rollbackReleaseRecord", response);
        }

        @Test
        public void testListReleaseRecord() throws ParseException {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ListReleaseRecordRequest request =
                    new ListReleaseRecordRequest().withWorkspaceID(workspaceID).withApplicationID(applicationID)
                            .withStartTime(simpleDateFormat.parse("2019-08-24 00:00:00"))
                            .withEndTime(simpleDateFormat.parse("2019-08-31 00:00:00"));
            ListReleaseRecordResponse response = client.listReleaseRecord(request);
            toJsonPrettyString("listReleaseRecord", response);
        }

        @Test
        public void testGetReleaseRecordProgress() {
            GetReleaseRecordProgressRequest request = new GetReleaseRecordProgressRequest()
                    .withWorkspaceID(workspaceID).withApplicationID(applicationID)
                    .withReleaseRecordID("5add5056-d362-4cf1-806f-c71d28513b6b");
            GetReleaseRecordProgressResponse response = client.getReleaseRecordProgress(request);
            toJsonPrettyString("getReleaseRecordProgress", response);
        }
    }


    /**
     * Test case about release record.
     */
    public static class AlertRulesTest extends Base {
        protected CnapClient client;

        @Before
        public void setUp() {
            super.setUp();
            client = new CnapClient(config);
        }

        @After
        public void tearDown() {
            // do something
            super.tearDown();
        }

        @Test
        public void testAddAlertRules() {
            Map<String, String> labels = new HashMap<String, String>();
            labels.put("key1", "value1");
            labels.put("key2", "value2");
            CreateAlertRulesRequest request =
                    new CreateAlertRulesRequest()
                            .withName("rule-from-sdk").withOp("==")
                            .withDuration("2m").withPromql(promql).withRepeatInterval("10m").withThreshold(20)
                            .withLabels(labels);
            CreateAlertRulesResponse response = client.createAlertRules(request);
            toJsonPrettyString("createAlertRules", response);
        }

        @Test
        public void testUpdateAlertRules() {
            Map<String, String> labels = new HashMap<String, String>();
            labels.put("key1", "value1");
            labels.put("key2", "value2");
            UpdateAlertRulesRequest request = new UpdateAlertRulesRequest()
                    .withAlertRuleID("ar-MXfeTwTm").withDuration("2m")
                    .withPromql(promql).withOp("==").withLabels(labels)
                    .withRepeatInterval("10m").withDescription("update description").withThreshold(10);
            UpdateAlertRulesResponse response = client.updateAlertRules(request);
            toJsonPrettyString("updateAlertRules", response);
        }

        @Test
        public void testDeleteAlertRules() {
            DeleteAlertRulesRequest request = new DeleteAlertRulesRequest().withAlertRuleID("ar-5tz4BrbP");
            DeleteAlertRulesResponse response = client.deleteAlertRules(request);
            toJsonPrettyString("deleteAlertRules", response);
        }


        @Test
        public void testQueryMonitorData() {
            GetMonitorDataRequest request = new GetMonitorDataRequest()
                    .withQuery("avg(rate(node_disk_reads_completed{region=~\"gz\",clusterName=~\"mesh-test\","
                            + "instance=~\".*\"}[5m]))")
                    .withStart(1568040691).withEnd(1568042491).withStep(15);
            GetMonitorDataResponse response = client.getMonitorData(request);
            toJsonPrettyString("getMonitorData", response);

        }

        @Test
        public void testListAlertRules() {
            ListAlertRulesRequest request = new ListAlertRulesRequest();
            ListAlertRulesResponse response = client.listAlertRules(request);
            toJsonPrettyString("listAlertRules", response);
        }

        @Test
        public void testListAlertRecord() {
            ListAlertRecordRequest request = new ListAlertRecordRequest().
                    withStartsAt(1567436371000L).withEndsAt(1568041171000L);
            ListAlertRecordResponse response = client.listAlertRecord(request);
            toJsonPrettyString("listAlertRecord", response);
        }
    }

    /**
     * The demo which indicates how to use cnap.
     */
    public static class Demo {
        protected CnapClient client;

        protected final String ak = "6025254c24ad40c1a0732e9bdf0150ef";
        protected final String sk = "ff9efbc8f9e44c58a0cf25aa30f6d3b0";
        protected static String endpoint = "gzns-inf-matrix0.gzns.baidu.com:8060";

        protected String cluster1ID;
        protected String importedCluster1ID;
        protected String cluster2ID;
        protected String importedCluster2ID;
        protected String workspaceName;
        protected String workspaceID;
        protected String username;
        protected String password;
        protected String repositoryName;
        private String bapRepositoryID;
        protected CnapClientConfiguration config;
        protected double cpuRequirement;
        protected int memoryRequirement;
        protected String imageName;
        protected String imagePath;
        protected String deployGroup1Name;
        protected String deployGroup2Name;
        protected String environment1ID;
        protected String environment2ID;
        protected String applicatnName;
        protected String applicationID;
        protected String deployGroup2ID;
        protected String accessName;
        protected int port;
        protected int targetPort;
        protected String simpleImagePath;
        protected String access2ID;

        @Before
        public void setUp() {
            this.config = new CnapClientConfiguration();
            this.config.setCredentials(new DefaultBceCredentials(ak, sk));
            this.config.setProtocol(Protocol.HTTP);
            this.config.setEndpoint(endpoint);
            client = new CnapClient(config);

            // set your cluster id in cce.
            this.cluster1ID = "c-OBSPLylc";
            this.cluster2ID = "c-qzl4djkv";

            // set your workspace name.
            this.workspaceName = "workspace-for-sdk";

            // set your repository info.
            this.repositoryName = "repository-for-sdk";
            this.username = "beiming";
            this.password = "MhxzKhl@123";

            // set your resource usage.
            this.cpuRequirement = 0.88;
            this.memoryRequirement = 1818;
            this.imageName = "consumer-demo";
            this.imagePath = "hub.baidubce.com/zwt-repo-demo/consumer-demo:demo-1.2.2.5";

            // set your deploy group name.
            this.deployGroup1Name = "GROUP1";
            this.deployGroup2Name = "GROUP2";

            this.applicatnName = "app-consumer-demo";

            // set your access info.
            this.accessName = "access-for-sdk";
            this.port = 9999;
            this.targetPort = 9999;

            // set your image path.
            this.simpleImagePath = "hub.baidubce.com/zwt-repo-demo/consumer-demo";
        }

        @Test
        public void createAndDelete() throws InterruptedException {
            // create resource
            createResource();

            // operate application and deploy group.
            operateAppAndDeployGroup();
            Thread.currentThread().sleep(10000);

            // create release record.
            releaseRecord();
            Thread.currentThread().sleep(10000);

            // delete resource.
            delete();
        }

        /**
         * Operate application and deploy group.
         *
         * @throws InterruptedException
         */
        public void operateAppAndDeployGroup() throws InterruptedException {
            createSimpleApplication();
            createDeployGroup();
            createAccess();
            scaleDeployGroup();

            // create deploy group with environment.
            createDeployGroupWithEnv();
            // create deploy group with argument.
            createDeployGroupWithArgu();
            // create deploy group with log config.
            // createDeployGroupWithLogConf();
        }

        /**
         * Create simple application which does not contains deploy group.
         */
        public void createSimpleApplication() {
            ApplicationModel applicationModel = new ApplicationModel().withName(applicatnName);
            CreateApplicationRequest createApplicationRequest = new CreateApplicationRequest()
                    .withWorkspaceID(workspaceID).withApplication(applicationModel);
            // just create app.
            CreateApplicationResponse createWorkspaceResponse = client.createApplication(createApplicationRequest);
            applicationID = createWorkspaceResponse.getResourceID();
        }

        /**
         * Create deploy Group.
         */
        public void createDeployGroup() {
            ResourceRequirementsModel resource = new ResourceRequirementsModel()
                    .withCpuRequirement(cpuRequirement).withMemoryRequirement(memoryRequirement);
            ContainerModel container = new ContainerModel()
                    .withName(imageName).withBapRepositoryID(bapRepositoryID)
                    .withImage(imagePath).withResources(resource);
            DeploySpecModel conf = new DeploySpecModel().addContainerModel(container);
            NewDeployGroupModel deployGroup = new NewDeployGroupModel()
                    .withEnvironmentID(environment2ID).withName(deployGroup2Name).withDeploySpecModel(conf);
            CreateDeployGroupRequest request = new CreateDeployGroupRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(applicationID)
                    .withDeployGroup(deployGroup);
            // create deploy group.
            CreateDeployGroupResponse createDeployGroupResponse = client.createDeployGroup(request);
            deployGroup2ID = createDeployGroupResponse.getResourceID();
        }

        /**
         * Create deploy Group which has environment config.
         */
        public void createDeployGroupWithEnv() {
            ResourceRequirementsModel resource = new ResourceRequirementsModel()
                    .withCpuRequirement(cpuRequirement).withMemoryRequirement(memoryRequirement);
            EnvVarModel envVarModel1 = new EnvVarModel().withName("ENV1").withValue("VALUE1");
            EnvVarModel envVarModel2 = new EnvVarModel().withName("ENV2").withValue("VALUE2");
            ContainerModel container = new ContainerModel()
                    .withName(imageName).withBapRepositoryID(bapRepositoryID)
                    .withImage(imagePath).withResources(resource).addEnv(envVarModel1).addEnv(envVarModel2);
            DeploySpecModel conf = new DeploySpecModel().addContainerModel(container);
            NewDeployGroupModel deployGroup = new NewDeployGroupModel()
                    .withEnvironmentID(environment2ID).withName("GROUP-WITH-ENV").withDeploySpecModel(conf);
            CreateDeployGroupRequest request = new CreateDeployGroupRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(applicationID)
                    .withDeployGroup(deployGroup);
            // create deploy group.
            CreateDeployGroupResponse createDeployGroupResponse = client.createDeployGroup(request);
        }

        /**
         * Create deploy group which has argument config.
         */
        public void createDeployGroupWithArgu() {
            ResourceRequirementsModel resource = new ResourceRequirementsModel()
                    .withCpuRequirement(cpuRequirement).withMemoryRequirement(memoryRequirement);
            ContainerModel container = new ContainerModel()
                    .withName(imageName).withBapRepositoryID(bapRepositoryID)
                    .withImage(imagePath).withResources(resource).addArgu("--port=8080");
            DeploySpecModel conf = new DeploySpecModel().addContainerModel(container);
            NewDeployGroupModel deployGroup = new NewDeployGroupModel()
                    .withEnvironmentID(environment2ID).withName("GROUP-WITH-START-ARGU").withDeploySpecModel(conf);
            CreateDeployGroupRequest request = new CreateDeployGroupRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(applicationID)
                    .withDeployGroup(deployGroup);
            // create deploy group.
            CreateDeployGroupResponse createDeployGroupResponse = client.createDeployGroup(request);
        }

        /**
         * Create deploy group which has log config.
         */
        public void createDeployGroupWithLogConf() {
            ResourceRequirementsModel resource = new ResourceRequirementsModel()
                    .withCpuRequirement(cpuRequirement).withMemoryRequirement(memoryRequirement);
            ContainerModel container = new ContainerModel()
                    .withName(imageName).withBapRepositoryID(bapRepositoryID)
                    .withImage(imagePath).withResources(resource);
            DeploySpecModel conf = new DeploySpecModel().addContainerModel(container);

            LamaConfigModel lamaConfigModel = new LamaConfigModel();
            DataSourceModel dataSourceModel = new DataSourceModel()
                    .withContainerName("consumer-demo").withHasPodlogs(true).withHasPodmetrics(true)
                    .withIsStdouterr(false).withId("6593-1567435466487-61102").withLogFilePath("/home/work/debug/");

            DataSourceModel logFileFilter = new DataSourceModel().withContainerName("consumer-demo")
                    .withIsStdouterr(false).withLogFilePath("/home/work/debug/*");
            LogConfigModel logConfigModel = new LogConfigModel()
                    .withExcludePath("/home/work/debug/").withHost("http://127.0.0.1:9090").withIndex("index")
                    .withIsLogParserEnabled(false).withLogsOutputType("ElasticSearch")
                    .withPid("6593-1567435466487-61102").withLogFileFilter(logFileFilter);
            PodLogsConfigModel podLogsConfigModel = new PodLogsConfigModel().addLogConfigModel(logConfigModel);

            DataSourceModel metricFilFilter = new DataSourceModel().withContainerName("consumer-demo")
                    .withIsStdouterr(false).withLogFilePath("/home/work/debug/*").withPid("6593-1567435466487-61102");

            InputConfigModel input = new InputConfigModel().withMetricFileFilter(metricFilFilter);

            DataSourceModel metricFileFilter = new DataSourceModel()
                    .withContainerName("consumer-demo").withIsStdouterr(false)
                    .withLogFilePath("/home/work/debug/*").withPid("6593-1567435466487-61102");
            MetricConfigModel metricConfigModel = new MetricConfigModel()
                    .withMatch(".*").withName("COUNT").withPid("6593-1567435466487-61102").withType("counter")
                    .withMetricFileFilter(metricFileFilter);
            PodMetricsConfigModel podMetricsConfigModel = new PodMetricsConfigModel().withInputConfigModel(input)
                    .addMetricConfigModel(metricConfigModel);

            lamaConfigModel.addDatasourceModel(dataSourceModel)
                    .withPodLogsConfig(podLogsConfigModel).withPodMetricsConfigModel(podMetricsConfigModel);

            NewComponentModel componentModel = new NewComponentModel().withName("lama").withParams(lamaConfigModel);

            NewDeployGroupModel deployGroup = new NewDeployGroupModel()
                    .withEnvironmentID(environment2ID).withName("GROUP-WITH-LOG-CONF")
                    .withDeploySpecModel(conf).addComponmentModel(componentModel);
            CreateDeployGroupRequest request = new CreateDeployGroupRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(applicationID)
                    .withDeployGroup(deployGroup);
            // create deploy group.
            CreateDeployGroupResponse createDeployGroupResponse = client.createDeployGroup(request);
        }

        /**
         * Create access.
         */
        public void createAccess() {
            CreateAccessModel accessModel = new CreateAccessModel().withWorkspaceID(workspaceID).withApplicationID(
                    applicationID).withDeployGroupID(deployGroup2ID)
                    .withName(accessName).withPort(port).withTargetPort(targetPort);
            CreateAccessRequest createAccessRequest = new CreateAccessRequest().addAccess(accessModel);
            // create access
            CreateAccessResponse createAccessResponse = client.createAccess(createAccessRequest);
            access2ID = createAccessResponse.getResult().get(0).getResourceID();
        }

        /**
         * Scale deploy group.
         *
         * @throws InterruptedException
         */
        public void scaleDeployGroup() throws InterruptedException {
            checkGroupStatus(deployGroup2ID);
            ScaleDeployGroupRequest scaleDeployGroupRequest = new ScaleDeployGroupRequest()
                    .withWorkspaceID(workspaceID).withApplicationID(applicationID).withDeployGroupID(deployGroup2ID)
                    .withReplicas(2);
            // scale replicas
            ScaleDeployGroupResponse scaleDeployGroupResponse = client.scaleDeployGroup(scaleDeployGroupRequest);
        }

        /**
         * Create resource which contains cluster, workspace, repository, environment.
         */
        public void createResource() {
            NewClusterModel newCluster1Model = new NewClusterModel().withUnderlayClusterID(cluster1ID)
                    .withDescription("demo cluster for sdk.").withRegion("bj");
            ImportClusterRequest importCluster1Request = new ImportClusterRequest()
                    .withNewClusterModel(newCluster1Model);
            // import cce cluster1 into cnap.
            ImportClusterResponse importCluster1Response = client.importCluster(importCluster1Request);
            // set imported cluster1 id.
            this.importedCluster1ID = importCluster1Response.getResourceID();

            NewWorkspaceModel newWorkspaceModel = new NewWorkspaceModel().withName(workspaceName).withDescription(
                    "demo workspace for sdk.");
            CreateWorkspaceRequest createWorkspaceRequest = new CreateWorkspaceRequest().withNewWorkspaceModel(newWorkspaceModel)
                    .addClusterID(this.importedCluster1ID);
            // create workspace and bind cluster.
            CreateWorkspaceResponse createWorkspaceResponse = client.createWorkspace(createWorkspaceRequest);
            // set workspace id.
            this.workspaceID = createWorkspaceResponse.getResourceID();

            NewClusterModel newCluster2Model = new NewClusterModel().withUnderlayClusterID(cluster2ID)
                    .withDescription("demo cluster for sdk.").withRegion("bj");
            ImportClusterRequest importCluster2Request = new ImportClusterRequest()
                    .withNewClusterModel(newCluster2Model);
            // import cce cluster2 into cnap.
            ImportClusterResponse importCluster2Response = client.importCluster(importCluster2Request);
            // set imported cluster2 id.
            this.importedCluster2ID = importCluster2Response.getResourceID();

            BindClusterToWorkspaceRequest bindClusterToWorkspaceRequest = new BindClusterToWorkspaceRequest()
                    .withWorkspaceID(workspaceID).withClusterIDs(Arrays.asList(importedCluster2ID));
            // bind cluster to workspace.
            BindClusterToWorkspaceResponse bindClusterToWorkspaceResponse =
                    client.bindClusterToWorkspace(bindClusterToWorkspaceRequest);

            CreateRepositoryRequest repositoryRequest = new CreateRepositoryRequest()
                    .withWorkspaceID(workspaceID).withUsername(username).withPassword(password)
                    .withName(repositoryName);
            CreateRepositoryResponse createRepositoryResponse = client.createRepository(repositoryRequest);
            this.bapRepositoryID = createRepositoryResponse.getResourceID();
            ListEnvironmentRequest request = new ListEnvironmentRequest().withWorkspaceID(workspaceID);
            ListEnvironmentResponse listEnvironmentResponse = client.listEnvironment(request);
            for (PageEnvironmentModel model : listEnvironmentResponse.getResult()) {
                if ("PROD".equals(model.getName())) {
                    environment1ID = model.getResourceID();
                }
                if ("DEV".equals(model.getName())) {
                    environment2ID = model.getResourceID();
                }
            }

            BindClusterToEnvironmentRequest bindClusterToEnvironmentRequest1 = new BindClusterToEnvironmentRequest()
                    .withWorkspaceID(workspaceID).withClusterID(importedCluster1ID).withEnvironmentID(environment1ID);
            // bind cluster to environment.
            BindClusterToEnvironmentResponse bindClusterToEnvironmentResponse1 =
                    client.bindClusterToEnvironment(bindClusterToEnvironmentRequest1);

            BindClusterToEnvironmentRequest bindClusterToEnvironmentRequest2 = new BindClusterToEnvironmentRequest()
                    .withWorkspaceID(workspaceID).withClusterID(importedCluster2ID).withEnvironmentID(environment2ID);
            // bind cluster to environment.
            BindClusterToEnvironmentResponse bindClusterToEnvironmentResponse2 =
                    client.bindClusterToEnvironment(bindClusterToEnvironmentRequest2);
        }

        /**
         * Release record.
         *
         * @throws InterruptedException
         */
        public void releaseRecord() throws InterruptedException {
            checkGroupStatus(deployGroup2ID);
            ImageAndExpectVersionModel imageModel =
                    new ImageAndExpectVersionModel().withImage(simpleImagePath).withExpectImageVersion("demo-1.2.2.2");
            TaskReqModel taskReqModel = new TaskReqModel().withDeployGroupID(deployGroup2ID);
            CreateReleaseRecordRequest request =
                    new CreateReleaseRecordRequest().withWorkspaceID(workspaceID).withApplicationID(applicationID)
                            .withDescription("上线变更").addImageAndExpectVersionModel(imageModel)
                            .addTaskReqModel(taskReqModel);
            CreateReleaseRecordResponse releaseRecordResponse = client.createReleaseRecord(request);
        }

        /**
         * Check  application's all deploy group status.
         *
         * @throws InterruptedException
         */
        public void checkGroupStatus() throws InterruptedException {
            ListDeployGroupByPageRequest listDeployGroupByPageRequest = new ListDeployGroupByPageRequest()
                    .withWorkspaceID(workspaceID).withApplicationID(applicationID);
            ListDeployGroupByPageResponse response = client.listDeployGroupByPage(listDeployGroupByPageRequest);
            for (DeployGroupModel deployGroupModel : response.getResult()) {
                checkGroupStatus(deployGroupModel.getResourceID());
            }
        }

        /**
         * Check deploy group status.
         *
         * @param deployGroupID
         * @throws InterruptedException
         */
        public void checkGroupStatus(String deployGroupID) throws InterruptedException {
            boolean isSuccess = false;
            while (!isSuccess) {
                Thread.currentThread().sleep(5000);
                GetDeployGroupRequest getDeployGroupRequest =
                        new GetDeployGroupRequest().withWorkspaceID(workspaceID).withApplicationID(applicationID)
                                .withDeployGroupID(deployGroupID);
                GetDeployGroupResponse getDeployGroupResponse = client.getDeployGroup(getDeployGroupRequest);
                // check deploy group status;
                if ("RUNNING".equals(getDeployGroupResponse.getStatus()) && !getDeployGroupResponse.getIsLocked()) {
                    isSuccess = true;
                }
            }
        }

        /**
         * Delete deploy group, application, workspace and release cluster.
         *
         * @throws InterruptedException
         */
        public void delete() throws InterruptedException {
            checkGroupStatus();
            // delete deploy group2 access
            DeleteAccessRequest deleteAccessRequest = new DeleteAccessRequest()
                    .withWorkspaceID(workspaceID).withApplicationID(applicationID).withDeployGroupID(deployGroup2ID)
                    .withAccessID(access2ID);
            client.deleteAccess(deleteAccessRequest);
            // delete deploy group2
            DeleteDeployGroupRequest deleteDeployGroupRequest = new DeleteDeployGroupRequest()
                    .withWorkspaceID(workspaceID).withApplicationID(applicationID).withDeployGroupID(deployGroup2ID);
            DeleteDeployGroupResponse deleteDeployGroupResponse = client.deleteDeployGroup(deleteDeployGroupRequest);
            checkGroupStatus();
            // delete app mean that delete deploy group1 access, delete group1 and delete app
            DeleteApplicationRequest deleteApplicationRequest =
                    new DeleteApplicationRequest().withWorkspaceID(workspaceID).withApplicationID(applicationID)
                            .withIgnoreUnderlayError(true).withSkipDeleteUnderlay(true);
            DeleteApplicationResponse deleteApplicationResponse = client.deleteApplication(deleteApplicationRequest);
            // delete workspace
            DeleteWorkspaceRequest request = new DeleteWorkspaceRequest().withWorkspaceID(workspaceID);
            DeleteWorkspaceResponse deleteWorkspaceResponse = client.deleteWorkspace(request);
            // release cluster
            ReleaseClusterRequest releaseClusterRequest1
                    = new ReleaseClusterRequest().withClusterID(importedCluster1ID);
            ReleaseClusterRequest releaseClusterRequest2
                    = new ReleaseClusterRequest().withClusterID(importedCluster2ID);
            ReleaseClusterResponse releaseClusterResponse1 = client.releaseCluster(releaseClusterRequest1);
            ReleaseClusterResponse releaseClusterResponse2 = client.releaseCluster(releaseClusterRequest2);
        }

        @After
        public void tearDown() {
        }
    }

    /**
     * The demo which indicates how to use empty deploy group.
     */
    public static class Demo2 {
        protected CnapClient client;


        protected final String ak = "your ak";
        protected final String sk = "your sk";
        // gz RD环境
        protected static String endpoint = "gzns-inf-matrix0.gzns.baidu.com:8080";


        protected CnapClientConfiguration config;

        protected String workspaceName;
        protected String workspaceID;

        protected String application1Name;
        protected String application2Name;

        protected String application1ID;
        protected String application2ID;

        protected String deployGroupID;
        protected String deployGroupName;

        protected String environmentName;
        protected String environmentID;


        @Before
        public void setUp() {
            this.config = new CnapClientConfiguration();
            this.config.setCredentials(new DefaultBceCredentials(ak, sk));
            this.config.setProtocol(Protocol.HTTP);
            this.config.setEndpoint(endpoint);
            client = new CnapClient(config);

            // set your workspace name.
            this.workspaceName = "ws-for-internal";

            this.application1Name = "app1";
            this.application2Name = "app2";

            this.environmentName = "OFFLINE";

            this.deployGroupName = "GROUP1";
        }

        @Test
        public void createAndDelete() throws InterruptedException {
            // create resource
            createResource();

            Thread.currentThread().sleep(10000);

            // operate application and deploy group.
            operateAppAndDeployGroup();
            Thread.currentThread().sleep(10000);

            // delete resource.
            delete();
        }

        /**
         * Create resource which contains workspace and environment.
         */
        public void createResource() {

            NewWorkspaceModel newWorkspaceModel = new NewWorkspaceModel().withName(workspaceName).withDescription(
                    "demo workspace for internal.");
            CreateWorkspaceRequest createWorkspaceRequest =
                    new CreateWorkspaceRequest().withNewWorkspaceModel(newWorkspaceModel);
            // create workspace.
            CreateWorkspaceResponse createWorkspaceResponse = client.createWorkspace(createWorkspaceRequest);
            // set workspace id.
            this.workspaceID = createWorkspaceResponse.getResourceID();

            CreateEnvironmentRequest request = new CreateEnvironmentRequest();
            // must set region info.
            request.withRegion(CNAPRegion.BJ).withName(environmentName).withWorkspaceID(workspaceID)
                    .withDescription("env description");
            CreateEnvironmentResponse response = client.createEnvironment(request);

            ListEnvironmentRequest listEnvironmentRequest = new ListEnvironmentRequest().withWorkspaceID(workspaceID);
            ListEnvironmentResponse listEnvironmentResponse = client.listEnvironment(listEnvironmentRequest);
            for (PageEnvironmentModel model : listEnvironmentResponse.getResult()) {
                if ("PROD".equals(model.getName())) {
                    // use default env must update region.
                    client.updateEnvironment(new UpdateEnvironmentRequest().withWorkspaceID(workspaceID)
                            .withEnvironmentID(model.getResourceID()).withRegion(CNAPRegion.BJ)
                            .withDescription("update env region"));
                    this.environmentID = model.getResourceID();
                }
            }
        }

        /**
         * Operate application and deploy group.
         *
         * @throws InterruptedException
         */
        public void operateAppAndDeployGroup() throws InterruptedException {
            createSingleApplication();
            createDeployGroup();
            listDeployGroup();
            createApplicationWithEmptyDeployGroup();
        }

        /**
         * Create simple microservice application which does not contains deploy group.
         */
        public void createSingleApplication() {
            ApplicationModel applicationModel = new ApplicationModel().withName(application1Name)
                    .withDescription("app description").withWorkloadType(WorkloadType.MICROSERVICE_APPLICATION);
            CreateApplicationRequest createApplicationRequest = new CreateApplicationRequest()
                    .withWorkspaceID(workspaceID).withApplication(applicationModel);
            // just create app.
            CreateApplicationResponse createWorkspaceResponse = client.createApplication(createApplicationRequest);
            application1ID = createWorkspaceResponse.getResourceID();
        }

        /**
         * Create microservice application which contains deploy group.
         */
        public void createApplicationWithEmptyDeployGroup() {
            NewDeployGroupModel deployGroup = new NewDeployGroupModel().withName(deployGroupName).withType(
                    DeployGroupType.EMPTY)
                    .withEnvironmentID(environmentID).withDescription("deploy description");
            CreateDeployGroupRequest request = new CreateDeployGroupRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(application1ID)
                    .withDeployGroup(deployGroup);

            ApplicationModel applicationModel = new ApplicationModel().withName(application2Name)
                    .withDescription("app description").withWorkloadType(WorkloadType.MICROSERVICE_APPLICATION);
            CreateApplicationRequest createApplicationRequest = new CreateApplicationRequest()
                    .withWorkspaceID(workspaceID).withApplication(applicationModel).withDeployGroup(deployGroup);

            // just create app and create deploy group.
            CreateApplicationResponse createWorkspaceResponse = client.createApplication(createApplicationRequest);
            application2ID = createWorkspaceResponse.getResourceID();
        }

        /**
         * Create empty deploy Group.
         */
        public void createDeployGroup() {
            NewDeployGroupModel deployGroup = new NewDeployGroupModel().withName(deployGroupName).withType(
                    DeployGroupType.EMPTY)
                    .withEnvironmentID(environmentID).withDescription("deploy description");
            CreateDeployGroupRequest request = new CreateDeployGroupRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(application1ID)
                    .withDeployGroup(deployGroup);
            // create deploy group.
            CreateDeployGroupResponse createDeployGroupResponse = client.createDeployGroup(request);
            deployGroupID = createDeployGroupResponse.getResourceID();
        }

        /**
         * List deploy group.
         */
        public void listDeployGroup() {
            ListDeployGroupByPageRequest request = new ListDeployGroupByPageRequest();
            request.withWorkspaceID(workspaceID).withApplicationID(application1ID).withName("GROUP1");
            ListDeployGroupByPageResponse response1 = client.listDeployGroupByPage(request);
            request.withName("NOT-EXISTS-GROUP");
            ListDeployGroupByPageResponse response2 = client.listDeployGroupByPage(request);
            request.getApplicationID();
        }

        /**
         * Delete deploy group, application, workspace and release cluster.
         *
         * @throws InterruptedException
         */
        public void delete() throws InterruptedException {

            // delete deploy group.
            DeleteDeployGroupRequest deleteDeployGroupRequest = new DeleteDeployGroupRequest()
                    .withWorkspaceID(workspaceID).withApplicationID(application1ID).withDeployGroupID(deployGroupID);
            DeleteDeployGroupResponse deleteDeployGroupResponse = client.deleteDeployGroup(deleteDeployGroupRequest);

            // delete app.
            DeleteApplicationRequest deleteApplication1Request =
                    new DeleteApplicationRequest().withWorkspaceID(workspaceID).withApplicationID(application1ID)
                            .withIgnoreUnderlayError(true).withSkipDeleteUnderlay(true);
            DeleteApplicationResponse deleteApplication1Response = client.deleteApplication(deleteApplication1Request);

            // delete app mean than delete deploy group and app
            DeleteApplicationRequest deleteApplication2Request =
                    new DeleteApplicationRequest().withWorkspaceID(workspaceID).withApplicationID(application2ID)
                            .withIgnoreUnderlayError(true).withSkipDeleteUnderlay(true);
            DeleteApplicationResponse deleteApplication2Response = client.deleteApplication(deleteApplication2Request);

            // delete workspace
            DeleteWorkspaceRequest request = new DeleteWorkspaceRequest().withWorkspaceID(workspaceID);
            DeleteWorkspaceResponse deleteWorkspaceResponse = client.deleteWorkspace(request);

        }
    }
}

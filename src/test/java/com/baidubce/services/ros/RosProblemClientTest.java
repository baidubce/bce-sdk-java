/*
 * Copyright 2019 Baidu, Inc.
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
package com.baidubce.services.ros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baidubce.services.ros.model.problem.Location;
import com.baidubce.services.ros.model.problem.PartitionRequest;
import com.baidubce.services.ros.model.problem.PartitionResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ros.model.BaseResponse;
import com.baidubce.services.ros.model.Capacity;
import com.baidubce.services.ros.model.ExternalCapacity;
import com.baidubce.services.ros.model.ExternalTimeWindow;
import com.baidubce.services.ros.model.ExternalVehicleModel;
import com.baidubce.services.ros.model.TimeWindow;
import com.baidubce.services.ros.model.ExternalVehicleGroup;
import com.baidubce.services.ros.model.problem.CreateMultiDepotProblemRequest;
import com.baidubce.services.ros.model.problem.CreateMultiSingleDepotProblemRequest;
import com.baidubce.services.ros.model.problem.CreateOptimizationRequest;
import com.baidubce.services.ros.model.problem.CreateOrderedRequest;
import com.baidubce.services.ros.model.problem.CreateProblemRequest;
import com.baidubce.services.ros.model.problem.GetRouteResponse;
import com.baidubce.services.ros.model.problem.GetSolutionResponse;
import com.baidubce.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Tests for ros client.
 *
 * @author zhangmengmeng01
 * @date 2019/05/20
 */
@Slf4j
@Ignore
public class RosProblemClientTest {
    private static final String TEST_AK = "your-access-key";
    private static final String TEST_SK = "your-secret-key";
    private static final String TEST_ENDPOINT = "endpoint";

    private RosProblemClient rosProblemClient;

    @Before
    public void setUp() {
        BceClientConfiguration configuration = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(TEST_AK, TEST_SK))
                .withEndpoint(TEST_ENDPOINT);
        rosProblemClient = new RosProblemClient(configuration);
    }

    @Test
    public void createSchedulerTask() {
        CreateProblemRequest request = generateCreateProblemRequest();
        log.info("request is : {}", request);
        BaseResponse response = rosProblemClient.createSchedulerTask(request);
        System.out.println(JsonUtils.toJsonString(response));
        log.info("response is : {}", response);
    }

    @Test
    public void createSchedulerTaskV3() {
        CreateProblemRequest request = generateCreateProblemRequest();
        log.info("request is : {}", request);
        BaseResponse response = rosProblemClient.createSchedulerTaskV3(request);
        System.out.println(JsonUtils.toJsonString(response));
        log.info("response is : {}", response);
    }


    @Test
    public void getSchedulerSolution() {
        String taskId = "10c0c4d66e284d938b0fb19dbb2410df";
        GetSolutionResponse response = rosProblemClient.getSchedulerSolution(taskId);
        System.out.println(JsonUtils.toJsonString(response));
        log.info("response is : {}", response);
    }

    @Test
    public void createOptimizationTask() {
        CreateOptimizationRequest request = generateCreateOptimizationRequest();
        log.info("request is : {}", request);
        BaseResponse response = rosProblemClient.createOptimizationTask(request);
        log.info("response is : {}", response);
    }

    @Test
    public void getOptimizationRoute() {
        String taskId = "68a6332f86a74b77b03a1cd7a39cecfb";
        GetRouteResponse response = rosProblemClient.getOptimizationRoute(taskId);
        log.info("response is : {}", response);
    }

    @Test
    public void createOrderedTask() {
        CreateOrderedRequest request = generateCreateOrderedRequest();
        log.info("request is : {}", request);
        BaseResponse response = rosProblemClient.createOrderedTask(request);
        log.info("response is : {}", response);
    }

    @Test
    public void getOrderedRoute() {
        String taskId = "a30f0b7d12cd4aa792f009d539f1a384";
        GetRouteResponse response = rosProblemClient.getOrderedRoute(taskId);
        log.info("response is : {}", response);
    }

    @Test
    public void createMultiDepotsTask() {
        CreateMultiDepotProblemRequest request = generateCreateMultiDepotProblemRequest();
        log.info("request is : {}", request);
        BaseResponse response = rosProblemClient.createMultiDepotsTask(request);
        log.info("response is : {}", response);
    }

    @Test
    public void createMultiSingleDepotsTask() {
        CreateMultiSingleDepotProblemRequest request = generateCreateMultiSingleDepotProblemRequest();
        log.info("request is : {}", request);
        BaseResponse response = rosProblemClient.createMultiSingleDepotTask(request);
        log.info("response is : {}", response);
    }

    @Test
    public void partition() {
        PartitionRequest request = generatePartitionRequest();
        log.info("request is : {}", request);
        PartitionResponse response = rosProblemClient.partition(request, "/test");
        log.info("response is : {}", response);
    }

    private CreateProblemRequest generateCreateProblemRequest() {
        CreateProblemRequest request = new CreateProblemRequest();
        request.setScenesType("SMALL_SCALE_GENERAL");
        request.setMatrixId("a9aef9e844c3498da77e83db1d553bf4");
        request.setCommitId("fe5785c384d8482daa8ccdf7d3b306d7");
        request.setLbsType("LEAST_TIME");
        request.setDistanceType("TRAVEL");
        request.setDepots(Arrays.asList(generateDepot()));
        request.setVehicleModels(Arrays.asList(generateVehicleModel()));
        request.setServiceJobs(generateServiceJobs());
        return request;
    }

    private CreateOptimizationRequest generateCreateOptimizationRequest() {
        CreateOptimizationRequest request = new CreateOptimizationRequest();
        request.setMatrixId("a9aef9e844c3498da77e83db1d553bf4");
        request.setCommitId("fe5785c384d8482daa8ccdf7d3b306d7");
        request.setLbsType("LEAST_TIME");
        request.setDistanceType("TRAVEL");
        request.setDepotTimeWindow(new TimeWindow(0, 1440));
        request.setDepotId("02b6a7d5f205415abe0bcd1074662ddd");
        request.setVehicleTypeId("GB01");
        request.setVehicleModelId("model_test_1");
        request.setServiceJobs(generateOptimizationServiceJobs());
        return request;
    }

    private CreateOrderedRequest generateCreateOrderedRequest() {
        CreateOrderedRequest request = new CreateOrderedRequest();
        request.setMatrixId("a9aef9e844c3498da77e83db1d553bf4");
        request.setCommitId("fe5785c384d8482daa8ccdf7d3b306d7");
        request.setLbsType("LEAST_TIME");
        request.setDepotDepartureTime(400);
        request.setDepotId("02b6a7d5f205415abe0bcd1074662ddd");
        request.setVehicleTypeId("GB01");
        request.setVehicleModelId("model_test_1");
        request.setServiceJobs(generateOrderedServiceJobs());
        return request;
    }

    private CreateMultiDepotProblemRequest generateCreateMultiDepotProblemRequest() {
        CreateMultiDepotProblemRequest request = new CreateMultiDepotProblemRequest();
        request.setMatrixId("a9aef9e844c3498da77e83db1d553bf4");
        request.setCommitId("fe5785c384d8482daa8ccdf7d3b306d7");
        request.setLbsType("LEAST_TIME");
        request.setDistanceType("TRAVEL");
        request.setScenesType("SMALL_SCALE_GENERAL");
        request.setDepots(Arrays.asList(generateMultiDepotExternalDepot()));
        request.setServiceJobs(generateMultiDepotExternalServiceJob());
        request.setVehicleGroups(Arrays.asList(generateMultiDepotExternalVehicleGroup()));
        request.setVehicleModels(Arrays.asList(generateExternalVehicleModel()));

        return request;
    }

    private CreateMultiSingleDepotProblemRequest generateCreateMultiSingleDepotProblemRequest() {
        CreateMultiSingleDepotProblemRequest request = new CreateMultiSingleDepotProblemRequest();
        request.setScenesType("SMALL_SCALE_GENERAL");
        request.setMatrixId("a9aef9e844c3498da77e83db1d553bf4");
        request.setLbsType("LEAST_TIME");
        request.setCommitId("fe5785c384d8482daa8ccdf7d3b306d7");
        request.setDistanceType("TRAVEL");
        request.setDepots(Arrays.asList(generateMultiSingleDepotExternalDepot()));
        request.setVehicleModels(Arrays.asList(generateExternalVehicleModel()));
        request.setServiceJobs(generateMultiSingleDepotExternalServiceJob());
        return request;
    }

    private CreateMultiSingleDepotProblemRequest.ExternalDepot generateMultiSingleDepotExternalDepot() {
        CreateMultiSingleDepotProblemRequest.ExternalDepot depot = new CreateMultiSingleDepotProblemRequest.ExternalDepot();
        depot.setDepotId("02b6a7d5f205415abe0bcd1074662ddd");
        depot.setDepotTimeWindow(new ExternalTimeWindow(0, 1440));
        depot.setVehicleGroups(Arrays.asList(generateExternalVehicleGroup()));
        return depot;
    }

    private ExternalVehicleGroup generateExternalVehicleGroup() {
        ExternalVehicleGroup vehicleGroup = new ExternalVehicleGroup();
        vehicleGroup.setVehicleTypeId("GB01");
        vehicleGroup.setVehicleModelId("VehicleModel001");
        vehicleGroup.setVehicleCount(3);
        return vehicleGroup;
    }

    private CreateMultiDepotProblemRequest.MultiDepotExternalDepot generateMultiDepotExternalDepot() {
        CreateMultiDepotProblemRequest.MultiDepotExternalDepot depot = new CreateMultiDepotProblemRequest.MultiDepotExternalDepot();
        depot.setDepotId("02b6a7d5f205415abe0bcd1074662ddd");
        depot.setDepotTimeWindow(new ExternalTimeWindow(0, 1440));
        return depot;
    }

    private List<CreateMultiDepotProblemRequest.MultiDepotExternalServiceJob> generateMultiDepotExternalServiceJob() {
        List<CreateMultiDepotProblemRequest.MultiDepotExternalServiceJob> serviceJobs = new ArrayList();
        List<String> serviceJobIdList = getServiceJobIdList();
        for (int i = 0; i < serviceJobIdList.size(); i++) {
            CreateMultiDepotProblemRequest.MultiDepotExternalServiceJob serviceJob = new CreateMultiDepotProblemRequest.MultiDepotExternalServiceJob();
            serviceJob.setLocationId(serviceJobIdList.get(i));
            serviceJob.setParentId(serviceJobIdList.get(i));
            serviceJob.setDemand(new ExternalCapacity(7.5, 7.5, null));
            serviceJobs.add(serviceJob);
        }
        return serviceJobs;
    }

    private List<CreateMultiSingleDepotProblemRequest.ExternalServiceJob> generateMultiSingleDepotExternalServiceJob() {
        List<CreateMultiSingleDepotProblemRequest.ExternalServiceJob> serviceJobs = new ArrayList();
        List<String> serviceJobIdList = getServiceJobIdList();
        for (int i = 0; i < serviceJobIdList.size(); i++) {
            CreateMultiSingleDepotProblemRequest.ExternalServiceJob serviceJob
                    = new CreateMultiSingleDepotProblemRequest.ExternalServiceJob();
            serviceJob.setServiceJobId(serviceJobIdList.get(i));
            serviceJob.setDepotId("02b6a7d5f205415abe0bcd1074662ddd");
            serviceJob.setDemand(new ExternalCapacity(7.5, 7.5, null));
            serviceJobs.add(serviceJob);
        }
        return serviceJobs;
    }

    private CreateMultiDepotProblemRequest.MultiDepotExternalVehicleGroup generateMultiDepotExternalVehicleGroup() {
        CreateMultiDepotProblemRequest.MultiDepotExternalVehicleGroup vehicleGroup = new CreateMultiDepotProblemRequest.MultiDepotExternalVehicleGroup();
        vehicleGroup.setVehicleTypeId("GB01");
        vehicleGroup.setVehicleModelId("VehicleModel001");
        vehicleGroup.setVehicleCount(3);
        vehicleGroup.setReturnToDepot(true);
        return vehicleGroup;
    }

    private ExternalVehicleModel generateExternalVehicleModel() {
        ExternalVehicleModel vehicleModel = new ExternalVehicleModel();
        vehicleModel.setVehicleModelId("VehicleModel001");
        vehicleModel.setAverageVelocity(100.);
        vehicleModel.setFixedCost(10.);
        vehicleModel.setCapacity(new ExternalCapacity(35., 35., null));
        vehicleModel.setPerDistanceUnitPrice(10.);
        return vehicleModel;
    }

    private CreateProblemRequest.Depot generateDepot() {
        CreateProblemRequest.Depot depot = new CreateProblemRequest.Depot();
        depot.setDepotId("02b6a7d5f205415abe0bcd1074662ddd");
        depot.setDepotTimeWindow(new TimeWindow(0, 1440));
        depot.setVehicleGroups(Arrays.asList(generateVehicleGroup()));
        return depot;
    }

    private CreateProblemRequest.VehicleGroup generateVehicleGroup() {
        CreateProblemRequest.VehicleGroup vehicleGroup = new CreateProblemRequest.VehicleGroup();
        vehicleGroup.setVehicleTypeId("GB01");
        vehicleGroup.setVehicleModelId("VehicleModel001");
        vehicleGroup.setVehicleCount(3);
        vehicleGroup.setVehicleSkills(Arrays.asList("vehicleSkills"));
        vehicleGroup.setReturnToDepot(true);
        return vehicleGroup;
    }

    private CreateProblemRequest.VehicleModel generateVehicleModel() {
        CreateProblemRequest.VehicleModel vehicleModel = new CreateProblemRequest.VehicleModel();
        vehicleModel.setVehicleModelId("VehicleModel001");
        vehicleModel.setAverageVelocity(100.);
        vehicleModel.setFixedCost(10.);
        vehicleModel.setCapacity(new Capacity(35., 35., null));
        vehicleModel.setPerDistanceUnitPrice(10.);
        return vehicleModel;
    }

    private List<CreateProblemRequest.ServiceJob> generateServiceJobs() {
        List<CreateProblemRequest.ServiceJob> serviceJobs = new ArrayList();
        List<String> serviceJobIdList = getServiceJobIdList();
        for (int i = 0; i < serviceJobIdList.size(); i++) {
            CreateProblemRequest.ServiceJob serviceJob = new CreateProblemRequest.ServiceJob();
            serviceJob.setServiceJobId(serviceJobIdList.get(i));
            serviceJob.setDemand(new Capacity(7.5, 7.5, null));
            serviceJob.setSkills(Arrays.asList("skills"));
            serviceJobs.add(serviceJob);
        }
        return serviceJobs;
    }

    private List<CreateOptimizationRequest.ServiceJob> generateOptimizationServiceJobs() {
        List<CreateOptimizationRequest.ServiceJob> serviceJobs = new ArrayList();
        List<String> serviceJobIdList = getServiceJobIdList();
        for (int i = 0; i < serviceJobIdList.size(); i++) {
            CreateOptimizationRequest.ServiceJob serviceJob = new CreateOptimizationRequest.ServiceJob();
            serviceJob.setServiceJobId(serviceJobIdList.get(i));
            serviceJob.setServiceStayDuration(20.);
            serviceJob.setServiceTimeWindows(Arrays.asList(new TimeWindow(800, 1200)));
            serviceJobs.add(serviceJob);
        }
        return serviceJobs;
    }

    private List<CreateOrderedRequest.ServiceJob> generateOrderedServiceJobs() {
        List<CreateOrderedRequest.ServiceJob> serviceJobs = new ArrayList();
        List<String> serviceJobIdList = getServiceJobIdList();
        for (int i = 0; i < serviceJobIdList.size(); i++) {
            CreateOrderedRequest.ServiceJob serviceJob = new CreateOrderedRequest.ServiceJob();
            serviceJob.setServiceJobId(serviceJobIdList.get(i));
            serviceJob.setServiceStayDuration(20.);
            serviceJobs.add(serviceJob);
        }
        return serviceJobs;
    }

    private List<String> getServiceJobIdList() {
        return Arrays.asList("f88c786a6d26493e9d548a5668cc6d21",
                "7a2d25b0bb2d41e5835f10acccc761e0",
                "b74b56bcffaa4d36ab553afc9a317ef3",
                "ee4238cc56974cf08e899b4e36e48253");
    }

    private PartitionRequest generatePartitionRequest() {
        PartitionRequest request = new PartitionRequest();
        Location location = new Location("a9aef9e844c3498da77e83db1d553bf4",
                0.00, 0.00);
        request.setDepot(location);
        request.setServiceList(Arrays.asList(location));
        request.setPartitionNum(0);
        request.setStartDirection(0);
        request.setPartRule(null);
        return request;
    }
}

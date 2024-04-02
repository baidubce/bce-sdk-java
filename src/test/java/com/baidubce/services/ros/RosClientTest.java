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

import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.ros.model.BaseResponse;
import com.baidubce.services.ros.model.Capacity;
import com.baidubce.services.ros.model.CurrentTaskResponse;
import com.baidubce.services.ros.model.ExternalCapacity;
import com.baidubce.services.ros.model.ExternalDepot;
import com.baidubce.services.ros.model.ExternalServiceJob;
import com.baidubce.services.ros.model.ExternalTimeWindow;
import com.baidubce.services.ros.model.ExternalVehicleGroup;
import com.baidubce.services.ros.model.ExternalVehicleModel;
import com.baidubce.services.ros.model.FastOrderDepot;
import com.baidubce.services.ros.model.FastOrderProblem;
import com.baidubce.services.ros.model.FastOrderServiceJob;
import com.baidubce.services.ros.model.FastOrderSolutionResponse;
import com.baidubce.services.ros.model.FastOrderSolutionResponseV2;
import com.baidubce.services.ros.model.FastOrderVehicleGroup;
import com.baidubce.services.ros.model.GetSchedulerResultResponse;
import com.baidubce.services.ros.model.GetSingleVehicleResultResponse;
import com.baidubce.services.ros.model.HighWayLocationResponse;
import com.baidubce.services.ros.model.LocationPoint;
import com.baidubce.services.ros.model.MatrixResponse;
import com.baidubce.services.ros.model.OptimizationProblemRequest;
import com.baidubce.services.ros.model.OrderedProblemRequest;
import com.baidubce.services.ros.model.ProblemRequest;
import com.baidubce.services.ros.model.RosGeneralResponse;
import com.baidubce.services.ros.model.TimeWindow;
import com.baidubce.services.ros.model.VehicleModel;
import com.baidubce.services.ros.model.matrix.Location;
import com.baidubce.services.ros.model.matrix.MatrixCreateRequest;
import com.baidubce.services.ros.model.matrix.MatrixDeleteRequest;
import com.baidubce.services.ros.model.matrix.MatrixPointRequest;
import com.baidubce.services.ros.model.matrix.MatrixUpdateRequest;
import com.baidubce.services.ros.model.matrix.WeightUpdateRequest;
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
public class RosClientTest {

    private static final String TEST_AK = "your-ak";
    private static final String TEST_SK = "your-sk";
    private static final String TEST_ENDPOINT = "http://endpoint";
    private RosClient rosClient;

    @Before
    public void setUp() {
        BceClientConfiguration configuration = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(TEST_AK, TEST_SK))
                .withEndpoint(TEST_ENDPOINT);
        rosClient = new RosClient(configuration);
    }

    @Test
    public void createProblem() {
        ProblemRequest request = generateProblemRequest();
        log.info("request is : {}", request);
        BaseResponse response = rosClient.createProblem(request);
        System.out.println(JsonUtils.toJsonString(response));
        log.info("response is : {}", response);
    }

    @Test
    public void getSchedulerTaskResult() {
        String problemId = "f199856cece84d6e879a0c0bd039de16";
        GetSchedulerResultResponse response = rosClient.getSchedulerTaskResult(problemId);
        System.out.println(JsonUtils.toJsonString(response));
        log.info("response is : {}", response);
    }

    @Test
    public void createOptimizationTask() {
        OptimizationProblemRequest request = generateOptimizationProblemRequest();
        log.info("request is : {}", request);
        BaseResponse response = rosClient.createOptimizationTask(request);
        log.info("response is : {}", response);
    }

    @Test
    public void getOptimizationTaskResult() {
        String problemId = "4a5ca85c2c844e4ea394bef159b77e36";
        GetSingleVehicleResultResponse response = rosClient.getOptimizationTaskResult(problemId);
        log.info("response is : {}", response);
    }

    @Test
    public void createOrderedTask() {
        OrderedProblemRequest request = generateOrderedProblemRequest();
        log.info("request is : {}", request);
        BaseResponse response = rosClient.createOrderedTask(request);
        log.info("response is : {}", response);
    }

    @Test
    public void getOrderedTaskResult() {
        String problemId = "689bcb1cdfe34a8098a95df98000bf47";
        GetSingleVehicleResultResponse response = rosClient.getOrderedTaskResult(problemId);
        log.info("response is : {}", response);
    }

    @Test
    public void testCreateFastOrderTask() {
        FastOrderProblem problem = new FastOrderProblem();
        problem.setOrderType("FAST_ORDER");
        problem.setDepots(Arrays.asList(generateDepot()));
        problem.setVehicleModels(generateVehicleModels());
        problem.setServiceJobs(generateServiceJobs());
        log.info("request is : {}", problem);
        RosGeneralResponse response = rosClient.createFastOrderTask(problem);
        log.info("response is :{}", response);
    }

    @Test
    public void testGetFastOrderSolutionResponse() {
        FastOrderSolutionResponse response = rosClient.getFastOrderSolutionResponse("7b12146365414b0f840a1c0c6e487d68");
        log.info("response is :{}", response);
    }

    @Test
    public void testGetFastOrderSolutionResponseV2() {
        FastOrderSolutionResponseV2 response =
                rosClient.getFastOrderSolutionResponseV2("7b12146365414b0f840a1c0c6e487d68");
        log.info("response is :{}", response);
    }

    @Test
    public void createMatrix() {

        MatrixCreateRequest request = JsonUtils.fromJsonString(getMatrixCreateRequest(), MatrixCreateRequest.class);
        MatrixResponse response =
                rosClient.createMatrix(request);
        log.info("response : {}", response);
    }

    @Test
    public void getMatrixStatus() {
        MatrixResponse response = rosClient.getMatrixStatus("a9aef9e844c3498da77e83db1d553bf4",
                "dacf588a405f48e396874b596135df0a");
        System.out.println(JsonUtils.toJsonString(response));
        log.info("response: {}", response);
    }

    @Test
    public void updatePoint() {
        MatrixPointRequest pointRequest = new MatrixPointRequest();
        pointRequest.setFrom(new Location("cc664383163948929cd4d7d8aaf7ee54", 31.863202, 117.103855));
        pointRequest.setTo(new Location("f88c786a6d26493e9d548a5668cc6d21", 31.943706, 117.518053));
        MatrixResponse response = rosClient.updatePoint(pointRequest, "a9aef9e844c3498da77e83db1d553bf4");
        log.info("response is : {}", JsonUtils.toJsonString(response));
    }

    @Test
    public void updateAddLocation() {
        MatrixUpdateRequest request = new MatrixUpdateRequest();
        request.setType(MatrixUpdateRequest.Type.ADD);
        List<Location> locations = new ArrayList<Location>();
        request.setLocations(locations);
        locations.add(new Location("testkey", 31.863202, 117.518099));
        MatrixResponse response = rosClient.updateMatrix(request, "a9aef9e844c3498da77e83db1d553bf4");
        log.info("response is : {}", JsonUtils.toJsonString(response));
    }

    @Test
    public void updateLocation() {
        MatrixUpdateRequest request = new MatrixUpdateRequest();
        request.setType(MatrixUpdateRequest.Type.UPDATE);
        List<Location> locations = new ArrayList<Location>();
        request.setLocations(locations);
        locations.add(new Location("testkey", 31.8632011112, 117.518011199));
        MatrixResponse response = rosClient.updateMatrix(request, "a9aef9e844c3498da77e83db1d553bf4");
        log.info("response is : {}", JsonUtils.toJsonString(response));
    }

    @Test
    public void deleteLocation() {
        MatrixUpdateRequest request = new MatrixUpdateRequest();
        request.setType(MatrixUpdateRequest.Type.DELETE);
        List<Location> locations = new ArrayList<Location>();
        request.setLocations(locations);
        locations.add(new Location("testkey", 31.8632011112, 117.518011199));
        MatrixResponse response = rosClient.updateMatrix(request, "a9aef9e844c3498da77e83db1d553bf4");
        log.info("response is : {}", JsonUtils.toJsonString(response));
    }

    @Test
    public void delete() {
        MatrixDeleteRequest request = new MatrixDeleteRequest();
        List<String> ids = new ArrayList<String>();
        ids.add("a9aef9e844c3498da77e83db1d553bf4");
        rosClient.deleteMatrix(request);
    }

    @Test
    public void current() {
        CurrentTaskResponse response = rosClient.getTaskExecuteStatus();
        log.info("response is : {}", response);
    }

    @Test
    public void updateTaskWeight() {
        rosClient.updateTaskWeight(new WeightUpdateRequest("a", 9));
    }

    @Test
    public void getMatrixHighWayLocation() {
        HighWayLocationResponse response = rosClient.getMatrixHighWayLocation("516573db1c934f3f8ba68a5437e24ba2");
        log.info("response is : {}", JsonUtils.toJsonString(response));
    }

    public String getMatrixCreateRequest() {
        String matrixCreateStr = "{\n"
                + "    \"name\": \"test-matrix\",\n"
                + "    \"locationType\": \"bd09ll\",\n"
                + "    \"lbsType\": [\n"
                + "        \"LEAST_TIME\"\n"
                + "    ],\n"
                + "    \"vehicleType\": [\n"
                + "        \"GB01\"\n"
                + "    ],\n"
                + "    \"locations\": [\n"
                + "        {\n"
                + "            \"key\": \"cc664383163948929cd4d7d8aaf7ee54\",\n"
                + "            \"latitude\": 31.863202,\n"
                + "            \"longitude\": 117.103855\n"
                + "        },\n"
                + "        {\n"
                + "            \"key\": \"f88c786a6d26493e9d548a5668cc6d21\",\n"
                + "            \"latitude\": 31.943706,\n"
                + "            \"longitude\": 117.518053\n"
                + "        },\n"
                + "        {\n"
                + "            \"key\": \"ee4238cc56974cf08e899b4e36e48253\",\n"
                + "            \"latitude\": 31.565629161039954,\n"
                + "            \"longitude\": 117.13141784280606\n"
                + "        },\n"
                + "        {\n"
                + "            \"key\": \"0e7762339470443381e6b5992edc7ed6\",\n"
                + "            \"latitude\": 31.565927781053286,\n"
                + "            \"longitude\": 116.93270098276002\n"
                + "        },\n"
                + "        {\n"
                + "            \"key\": \"b74b56bcffaa4d36ab553afc9a317ef3\",\n"
                + "            \"latitude\": 31.566036302638214,\n"
                + "            \"longitude\": 117.01404248591066\n"
                + "        },\n"
                + "        {\n"
                + "            \"key\": \"df7666092396438397b4065bbd3ad94b\",\n"
                + "            \"latitude\": 31.566608788274788,\n"
                + "            \"longitude\": 117.18622563489265\n"
                + "        },\n"
                + "        {\n"
                + "            \"key\": \"7a2d25b0bb2d41e5835f10acccc761e0\",\n"
                + "            \"latitude\": 31.56698177988206,\n"
                + "            \"longitude\": 116.93374537916095\n"
                + "        },\n"
                + "        {\n"
                + "            \"key\": \"02b6a7d5f205415abe0bcd1074662ddd\",\n"
                + "            \"latitude\": 31.566985720085974,\n"
                + "            \"longitude\": 116.81087171122789\n"
                + "        }\n"
                + "    ]\n"
                + "}";
        return matrixCreateStr;
    }

    private FastOrderDepot generateDepot() {
        return new FastOrderDepot("K00061",
                new LocationPoint(new Double("31.223188"), new Double("121.52481")),
                new TimeWindow(1, 1440),
                Arrays.asList(
                        new FastOrderVehicleGroup("GB01",
                                "model_test_1",
                                0, null,
                                null,
                                1,
                                "vid",
                                null,
                                null)));

    }

    private List<VehicleModel> generateVehicleModels() {
        return Arrays.asList(
                new VehicleModel("model_test_1",
                        new Capacity(1000.0, 1000.0, 1000.0),
                        12.22, 20., null, null, null));
    }

    private List<FastOrderServiceJob> generateServiceJobs() {
        List<FastOrderServiceJob> serviceJobs = new ArrayList<FastOrderServiceJob>();
        List<Location> locations = getLocations();
        for (int i = 0; i < 15; i++) {
            FastOrderServiceJob serviceJob = new FastOrderServiceJob();
            Location location = locations.get(i);
            serviceJob.setServiceJobId(location.getKey());
            serviceJob.setLocation(new LocationPoint(location.getLatitude(), location.getLongitude()));
            serviceJob.setDemand(new Capacity(RandomUtils.nextDouble(1, 5),
                    RandomUtils.nextDouble(1, 5),
                    RandomUtils.nextDouble(1, 5)));
            serviceJobs.add(serviceJob);
        }
        return serviceJobs;
    }

    private ProblemRequest generateProblemRequest() {
        ProblemRequest request = new ProblemRequest();
        request.setScenesType("SMALL_SCALE_GENERAL");
        request.setMatrixId("a9aef9e844c3498da77e83db1d553bf4");
        request.setCommitId("fe5785c384d8482daa8ccdf7d3b306d7");
        request.setLbsType("LEAST_TIME");
        request.setDistanceType("TRAVEL");
        request.setDepots(Arrays.asList(generateExternalDepot()));
        request.setVehicleModels(Arrays.asList(generateExternalVehicleModel()));
        request.setServiceJobs(generateExternalServiceJobs());
        return request;
    }

    private OptimizationProblemRequest generateOptimizationProblemRequest() {
        OptimizationProblemRequest request = new OptimizationProblemRequest();
        request.setMatrixId("a9aef9e844c3498da77e83db1d553bf4");
        request.setCommitId("fe5785c384d8482daa8ccdf7d3b306d7");
        request.setLbsType("LEAST_TIME");
        request.setDistanceType("TRAVEL");
        request.setDepotTimeWindow(new ExternalTimeWindow(0, 1440));
        request.setDepotId("02b6a7d5f205415abe0bcd1074662ddd");
        request.setVehicleTypeId("GB01");
        request.setVehicleModelId("model_test_1");
        request.setServiceJobs(generateOptimizationServiceJobs());
        return request;
    }

    private OrderedProblemRequest generateOrderedProblemRequest() {
        OrderedProblemRequest request = new OrderedProblemRequest();
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

    private ExternalDepot generateExternalDepot() {
        ExternalDepot depot = new ExternalDepot();
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

    private ExternalVehicleModel generateExternalVehicleModel() {
        ExternalVehicleModel vehicleModel = new ExternalVehicleModel();
        vehicleModel.setVehicleModelId("VehicleModel001");
        vehicleModel.setAverageVelocity(100.);
        vehicleModel.setFixedCost(10.);
        vehicleModel.setCapacity(new ExternalCapacity(35., 35., null));
        vehicleModel.setPerDistanceUnitPrice(10.);
        return vehicleModel;
    }

    private List<ExternalServiceJob> generateExternalServiceJobs() {
        List<ExternalServiceJob> serviceJobs = new ArrayList();
        List<String> serviceJobIdList = getServiceJobIdList();
        for (int i = 0; i < serviceJobIdList.size(); i++) {
            ExternalServiceJob serviceJob = new ExternalServiceJob();
            serviceJob.setServiceJobId(serviceJobIdList.get(i));
            serviceJob.setDemand(new ExternalCapacity(7.5, 7.5, null));
            serviceJob.setSkills(Arrays.asList("skills"));
            serviceJobs.add(serviceJob);
        }
        return serviceJobs;
    }

    private List<ExternalServiceJob> generateOptimizationServiceJobs() {
        List<ExternalServiceJob> serviceJobs = new ArrayList();
        List<String> serviceJobIdList = getServiceJobIdList();
        for (int i = 0; i < serviceJobIdList.size(); i++) {
            ExternalServiceJob serviceJob = new ExternalServiceJob();
            serviceJob.setServiceJobId(serviceJobIdList.get(i));
            serviceJob.setServiceStayDuration(20d);
            serviceJob.setServiceTimeWindows(Arrays.asList(new ExternalTimeWindow(800, 1200)));
            serviceJobs.add(serviceJob);
        }
        return serviceJobs;
    }

    private List<ExternalServiceJob> generateOrderedServiceJobs() {
        List<ExternalServiceJob> serviceJobs = new ArrayList();
        List<String> serviceJobIdList = getServiceJobIdList();
        for (int i = 0; i < serviceJobIdList.size(); i++) {
            ExternalServiceJob serviceJob = new ExternalServiceJob();
            serviceJob.setServiceJobId(serviceJobIdList.get(i));
            serviceJob.setServiceStayDuration(20d);
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

    private List<Location> getLocations() {
        return Arrays.asList(new Location("K00069", new Double("31.342084"), new Double("121.573599")),
                new Location("K00182", new Double("31.220752"), new Double("121.52566")),
                new Location("K00062", new Double("31.365328"), new Double("121.539487")),
                new Location("K00183", new Double("31.22024"), new Double("121.52778")),
                new Location("K00180", new Double("31.225175"), new Double("121.558869")),
                new Location("K00060", new Double("31.347316"), new Double("121.56958")),
                new Location("K00181", new Double("31.23258"), new Double("121.54574")),
                new Location("K00065", new Double("31.340173"), new Double("121.58671")),
                new Location("K00186", new Double("31.22246"), new Double("121.52088")),
                new Location("K00066", new Double("31.342107"), new Double("121.57348")),
                new Location("K00187", new Double("31.223072"), new Double("121.51817")),
                new Location("K00063", new Double("31.370528"), new Double("121.50831")),
                new Location("K00184", new Double("31.220493"), new Double("121.52542")),
                new Location("K00064", new Double("31.36352"), new Double("121.53651")),
                new Location("K00185", new Double("31.21723"), new Double("121.51927")));
    }
}

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
package com.baidubce.services.lps;

import org.junit.Before;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.lps.model.DirectionRequest;
import com.baidubce.services.lps.model.DirectionResponse;
import com.baidubce.services.lps.model.PointMatrix;
import com.baidubce.services.lps.model.RouteMatrixRequest;
import com.baidubce.services.lps.model.RouteMatrixResponse;
import com.baidubce.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Tests of lps client.
 *
 * @author weizhijun
 * @date 2019/03/07
 */
@Slf4j
public class LpsClientTest {

    private static final String TEST_AK = "your-access-key";
    private static final String TEST_SK = "your-secret-key";
    private static final String TEST_ENDPOINT = "endpoint";

    private LpsClient lpsClient;

    @Before
    public void setUp() {
        BceClientConfiguration configuration = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(TEST_AK, TEST_SK));
        lpsClient = new LpsClient(configuration);
    }

    @Test
    public void testDirection() {
        DirectionRequest request = DirectionRequest.builder()
                .taskId("1")
                .vehicleId("1")
                .routeId("1")
                .origin("31.753584,117.205126")
                .retCoordType("bd09ll")
                .destination("31.561788,117.170631")
                .height(2.0)
                .width(2.0)
                .weight(2.0)
                .length(2.0)
                .axleWeight(2.0)
                .axleCount(2)
                .isTrailer(0)
                .plateProvince("豫")
                .plateNumber("A12345")
                .plateColor(1)
                .waypoints("40.052169,116.307473|40.048579,116.311426|40.047806,116.317894|40.044768,116"
                                   + ".317894|40.042062,116.317319|40.035157,116.319115|40.029577,116.320553|40"
                                   + ".028427,116.310453|40.021952,116.324218|40.019687,116.324721|40.030337,116"
                                   + ".343657|40.032298,116.343657|40.033569,116.343657|40.035074,116.343729|40"
                                   + ".036607,116.341986|40.040253,116.343819|40.039314,116.337315|40.042338,116"
                                   + ".342867|40.044921,116.340926|40.046232,116.336327")
                .build();
        DirectionResponse response = lpsClient.direction(request);
        log.info("direction response: {}", JsonUtils.toJsonString(response));
    }

    @Test
    public void testRouteMatrix() {
        RouteMatrixRequest request = RouteMatrixRequest.builder()
                .pointMatrix(new PointMatrix("31.753584,117.205126", "31.271865,117.154534|31.041455,116.197874"))
                .height(2.0)
                .width(2.0)
                .weight(2.0)
                .length(2.0)
                .axleWeight(2.0)
                .axleCount(2)
                .isTrailer(0)
                .plateProvince("豫")
                .plateNumber("A12345")
                .plateColor(1)
                .build();
        RouteMatrixResponse response = lpsClient.routeMatrix(request);
        log.info("route matrix response: {}", JsonUtils.toJsonString(response));
    }
}

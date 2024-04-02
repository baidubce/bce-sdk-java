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

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.lps.model.Coordinate;
import com.baidubce.services.lps.model.ListRouteResponse;
import com.baidubce.services.lps.model.UploadRouteRequest;
import com.baidubce.services.lps.model.UploadRouteResponse;
import com.baidubce.util.JsonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * Tests of lps route client.
 * Create by chenbo14
 */
@Slf4j
public class LpsRouteClientTest {

    private static final String TEST_AK = "your-access-key";
    private static final String TEST_SK = "your-secret-key";

    private LpsRouteClient lpsRouteClient;

    @Before
    public void setUp() {
        BceClientConfiguration configuration = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(TEST_AK, TEST_SK));
        lpsRouteClient = new LpsRouteClient(configuration);
    }

    @Test
    public void uploadRouteInfo() {
        UploadRouteRequest request = buildUploadRouteRequest();
        UploadRouteResponse response = lpsRouteClient.uploadRouteInfo(request);
        log.info("response:" + JsonUtils.toJsonString(response));
        log.info("routeId:" + response.getRouteId());
    }

    @Test
    public void listRouteInfo() {
        ListRouteResponse response = lpsRouteClient.listRouteInfo(1, 10);
        log.info("response:" + JsonUtils.toJsonString(response));
    }

    @Test
    public void deleteRouteInfo() {
        lpsRouteClient.deleteRouteInfo("d93d26eda18c4bd0974dc9761d159f0b");
    }

    private UploadRouteRequest buildUploadRouteRequest() {
        UploadRouteRequest request = new UploadRouteRequest();
        request.setDistance(100);
        request.setDuration(10);
        request.setDepartureCoordinate(new Coordinate(118.024426, 31.640925));
        request.setArrivalCoordinate(new Coordinate(118.026251, 31.640612));
        request.setRouteCoordinateList(Arrays.asList(new Coordinate(118.024426, 31.640925),
                new Coordinate(118.024598, 31.640726),
                new Coordinate(118.024598, 31.640726),
                new Coordinate(118.024598, 31.640203),
                new Coordinate(118.026251, 31.640612)));
        return request;
    }
}

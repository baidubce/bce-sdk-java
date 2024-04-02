/*
 * Copyright 2022 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.cfs.v2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidubce.common.BceRegion;
import com.baidubce.services.cfs.v2.model.CreateFSRequest;
import com.baidubce.services.cfs.v2.model.CreateFSResponse;
import com.baidubce.services.cfs.v2.model.CreateMountTargetRequest;
import com.baidubce.services.cfs.v2.model.CreateMountTargetResponse;
import com.baidubce.services.cfs.v2.model.DescribeFsRequest;
import com.baidubce.services.cfs.v2.model.DescribeFsResponse;
import com.baidubce.services.cfs.v2.model.DescribeMountTargetRequest;
import com.baidubce.services.cfs.v2.model.DescribeMountTargetResponse;
import com.baidubce.services.cfs.v2.model.GetFsQuotaResponse;
import com.baidubce.services.cfs.v2.model.DropFsRequest;
import com.baidubce.services.cfs.v2.model.UpdateFsRequest;

/**
 * unit case
 */
public class CfsTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CfsTest.class);
    private static final String AK = "YourAk";
    private static final String SK = "YourSk";
    private CfsClient client = new CfsClient(AK, SK, BceRegion.DEFAULT);

    @Before
    public void setUp() {
        // do something
    }

    @After
    public void tearDown() {
        // do something
    }

    @Test
    public void createFSTest() {
        // test api createFS
        CreateFSRequest request = new CreateFSRequest();
        request.setFsName("");
		try {
          CreateFSResponse response = client.createFS(request);
          // 返回结果格式为Json字符串
          System.out.println(response.toString());
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    @Test
    public void createMountTargetTest() {
        // test api createMountTarget
        CreateMountTargetRequest request = new CreateMountTargetRequest();
        request.setSubnetId("");
        request.setVpcId("");
        
        try {
          CreateMountTargetResponse response = client.createMountTarget("", request);
          // 返回结果格式为Json字符串
          System.out.println(response.toString());
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    @Test
    public void deleteMountTargetTest() {
        // test api deleteMountTarget
        try {
          client.deleteMountTarget("", "");
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    @Test
    public void describeFsTest() {
        // test api describeFs
        DescribeFsRequest request = new DescribeFsRequest();

        try {
          DescribeFsResponse response = client.describeFs(request, "", "", "", 0);
            // 返回结果格式为Json字符串
          System.out.println(response.toString());
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    @Test
    public void describeMountTargetTest() {
        // test api describeMountTarget
        DescribeMountTargetRequest request = new DescribeMountTargetRequest();
        
        try {
          DescribeMountTargetResponse response= client.describeMountTarget("", request, "", "", 1);
          // 返回结果格式为Json字符串
          System.out.println(response.toString());
        } catch (Exception e) {
          e.printStackTrace();
		}
    }

    @Test
    public void dropFsTest() {
        // test api dropFs
        DropFsRequest request = new DropFsRequest();
        try {
          client.dropFs("", request);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    @Test
    public void getFsQuotaTest() {
        // test api getFsQuota
        try {
          GetFsQuotaResponse response = client.getFsQuota();
          // 返回结果格式为Json字符串
          System.out.println(response.toString());
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    @Test
    public void updateFsTest() {
        // test api updateFs
        UpdateFsRequest request = new UpdateFsRequest();

        request.setFsName("");
        try {
          client.updateFs("", request);
        } catch (Exception e) {
          e.printStackTrace();
        }
    }
}

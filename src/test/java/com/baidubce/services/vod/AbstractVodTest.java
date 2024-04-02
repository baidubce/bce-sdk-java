/*
 * Copyright 2016-2019 Baidu, Inc.
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

package com.baidubce.services.vod;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.vod.model.CreateMediaResourceResponse;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class AbstractVodTest {

    static final String VOD_END_POINT = "VodEndPoint";
    static final String BOS_END_POINT = "BosEndPoint";

    static String AK = "YourAK";
    static String SK = "YourSK";

    protected VodClient vodClient;

    protected String mediaId;

    public void setUp() throws Exception {
        DefaultBceCredentials cred = new DefaultBceCredentials(AK, SK);

        BceClientConfiguration vodConfig =
                new BceClientConfiguration().withCredentials(cred).withEndpoint(VOD_END_POINT);

        BosClientConfiguration bosConfig =
                new BosClientConfiguration().withCredentials(cred).withEndpoint(BOS_END_POINT);

        vodClient = new VodClient(vodConfig, bosConfig);

    }

    protected void createSampleMedia() throws FileNotFoundException, InterruptedException {
        File file = new File("YourMediaFilePath");
        String title = "YourMediaTitle";
        String description = "YourMediaDescription";
        CreateMediaResourceResponse response = vodClient.createMediaResource(title, description, file, null, 0);
        mediaId = response.getMediaId();
        String status;
        do {
            Thread.sleep(2000);
            status = vodClient.getMediaResource(mediaId).getStatus();
        } while (!status.equals("PUBLISHED"));
    }
}

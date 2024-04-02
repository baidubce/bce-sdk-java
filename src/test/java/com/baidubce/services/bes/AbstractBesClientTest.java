/**
 * Copyright 2020 Baidu, Inc.
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
package com.baidubce.services.bes;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.conf.BesConf;

/**
 *  @Description：Build the BES Client
 */
public class AbstractBesClientTest {

    protected BesClient besClient;

    public void setUp() {
        BceClientConfiguration bceClientConfiguration = new BceClientConfiguration();
        bceClientConfiguration.setCredentials(new DefaultBceCredentials(
                BesConf.BES_AK, BesConf.BES_SK
        ));
        bceClientConfiguration.setEndpoint(BesConf.BES_ENDPOINT);
        this.besClient = new BesClient(bceClientConfiguration, BesConf.REGION);
    }
}

/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
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
package com.baidubce.services.iotdmp.model.platform;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RuleChainExternalDestinationArgsInfo {


    private RuleChainExternalDestinationAuthType authType;
    private String address;
    private String truststoreId;
    private String truststorePassword;
    private String keystoreId;
    private String keystorePassword;
    private String topic;
    private String name;
    private String ingestAddress;
    private String accessKey;
    private String secretKey;
    private String database;
    private String host;
    private String port;
    private String accountName;
    private String accountPassword;
    private String dbName;
    private String region;
    private String bucket;

    public RuleChainExternalDestinationArgsInfo(RuleChainExternalDestinationAuthType authType) {
        this.authType = authType;
    }
}

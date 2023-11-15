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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalTsdbArgs extends RuleChainExternalDestinationArgs {
    private String name;
    private String ingestAddress;
    private String accessKey;
    private String secretKey;
    private String database;

    public ExternalTsdbArgs() {
        super(RuleChainExternalDestinationType.EXTERNAL_TSDB);
    }

    public ExternalTsdbArgs(String name, String ingestAddress, String accessKey, String secretKey, String database) {
        super(RuleChainExternalDestinationType.EXTERNAL_TSDB);
        this.name = name;
        this.ingestAddress = ingestAddress;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.database = database;
    }
}

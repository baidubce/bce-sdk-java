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
public class ExternalMinioArgs extends RuleChainExternalDestinationArgs {
    private String name;
    private String address;
    private String accessKey;
    private String secretKey;
    private String region;
    private String bucket;

    public ExternalMinioArgs() {
        super(RuleChainExternalDestinationType.EXTERNAL_MINIO);
    }

    public ExternalMinioArgs(String name, String address, String accessKey,
                             String secretKey, String region, String bucket) {
        super(RuleChainExternalDestinationType.EXTERNAL_MINIO);
        this.name = name;
        this.address = address;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.region = region;
        this.bucket = bucket;
    }
}

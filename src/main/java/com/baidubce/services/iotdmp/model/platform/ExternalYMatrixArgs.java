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
public class ExternalYMatrixArgs extends RuleChainExternalDestinationArgs {
    private String name;
    private String host;
    private String port;
    private String accountName;
    private String accountPassword;
    private String dbName;

    public ExternalYMatrixArgs() {
        super(RuleChainExternalDestinationType.EXTERNAL_YMATRIX);
    }

    public ExternalYMatrixArgs(String name, String host, String port, String accountName,
                               String accountPassword, String dbName) {
        super(RuleChainExternalDestinationType.EXTERNAL_YMATRIX);
        this.name = name;
        this.host = host;
        this.port = port;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.dbName = dbName;
    }
}
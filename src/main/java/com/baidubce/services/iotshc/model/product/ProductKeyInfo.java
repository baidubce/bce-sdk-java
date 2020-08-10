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
package com.baidubce.services.iotshc.model.product;

import com.baidubce.model.AbstractBceResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * product key information
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductKeyInfo extends AbstractBceResponse {

    private String accountUuid;
    private String fc;
    private String fcName;
    private String pk;
    private String devSku;
    private String devType;
    private String description;
    private int pid;
    private String key;
    private String unitServiceId;
    private String unitUsid;
    private String kafka;
    private String asrKafka;
    private boolean enableDelete;
    private String unitContext;
    private String createTime;
    private String updateTime;
}

/*
 * Copyright (c) 2024 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bec.model.vo;

import com.baidubce.BceConstants;
import com.baidubce.services.tag.model.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-08 12:27
 * @Version v1.0
 *
 * The abstract of the elastic public ip.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EipVo {

    /**
     * The id of the elastic public ip.
     */
    private String eipId;

    /**
     * The name of the elastic public ip.
     */
    private String name;

    /**
     * The shared bandwidth group id
     */
    private String shareGroupId;

    /**
     * The elastic public ip instance type, the value must be normal/shared.
     */
    private String eipInstanceType;

    /**
     * The elastic public ip
     */
    private String eip;

    /**
     * The id of instance which the elastic public ip is bound to.
     */
    private String instanceId;

    /**
     * The type of instance which the elastic public ip is bound to.
     */
    private String instanceType;

    /**
     * The binding status.
     */
    private String status;

    /**
     * The bandwidth.
     */
    private Integer bandwidthInMbps;

    /**
     * The route type.
     */
    private String routeType;

    /**
     * Creation time.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "GMT-8")
    private Date createTime;

    /**
     * The expired time.
     */
    private String expireTime;

    /**
     * The payment time, Prepaid,Postpaid.
     */
    private String paymentTiming;

    /**
     * The resource tags.
     */
    private List<Tag> tags;

    /**
     * The region id of the elastic public ip.
     */
    private String regionId;

    /**
     * The ip type version.
     */
    private Integer ipVersion;

    /**
     * The mode of network.
     */
    private String mode;

    /**
     * The purpose.
     */
    private String purpose;

    /**
     * The name of instance which the elastic public ip is bound to.
     */
    private String instanceName;

    /**
     * The ip of instance which the elastic public ip is bound to.
     */
    private String instanceIp;

    /**
     * The internet service provider.
     */
    private String isp;

    /**
     * The lb type.
     */
    private String lbType;

    /**
     * The status of instance which the elastic public ip is bound to.
     */
    private String instanceStatus;
}
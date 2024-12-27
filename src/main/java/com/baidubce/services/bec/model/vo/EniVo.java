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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author zhangyongchao01
 * @Since 2024-11-08 12:19
 * @Version v1.0
 *
 * The abstract of the elastic network interface.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EniVo {

    /**
     * The id of the elastic network interface.
     */
    private String eniId;

    /**
     * The name of the elastic network interface.
     */
    private String name;

    /**
     * The description of the elastic network interface.
     */
    private String description;

    /**
     * The vpc id of the elastic network interface.
     */
    private String vpcId;

    /**
     * The subnet id of the elastic network interface.
     */
    private String subnetId;

    /**
     * The security group ids of the elastic network interface.
     */
    private List<String> securityGroupIds;

    /**
     * The intranet ip set v1.
     */
    private List<PrivateIpSetVo> ipSet;

    /**
     * The status of the elastic network interface, like available/inuse/attaching/detaching.
     */
    private String status;

    /**
     * The mac address of the elastic network interface.
     */
    private String macAddress;

    /**
     * The instance id which the elastic network interface belong to.
     */
    private String instanceId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = BceConstants.DEFAULT_DATETIME_FORMAT, timezone = "UTC")
    /**
     * Creation time.
     */
    private Date createTime;

    /**
     * The region id of the elastic network interface.
     */
    private String regionId;

    /**
     * The subnet cidr which the elastic network interface belong to.
     */
    private String subnetCidr;

    /**
     * The instance name which the elastic network interface belong to.
     */
    private String instanceName;

    /**
     * The elastic public ips of the elastic network interface.
     */
    private List<EipVo> eips;
}

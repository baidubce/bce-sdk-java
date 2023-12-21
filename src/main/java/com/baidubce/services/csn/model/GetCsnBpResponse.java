/*
 * Copyright 2023 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.csn.model;

import com.baidubce.common.BaseBceResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetCsnBpResponse extends BaseBceResponse {
    /**
     * 带宽包的ID
     */
    private String csnBpId;

    /**
     * 带宽包的名称
     */
    private String name;

    /**
     * 带宽包的总带宽
     */
    private String bandwidth;

    /**
     * 带宽包的已分配带宽
     */
    private String usedBandwidth;

    /**
     * 绑定云智能网实例
     */
    private String csnId;

    /**
     * 带宽包互通类型，取值 [ center | center-edge | edge-edge ]，分别表示云间互通、云边互通、边边互通
     */
    private String interworkType;

    /**
     * 带宽包互通地域，取值 [ chinesemainland | asiapacific | crossregional ]，分别表示中国大陆、亚太区域、跨区域互通
     */
    private String interworkRegion;

    /**
     * 带宽包的状态
     */
    private String status;

    /**
     * 带宽包的付费方式，取值 [ PrePaid | PostPaid ]，分别表示预付费、后付费
     */
    private String paymentTiming;

    /**
     * 带宽包预付费的到期时间
     */
    private String expiredTime;

    /**
     * 带宽包的创建时间
     */
    private String createTime;
}

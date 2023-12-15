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

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCsnBpRequest extends BaseBceRequest {
    /**
     * 带宽包的名称，不能为空
     */
    private String name;

    /**
     * 带宽包的互通类型，取值 [ center | center-edge | edge-edge ]，分别表示云间互通、云边互通、边边互通，默认为云间互通
     */
    private String interworkType;

    /**
     * 带宽包的带宽值，最大值为10000
     */
    private Integer bandwidth;

    /**
     * 网络实例所属的区域。取值 [ China | Asia-Pacific ]，分别表示中国大陆、亚太区域
     */
    private String geographicA;

    /**
     * 另一个网络实例所属的区域。取值 [ China | Asia-Pacific ]，分别表示中国大陆、亚太区域
     */
    private String geographicB;

    /**
     * billing
     */
    private Billing billing;

    @Getter
    @Setter
    @ToString
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Billing {
        private String paymentTiming;
    
        private Reservation reservation;

        @Getter
        @Setter
        @ToString
        @Builder
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Reservation {
            private Integer reservationLength;
        
            private String reservationTimeUnit;
        }    
    }

}
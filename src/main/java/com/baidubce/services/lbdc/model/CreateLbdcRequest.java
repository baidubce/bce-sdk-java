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
package com.baidubce.services.lbdc.model;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateLbdcRequest extends BaseBceRequest {
    /**
     * 集群名称，长度1~65个字节，字母开头，_可包含字母数字-/.字符
     */
    private String name;

    /**
     * 集群类型，取值为4Layer或者7Layer
     */
    private String type;

    /**
     * 集群性能容量单位CCU（Cluster Capacity Unit）是用来衡量BLB集群处理流量时涉及的各个指标。
     */
    private Integer ccuCount;

    /**
     * LBDC的描述，最大支持200字符
     */
    private String desc;

    /**
     * billing
     */
    private BillingForCreate billing;

    /**
     * renewReservation
     */
    private Reservation renewReservation;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setCcuCount(Integer ccuCount) {
        this.ccuCount = ccuCount;
    }

    public Integer getCcuCount() {
        return this.ccuCount;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setBilling(BillingForCreate billing) {
        this.billing = billing;
    }

    public BillingForCreate getBilling() {
        return this.billing;
    }

    public void setRenewReservation(Reservation renewReservation) {
        this.renewReservation = renewReservation;
    }

    public Reservation getRenewReservation() {
        return this.renewReservation;
    }

    @Override
    public String toString() {
        return "CreateLbdcRequest{"
                + "name=" + name + "\n"
                + "type=" + type + "\n"
                + "ccuCount=" + ccuCount + "\n"
                + "desc=" + desc + "\n"
                + "billing=" + billing + "\n"
                + "renewReservation=" + renewReservation + "\n"
                + "}";
    }

    public static class BillingForCreate {
        private String paymentTiming;
    
        private Reservation reservation;
    
        public void setPaymentTiming(String paymentTiming) {
            this.paymentTiming = paymentTiming;
        }
    
        public String getPaymentTiming() {
            return this.paymentTiming;
        }
    
        public void setReservation(Reservation reservation) {
            this.reservation = reservation;
        }
    
        public Reservation getReservation() {
            return this.reservation;
        }
    
        @Override
        public String toString() {
            return "BillingForCreate{"
                    + "paymentTiming=" + paymentTiming + "\n"
                    + "reservation=" + reservation + "\n"
                    + "}";
        }
    }

    public static class Reservation {
        private Integer reservationLength;

        public void setReservationLength(Integer reservationLength) {
            this.reservationLength = reservationLength;
        }

        public Integer getReservationLength() {
            return this.reservationLength;
        }

        @Override
        public String toString() {
            return "Reservation{"
                    + "reservationLength=" + reservationLength + "\n"
                    + "}";
        }
    }

}
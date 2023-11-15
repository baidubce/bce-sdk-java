/*
 * Copyright 2022 Baidu, Inc. All Rights Reserved
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
package com.baidubce.services.dns.model;

import java.util.List;

import com.baidubce.common.BaseBceRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpgradeZoneRequest extends BaseBceRequest {
    /**
     * 域名的名称。
     */
    private List<String> names;

    /**
     * billing
     */
    private Billing billing;

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return this.names;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Billing getBilling() {
        return this.billing;
    }

    @Override
    public String toString() {
        return "UpgradeZoneRequest{"
                + "names=" + names + "\n"
                + "billing=" + billing + "\n"
                + "}";
    }

    public static class Billing {
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
            return "Billing{"
                    + "paymentTiming=" + paymentTiming + "\n"
                    + "reservation=" + reservation + "\n"
                    + "}";
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

}
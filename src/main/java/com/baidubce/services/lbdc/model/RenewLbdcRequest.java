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
public class RenewLbdcRequest extends BaseBceRequest {
    /**
     * billing
     */
    private BillingForRenew billing;

    public BillingForRenew getBilling() {
        return billing;
    }

    public void setBilling(BillingForRenew billing) {
        this.billing = billing;
    }

    @Override
    public String toString() {
        return "RenewLbdcRequest{" +
                "billing=" + billing +
                '}';
    }

    public static class BillingForRenew {
        private Reservation reservation;
    
        public void setReservation(Reservation reservation) {
            this.reservation = reservation;
        }
    
        public Reservation getReservation() {
            return this.reservation;
        }
    
        @Override
        public String toString() {
            return "BillingForRenew{"
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
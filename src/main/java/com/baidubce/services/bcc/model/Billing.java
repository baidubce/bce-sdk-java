/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model;

/**
 * The model for billing.
 */
public class Billing {

    /**
     * The pay time of the payment,the default value is "Postpaid".
     * See more detail in <a href = "https://bce.baidu.com/doc/BCC/API.html#Billing">BCE API doc</a>
     */
    private String paymentTiming;

    /**
     * The reservation model to specify the detail to buy.
     */
    private Reservation reservation;

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    /**
     * Configure paymentTiming for the model.
     *
     * @param paymentTiming The pay time of the payment, see more detail in
     *                      <a href = "https://bce.baidu.com/doc/BCC/API.html#Billing">BCE API doc</a>
     * @return Billing with paymentTiming.
     */
    public Billing withPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
        return this;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    /**
     * Configure reservation for the model.
     *
     * @param reservation The reservation model to specify the detail to buy.
     * @return Billing with reservation.
     */
    public Billing withReservation(Reservation reservation) {
        this.reservation = reservation;
        return this;
    }

    @Override
    public String toString() {
        return "Billing{"
                + "paymentTiming='" + paymentTiming + '\''
                + ", reservation=" + reservation
                + '}';
    }
}

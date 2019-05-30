/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.bcc.model.volume;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.bcc.model.Billing;
import com.baidubce.services.bcc.model.Reservation;

/**
 * the request for changing volume's billing method
 */
public class ModifyVolumeChargeRequest extends AbstractBceRequest {

    /**
     * specify the volume to change billing method
     */
    private String volumeId;

    /**
     * specify the billing method, can only be set to 'postpay' or 'prepay'
     */
    private String billingMethod;

    /**
     * this field is used to specify how many month to buy this volume. only used when billingMethod is 'prepay'
     */
    private int reservationLength;

    public ModifyVolumeChargeRequest withVolumeId(String volumeId) {
        this.volumeId = volumeId;
        return this;
    }

    public ModifyVolumeChargeRequest withBillingMethod(String billingMethod) {
        if (!"prepay".equals(billingMethod) && !"postpay".equals(billingMethod)) {
            throw new IllegalArgumentException("billingMethod can only be set to 'prepay' or 'postpay'");
        }
        this.billingMethod = billingMethod;
        return this;
    }

    public ModifyVolumeChargeRequest withReservationLength(int reservationLength) {
        if (reservationLength < 0) {
            throw new IllegalArgumentException("reservationLength can not be negative integer");
        }
        this.reservationLength = reservationLength;
        return this;
    }

    public String getBillingMethod() {
        return billingMethod;
    }

    public void setBillingMethod(String billingMethod) {
        if (!"prepay".equals(billingMethod) && !"postpay".equals(billingMethod)) {
            throw new IllegalArgumentException("billingMethod can only be set to 'prepay' or 'postpay'");
        }
        this.billingMethod = billingMethod;
    }

    public int getReservationLength() {
        return reservationLength;
    }

    public void setReservationLength(int reservationLength) {
        if (reservationLength < 0) {
            throw new IllegalArgumentException("reservationLength can not be negative integer");
        }
        this.reservationLength = reservationLength;
    }

    public String getVolumeId() {
        return volumeId;
    }

    public void setVolumeId(String volumeId) {
        this.volumeId = volumeId;
    }

    @Override
    public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * convert user request to internal request model
     * @return ModifyVolumeChargeModel
     */
    public ModifyVolumeChargeModel toModifyVolumeChargeModel() {
        ModifyVolumeChargeModel modifyVolumeChargeModel = new ModifyVolumeChargeModel();
        Reservation reservation = new Reservation();
        Billing billing = new Billing();
        if ("prepay".equals(billingMethod)) {
            reservation.withReservationLength(reservationLength);
            billing.withReservation(reservation);
        } else if ("postpay".equals(billingMethod)) {
            reservation.withReservationLength(0);
            billing.withReservation(reservation);
        } else {
            throw new IllegalArgumentException("billingMethod can only be set to 'prepay' or 'postpay'");
        }
        modifyVolumeChargeModel.setBilling(billing);
        return modifyVolumeChargeModel;
    }

    /**
     * internal request class
     */
    public static class ModifyVolumeChargeModel extends AbstractBceRequest {

        private Billing billing;

        public Billing getBilling() {
            return billing;
        }

        public void setBilling(Billing billing) {
            this.billing = billing;
        }

        @Override
        public AbstractBceRequest withRequestCredentials(BceCredentials credentials) {
            this.setRequestCredentials(credentials);
            return this;
        }
    }
}

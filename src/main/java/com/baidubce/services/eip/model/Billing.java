/*
 * Copyright (C) 2016 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.eip.model;

/**
 * The model for billing information.
 */
public class Billing {
    /**
     * The pay time of the payment,
     * see more detail at <a href = "https://bce.baidu.com/doc/EIP/API.html#Billing">BCE API doc</a>
     * default value 'Postpaid'
     */
    private String paymentTiming;

    /**
     *  The way of eip charging
     *  see more detail at <a href = "https://bce.baidu.com/doc/EIP/API.html#Billing">BCE API doc</a>
     */
    private String billingMethod;

    /**
     * The reservation model to specify the detail to buy.
     * be used when Prepaid
     */
    private Reservation reservation;

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public String getBillingMethod() {
        return billingMethod;
    }

    public void setBillingMethod(String billingMethod) {
        this.billingMethod = billingMethod;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Billing withPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
        return this;
    }

    public Billing withBillingMethod(String billingMethod) {
        this.billingMethod = billingMethod;
        return this;
    }

    public Billing withReservation(Reservation reservation) {
        this.reservation = reservation;
        return this;
    }

    /**
     * The reservation model to specify the detail to buy.
     */
    public static class Reservation {
        /**
         * purchase length
         */
        private int reservationLength;

        /**
         * time unit of purchasing，default 'Month'
         */
        private String reservationTimeUnit = "Month";

        public int getReservationLength() {
            return reservationLength;
        }

        public void setReservationLength(int reservationLength) {
            this.reservationLength = reservationLength;
        }

        public String getReservationTimeUnit() {
            return reservationTimeUnit;
        }

        public void setReservationTimeUnit(String reservationTimeUnit) {
            this.reservationTimeUnit = reservationTimeUnit;
        }

        public Reservation withReservationLength(int reservationLength) {
            this.reservationLength = reservationLength;
            return this;
        }

        public Reservation withReservationTimeUnit(String reservationTimeUnit) {
            this.reservationTimeUnit = reservationTimeUnit;
            return this;
        }
    }
}

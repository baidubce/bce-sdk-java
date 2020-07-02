/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.ipv6Gateway.model;

/**
 * The model for billing information.
 */
public class Billing {
    /**
     * The pay time of the payment,
     */
    private String paymentTiming;

    /**
     * The way of ipv6Gateway charging
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

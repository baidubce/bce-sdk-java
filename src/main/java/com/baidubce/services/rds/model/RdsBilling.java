package com.baidubce.services.rds.model;

/**
 * Billing of rds
 */
public class RdsBilling {

    private RdsPaymentTiming paymentTiming;
    private RdsReservation reservation;

    public RdsPaymentTiming getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(RdsPaymentTiming paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public RdsReservation getReservation() {
        return reservation;
    }

    public void setReservation(RdsReservation reservation) {
        this.reservation = reservation;
    }
}

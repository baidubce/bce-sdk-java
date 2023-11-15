package com.baidubce.services.scs.model;

/**
 * The billing info
 */
public class ScsBilling {

    private String paymentTiming;
    private ScsReservation reservation;

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public ScsReservation getReservation() {
        return reservation;
    }

    public void setReservation(ScsReservation reservation) {
        this.reservation = reservation;
    }
}

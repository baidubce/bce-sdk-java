package com.baidubce.services.as.model.asgroup;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xucongyang on 2018/06/25.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillingInfo {
    private String paymentTiming = "postpay";
    private Reservation reservation;

    public String getPaymentTiming() {
        return paymentTiming;
    }

    public void setPaymentTiming(String paymentTiming) {
        this.paymentTiming = paymentTiming;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public static class Reservation {
        private int reservationLengthInMonth;

        public int getReservationLengthInMonth() {
            return reservationLengthInMonth;
        }

        public void setReservationLengthInMonth(int reservationLengthInMonth) {
            this.reservationLengthInMonth = reservationLengthInMonth;
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BillingInfo{");
        sb.append("paymentTiming='").append(paymentTiming).append('\'');
        sb.append(", reservation=").append(reservation);
        sb.append('}');
        return sb.toString();
    }
}

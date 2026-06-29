package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Billing information for MongoDB order requests.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbBilling {
    private String paymentTiming;

    private Reservation reservation;

    private AutoRenew autoRenew;

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

    public AutoRenew getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(AutoRenew autoRenew) {
        this.autoRenew = autoRenew;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Reservation {
        private int reservationLength;

        private String reservationTimeUnit;

        public Reservation() {
        }

        public Reservation(int reservationLength) {
            this.reservationLength = reservationLength;
        }

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
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AutoRenew {
        private int autoRenewLength;

        private String autoRenewTimeUnit;

        public int getAutoRenewLength() {
            return autoRenewLength;
        }

        public void setAutoRenewLength(int autoRenewLength) {
            this.autoRenewLength = autoRenewLength;
        }

        public String getAutoRenewTimeUnit() {
            return autoRenewTimeUnit;
        }

        public void setAutoRenewTimeUnit(String autoRenewTimeUnit) {
            this.autoRenewTimeUnit = autoRenewTimeUnit;
        }
    }
}

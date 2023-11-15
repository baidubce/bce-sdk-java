package com.baidubce.services.scs.model;

/**
 * The reservation info
 */
public class ScsReservation {

    private Integer reservationLength;
    private String reservationTimeUnit;

    public Integer getReservationLength() {
        return reservationLength;
    }

    public void setReservationLength(Integer reservationLength) {
        this.reservationLength = reservationLength;
    }

    public String getReservationTimeUnit() {
        return reservationTimeUnit;
    }

    public void setReservationTimeUnit(String reservationTimeUnit) {
        this.reservationTimeUnit = reservationTimeUnit;
    }
}

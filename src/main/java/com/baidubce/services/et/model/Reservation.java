package com.baidubce.services.et.model;

import lombok.ToString;

@ToString
public class Reservation {
    private int reservationLength;
    private String reservationTimeUnit;

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

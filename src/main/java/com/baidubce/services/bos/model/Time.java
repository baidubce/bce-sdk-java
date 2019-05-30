package com.baidubce.services.bos.model;

/**
 * the conditonTime of Bucket Lifecycle.
 */
public class Time {

    /**
     * dateGreaterThan of the conditonTime of Bucket Lifecycle.
     */
    private String dateGreaterThan;

    /**
     * Gets dateGreaterThan of the conditonTime of Bucket Lifecycle.
     * @return dateGreaterThan of the conditonTime of Bucket Lifecycle.
     */
    public String getDateGreaterThan() {
        return dateGreaterThan;
    }

    /**
     * Sets dateGreaterThan of the conditonTime of Bucket Lifecycle.
     * @param dateGreaterThan The dateGreaterThan of the conditonTime of Bucket Lifecycle.
     */
    public void setDateGreaterThan(String dateGreaterThan) {
        this.dateGreaterThan = dateGreaterThan;
    }

    /**
     * sets the dateGreaterThan of the conditonTime of Bucket Lifecycle.
     * @param dateGreaterThan The dateGreaterThan of the conditonTime of Bucket Lifecycle.
     * @return this object
     */
    public Time withDateGreaterThan(String dateGreaterThan) {
        this.setDateGreaterThan(dateGreaterThan);
        return this;
    }

    /**
     * Constructs a void Constructor for Time of Bucket Lifecycle.
     */
    public Time() {
    }

    /**
     * Constructs a new Time object for Time of Bucket Lifecycle.
     * @param dateGreaterThan
     */
    public Time(String dateGreaterThan) {
        this.dateGreaterThan = dateGreaterThan;
    }

    @Override
    public String toString() {
        return "Time{"
                + "dateGreaterThan='" + dateGreaterThan + '\''
                + '}';
    }
}

package com.baidubce.services.tsdb.model;

/**
 * Represent the quota for database.
 */
public class Quota {

    private Integer ingestDataPointsMonthly;

    public Integer getIngestDataPointsMonthly() {
        return ingestDataPointsMonthly;
    }

    public void setIngestDataPointsMonthly(Integer ingestDataPointsMonthly) {
        this.ingestDataPointsMonthly = ingestDataPointsMonthly;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Quota)) {
            return false;
        }

        Quota quota = (Quota) o;

        return ingestDataPointsMonthly != null ? ingestDataPointsMonthly.equals(quota.ingestDataPointsMonthly) :
                quota.ingestDataPointsMonthly == null;
    }
}

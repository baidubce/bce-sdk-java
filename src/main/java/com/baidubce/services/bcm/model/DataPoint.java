package com.baidubce.services.bcm.model;

/**
 * the metric data
 */
public class DataPoint {

    public Double average;

    public Double sum;

    public Double maximum;

    public Double minimum;

    public Integer sampleCount;

    public Double value;

    /**
     * DateTime,请参考日期与时间，UTC日期表示
     */
    public String timestamp;

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getMaximum() {
        return maximum;
    }

    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }

    public Double getMinimum() {
        return minimum;
    }

    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }

    public Integer getSampleCount() {
        return sampleCount;
    }

    public void setSampleCount(Integer sampleCount) {
        this.sampleCount = sampleCount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValue(){
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
}

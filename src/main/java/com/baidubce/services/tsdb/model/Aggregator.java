package com.baidubce.services.tsdb.model;

/**
 * Represent the Aggregator for querying datapoints from Tsdb.
 */
public class Aggregator {
    
    /**
     * Required.
     * Represent the name of the aggregator.
     */
    private String name;
    
    /**
     * Required when name is Avg, Dev, Count, First, Gaps, Last, LeastSquares, Max, Min, Percentile, Sum,
     * Rate, or Sampler.
     * Represent the length of sampling time. eg: "1 minutes" or "2 hours".
     */
    private String sampling;
    
    /**
     * Required when name is Div.
     * Represent the divisor that should not be zero.
     */
    private Double divisor;
    
    /**
     * Required when name is Percentile.
     * Represent the precentile number that should in the range of (0, 1].
     */
    private Double percentile;
    
    /**
     * Required when name is Scale.
     * Represent the factor of the Scale.
     */
    private Double factor;

    public String getName() {
        return name;
    }

    /**
     * Set the name of this aggregator.
     * The available aggregator names are in {@code TsdbConstants}
     * 
     * @param name should be one of the Avg, Dev, Count, First, Last, LeastSquares, Max, Min, Percentile, Sum, Diff, Div and Scale.
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getSampling() {
        return sampling;
    }

    public void setSampling(String sampling) {
        this.sampling = sampling;
    }

    public Double getDivisor() {
        return divisor;
    }

    public void setDivisor(Double divisor) {
        this.divisor = divisor;
    }

    public Double getPercentile() {
        return percentile;
    }

    public void setPercentile(Double percentile) {
        this.percentile = percentile;
    }

    public Double getFactor() {
        return factor;
    }

    public void setFactor(Double factor) {
        this.factor = factor;
    }

    public Aggregator withName(String name) {
        this.name = name;
        return this;
    }
    
    public Aggregator withSampling(String sampling) {
        this.sampling = sampling;
        return this;
    }
    
    public Aggregator withDivisor(double divisor) {
        this.divisor = divisor;
        return this;
    }
    
    public Aggregator withPercentile(double percentile) {
        this.percentile = percentile;
        return this;
    }
    
    public Aggregator withFactor(double factor) {
        this.factor = factor;
        return this;
    }
}

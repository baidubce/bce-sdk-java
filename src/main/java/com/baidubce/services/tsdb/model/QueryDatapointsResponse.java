package com.baidubce.services.tsdb.model;

import java.util.List;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response for quering datapoints from Tsdb.
 */
public class QueryDatapointsResponse extends AbstractBceResponse {
    
    private List<Result> results;
    
    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}

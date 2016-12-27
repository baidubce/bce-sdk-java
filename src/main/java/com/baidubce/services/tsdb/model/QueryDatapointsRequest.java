package com.baidubce.services.tsdb.model;

import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.google.common.collect.Lists;

/**
 * Represent the request for quering datapoints from Tsdb.
 */
public class QueryDatapointsRequest extends AbstractBceRequest {
    
    private List<Query> queries;
    
    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }

    public QueryDatapointsRequest withQueries(List<Query> queries) {
        this.queries = queries;
        return this;
    }
    
    public QueryDatapointsRequest addQuery(Query query) {
        initialQureies();
        queries.add(query);
        return this;
    }
    
    private void initialQureies() {
        if (queries == null) {
            queries = Lists.newArrayList();
        }
    }
    
    @Override
    public QueryDatapointsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

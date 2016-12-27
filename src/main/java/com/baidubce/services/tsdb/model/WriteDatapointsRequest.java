package com.baidubce.services.tsdb.model;

import java.util.List;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;
import com.google.common.collect.Lists;

/**
 * Represent the request for writing datapoints to Tsdb.
 */
public class WriteDatapointsRequest extends AbstractBceRequest {
    
    private List<Datapoint> datapoints;
    
    public List<Datapoint> getDatapoints() {
        return datapoints;
    }

    public void setDatapoints(List<Datapoint> datapoints) {
        this.datapoints = datapoints;
    }
    
    public WriteDatapointsRequest withDatapoints(List<Datapoint> datapoints) {
        this.datapoints = datapoints;
        return this;
    }
    
    public WriteDatapointsRequest addDatapoint(Datapoint datapoint) {
        initialDatapoints();
        datapoints.add(datapoint);
        return this;
    }
    
    private void initialDatapoints() {
        if (datapoints == null) {
            datapoints = Lists.newArrayList();
        }
    }

    @Override
    public WriteDatapointsRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

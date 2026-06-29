package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Response of creating a MongoDB instance.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbCreateInstanceResponse extends GenericMongodbResponse {
    private String orderId;

    private List<MongodbInstanceIdView> dbInstanceSimpleModels = new ArrayList<MongodbInstanceIdView>();

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<MongodbInstanceIdView> getDbInstanceSimpleModels() {
        return dbInstanceSimpleModels;
    }

    public void setDbInstanceSimpleModels(List<MongodbInstanceIdView> dbInstanceSimpleModels) {
        this.dbInstanceSimpleModels = dbInstanceSimpleModels;
    }
}

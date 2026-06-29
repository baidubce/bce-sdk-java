package com.baidubce.services.mongodb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Payment model used by MongoDB order requests.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MongodbPaymentModel {
    // type is campaign or normal
    private String type;

    private java.util.List<String> values;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public java.util.List<String> getValues() {
        return values;
    }

    public void setValues(java.util.List<String> values) {
        this.values = values;
    }
}

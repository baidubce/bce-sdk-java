package com.baidubce.services.tsdb.model;

import java.util.Map;

import com.baidubce.model.AbstractBceResponse;

/**
 * Represent the response for getting fields from Tsdb.
 */
public class GetFieldsResponse extends AbstractBceResponse {
    
    private Map<String, FieldInfo> fields;

    public Map<String, FieldInfo> getFields() {
        return fields;
    }

    public void setFields(Map<String, FieldInfo> fields) {
        this.fields = fields;
    }

    public static class FieldInfo {
        private String type;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

}

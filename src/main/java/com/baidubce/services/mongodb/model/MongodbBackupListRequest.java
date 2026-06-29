package com.baidubce.services.mongodb.model;

/**
 * Request for listing MongoDB backups (marker-based paging, OpenAPI).
 */
public class MongodbBackupListRequest extends GenericMongodbRequest {
    private String marker;

    private Integer maxKeys;

    public String getMarker() {
        return marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public Integer getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
    }
}

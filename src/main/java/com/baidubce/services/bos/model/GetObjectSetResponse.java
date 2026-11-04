package com.baidubce.services.bos.model;

import com.fasterxml.jackson.annotation.JsonAlias;

/** Basic information for an ObjectSet. */
public class GetObjectSetResponse extends BosResponse {
    @JsonAlias("ObjectSetName")
    private String objectSetName;

    public String getObjectSetName() {
        return objectSetName;
    }

    public void setObjectSetName(String objectSetName) {
        this.objectSetName = objectSetName;
    }
}

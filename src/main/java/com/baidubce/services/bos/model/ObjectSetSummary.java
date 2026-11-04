package com.baidubce.services.bos.model;

import com.fasterxml.jackson.annotation.JsonAlias;

/** Summary returned by the ObjectSet list operation. */
public class ObjectSetSummary {
    @JsonAlias("ObjectSetName")
    private String objectSetName;

    public String getObjectSetName() {
        return objectSetName;
    }

    public void setObjectSetName(String objectSetName) {
        this.objectSetName = objectSetName;
    }
}

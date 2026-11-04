package com.baidubce.services.bos.model;

import com.baidubce.auth.BceCredentials;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base request for operations on a named ObjectSet.
 */
public abstract class ObjectSetRequest extends GenericBucketRequest {
    private String objectSetName;

    protected ObjectSetRequest() {
    }

    protected ObjectSetRequest(String bucketName, String objectSetName) {
        super(bucketName);
        this.setObjectSetName(objectSetName);
    }

    @JsonIgnore
    public String getObjectSetName() {
        return objectSetName;
    }

    public void setObjectSetName(String objectSetName) {
        if (objectSetName == null || objectSetName.length() < 3 || objectSetName.length() > 256) {
            throw new IllegalArgumentException("objectSetName length should be between 3 and 256.");
        }
        this.objectSetName = objectSetName;
    }

    @Override
    public ObjectSetRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    public ObjectSetRequest withObjectSetName(String objectSetName) {
        this.setObjectSetName(objectSetName);
        return this;
    }

    @Override
    public ObjectSetRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}

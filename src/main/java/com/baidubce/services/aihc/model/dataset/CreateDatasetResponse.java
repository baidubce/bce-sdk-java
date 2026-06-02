package com.baidubce.services.aihc.model.dataset;

import com.baidubce.model.AbstractBceResponse;

public class CreateDatasetResponse extends AbstractBceResponse {
    
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
} 
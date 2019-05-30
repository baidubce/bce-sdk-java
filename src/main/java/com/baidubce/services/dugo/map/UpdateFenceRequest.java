/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.map;

import com.baidubce.services.dugo.AbstractDuGoRequest;

/**
 * Request for updating a fence
 */
public class UpdateFenceRequest extends AbstractDuGoRequest {
    private String projectId;

    private String fenceId;

    private String fenceName;

    private Object fenceParamsOption;

    private String coordType;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFenceId() {
        return fenceId;
    }

    public void setFenceId(String fenceId) {
        this.fenceId = fenceId;
    }

    public String getFenceName() {
        return fenceName;
    }

    public void setFenceName(String fenceName) {
        this.fenceName = fenceName;
    }

    public Object getFenceParamsOption() {
        return fenceParamsOption;
    }

    public void setFenceParamsOption(Object fenceParamsOption) {
        this.fenceParamsOption = fenceParamsOption;
    }

    public String getCoordType() {
        return coordType;
    }

    public void setCoordType(String coordType) {
        this.coordType = coordType;
    }
}

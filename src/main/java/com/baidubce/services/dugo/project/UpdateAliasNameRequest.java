/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.baidubce.services.dugo.project;

import com.baidubce.services.dugo.AbstractDuGoRequest;

/**
 * Request for updating alias of a device
 */
public class UpdateAliasNameRequest extends AbstractDuGoRequest {
    private String aliasName;

    public UpdateAliasNameRequest(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }
}

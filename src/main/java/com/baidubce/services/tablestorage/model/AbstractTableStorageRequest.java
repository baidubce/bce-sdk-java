/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.baidubce.services.tablestorage.model;

import com.baidubce.auth.BceCredentials;
import com.baidubce.model.AbstractBceRequest;

/**
 * Base class for all TableStorage request objects.
 */
public abstract class AbstractTableStorageRequest extends AbstractBceRequest {
    private String tableName;

    /**
     * Create a TableStorage request.
     */
    AbstractTableStorageRequest() {
    }

    /**
     * Create a TableStorage request using a target table name.
     *
     * @param tableName the target table in TableStorage.
     */
    AbstractTableStorageRequest(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Get the target table name.
     *
     * @return return the target table name.
     */
    public String getTableName() {
        return tableName;
    }


    /**
     * Set bce credentials of this request.
     *
     * @param credentials the bce credentials.
     * @return the reference of this request.
     */
    @Override
    public AbstractTableStorageRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    /**
     * Convert this request to json string.
     *
     * @return the json string represent this request.
     */
    public abstract String toJsonString();
}

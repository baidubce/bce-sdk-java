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

import com.baidubce.BceClientException;
import com.baidubce.services.tablestorage.TableStorageConstants;

/**
 * Represent the request for CreateTable operation.
 */
public class CreateTableRequest extends AbstractTableStorageRequest {
    private TableOption tableOption;

    /**
     * Constructs a create table request with table name and option.
     *
     * @param tableName The target table name.
     * @param option The option used to this request.
     */
    public CreateTableRequest(String tableName, TableOption option) {
        super(tableName);
        this.tableOption = option;
    }

    /**
     * Convert this request to json string.
     *
     * @return Return the json string represent this request.
     */
    @Override
    public String toJsonString() {
        StringBuffer buffer = new StringBuffer("{");

        buffer.append("\"tableVersion\":");
        if (tableOption.getTableVersion() != TableStorageConstants.CREATE_TABLE_VERSION) {
            throw new BceClientException("The tableVersion's value must be "
                    + TableStorageConstants.CREATE_TABLE_VERSION + " in CreateTableRequest.");
        }
        buffer.append(tableOption.getTableVersion());

        buffer.append(",\"compressType\":\"");
        if (tableOption.getCompressType() != CompressType.DEFAULT) {
            buffer.append(tableOption.getCompressType().toString());
        } else {
            buffer.append(CompressType.NONE.toString());
        }
        buffer.append("\"");

        buffer.append(",\"ttl\":");
        long timeToLive = tableOption.getTimeToLive();
        if (timeToLive >= TableStorageConstants.MIN_VALID_LIVE_TIME
                || timeToLive == TableStorageConstants.FORERVER_LIVE_TIME) {
            buffer.append(tableOption.getTimeToLive());
        } else if (timeToLive == TableStorageConstants.DEFAULT_LIVE_TIME) {
            buffer.append(TableStorageConstants.FORERVER_LIVE_TIME);
        } else {
            throw new BceClientException("The timeToLive's value must be "
                    + TableStorageConstants.DEFAULT_LIVE_TIME + " or greater than "
                    + TableStorageConstants.MIN_VALID_LIVE_TIME + ". timeToLive=" + timeToLive + ".");
        }

        buffer.append(",\"maxVersions\":");
        int maxVersions = tableOption.getMaxVersions();
        if (maxVersions > 0) {
            buffer.append(tableOption.getMaxVersions());
        } else if (tableOption.getMaxVersions() == TableStorageConstants.DEFAULT_TABLE_MAX_VERSIONS ) {
            buffer.append(TableStorageConstants.MIN_TABLE_MAX_VERSIONS);
        } else {
            throw new BceClientException("The maxVersions' value must be positive, or equal to "
                    + "DEFAULT_TABLE_MAX_VERSIONS " + TableStorageConstants.DEFAULT_TABLE_MAX_VERSIONS
                    + ". maxVersions=" + maxVersions + ".");
        }

        String storageType = tableOption.getStorageType();
        if (!storageType.equals(TableStorageConstants.EMPTY_STORAGE_TYPE)) {
            if (storageType.equals(TableStorageConstants.STORAGE_TYPE_HIGH_PERFORMANCE)
                    || storageType.equals(TableStorageConstants.STORAGE_TYPE_COMMON_PERFORMANCE)) {
                buffer.append(",\"storageType\":\"");
                buffer.append(storageType);
                buffer.append("\"");
            }
            else {
                throw new BceClientException("The storageType's value must be HighPerformance or CommonPerformance");
            }
        }

        buffer.append("}");
        return buffer.toString();
    }
}

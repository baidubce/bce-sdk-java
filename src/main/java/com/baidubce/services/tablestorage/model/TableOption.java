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

import com.baidubce.services.tablestorage.TableStorageConstants;

/**
 * Table option for create table and update table
 */
public class TableOption {
    private long tableVersion = TableStorageConstants.CREATE_TABLE_VERSION;
    private long timeToLive = TableStorageConstants.DEFAULT_LIVE_TIME;
    private int maxVersions = TableStorageConstants.DEFAULT_TABLE_MAX_VERSIONS;
    private CompressType compressType = CompressType.DEFAULT;

    /**
     * Get table version of this table option object.
     *
     * @return The table version.
     */
    public long getTableVersion() {
        return tableVersion;
    }

    /**
     * Set table state to this table option object.
     *
     * @return true if the table version is valid, otherwise false.
     */
    public void setTableVersion(long tableVersion) {
        this.tableVersion = tableVersion;
    }

    /**
     * Get compress type of this table option object.
     *
     * @return The compress type.
     */
    public CompressType getCompressType() {
        return compressType;
    }

    /**
     * Set compress type to this table option object.
     */
    public void setCompressType(CompressType compressType) {
        this.compressType = compressType;
    }

    /**
     * Get timeToLive of this table option object.
     *
     * @return The timeToLive.
     */
    public long getTimeToLive() {
        return timeToLive;
    }

    /**
     * Set timeToLive of this table option object.
     *
     * @return true if the timeToLive is valid, otherwise false.
     */
    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    /**
     * Get max version of this table option object.
     *
     * @return The max version.
     */
    public int getMaxVersions() {
        return maxVersions;
    }

    /**
     * Set max version of this table option object.
     *
     * @return true if the max version is valid, otherwise false.
     */
    public void setMaxVersions(int maxVersions) {
        this.maxVersions = maxVersions;
    }
}

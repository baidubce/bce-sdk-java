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

/**
 * Represents the output of a ShowTable operation.
 */
public class ShowTableResponse extends AbstractTableStorageResponse {
    private String instanceName;
    private String tableName;
    private TableState tableState;
    private long tableVersion;
    private String createTime;
    private CompressType compressType;
    private int timeToLive;
    private int maxVersion;

    /**
     * Get the instance name of the table.
     * @return The instance name.
     */
    public String getInstanceName() {
        return instanceName;
    }

    /**
     * Set the instance name.
     * @param instanceName The instance name set to show tabel response.
     */
    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    /**
     * Get table table of this show table response.
     *
     * @return The table name of the target table.
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Set the table name.
     *
     * @param tableName The table name set to the show table response.
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Get the table state.
     * @return The table state of the target table.
     */
    public TableState getTableState() {
        return tableState;
    }

    /**
     * Set the table state.
     *
     * @param tableState The table state set to this show table response.
     */
    public void setTableState(TableState tableState) {
        this.tableState = tableState;
    }

    /**
     * Get the table version.
     *
     * @return The table version of the target table.
     */
    public long getTableVersion() {
        return tableVersion;
    }

    /**
     * Set the table version.
     *
     * @param tableVersion The table version set to this show table response.
     */
    public void setTableVersion(long tableVersion) {
        this.tableVersion = tableVersion;
    }

    /**
     * Get the create time.
     *
     * @return The create time of the target table.
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * Set the create time.
     *
     * @return The create time set to this show table response.
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * Get the compress type of target table.
     *
     * @return The compress type of the target table.
     */
    public CompressType getCompressType() {
        return compressType;
    }

    /**
     * Set the compress type.
     *
     * @return The compress type set to this show table response.
     */
    public void setCompressType(CompressType compressType) {
        this.compressType = compressType;
    }

    /**
     * Get the timeToLive of target table.
     *
     * @return The timeToLive of the target table.
     */
    public int getTimeToLive() {
        return timeToLive;
    }

    /**
     * Set the timeToLive.
     *
     * @return The timeToLive set to this show table response.
     */
    public void setTimeToLive(int timeToLive) {
        this.timeToLive = timeToLive;
    }

    /**
     * Get the max version of target table.
     *
     * @return The max version of the target table.
     */
    public int getMaxVersion() {
        return maxVersion;
    }

    /**
     * Set the max version.
     *
     * @return The max version set to this show table response.
     */
    public void setMaxVersion(int maxVersion) {
        this.maxVersion = maxVersion;
    }

    /**
     * Convert this object to string.
     *
     * @return The string represent this object.
     */
    @Override
    public String toString() {
        return "ShowTableResponse [\n  instanceName=" + instanceName
                + ", \n  tableName=" + tableName
                + ", \n  tableState=" + tableState
                + ", \n  tableVersion=" + tableVersion
                + ", \n  createTime=" + createTime
                + ", \n  compressType=" + compressType
                + ", \n  timeToLive=" + timeToLive
                + ", \n  maxVersion=" + maxVersion + "\n]";
    }
}

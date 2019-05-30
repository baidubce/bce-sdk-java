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
 * Represent the request for GetRow operation.
 */
public class GetRowRequest extends AbstractTableStorageRequest {
    private GetRow getRow;

    /**
     * Constructs a get row request with target table name and rowkey.
     *
     * @param tableName The target table name.
     * @param rowkey The target rowkey.
     */
    public GetRowRequest(String tableName, String rowkey) {
        super(tableName);
        this.getRow = new GetRow(rowkey);
    }

    /**
     * Get maxVersion in GetRowRequest.
     *
     * @return The target maxVersion.
     */
    public int getMaxVersions() {
        return getRow.getMaxVersions();
    }

    /**
     * Set maxVersion.
     *
     * @param maxVersion The target maxVersion.
     */
    public void setMaxVersions(int maxVersion) {
        getRow.setMaxVersions(maxVersion);
    }

    /**
     * Add a cell with column name to this request.
     *
     * @param column The column name of the cell.
     * @return This get row request object.
     */
    public GetRowRequest addCell(String column) {
        getRow.addCell(column);
        return this;
    }

    /**
     * Convert to this request object to json string.
     *
     * @return The json string represent this get row request.
     */
    @Override
    public String toJsonString() {
        return getRow.toJsonString();
    }
}

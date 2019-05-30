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
 * Represent the request for PutRow operation.
 */
public class PutRowRequest extends AbstractTableStorageRequest {
    private PutRow putRow;

    /**
     * Constructs the put row request with target table name and rowkey.
     *
     * @param tableName The target table name.
     * @param rowkey The target rowkey.
     */
    public PutRowRequest(String tableName, String rowkey) {
        super(tableName);
        this.putRow = new PutRow(rowkey);
    }

    /**
     * Add a cell with column name and value.
     *
     * @param column The column name of the cell.
     * @param value The value of the cell.
     * @return
     */
    public PutRowRequest addCell(String column, String value) {
        putRow.addCell(column, value);
        return this;
    }

    /**
     * Convert the put row request object to json string.
     *
     * @return The json string represent this put row request.
     */
    @Override
    public String toJsonString() {
        return putRow.toJsonString();
    }
}

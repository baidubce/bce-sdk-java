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
 * Represent the request for DeleteRow operation.
 */
public class DeleteRowRequest extends AbstractTableStorageRequest {
    private DeleteRow deleteRow;

    /**
     * Constructs a delete row request with target table name and rowkey.
     *
     * @param tableName The target table name.
     * @param rowkey The target rowkey.
     */
    public DeleteRowRequest(String tableName, String rowkey) {
        super(tableName);
        this.deleteRow = new DeleteRow(rowkey);
    }

    /**
     * Add a cell with column name to this delete row request.
     * @param column The column name of cell.
     * @return This delete row request object.
     */
    public DeleteRowRequest addCell(String column) {
        deleteRow.addCell(column);
        return this;
    }

    /**
     * Convert this request object to json string.
     *
     * @return the json string represent the request object.
     */
    @Override
    public String toJsonString() {
        return deleteRow.toJsonString();
    }
}

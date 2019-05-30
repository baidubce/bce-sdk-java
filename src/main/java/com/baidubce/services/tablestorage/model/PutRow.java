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
 * Performs PutRow operation on a single row.
 */
public class PutRow extends TableStorageRow {
    /**
     * Constructs a put row object with rowkey.
     *
     * @param rowkey The rowkey of this put row object.
     */
    public PutRow(String rowkey) {
        super(rowkey);
    }

    /**
     * Add a cell to this put row object.
     *
     * @param name the column name of the cell.
     * @param value the value name of the cell.
     * @return This put row object.
     */
    public PutRow addCell(String name, String value) {
        TableStorageCell cell = new TableStorageCell(CellType.PutCell, name, value);
        cells.add(cell);
        rowSize += cell.getSize();
        return this;
    }
}

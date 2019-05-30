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

import java.util.Collections;
import java.util.List;

/**
 * Single row result of a GetRow, BatchGetRow or Scan operation.
 */
public class TableStorageResult {
    private String rowkey;
    private List<TableStorageCell> cells;

    /**
     * Constructs a result object with rowkey and cells.
     *
     * @param rowkey The rowkey set to this object.
     * @param cells
     */
    public TableStorageResult(String rowkey, List<TableStorageCell> cells) {
        this.rowkey = rowkey;
        this.cells = cells;
    }

    /**
     * Get the rowkey of this object.
     *
     * @return The rowkey of this object.
     */
    public String getRowkey() {
        return rowkey;
    }

    /**
     * The list of cells of this object.
     *
     * @return The list of cells of this object.
     */
    public List<TableStorageCell> getCells() {
        return Collections.unmodifiableList(cells);
    }

    /**
     * Convert this object to string.
     *
     * @return The string represent this object.
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("TableStorageResult [\n");
        buffer.append("rowkey=" + rowkey + "\n");
        for (TableStorageCell cell : cells) {
            buffer.append(cell);
            buffer.append(",\n");
        }
        buffer.append("]");
        return buffer.toString();
    }
}

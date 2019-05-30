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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represent a single row in TableStorage.
 */
public class TableStorageRow {
    protected String rowkey;
    protected List<TableStorageCell> cells = new ArrayList<TableStorageCell>();
    // row size is equal to the sum of all cell sizes.
    protected int rowSize = 0;

    /**
     * Constructs a row object with target rowkey.
     *
     * @param rowkey The target rowkey.
     */
    public TableStorageRow(String rowkey) {
        this.rowkey = rowkey;
    }

    /**
     * Get the rowkey of this get object.
     *
     * @return Return the rowkey of this get object.
     */
    public String getRowkey() {
        return rowkey;
    }

    /**
     * Get the list of cells of this get object.
     *
     * @return The list of cells of this get object.
     */
    public List<TableStorageCell> getCells() {
        return Collections.unmodifiableList(cells);
    }

    /**
     * Get the size of this row.
     *
     * @return Get the size of this row.
     */
    public int getRowSize() {
        return rowSize;
    }

    /**
     * Check this table storage row object
     */
    protected static void checkRow(TableStorageRow row) {
        if (row == null) {
            return;
        }

        if (row.rowkey == null || row.rowkey.isEmpty()) {
            throw new BceClientException("The rowkey's value should not be null or be empty.");
        }
        if (row.rowkey.length() > TableStorageConstants.MAX_ROWKEY_SIZE) {
            throw new BceClientException("The rowkey's size should not exceed the limit "
                    + TableStorageConstants.MAX_ROWKEY_SIZE + ". rowkeySize=" + row.rowkey.length() + ".");
        }

        if (row instanceof PutRow && row.rowSize > TableStorageConstants.MAX_ROW_SIZE) {
            throw new BceClientException("The row's size should not exceed the limit "
                    + TableStorageConstants.MAX_ROW_SIZE + ". rowkey="
                    + row.rowkey + ", rowSize=" + row.rowSize + ".");
        }

        if ((row instanceof PutRow || row instanceof DeleteRow)
                && row.cells.size() > TableStorageConstants.MAX_WRITE_CELL_NUM) {
            throw new BceClientException("The number of cells should not exceed the limit "
                    + TableStorageConstants.MAX_WRITE_CELL_NUM + ". rowkey="
                    + row.rowkey + ", cellNum=" + row.cells.size() + ".");
        }
        if (row instanceof GetRow
                && row.cells.size() > TableStorageConstants.MAX_READ_CELL_NUM) {
            throw new BceClientException("The number of cells should not exceed the limit "
                    + TableStorageConstants.MAX_READ_CELL_NUM + ". rowkey="
                    + row.rowkey + ", cellNum=" + row.cells.size() + ".");
        }

        for (TableStorageCell cell : row.cells) {
            TableStorageCell.checkCell(cell);
        }
    }

    /**
     * Convert this row to json string.
     *
     * @return The json string represent this row.
     */
    public String toJsonString() {
        checkRow(this);

        StringBuffer buffer = new StringBuffer("{\"rowkey\":\"");
        try {
            buffer.append(URLEncoder.encode(rowkey, TableStorageConstants.DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("The rowkey don't support "
                    + TableStorageConstants.DEFAULT_ENCODING + " encode, rowkey=" + rowkey + ".");
        }
        buffer.append("\",\"cells\":[");
        for (int i = 0; i < cells.size(); i++) {
            if (i > 0) {
                buffer.append(",");
            }
            TableStorageCell cell = cells.get(i);
            buffer.append(cell.toJsonString());
        }
        buffer.append("]}");
        return buffer.toString();
    }
}

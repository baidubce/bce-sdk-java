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
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

/**
 * Represents a TableStorageCell in a row.
 */
public class TableStorageCell {
    private static final int JSON_STR_LEN = "{\"column\":\"\",\"value\":\"\"}".length();
    private static final int NO_VALUE_JSON_STR_LEN = "{\"column\":\"\"}".length();

    private CellType cellType;
    private String column;
    private String value = "";
    private long timestamp;
    private int size;

    /**
     * Constructs a call with column name.
     *
     * @param column The column name of this cell.
     */
    public TableStorageCell(CellType cellType, String column) {
        this.cellType = cellType;
        this.column = column;
        this.size = NO_VALUE_JSON_STR_LEN + column.length();
    }

    /**
     * Constructs a cell with column name and value.
     * @param column The column name of this cell.
     * @param value The value of this cell.
     */
    public TableStorageCell(CellType cellType, String column, String value) {
        this.cellType = cellType;
        this.column = column;
        this.value = value;
        this.size = JSON_STR_LEN + column.length() + value.length();
    }

    /**
     * Constructs a cell with column name、value and timestamp.
     * @param column The column name of this cell.
     * @param value The value of this cell.
     */
    public TableStorageCell(CellType cellType, String column, String value, long timestamp) {
        this.cellType = cellType;
        this.column = column;
        this.value = value;
        this.timestamp = timestamp;
        this.size = JSON_STR_LEN + column.length() + value.length();
    }

    /**
     * Get the column name of this cell.
     *
     * @return The column name of this cell.
     */
    public String getColumn() {
        return column;
    }

    /**
     * Get the value of this cell.
     *
     * @return The value of this cell.
     */
    public String getValue() {
        return value;
    }

    /**
     * Get the value of this cell.
     *
     * @return The value of this cell.
     */
    public long getTimestamp() { return timestamp; }

    /**
     * Get the size of this cell.
     *
     * @return The size of this cell.
     */
    public int getSize() {
        return size;
    }

    /**
     * Check this cell object
     */
    protected static void checkCell(TableStorageCell cell) {
        if (cell == null) {
            return;
        }

        if (StringUtils.isBlank(cell.column)) {
            throw new BceClientException("The column's value should not be blank.");
        }
        if (!Pattern.matches(TableStorageConstants.NAME_PATTERN_STR, cell.column)) {
            throw new BceClientException("The column's value should match the pattern : "
                    + TableStorageConstants.NAME_PATTERN_STR + ". column=" + cell.column + ".");
        }
        if (cell.cellType == CellType.PutCell) {
            if (cell.value != null && cell.value.length() > TableStorageConstants.MAX_CELL_VALUE_SIZE) {
                throw new BceClientException("The value's length should not exceed the limit "
                        + TableStorageConstants.MAX_CELL_VALUE_SIZE + ". valueLength=" + cell.value.length() + ".");
            }
        } else if (cell.cellType == CellType.GetCell
                || cell.cellType == CellType.ScanCell
                || cell.cellType == CellType.DeleteCell) {
            if (!cell.value.isEmpty()) {
                throw new BceClientException("The value should be empty in GetCell, ScanCell and DeleteCell.");
            }
        }
    }

    /**
     * Convert this cell to string.
     *
     * @return Return the String represent this cell.
     */
    @Override
    public String toString() {
        return "TableStorageCell [\n  column=" + column
                + ",\n  value=" + value
                + ",\n  timestamp=" + timestamp + "\n]";
    }

    /**
     * Convert this cell to json string.
     *
     * @return The json string represent this cell.
     */
    public String toJsonString() {
        checkCell(this);

        StringBuffer buffer = new StringBuffer("{\"column\":\"");
        buffer.append(column);
        buffer.append("\"");
        if (cellType == CellType.PutCell) {
            try {
                buffer.append(",\"value\":\"");
                buffer.append(URLEncoder.encode(value, TableStorageConstants.DEFAULT_ENCODING));
                buffer.append("\"");
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Value don't support "
                        + TableStorageConstants.DEFAULT_ENCODING + " encode, column="
                        + column + ", value=" + value + ".");
            }
        }
        buffer.append("}");
        return buffer.toString();
    }
}

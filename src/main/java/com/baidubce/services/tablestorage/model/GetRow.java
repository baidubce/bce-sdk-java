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

/**
 * Performs GetRow operation on a single row.
 */
public class GetRow extends TableStorageRow {
    private int maxVersions = TableStorageConstants.DEFAULT_MAX_VERSIONS;

    /**
     * Constructs a get row object with target rowkey.
     *
     * @param rowkey The target rowkey.
     */
    public GetRow(String rowkey) {
        super(rowkey);
    }

    /**
     * Get maxVersions in GetRow.
     *
     * @return The target maxVersions.
     */
    public int getMaxVersions() {
        return maxVersions;
    }

    /**
     * Set maxVersions.
     *
     * @param maxVersions The target maxVersions.
     */
    public void setMaxVersions(int maxVersions) {
        this.maxVersions = maxVersions;
    }

    /**
     * Add a cell with column name to this get row object.
     *
     * @param name The column name of this cell.
     * @return This get row object.
     */
    public GetRow addCell(String name) {
        TableStorageCell cell = new TableStorageCell(CellType.GetCell, name);
        cells.add(cell);
        rowSize += cell.getSize();
        return this;
    }

    /**
     * Convert this row to json string.
     *
     * @return The json string represent this row.
     */
    public String toJsonString() {
        StringBuffer buffer = new StringBuffer("{\"rowkey\":\"");
        try {
            buffer.append(URLEncoder.encode(rowkey, TableStorageConstants.DEFAULT_ENCODING));
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("Rowkey don't support "
                    + TableStorageConstants.DEFAULT_ENCODING + " encode, rowkey=" + rowkey + ".");
        }
        buffer.append("\",\"maxVersions\":" + maxVersions);
        buffer.append(",\"cells\":[");
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

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
import java.util.List;

/**
 * Represent the request for Scan operation.
 */
public class ScanRequest extends AbstractTableStorageRequest {
    private String startRowkey = TableStorageConstants.DEFAULT_START_ROWKEY;
    private boolean includeStart = true;
    private String stopRowkey = TableStorageConstants.DEFAULT_STOP_ROWKEY;
    private boolean includeStop = false;
    private int limit = Integer.MAX_VALUE;
    private int maxVersions = TableStorageConstants.DEFAULT_MAX_VERSIONS;
    private List<TableStorageCell> selectors = new ArrayList<TableStorageCell>();

    /**
     * Constructs a scan request with target table name.
     *
     * @param tableName The target table name.
     */
    public ScanRequest(String tableName) {
        super(tableName);
    }

    /**
     * Get the start rowkey of this scan request.
     *
     * @return The start rowkey of this scan request.
     */
    public String getStartRowkey() {
        return startRowkey;
    }

    /**
     * Set the start rowkey and include start rowkey to this scan request.
     *
     * @param startRowkey The start rowkey set to this scan request.
     * @param includeStart Set true if want this scan request include start rowkey, otherwise false.
     */
    public void setStartRowkey(String startRowkey, boolean includeStart) {
        this.startRowkey = startRowkey;
        this.includeStart = includeStart;
    }

    /**
     * Whether this scan request includes start rowkey.
     * @return Return true if this scan request includes start rowkey, otherwise false.
     */
    public boolean isIncludeStart() {
        return includeStart;
    }

    /**
     * Get the stop rowkey of this scan request.
     *
     * @return The stop rowkey of this scan request.
     */
    public String getStopRowkey() {
        return stopRowkey;
    }

    /**
     * Set the stop rowkey and include stop rowkey to this scan request.
     *
     * @param stopRowkey The stop rowkey set to this scan request.
     * @param includeStop Set true if want this scan request include stop rowkey, otherwise false.
     */
    public void setStopRowkey(String stopRowkey, boolean includeStop) {
        this.stopRowkey = stopRowkey;
        this.includeStop = includeStop;
    }

    /**
     * Whether this scan request includes stop rowkey.
     * @return Return true if this scan request includes stop rowkey, otherwise false.
     */
    public boolean isIncludeStop() {
        return includeStop;
    }

    /**
     * Get the limit number of returned records corresponding to this request.
     *
     * @return The value of the limit.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Set the limit number of returned records corresponding to this request.
     *
     * @param limit The value set to the limit.
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Get the maxVersions of this scan request. Multiple versions is a advance function of TableStorage, which will
     * release to public in the future.
     *
     */
    public int getMaxVersions() {
        return maxVersions;
    }

    /**
     * Set the maxVersions to this scan request. Multiple versions is a advance feature of TableStorage, which will
     * release to public in the future.
     *
     * @param maxVersions The value set to maxVersions.
     */
    public void setMaxVersions(int maxVersions) {
        this.maxVersions = maxVersions;
    }

    /**
     * Get all cell objects in this request
     *
     * @return Return the list of all cell objects
     */
    public List<TableStorageCell> getSelectors() { return selectors; }

    /**
     * Add a cell with column name to this get object.
     *
     * @param column The column name of this cell.
     * @return This scan request object.
     */
    public ScanRequest addSelector(String column) {
        TableStorageCell cell = new TableStorageCell(CellType.ScanCell, column);
        this.selectors.add(cell);
        return this;
    }


    /**
     * Check this table storage row object
     */
    protected static void checkScanRequest(ScanRequest request) {
        if (request == null) {
            return;
        }

        if (request.getStartRowkey() != null
                && request.getStopRowkey() != null
                && request.getStartRowkey() != TableStorageConstants.DEFAULT_START_ROWKEY
                && request.getStopRowkey() != TableStorageConstants.DEFAULT_STOP_ROWKEY) {
            int compareResult = request.getStopRowkey().compareTo(request.getStartRowkey());
            if ((compareResult < 0)) {
                throw new BceClientException("The stopRowkey's value " + request.getStopRowkey()
                        + " must be greater than the startRowkey's value " + request.getStartRowkey() + ".");
            }
        }
        if (request.getLimit() <= 0) {
            throw new BceClientException("The limit's value must be positive. limit=" + request.getLimit() + ".");
        }
        if (request.getMaxVersions() <= 0) {
            throw new BceClientException("The maxVersions' value must be positive. maxVersions="
                    + request.getMaxVersions() + ".");
        }
        if (request.selectors.size() > TableStorageConstants.MAX_READ_CELL_NUM) {
            throw new BceClientException("The number of selectors should not exceed the limit "
                    + TableStorageConstants.MAX_READ_CELL_NUM + ". selectorNum=" + request.selectors.size() + ".");
        }

        for (TableStorageCell cell : request.selectors) {
            TableStorageCell.checkCell(cell);
        }
    }

    /**
     * Convert this scan request to json string.
     *
     * @return The json string represent this scan request.
     */
    @Override
    public String toJsonString() {
        checkScanRequest(this);

        StringBuffer buffer = new StringBuffer("{");
        buffer.append("\"maxVersions\":" + maxVersions);
        try {
            if ((startRowkey != null) && startRowkey != TableStorageConstants.DEFAULT_START_ROWKEY) {
                buffer.append(",\"startRowkey\":\"");
                buffer.append(URLEncoder.encode(startRowkey, TableStorageConstants.DEFAULT_ENCODING));
                buffer.append("\",\"includeStart\":");
                buffer.append(includeStart);
            }
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("The startRowkey's value don't support "
                    + TableStorageConstants.DEFAULT_ENCODING + " encode, startRowkey=" + startRowkey + ".");
        }
        try {
            if (stopRowkey != null && stopRowkey != TableStorageConstants.DEFAULT_STOP_ROWKEY) {
                buffer.append(",\"stopRowkey\":\"");
                buffer.append(URLEncoder.encode(stopRowkey, TableStorageConstants.DEFAULT_ENCODING));
                buffer.append("\",\"includeStop\":");
                buffer.append(includeStop);
            }
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("The stopRowkey's value don't support "
                    + TableStorageConstants.DEFAULT_ENCODING + " encode, stopRowkey=" + stopRowkey + ".");
        }
        buffer.append(",\"selector\":[");
        for (int i = 0; i < selectors.size(); i++) {
            if (i > 0) {
                buffer.append(",");
            }
            TableStorageCell cell = selectors.get(i);
            buffer.append(cell.toJsonString());
        }
        buffer.append("]");
        if (limit != Integer.MAX_VALUE) {
            buffer.append(",\"limit\":");
            buffer.append(limit);
        }
        buffer.append("}");
        return buffer.toString();
    }

}

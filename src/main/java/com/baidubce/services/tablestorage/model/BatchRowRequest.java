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

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for all BatchXXXRowRequest objects.
 */
public class BatchRowRequest<T extends TableStorageRow> extends AbstractTableStorageRequest {
    private int maxVersions = TableStorageConstants.DEFAULT_MAX_VERSIONS;
    protected List<T> rows = new ArrayList<T>();

    BatchRowRequest(String tableName) {
        super(tableName);
    }

    /**
     * Check this batch row object
     */
    public static void checkBatchRequest(BatchRowRequest batchRowRequest) {
        if (batchRowRequest == null) {
            return;
        }

        if ((batchRowRequest instanceof BatchPutRowRequest || batchRowRequest instanceof BatchDeleteRowRequest)
                && batchRowRequest.rows.size() > TableStorageConstants.MAX_WRITE_ROW_NUM) {
            throw new BceClientException("The row's size should not exceed the limit "
                    + TableStorageConstants.MAX_WRITE_ROW_NUM + " in Put and Delete operations. rowSize="
                    + batchRowRequest.rows.size() + ".");
        }
        if (batchRowRequest instanceof BatchGetRowRequest
                && batchRowRequest.rows.size() > TableStorageConstants.MAX_READ_ROW_NUM) {
            throw new BceClientException("The row's size should not exceed the limit "
                    + TableStorageConstants.MAX_READ_ROW_NUM + " in Get operation. rowSize="
                    + batchRowRequest.rows.size() + ".");
        }
        for (Object row : batchRowRequest.rows) {
            TableStorageRow.checkRow((TableStorageRow) row);
        }
    }

    /**
     * Get maxVersions in BatchGetRow.
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
     * Convert this request to json string.
     *
     * @return The json string represent this request.
     */
    @Override
    public String toJsonString() {
        checkBatchRequest(this);
        StringBuffer buffer = new StringBuffer("{");
        buffer.append("\"maxVersions\":" + maxVersions);
        buffer.append(",");
        buffer.append("\"rows\":[");
        for (int i = 0; i < rows.size(); i++) {
            if (i > 0) {
                buffer.append(",");
            }
            TableStorageRow row = rows.get(i);
            buffer.append(row.toJsonString());
        }
        buffer.append("]}");
        return buffer.toString();
    }
}

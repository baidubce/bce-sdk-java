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
 * Represents the output of a GetRow operation.
 */
public class GetRowResponse extends AbstractTableStorageResponse {
    private TableStorageResult result;

    /**
     * Get the result of this get row response.
     *
     * @return The result object of this get row response.
     */
    public TableStorageResult getResult() {
        return result;
    }

    /**
     * Set the result of this get row response.
     *
     * @param result The result set to this get row response.
     */
    public void setResult(TableStorageResult result) {
        this.result = result;
    }

    /**
     * Convert this response object to string.
     *
     * @return The string represent this response object.
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("GetRowResponse [\n");
        buffer.append(result);
        buffer.append(",\n");
        buffer.append("]");
        return buffer.toString();
    }
}

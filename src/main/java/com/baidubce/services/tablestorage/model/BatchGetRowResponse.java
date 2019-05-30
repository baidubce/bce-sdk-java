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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the output of a BatchGetRow operation.
 */
public class BatchGetRowResponse extends AbstractTableStorageResponse {
    private List<TableStorageResult> results = new ArrayList<TableStorageResult>();

    /**
     * Get the results of this response.
     *
     * @return Return the list of all results.
     */
    public List<TableStorageResult> getResults() {
        return Collections.unmodifiableList(results);
    }

    /**
     * Set the results of this response.
     *
     * @param results the results will be set to this response.
     */
    public void setResults(List<TableStorageResult> results) {
        this.results = results;
    }

    /**
     * Convert this response to string.
     *
     * @return the string represent this response.
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("BatchGetRowResponse [\n");
        for (TableStorageResult result : results) {
            buffer.append(result);
            buffer.append(",\n");
        }
        buffer.append("]");
        return buffer.toString();
    }
}

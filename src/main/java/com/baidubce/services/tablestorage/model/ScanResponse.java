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

import com.baidubce.model.AbstractBceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the output of a Scan operation.
 */
public class ScanResponse extends AbstractBceResponse {
    private List<TableStorageResult> results = new ArrayList<TableStorageResult>();
    /*
     * nextStartKey used for next scan when not empty
     */
    private String nextStartKey;

    /**
     * Get the next start key of this scan response, which can be use to continue scan.
     *
     * @return The next start key of this scan response.
     */
    public String getNextStartKey() {
        return nextStartKey;
    }

    /**
     * Set the next start key of this scan response.
     *
     * @param nextStartKey The next start rowkey set to this scan response.
     */
    public void setNextStartKey(String nextStartKey) {
        this.nextStartKey = nextStartKey;
    }

    /**
     * Set the results of this scan response.
     *
     * @param results The result set to this scan response.
     */
    public void setResults(List<TableStorageResult> results) {
        this.results = results;
    }

    /**
     * Get list of results of this scan response.
     *
     * @return The list of results of this scan response.
     */
    public List<TableStorageResult> getResults() {
        return results;
    }

    /**
     * Convert this object to string.
     *
     * @return The string represent this object.
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("ScanResponse [\n");
        buffer.append("nextStartKey:" + nextStartKey + ",\n");
        for (TableStorageResult result : results) {
            buffer.append(result);
            buffer.append(",\n");
        }
        buffer.append("]");
        return buffer.toString();
    }
}

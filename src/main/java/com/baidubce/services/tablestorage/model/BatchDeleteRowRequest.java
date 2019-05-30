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
 * Represent the request for BatchDeleteRow operation.
 */
public class BatchDeleteRowRequest extends BatchRowRequest<DeleteRow> {

    /**
     * Constructs a batch delete row request with a table name.
     *
     * @param tableName The target table name in TableStorage.
     */
    public BatchDeleteRowRequest(String tableName) {
        super(tableName);
    }

    /**
     * Add a delete row object to the batch delete row request.
     *
     * @param deleteRow The single delete row object.
     */
    public void addDeleteRow(DeleteRow deleteRow) {
        rows.add(deleteRow);
    }

    /**
     * Get all delete row objects in this request.
     *
     * @return Return the list of all delete row objects.
     */
    public List<DeleteRow> getDeleteRows() {
        return Collections.unmodifiableList(rows);
    }
}

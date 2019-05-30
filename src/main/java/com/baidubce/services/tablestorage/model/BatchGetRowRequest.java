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
 * Represent the request for BatchGetRow operation.
 */
public class BatchGetRowRequest extends BatchRowRequest<GetRow> {

    /**
     * Constructs a batch get row request with a table name.
     *
     * @param tableName The target table name in TableStorage.
     */
    public BatchGetRowRequest(String tableName) {
        super(tableName);
    }

    /**
     * Add a get row object to the batch get row request.
     *
     * @param getRow The single get row object.
     */
    public void addGetRow(GetRow getRow) {
        rows.add(getRow);
    }

    /**
     * Get all get row objects in this request.
     *
     * @return Return the list of all get row objects.
     */
    public List<GetRow> getGetRows() {
        return Collections.unmodifiableList(rows);
    }
}

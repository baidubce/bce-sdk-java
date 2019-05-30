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

package com.baidubce.services.tablestorage.model.transform;


import com.baidubce.services.tablestorage.TableStorageConstants;
import com.baidubce.services.tablestorage.model.CellType;
import com.baidubce.services.tablestorage.model.TableStorageCell;
import com.baidubce.services.tablestorage.model.TableStorageResult;
import com.fasterxml.jackson.databind.JsonNode;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Used to parse BatchGetRowResponse from HttpResponse body
 */
public class TableStorageResultUnmarshaller implements Unmarshaller<TableStorageResult, JsonNode> {

    /**
     * Unmarshal the result of JsonNode
     *
     * @param resultNode result JsonNode which can be unmarshalled to TableStorageResult object.
     * @return The TableStorageResult object unmarshalled from resultNode.
     * @throws Exception
     */
    @Override
    public TableStorageResult unmarshall(JsonNode resultNode) throws Exception {
        String rowkey = resultNode.get(TableStorageConstants.JSON_ROWKEY).asText();
        JsonNode cellsNode = resultNode.get(TableStorageConstants.JSON_CELLS);
        List<TableStorageCell> cells = new ArrayList<TableStorageCell>();
        if (cells != null) {
            Iterator<JsonNode> cellList = cellsNode.elements();
            while (cellList.hasNext()) {
                JsonNode cellNode = cellList.next();
                String column = cellNode.get(TableStorageConstants.JSON_COLUMN).asText();
                String value = cellNode.get(TableStorageConstants.JSON_VALUE).asText();
                String decodeValue = URLDecoder.decode(value, TableStorageConstants.DEFAULT_ENCODING);
                int timestamp = 0;
                if (cellNode.has(TableStorageConstants.JSON_TIMESTAMP)) {
                    timestamp = cellNode.get(TableStorageConstants.JSON_TIMESTAMP).asInt();
                }
                TableStorageCell cell = new TableStorageCell(CellType.ResultCell, column, decodeValue, timestamp);
                cells.add(cell);
            }
        }

        String decodeRowkey = URLDecoder.decode(rowkey, TableStorageConstants.DEFAULT_ENCODING);
        return new TableStorageResult(decodeRowkey, cells);
    }
}


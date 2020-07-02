/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.baidubce.services.tablestoragehbaseclient.adaptor;

import com.baidubce.services.tablestorage.model.CompressType;
import com.baidubce.services.tablestorage.model.TableStorageCell;
import com.baidubce.services.tablestorage.model.TableStorageResult;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

/**
 * Convert tablestorage parameters to hbase parameters.
 */
public class TablestorageConvertor {

    public static Compression.Algorithm toCompressionAlgorithm(CompressType type) {
        if (type == CompressType.NONE) {
            return Compression.Algorithm.NONE;
        } else if (type == CompressType.SNAPPY_ALL) {
            return Compression.Algorithm.SNAPPY;
        } else {
            return Compression.Algorithm.valueOf(type.name());
        }
    }

    public static CompressType toCompressionType(Compression.Algorithm algorithm) {
        if (algorithm == Compression.Algorithm.NONE) {
            return CompressType.NONE;
        } else if (algorithm == Compression.Algorithm.SNAPPY) {
            return CompressType.SNAPPY_ALL;
        } else {
            throw new UnsupportedOperationException("Only support SNAPPY compression algorithm");
        }
    }

    public static Result toHBaseResult(TableStorageResult tableStorageResult) {
        if (tableStorageResult == null) {
            return Result.create(new Cell[0]);
        }

        List<TableStorageCell> tableStorageCells = tableStorageResult.getCells();
        List<Cell> cells = new ArrayList<Cell>(tableStorageResult.getCells().size());
        for (TableStorageCell cell : tableStorageCells) {
            Cell hbaseCell = CellUtil.createCell(tableStorageResult.getRowkey().getBytes(),
                    Constants.DEFAULT_FAMILY.getBytes(), cell.getColumn().getBytes(),
                    cell.getTimestamp() == 0 ? HConstants.LATEST_TIMESTAMP : cell.getTimestamp(),
                    KeyValue.Type.Put.getCode(), cell.getValue().getBytes());
            cells.add(hbaseCell);
        }
        return Result.create(cells);
    }

    public static Result[] toBatchGetHBaseResults(List<Get> gets, List<TableStorageResult> tableStorageResults) {
        Result[] results = new Result[gets.size()];
        int index = 0;
        int getsIndex = 0;

        for (TableStorageResult tableStorageResult : tableStorageResults) {
            while (Bytes.compareTo(gets.get(getsIndex++).getRow(), tableStorageResult.getRowkey().getBytes()) != 0) {
                results[index++] = TablestorageConvertor.toHBaseResult(null);
            }

            results[index++] = TablestorageConvertor.toHBaseResult(tableStorageResult);
        }

        while (index < results.length) {
            results[index++] = TablestorageConvertor.toHBaseResult(null);
        }

        return results;
    }
}

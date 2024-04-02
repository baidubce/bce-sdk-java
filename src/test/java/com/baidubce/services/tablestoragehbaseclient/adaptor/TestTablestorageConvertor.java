/*
 * Copyright (c) 2019 Baidu.com, Inc. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.baidubce.services.tablestoragehbaseclient.adaptor;

import com.baidubce.services.tablestorage.model.CellType;
import com.baidubce.services.tablestorage.model.CompressType;
import com.baidubce.services.tablestorage.model.TableStorageCell;
import com.baidubce.services.tablestorage.model.TableStorageResult;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestTablestorageConvertor {

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentToCompressionAlgorithm() {
        Compression.Algorithm algorithm = TablestorageConvertor.toCompressionAlgorithm(CompressType.DEFAULT);
    }

    @Test
    public void testNormalToCompressionAlgorithm() {
        Compression.Algorithm algorithm = TablestorageConvertor.toCompressionAlgorithm(CompressType.NONE);
        assertEquals(Compression.Algorithm.NONE, algorithm);

        algorithm = TablestorageConvertor.toCompressionAlgorithm(CompressType.SNAPPY_ALL);
        assertEquals(Compression.Algorithm.SNAPPY, algorithm);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGZArgumentToCompressionType() {
        CompressType compressType = TablestorageConvertor.toCompressionType(Compression.Algorithm.GZ);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLZ4ArgumentToCompressionType() {
        CompressType compressType = TablestorageConvertor.toCompressionType(Compression.Algorithm.LZ4);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testLZ0ArgumentToCompressionType() {
        CompressType compressType = TablestorageConvertor.toCompressionType(Compression.Algorithm.LZO);
    }

    @Test
    public void testNormalToCompressionType() {
        CompressType compressType = TablestorageConvertor.toCompressionType(Compression.Algorithm.NONE);
        assertEquals(CompressType.NONE, compressType);

        compressType = TablestorageConvertor.toCompressionType(Compression.Algorithm.SNAPPY);
        assertEquals(CompressType.SNAPPY_ALL, compressType);
    }

    @Test
    public void toHBaseResult() {
        Result result1 = TablestorageConvertor.toHBaseResult(null);
        assertTrue(result1.isEmpty());

        TableStorageCell[] tableStorageCells = {new TableStorageCell(CellType.ResultCell, "col0", "value0"),
                new TableStorageCell(CellType.ResultCell, "col1", "value1"),
                new TableStorageCell(CellType.ResultCell, "col2", "value2")};
        List<TableStorageCell> tableStorageCellList = Arrays.asList(tableStorageCells);
        TableStorageResult tableStorageResult = new TableStorageResult("rowkey", tableStorageCellList);
        Result result2 = TablestorageConvertor.toHBaseResult(tableStorageResult);
        assertTrue(Bytes.toString(result2.getRow()).equals("rowkey"));
        assertEquals(3, result2.size());

        int i = 0;
        for (Cell cell : result2.rawCells()) {
            assertTrue(Bytes.toString(CellUtil.cloneFamily(cell)).equals(Constants.DEFAULT_FAMILY));
            assertTrue(Bytes.toString(CellUtil.cloneQualifier(cell)).equals("col" + i));
            assertTrue(Bytes.toString(CellUtil.cloneValue(cell)).equals("value" + i));
            i++;
        }
    }

    @Test
    public void toBatchGetHBaseResults() {
        TableStorageCell[] tableStorageCells = {new TableStorageCell(CellType.ResultCell, "col0", "value0"),
                new TableStorageCell(CellType.ResultCell, "col1", "value1"),
                new TableStorageCell(CellType.ResultCell, "col2", "value2")};
        List<TableStorageCell> tableStorageCellList = Arrays.asList(tableStorageCells);
        TableStorageResult tableStorageResult = new TableStorageResult("rowkey2", tableStorageCellList);
        List<TableStorageResult> tableStorageResultList = Collections.singletonList(tableStorageResult);

        Get[] gets = {new Get("rowkey1".getBytes()), new Get("rowkey1".getBytes()),
                new Get("rowkey2".getBytes()), new Get("rowkey3".getBytes())};
        List<Get> getList = Arrays.asList(gets);

        Result[] results = TablestorageConvertor.toBatchGetHBaseResults(getList, tableStorageResultList);
        assertEquals(getList.size(), results.length);

        int i = 0;
        for (Result result : results) {
            if (i++ == 2) {
                assertTrue(Bytes.toString(result.getRow()).equals(tableStorageResult.getRowkey()));
                assertEquals(3, result.size());
                int j = 0;
                for (Cell cell : result.rawCells()) {
                    assertTrue(Bytes.toString(CellUtil.cloneFamily(cell)).equals(Constants.DEFAULT_FAMILY));
                    assertTrue(Bytes.toString(CellUtil.cloneQualifier(cell)).equals("col" + j));
                    assertTrue(Bytes.toString(CellUtil.cloneValue(cell)).equals("value" + j));
                    j++;
                }
            } else {
                assertTrue(result.isEmpty());
            }
        }
    }
}
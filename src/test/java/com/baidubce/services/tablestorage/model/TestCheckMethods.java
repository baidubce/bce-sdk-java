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
import org.junit.Test;

public class TestCheckMethods {

    private String genString(int length) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            buffer.append("a");
        }
        return buffer.toString();
    }

    @Test (expected = BceClientException.class)
    public void testEmptyColumn() {
        TableStorageCell cell = new TableStorageCell(CellType.GetCell, "");
        TableStorageCell.checkCell(cell);
    }

    @Test (expected = BceClientException.class)
    public void testLongColumn() {
        String column = genString(256);
        TableStorageCell cell = new TableStorageCell(CellType.GetCell, column);
        TableStorageCell.checkCell(cell);
    }

    @Test (expected = BceClientException.class)
    public void testSpecialCharactersColumn() {
        TableStorageCell cell = new TableStorageCell(CellType.GetCell, "//");
        TableStorageCell.checkCell(cell);
    }

    @Test(expected = BceClientException.class)
    public void testDigitalColumn() {
        TableStorageCell cell = new TableStorageCell(CellType.GetCell, "70_A");
        TableStorageCell.checkCell(cell);
    }

    @Test (expected = BceClientException.class)
    public void testLongPutValue() {
        String value = genString(TableStorageConstants.MAX_CELL_VALUE_SIZE + 1);
        TableStorageCell cell = new TableStorageCell(CellType.PutCell, "column", value);
        TableStorageCell.checkCell(cell);
    }

    @Test (expected = BceClientException.class)
    public void testGetValue() {
        TableStorageCell cell = new TableStorageCell(CellType.GetCell, "column", "value");
        TableStorageCell.checkCell(cell);
    }


    @Test (expected = BceClientException.class)
    public void testEmptyRowkey() {
        TableStorageRow row = new TableStorageRow("");
        TableStorageRow.checkRow(row);
    }

    @Test (expected = BceClientException.class)
    public void testLongRowkey() {
        String rowkey = genString(TableStorageConstants.MAX_ROWKEY_SIZE + 1);
        TableStorageRow row = new TableStorageRow(rowkey);
        TableStorageRow.checkRow(row);
    }

    @Test (expected = BceClientException.class)
    public void testPutLongRow() {
        PutRow row = new PutRow("rowkey");
        for (int i = 0; i <= TableStorageConstants.MAX_ROW_SIZE / TableStorageConstants.MAX_CELL_VALUE_SIZE; i++) {
            String value = genString(TableStorageConstants.MAX_CELL_VALUE_SIZE);
            row.addCell("col" + i, value);
        }
        TableStorageRow.checkRow(row);
    }

    @Test (expected = BceClientException.class)
    public void testCheckPutCellNum() {
        PutRow row = new PutRow("rowkey");
        for (int i = 0; i <= TableStorageConstants.MAX_WRITE_CELL_NUM; i++) {
            row.addCell("col" + i, "value" + i);
        }
        TableStorageRow.checkRow(row);
    }

    @Test (expected = BceClientException.class)
    public void testCheckDeleteCellNum() {
        DeleteRow row = new DeleteRow("rowkey");
        for (int i = 0; i <= TableStorageConstants.MAX_WRITE_CELL_NUM; i++) {
            row.addCell("col" + i);
        }
        TableStorageRow.checkRow(row);
    }

    @Test (expected = BceClientException.class)
    public void testCheckGetCellNum() {
        GetRow row = new GetRow("rowkey");
        for (int i = 0; i <= TableStorageConstants.MAX_READ_CELL_NUM; i++) {
            row.addCell("col" + i);
        }
        TableStorageRow.checkRow(row);
    }

    @Test (expected = BceClientException.class)
    public void testInvalidCell() {
        GetRow row = new GetRow("rowkey");
        row.addCell("");
        TableStorageRow.checkRow(row);
    }

    @Test (expected = BceClientException.class)
    public void testCheckPutRowNum() {
        BatchPutRowRequest request = new BatchPutRowRequest("test");
        for (int i = 0; i <= TableStorageConstants.MAX_WRITE_ROW_NUM; i++) {
            PutRow row = new PutRow("rowkey" + i);
            row.addCell("col", "value");
            request.addPutRow(row);
        }
        BatchPutRowRequest.checkBatchRequest(request);
    }

    @Test (expected = BceClientException.class)
    public void testCheckDeleteRowNum() {
        BatchDeleteRowRequest request = new BatchDeleteRowRequest("test");
        for (int i = 0; i <= TableStorageConstants.MAX_WRITE_ROW_NUM; i++) {
            DeleteRow row = new DeleteRow("rowkey" + i);
            row.addCell("col");
            request.addDeleteRow(row);
        }
        BatchPutRowRequest.checkBatchRequest(request);
    }

    @Test (expected = BceClientException.class)
    public void testCheckGetRowNum() {
        BatchGetRowRequest request = new BatchGetRowRequest("test");
        for (int i = 0; i <= TableStorageConstants.MAX_READ_ROW_NUM; i++) {
            GetRow row = new GetRow("rowkey" + i);
            row.addCell("col");
            request.addGetRow(row);
        }
        BatchPutRowRequest.checkBatchRequest(request);
    }

    @Test (expected = BceClientException.class)
    public void testStopRowkeyLessThanStartRowkey() {
        ScanRequest request = new ScanRequest("test");
        request.setStartRowkey("2", true);
        request.setStopRowkey("1", true);
        ScanRequest.checkScanRequest(request);
    }

    @Test (expected = BceClientException.class)
    public void testStopRowkeyEqualStartRowkeyIncludeFalse1() {
        ScanRequest request = new ScanRequest("test");
        request.setStartRowkey("1", true);
        request.setStopRowkey("1", false);
        ScanRequest.checkScanRequest(request);
    }

    @Test (expected = BceClientException.class)
    public void testStopRowkeyEqualStartRowkeyIncludeFalse2() {
        ScanRequest request = new ScanRequest("test");
        request.setStartRowkey("1", false);
        request.setStopRowkey("1", true);
        ScanRequest.checkScanRequest(request);
    }

    @Test (expected = BceClientException.class)
    public void testLimitError() {
        ScanRequest request = new ScanRequest("test");
        request.setLimit(0);
        ScanRequest.checkScanRequest(request);
    }

    @Test (expected = BceClientException.class)
    public void testMaxVersionError() {
        ScanRequest request = new ScanRequest("test");
        request.setMaxVersions(0);
        ScanRequest.checkScanRequest(request);
    }

    @Test (expected = BceClientException.class)
    public void testCheckScanCellNum() {
        ScanRequest request = new ScanRequest("test");
        for (int i = 0; i <= TableStorageConstants.MAX_READ_CELL_NUM; i++) {
            request.addSelector("col" + i);
        }
        ScanRequest.checkScanRequest(request);
    }

    @Test (expected = BceClientException.class)
    public void testInvalidSelector() {
        ScanRequest request = new ScanRequest("test");
        request.addSelector("");
        ScanRequest.checkScanRequest(request);
    }


}

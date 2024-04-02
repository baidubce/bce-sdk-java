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

package com.baidubce.services.tablestorage;

import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.auth.BceCredentials;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tablestorage.model.BatchDeleteRowRequest;
import com.baidubce.services.tablestorage.model.BatchDeleteRowResponse;
import com.baidubce.services.tablestorage.model.BatchGetRowRequest;
import com.baidubce.services.tablestorage.model.BatchGetRowResponse;
import com.baidubce.services.tablestorage.model.BatchPutRowRequest;
import com.baidubce.services.tablestorage.model.BatchPutRowResponse;
import com.baidubce.services.tablestorage.model.DeleteRow;
import com.baidubce.services.tablestorage.model.DeleteRowRequest;
import com.baidubce.services.tablestorage.model.DeleteRowResponse;
import com.baidubce.services.tablestorage.model.GetRow;
import com.baidubce.services.tablestorage.model.GetRowRequest;
import com.baidubce.services.tablestorage.model.GetRowResponse;
import com.baidubce.services.tablestorage.model.PutRow;
import com.baidubce.services.tablestorage.model.PutRowRequest;
import com.baidubce.services.tablestorage.model.PutRowResponse;
import com.baidubce.services.tablestorage.model.ScanRequest;
import com.baidubce.services.tablestorage.model.ScanResponse;

/**
 * Example for Row Operations.
 */
public class RowOperationsExample {

    private TableStorageClient tableStorageClient;

    public RowOperationsExample(TableStorageClient client) {
        this.tableStorageClient = client;
    }

    private void putRow(PutRowRequest request) {
        try {
            PutRowResponse result = tableStorageClient.putRow(request);
            System.out.println("result:" + result.toString());
        } catch (Exception e) {
            printExceptionMessage(e);
        }
    }

    private void deleteRow(DeleteRowRequest request) {
        try {
            DeleteRowResponse result = tableStorageClient.deleteRow(request);
            System.out.println("result:" + result.toString());
        } catch (Exception e) {
            printExceptionMessage(e);
        }
    }

    private void getRow(GetRowRequest request) {
        try {
            GetRowResponse result = tableStorageClient.getRow(request);
            System.out.println("result:" + result.toString());
        } catch (Exception e) {
            printExceptionMessage(e);
        }
    }

    private void batchPutRow(BatchPutRowRequest request) {
        try {
            BatchPutRowResponse result = tableStorageClient.batchPutRow(request);
            System.out.println("result:" + result.toString());
        } catch (Exception e) {
            printExceptionMessage(e);
        }
    }

    private void batchDeleteRow(BatchDeleteRowRequest request) {
        try {
            BatchDeleteRowResponse result = tableStorageClient.batchDeleteRow(request);
            System.out.println("result:" + result.toString());
        } catch (Exception e) {
            printExceptionMessage(e);
        }
    }

    private void batchGetRow(BatchGetRowRequest request) {
        try {
            BatchGetRowResponse result = tableStorageClient.batchGetRow(request);
            System.out.println("result:" + result.toString());
        } catch (Exception e) {
            printExceptionMessage(e);
        }
    }

    private void printExceptionMessage(Exception e) {
        if (e instanceof BceServiceException) {
            BceServiceException bse = (BceServiceException) e;
            System.out.println("Caught an tablestorage, which means your request made it "
                    + "to tablestorage, but was rejected with an error responseContent for some reason.");
            System.out.println("HTTP ErrorDescription Code: " + bse.getStatusCode());
            System.out.println("tablestorage Error Code:   " + bse.getErrorCode());
            System.out.println("Error Type:       " + bse.getErrorType());
            System.out.println("Request ID:       " + bse.getRequestId());
        } else if (e instanceof BceClientException) {
            BceClientException bce = (BceClientException) e;
            System.out.println("Caught an TableStorageClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with TableStorage, "
                    + "such as not being able to access the network.");
        }
        System.out.println("Error Message: " + e.getMessage());
        e.printStackTrace();
    }

    private void scan(ScanRequest request) {
        try {
            ScanResponse result = tableStorageClient.scan(request);
            System.out.println("result:" + result.toString());
        } catch (BceServiceException ase) {
            System.out.println("Caught an tablestorage, which means your request made it "
                    + "to tablestorage, but was rejected with an error responseContent for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP ErrorDescription Code: " + ase.getStatusCode());
            System.out.println("tablestorage Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            ase.printStackTrace();
        } catch (BceClientException ace) {
            System.out.println("Caught an TableStorageClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with TableStorage, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
            ace.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("Getting Started with Baidu TableStorage");
        System.out.println("===========================================\n");

        BceCredentials credentials = null;
        try {
            credentials = new DefaultBceCredentials("xxxxxx", "xxxxxx");
        } catch (Exception e) {
            throw new BceClientException(
                    "Cannot load the credentials from the credential profiles file. "
                            + "Please make sure that your credentials file is at the correct "
                            + "and is in valid format.", e);
        }

        BceClientConfiguration conf = new BceClientConfiguration();

        conf.setCredentials(credentials);
        conf.setEndpoint("bts.xx.baidubce.com");

        String instanceName = "table_demo";
        String tableName = "instance_demo";
        String rowkey1 = "rowkey1";
        String rowkey2 = "rowkey2";
        String rowkey3 = "rowkey3";

        TableStorageClient client = new TableStorageClient(conf, instanceName);
        RowOperationsExample example = new RowOperationsExample(client);

        // put row
        PutRowRequest putRequest = new PutRowRequest(tableName, rowkey1);
        putRequest.addCell("col1", "v1");
        putRequest.addCell("col2", "v2");
        putRequest.addCell("col3", "v3");
        example.putRow(putRequest);

        // delete row
        DeleteRowRequest deleteRequest = new DeleteRowRequest(tableName, rowkey1);
        deleteRequest.addCell("col2");
        example.deleteRow(deleteRequest);

        // get row
        GetRowRequest getRequest = new GetRowRequest(tableName, rowkey1);
        getRequest.addCell("col1");
        getRequest.addCell("col3");
        example.getRow(getRequest);

        // get row with maxVersion for future feature
        int maxVersion = 3;
        GetRowRequest getRequestWithMaxVersion = new GetRowRequest(tableName, rowkey1);
        getRequestWithMaxVersion.addCell("col1");
        getRequestWithMaxVersion.addCell("col3");
        getRequestWithMaxVersion.setMaxVersions(maxVersion);
        example.getRow(getRequestWithMaxVersion);

        // batch put row
        BatchPutRowRequest batchPutRowRequest = new BatchPutRowRequest(tableName);
        PutRow putRow1 = new PutRow(rowkey2);
        putRow1.addCell("col1", "v1");
        putRow1.addCell("col2", "v2");
        putRow1.addCell("col3", "v3");
        PutRow putRow2 = new PutRow(rowkey3);
        putRow2.addCell("col1", "v1");
        putRow2.addCell("col2", "v2");
        putRow2.addCell("col3", "v3");
        batchPutRowRequest.addPutRow(putRow1);
        batchPutRowRequest.addPutRow(putRow2);
        example.batchPutRow(batchPutRowRequest);

        // batch get row
        BatchGetRowRequest batchGetRowRequest = new BatchGetRowRequest(tableName);
        GetRow getRow1 = new GetRow(rowkey2);
        getRow1.addCell("col1");
        getRow1.addCell("col2");
        getRow1.addCell("col3");
        GetRow getRow2 = new GetRow(rowkey3);
        getRow2.addCell("col1");
        getRow2.addCell("col2");
        batchGetRowRequest.addGetRow(getRow1);
        batchGetRowRequest.addGetRow(getRow2);
        example.batchGetRow(batchGetRowRequest);

        // batch get row with maxVersion for future feature
        BatchGetRowRequest batchGetRowRequestWithMaxVersion = new BatchGetRowRequest(tableName);
        GetRow getRowWithMaxVersion1 = new GetRow(rowkey2);
        getRowWithMaxVersion1.addCell("col1");
        getRowWithMaxVersion1.addCell("col2");
        getRowWithMaxVersion1.addCell("col3");
        GetRow getRowWithMaxVersion2 = new GetRow(rowkey3);
        getRowWithMaxVersion2.addCell("col1");
        getRowWithMaxVersion2.addCell("col2");
        batchGetRowRequestWithMaxVersion.addGetRow(getRowWithMaxVersion1);
        batchGetRowRequestWithMaxVersion.addGetRow(getRowWithMaxVersion2);
        batchGetRowRequestWithMaxVersion.setMaxVersions(3);
        example.batchGetRow(batchGetRowRequestWithMaxVersion);

        // batch delete row
        BatchDeleteRowRequest batchDeleteRowRequest = new BatchDeleteRowRequest(tableName);
        DeleteRow delete1 = new DeleteRow(rowkey2);
        delete1.addCell("col1");
        delete1.addCell("col2");
        delete1.addCell("col3");
        DeleteRow delete2 = new DeleteRow(rowkey3);
        delete2.addCell("col1");
        delete2.addCell("col2");
        delete2.addCell("col3");
        batchDeleteRowRequest.addDeleteRow(delete1);
        batchDeleteRowRequest.addDeleteRow(delete2);
        example.batchDeleteRow(batchDeleteRowRequest);

        // scan
        ScanRequest scanRequest = new ScanRequest(tableName);
        scanRequest.setStartRowkey(rowkey1, true);
        scanRequest.setStopRowkey(rowkey1, true);
        scanRequest.setLimit(1);
        scanRequest.addSelector("col1");
        scanRequest.addSelector("col2");
        scanRequest.addSelector("col3");
        example.scan(scanRequest);

        // scan with MaxVersion for future feature
        ScanRequest scanRequestWithMaxVersion = new ScanRequest(tableName);
        scanRequestWithMaxVersion.setStartRowkey(rowkey1, true);
        scanRequestWithMaxVersion.setStopRowkey(rowkey1, true);
        scanRequestWithMaxVersion.setLimit(1);
        scanRequestWithMaxVersion.setMaxVersions(3);
        scanRequestWithMaxVersion.addSelector("col1");
        scanRequestWithMaxVersion.addSelector("col2");
        scanRequestWithMaxVersion.addSelector("col3");
        example.scan(scanRequestWithMaxVersion);

        client.shutdown();
        System.out.println("end now");
    }
}

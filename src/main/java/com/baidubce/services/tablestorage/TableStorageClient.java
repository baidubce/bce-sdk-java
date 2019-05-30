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
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.tablestorage.model.AbstractTableStorageRequest;
import com.baidubce.services.tablestorage.model.BatchDeleteRowRequest;
import com.baidubce.services.tablestorage.model.BatchDeleteRowResponse;
import com.baidubce.services.tablestorage.model.BatchGetRowRequest;
import com.baidubce.services.tablestorage.model.BatchGetRowResponse;
import com.baidubce.services.tablestorage.model.BatchPutRowRequest;
import com.baidubce.services.tablestorage.model.BatchPutRowResponse;
import com.baidubce.services.tablestorage.model.CreateTableRequest;
import com.baidubce.services.tablestorage.model.CreateTableResponse;
import com.baidubce.services.tablestorage.model.DeleteRowRequest;
import com.baidubce.services.tablestorage.model.DeleteRowResponse;
import com.baidubce.services.tablestorage.model.DropTableRequest;
import com.baidubce.services.tablestorage.model.DropTableResponse;
import com.baidubce.services.tablestorage.model.GetRowRequest;
import com.baidubce.services.tablestorage.model.GetRowResponse;
import com.baidubce.services.tablestorage.model.ListKeyRangesRequest;
import com.baidubce.services.tablestorage.model.ListKeyRangesResponse;
import com.baidubce.services.tablestorage.model.ListTablesRequest;
import com.baidubce.services.tablestorage.model.ListTablesResponse;
import com.baidubce.services.tablestorage.model.PutRowRequest;
import com.baidubce.services.tablestorage.model.PutRowResponse;
import com.baidubce.services.tablestorage.model.ScanRequest;
import com.baidubce.services.tablestorage.model.ScanResponse;
import com.baidubce.services.tablestorage.model.ShowTableRequest;
import com.baidubce.services.tablestorage.model.ShowTableResponse;
import com.baidubce.services.tablestorage.model.ShowTableStateResponse;
import com.baidubce.services.tablestorage.model.TableState;
import com.baidubce.services.tablestorage.model.UpdateTableRequest;
import com.baidubce.services.tablestorage.model.UpdateTableResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

import static com.baidubce.services.tablestorage.TableStorageConstants.NAME_PATTERN_STR;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Baidu TableStorage.
 */
public class TableStorageClient extends AbstractTableStorageBceClient {
    private String instanceName;

    /**
     * Constructs a new TableStorage client.
     */
    public TableStorageClient(BceClientConfiguration config, String instanceName) {
        super(config, true);
        this.instanceName = instanceName;
    }

    /**
     * Check if input is legal.
     *
     * @param request The input request.
     */
    private void checkInput(AbstractTableStorageRequest request) {
        if (StringUtils.isBlank(instanceName)) {
            throw new BceClientException("The InstanceName's value should not be blank");
        }
        if (!Pattern.matches(NAME_PATTERN_STR, instanceName)) {
            throw new BceClientException("The InstanceName's value should match the pattern : "
                    + NAME_PATTERN_STR + ".");
        }

        String lowerInstanceName = instanceName.toLowerCase();
        for (String word : TableStorageConstants.INSTANCE_NAME_NOT_START_WITH_WORDS) {
            if (lowerInstanceName.startsWith(word)) {
                throw new BceClientException("The InstanceName's value should not start with " + word + ".");
            }
        }
        for (String word : TableStorageConstants.INSTANCE_NAME_NOT_CONTAIN_WORDS) {
            if (lowerInstanceName.contains(word)) {
                throw new BceClientException("The InstanceName's value should not contain " + word + ".");
            }
        }

        checkNotNull(request, "Request should not be null");
        if (request instanceof ListTablesRequest) {
            if (request.getTableName() != null && !request.getTableName().isEmpty()) {
                throw new BceClientException("The TableName's value should be empty in ListTablesRequest.");
            }
        } else {
            if (StringUtils.isBlank(request.getTableName())) {
                throw new BceClientException("The TableName's value should not be blank in "
                        + request.getClass().getSimpleName() + ".");
            }
            if (!Pattern.matches(NAME_PATTERN_STR, request.getTableName())) {
                throw new BceClientException("The TableName's value should match the pattern : "
                        + NAME_PATTERN_STR + ".");
            }
        }
    }

    /**
     * Create table in tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the create table method on tablestorage.
     * @return The response from the create table method, as returned by tablestorage.
     */
    public CreateTableResponse createTable(CreateTableRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName());
        fillInHeadAndBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, CreateTableResponse.class);
    }

    /**
     * Update table in tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the update table method on tablestorage.
     * @return The response from the update table method, as returned by tablestorage.
     */
    public UpdateTableResponse updateTable(UpdateTableRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName());
        fillInHeadAndBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, UpdateTableResponse.class);
    }

    /**
     * Drop table in tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the drop table method on tablestorage.
     * @return The response from the drop table method, as returned by tablestorage.
     */
    public DropTableResponse dropTable(DropTableRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName());
        return this.invokeHttpClient(internalRequest, DropTableResponse.class);
    }

    /**
     * List tables from tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the list tables method on tablestorage.
     * @return The response from the list tables method, as returned by tablestorage.
     */
    public ListTablesResponse listTables(ListTablesRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                TableStorageConstants.URI_INSTANCE, instanceName, TableStorageConstants.URI_TABLES);
        return this.invokeHttpClient(internalRequest, ListTablesResponse.class);
    }

    /**
     * Show the state of the table named tableName from tablestorage.
     *
     * @param tableName the name of table in tablestorage.
     * @return state of the table in tablestorage.
     */
    public TableState showTableState(String tableName) {
        ShowTableRequest request = new ShowTableRequest(tableName);
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, tableName);
        internalRequest.addParameter(TableStorageConstants.ONLY_STATE, "");
        ShowTableStateResponse response = this.invokeHttpClient(internalRequest, ShowTableStateResponse.class);
        return response.getTableState();
    }

    /**
     * Show the table named tableName from tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the show table method on tablestorage.
     * @return The response from the show table method, as returned by tablestorage.
     */
    public ShowTableResponse showTable(ShowTableRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName());
        return this.invokeHttpClient(internalRequest, ShowTableResponse.class);
    }

    /**
     * List key range in table from tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the list keyRanges method on tablestorage.
     * @return The response from the list keyRanges method, as returned by tablestorage.
     */
    public ListKeyRangesResponse listKeyRanges(ListKeyRangesRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName(),
                TableStorageConstants.URI_SLICES);
        return this.invokeHttpClient(internalRequest, ListKeyRangesResponse.class);
    }

    /**
     * Get the row of the table in tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the get row method on tablestorage.
     * @return The response from the get row method, as returned by tablestorage.
     */
    public GetRowResponse getRow(GetRowRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName(),
                TableStorageConstants.URI_ROW);
        internalRequest.addHeader(TableStorageConstants.X_BCE_BTS_METHOD_KEY,
                                  TableStorageConstants.X_BCE_BTS_METHOD_GET);
        fillInHeadAndBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, GetRowResponse.class);
    }

    /**
     * Put the row to the table in tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the put row method on tablestorage.
     * @return The response from the put row method, as returned by tablestorage.
     */
    public PutRowResponse putRow(PutRowRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName(),
                TableStorageConstants.URI_ROW);
        internalRequest.addHeader(TableStorageConstants.X_BCE_BTS_METHOD_KEY,
                                  TableStorageConstants.X_BCE_BTS_METHOD_PUT);
        fillInHeadAndBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, PutRowResponse.class);
    }

    /**
     * Delete the row of the table in tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the delete row method on tablestorage.
     * @return The response from the delete row method, as returned by tablestorage.
     */
    public DeleteRowResponse deleteRow(DeleteRowRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName(),
                TableStorageConstants.URI_ROW);
        internalRequest.addHeader(TableStorageConstants.X_BCE_BTS_METHOD_KEY,
                                  TableStorageConstants.X_BCE_BTS_METHOD_DELETE);
        fillInHeadAndBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, DeleteRowResponse.class);
    }

    /**
     * Batch get the rows of the table in tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the batch get rows row method on tablestorage.
     * @return The response from the batch get rows method, as returned by tablestorage.
     */
    public BatchGetRowResponse batchGetRow(BatchGetRowRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName(),
                TableStorageConstants.URI_ROWS);
        internalRequest.addHeader(TableStorageConstants.X_BCE_BTS_METHOD_KEY,
                                  TableStorageConstants.X_BCE_BTS_METHOD_GET);
        fillInHeadAndBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, BatchGetRowResponse.class);
    }

    /**
     * Batch put the rows to the table in tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the batch put rows method on tablestorage.
     * @return The response from the batch put rows method, as returned by tablestorage.
     */
    public BatchPutRowResponse batchPutRow(BatchPutRowRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName(),
                TableStorageConstants.URI_ROWS);
        internalRequest.addHeader(TableStorageConstants.X_BCE_BTS_METHOD_KEY,
                                  TableStorageConstants.X_BCE_BTS_METHOD_PUT);
        fillInHeadAndBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, BatchPutRowResponse.class);
    }

    /**
     * Batch delete the rows of the table in tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute batch delete rows method on tablestorage.
     * @return The response from the batch delete rows method, as returned by tablestorage.
     */
    public BatchDeleteRowResponse batchDeleteRow(BatchDeleteRowRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName(),
                TableStorageConstants.URI_ROWS);
        internalRequest.addHeader(TableStorageConstants.X_BCE_BTS_METHOD_KEY,
                                  TableStorageConstants.X_BCE_BTS_METHOD_DELETE);
        fillInHeadAndBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, BatchDeleteRowResponse.class);
    }

    /**
     * Scan the rows of table in tablestorage.
     *
     * @param request Container for the necessary parameters to
     *                execute the scan method on tablestorage.
     * @return The response from the scan method, as returned by tablestorage.
     */
    public ScanResponse scan(ScanRequest request) {
        checkInput(request);

        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST,
                TableStorageConstants.URI_INSTANCE, instanceName,
                TableStorageConstants.URI_TABLE, request.getTableName(),
                TableStorageConstants.URI_ROWS);
        internalRequest.addHeader(TableStorageConstants.X_BCE_BTS_METHOD_KEY,
                TableStorageConstants.X_BCE_BTS_METHOD_GET);
        fillInHeadAndBody(request, internalRequest);
        return this.invokeHttpClient(internalRequest, ScanResponse.class);
    }


}

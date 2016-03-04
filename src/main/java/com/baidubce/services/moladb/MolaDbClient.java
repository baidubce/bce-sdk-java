/*
 * Copyright (c) 2014 Baidu.com, Inc. All Rights Reserved
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
package com.baidubce.services.moladb;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientException;
import com.baidubce.BceServiceException;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.services.moladb.model.AttributeValue;
import com.baidubce.services.moladb.model.BatchGetItemRequest;
import com.baidubce.services.moladb.model.BatchGetItemResponse;
import com.baidubce.services.moladb.model.BatchWriteItemRequest;
import com.baidubce.services.moladb.model.BatchWriteItemResponse;
import com.baidubce.services.moladb.model.CreateInstanceRequest;
import com.baidubce.services.moladb.model.CreateInstanceResponse;
import com.baidubce.services.moladb.model.CreateTableRequest;
import com.baidubce.services.moladb.model.CreateTableResponse;
import com.baidubce.services.moladb.model.DeleteInstanceRequest;
import com.baidubce.services.moladb.model.DeleteInstanceResponse;
import com.baidubce.services.moladb.model.DeleteItemRequest;
import com.baidubce.services.moladb.model.DeleteItemResponse;
import com.baidubce.services.moladb.model.DeleteTableRequest;
import com.baidubce.services.moladb.model.DeleteTableResponse;
import com.baidubce.services.moladb.model.GetInstanceRequest;
import com.baidubce.services.moladb.model.GetInstanceResponse;
import com.baidubce.services.moladb.model.GetTableRequest;
import com.baidubce.services.moladb.model.GetTableResponse;
import com.baidubce.services.moladb.model.GetItemRequest;
import com.baidubce.services.moladb.model.GetItemResponse;
import com.baidubce.services.moladb.model.Key;
import com.baidubce.services.moladb.model.ListInstancesResponse;
import com.baidubce.services.moladb.model.ListInstancesRequest;
import com.baidubce.services.moladb.model.ListTablesRequest;
import com.baidubce.services.moladb.model.ListTablesResponse;
import com.baidubce.services.moladb.model.PutItemRequest;
import com.baidubce.services.moladb.model.PutItemResponse;
import com.baidubce.services.moladb.model.QueryRequest;
import com.baidubce.services.moladb.model.QueryResponse;
import com.baidubce.services.moladb.model.UpdateItemRequest;
import com.baidubce.services.moladb.model.UpdateItemResponse;
import com.baidubce.services.moladb.model.UpdateTableRequest;
import com.baidubce.services.moladb.model.UpdateTableResponse;
import com.baidubce.util.HashUtils;
import com.baidubce.util.HttpUtils;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>
 * Represents the MolaDb client to access MolaDb.
 * </p>
 */

public class MolaDbClient extends AbstractBceClient {
    private static final HttpResponseHandler[] MOLADB_HANDLERS = 
            new HttpResponseHandler[] { 
                new BceMetadataResponseHandler(), 
                new BceErrorResponseHandler(), 
                new MolaDbJsonResponseHandler() 
            };
    private static Logger logger = LoggerFactory.getLogger(MolaDbClient.class);    
    private static final String HEXES = "0123456789ABCDEF";
    
    private static String getHex( byte [] raw ) {
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }
    
    private String defaultInstanceName;

    /**
     * Constructs a new Moladb client using the client configuration to access Moladb.
     *
     * @param conf The Moladb client configuration options controlling how this client
     *             connects to Moladb (e.g. proxy settings, retry counts, etc).
     */
    public MolaDbClient(MolaDbClientConfiguration conf) {
        super(conf, MOLADB_HANDLERS, false);
    }
    
    /**
     * <p>
     * Set the default instance name. Operation on tables and items will be
     * carried on this instance 
     * @param instanceName Default instance name to be set
     * </p>
     */
    public void setDefaultInstanceName(String instanceName) {
        this.defaultInstanceName = instanceName;
    }

    /**
     * <p>
     * Get the default instance name.
     * </p>
     * @return Default instance name
     */
    public String getDefaultInstanceName() {
        return this.defaultInstanceName;
    }

    /**
     * <p>
     * Creates a new instance under same account.
     * </p>
     *
     * @param request Container for the necessary parameters to
     *           execute the Create instance service method on Moladb.
     * @return The responseContent from the Create instance service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public CreateInstanceResponse createInstance(CreateInstanceRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequest(HttpMethodName.POST,
                MolaDbConstants.URI_INSTANCE);
        fillInHeadAndBody(request, httpRequest);
        CreateInstanceResponse ret = this.invokeHttpClient(httpRequest, CreateInstanceResponse.class);
        return ret;
    }

    /**
     * <p>
     * Get instance detail from Moladb
     * </p>
     *
     * @param instanceName Name of the instance to be got from Moladb.
     * @return The responseContent from the Get instance service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public GetInstanceResponse getInstance(String instanceName) {
        checkNotNull(instanceName, "request should not be null.");
        InternalRequest httpRequest = createRequest(HttpMethodName.GET,
                MolaDbConstants.URI_INSTANCE,
                instanceName);
        GetInstanceResponse ret = this.invokeHttpClient(httpRequest, GetInstanceResponse.class);
        return ret;
    }
    
    /**
     * <p>
     * Get instance detail from Moladb
     * </p>
     *
     * @param request Container for the necessary parameters to
     *           execute the Get instance service method on Moladb.
     * @return The responseContent from the Get instance service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public GetInstanceResponse getInstance(GetInstanceRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequest(HttpMethodName.GET,
                MolaDbConstants.URI_INSTANCE,
                request.getInstanceName());
        GetInstanceResponse ret = this.invokeHttpClient(httpRequest, GetInstanceResponse.class);
        return ret;
    }

    /**
     * <p>
     * Delete instance from Moladb
     * </p>
     *
     * <b>NOTE:</b> There MUST be no table within the instance to be deleted.
     * 
     * @param request Container for the necessary parameters to
     *           execute the Delete instance service method on Moladb.
     * @return The responseContent from the Delete instance service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DeleteInstanceResponse deleteInstance(DeleteInstanceRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequest(HttpMethodName.DELETE,
                MolaDbConstants.URI_INSTANCE,
                request.getInstanceName());
        DeleteInstanceResponse ret = this.invokeHttpClient(httpRequest, DeleteInstanceResponse.class);
        return ret;
    }

    /**
     * <p>
     * Delete instance from Moladb
     * </p>
     *
     * <b>NOTE:</b> There MUST be no table within the instance to be deleted.
     * 
     * @param Instance name to be deleted.
     * 
     * @return The responseContent from the Delete instance service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DeleteInstanceResponse deleteInstance(String instanceName) {
        checkNotNull(instanceName, "request should not be null.");
        InternalRequest httpRequest = createRequest(HttpMethodName.DELETE,
                MolaDbConstants.URI_INSTANCE,
                instanceName);
        DeleteInstanceResponse ret = this.invokeHttpClient(httpRequest, DeleteInstanceResponse.class);
        return ret;
    }

    /**
     * <p>
     * Get all instance names created by this account
     * </p>
     * 
     * @return The responseContent from the List instance service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public ListInstancesResponse listInstances() {
        ListInstancesRequest req = new ListInstancesRequest();
        return this.listInstances(req);
    }
    /**
     * <p>
     * Get the instance name list created by this account
     * </p>
     * 
     * @param request Container for the necessary parameters to
     *           execute the list instance service method on Moladb.
     * @return The responseContent from the List instance service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public ListInstancesResponse listInstances(ListInstancesRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequest(HttpMethodName.GET,
                MolaDbConstants.URI_INSTANCE);
        ListInstancesResponse ret = this.invokeHttpClient(httpRequest, ListInstancesResponse.class);
        return ret;
    }

    /**
     * <p>
     * Create a table in moladb
     * </p>
     * 
     * @param request Container for the necessary parameters to
     *           execute the create table service method on Moladb.
     * @return The responseContent from the Create table service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public CreateTableResponse createTable(CreateTableRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.POST,
                MolaDbConstants.URI_TABLE);
        fillInHeadAndBody(request, httpRequest);
        CreateTableResponse ret = this.invokeHttpClient(httpRequest, CreateTableResponse.class);
        return ret;
    }

    /**
     * <p>
     * Delete the specified table from moladb
     * </p>
     * 
     * @param tableName Name of the table to be deleted from Moladb.
     * @return The responseContent from the Delete table service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DeleteTableResponse deleteTable(String tableName) {
        checkNotNull(tableName, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.DELETE,
                MolaDbConstants.URI_TABLE,
                tableName);
        DeleteTableResponse ret = this.invokeHttpClient(httpRequest, DeleteTableResponse.class);
        return ret;
    }
    
    /**
     * <p>
     * Delete a table from moladb
     * </p>
     * 
     * @param request Container for the necessary parameters to
     *           execute the delete table service method on Moladb.
     * @return The responseContent from the Delete table service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DeleteTableResponse deleteTable(DeleteTableRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.DELETE,
                MolaDbConstants.URI_TABLE,
                request.getTableName());
        DeleteTableResponse ret = this.invokeHttpClient(httpRequest, DeleteTableResponse.class);
        return ret;
    }
    
    /**
     * <p>
     * Update a table in moladb
     * </p>
     * 
     * @param request Container for the necessary parameters to
     *           execute the update table service method on Moladb.
     * @return The responseContent from the Update table service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public UpdateTableResponse updateTable(UpdateTableRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.PUT,
                MolaDbConstants.URI_TABLE,
                request.getTableName());
        fillInHeadAndBody(request, httpRequest);
        UpdateTableResponse ret = this.invokeHttpClient(httpRequest, UpdateTableResponse.class);
        return ret;
    }

    /**
     * <p>
     * Get the table details from moladb
     * </p>
     * 
     * @param tableName Name of table to be get from Moladb.
     * @return The responseContent from the Get table service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public GetTableResponse getTable(String tableName) {
        checkNotNull(tableName, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.GET,
                MolaDbConstants.URI_TABLE,
                tableName);
        GetTableResponse ret = this.invokeHttpClient(httpRequest, GetTableResponse.class);
        return ret;
    }
    
    /**
     * <p>
     * Get the table details from moladb
     * </p>
     * 
     * @param request Container for the necessary parameters to
     *           execute the GetTable service method on Moladb.
     * @return The responseContent from the Get table service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public GetTableResponse getTable(GetTableRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.GET,
                MolaDbConstants.URI_TABLE,
                request.getTableName());
        GetTableResponse ret = this.invokeHttpClient(httpRequest, GetTableResponse.class);
        return ret;
    }
    
    /**
     * <p>
     * List all table names under the specified instance name from moladb
     * </p>
     * 
     * @return The responseContent from the list tables service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public ListTablesResponse listTables() {
        ListTablesRequest req = new ListTablesRequest();
        return this.listTables(req);
    }
    
    /**
     * <p>
     * List all table names under the specified instance name from moladb
     * </p>
     * @param request Container for the necessary parameters to
     *           execute the ListTables service method on Moladb.
     * @return The responseContent from the list tables service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public ListTablesResponse listTables(ListTablesRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.GET,
                MolaDbConstants.URI_TABLE);
        ListTablesResponse ret = this.invokeHttpClient(httpRequest, ListTablesResponse.class);
        return ret;
    }

    /**
     * <p>
     * Creates a new item, or replaces an old item with a new item. If an
     * item that has the same primary key as the new item already exists in
     * the specified table, the new item completely replaces the existing
     * item. You can perform a conditional put operation (add a new item if
     * one with the specified primary key doesn't exist), or replace an
     * existing item if it has certain attribute values.
     * </p>
     * <p>
     * When you add an item, the primary key attribute(s) are the only
     * required attributes. Attribute values cannot be null. String and
     * Binary type attributes must have lengths greater than zero. Set type
     * attributes cannot be empty. 
     * </p>
     *
     * @param request Container for the necessary parameters to
     *           execute the PutItem service method on Moladb.
     * @return The responseContent from the PutItem service method, as returned by
     *         Moladb.
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public PutItemResponse putItem(PutItemRequest request) {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.PUT,
                MolaDbConstants.URI_TABLE,
                request.getTableName(),
                MolaDbConstants.URI_ITEM);
        fillInHeadAndBody(request, httpRequest);
        PutItemResponse ret = this.invokeHttpClient(httpRequest, PutItemResponse.class);
        return ret;
    }

    /**
     * <p>
     * The <i>GetItem</i> operation returns a set of attributes for the item
     * with the given primary key. If there is no matching item,
     * <i>GetItem</i> does not return any data.
     * </p>
     * <p>
     * <i>GetItem</i> provides an eventually consistent read by default. If
     * your application requires a strongly consistent read, set
     * <i>ConsistentRead</i> to <code>true</code> . Although a strongly
     * consistent read might take more time than an eventually consistent
     * read, it always returns the last updated value.
     * </p>
     * 
     * @param request Container for the necessary parameters to execute the
     *                  GetItem request.
     * 
     * @return The responseContent from the GetItem service method, as returned by
     *         Moladb.
     * 
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public GetItemResponse getItem(GetItemRequest request)
            throws BceClientException, BceServiceException {
        checkNotNull(request, "request should not be null.");
        HttpMethodName method = HttpMethodName.GET;
        InternalRequest httpRequest = createRequestUnderInstance(method, 
                                                    MolaDbConstants.URI_TABLE, 
                                                    request.getTableName(),
                                                    MolaDbConstants.URI_ITEM);
        buildGetItemRequest(httpRequest, request);
        GetItemResponse ret = this.invokeHttpClient(httpRequest, GetItemResponse.class);
        return ret;
    }

    /**
     * <p>
     * The <i>DeleteItem</i> operation delete the item
     * with the given primary key. If there is no matching item,
     * <i>DeleteItem</i> does not return any data.
     * </p>
     * 
     * @param request Container for the necessary parameters to execute the
     *                  DeleteItem request.
     * 
     * @return The responseContent from the DeleteItem service method, as returned by
     *         Moladb.
     * 
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DeleteItemResponse deleteItem(DeleteItemRequest request)
            throws BceClientException, BceServiceException {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.DELETE, 
                MolaDbConstants.URI_TABLE,
                request.getTableName(),
                MolaDbConstants.URI_ITEM);
        this.putKeyInUrl(httpRequest, request.getKey());
        DeleteItemResponse ret = this.invokeHttpClient(httpRequest, DeleteItemResponse.class);
        return ret;
    }

    /**
     * <p>
     * The <i>UpdateItem</i> operation update the item
     * with the given primary key. If there is no matching item,
     * <i>UpdateItem</i> does not return any data.
     * </p>
     * 
     * @param request Container for the necessary parameters to execute the
     *                  UpdateItem request.
     * 
     * @return The responseContent from the UpdateItem service method, as returned by
     *         Moladb.
     * 
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public UpdateItemResponse updateItem(UpdateItemRequest request) 
            throws BceClientException, BceServiceException {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.POST,
                MolaDbConstants.URI_TABLE, 
                request.getTableName(),
                MolaDbConstants.URI_ITEM);
        fillInHeadAndBody(request, httpRequest);
        UpdateItemResponse ret = this.invokeHttpClient(httpRequest, UpdateItemResponse.class);
        return ret;
    }

    /**
     * <p>
     * The <i>BatchGetItem</i> operation get items with the given primary keys from Moladb. 
     * If there is no matching item, <i>BatchGetItem</i> does not return any data.
     * </p>
     * <p>
     * The <i>BatchGetItem</i> can get items from multi tables with corresponding keys. 
     * These tables MUST under same instance.
     * </p>
     * 
     * @param request Container for the necessary parameters to execute the
     *                  BatchGetItem request.
     * 
     * @return The responseContent from the BatchGetItem service method, as returned by
     *         Moladb.
     * 
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public BatchGetItemResponse batchGetItem(BatchGetItemRequest request)
            throws BceClientException, BceServiceException {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.POST,
                MolaDbConstants.URI_TABLE);
        httpRequest.addParameter(MolaDbConstants.URI_BATCH_GET, null);
        fillInHeadAndBody(request, httpRequest);
        BatchGetItemResponse ret = this.invokeHttpClient(httpRequest, BatchGetItemResponse.class);
        return ret;
    }

    /**
     * <p>
     * The <i>BatchWriteItem</i> operation write items with to Moladb. 
     * </p>
     * <p>
     * The <i>BatchGetItem</i> can write items within multi tables.
     * These tables MUST under same instance.
     * </p>
     * 
     * @param request Container for the necessary parameters to execute the
     *                  BatchWriteItem request.
     * 
     * @return The responseContent from the BatchWriteItem service method, as returned by
     *         Moladb.
     * 
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public BatchWriteItemResponse batchWriteItem(BatchWriteItemRequest request)
            throws BceClientException, BceServiceException {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.POST,
                MolaDbConstants.URI_TABLE);
        httpRequest.addParameter(MolaDbConstants.URI_BATCH_WRITE, null);
        fillInHeadAndBody(request, httpRequest);
        BatchWriteItemResponse ret = this.invokeHttpClient(httpRequest, BatchWriteItemResponse.class);
        return ret;
    }

    /**
     * <p>
     * The <i>Query</i> operation get items from Moladb with Hash Key and an interval of Range Key
     * </p>
     * <p>
     * The <i>Query</i> operation is only support on tables whose Primary key type is HashAndRange Key 
     * </p>
     * 
     * @param request Container for the necessary parameters to execute the
     *                  Query request.
     * 
     * @return The responseContent from the Query service method, as returned by
     *         Moladb.
     * 
     * @throws BceClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the responseContent.  For example
     *             if a network connection is not available.
     * @throws BceServiceException
     *             If an error responseContent is returned by Moladb indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public QueryResponse query(QueryRequest request)
            throws BceClientException, BceServiceException {
        checkNotNull(request, "request should not be null.");
        InternalRequest httpRequest = createRequestUnderInstance(HttpMethodName.POST, 
                                                    MolaDbConstants.URI_TABLE,
                                                    request.getTableName(),
                                                    MolaDbConstants.URI_ITEM);
        httpRequest.addParameter(MolaDbConstants.URI_QUERY, null);
        fillInHeadAndBody(request, httpRequest);
        QueryResponse ret = this.invokeHttpClient(httpRequest, QueryResponse.class);
        return ret;
    }

    protected InternalRequest createRequest(HttpMethodName httpMethod,
            String...pathVariables) {
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(URL_PREFIX); 
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }
        InternalRequest request = new InternalRequest(httpMethod, HttpUtils.appendUri(this.getEndpoint(),
                pathComponents.toArray(new String[pathComponents.size()])));
        request.setCredentials(config.getCredentials());
        request.addHeader(Headers.CONTENT_TYPE, MolaDbConstants.CONTENT_TYPE_JSON);
        return request;
    }
    
    protected InternalRequest createRequestUnderInstance(
            HttpMethodName httpMethod, String...pathVariables) {
        List<String> pathComponents = new ArrayList<String>();
        pathComponents.add(URL_PREFIX); 
        pathComponents.add(MolaDbConstants.URI_INSTANCE); 
        if (this.defaultInstanceName != null) {
            pathComponents.add(this.defaultInstanceName); 
        } else {
            throw new IllegalArgumentException("Instance name MUST be provided when accessing table");
        }
        if (pathVariables != null) {
            for (String pathVariable : pathVariables) {
                pathComponents.add(pathVariable);
            }
        }
        InternalRequest request = new InternalRequest(httpMethod, HttpUtils.appendUri(this.getEndpoint(),
                pathComponents.toArray(new String[pathComponents.size()])));
        request.setCredentials(config.getCredentials());
        request.addHeader(Headers.CONTENT_TYPE, MolaDbConstants.CONTENT_TYPE_JSON);
        return request;
    }

    protected void fillInHeadAndBody(AbstractBceRequest request, InternalRequest httpRequest) {
        String jsonStr = request.toString();
        try {
            byte[] content = jsonStr.getBytes(MolaDbConstants.DEFAULT_ENCODING);
            String md5 = "md5";
            try {
                md5 = getHex(HashUtils.computeMd5Hash(new ByteArrayInputStream(content)));
            } catch (Exception e) {
                logger.warn("Unable calc md5 of the request", e);
            }
           
            httpRequest.addHeader(Headers.CONTENT_LENGTH, Integer.toString(content.length));
            httpRequest.setContent(toRestartableInputStream(content));
            httpRequest.addHeader(Headers.CONTENT_MD5, md5);
        } catch (UnsupportedEncodingException e) {
            throw new BceClientException("json string UnsupportedEncodingException:", e);
        }
    }
    
    private void putKeyInUrl(InternalRequest httpRequest, Key key) {
        Iterator<Entry<String, AttributeValue>> iter = key.getAttributes().entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, AttributeValue> entry = iter.next();
            httpRequest.addParameter(entry.getKey(), entry.getValue().getS());
        }
    }

    private void buildGetItemRequest(InternalRequest httpRequest, GetItemRequest req) {
        this.putKeyInUrl(httpRequest, req.getKey());
        if (req.isConsistentRead()) {
            httpRequest.addParameter(MolaDbConstants.URI_CONSISTENT_READ, MolaDbConstants.JSON_TRUE);
        }
        
        if (req.getAttributesToGet() != null && !req.getAttributesToGet().isEmpty()) {
            Iterator<String> iter = req.getAttributesToGet().iterator();
            String attrList = "";
            attrList += iter.next();
            while (iter.hasNext()) {
                attrList += "," + iter.next();
            }
            httpRequest.addParameter(MolaDbConstants.URI_ATTRIBUTES_TO_GET, attrList);
        }
    }
    
    private RestartableInputStream toRestartableInputStream(byte[] input) {
        return RestartableInputStream.wrap(input);
    }
}

/*
 * Copyright 2018-2019 Baidu, Inc.
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
package com.baidubce.services.tsdb;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.tsdb.model.CreateDatabaseRequest;
import com.baidubce.services.tsdb.model.CreateDatabaseResponse;
import com.baidubce.services.tsdb.model.DeleteDatabaseRequest;
import com.baidubce.services.tsdb.model.DeleteDatabaseResponse;
import com.baidubce.services.tsdb.model.DeleteDatapointsRequest;
import com.baidubce.services.tsdb.model.DeleteDatapointsResponse;
import com.baidubce.services.tsdb.model.DeleteTaskParams;
import com.baidubce.services.tsdb.model.DeleteTaskResult;
import com.baidubce.services.tsdb.model.ExportDatapointsRequest;
import com.baidubce.services.tsdb.model.ExportDatapointsResponse;
import com.baidubce.services.tsdb.model.ExportTaskParams;
import com.baidubce.services.tsdb.model.ExportTaskResult;
import com.baidubce.services.tsdb.model.GetDatabaseRequest;
import com.baidubce.services.tsdb.model.GetDatabaseResponse;
import com.baidubce.services.tsdb.model.GetTaskInternalResponse;
import com.baidubce.services.tsdb.model.GetTaskRequest;
import com.baidubce.services.tsdb.model.GetTaskResponse;
import com.baidubce.services.tsdb.model.ListDatabaseRequest;
import com.baidubce.services.tsdb.model.ListDatabaseResponse;
import com.baidubce.services.tsdb.model.RenewDatabaseRequest;
import com.baidubce.services.tsdb.model.RenewDatabaseResponse;
import com.baidubce.services.tsdb.model.ResizeDatabaseRequest;
import com.baidubce.services.tsdb.model.ResizeDatabaseResponse;
import com.baidubce.services.tsdb.model.TaskParams;
import com.baidubce.services.tsdb.model.TaskResult;
import com.baidubce.services.tsdb.model.TaskType;
import com.baidubce.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Tsdb(Time series database) admin api.
 */
public class TsdbAdminClient extends AbstractTsdbBceClient {

    private static final String ENDPOINT_HOST = "tsdb.iot.gz.baidubce.com";
    private static final String DATABASE = "database";
    private static final String TASK = "task";
    private static final String CLIENT_TOKEN = "clientToken";
    private static final String QUOTA = "quota";
    private static final String RENEW = "renew";

    private static final String REQUEST_NOT_NULL = "request should not be null.";
    private static final String DATABASE_ID_NOT_NULL = "database id should not be null.";

    public TsdbAdminClient(BceClientConfiguration config) {
        super(config.getEndpoint() == null ? config.withEndpoint(ENDPOINT_HOST) : config, TSDB_HANDLERS);
    }

    public CreateDatabaseResponse createDatabase(CreateDatabaseRequest createDatabaseRequest, String clientToken) {
        checkNotNull(createDatabaseRequest, REQUEST_NOT_NULL);
        InternalRequest internalRequest = createRequest(createDatabaseRequest, HttpMethodName.POST, DATABASE);
        internalRequest.addParameter(CLIENT_TOKEN, clientToken);
        fillInHeadAndBody(createDatabaseRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, CreateDatabaseResponse.class);
    }

    public ResizeDatabaseResponse resizeDatabase(ResizeDatabaseRequest resizeDatabaseRequest) {
        checkNotNull(resizeDatabaseRequest);

        InternalRequest internalRequest = createRequest(resizeDatabaseRequest, HttpMethodName.PUT,
                DATABASE, resizeDatabaseRequest.getDatabaseId());
        internalRequest.addParameter(QUOTA, null);
        fillInHeadAndBody(resizeDatabaseRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, ResizeDatabaseResponse.class);
    }

    public RenewDatabaseResponse renewDatabase(RenewDatabaseRequest renewDatabaseRequest) {
        checkNotNull(renewDatabaseRequest);

        InternalRequest internalRequest = createRequest(renewDatabaseRequest, HttpMethodName.PUT,
                DATABASE, renewDatabaseRequest.getDatabaseId());
        internalRequest.addParameter(RENEW, null);
        fillInHeadAndBody(renewDatabaseRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, RenewDatabaseResponse.class);
    }

    public DeleteDatabaseResponse deleteDatabase(String databaseId) {
        checkNotNull(databaseId, DATABASE_ID_NOT_NULL);
        return deleteDatabase(new DeleteDatabaseRequest().withDatabaseId(databaseId));
    }

    public DeleteDatabaseResponse deleteDatabase(DeleteDatabaseRequest deleteDatabaseRequest) {
        checkNotNull(deleteDatabaseRequest, REQUEST_NOT_NULL);
        InternalRequest internalRequest = createRequest(deleteDatabaseRequest, HttpMethodName.DELETE, DATABASE,
                deleteDatabaseRequest.getDatabaseId());
        return this.invokeHttpClient(internalRequest, DeleteDatabaseResponse.class);
    }

    public GetDatabaseResponse getDatabase(String databaseId) {
        checkNotNull(databaseId, DATABASE_ID_NOT_NULL);
        return getDatabase(new GetDatabaseRequest().withDatabaseId(databaseId));
    }

    public GetDatabaseResponse getDatabase(GetDatabaseRequest getDatabaseRequest) {
        checkNotNull(getDatabaseRequest, REQUEST_NOT_NULL);
        InternalRequest internalRequest = createRequest(getDatabaseRequest, HttpMethodName.GET, DATABASE,
                getDatabaseRequest.getDatabaseId());
        return this.invokeHttpClient(internalRequest, GetDatabaseResponse.class);
    }

    public ListDatabaseResponse listDatabase() {
        return listDatabase(new ListDatabaseRequest());
    }

    public ListDatabaseResponse listDatabase(ListDatabaseRequest listDatabaseRequest) {
        checkNotNull(listDatabaseRequest, REQUEST_NOT_NULL);
        InternalRequest internalRequest = createRequest(listDatabaseRequest, HttpMethodName.GET, DATABASE);
        return this.invokeHttpClient(internalRequest, ListDatabaseResponse.class);
    }

    public DeleteDatapointsResponse deleteDatapoints(DeleteDatapointsRequest deleteDatapointsRequest) {
        checkNotNull(deleteDatapointsRequest, REQUEST_NOT_NULL);
        InternalRequest internalRequest = createRequest(deleteDatapointsRequest, HttpMethodName.PUT, DATABASE,
                deleteDatapointsRequest.getDatabaseId());
        internalRequest.addParameter("delete", null);
        fillInHeadAndBody(deleteDatapointsRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, DeleteDatapointsResponse.class);
    }

    public ExportDatapointsResponse exportDatapoints(ExportDatapointsRequest exportDatapointsRequest) {
        checkNotNull(exportDatapointsRequest, REQUEST_NOT_NULL);
        InternalRequest internalRequest = createRequest(exportDatapointsRequest, HttpMethodName.PUT, DATABASE,
                exportDatapointsRequest.getDatabaseId());
        internalRequest.addParameter("export", null);
        fillInHeadAndBody(exportDatapointsRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, ExportDatapointsResponse.class);
    }

    public GetTaskResponse getTask(GetTaskRequest getTaskRequest) {
        checkNotNull(getTaskRequest, REQUEST_NOT_NULL);
        InternalRequest internalRequest = createRequest(getTaskRequest, HttpMethodName.GET, DATABASE,
                getTaskRequest.getDatabaseId(), TASK, getTaskRequest.getTaskId());

        GetTaskInternalResponse internalResponse = this.invokeHttpClient(internalRequest,
                GetTaskInternalResponse.class);
        GetTaskResponse response = new GetTaskResponse();
        response.setTaskId(internalResponse.getTaskId());
        response.setType(internalResponse.getType());
        response.setStatus(internalResponse.getStatus());
        response.setCreteTime(internalResponse.getCreteTime());
        response.setCompletionTime(internalResponse.getCompletionTime());
        response.setError(response.getError());
        response.setParams(getTaskParams(internalResponse));
        response.setResults(getTaskResult(internalResponse));

        return response;
    }

    private TaskParams getTaskParams(GetTaskInternalResponse response) {
        TaskParams params = null;
        if (TaskType.DELETE.getType().equalsIgnoreCase(response.getType())) {
            params = JsonUtils.fromJsonString(response.getParams(), DeleteTaskParams.class);
        } else if (TaskType.EXPORT.getType().equalsIgnoreCase(response.getType())) {
            params = JsonUtils.fromJsonString(response.getParams(), ExportTaskParams.class);
        }
        return params;
    }

    private TaskResult getTaskResult(GetTaskInternalResponse response) {
        TaskResult results = null;
        if (TaskType.DELETE.getType().equalsIgnoreCase(response.getType())) {
            if (StringUtils.isNotEmpty(response.getResults())) {
                results = JsonUtils.fromJsonString(response.getResults(), DeleteTaskResult.class);
            } else {
                results = new DeleteTaskResult();
            }
        } else if (TaskType.EXPORT.getType().equalsIgnoreCase(response.getType())) {
            if (StringUtils.isNotEmpty(response.getResults())) {
                results = JsonUtils.fromJsonString(response.getResults(), ExportTaskResult.class);
            } else {
                results = new ExportTaskResult();
            }
        }
        return results;
    }
}

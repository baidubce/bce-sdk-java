package com.baidubce.services.tsdb;

import com.baidubce.BceClientConfiguration;
import com.baidubce.http.HttpMethodName;
import com.baidubce.internal.InternalRequest;
import com.baidubce.services.tsdb.model.CreateDatabaseRequest;
import com.baidubce.services.tsdb.model.CreateDatabaseResponse;
import com.baidubce.services.tsdb.model.DeleteDatabaseRequest;
import com.baidubce.services.tsdb.model.DeleteDatabaseResponse;
import com.baidubce.services.tsdb.model.GetDatabaseRequest;
import com.baidubce.services.tsdb.model.GetDatabaseResponse;
import com.baidubce.services.tsdb.model.ListDatabaseRequest;
import com.baidubce.services.tsdb.model.ListDatabaseResponse;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Provides the client for accessing the Tsdb(Time series database) admin api.
 */
public class TsdbAdminClient extends AbstractTsdbBceClient {

    private static final String DATABASE = "database";
    private static final String CLIENT_TOKEN = "clientToken";

    public TsdbAdminClient(BceClientConfiguration config) {
        super(config, TSDB_HANDLERS);
    }

    public CreateDatabaseResponse createDatabase(CreateDatabaseRequest createDatabaseRequest, String clientToken) {
        checkNotNull(createDatabaseRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(createDatabaseRequest, HttpMethodName.POST, DATABASE);
        internalRequest.addParameter(CLIENT_TOKEN, clientToken);
        fillInHeadAndBody(createDatabaseRequest, internalRequest);
        return this.invokeHttpClient(internalRequest, CreateDatabaseResponse.class);
    }

    public DeleteDatabaseResponse deleteDatabase(String databaseId) {
        checkNotNull(databaseId, "database id should not be null.");
        return deleteDatabase(new DeleteDatabaseRequest().withDatabaseId(databaseId));
    }

    public DeleteDatabaseResponse deleteDatabase(DeleteDatabaseRequest deleteDatabaseRequest) {
        checkNotNull(deleteDatabaseRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(deleteDatabaseRequest, HttpMethodName.DELETE, DATABASE,
                deleteDatabaseRequest.getDatabaseId());
        return this.invokeHttpClient(internalRequest, DeleteDatabaseResponse.class);
    }

    public GetDatabaseResponse getDatabase(String databaseId) {
        checkNotNull(databaseId, "database id should not be null.");
        return getDatabase(new GetDatabaseRequest().withDatabaseId(databaseId));
    }

    public GetDatabaseResponse getDatabase(GetDatabaseRequest getDatabaseRequest) {
        checkNotNull(getDatabaseRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(getDatabaseRequest, HttpMethodName.GET, DATABASE,
                getDatabaseRequest.getDatabaseId());
        return this.invokeHttpClient(internalRequest, GetDatabaseResponse.class);
    }

    public ListDatabaseResponse listDatabase() {
        return listDatabase(new ListDatabaseRequest());
    }

    public ListDatabaseResponse listDatabase(ListDatabaseRequest listDatabaseRequest) {
        checkNotNull(listDatabaseRequest, "request should not be null.");
        InternalRequest internalRequest = createRequest(listDatabaseRequest, HttpMethodName.GET, DATABASE);
        return this.invokeHttpClient(internalRequest, ListDatabaseResponse.class);
    }
}

package com.baidubce.services.mongodb;

import com.baidubce.AbstractBceClient;
import com.baidubce.BceClientConfiguration;
import com.baidubce.BceClientException;
import com.baidubce.auth.SignOptions;
import com.baidubce.http.Headers;
import com.baidubce.http.HttpMethodName;
import com.baidubce.http.handler.BceErrorResponseHandler;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.http.handler.BceMetadataResponseHandler;
import com.baidubce.http.handler.HttpResponseHandler;
import com.baidubce.internal.InternalRequest;
import com.baidubce.internal.RestartableInputStream;
import com.baidubce.model.AbstractBceRequest;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.mongodb.model.GenericMongodbRequest;
import com.baidubce.services.mongodb.model.MongodbBackup;
import com.baidubce.services.mongodb.model.MongodbBackupListRequest;
import com.baidubce.services.mongodb.model.MongodbBackupMarkerResultResponse;
import com.baidubce.services.mongodb.model.MongodbBackupPolicy;
import com.baidubce.services.mongodb.model.MongodbBackupPolicyRequest;
import com.baidubce.services.mongodb.model.MongodbBackupRequest;
import com.baidubce.services.mongodb.model.MongodbBackupResponse;
import com.baidubce.services.mongodb.model.MongodbCreateInstanceRequest;
import com.baidubce.services.mongodb.model.MongodbCreateInstanceResponse;
import com.baidubce.services.mongodb.model.MongodbCreateReplicaRequest;
import com.baidubce.services.mongodb.model.MongodbCreateShardingRequest;
import com.baidubce.services.mongodb.model.MongodbCreateNodeRequest;
import com.baidubce.services.mongodb.model.MongodbCreateNodeResponse;
import com.baidubce.services.mongodb.model.MongodbCreateOrderRequest;
import com.baidubce.services.mongodb.model.MongodbDatabase;
import com.baidubce.services.mongodb.model.MongodbDatabaseMarkerResultResponse;
import com.baidubce.services.mongodb.model.MongodbGetRecoverableTimeRangeResponse;
import com.baidubce.services.mongodb.model.MongodbInstance;
import com.baidubce.services.mongodb.model.MongodbInstanceListRequest;
import com.baidubce.services.mongodb.model.MongodbInstanceMarkerResultResponse;
import com.baidubce.services.mongodb.model.MongodbInstanceRequest;
import com.baidubce.services.mongodb.model.MongodbInstanceResizeRequest;
import com.baidubce.services.mongodb.model.MongodbInstanceResizeResponse;
import com.baidubce.services.mongodb.model.MongodbMarkerResultResponse;
import com.baidubce.services.mongodb.model.MongodbModifyInstanceNameRequest;
import com.baidubce.services.mongodb.model.MongodbNodeRequest;
import com.baidubce.services.mongodb.model.MongodbOrderResponse;
import com.baidubce.services.mongodb.model.MongodbResetPasswordRequest;
import com.baidubce.services.mongodb.model.MongodbResizeNodeRequest;
import com.baidubce.services.mongodb.model.MongodbCreateSecurityIpGroupRequest;
import com.baidubce.services.mongodb.model.MongodbSecurityIp;
import com.baidubce.services.mongodb.model.MongodbSecurityIpGroupListResponse;
import com.baidubce.services.mongodb.model.MongodbSecurityIpRequest;
import com.baidubce.services.mongodb.model.MongodbUser;
import com.baidubce.services.mongodb.model.MongodbUserDatabaseRequest;
import com.baidubce.services.mongodb.model.MongodbUserMarkerResultResponse;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;

import static com.baidubce.util.StringFormatUtils.checkEmptyExceptionMessageFormat;
import static com.baidubce.util.Validate.checkStringNotEmpty;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Client for accessing MongoDB. All service calls made using this client are blocking, and will
 * not return until the service call completes.
 */
public class MongodbClient extends AbstractBceClient {

    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};
    private static final String BASE_PATH = "/v1/instance";
    private static final String API_BASE_PATH = "/v1/api/mongo/instance";
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";
    private static final String FROM_API = "api";

    private static final HttpResponseHandler[] MONGODB_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public MongodbClient() {
        this(new MongodbClientConfiguration());
    }

    public MongodbClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, MONGODB_HANDLERS);
    }

    // ---------------------------------------------------------------------
    // Instance lifecycle
    // ---------------------------------------------------------------------

    /**
     * Create a MongoDB replica set instance.
     */
    public MongodbCreateInstanceResponse createReplicaInstance(MongodbCreateReplicaRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST, BASE_PATH),
                MongodbCreateInstanceResponse.class);
    }

    /**
     * Create a MongoDB sharded cluster instance.
     */
    public MongodbCreateInstanceResponse createShardInstance(MongodbCreateShardingRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST, BASE_PATH),
                MongodbCreateInstanceResponse.class);
    }

    /**
     * List instances using marker-based paging (manner=marker). Returns
     * {@code MongodbMarkerResultResponse<MongodbInstance>}; the data list is in {@code result}.
     */
    public MongodbMarkerResultResponse<MongodbInstance> listInstances(MongodbInstanceListRequest request) {
        if (request == null) {
            request = new MongodbInstanceListRequest();
        }
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, BASE_PATH);
        internalRequest.addParameter("manner", "marker");
        addParameterIfNotNull(internalRequest, "marker", request.getMarker());
        addParameterIfNotNull(internalRequest, "maxKeys", request.getMaxKeys());
        addParameterIfNotNull(internalRequest, "engineVersion", request.getEngineVersion());
        addParameterIfNotNull(internalRequest, "storageEngine", request.getStorageEngine());
        addParameterIfNotNull(internalRequest, "dbInstanceType", request.getDbInstanceType());
        return invokeHttpClient(internalRequest, MongodbInstanceMarkerResultResponse.class);
    }

    /**
     * Get the detail of an instance.
     */
    public MongodbInstance getInstance(String instanceId) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        return invokeHttpClient(createRequest(HttpMethodName.GET, BASE_PATH + "/" + instanceId),
                MongodbInstance.class);
    }

    /**
     * Recycle instances (move into recycle bin). Supports multiple comma-separated instance ids.
     */
    public AbstractBceResponse recycleInstances(String dbInstanceIds) {
        checkStringNotEmpty(dbInstanceIds, checkEmptyExceptionMessageFormat("dbInstanceIds"));
        InternalRequest internalRequest = createRequest(HttpMethodName.DELETE, BASE_PATH);
        internalRequest.addParameter("dbInstanceIds", dbInstanceIds);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Permanently delete instances from the recycle bin. Supports multiple comma-separated ids.
     */
    public AbstractBceResponse deleteInstancesPermanent(String dbInstanceIds) {
        checkStringNotEmpty(dbInstanceIds, checkEmptyExceptionMessageFormat("dbInstanceIds"));
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, BASE_PATH);
        internalRequest.addParameter("deletePermanent", "");
        internalRequest.addParameter("dbInstanceIds", dbInstanceIds);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Recover postpaid instances from the recycle bin. Supports multiple comma-separated ids.
     */
    public AbstractBceResponse recoverInstances(String dbInstanceIds) {
        checkStringNotEmpty(dbInstanceIds, checkEmptyExceptionMessageFormat("dbInstanceIds"));
        InternalRequest internalRequest = createRequest(HttpMethodName.POST, BASE_PATH);
        internalRequest.addParameter("recover", "");
        internalRequest.addParameter("dbInstanceIds", dbInstanceIds);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modify the name of an instance.
     */
    public AbstractBceResponse modifyInstanceName(String instanceId,
            MongodbModifyInstanceNameRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + instanceId);
        internalRequest.addParameter("modifyName", "");
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // ---------------------------------------------------------------------
    // Instance resize
    // ---------------------------------------------------------------------

    /**
     * Resize a replica set instance. The request wraps {@link MongodbInstanceResizeRequest}.
     */
    public MongodbInstanceResizeResponse resizeInstance(
            MongodbCreateOrderRequest<MongodbInstanceResizeRequest> request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT, BASE_PATH);
        internalRequest.addParameter("resize", "");
        return invokeHttpClient(internalRequest, MongodbInstanceResizeResponse.class);
    }

    /**
     * Add a component (mongos / shard) to a sharded cluster.
     */
    public MongodbCreateNodeResponse createNode(String instanceId,
            MongodbCreateOrderRequest<MongodbCreateNodeRequest> request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST,
                BASE_PATH + "/" + instanceId + "/node"), MongodbCreateNodeResponse.class);
    }

    /**
     * Resize a sharded cluster component (mongos / shard).
     */
    public MongodbOrderResponse resizeNode(String instanceId,
            MongodbCreateOrderRequest<MongodbResizeNodeRequest> request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + instanceId + "/node");
        internalRequest.addParameter("resize", "");
        return invokeHttpClient(internalRequest, MongodbOrderResponse.class);
    }

    // ---------------------------------------------------------------------
    // User & database management
    // ---------------------------------------------------------------------

    /**
     * Create a user.
     */
    public AbstractBceResponse createUser(String instanceId, MongodbUserDatabaseRequest request) {
        return userDatabaseAction(instanceId, "createUser", request);
    }

    /**
     * Drop a user.
     */
    public AbstractBceResponse dropUser(String instanceId, MongodbUserDatabaseRequest request) {
        return userDatabaseAction(instanceId, "dropUser", request);
    }

    /**
     * Update a user's role/privileges.
     */
    public AbstractBceResponse updateUserRole(String instanceId, MongodbUserDatabaseRequest request) {
        return userDatabaseAction(instanceId, "updateRole", request);
    }

    /**
     * Reset the instance administrator password.
     */
    public AbstractBceResponse resetPassword(String instanceId, MongodbResetPasswordRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + instanceId);
        internalRequest.addParameter("resetPassword", "");
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * List users using marker-based paging. Returns {@code MongodbMarkerResultResponse<MongodbUser>};
     * the user list is in {@code result}.
     */
    public MongodbMarkerResultResponse<MongodbUser> listUsers(String instanceId, String marker,
            Integer maxKeys) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, BASE_PATH + "/" + instanceId);
        internalRequest.addParameter("listUsers", "");
        internalRequest.addParameter("manner", "marker");
        addParameterIfNotNull(internalRequest, "marker", marker);
        addParameterIfNotNull(internalRequest, "maxKeys", maxKeys);
        return invokeHttpClient(internalRequest, MongodbUserMarkerResultResponse.class);
    }

    /**
     * Create a database.
     */
    public AbstractBceResponse createDatabase(String instanceId, MongodbUserDatabaseRequest request) {
        return userDatabaseAction(instanceId, "createDatabase", request);
    }

    /**
     * Drop a database.
     */
    public AbstractBceResponse dropDatabase(String instanceId, MongodbUserDatabaseRequest request) {
        return userDatabaseAction(instanceId, "dropDatabase", request);
    }

    /**
     * List databases using marker-based paging. Returns
     * {@code MongodbMarkerResultResponse<MongodbDatabase>}; the database list is in {@code result}.
     */
    public MongodbMarkerResultResponse<MongodbDatabase> listDatabases(String instanceId, String marker,
            Integer maxKeys) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, BASE_PATH + "/" + instanceId);
        internalRequest.addParameter("listDatabases", "");
        internalRequest.addParameter("manner", "marker");
        addParameterIfNotNull(internalRequest, "marker", marker);
        addParameterIfNotNull(internalRequest, "maxKeys", maxKeys);
        return invokeHttpClient(internalRequest, MongodbDatabaseMarkerResultResponse.class);
    }

    // ---------------------------------------------------------------------
    // Whitelist (security IP) management
    // ---------------------------------------------------------------------

    /**
     * Get the whitelist of an instance. Returns {@code MongodbSecurityIp} containing plain IPs,
     * security-group bound IPs and template bound IPs.
     */
    public MongodbSecurityIp describeSecurityIps(String instanceId) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, BASE_PATH + "/" + instanceId);
        internalRequest.addParameter("describeSecurityIps", "");
        return invokeHttpClient(internalRequest, MongodbSecurityIp.class);
    }

    /**
     * Add IPs to the whitelist.
     */
    public AbstractBceResponse addSecurityIps(String instanceId, MongodbSecurityIpRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + instanceId);
        internalRequest.addParameter("addSecurityIps", "");
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete IPs from the whitelist.
     */
    public AbstractBceResponse deleteSecurityIps(String instanceId, MongodbSecurityIpRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + instanceId);
        internalRequest.addParameter("deleteSecurityIps", "");
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // ---------------------------------------------------------------------
    // Whitelist (security IP group) management
    // ---------------------------------------------------------------------

    /**
     * Create a security IP group. Returns {@code AbstractBceResponse} (server returns a boolean result).
     */
    public AbstractBceResponse createSecurityIpGroup(String instanceId,
            MongodbCreateSecurityIpGroupRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST,
                API_BASE_PATH + "/" + instanceId + "/securityIpGroup"), AbstractBceResponse.class);
    }

    /**
     * Delete a security IP group by its uuid.
     */
    public AbstractBceResponse deleteSecurityIpGroup(String instanceId, String uuid) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkStringNotEmpty(uuid, checkEmptyExceptionMessageFormat("uuid"));
        return invokeHttpClient(createRequest(HttpMethodName.DELETE,
                API_BASE_PATH + "/" + instanceId + "/securityIpGroup/" + uuid), AbstractBceResponse.class);
    }

    /**
     * Update a security IP group identified by its uuid.
     */
    public AbstractBceResponse updateSecurityIpGroup(String instanceId, String uuid,
            MongodbCreateSecurityIpGroupRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkStringNotEmpty(uuid, checkEmptyExceptionMessageFormat("uuid"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.PUT,
                API_BASE_PATH + "/" + instanceId + "/securityIpGroup/" + uuid), AbstractBceResponse.class);
    }

    /**
     * Get the security IP group list of an instance.
     */
    public MongodbSecurityIpGroupListResponse getSecurityIpGroupList(String instanceId) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        return invokeHttpClient(createRequest(HttpMethodName.GET,
                API_BASE_PATH + "/" + instanceId + "/securityIpGroup"),
                MongodbSecurityIpGroupListResponse.class);
    }

    // ---------------------------------------------------------------------
    // Backup management
    // ---------------------------------------------------------------------

    /**
     * Create a backup.
     */
    public MongodbBackupResponse createBackup(String instanceId, MongodbBackupRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST,
                BASE_PATH + "/" + instanceId + "/backup"), MongodbBackupResponse.class);
    }

    /**
     * List backups using marker-based paging (listBackupList). Returns
     * {@code MongodbMarkerResultResponse<MongodbBackup>}; the backup list is in {@code result}.
     */
    public MongodbMarkerResultResponse<MongodbBackup> listBackups(String instanceId,
            MongodbBackupListRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        if (request == null) {
            request = new MongodbBackupListRequest();
        }
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                BASE_PATH + "/" + instanceId + "/backup");
        internalRequest.addParameter("listBackupList", "");
        internalRequest.addParameter("marker", request.getMarker() == null ? "-1" : request.getMarker());
        internalRequest.addParameter("maxKeys",
                String.valueOf(request.getMaxKeys() == null ? 10 : request.getMaxKeys()));
        return invokeHttpClient(internalRequest, MongodbBackupMarkerResultResponse.class);
    }

    /**
     * Get the detail of a backup.
     */
    public MongodbBackup describeBackup(String instanceId, String backupId) {
        return describeBackup(instanceId, backupId, null);
    }

    /**
     * Get the detail of a backup, optionally for a specific node (shard).
     */
    public MongodbBackup describeBackup(String instanceId, String backupId, String nodeId) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkStringNotEmpty(backupId, checkEmptyExceptionMessageFormat("backupId"));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                BASE_PATH + "/" + instanceId + "/backup/" + backupId);
        addParameterIfNotNull(internalRequest, "nodeId", nodeId);
        return invokeHttpClient(internalRequest, MongodbBackup.class);
    }

    /**
     * Get the automatic backup policy of an instance.
     */
    public MongodbBackupPolicy getBackupPolicy(String instanceId) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        return invokeHttpClient(createRequest(HttpMethodName.GET,
                BASE_PATH + "/" + instanceId + "/backupPolicy"), MongodbBackupPolicy.class);
    }

    /**
     * Modify the automatic backup policy of an instance.
     */
    public AbstractBceResponse modifyBackupPolicy(String instanceId, MongodbBackupPolicyRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + instanceId + "/backupPolicy"), AbstractBceResponse.class);
    }

    // ---------------------------------------------------------------------
    // Recovery management
    // ---------------------------------------------------------------------

    /**
     * Get the recoverable time range (full + incremental) of an instance.
     */
    public MongodbGetRecoverableTimeRangeResponse getRecoverableTimeRange(String instanceId) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, BASE_PATH + "/" + instanceId);
        internalRequest.addParameter("getRecovableTimeRange", "");
        return invokeHttpClient(internalRequest, MongodbGetRecoverableTimeRangeResponse.class);
    }

    /**
     * Restore by time point (database/table level supported via the request's table-restore fields).
     * This creates a new instance from a source instance at the given time point.
     */
    public MongodbCreateInstanceResponse restoreByTimePoint(
            MongodbCreateOrderRequest<MongodbCreateInstanceRequest> request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST, BASE_PATH),
                MongodbCreateInstanceResponse.class);
    }

    /**
     * Restore from a backup set (database/table level supported via the request's table-restore fields).
     * This creates a new instance from the specified backup set.
     */
    public MongodbCreateInstanceResponse restoreByBackupSet(
            MongodbCreateOrderRequest<MongodbCreateInstanceRequest> request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST, BASE_PATH),
                MongodbCreateInstanceResponse.class);
    }

    // ---------------------------------------------------------------------
    // High availability
    // ---------------------------------------------------------------------

    /**
     * Master/slave switch for a replica set instance.
     */
    public AbstractBceResponse switchInstanceHA(String instanceId, MongodbInstanceRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        if (request == null) {
            request = new MongodbInstanceRequest();
        }
        request.setDbInstanceId(instanceId);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + instanceId);
        internalRequest.addParameter("switchHA", "");
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Master/slave switch for a sharded cluster component (node).
     */
    public AbstractBceResponse switchNodeHA(String instanceId, String nodeId, MongodbNodeRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkStringNotEmpty(nodeId, checkEmptyExceptionMessageFormat("nodeId"));
        if (request == null) {
            request = new MongodbNodeRequest();
        }
        request.setDbInstanceId(instanceId);
        request.setNodeId(nodeId);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + instanceId + "/node/" + nodeId);
        internalRequest.addParameter("switchHA", "");
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    // ---------------------------------------------------------------------
    // Internal helpers
    // ---------------------------------------------------------------------

    private AbstractBceResponse userDatabaseAction(String instanceId, String action,
            MongodbUserDatabaseRequest request) {
        checkStringNotEmpty(instanceId, checkEmptyExceptionMessageFormat("instanceId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        request.setDbInstanceId(instanceId);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + instanceId);
        internalRequest.addParameter(action, "");
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    private InternalRequest createRequest(HttpMethodName httpMethod, String path) {
        return createRequest(new GenericMongodbRequest(), httpMethod, path);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod, String path) {
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path);
        InternalRequest request = new InternalRequest(httpMethod, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());
        // All MongoDB SDK calls are OpenAPI calls; the server distinguishes them by from=api.
        request.addParameter("from", FROM_API);
        return request;
    }

    private InternalRequest createBodyRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod, String path) {
        InternalRequest request = createRequest(bceRequest, httpMethod, path);
        fillPayload(request, bceRequest);
        return request;
    }

    protected void fillPayload(InternalRequest internalRequest, AbstractBceRequest bceRequest) {
        if (internalRequest.getHttpMethod() == HttpMethodName.POST
                || internalRequest.getHttpMethod() == HttpMethodName.PUT
                || internalRequest.getHttpMethod() == HttpMethodName.PATCH) {
            String strJson = JsonUtils.toJsonString(bceRequest);
            byte[] requestJson;
            try {
                requestJson = strJson.getBytes(DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new BceClientException("Unsupported encode.", e);
            }
            internalRequest.addHeader(Headers.CONTENT_LENGTH, String.valueOf(requestJson.length));
            internalRequest.addHeader(Headers.CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
            internalRequest.setContent(RestartableInputStream.wrap(requestJson));
        }
    }

    private void addParameterIfNotNull(InternalRequest request, String name, Object value) {
        if (value != null) {
            request.addParameter(name, String.valueOf(value));
        }
    }
}

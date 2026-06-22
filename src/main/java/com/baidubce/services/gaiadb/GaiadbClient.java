package com.baidubce.services.gaiadb;

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
import com.baidubce.services.gaiadb.model.Account;
import com.baidubce.services.gaiadb.model.AccountListResponse;
import com.baidubce.services.gaiadb.model.AccountPrivilegesRequest;
import com.baidubce.services.gaiadb.model.ClusterCreateRequest;
import com.baidubce.services.gaiadb.model.ClusterCreateResponse;
import com.baidubce.services.gaiadb.model.ClusterDetailResponse;
import com.baidubce.services.gaiadb.model.ClusterListRequest;
import com.baidubce.services.gaiadb.model.ClusterListResponse;
import com.baidubce.services.gaiadb.model.ClusterResizeRequest;
import com.baidubce.services.gaiadb.model.ComputeModuleParamsResponse;
import com.baidubce.services.gaiadb.model.Database;
import com.baidubce.services.gaiadb.model.DatabaseListResponse;
import com.baidubce.services.gaiadb.model.GaiadbInterfaceListResponse;
import com.baidubce.services.gaiadb.model.GaiadbInterfaceUpdateDnsNameRequest;
import com.baidubce.services.gaiadb.model.GaiadbInterfaceUpdateRequest;
import com.baidubce.services.gaiadb.model.GenericGaiadbRequest;
import com.baidubce.services.gaiadb.model.OrderResponse;
import com.baidubce.services.gaiadb.model.PriceResponse;
import com.baidubce.services.gaiadb.model.RenewClusterRequest;
import com.baidubce.services.gaiadb.model.SnapshotCreateRequest;
import com.baidubce.services.gaiadb.model.SnapshotListResponse;
import com.baidubce.services.gaiadb.model.SnapshotPolicyListRequest;
import com.baidubce.services.gaiadb.model.SnapshotPolicyListResponse;
import com.baidubce.services.gaiadb.model.SnapshotPolicyUpdateRequest;
import com.baidubce.services.gaiadb.model.UpdateAccountAuthIpRequest;
import com.baidubce.services.gaiadb.model.UpdateAccountPasswordRequest;
import com.baidubce.services.gaiadb.model.UpdateComputeModuleParamsRequest;
import com.baidubce.services.gaiadb.model.WhitelistRequest;
import com.baidubce.services.gaiadb.model.WhitelistResponse;
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
 * GaiaDB client.
 */
public class GaiadbClient extends AbstractBceClient {

    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};
    private static final String BASE_PATH = "/v1/gaiadb";
    private static final String REQUEST_NULL_ERROR_MESSAGE = "request should not be null.";

    private static final HttpResponseHandler[] GAIADB_HANDLERS = new HttpResponseHandler[] {
            new BceMetadataResponseHandler(),
            new BceErrorResponseHandler(),
            new BceJsonResponseHandler()
    };

    public GaiadbClient() {
        this(new GaiadbClientConfiguration());
    }

    public GaiadbClient(BceClientConfiguration clientConfiguration) {
        super(clientConfiguration, GAIADB_HANDLERS);
    }

    private InternalRequest createRequest(HttpMethodName httpMethod, String path) {
        return createRequest(new GenericGaiadbRequest(), httpMethod, path);
    }

    private InternalRequest createRequest(AbstractBceRequest bceRequest, HttpMethodName httpMethod, String path) {
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path);
        InternalRequest request = new InternalRequest(httpMethod, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        request.setSignOptions(signOptions);
        request.setCredentials(bceRequest.getRequestCredentials());
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

    public ClusterCreateResponse createCluster(ClusterCreateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST, BASE_PATH + "/cluster"),
                ClusterCreateResponse.class);
    }

    public AbstractBceResponse deleteCluster(String clusterId) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        return invokeHttpClient(createRequest(HttpMethodName.DELETE, BASE_PATH + "/cluster/" + clusterId),
                AbstractBceResponse.class);
    }

    public OrderResponse resizeCluster(String clusterId, ClusterResizeRequest request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/cluster/" + clusterId);
        internalRequest.addParameter("resize", "");
        return invokeHttpClient(internalRequest, OrderResponse.class);
    }

    public ClusterListResponse listClusters() {
        return listClusters(null);
    }

    public ClusterListResponse listClusters(ClusterListRequest request) {
        if (request == null) {
            request = new ClusterListRequest();
        }
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, BASE_PATH + "/cluster");
        addParameterIfNotNull(internalRequest, "marker", request.getMarker());
        addParameterIfNotNull(internalRequest, "maxKeys", request.getMaxKeys());
        addParameterIfNotNull(internalRequest, "clusterIpList", request.getClusterIpList());
        addParameterIfNotNull(internalRequest, "clusterIds", request.getClusterIds());
        return invokeHttpClient(internalRequest, ClusterListResponse.class);
    }

    public ClusterDetailResponse getCluster(String clusterId) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        return invokeHttpClient(createRequest(HttpMethodName.GET, BASE_PATH + "/cluster/" + clusterId),
                ClusterDetailResponse.class);
    }

    public OrderResponse renewCluster(String clusterId, RenewClusterRequest request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/cluster/" + clusterId);
        internalRequest.addParameter("renew", "");
        return invokeHttpClient(internalRequest, OrderResponse.class);
    }

    public PriceResponse getClusterPrice(ClusterCreateRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST, BASE_PATH + "/price"),
                PriceResponse.class);
    }

    public PriceResponse getResizePrice(ClusterResizeRequest request) {
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST, BASE_PATH + "/price/diff"),
                PriceResponse.class);
    }

    public GaiadbInterfaceListResponse listInterfaces(String clusterId) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        return invokeHttpClient(createRequest(HttpMethodName.GET, BASE_PATH + "/" + clusterId + "/interface"),
                GaiadbInterfaceListResponse.class);
    }

    public AbstractBceResponse updateInterface(String clusterId, GaiadbInterfaceUpdateRequest request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + clusterId + "/interface"), AbstractBceResponse.class);
    }

    public AbstractBceResponse updateInterfaceDnsName(String clusterId, GaiadbInterfaceUpdateDnsNameRequest request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + clusterId + "/interface/dns-name"), AbstractBceResponse.class);
    }

    public AbstractBceResponse createAccount(String clusterId, Account request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST,
                BASE_PATH + "/" + clusterId + "/account"), AbstractBceResponse.class);
    }

    public AbstractBceResponse deleteAccount(String clusterId, String accountName) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkStringNotEmpty(accountName, checkEmptyExceptionMessageFormat("accountName"));
        return invokeHttpClient(createRequest(HttpMethodName.DELETE,
                BASE_PATH + "/" + clusterId + "/account/" + accountName), AbstractBceResponse.class);
    }

    public AccountListResponse listAccounts(String clusterId) {
        return listAccounts(clusterId, null);
    }

    public AccountListResponse listAccounts(String clusterId, String accountType) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        InternalRequest internalRequest = createRequest(HttpMethodName.GET, BASE_PATH + "/" + clusterId + "/account");
        addParameterIfNotNull(internalRequest, "type", accountType);
        return invokeHttpClient(internalRequest, AccountListResponse.class);
    }

    public AbstractBceResponse updateAccountPassword(String clusterId, String accountName,
                                                     UpdateAccountPasswordRequest request) {
        return updateAccount(clusterId, accountName, "password", request);
    }

    public AbstractBceResponse updateAccountPrivileges(String clusterId, String accountName,
                                                       AccountPrivilegesRequest request) {
        return updateAccount(clusterId, accountName, "privileges", request);
    }

    public AbstractBceResponse updateAccountAuthIp(String clusterId, String accountName,
                                                   UpdateAccountAuthIpRequest request) {
        return updateAccount(clusterId, accountName, "authip", request);
    }

    public AbstractBceResponse createDatabase(String clusterId, Database request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST,
                BASE_PATH + "/" + clusterId + "/database"), AbstractBceResponse.class);
    }

    public AbstractBceResponse deleteDatabase(String clusterId, String dbName) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkStringNotEmpty(dbName, checkEmptyExceptionMessageFormat("dbName"));
        return invokeHttpClient(createRequest(HttpMethodName.DELETE,
                BASE_PATH + "/" + clusterId + "/database/" + dbName), AbstractBceResponse.class);
    }

    public DatabaseListResponse listDatabases(String clusterId) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        return invokeHttpClient(createRequest(HttpMethodName.GET, BASE_PATH + "/" + clusterId + "/database"),
                DatabaseListResponse.class);
    }

    public AbstractBceResponse createSnapshot(String clusterId) {
        return createSnapshot(clusterId, null);
    }

    public AbstractBceResponse createSnapshot(String clusterId, SnapshotCreateRequest request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        if (request == null) {
            request = new SnapshotCreateRequest();
        }
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.POST,
                BASE_PATH + "/" + clusterId + "/snapshot"), AbstractBceResponse.class);
    }

    public SnapshotListResponse listSnapshots(String clusterId) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        return invokeHttpClient(createRequest(HttpMethodName.GET,
                BASE_PATH + "/" + clusterId + "/snapshot"), SnapshotListResponse.class);
    }

    public AbstractBceResponse updateSnapshotPolicy(String clusterId, SnapshotPolicyUpdateRequest request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + clusterId + "/snapshot/policy"), AbstractBceResponse.class);
    }

    public SnapshotPolicyListResponse getSnapshotPolicy(String clusterId) {
        return getSnapshotPolicy(clusterId, null);
    }

    public SnapshotPolicyListResponse getSnapshotPolicy(String clusterId, SnapshotPolicyListRequest request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        if (request == null) {
            request = new SnapshotPolicyListRequest();
        }
        InternalRequest internalRequest = createRequest(HttpMethodName.GET,
                BASE_PATH + "/" + clusterId + "/snapshot/policy");
        addParameterIfNotNull(internalRequest, "dataType", request.getDataType());
        addParameterIfNotNull(internalRequest, "clusterID", request.getClusterID());
        addParameterIfNotNull(internalRequest, "marker", request.getMarker());
        addParameterIfNotNull(internalRequest, "maxKeys", request.getMaxKeys());
        return invokeHttpClient(internalRequest, SnapshotPolicyListResponse.class);
    }

    public AbstractBceResponse updateWhitelist(String clusterId, WhitelistRequest request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + clusterId + "/whitelist"), AbstractBceResponse.class);
    }

    public WhitelistResponse getWhitelist(String clusterId) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        return invokeHttpClient(createRequest(HttpMethodName.GET, BASE_PATH + "/" + clusterId + "/whitelist"),
                WhitelistResponse.class);
    }

    public ComputeModuleParamsResponse getComputeModuleParams(String clusterId) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        return invokeHttpClient(createRequest(HttpMethodName.GET,
                BASE_PATH + "/cluster/" + clusterId + "/compute/params"), ComputeModuleParamsResponse.class);
    }

    public AbstractBceResponse updateComputeModuleParams(String clusterId, UpdateComputeModuleParamsRequest request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        return invokeHttpClient(createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/cluster/" + clusterId + "/compute/params"), AbstractBceResponse.class);
    }

    private AbstractBceResponse updateAccount(String clusterId, String accountName, String action,
                                              AbstractBceRequest request) {
        checkStringNotEmpty(clusterId, checkEmptyExceptionMessageFormat("clusterId"));
        checkStringNotEmpty(accountName, checkEmptyExceptionMessageFormat("accountName"));
        checkNotNull(request, REQUEST_NULL_ERROR_MESSAGE);
        InternalRequest internalRequest = createBodyRequest(request, HttpMethodName.PUT,
                BASE_PATH + "/" + clusterId + "/account/" + accountName);
        internalRequest.addParameter(action, "");
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }
}

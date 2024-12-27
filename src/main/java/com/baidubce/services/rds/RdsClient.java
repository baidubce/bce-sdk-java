package com.baidubce.services.rds;

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
import com.baidubce.services.rds.model.DatabaseEntryPort;
import com.baidubce.services.rds.model.DatabasePrivilege;
import com.baidubce.services.rds.model.DatabasePrivilegeList;
import com.baidubce.services.rds.model.PassWord;
import com.baidubce.services.rds.model.RdsAccount;
import com.baidubce.services.rds.model.RdsAccountInfoRequest;
import com.baidubce.services.rds.model.RdsAccountListRequest;
import com.baidubce.services.rds.model.RdsAccountListResponse;
import com.baidubce.services.rds.model.RdsAddress;
import com.baidubce.services.rds.model.RdsAutoRenewRequest;
import com.baidubce.services.rds.model.RdsBackupInfoRequest;
import com.baidubce.services.rds.model.RdsBackupInfoResponse;
import com.baidubce.services.rds.model.RdsBatchScalingRequest;
import com.baidubce.services.rds.model.RdsBatchScalingResponse;
import com.baidubce.services.rds.model.RdsBilling;
import com.baidubce.services.rds.model.RdsBindingTagsRequest;
import com.baidubce.services.rds.model.RdsChangeDatabasePortRequest;
import com.baidubce.services.rds.model.RdsClusterStatusCheckRequest;
import com.baidubce.services.rds.model.RdsClusterStatusCheckResponse;
import com.baidubce.services.rds.model.RdsConnInformationRequest;
import com.baidubce.services.rds.model.RdsCreateAccountRequest;
import com.baidubce.services.rds.model.RdsCreateDatabaseRequest;
import com.baidubce.services.rds.model.RdsCreateInstanceRequest;
import com.baidubce.services.rds.model.RdsCreateInstanceResponse;
import com.baidubce.services.rds.model.RdsCreateProxyInstance;
import com.baidubce.services.rds.model.RdsCreateReadableInstance;
import com.baidubce.services.rds.model.RdsDeleteAccountRequest;
import com.baidubce.services.rds.model.RdsDeleteDatabaseRequest;
import com.baidubce.services.rds.model.RdsDeleteSpecifiedBackUpRequest;
import com.baidubce.services.rds.model.RdsDialingTestRequest;
import com.baidubce.services.rds.model.RdsDialingTestResponse;
import com.baidubce.services.rds.model.RdsEngine;
import com.baidubce.services.rds.model.RdsFullPhysicalBackupRequest;
import com.baidubce.services.rds.model.RdsGetAutoConfigForSpecifiedRequest;
import com.baidubce.services.rds.model.RdsGetAutoConfigForSpecifiedResponse;
import com.baidubce.services.rds.model.RdsGetBackupListRequest;
import com.baidubce.services.rds.model.RdsGetBackupListResponse;
import com.baidubce.services.rds.model.RdsGetBinLogInfoRequest;
import com.baidubce.services.rds.model.RdsGetBinLogInfoRsponse;
import com.baidubce.services.rds.model.RdsGetBinLogListRequest;
import com.baidubce.services.rds.model.RdsGetBinLogListResponse;
import com.baidubce.services.rds.model.RdsGetDatabaseListRequest;
import com.baidubce.services.rds.model.RdsGetDatabaseListResponse;
import com.baidubce.services.rds.model.RdsGetNewPurchasePriceRequest;
import com.baidubce.services.rds.model.RdsGetNewPurchasePriceResponse;
import com.baidubce.services.rds.model.RdsGetOrderStatusRequest;
import com.baidubce.services.rds.model.RdsGetOrderStatusResponse;
import com.baidubce.services.rds.model.RdsGetPGListRequest;
import com.baidubce.services.rds.model.RdsGetPGListResponse;
import com.baidubce.services.rds.model.RdsGetPGLogDetailsRequest;
import com.baidubce.services.rds.model.RdsGetPGLogDetailsResponse;
import com.baidubce.services.rds.model.RdsGetPriceDifferenceRequest;
import com.baidubce.services.rds.model.RdsGetPriceDifferenceResponse;
import com.baidubce.services.rds.model.RdsInstanceDetailRequest;
import com.baidubce.services.rds.model.RdsInstanceDetailResponse;
import com.baidubce.services.rds.model.RdsInstanceListRequest;
import com.baidubce.services.rds.model.RdsInstanceListResponse;
import com.baidubce.services.rds.model.RdsInstanceName;
import com.baidubce.services.rds.model.RdsInstanceResizeRequest;
import com.baidubce.services.rds.model.RdsModifyAccountPasswordRequest;
import com.baidubce.services.rds.model.RdsModifyAccountPermissionRequest;
import com.baidubce.services.rds.model.RdsModifyAccountRemarksRequest;
import com.baidubce.services.rds.model.RdsModifyBackupRequest;
import com.baidubce.services.rds.model.RdsModifyDatabaseDescriptionRequest;
import com.baidubce.services.rds.model.RdsModifyParameterRequest;
import com.baidubce.services.rds.model.RdsNetworkStatusRequest;
import com.baidubce.services.rds.model.RdsParameterListRequest;
import com.baidubce.services.rds.model.RdsParameterListResponse;
import com.baidubce.services.rds.model.RdsPaymentTiming;
import com.baidubce.services.rds.model.RdsPublicAccess;
import com.baidubce.services.rds.model.RdsRecoverForBackUpSetRequest;
import com.baidubce.services.rds.model.RdsRecoverForTimeNodeRequest;
import com.baidubce.services.rds.model.RdsReleaseInstanceRequest;
import com.baidubce.services.rds.model.RdsReleaseInstanceResponse;
import com.baidubce.services.rds.model.RdsRemark;
import com.baidubce.services.rds.model.RdsRenewalRequest;
import com.baidubce.services.rds.model.RdsRenewalResponse;
import com.baidubce.services.rds.model.RdsReservation;
import com.baidubce.services.rds.model.RdsRestartRequest;
import com.baidubce.services.rds.model.RdsSlowLogDownloadDetailRequest;
import com.baidubce.services.rds.model.RdsSlowLogDownloadDetailResponse;
import com.baidubce.services.rds.model.RdsSlowLogDownloadTasksRequest;
import com.baidubce.services.rds.model.RdsSlowLogDownloadTasksResponse;
import com.baidubce.services.rds.model.RdsSlowLogErrorLogDownloadDetailsRequest;
import com.baidubce.services.rds.model.RdsSlowLogErrorLogDownloadDetailsResponse;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogDetailsRequest;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogDetailsResponse;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogListRequest;
import com.baidubce.services.rds.model.RdsSlowLogGetErrorLogListResponse;
import com.baidubce.services.rds.model.RdsSlowLogRequest;
import com.baidubce.services.rds.model.RdsSlowLogResponse;
import com.baidubce.services.rds.model.RdsSubnetRequest;
import com.baidubce.services.rds.model.RdsSubnetResponse;
import com.baidubce.services.rds.model.RdsSupportEnableAutoExpansionRequest;
import com.baidubce.services.rds.model.RdsSupportEnableAutoExpansionResponse;
import com.baidubce.services.rds.model.RdsSupportHotSwappingRequest;
import com.baidubce.services.rds.model.RdsSupportHotSwappingResponse;
import com.baidubce.services.rds.model.RdsSyncMode;
import com.baidubce.services.rds.model.RdsSyncModeRequest;
import com.baidubce.services.rds.model.RdsTag;
import com.baidubce.services.rds.model.RdsUpdateNameRequest;
import com.baidubce.services.rds.model.RdsUpdateStorageAutoExpansionConfigRequest;
import com.baidubce.services.rds.model.RdsUpdateStorageAutoExpansionConfigResPonse;
import com.baidubce.services.rds.model.RdsUpdateTimeWindowRequest;
import com.baidubce.services.rds.model.RdsUpdateWriteListResquest;
import com.baidubce.services.rds.model.RdsViewWriteListRequest;
import com.baidubce.services.rds.model.RdsViewWriteListResponse;
import com.baidubce.services.rds.model.RdsZoneMigrationRequest;
import com.baidubce.services.rds.model.RdsZoneRequest;
import com.baidubce.services.rds.model.RdsZoneResponse;
import com.baidubce.services.rds.model.Resource;
import com.baidubce.services.rds.model.SecurityIps;
import com.baidubce.util.HttpUtils;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Rds client, operate rds instance
 */
public class RdsClient extends AbstractBceClient {
    private static void print(String method, Object obj) {
        try {
            String json = JsonUtils.toJsonPrettyString(obj);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private static final String X_BCE_IF_MATCH = "x-bce-if-match";
    private static final String X_BCE_ACCESSKEY = "X-Bce-Accesskey";
    /**
     * Responsible for handling httpResponses from all rds service calls.
     */
    private static final HttpResponseHandler[] RDS_HANDLERS =
        new HttpResponseHandler[] {
            new BceMetadataResponseHandler(), new BceErrorResponseHandler(), new BceJsonResponseHandler()
        };

    private static final String BCE_HEADER_ETAG = "x-bce-if-match";

    /**
     * Generate signature with specified headers.
     */
    private static final String[] HEADERS_TO_SIGN = {"host", "x-bce-date"};
    private static final String REQUEST_KEY = "request";
    private static final String INSTANCE_ID_KEY = "instanceId";
    private static final String CLIENT_TOKEN = "clientToken";
    private static final String ACCOUNT_NAME_KEY = "accountName";
    private static final String DESC_KEY = "desc";

    public RdsClient() {
        this(new RdsClientConfiguration());
    }

    public RdsClient(BceClientConfiguration configuration) {
        this(configuration, RDS_HANDLERS);
    }

    public RdsClient(BceClientConfiguration config, HttpResponseHandler[] responseHandlers) {
        super(config, responseHandlers);
    }

    private InternalRequest createRequest(AbstractBceRequest request, HttpMethodName method, String... pathVariables) {
        return createRequest(null, request, method, pathVariables);
    }

    private InternalRequest createRequest(
        String version, AbstractBceRequest request, HttpMethodName method, String... pathVariables) {
        List<String> path = new ArrayList<String>();
        if (StringUtils.isEmpty(version)) {
            path.add(RdsPaths.VERSION);
        } else {
            path.add(version);
        }
        if (pathVariables != null) {
            Collections.addAll(path, pathVariables);
        }
        URI uri = HttpUtils.appendUri(this.getEndpoint(), path.toArray(new String[0]));
        InternalRequest internalRequest = new InternalRequest(method, uri);
        SignOptions signOptions = new SignOptions();
        signOptions.setHeadersToSign(new HashSet<String>(Arrays.asList(HEADERS_TO_SIGN)));
        internalRequest.setSignOptions(signOptions);
        internalRequest.setCredentials(request.getRequestCredentials());
        return internalRequest;
    }

    /**
     * The method to fill the internalRequest's content field with bceRequest.
     * Only support HttpMethodName.POST or HttpMethodName.PUT
     *
     * @param internalRequest A request object
     * @param request         The original request, as created by the user.
     */
    private void fillPayload(InternalRequest internalRequest, AbstractBceRequest request) {
        if (HttpMethodName.POST == internalRequest.getHttpMethod()
            || HttpMethodName.PUT == internalRequest.getHttpMethod()) {
            String strJson = JsonUtils.toJsonString(request);
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

    /**
     * The encryption implement for AES-128 algorithm for RDS password encryption.
     * Only the first 16 bytes of privateKey will be used to encrypt the content.
     * <p>
     * See more detail on
     * <a href = "https://cloud.baidu.com/doc/RDS/s/Ejwvz0uoq#密码加密传输规范定义">
     * RDS API doc</a>
     *
     * @param content    The content String to encrypt.
     * @param privateKey The security key to encrypt.
     *                   Only the first 16 bytes of privateKey will be used to encrypt the content.
     * @return The encrypted string of the original content with AES-128 algorithm.
     * @throws GeneralSecurityException
     */
    private String aes128WithFirst16Char(String content, String privateKey) throws GeneralSecurityException {
        byte[] crypted = null;
        SecretKeySpec skey = new SecretKeySpec(privateKey.substring(0, 16).getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skey);
        crypted = cipher.doFinal(content.getBytes());
        return new String(Hex.encodeHex(crypted));
    }

    /**
     * The default method to generate the random String for clientToken if the optional parameter clientToken
     * is not specified by the user.
     * <p>
     * The default algorithm is using {@link UUID} to generate a random UUID,
     *
     * @return An random String generated by {@link UUID}.
     */
    private String generateClientToken() {
        return UUID.randomUUID().toString();
    }

    private RdsBilling generateDefaultBilling() {
        RdsBilling billing = new RdsBilling();
        billing.setPaymentTiming(RdsPaymentTiming.Postpaid);
        return billing;
    }

    private RdsBilling generateDefaultBillingWithReservation() {
        RdsBilling billing = new RdsBilling();
        billing.setPaymentTiming(RdsPaymentTiming.Prepaid);
        RdsReservation reservation = new RdsReservation();
        reservation.setReservationLength(1);
        reservation.setReservationTimeUnit("month");
        return billing;
    }

    /**
     * Get the instance list of rds
     *
     * @param request the request
     * @return rds instance list
     */
    public RdsInstanceListResponse getRdsInstanceList(RdsInstanceListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkMaxKeys(request.getMaxKeys());
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsInstanceListResponse.class);
    }

    /**
     * Request the instance detail info
     *
     * @param request thd detail request
     * @return ths response of detail
     */
    public RdsInstanceDetailResponse getRdsInstanceDetail(RdsInstanceDetailRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsInstanceDetailResponse.class);
    }

    /**
     * Create rds account
     *
     * @param request the request of create account
     * @return the response of create account
     * @throws GeneralSecurityException the exception when encrypt
     */
    public AbstractBceResponse createAccount(RdsCreateAccountRequest request) throws GeneralSecurityException {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        // desc is needed
        RdsArgumentUtil.checkString(request.getDesc(), DESC_KEY);
        RdsInstanceDetailRequest detailRequest = new RdsInstanceDetailRequest();
        detailRequest.setInstanceId(request.getInstanceId());
        // request the instance detail
        RdsInstanceDetailResponse detailResponse = getRdsInstanceDetail(detailRequest);
        if (RdsEngine.MySQL != detailResponse.getEngine() && RdsEngine.SQLServer != detailResponse.getEngine()) {
            if (CollectionUtils.isNotEmpty(request.getDatabasePrivileges())) {
                throw new BceClientException("Rds databasePrivileges only support the engine mySql or SQLServer");
            }
        }
        String clientToken = request.getClientToken();
        if (StringUtils.isEmpty(clientToken)) {
            request.setClientToken(generateClientToken());
        }
        String[] paths = {RdsPaths.INSTANCE, request.getInstanceId(), RdsPaths.ACCOUNT};
        String sourcePassword = request.getPassword();
        String signedPassword = aes128WithFirst16Char(sourcePassword, config.getCredentials().getSecretKey());
        request.setPassword(signedPassword);
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        print("", internalRequest);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Request the account list of instance
     *
     * @param request the request of account list
     * @return all accounts of the instance
     */
    public RdsAccountListResponse getAccountList(RdsAccountListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.ACCOUNT};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsAccountListResponse.class);
    }

    /**
     * Request the specific account info
     *
     * @param request the request of specific account
     * @return the response of specific account info
     */
    public RdsAccount getSpecificAccountInfo(RdsAccountInfoRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getAccountName(), ACCOUNT_NAME_KEY);
        String instanceId = request.getInstanceId();
        String accountName = request.getAccountName();
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.ACCOUNT, accountName};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsAccount.class);
    }

    /**
     * ModifyAccountRemark
     *
     * @param request the request of modifyAccountRemark
     * @return the response of modifyAccountRemark
     */
    public AbstractBceResponse modifyAccountRemark(RdsModifyAccountRemarksRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getAccountName(), ACCOUNT_NAME_KEY);
        RdsArgumentUtil.checkNull(request.getRemark(), "remark");
        String instanceId = request.getInstanceId();
        String accountName = request.getAccountName();
        RdsRemark remark = new RdsRemark();
        remark.setRemark(request.getRemark());
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.ACCOUNT, accountName, "desc"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, remark);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * modifyAccountPermissions
     *
     * @param request the request of modifyAccountPermissions
     * @return the response of modifyAccountPermissions
     */
    public AbstractBceResponse modifyAccountPermissions(RdsModifyAccountPermissionRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), "instanceId");
        RdsArgumentUtil.checkString(request.getAccountName(), "accountName");
        RdsArgumentUtil.checkNull(request.getDatabasePrivileges(), "databasePrivileges");

        // only MySQL and SQL Server can set databasePrivileges
        RdsInstanceDetailRequest detailRequest = new RdsInstanceDetailRequest();
        detailRequest.setInstanceId(request.getInstanceId());
        // request the instance detail
        RdsInstanceDetailResponse detailResponse = getRdsInstanceDetail(detailRequest);
        if (RdsEngine.MySQL != detailResponse.getEngine() && RdsEngine.SQLServer != detailResponse.getEngine()) {
            if (CollectionUtils.isNotEmpty(request.getDatabasePrivileges())) {
                throw new BceClientException("Rds databasePrivileges only support the engine mySql or SQLServer");
            }
        }

        String instanceId = request.getInstanceId();
        String accountName = request.getAccountName();
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.ACCOUNT, accountName, "privileges"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addHeader(X_BCE_IF_MATCH, "v1");
        internalRequest.addHeader(X_BCE_ACCESSKEY, config.getCredentials().getAccessKeyId());

        List<DatabasePrivilege> privileges = new ArrayList<>();
        privileges.add(request.getDatabasePrivileges().get(0));

        DatabasePrivilegeList list = new DatabasePrivilegeList();
        list.setDatabasePrivileges(privileges);
        fillPayload(internalRequest, list);
        print("", internalRequest);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * modifyAccountPassword
     *
     * @param request the request of modifyAccountPassword
     * @return the response of modifyAccountPassword
     */
    public AbstractBceResponse modifyAccountPassword(RdsModifyAccountPasswordRequest request)
        throws GeneralSecurityException {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getAccountName(), ACCOUNT_NAME_KEY);
        String sourcePassword = request.getPassword();
        String signedPassword = aes128WithFirst16Char(sourcePassword, config.getCredentials().getSecretKey());
        request.setPassword(signedPassword);
        String instanceId = request.getInstanceId();
        String accountName = request.getAccountName();
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.ACCOUNT, accountName, "password"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addHeader(X_BCE_ACCESSKEY, config.getCredentials().getAccessKeyId());

        PassWord passWord = new PassWord();
        passWord.setPassword(request.getPassword());

        fillPayload(internalRequest, passWord);
        print("", internalRequest);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * deleteAccount
     *
     * @param request the request of deleteAccount
     * @return the response of deleteAccount
     */
    public AbstractBceResponse deleteAccount(RdsDeleteAccountRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getAccountName(), ACCOUNT_NAME_KEY);
        String instanceId = request.getInstanceId();
        String accountName = request.getAccountName();
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.ACCOUNT, accountName};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, paths);
        return invokeHttpClient(internalRequest, RdsAccount.class);
    }

    /**
     * Resize the instance
     *
     * @param request the request of resize rds instance
     * @return ths response of resize rds instance
     */
    public AbstractBceResponse resizeInstance(RdsInstanceResizeRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        Integer cpuCount = request.getCpuCount();
        Integer memoryCapacity = request.getMemoryCapacity();
        Integer volumeCapacity = request.getVolumeCapacity();
        if (cpuCount == null && memoryCapacity == null && volumeCapacity == null) {
            String msg = "Please set one of cpuCount,memoryCapacity,volumeCapacity when resize instance";
            throw new BceClientException(msg);
        }
        // check volumeCapacity whether multiple of 5
        RdsArgumentUtil.checkVolumeCapacity(volumeCapacity);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter("resize", null);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create rds instance
     *
     * @param request the request of create rds instance
     * @return response the response of Create rds instance
     */
    public RdsCreateInstanceResponse createInstance(RdsCreateInstanceRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        if (StringUtils.isEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        if (request.getBilling() == null) {
            request.setBilling(generateDefaultBilling());
        }
        RdsArgumentUtil.checkPurchaseCount(request.getPurchaseCount());
        RdsArgumentUtil.checkEngine(request.getEngine());
        RdsArgumentUtil.checkEngineVersion(request.getEngineVersion());
        RdsArgumentUtil.checkCpuCount(request.getCpuCount());
        RdsArgumentUtil.checkMemoryCapacity(request.getMemoryCapacity());
        RdsArgumentUtil.checkVolumeCapacity(request.getVolumeCapacity());
        RdsArgumentUtil.checkCharacterSetName(request.getCharacterSetName());
        RdsArgumentUtil.checkAutoRenew(request);
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RdsCreateInstanceResponse.class);
    }

    /**
     * Request the slow log of rds instance
     *
     * @param request the request of slow log
     * @return the response of Request the slow log of rds instance
     */
    public RdsSlowLogResponse getSlowLog(RdsSlowLogRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        RdsArgumentUtil.checkNull(request.getStartTime(), "startTime");
        RdsArgumentUtil.checkNull(request.getEndTime(), "endTime");
        RdsArgumentUtil.checkPage(request.getPageNo(), request.getPageSize());
        String[] paths = {RdsPaths.INSTANCE, instanceId, "slowlogs", "details"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RdsSlowLogResponse.class);
    }

    /**
     * Auto renew the rds instance
     *
     * @param request the request to auto renew rds instance
     * @return the response of Auto renew the rds instance
     */
    public AbstractBceResponse autoRenew(RdsAutoRenewRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        if (CollectionUtils.isEmpty(request.getInstanceIds())) {
            throw new BceClientException("Please set instanceIds");
        }
        RdsArgumentUtil.checkAutoRenew(request.getAutoRenewTimeUnit(), request.getAutoRenewTime());
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter("autoRenew", null);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Create a readable rds instance
     *
     * @param request the request of instance creation
     * @return the response of Create a readable rds instance
     */
    public RdsCreateInstanceResponse createInstanceReadableReplica(RdsCreateReadableInstance request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getSourceInstanceId(), "sourceInstanceId");
        if (StringUtils.isEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        if (request.getBilling() == null) {
            request.setBilling(generateDefaultBilling());
        } else if (request.getBilling().getPaymentTiming() == RdsPaymentTiming.Prepaid) {
            throw new BceClientException("Prepaid is not support for rds read replica instance");
        }
        Integer purchaseCount = request.getPurchaseCount();
        if (purchaseCount != null && purchaseCount != 1) {
            throw new BceClientException("Currently only supported purchaseCount 1");
        }
        RdsArgumentUtil.checkCpuCount(request.getCpuCount());
        RdsArgumentUtil.checkMemoryCapacity(request.getMemoryCapacity());
        RdsArgumentUtil.checkVolumeCapacity(request.getVolumeCapacity());
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        internalRequest.addParameter("readReplica", null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RdsCreateInstanceResponse.class);
    }

    /**
     * Create a rds proxy instance
     *
     * @param request the request to create rds proxy instance
     * @return the response of Create a rds proxy instance
     */
    public RdsCreateInstanceResponse createInstanceProxy(RdsCreateProxyInstance request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getSourceInstanceId(), "sourceInstanceId");
        if (StringUtils.isEmpty(request.getClientToken())) {
            request.setClientToken(generateClientToken());
        }
        if (request.getBilling() == null) {
            request.setBilling(generateDefaultBilling());
        } else if (request.getBilling().getPaymentTiming() == RdsPaymentTiming.Prepaid) {
            throw new BceClientException("Prepaid is not support for rds proxy instance");
        }
        Integer nodeAmount = request.getNodeAmount();
        RdsArgumentUtil.checkNodeAmount(nodeAmount);
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        internalRequest.addParameter("rdsproxy", null);
        internalRequest.addParameter(CLIENT_TOKEN, request.getClientToken());
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RdsCreateInstanceResponse.class);
    }

    /**
     * Request the rds zone list
     *
     * @return the response
     */
    public RdsZoneResponse getZoneList() {
        String[] paths = {RdsPaths.ZONE};
        InternalRequest internalRequest = createRequest(new RdsZoneRequest(), HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsZoneResponse.class);
    }

    /**
     * Request the rds subnet list
     *
     * @param request the request of rds subnet
     * @return the response of rds subnet
     */
    public RdsSubnetResponse getSubnetList(RdsSubnetRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String[] paths = {RdsPaths.SUBNET};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        if (StringUtils.isNotEmpty(request.getVpcId())) {
            internalRequest.addParameter("vpcId", request.getVpcId());
        }
        if (StringUtils.isNotEmpty(request.getZoneName())) {
            internalRequest.addParameter("zoneName", request.getZoneName());
        }
        return invokeHttpClient(internalRequest, RdsSubnetResponse.class);
    }

    /**
     * view write list
     *
     * @param request the request of view write list
     * @return response the response of view write list
     */
    public RdsViewWriteListResponse viewWriteList(RdsViewWriteListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), "instanceId");
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "securityIp"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsViewWriteListResponse.class);
    }

    /**
     *Rds Update WriteList
     *
     * @param request the request of Rds Update WriteList
     * @return response the response of Rds Update WriteList
     */
    public AbstractBceResponse updateWriteList(RdsUpdateWriteListResquest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), "instanceId");
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "securityIp"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addHeader(X_BCE_IF_MATCH, "v1");
        SecurityIps securityIps = new SecurityIps();
        securityIps.setSecurityIps(request.getSecurityIps());
        fillPayload(internalRequest, securityIps);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     *RDS get Order Status
     *
     * @param request the request of RDS get Order Status
     * @return response the response of RDS get Order Status
     */
    public RdsGetOrderStatusResponse getOrderStatus(RdsGetOrderStatusRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String orderId = request.getOrderId();
        String[] paths = {RdsPaths.INSTANCE, "order", orderId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsGetOrderStatusResponse.class);
    }

    /**
     * Release the specific rds instance
     *
     * @param request the request to release rds instance
     * @return the response
     */
    public RdsReleaseInstanceResponse releaseInstance(RdsReleaseInstanceRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        if (CollectionUtils.isEmpty(request.getInstanceIds())) {
            throw new BceClientException("instanceIds can not be empty");
        } else if (request.getInstanceIds().size() > 10) {
            throw new BceClientException("instanceIds can not be greater than 10");
        }
        String[] paths = {RdsPaths.INSTANCE};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, paths);
        internalRequest.addParameter("instanceIds", convertInstanceIds(request.getInstanceIds()));
        return invokeHttpClient(internalRequest, RdsReleaseInstanceResponse.class);
    }

    /**
     * convert the instanceIds to request parameter
     *
     * @param instanceIds the list of instance id
     * @return the request parameter string
     */
    private String convertInstanceIds(List<String> instanceIds) {
        StringBuilder builder = new StringBuilder();
        for (String instanceId : instanceIds) {
            if (builder.length() == 0) {
                builder.append(instanceId);
            } else {
                builder.append(",").append(instanceId);
            }
        }
        return builder.toString();
    }

    /**
     * Create rds database
     *
     * @param request the request of create database
     * @return the response
     */
    public AbstractBceResponse createDatabase(RdsCreateDatabaseRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getDbName(), "dbName");
        // RdsArgumentUtil.checkString(request.getRemark(), "remark");
        if (request.getRemark() == null) {
            request.setRemark("");
        }
        RdsArgumentUtil.checkNull(request.getCharacterSetName(), "characterSetName");
        RdsInstanceDetailRequest detailRequest = new RdsInstanceDetailRequest();
        detailRequest.setInstanceId(request.getInstanceId());
        RdsInstanceDetailResponse detailResponse = getRdsInstanceDetail(detailRequest);
        if (detailResponse.getEngine() == RdsEngine.PostgreSQL) {
            throw new BceClientException("can not create database for engine " + RdsEngine.PostgreSQL);
        }
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.DATABASES};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete rds database
     *
     * @param request the request of delete rds database
     * @return the response of delete database
     */
    public AbstractBceResponse deleteDatabase(RdsDeleteDatabaseRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String dbName = request.getDbName();
        RdsArgumentUtil.checkString(dbName, "dbName");
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.DATABASES, dbName};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, paths);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * changeDatabasePort
     *
     * @param request the request of changeDatabasePort
     * @return the response of changeDatabasePort
     */
    public AbstractBceResponse changeDatabasePort(RdsChangeDatabasePortRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkNull(request.getEntryPort(), "entryPort");
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "port"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        DatabaseEntryPort entryPort = new DatabaseEntryPort();
        entryPort.setEntryPort(request.getEntryPort());
        fillPayload(internalRequest, entryPort);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * getDatabaseList
     *
     * @param request the request of getDatabaseList
     * @return the response of getDatabaseList
     */
    public RdsGetDatabaseListResponse getDatabaseList(RdsGetDatabaseListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "databases"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsGetDatabaseListResponse.class);
    }

    /**
     * modifyDatabaseDescription
     *
     * @param request the request of modifyDatabaseDescription
     * @return the response of modifyDatabaseDescription
     */
    public AbstractBceResponse modifyDatabaseDescription(RdsModifyDatabaseDescriptionRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getDbName(), "dbName");
        RdsArgumentUtil.checkString(request.getRemark(), "remark");
        String instanceId = request.getInstanceId();
        String dbName = request.getDbName();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "databases", dbName, "remark"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Modify the rds backup policy
     *
     * @param request the request of modify rds backup policy
     * @return the response of modifyBackup
     */
    public AbstractBceResponse modifyBackup(RdsModifyBackupRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String backupDays = request.getBackupDays();
        RdsArgumentUtil.checkString(backupDays, "backupDays");
        String[] days = backupDays.split(",");
        if (days.length == 0) {
            throw new BceClientException("backupDays must be split by ','");
        }
        RdsArgumentUtil.checkString(request.getBackupTime(), "backupTime");
        Integer expireInDays = request.getExpireInDays();
        if (expireInDays != null && (expireInDays < 7 || expireInDays > 730)) {
            throw new BceClientException("expireInDays must range in [7,730]");
        }
        String[] paths = {RdsPaths.INSTANCE, instanceId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        request.setInstanceId(null);
        fillPayload(internalRequest, request);
        internalRequest.addParameter("modifyBackupPolicy", null);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Get the rds backup list
     *
     * @param request the request of get rds backup list
     * @return the response of getBackupList
     */
    public RdsGetBackupListResponse getBackupList(RdsGetBackupListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.BACKUP};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        if (StringUtils.isNotEmpty(request.getMarker())) {
            internalRequest.addParameter("marker", request.getMarker());
        }
        Integer maxKeys = request.getMaxKeys();
        if (maxKeys != null) {
            if (maxKeys > 1000) {
                throw new BceClientException("maxKeys can not be greater than 1000");
            }
            internalRequest.addParameter("maxKeys", String.valueOf(maxKeys));
        }
        return invokeHttpClient(internalRequest, RdsGetBackupListResponse.class);
    }

    /**
     * Get the rds backup info
     *
     * @param request the request of get the rds backup info
     * @return the response of getBackupInfo
     */
    public RdsBackupInfoResponse getBackupInfo(RdsBackupInfoRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String backupId = request.getBackupId();
        RdsArgumentUtil.checkString(backupId, "backupId");
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.BACKUP, backupId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsBackupInfoResponse.class);
    }

    /**
     * Manually initiate a full physical backup
     *
     * @param request the request of Manually initiate a full physical backup
     * @return the response of manuallyInitiateFullPhysicalBackup
     */
    public AbstractBceResponse manuallyInitiateFullPhysicalBackup(RdsFullPhysicalBackupRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getEffectiveTime(), "effectiveTime");
        RdsArgumentUtil.checkString(request.getDataBackupType(), "dataBackupType");
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.BACKUP};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Delete the specified backup set
     *
     * @param request the request of DeleteTheSpecifiedBackupSet
     * @return the response of DeleteTheSpecifiedBackupSet
     */
    public AbstractBceResponse deleteTheSpecifiedBackupSet(RdsDeleteSpecifiedBackUpRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getSnapshotId(), "snapshotId");
        String instanceId = request.getInstanceId();
        String snapshotId = request.getSnapshotId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "backup", snapshotId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.DELETE, paths);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * getBinLogList
     *
     * @param request the request of getBinLogList
     * @return the response of getBinLogList
     */
    public RdsGetBinLogListResponse getBinLogList(RdsGetBinLogListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getDatetime(), "datetime");
        String instanceId = request.getInstanceId();
        String datetime = request.getDatetime();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "binlogs", datetime};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsGetBinLogListResponse.class);
    }

    /**
     * getBinLogInfo
     *
     * @param request the request of getBinLogInfo
     * @return the response of getBinLogInfo
     */
    public RdsGetBinLogInfoRsponse getBinLogInfo(RdsGetBinLogInfoRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getLogId(), "logId");
        RdsArgumentUtil.checkString(request.getDownloadValidTimeInSec(), "downloadValidTimeInSec");
        String instanceId = request.getInstanceId();
        String logId = request.getLogId();
        String downloadValidTimeInSec = request.getDownloadValidTimeInSec();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "binlogs", logId, downloadValidTimeInSec};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsGetBinLogInfoRsponse.class);
    }

    /**
     * recover Database Table For TimeNode
     *
     * @param request the request of recover Database Table For TimeNode
     * @return response the response of recover Database Table For TimeNode
     */
    public AbstractBceResponse recoverForTimeNode(RdsRecoverForTimeNodeRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getDatetime(), "datetime");
        RdsArgumentUtil.checkNull(request.getData(), "data");
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "recoveryToSourceInstanceByDatetime"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * recover Database Table For BackUp Set
     *
     * @param request the request of recover Database Table For BackUp Set
     * @return response the response of recover Database Table For BackUp Set
     */
    public AbstractBceResponse recoverForBackUpSet(RdsRecoverForBackUpSetRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getSnapshotId(), "snapshotId");
        RdsArgumentUtil.checkNull(request.getData(), "data");
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "recoveryToSourceInstanceBySnapshot"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Get the rds slow log download task list
     *
     * @param request the request of download rds slow log list
     * @return the response of getSlowLogDownloadTaskList
     */
    public RdsSlowLogDownloadTasksResponse getSlowLogDownloadTaskList(RdsSlowLogDownloadTasksRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String datetime = request.getDatetime();
        RdsArgumentUtil.checkString(datetime, "datetime");
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.SLOW_LOGS, RdsPaths.LOG_LIST, datetime};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsSlowLogDownloadTasksResponse.class);
    }

    /**
     * Get the detail info of downloading rds slow log
     *
     * @param request the request of getSlowLogDownloadDetail
     * @return the response of getSlowLogDownloadDetail
     */
    public RdsSlowLogDownloadDetailResponse getSlowLogDownloadDetail(RdsSlowLogDownloadDetailRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String logId = request.getLogId();
        RdsArgumentUtil.checkString(logId, "logId");
        Integer time = request.getDownloadValidTimeInSec();
        RdsArgumentUtil.checkNull(time, "downloadValidTimeInSec");
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.SLOW_LOGS, "download_url", logId, time.toString()};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsSlowLogDownloadDetailResponse.class);
    }

    /**
     * Rds Get ErrorLog Details
     *
     * @param request the request of Rds Get ErrorLog Details
     * @return response the response of Rds Get ErrorLog Details
     */
    public RdsSlowLogGetErrorLogDetailsResponse getSlowLogGetErrorLogDetails(
        RdsSlowLogGetErrorLogDetailsRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String[] paths = {RdsPaths.INSTANCE, instanceId, "errorlogs", "details"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        return invokeHttpClient(internalRequest, RdsSlowLogGetErrorLogDetailsResponse.class);
    }

    /**
     * RDS SlowLog ErrorLog Download Details
     *
     * @param request the request of RDS SlowLog ErrorLog Download Details
     * @return response the response of RDS SlowLog ErrorLog Download Details
     */
    public RdsSlowLogErrorLogDownloadDetailsResponse getSlowLogErrorLogDownloadDetails(
        RdsSlowLogErrorLogDownloadDetailsRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getLogId(), "logId");
        RdsArgumentUtil.checkNull(request.getDownloadValidTimeInSec(), "downloadValidTimeInSec");
        String instanceId = request.getInstanceId();
        String logId = request.getLogId();
        Integer downloadValidTimeInSec = request.getDownloadValidTimeInSec();
        String[] paths = {
            RdsPaths.INSTANCE, instanceId, "errorlogs", "download_url", logId, downloadValidTimeInSec.toString()
        };
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsSlowLogErrorLogDownloadDetailsResponse.class);
    }

    /**
     *Rds SlowLog Get Error Log List
     *
     * @param request the request of Rds SlowLog Get Error Log List
     * @return response the response of Rds SlowLog Get Error Log List
     */
    public RdsSlowLogGetErrorLogListResponse slowLogGetErrorLogList(RdsSlowLogGetErrorLogListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String datetime = request.getDatetime();
        RdsArgumentUtil.checkString(datetime, "datetime");
        String[] paths = {RdsPaths.INSTANCE, instanceId, "errorlogs", "logList", datetime};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsSlowLogGetErrorLogListResponse.class);
    }

    /**
     *Rds Get PG Log Details
     *
     * @param request the request of Rds Get PG Log Details
     * @return response the response of Rds Get PG Log Details
     */
    public RdsGetPGLogDetailsResponse getPGLogDetails(RdsGetPGLogDetailsRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        RdsArgumentUtil.checkString(request.getPglogId(), "pglogId");
        RdsArgumentUtil.checkNull(request.getDownloadValidTimeInSec(), "downloadValidTimeInSec");
        String instanceId = request.getInstanceId();
        String pglogId = request.getPglogId();
        Integer downloadValidTimeInSec = request.getDownloadValidTimeInSec();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "pg", "download"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        internalRequest.addParameter("pglogId", pglogId);
        internalRequest.addParameter("downloadValidTimeInSec", downloadValidTimeInSec.toString());
        return invokeHttpClient(internalRequest, RdsGetPGLogDetailsResponse.class);
    }

    /**
     *get PG List
     *
     * @param request the request of get PG List
     * @return response the response of get PG List
     */
    public RdsGetPGListResponse getPGList(RdsGetPGListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        String date = request.getDate();
        RdsArgumentUtil.checkString(date, "date");
        RdsArgumentUtil.checkString(instanceId, "instanceId");
        String[] paths = {RdsPaths.INSTANCE, instanceId, "pg", "list"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        internalRequest.addParameter("date", date);
        return invokeHttpClient(internalRequest, RdsGetPGListResponse.class);
    }

    /**
     * Get the rds parameter list
     *
     * @param request the request of get the rds parameter list
     * @return the response of getParameterList
     */
    public RdsParameterListResponse getParameterList(RdsParameterListRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.PARAMETER};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        return invokeHttpClient(internalRequest, RdsParameterListResponse.class);
    }

    /**
     * Modify rds parameter
     *
     * @param request the request of modify rds parameter
     * @return the response of modifyParameter
     */
    public AbstractBceResponse modifyParameter(RdsModifyParameterRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkString(instanceId, INSTANCE_ID_KEY);
        String etag = request.getEtag();
        RdsArgumentUtil.checkString(etag, "etag");
        if (CollectionUtils.isEmpty(request.getParameters())) {
            throw new BceClientException("The parameters can not be null or empty");
        }
        String[] paths = {RdsPaths.INSTANCE, instanceId, RdsPaths.PARAMETER};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        request.setInstanceId(null);
        internalRequest.addHeader(BCE_HEADER_ETAG, etag);
        request.setEtag(null);
        fillPayload(internalRequest, request);
        AbstractBceResponse response = invokeHttpClient(internalRequest, AbstractBceResponse.class);
        // restore instanceId and etag
        request.setInstanceId(instanceId);
        request.setEtag(etag);
        return response;
    }

    /**
     * Update rds public network access status
     *
     * @param request the request of Update rds public network access status
     * @return the response
     */
    public AbstractBceResponse updateRdsPublicNetworkAccessStatus(RdsNetworkStatusRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();

        RdsPublicAccess rdsPublicAccess = new RdsPublicAccess();
        rdsPublicAccess.setPublicAccess(request.isPublicAccess());

        String[] paths = {RdsPaths.INSTANCE, instanceId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter("modifyPublicAccess", null);
        fillPayload(internalRequest, rdsPublicAccess);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update instance sync mode
     *
     * @param request the request of Update instance sync mode
     * @return the response
     */
    public AbstractBceResponse updateRdsSyncMode(RdsSyncModeRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();

        RdsSyncMode rdsSyncMode = new RdsSyncMode();
        rdsSyncMode.setSyncMode(request.getSyncMode());

        String[] paths = {RdsPaths.INSTANCE, instanceId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter("modifySyncMode", null);

        fillPayload(internalRequest, rdsSyncMode);
        return invokeHttpClient(internalRequest, AbstractBceResponse.class);
    }

    /**
     * Update instance name
     *
     * @param request the request of Update instance name
     * @return the response
     */
    public AbstractBceResponse updateRdsName(RdsUpdateNameRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId};

        RdsInstanceName instanceName = new RdsInstanceName();
        instanceName.setInstanceName(request.getInstanceName());

        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter("rename", null);
        fillPayload(internalRequest, instanceName);
        AbstractBceResponse response = invokeHttpClient(internalRequest, AbstractBceResponse.class);
        return response;
    }

    /**
     * Update instance connection information
     *
     * @param request the request of Update instance connection information
     * @return the response
     */
    public AbstractBceResponse updateRdsConnectionInformation(RdsConnInformationRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter("modifyEndpoint", null);

        RdsAddress address = new RdsAddress();
        address.setAddress(request.getAddress());

        fillPayload(internalRequest, address);
        AbstractBceResponse response = invokeHttpClient(internalRequest, AbstractBceResponse.class);
        return response;
    }

    /**
     * Restart the instance
     *
     * @param request the request of Restart the instance
     * @return the response
     */
    public AbstractBceResponse rdsRestart(RdsRestartRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        String effectiveTime = request.geteffectiveTime();
        String[] paths = {RdsPaths.INSTANCE, instanceId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter("reboot", null);
        AbstractBceResponse response = invokeHttpClient(internalRequest, AbstractBceResponse.class);
        return response;
    }

    /**
     * Renewal instance
     *
     * @param request the request of Renewal instance
     * @return the response
     */
    public RdsRenewalResponse rdsRenewal(RdsRenewalRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkNull(request.getDuration(), "duration");
        String[] paths = {RdsPaths.INSTANCE, "renew"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        RdsRenewalResponse response = invokeHttpClient(internalRequest, RdsRenewalResponse.class);
        return response;
    }

    /**
     * Instance Update Time Window
     *
     * @param request the request of Instance Update Time Window
     * @return the response
     */
    public AbstractBceResponse rdsUpdateTimeWindow(RdsUpdateTimeWindowRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkNull(request.getMaintainStartTime(), "maintainStartTime");
        RdsArgumentUtil.checkNull(request.getMaintainDuration(), "maintainDuration");
        String[] paths = {RdsPaths.INSTANCE, instanceId, "maintaintime"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        AbstractBceResponse response = invokeHttpClient(internalRequest, AbstractBceResponse.class);
        return response;
    }

    /**
     * Instance Update storage automatic expansion configuration
     *
     * @param request the request of Instance Update storage automatic expansion configuration
     * @return the response
     */
    public RdsUpdateStorageAutoExpansionConfigResPonse updateRdsStorageAutoExpansionConfig(
        RdsUpdateStorageAutoExpansionConfigRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        String action = request.getAction();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "diskAutoResize", "config", action};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        RdsUpdateStorageAutoExpansionConfigResPonse resPonse =
            invokeHttpClient(internalRequest, RdsUpdateStorageAutoExpansionConfigResPonse.class);
        return resPonse;
    }

    /**
     * Get the automatic scaling configuration information for the specified instance
     *
     * @param request the request of Get the automatic scaling configuration information for the specified instance
     * @return the response
     */
    public RdsGetAutoConfigForSpecifiedResponse getAutoConfigForSpecified(RdsGetAutoConfigForSpecifiedRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "autoResizeConfig"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        RdsGetAutoConfigForSpecifiedResponse response =
            invokeHttpClient(internalRequest, RdsGetAutoConfigForSpecifiedResponse.class);
        return response;
    }

    /**
     * Does the instance support enabling automatic scaling
     *
     * @param request the request of Does the instance support enabling automatic scaling
     * @return the response
     */
    public RdsSupportEnableAutoExpansionResponse supportEnableAutoExpansion(
        RdsSupportEnableAutoExpansionRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "autoExpansion"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        RdsSupportEnableAutoExpansionResponse response =
            invokeHttpClient(internalRequest, RdsSupportEnableAutoExpansionResponse.class);
        return response;
    }

    /**
     * Available zone migration
     *
     * @param request the request of instance Available zone migration
     * @return the response
     */
    public AbstractBceResponse availableZoneMigration(RdsZoneMigrationRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkNull(request.getMasterAzone(), "master_azone");
        RdsArgumentUtil.checkNull(request.getBackupAzone(), "backup_azone");
        if (CollectionUtils.isEmpty(request.getZoneNames())) {
            throw new BceClientException("Please set ZoneNames in request.");
        }
        if (CollectionUtils.isEmpty(request.getSubnets())) {
            throw new BceClientException("Please set subNets in request.");
        }
        RdsArgumentUtil.checkNull(request.getEffectiveTime(), "effectiveTime");
        String[] paths = {RdsPaths.INSTANCE, instanceId, "azoneMigration"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        fillPayload(internalRequest, request);
        AbstractBceResponse response = invokeHttpClient(internalRequest, AbstractBceResponse.class);
        return response;
    }

    /**
     * Binding tags
     *
     * @param request the request of instance Binding tags
     * @return the response
     */
    public AbstractBceResponse bindingTags(RdsBindingTagsRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkNull(request.getResources(), "resources");
        Resource resource = request.getResources().get(0);
        resource.getInstanceId();
        RdsTag tag = resource.getTags().get(0);
        tag.getTagKey();
        tag.getTagValue();
        String[] paths = {RdsPaths.INSTANCE, "tags"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        AbstractBceResponse response = invokeHttpClient(internalRequest, AbstractBceResponse.class);
        return response;
    }

    /**
     * Cluster status check
     *
     * @param request the request of Cluster status check
     * @return the response
     */
    public RdsClusterStatusCheckResponse clusterStatusCheck(RdsClusterStatusCheckRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "status"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        RdsClusterStatusCheckResponse response = invokeHttpClient(internalRequest, RdsClusterStatusCheckResponse.class);
        return response;
    }

    /**
     * Batch scaling of instances
     *
     * @param request the request of Batch scaling  instances
     * @return the response
     */
    public RdsBatchScalingResponse batchScaling(RdsBatchScalingRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkString(request.getInstanceId(), INSTANCE_ID_KEY);
        String instanceId = request.getInstanceId();
        Integer cpuCount = request.getCpuCount();
        Integer memoryCapacity = request.getMemoryCapacity();
        Integer volumeCapacity = request.getVolumeCapacity();
        if (cpuCount == null && memoryCapacity == null && volumeCapacity == null) {
            String msg = "Please set one of cpuCount,memoryCapacity,volumeCapacity when resize instance";
            throw new BceClientException(msg);
        }

        // check volumeCapacity whether multiple of 5
        RdsArgumentUtil.checkVolumeCapacity(volumeCapacity);
        String[] paths = {RdsPaths.INSTANCE, instanceId};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.PUT, paths);
        internalRequest.addParameter("resize", null);
        fillPayload(internalRequest, request);
        RdsBatchScalingResponse response = invokeHttpClient(internalRequest, RdsBatchScalingResponse.class);
        return response;
    }

    /**
     * Dialing test interface
     *
     * @param request the request of Dialing test interface
     * @return the response
     */
    public RdsDialingTestResponse dialingTest(RdsDialingTestRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        String[] paths = {RdsPaths.INSTANCE, instanceId, "probe"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.GET, paths);
        RdsDialingTestResponse response = invokeHttpClient(internalRequest, RdsDialingTestResponse.class);
        return response;
    }

    /**
     * Obtain the price difference for expansion in prepaid mode
     *
     * @param request the request of Obtain the price difference for expansion in prepaid mode
     * @return the response
     */
    public RdsGetPriceDifferenceResponse getPriceDifference(RdsGetPriceDifferenceRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkNull(request.getInstanceId(), "instanceId");
        RdsArgumentUtil.checkNull(request.getCpuCount(), "cpuCount");
        RdsArgumentUtil.checkNull(request.getAllocatedMemoryInGB(), "allocatedMemoryInGB");
        RdsArgumentUtil.checkNull(request.getAllocatedStorageInGB(), "allocatedStorageInGB");
        RdsArgumentUtil.checkNull(request.getDiskIoType(), "diskIoType");
        String[] paths = {RdsPaths.INSTANCE, "price", "diff"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        RdsGetPriceDifferenceResponse response = invokeHttpClient(internalRequest, RdsGetPriceDifferenceResponse.class);
        return response;
    }

    /**
     * Obtain new purchase price
     *
     * @param request the request of Obtain new purchase price
     * @return the response
     */
    public RdsGetNewPurchasePriceResponse getNewPurchasePrice(RdsGetNewPurchasePriceRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        RdsArgumentUtil.checkNull(request.getProductType(), "productType");
        // 获取 instance 对象并检查其属性
        RdsGetNewPurchasePriceRequest.Instance instance = request.getInstance();
        if (instance != null) {
            // 检查 instance 的属性是否为空
            RdsArgumentUtil.checkNull(instance.getEngine(), "engine");
            RdsArgumentUtil.checkNull(instance.getDiskIoType(), "diskIoType");
            RdsArgumentUtil.checkNull(instance.getCpuCount(), "cpuCount");
            RdsArgumentUtil.checkNull(instance.getAllocatedMemoryInGB(), "allocatedMemoryInGB");
            RdsArgumentUtil.checkNull(instance.getAllocatedStorageInGB(), "allocatedStorageInGB");
        } else {
            // 如果 instance 为 null，抛出异常
            RdsArgumentUtil.checkNull(instance, "Instance cannot be null");
        }
        String[] paths = {RdsPaths.INSTANCE, "price"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        RdsGetNewPurchasePriceResponse response =
            invokeHttpClient(internalRequest, RdsGetNewPurchasePriceResponse.class);
        return response;
    }

    /**
     * Check if the instance supports hot swapping
     *
     * @param request the request of Check if the instance supports hot swapping
     * @return the response
     */
    public RdsSupportHotSwappingResponse supportHotSwapping(RdsSupportHotSwappingRequest request) {
        RdsArgumentUtil.checkNull(request, REQUEST_KEY);
        String instanceId = request.getInstanceId();
        RdsArgumentUtil.checkNull(request.getInstanceId(), "instanceId");
        RdsArgumentUtil.checkNull(request.getCpuCount(), "cpuCount");
        RdsArgumentUtil.checkNull(request.getAllocatedMemoryInMB(), "allocatedMemoryInMB");
        RdsArgumentUtil.checkNull(request.getAllocatedStorageInGB(), "allocatedStorageInGB");
        RdsArgumentUtil.checkNull(request.getMasterAzone(), "masterAzone");
        RdsArgumentUtil.checkNull(request.getSubnetId(), "subnetId");
        String[] paths = {RdsPaths.INSTANCE, instanceId, "checkHotUpgrade"};
        InternalRequest internalRequest = createRequest(request, HttpMethodName.POST, paths);
        fillPayload(internalRequest, request);
        RdsSupportHotSwappingResponse response = invokeHttpClient(internalRequest, RdsSupportHotSwappingResponse.class);
        return response;
    }
}
